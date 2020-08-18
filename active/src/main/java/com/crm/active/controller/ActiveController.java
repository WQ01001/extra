package com.crm.active.controller;

import com.crm.active.dto.ActivityVo;
import com.crm.active.service.ActivityService;
import com.crm.active.service.ApplyService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: extra
 * @description: active控制器
 * @author: wq
 * @create: 2020-08-13 15:13
 */
@RestController
// @CrossOrigin
@RequestMapping("/api/active")
public class ActiveController {
  final ActivityService service;
  final ApplyService applyService;

  public ActiveController(ActivityService activityService, ApplyService applyService) {
    this.service = activityService;
    this.applyService = applyService;
  }

  @RequestMapping("/save")
  public String createActivity(ActivityVo activityVo) {
    return service.create(activityVo);
  }

  @RequestMapping("/delete")
  public String deleteActivity(ActivityVo activityVo) {
    return service.delete(activityVo);
  }

  @RequestMapping("/enable")
  public String enableActivity(ActivityVo activityVo) {
    return service.enable(activityVo);
  }

  @RequestMapping("/query")
  public String query(ActivityVo activityVo) {
    return service.query(activityVo);
  }

  @RequestMapping("/queryByActivityId")
  public String queryByActivityId(@RequestBody ActivityVo activityVo) {
    return service.queryByActivityId(activityVo);
  }

  @RequestMapping("/queryById")
  public String queryByActivityId(@RequestParam String activityId) {
    return service.queryByActivityId(activityId);
  }
}
