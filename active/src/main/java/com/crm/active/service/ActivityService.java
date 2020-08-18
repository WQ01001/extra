package com.crm.active.service;

import com.crm.active.dto.ActivityVo;
import com.crm.active.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * The interface Activity service.
 */
public interface ActivityService extends IService<Activity> {

    /**
     * Create string.
     *
     * @param activityVo the activity vo
     * @return the string
     */
    String create(ActivityVo activityVo);

    /**
     * Delete string.
     *
     * @param activityVo the activity vo
     * @return the string
     */
    String delete(ActivityVo activityVo);

    /**
     * Enable string.
     *
     * @param activityId the activity id
     * @return the string
     */
    String enable(ActivityVo activityId);

    /**
     * Query string.
     *
     * @param activityVo the activity vo
     * @return the string
     */
    String query(ActivityVo activityVo);

    /**
     * Query by activity id string.
     *
     * @param activityVo the activity vo
     * @return the string
     */
    String queryByActivityId(ActivityVo activityVo);

    String queryByActivityId(String activityId);

    String queryApply(String activityId,Integer page,Integer limit);
}

