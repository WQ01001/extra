package com.crm.active.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

@TableName(value = "`resource`")
public class Resource {
    @TableId(value = "resourceId", type = IdType.INPUT)
    private Integer resourceid;

    /**
     * 用户名称
     */
    @TableField(value = "resourceName")
    private String resourcename;

    /**
     * 资源创建时间
     */
    @TableField(value = "resourceTime")
    private Date resourcetime;

    /**
     * 用户手机号码
     */
    @TableField(value = "resourcePhone")
    private String resourcephone;

    /**
     * 数据来源 1百度，2抖音，3头条，4信息流，5商务通，6电话，7朋友圈，8快手、9 58、10广点通
     */
    @TableField(value = "resourceType")
    private String resourcetype;

    /**
     * 资源城市地址
     */
    @TableField(value = "resourceAddress")
    private String resourceaddress;

    /**
     * 数据是否确认 0 为确认 1 确认
     */
    @TableField(value = "resourceIfconfirm")
    private Integer resourceifconfirm;

    /**
     * 被分配者ID
     */
    @TableField(value = "userid")
    private String userid;

    /**
     * 最早被分配人
     */
    @TableField(value = "OldUserId")
    private String olduserid;

    /**
     * 分配后确认接收的时间
     */
    @TableField(value = "updatetime")
    private Date updatetime;

    /**
     * 分配时间
     */
    @TableField(value = "dispatchtime")
    private Date dispatchtime;

    /**
     * 分配人ID
     */
    @TableField(value = "dispatchuserid")
    private String dispatchuserid;

    /**
     * 数据来源 0 人工创建 1 批量导入 2 校区签到创建
     */
    @TableField(value = "datafrom")
    private Integer datafrom;

    /**
     * 导入时间
     */
    @TableField(value = "importtime")
    private Date importtime;

    /**
     * 导入者Id
     */
    @TableField(value = "importuserid")
    private String importuserid;

    /**
     * 预约状态 0 未转约 1 已转约
     */
    @TableField(value = "appointStatus")
    private Integer appointstatus;

    /**
     * 创建用户或者分配用的备注内容
     */
    @TableField(value = "remarksDetail")
    private String remarksdetail;

    /**
     * 咨询课程
     */
    @TableField(value = "consultCourseId")
    private String consultcourseid;

    /**
     * 咨询时间
     */
    @TableField(value = "consultTime")
    private Date consulttime;

    /**
     * 意向等级 A B C D E
     */
    @TableField(value = "`level`")
    private String level;

    /**
     * 逻辑删除 0 删除 1 生效  2存量池
     */
    @TableField(value = "flag")
    private String flag;

    /**
     * 是否私人 0 否 1 是
     */
    @TableField(value = "personalFlag")
    private String personalflag;

    /**
     * 访问次数
     */
    @TableField(value = "formCount")
    private Integer formcount;

    /**
     * 回访次数
     */
    @TableField(value = "totalVisitCount")
    private Integer totalvisitcount;

    /**
     * 最近回访内容
     */
    @TableField(value = "lastVisitContent")
    private String lastvisitcontent;

    /**
     * 最近回访时间
     */
    @TableField(value = "lastVisitTime")
    private Date lastvisittime;

    /**
     * 回访方式 关联字典表 contactWay
     */
    @TableField(value = "contactWay")
    private String contactway;

    /**
     * 记录第一次点击确认的客服
     */
    @TableField(value = "recipient")
    private String recipient;

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
     * 获取用户名称
     *
     * @return resourceName - 用户名称
     */
    public String getResourcename() {
        return resourcename;
    }

    /**
     * 设置用户名称
     *
     * @param resourcename 用户名称
     */
    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    /**
     * 获取资源创建时间
     *
     * @return resourceTime - 资源创建时间
     */
    public Date getResourcetime() {
        return resourcetime;
    }

    /**
     * 设置资源创建时间
     *
     * @param resourcetime 资源创建时间
     */
    public void setResourcetime(Date resourcetime) {
        this.resourcetime = resourcetime;
    }

    /**
     * 获取用户手机号码
     *
     * @return resourcePhone - 用户手机号码
     */
    public String getResourcephone() {
        return resourcephone;
    }

    /**
     * 设置用户手机号码
     *
     * @param resourcephone 用户手机号码
     */
    public void setResourcephone(String resourcephone) {
        this.resourcephone = resourcephone;
    }

    /**
     * 获取数据来源 1百度，2抖音，3头条，4信息流，5商务通，6电话，7朋友圈，8快手、9 58、10广点通
     *
     * @return resourceType - 数据来源 1百度，2抖音，3头条，4信息流，5商务通，6电话，7朋友圈，8快手、9 58、10广点通
     */
    public String getResourcetype() {
        return resourcetype;
    }

    /**
     * 设置数据来源 1百度，2抖音，3头条，4信息流，5商务通，6电话，7朋友圈，8快手、9 58、10广点通
     *
     * @param resourcetype 数据来源 1百度，2抖音，3头条，4信息流，5商务通，6电话，7朋友圈，8快手、9 58、10广点通
     */
    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }

    /**
     * 获取资源城市地址
     *
     * @return resourceAddress - 资源城市地址
     */
    public String getResourceaddress() {
        return resourceaddress;
    }

    /**
     * 设置资源城市地址
     *
     * @param resourceaddress 资源城市地址
     */
    public void setResourceaddress(String resourceaddress) {
        this.resourceaddress = resourceaddress;
    }

    /**
     * 获取数据是否确认 0 为确认 1 确认
     *
     * @return resourceIfconfirm - 数据是否确认 0 为确认 1 确认
     */
    public Integer getResourceifconfirm() {
        return resourceifconfirm;
    }

    /**
     * 设置数据是否确认 0 为确认 1 确认
     *
     * @param resourceifconfirm 数据是否确认 0 为确认 1 确认
     */
    public void setResourceifconfirm(Integer resourceifconfirm) {
        this.resourceifconfirm = resourceifconfirm;
    }

    /**
     * 获取被分配者ID
     *
     * @return userid - 被分配者ID
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置被分配者ID
     *
     * @param userid 被分配者ID
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取最早被分配人
     *
     * @return OldUserId - 最早被分配人
     */
    public String getOlduserid() {
        return olduserid;
    }

    /**
     * 设置最早被分配人
     *
     * @param olduserid 最早被分配人
     */
    public void setOlduserid(String olduserid) {
        this.olduserid = olduserid;
    }

    /**
     * 获取分配后确认接收的时间
     *
     * @return updatetime - 分配后确认接收的时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置分配后确认接收的时间
     *
     * @param updatetime 分配后确认接收的时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取分配时间
     *
     * @return dispatchtime - 分配时间
     */
    public Date getDispatchtime() {
        return dispatchtime;
    }

    /**
     * 设置分配时间
     *
     * @param dispatchtime 分配时间
     */
    public void setDispatchtime(Date dispatchtime) {
        this.dispatchtime = dispatchtime;
    }

    /**
     * 获取分配人ID
     *
     * @return dispatchuserid - 分配人ID
     */
    public String getDispatchuserid() {
        return dispatchuserid;
    }

    /**
     * 设置分配人ID
     *
     * @param dispatchuserid 分配人ID
     */
    public void setDispatchuserid(String dispatchuserid) {
        this.dispatchuserid = dispatchuserid;
    }

    /**
     * 获取数据来源 0 人工创建 1 批量导入 2 校区签到创建
     *
     * @return datafrom - 数据来源 0 人工创建 1 批量导入 2 校区签到创建
     */
    public Integer getDatafrom() {
        return datafrom;
    }

    /**
     * 设置数据来源 0 人工创建 1 批量导入 2 校区签到创建
     *
     * @param datafrom 数据来源 0 人工创建 1 批量导入 2 校区签到创建
     */
    public void setDatafrom(Integer datafrom) {
        this.datafrom = datafrom;
    }

    /**
     * 获取导入时间
     *
     * @return importtime - 导入时间
     */
    public Date getImporttime() {
        return importtime;
    }

    /**
     * 设置导入时间
     *
     * @param importtime 导入时间
     */
    public void setImporttime(Date importtime) {
        this.importtime = importtime;
    }

    /**
     * 获取导入者Id
     *
     * @return importuserid - 导入者Id
     */
    public String getImportuserid() {
        return importuserid;
    }

    /**
     * 设置导入者Id
     *
     * @param importuserid 导入者Id
     */
    public void setImportuserid(String importuserid) {
        this.importuserid = importuserid;
    }

    /**
     * 获取预约状态 0 未转约 1 已转约
     *
     * @return appointStatus - 预约状态 0 未转约 1 已转约
     */
    public Integer getAppointstatus() {
        return appointstatus;
    }

    /**
     * 设置预约状态 0 未转约 1 已转约
     *
     * @param appointstatus 预约状态 0 未转约 1 已转约
     */
    public void setAppointstatus(Integer appointstatus) {
        this.appointstatus = appointstatus;
    }

    /**
     * 获取创建用户或者分配用的备注内容
     *
     * @return remarksDetail - 创建用户或者分配用的备注内容
     */
    public String getRemarksdetail() {
        return remarksdetail;
    }

    /**
     * 设置创建用户或者分配用的备注内容
     *
     * @param remarksdetail 创建用户或者分配用的备注内容
     */
    public void setRemarksdetail(String remarksdetail) {
        this.remarksdetail = remarksdetail;
    }

    /**
     * 获取咨询课程
     *
     * @return consultCourseId - 咨询课程
     */
    public String getConsultcourseid() {
        return consultcourseid;
    }

    /**
     * 设置咨询课程
     *
     * @param consultcourseid 咨询课程
     */
    public void setConsultcourseid(String consultcourseid) {
        this.consultcourseid = consultcourseid;
    }

    /**
     * 获取咨询时间
     *
     * @return consultTime - 咨询时间
     */
    public Date getConsulttime() {
        return consulttime;
    }

    /**
     * 设置咨询时间
     *
     * @param consulttime 咨询时间
     */
    public void setConsulttime(Date consulttime) {
        this.consulttime = consulttime;
    }

    /**
     * 获取意向等级 A B C D E
     *
     * @return level - 意向等级 A B C D E
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置意向等级 A B C D E
     *
     * @param level 意向等级 A B C D E
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取逻辑删除 0 删除 1 生效  2存量池
     *
     * @return flag - 逻辑删除 0 删除 1 生效  2存量池
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置逻辑删除 0 删除 1 生效  2存量池
     *
     * @param flag 逻辑删除 0 删除 1 生效  2存量池
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 获取是否私人 0 否 1 是
     *
     * @return personalFlag - 是否私人 0 否 1 是
     */
    public String getPersonalflag() {
        return personalflag;
    }

    /**
     * 设置是否私人 0 否 1 是
     *
     * @param personalflag 是否私人 0 否 1 是
     */
    public void setPersonalflag(String personalflag) {
        this.personalflag = personalflag;
    }

    /**
     * 获取访问次数
     *
     * @return formCount - 访问次数
     */
    public Integer getFormcount() {
        return formcount;
    }

    /**
     * 设置访问次数
     *
     * @param formcount 访问次数
     */
    public void setFormcount(Integer formcount) {
        this.formcount = formcount;
    }

    /**
     * 获取回访次数
     *
     * @return totalVisitCount - 回访次数
     */
    public Integer getTotalvisitcount() {
        return totalvisitcount;
    }

    /**
     * 设置回访次数
     *
     * @param totalvisitcount 回访次数
     */
    public void setTotalvisitcount(Integer totalvisitcount) {
        this.totalvisitcount = totalvisitcount;
    }

    /**
     * 获取最近回访内容
     *
     * @return lastVisitContent - 最近回访内容
     */
    public String getLastvisitcontent() {
        return lastvisitcontent;
    }

    /**
     * 设置最近回访内容
     *
     * @param lastvisitcontent 最近回访内容
     */
    public void setLastvisitcontent(String lastvisitcontent) {
        this.lastvisitcontent = lastvisitcontent;
    }

    /**
     * 获取最近回访时间
     *
     * @return lastVisitTime - 最近回访时间
     */
    public Date getLastvisittime() {
        return lastvisittime;
    }

    /**
     * 设置最近回访时间
     *
     * @param lastvisittime 最近回访时间
     */
    public void setLastvisittime(Date lastvisittime) {
        this.lastvisittime = lastvisittime;
    }

    /**
     * 获取回访方式 关联字典表 contactWay
     *
     * @return contactWay - 回访方式 关联字典表 contactWay
     */
    public String getContactway() {
        return contactway;
    }

    /**
     * 设置回访方式 关联字典表 contactWay
     *
     * @param contactway 回访方式 关联字典表 contactWay
     */
    public void setContactway(String contactway) {
        this.contactway = contactway;
    }

    /**
     * 获取记录第一次点击确认的客服
     *
     * @return recipient - 记录第一次点击确认的客服
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * 设置记录第一次点击确认的客服
     *
     * @param recipient 记录第一次点击确认的客服
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}