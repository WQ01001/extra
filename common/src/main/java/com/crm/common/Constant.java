package com.crm.common;

/**
 * @program: extra
 * @description: 常量
 * @author: wq
 * @create: 2020-08-13 17:42
 */
public class Constant {
  public static final String ACCESS_TOKEN_URL =
      "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%1s&secret=%2s&code=%3s&grant_type=authorization_code";

  public static final String USER_INFO_URL =
      "https://api.weixin.qq.com/sns/userinfo?access_token=%1s&openid=%2s&lang=zh_CN";

  public static final String APPID = "wxa41cd8b48b52a592";

  public static final String SECRET = "f8a1a8487e4eaddf0b4c04c164d6ba40";

  public static final String URL = "http://look.ainisixuexiao.com/";
}
