package com.crm.active.mapper;

import com.crm.active.dto.ActivityVo;
import com.crm.active.dto.ApplyVo;
import com.crm.active.entity.Activity;
import com.crm.active.entity.Apply;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @program: extra
 * @description: 转换mapper
 * @author: wq
 * @create: 2020-08-14 09:21
 */
@Mapper
public interface ConvertMapper {
  ConvertMapper convertMapper = Mappers.getMapper(ConvertMapper.class);

  Activity activityVoToActivity(ActivityVo vo);

  Apply applyVoToApply(ApplyVo applyVo);
}
