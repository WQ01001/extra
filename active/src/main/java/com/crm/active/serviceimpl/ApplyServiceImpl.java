package com.crm.active.serviceimpl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crm.active.dto.ApplyVo;
import com.crm.active.entity.Apply;
import com.crm.active.mapper.ApplyMapper;
import com.crm.active.service.ApplyService;
import com.crm.common.Constant;
import com.crm.common.utils.RedisUtils;
import com.crm.common.utils.Utils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {

  public static final Logger log = LoggerFactory.getLogger(ApplyServiceImpl.class);

  final ApplyMapper applyMapper;
  final RedisUtils redisUtils;

  public ApplyServiceImpl(ApplyMapper applyMapper, RedisUtils redisUtils) {
    this.applyMapper = applyMapper;
    this.redisUtils = redisUtils;
  }

  @Override
  public Map<String, Object> authorize(Map<String, Object> map) {
    Object activityId = map.get("state");
    if (activityId == null) {
      log.info("activityId不能为空");
      return null;
    }
    AtomicReference<String> openid = new AtomicReference<>();
    AtomicReference<String> access_token = new AtomicReference<>();
    //    AtomicReference<JSONObject> jsonObject = new AtomicReference<>();
    Object code = map.get("code");
    if (code == null) {
      log.info("code没有获取到");
      return null;
    }
    log.info("code:{}", code);
    String accessTokenUrl =
        String.format(Constant.ACCESS_TOKEN_URL, Constant.APPID, Constant.SECRET, code);
    log.info("accessTokenUrl:{}", accessTokenUrl);
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(accessTokenUrl);
    HttpResponse httpResponse = null;
    try {
      httpResponse = httpClient.execute(httpGet);
    } catch (IOException e) {
      e.printStackTrace();
    }

    Optional.ofNullable(httpResponse)
        .ifPresent(
            r -> {
              HttpEntity entity = r.getEntity();
              Optional.ofNullable(entity)
                  .ifPresent(
                      e -> {
                        try {
                          String s = EntityUtils.toString(e);
                          JSONObject jsonObjectTemp = JSON.parseObject(s);
                          openid.set(jsonObjectTemp.getString("openid"));
                          access_token.set(jsonObjectTemp.getString("access_token"));
                        } catch (IOException ioException) {
                          ioException.printStackTrace();
                        }
                      });
            });

    String userInfoUrl = String.format(Constant.USER_INFO_URL, access_token.get(), openid.get());
    log.info("userInfoUrl:{}", userInfoUrl);
    httpGet.setURI(URI.create(userInfoUrl));
    try {
      httpResponse = httpClient.execute(httpGet);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Optional.ofNullable(httpResponse)
        .ifPresent(
            r -> {
              HttpEntity entity = r.getEntity();
              Optional.ofNullable(entity)
                  .ifPresent(
                      e -> {
                        try {
                          String s =
                              new String(
                                  EntityUtils.toString(e).getBytes(StandardCharsets.ISO_8859_1),
                                  StandardCharsets.UTF_8);
                          JSONObject newValue = JSON.parseObject(s);
                          //                          jsonObject.set(newValue);
                          if (newValue.containsKey("errcode")) {
                            log.info("请求错误:{}", s);
                            return;
                          }
                          if (redisUtils.sHasKey(
                              "activity-browse-duplicate" + activityId,
                              newValue.getString("openid"))) {
                            log.info("已存在{}", s);
                            return;
                          }
                          newValue.put("browseTime", System.currentTimeMillis());
                          redisUtils.hset(
                              "activity-" + activityId + "-" + "0",
                              newValue.getString("openid"),
                              newValue);
                          log.info(newValue.toJSONString());
                        } catch (IOException ioException) {
                          ioException.printStackTrace();
                        }
                      });
            });

    Map<String, Object> hashMap = new HashMap<>();
    hashMap.put("openId", openid.get());
    hashMap.put("activityId", activityId);
    return hashMap;
  }

  @Override
  public String apply(Map<String, Object> map) {

    Object activityId = map.get("activityId");
    if (activityId == null) {
      return Utils.message("0001", "activityId不能为空", null);
    }

    Object openId = map.get("openId");
    if (openId == null) {
      return Utils.message("0001", "openId不能为空", null);
    }

    String openIdValue = String.valueOf(openId);
    if (redisUtils.hHasKey("activity-" + activityId + "-" + "1", String.valueOf(openId))) {
      return Utils.message("0001", "您已报过名", null);
    }
    Object json = redisUtils.hget("activity-" + activityId + "-" + "0", openIdValue);
    Optional.ofNullable(json)
        .ifPresent(
            j -> {
              JSONObject jsonObject = (JSONObject) j;
              redisUtils.hset("activity-" + activityId + "-" + "1", openIdValue, jsonObject);
            });

    return Utils.message("0000", "报名成功", null);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public String cheat(ApplyVo applyVo) {
    String openId = IdUtil.simpleUUID();
    JSONObject newValue = new JSONObject();
    newValue.put("nickname", applyVo.getNickname());
    newValue.put("headimgurl", applyVo.getHeadImgurl());
    log.info("我是图片:{}", applyVo.getHeadImgurl());
    newValue.put("browseTime", System.currentTimeMillis());
    newValue.put("openid", openId);
    redisUtils.hset(
        "activity-" + applyVo.getActivityId() + "-" + "1",
        //        newValue.getString("openid"),
        openId,
        newValue);
    return Utils.message("0000", "新增报名成功", null);
  }

  @Override
  public String getCodeUrl(Map<String, Object> map) {
    Object activityId = map.get("activityId");
    if (activityId == null) {
      return Utils.message("0001", "activityId为空!", null);
    }
    return String.format(
        "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect",
        Constant.APPID,
        "http://look.ainisixuexiao.com/api/apply/authorize",
        "snsapi_userinfo",
        activityId);
  }
}
