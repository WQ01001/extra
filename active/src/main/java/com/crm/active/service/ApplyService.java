package com.crm.active.service;

import com.crm.active.dto.ApplyVo;
import com.crm.active.entity.Apply;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ApplyService extends IService<Apply> {

  /**
   * 用户报名
   *
   * @param map
   * @return
   */
  Map<String, Object> authorize(Map<String, Object> map);

  String apply(Map<String, Object> map);

  String cheat(ApplyVo applyVo);

  String getCodeUrl(Map<String, Object> map);

}
