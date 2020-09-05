package com.crm.active.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crm.active.entity.Information;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface InformationMapper extends BaseMapper<Information> {

  @Select(
      "select * from (select * from information where type = 'unconfirmedservice') t where not exists(select * from resource r where r.resourceId = t.resourceId and r.recipient is not null)")
  List<Information> check();

  @Select(
      "select * from information t where t.resourceId not in (select resourceId  from information where type = 'unconfirmedservice')")
  List<Information> others();
}
