package com.crm.active.controller;

import com.crm.active.dto.ApplyVo;
import com.crm.active.service.ActivityService;
import com.crm.active.service.ApplyService;
import com.crm.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @program: extra
 * @description:
 * @author: wq
 * @create: 2020-08-14 09:54
 */
// @CrossOrigin
@RestController
@RequestMapping("/api/apply")
public class ApplyController {
  public static final Logger logger = LoggerFactory.getLogger(ApplyController.class);

  final ActivityService service;
  final ApplyService applyService;

  public ApplyController(ActivityService activityService, ApplyService applyService) {
    this.service = activityService;
    this.applyService = applyService;
  }

  @RequestMapping("/authorize")
  public void authorize(HttpServletResponse response, @RequestParam Map<String, Object> map) {
    Map<String, Object> resultMap = applyService.authorize(map);
    try {
      response.sendRedirect(
          Constant.URL
              + "?openId="
              + resultMap.get("openId")
              + "&activityId="
              + resultMap.get("activityId"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/getCodeUrl")
  public void getCodeUrl(HttpServletResponse response, @RequestParam Map<String, Object> map) {
    String codeUrl = applyService.getCodeUrl(map);
    try {
      response.sendRedirect(codeUrl);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/signUp")
  public String apply(@RequestBody Map<String, Object> map) {
    return applyService.apply(map);
  }

  @RequestMapping("/cheat")
  public String cheat(@RequestBody ApplyVo applyVo) {
    return applyService.cheat(applyVo);
  }
}
