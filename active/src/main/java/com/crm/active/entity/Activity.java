package com.crm.active.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

@TableName(value = "activity")
public class Activity implements Serializable {
    @TableId(value = "activity_id", type = IdType.AUTO)
    private Integer activityId;

    /**
     * 标题
     */
    @TableField(value = "headline")
    private String headline;

    /**
     * 副标题
     */
    @TableField(value = "subtitle")
    private String subtitle;

    /**
     * 类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 0:启用 1:禁用
     */
    @TableField(value = "flag")
    private Byte flag;

    private static final long serialVersionUID = 1L;

    /**
     * @return activity_id
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * @param activityId
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取标题
     *
     * @return headline - 标题
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * 设置标题
     *
     * @param headline 标题
     */
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    /**
     * 获取副标题
     *
     * @return subtitle - 副标题
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 设置副标题
     *
     * @param subtitle 副标题
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取0:启用 1:禁用
     *
     * @return flag - 0:启用 1:禁用
     */
    public Byte getFlag() {
        return flag;
    }

    /**
     * 设置0:启用 1:禁用
     *
     * @param flag 0:启用 1:禁用
     */
    public void setFlag(Byte flag) {
        this.flag = flag;
    }
}