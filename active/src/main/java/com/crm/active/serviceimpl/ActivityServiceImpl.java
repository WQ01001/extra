package com.crm.active.serviceimpl;

import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crm.active.dto.ActivityVo;
import com.crm.active.entity.Activity;
import com.crm.active.mapper.ActivityMapper;
import com.crm.active.mapper.ApplyMapper;
import com.crm.active.mapper.ConvertMapper;
import com.crm.active.service.ActivityService;
import com.crm.common.utils.PageUtils;
import com.crm.common.utils.RedisUtils;
import com.crm.common.utils.Utils;
import org.ehcache.core.util.CollectionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/** @author wq */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService {

  final ActivityMapper mapper;
  final RedisUtils redisUtils;
  final ApplyMapper applyMapper;

  public ActivityServiceImpl(
      ActivityMapper mapper, RedisUtils redisUtils, ApplyMapper applyMapper) {
    this.mapper = mapper;
    this.redisUtils = redisUtils;
    this.applyMapper = applyMapper;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public String create(ActivityVo activityVo) {
    QueryWrapper<Activity> wrapper = new QueryWrapper<>();
    wrapper.eq("headline", activityVo.getHeadline());
    List<Activity> list = mapper.selectList(wrapper);
    if (!CollectionUtils.isEmpty(list)) {
      return Utils.message("0001", "已存在此活动", null);
    }
    Activity activity = ConvertMapper.convertMapper.activityVoToActivity(activityVo);
    activity.setCreateTime(new Date());
    mapper.insert(activity);
    return Utils.message("0000", "添加活动成功", activity);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public String delete(ActivityVo activityVo) {
    Integer activityId = activityVo.getActivityId();
    mapper.deleteById(activityId);
    redisUtils.del("activity-" + activityId + "-" + "1");
    redisUtils.del("activity-" + activityId + "-" + "0");
    redisUtils.del("activity-browse-duplicate" + activityId);
    return Utils.message("0000", "删除成功", null);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public String enable(ActivityVo activityVo) {
    UpdateWrapper<Activity> wrapper = new UpdateWrapper<>();
    wrapper.setSql("flag = " + activityVo.getFlag()).eq("activity_id", activityVo.getActivityId());
    update(wrapper);
    return Utils.message("0000", "状态修改成功", null);
  }

  @Override
  public String query(ActivityVo activityVo) {
    QueryWrapper<Activity> wrapper = new QueryWrapper<>();
    wrapper.orderByDesc("create_time");
    Integer page = activityVo.getPage();
    Integer limit = activityVo.getLimit();
    Page<Activity> pagination = PageUtils.getPage(page, limit);
    List<Activity> activities = mapper.selectPage(pagination, wrapper).getRecords();
    List<Map<String, Object>> list = new ArrayList<>();
    activities.forEach(
        a -> {
          Map<String, Object> map = new HashMap<>(3);
          Map<Object, Object> browseCount =
              redisUtils.hmget("activity-" + a.getActivityId() + "-" + "0");
          Map<Object, Object> applyCount =
              redisUtils.hmget("activity-" + a.getActivityId() + "-" + "1");
          map.put("activity", a);
          map.put("browseCount", browseCount.size());
          map.put("applyCount", applyCount.size());
          list.add(map);
        });
    Page<Map<String, Object>> resultPage = PageUtils.getPage(page, limit);
    resultPage.setRecords(list);
    QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
    resultPage.setTotal(mapper.selectCount(queryWrapper));
    return Utils.toPageMsg(resultPage);
  }

  @Override
  public String queryByActivityId(ActivityVo activityVo) {
    Integer activityId = activityVo.getActivityId();
    Activity activity = mapper.selectById(activityId);
    String result = getResult(activity);
    if (result != null) {
      return result;
    }
    Map<String, Object> map = getStringObjectMap(String.valueOf(activityId), activity);
    return Utils.message("0000", "查询成功", map);
  }

  @Override
  public String queryByActivityId(String activityId) {
    Activity activity = mapper.selectById(activityId);
    String result = getResult(activity);
    if (result != null) {
      return result;
    }
    Map<String, Object> map = getStringObjectMap(activityId, activity);
    return Utils.message("0000", "查询成功", map);
  }

  @Override
  public String queryApply(String activityId, Integer page, Integer limit) {
    //    Map<Object, Object> map = redisUtils.hmget("activity-" + activityId + "-" + "1");
    //    List<Object> list = new ArrayList<>();
    //    map.entrySet().forEach(key -> list.add(map.get(key)));
    //    Page<Map<Object, Object>> pageination = PageUtils.getPage(page, limit);
    //    pageination.setTotal(map.size());
    //    return Utils.toPageMsg(pageination);
    return null;
  }

  private String getResult(Activity activity) {
    if (activity == null) {
      return Utils.message("0001", "活动已删除", null);
    }
    if (activity.getFlag() == 1) {
      return Utils.message("0001", "活动已停止", null);
    }
    return null;
  }

  private Map<String, Object> getStringObjectMap(String activityId, Activity activity) {
    Map<String, Object> map = new HashMap<>(6);
    Map<Object, Object> browse = redisUtils.hmget("activity-" + activityId + "-" + "0");
    Map<Object, Object> apply = redisUtils.hmget("activity-" + activityId + "-" + "1");
    map.put("activity", activity);
    map.put("applyCount", apply.size());
    map.put(
        "apply",
        apply.keySet().stream()
            .map(aKey -> (JSONObject) apply.get(aKey))
            .collect(Collectors.toList()));
    map.put("browseCount", browse.size());
    map.put(
        "browse",
        browse.keySet().stream()
            .map(bKey -> (JSONObject) (browse.get(bKey)))
            .collect(Collectors.toList()));
    return map;
  }
}
