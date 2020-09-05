package com.crm.active.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
    * 消息实体
    */
@TableName(value = "information")
public class Information {
    /**
     * 用户id
     */
    @TableField(value = "userId")
    private String userid;

    /**
     * 客户名称
     */
    @TableField(value = "resourceName")
    private String resourcename;

    /**
     * 签到时间
     */
    @TableField(value = "signTime")
    private Date signtime;

    /**
     * 报名时间
     */
    @TableField(value = "applyTime")
    private Date applytime;

    /**
     * 校区ID
     */
    @TableField(value = "schoolId")
    private Integer schoolid;

    /**
     * 校区名称
     */
    @TableField(value = "schoolName")
    private String schoolname;

    /**
     * 预约时间
     */
    @TableField(value = "orderDate")
    private Date orderdate;

    /**
     * 预约时间 上午 下午
     */
    @TableField(value = "orderPeroid")
    private String orderperoid;

    @TableField(value = "resourceId")
    private Integer resourceid;

    /**
     * 类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 消息状态 1已读 0未读
     */
    @TableField(value = "`status`")
    private Boolean status;

    /**
     * 消息实体主键
     */
    @TableField(value = "infoId")
    private String infoid;

    /**
     * 接受消息日期
     */
    @TableField(value = "`date`")
    private Date date;

    /**
     * 分配人
     */
    @TableField(value = "dispatchuserId")
    private String dispatchuserid;

    /**
     * 校区备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 分配人姓名
     */
    @TableField(value = "dispatchuserName")
    private String dispatchusername;

    /**
     * 客服姓名
     */
    @TableField(value = "userName")
    private String username;

    /**
     * 获取用户id
     *
     * @return userId - 用户id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取客户名称
     *
     * @return resourceName - 客户名称
     */
    public String getResourcename() {
        return resourcename;
    }

    /**
     * 设置客户名称
     *
     * @param resourcename 客户名称
     */
    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    /**
     * 获取签到时间
     *
     * @return signTime - 签到时间
     */
    public Date getSigntime() {
        return signtime;
    }

    /**
     * 设置签到时间
     *
     * @param signtime 签到时间
     */
    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }

    /**
     * 获取报名时间
     *
     * @return applyTime - 报名时间
     */
    public Date getApplytime() {
        return applytime;
    }

    /**
     * 设置报名时间
     *
     * @param applytime 报名时间
     */
    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    /**
     * 获取校区ID
     *
     * @return schoolId - 校区ID
     */
    public Integer getSchoolid() {
        return schoolid;
    }

    /**
     * 设置校区ID
     *
     * @param schoolid 校区ID
     */
    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    /**
     * 获取校区名称
     *
     * @return schoolName - 校区名称
     */
    public String getSchoolname() {
        return schoolname;
    }

    /**
     * 设置校区名称
     *
     * @param schoolname 校区名称
     */
    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    /**
     * 获取预约时间
     *
     * @return orderDate - 预约时间
     */
    public Date getOrderdate() {
        return orderdate;
    }

    /**
     * 设置预约时间
     *
     * @param orderdate 预约时间
     */
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    /**
     * 获取预约时间 上午 下午
     *
     * @return orderPeroid - 预约时间 上午 下午
     */
    public String getOrderperoid() {
        return orderperoid;
    }

    /**
     * 设置预约时间 上午 下午
     *
     * @param orderperoid 预约时间 上午 下午
     */
    public void setOrderperoid(String orderperoid) {
        this.orderperoid = orderperoid;
    }

    /**
     * @return resourceId
     */
    public Integer getResourceid() {
        return resourceid;
    }

    /**
     * @param resourceid
     */
    public void setResourceid(Integer resourceid) {
        this.resourceid = resourceid;
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
     * 获取消息状态 1已读 0未读
     *
     * @return status - 消息状态 1已读 0未读
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置消息状态 1已读 0未读
     *
     * @param status 消息状态 1已读 0未读
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取消息实体主键
     *
     * @return infoId - 消息实体主键
     */
    public String getInfoid() {
        return infoid;
    }

    /**
     * 设置消息实体主键
     *
     * @param infoid 消息实体主键
     */
    public void setInfoid(String infoid) {
        this.infoid = infoid;
    }

    /**
     * 获取接受消息日期
     *
     * @return date - 接受消息日期
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置接受消息日期
     *
     * @param date 接受消息日期
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取分配人
     *
     * @return dispatchuserId - 分配人
     */
    public String getDispatchuserid() {
        return dispatchuserid;
    }

    /**
     * 设置分配人
     *
     * @param dispatchuserid 分配人
     */
    public void setDispatchuserid(String dispatchuserid) {
        this.dispatchuserid = dispatchuserid;
    }

    /**
     * 获取校区备注
     *
     * @return remark - 校区备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置校区备注
     *
     * @param remark 校区备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取分配人姓名
     *
     * @return dispatchuserName - 分配人姓名
     */
    public String getDispatchusername() {
        return dispatchusername;
    }

    /**
     * 设置分配人姓名
     *
     * @param dispatchusername 分配人姓名
     */
    public void setDispatchusername(String dispatchusername) {
        this.dispatchusername = dispatchusername;
    }

    /**
     * 获取客服姓名
     *
     * @return userName - 客服姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置客服姓名
     *
     * @param username 客服姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }
}