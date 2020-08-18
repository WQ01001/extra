package com.crm.active.dto;

/**
 * @program: extra
 * @description:
 * @author: wq
 * @create: 2020-08-14 09:24
 */
public class ActivityVo {

  private Integer activityId;

  /** 标题 */
  private String headline;

  /** 副标题 */
  private String subtitle;

  /** 类型 */
  private String type;

  private Byte flag;

  private Integer page;

  private Integer limit;

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activityId) {
    this.activityId = activityId;
  }

  public String getHeadline() {
    return headline;
  }

  public void setHeadline(String headline) {
    this.headline = headline;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Byte getFlag() {
    return flag;
  }

  public void setFlag(Byte flag) {
    this.flag = flag;
  }
}
