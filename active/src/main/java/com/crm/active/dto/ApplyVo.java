package com.crm.active.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @program: extra
 * @description:
 * @author: wq
 * @create: 2020-08-15 10:30
 */
public class ApplyVo {
  private Integer applyId;

  /** 微信昵称 */
  private String nickname;

  @JsonProperty(value = "headimgurl")
  private String headimgurl;

  /** 活动ID */
  private Integer activityId;

  public Integer getApplyId() {
    return applyId;
  }

  public void setApplyId(Integer applyId) {
    this.applyId = applyId;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getHeadImgurl() {
    return headimgurl;
  }

  public void setHeadImgurl(String headImgurl) {
    this.headimgurl = headImgurl;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activityId) {
    this.activityId = activityId;
  }
}
