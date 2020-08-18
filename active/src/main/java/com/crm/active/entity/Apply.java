package com.crm.active.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

@TableName(value = "apply")
public class Apply implements Serializable {
    @TableId(value = "apply_id", type = IdType.AUTO)
    private Integer applyId;

    /**
     * 微信昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    @TableField(value = "head_imgurl")
    private String headImgurl;

    /**
     * 报名时间
     */
    @TableField(value = "apply_time")
    private Date applyTime;

    /**
     * 活动ID
     */
    @TableField(value = "activity_id")
    private Integer activityId;

    /**
     * 浏览时间
     */
    @TableField(value = "browse_time")
    private Date browseTime;

    /**
     * 0:浏览 1:报名
     */
    @TableField(value = "type")
    private Byte type;

    private static final long serialVersionUID = 1L;

    /**
     * @return apply_id
     */
    public Integer getApplyId() {
        return applyId;
    }

    /**
     * @param applyId
     */
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取微信昵称
     *
     * @return nickname - 微信昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置微信昵称
     *
     * @param nickname 微信昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return head_imgurl
     */
    public String getHeadImgurl() {
        return headImgurl;
    }

    /**
     * @param headImgurl
     */
    public void setHeadImgurl(String headImgurl) {
        this.headImgurl = headImgurl;
    }

    /**
     * 获取报名时间
     *
     * @return apply_time - 报名时间
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 设置报名时间
     *
     * @param applyTime 报名时间
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取活动ID
     *
     * @return activity_id - 活动ID
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * 设置活动ID
     *
     * @param activityId 活动ID
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取浏览时间
     *
     * @return browse_time - 浏览时间
     */
    public Date getBrowseTime() {
        return browseTime;
    }

    /**
     * 设置浏览时间
     *
     * @param browseTime 浏览时间
     */
    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    /**
     * 获取0:浏览 1:报名
     *
     * @return type - 0:浏览 1:报名
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置0:浏览 1:报名
     *
     * @param type 0:浏览 1:报名
     */
    public void setType(Byte type) {
        this.type = type;
    }
}