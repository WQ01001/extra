package com.crm.active.mapper;

import com.crm.active.dto.ActivityVo;
import com.crm.active.dto.ApplyVo;
import com.crm.active.entity.Activity;
import com.crm.active.entity.Apply;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-04T18:30:08+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class ConvertMapperImpl implements ConvertMapper {

    @Override
    public Activity activityVoToActivity(ActivityVo vo) {
        if ( vo == null ) {
            return null;
        }

        Activity activity = new Activity();

        activity.setActivityId( vo.getActivityId() );
        activity.setHeadline( vo.getHeadline() );
        activity.setSubtitle( vo.getSubtitle() );
        activity.setType( vo.getType() );
        activity.setFlag( vo.getFlag() );

        return activity;
    }

    @Override
    public Apply applyVoToApply(ApplyVo applyVo) {
        if ( applyVo == null ) {
            return null;
        }

        Apply apply = new Apply();

        apply.setApplyId( applyVo.getApplyId() );
        apply.setNickname( applyVo.getNickname() );
        apply.setHeadImgurl( applyVo.getHeadImgurl() );
        apply.setActivityId( applyVo.getActivityId() );

        return apply;
    }
}
