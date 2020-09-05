package com.crm.active.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
    * 花名册管理表
    */
@TableName(value = "roster_main")
public class RosterMain implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "userId", type = IdType.INPUT)
    private Integer userid;

    @TableField(value = "roster_id")
    private String rosterId;

    @TableField(value = "roster_name")
    private String rosterName;

    @TableField(value = "roster_phone")
    private String rosterPhone;

    /**
     * 工号
     */
    @TableField(value = "roster_jobNo")
    private String rosterJobno;

    /**
     * 0=女 1=男  
     */
    @TableField(value = "roster_sex")
    private Integer rosterSex;

    @TableField(value = "roster_post")
    private String rosterPost;

    /**
     * 部门
     */
    @TableField(value = "roster_department")
    private String rosterDepartment;

    /**
     * 预计入职时间
     */
    @TableField(value = "entry_time")
    private Date entryTime;

    /**
     * 实际入职时间（确认到岗填写）
     */
    @TableField(value = "reality_entry_time")
    private Date realityEntryTime;

    @TableField(value = "invitation_name")
    private String invitationName;

    /**
     * 状态 默认0 未核实  1=核实
     */
    @TableField(value = "roster_type")
    private Integer rosterType;

    /**
     * 在职状态 1=未入职 2=已入职 3=离职
     */
    @TableField(value = "onjob_type")
    private Integer onjobType;

    @TableField(value = "roster_remarks")
    private String rosterRemarks;

    /**
     * 逻辑删除0 生效 1
     */
    @TableField(value = "flag")
    private Integer flag;

    /**
     * 用户是否禁用 0 不禁用，1 禁用
     */
    @TableField(value = "is_Destroy")
    private String isDestroy;

    /**
     * 区分花名册以及roster_main 0:花名册 1:user_main
     */
    @TableField(value = "`type`")
    private Integer type;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 办公地点
     */
    @TableField(value = "work_space")
    private String workSpace;

    /**
     * 密码
     */
    @TableField(value = "user_Pwd")
    private String userPwd;

    /**
     * 部门id
     */
    @TableField(value = "depId")
    private Integer depid;

    /**
     * 角色id
     */
    @TableField(value = "roleId")
    private Integer roleid;

    /**
     * 数据权限id
     */
    @TableField(value = "authorityId")
    private Integer authorityid;

    /**
     * 是否是部门主管   1是  0 否  默认0
     */
    @TableField(value = "departmentHead")
    private String departmenthead;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return userId - 主键
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置主键
     *
     * @param userid 主键
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return roster_id
     */
    public String getRosterId() {
        return rosterId;
    }

    /**
     * @param rosterId
     */
    public void setRosterId(String rosterId) {
        this.rosterId = rosterId;
    }

    /**
     * @return roster_name
     */
    public String getRosterName() {
        return rosterName;
    }

    /**
     * @param rosterName
     */
    public void setRosterName(String rosterName) {
        this.rosterName = rosterName;
    }

    /**
     * @return roster_phone
     */
    public String getRosterPhone() {
        return rosterPhone;
    }

    /**
     * @param rosterPhone
     */
    public void setRosterPhone(String rosterPhone) {
        this.rosterPhone = rosterPhone;
    }

    /**
     * 获取工号
     *
     * @return roster_jobNo - 工号
     */
    public String getRosterJobno() {
        return rosterJobno;
    }

    /**
     * 设置工号
     *
     * @param rosterJobno 工号
     */
    public void setRosterJobno(String rosterJobno) {
        this.rosterJobno = rosterJobno;
    }

    /**
     * 获取0=女 1=男  
     *
     * @return roster_sex - 0=女 1=男  
     */
    public Integer getRosterSex() {
        return rosterSex;
    }

    /**
     * 设置0=女 1=男  
     *
     * @param rosterSex 0=女 1=男  
     */
    public void setRosterSex(Integer rosterSex) {
        this.rosterSex = rosterSex;
    }

    /**
     * @return roster_post
     */
    public String getRosterPost() {
        return rosterPost;
    }

    /**
     * @param rosterPost
     */
    public void setRosterPost(String rosterPost) {
        this.rosterPost = rosterPost;
    }

    /**
     * 获取部门
     *
     * @return roster_department - 部门
     */
    public String getRosterDepartment() {
        return rosterDepartment;
    }

    /**
     * 设置部门
     *
     * @param rosterDepartment 部门
     */
    public void setRosterDepartment(String rosterDepartment) {
        this.rosterDepartment = rosterDepartment;
    }

    /**
     * 获取预计入职时间
     *
     * @return entry_time - 预计入职时间
     */
    public Date getEntryTime() {
        return entryTime;
    }

    /**
     * 设置预计入职时间
     *
     * @param entryTime 预计入职时间
     */
    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    /**
     * 获取实际入职时间（确认到岗填写）
     *
     * @return reality_entry_time - 实际入职时间（确认到岗填写）
     */
    public Date getRealityEntryTime() {
        return realityEntryTime;
    }

    /**
     * 设置实际入职时间（确认到岗填写）
     *
     * @param realityEntryTime 实际入职时间（确认到岗填写）
     */
    public void setRealityEntryTime(Date realityEntryTime) {
        this.realityEntryTime = realityEntryTime;
    }

    /**
     * @return invitation_name
     */
    public String getInvitationName() {
        return invitationName;
    }

    /**
     * @param invitationName
     */
    public void setInvitationName(String invitationName) {
        this.invitationName = invitationName;
    }

    /**
     * 获取状态 默认0 未核实  1=核实
     *
     * @return roster_type - 状态 默认0 未核实  1=核实
     */
    public Integer getRosterType() {
        return rosterType;
    }

    /**
     * 设置状态 默认0 未核实  1=核实
     *
     * @param rosterType 状态 默认0 未核实  1=核实
     */
    public void setRosterType(Integer rosterType) {
        this.rosterType = rosterType;
    }

    /**
     * 获取在职状态 1=未入职 2=已入职 3=离职
     *
     * @return onjob_type - 在职状态 1=未入职 2=已入职 3=离职
     */
    public Integer getOnjobType() {
        return onjobType;
    }

    /**
     * 设置在职状态 1=未入职 2=已入职 3=离职
     *
     * @param onjobType 在职状态 1=未入职 2=已入职 3=离职
     */
    public void setOnjobType(Integer onjobType) {
        this.onjobType = onjobType;
    }

    /**
     * @return roster_remarks
     */
    public String getRosterRemarks() {
        return rosterRemarks;
    }

    /**
     * @param rosterRemarks
     */
    public void setRosterRemarks(String rosterRemarks) {
        this.rosterRemarks = rosterRemarks;
    }

    /**
     * 获取逻辑删除0 生效 1
     *
     * @return flag - 逻辑删除0 生效 1
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置逻辑删除0 生效 1
     *
     * @param flag 逻辑删除0 生效 1
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 获取用户是否禁用 0 不禁用，1 禁用
     *
     * @return is_Destroy - 用户是否禁用 0 不禁用，1 禁用
     */
    public String getIsDestroy() {
        return isDestroy;
    }

    /**
     * 设置用户是否禁用 0 不禁用，1 禁用
     *
     * @param isDestroy 用户是否禁用 0 不禁用，1 禁用
     */
    public void setIsDestroy(String isDestroy) {
        this.isDestroy = isDestroy;
    }

    /**
     * 获取区分花名册以及roster_main 0:花名册 1:user_main
     *
     * @return type - 区分花名册以及roster_main 0:花名册 1:user_main
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置区分花名册以及roster_main 0:花名册 1:user_main
     *
     * @param type 区分花名册以及roster_main 0:花名册 1:user_main
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取办公地点
     *
     * @return work_space - 办公地点
     */
    public String getWorkSpace() {
        return workSpace;
    }

    /**
     * 设置办公地点
     *
     * @param workSpace 办公地点
     */
    public void setWorkSpace(String workSpace) {
        this.workSpace = workSpace;
    }

    /**
     * 获取密码
     *
     * @return user_Pwd - 密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 设置密码
     *
     * @param userPwd 密码
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * 获取部门id
     *
     * @return depId - 部门id
     */
    public Integer getDepid() {
        return depid;
    }

    /**
     * 设置部门id
     *
     * @param depid 部门id
     */
    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    /**
     * 获取角色id
     *
     * @return roleId - 角色id
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 设置角色id
     *
     * @param roleid 角色id
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取数据权限id
     *
     * @return authorityId - 数据权限id
     */
    public Integer getAuthorityid() {
        return authorityid;
    }

    /**
     * 设置数据权限id
     *
     * @param authorityid 数据权限id
     */
    public void setAuthorityid(Integer authorityid) {
        this.authorityid = authorityid;
    }

    /**
     * 获取是否是部门主管   1是  0 否  默认0
     *
     * @return departmentHead - 是否是部门主管   1是  0 否  默认0
     */
    public String getDepartmenthead() {
        return departmenthead;
    }

    /**
     * 设置是否是部门主管   1是  0 否  默认0
     *
     * @param departmenthead 是否是部门主管   1是  0 否  默认0
     */
    public void setDepartmenthead(String departmenthead) {
        this.departmenthead = departmenthead;
    }
}