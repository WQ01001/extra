package com.crm.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 工具类 -李敏
public class Utils {

  public static boolean checkEngStr(String str, int minSize, int maxSzize) {
    if (Utils.IsNullOrEmpty(str)) return false;
    if (containsSpecialChar(str)) return false;
    if (containsCNCode(str)) return false;
    if (str.length() < minSize) return false;
    return str.length() <= maxSzize;
  }

  public static boolean checkCNStr(String str, int minSize, int maxSzize) {
    if (Utils.IsNullOrEmpty(str)) return false;
    if (containsSpecialChar(str)) return false;
    if (!isCNCode(str)) return false;
    if (str.length() < minSize) return false;
    if (str.length() > maxSzize) return false;
    return true;
  }

  /**
   * 元转分
   *
   * @param totalAmount
   * @return
   * @author CuiYuan
   * @created 2018年9月27日 上午10:59:30
   * @lastModified
   * @history
   */
  public static BigDecimal tofen(BigDecimal totalAmount) {
    BigDecimal fen = BigDecimal.ZERO;
    try {
      fen = totalAmount.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
    } catch (Exception e) {
      System.err.println(e);
    }
    return fen;
  }

  public static BigDecimal toYuan(BigDecimal fen) {
    BigDecimal yuan = BigDecimal.ZERO;
    try {
      yuan = fen.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
    } catch (Exception e) {
      System.err.println(e);
    }
    return yuan;
  }

  // 长度参数 第一个传不能大于 第二个传不能小于
  public static boolean checkStr(String str, int minSize, int maxSzize) {
    if (Utils.IsNullOrEmpty(str)) return false;
    if (containsSpecialChar(str)) return false;
    if (str.length() < minSize) return false;
    if (str.length() > maxSzize) return false;
    return true;
  }

  public static boolean CheckLoginName(String loginName) {
    if (IsNullOrEmpty(loginName)) return false;
    if (loginName.length() < 6 || loginName.length() > 20) return false;
    return Utils.CheckIsValid(loginName, "^[a-zA-Z]{1}[0-9|a-z|A-Z|+-]+$");
  }

  public static boolean checkPwdStr(String str) {
    if (Utils.IsNullOrEmpty(str)) return false;
    if (str.length() < 6 || str.length() > 16) return false;
    return true;
  }

  public static boolean checkAlipayNo(String str) {
    if (Utils.IsNullOrEmpty(str)) return false;
    return Utils.CheckIsValid(
        str,
        "^(([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,})|([1]+\\d{10})$");
  }

  public static boolean checkLicence(String str) {
    if (Utils.IsNullOrEmpty(str)) return false;
    return Utils.CheckIsValid(str, "^[1-9A-GY]{1}[1239]{1}[1-5]{1}[0-9]{5}[0-9A-Z]{10}$");
  }

  public static boolean isEmptyObj(Object... arr) {
    boolean flag = false;
    for (Object obj : arr) {
      if (isEmpty(obj)) {
        return true;
      }
    }
    return flag;
  }
  //	public static String  isEmptyObj(List<MsgObj> list) {
  //		if (list.size()==0) {
  //			return null;
  //		}
  //		for (MsgObj obj : list) {
  //			if (isEmpty(obj.getObj())){
  //				return obj.getMsg()+"不能为空";
  //				}
  //		}
  //		return null;
  //	}

  /** 判断对象是否为空 */
  public static boolean isEmpty(Object obj) {
    if (obj == null) return true;
    if (obj instanceof String) return IsNullOrEmpty((String) obj);
    if (obj instanceof Collection && ((Collection<?>) obj).isEmpty()) return true;
    if (obj.getClass().isArray() && Array.getLength(obj) == 0) return true;
    if (obj instanceof Map && ((Map<?, ?>) obj).isEmpty()) return true;

    return false;
  }

  public static boolean isBigDecimal(Object obj) {
    try {
      new BigDecimal(Utils.ToString(obj));
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public static boolean checkBankCard(String cardId) {
    if (cardId == null || cardId.trim().length() == 0) {
      return false;
    }
    if (cardId.length() < 16 || cardId.length() > 19) {
      return false;
    }

    // 根据Luhm法则得到校验位
    char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
    if (bit == 'N') {
      return false;
    }

    // 和银行卡号的校验位(最后一位)比较,相同为true 不同为false
    return cardId.charAt(cardId.length() - 1) == bit;
  }

  public static boolean checkCallOrMobile(String str) {
    String call = "^(0|86|17951)?(13[0-9]|15[012356789]|17[01678]|18[0-9]|14[57])[0-9]{8}$";
    String phone = "^(0[0-9]{2,3}\\-)([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";
    Pattern p_call = Pattern.compile(call);
    Matcher m_call = p_call.matcher(str);
    Pattern p_phone = Pattern.compile(phone);
    Matcher m_phone = p_phone.matcher(str);
    return !(!m_call.find() && !m_phone.find());
  }

  public static char getBankCardCheckCode(String nonCheckCodeCardId) {
    // 如果传的不是数字返回N
    if (nonCheckCodeCardId == null
        || nonCheckCodeCardId.trim().length() == 0
        || !nonCheckCodeCardId.matches("\\d+")) {
      return 'N';
    }
    char[] chs = nonCheckCodeCardId.trim().toCharArray();
    int luhmSum = 0;
    /** 注意是从下标为0处开始的 */
    for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
      int k = chs[i] - '0';
      /** 是偶数位数字做处理先乘以2（如果乘积为两位数，则将其减去9或个位与十位相加的和），再求和。 不是则不做处理 */
      if (j % 2 == 0) {
        k *= 2;
        k = k / 10 + k % 10;
      }
      luhmSum += k;
    }
    return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
  }

  public static boolean containsSpecialChar(String str) {
    String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.find();
  }

  public static boolean containsCNCode(String str) {
    Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
    Matcher m = p.matcher(str);
    return m.find();
  }

  public static boolean isCNCode(String str) {
    String reg = "[\\u4e00-\\u9fa5]+";
    return str.matches(reg);
  }


  // 替换特殊字符
  public static String Specialfilter(String str) {
    String regEx = "[`~!@#$%^&*()\\-+={}':;,\\[\\].<>/?￥%…（）_+|【】‘；：”“’。，、？\\s]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.replaceAll("").trim();
  }

  // 手机或者邮箱隐藏部分为*
  public static String phoneOrEmailToHide(String rolesName) {
    if (Utils.CheckMobile(rolesName)) {
      rolesName =
          rolesName.substring(0, rolesName.length() - (rolesName.substring(3)).length())
              + "****"
              + rolesName.substring(6);
    } else if (Utils.CheckEmail(rolesName)) {
      String[] arr = rolesName.split("@");
      String temp = arr[0];
      rolesName =
          temp.length() >= 6
              ? temp.substring(0, temp.length() - 4) + "****" + arr[1]
              : temp + arr[1];
    }
    return rolesName;
  }

  // 替换特殊字符
  public static String Specialfilter4Sql(String str) {
    if (str == null) return "";
    String regEx = "[%'_\"]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.replaceAll("").trim();
  }

  public static String joinWith(List<String> list, String chr) {
    String res = "";
    if (list == null || list.size() == 0) return res;
    for (String s : list) {
      res += s + chr;
    }
    return trimEnd(res, chr);
  }

  // 补全位数
  public static String getSerialNumber(String str, int length) {
    StringBuffer sb = new StringBuffer();
    // 补位数
    int n = length - str.length();
    for (int i = 0; i < n; i++) {
      sb.append("0");
    }
    sb.append(str);
    return sb.toString();
  }

  // 判断字符串是否为空
  public static Boolean IsNullOrEmpty(String s) {
    return s == null || "".equals(s.trim());
  }

  //	判断字符传为空或为"null"
  public static Boolean isEmptyOrNull(String s) {
    return StringUtils.isEmpty(s) || "null".equals(s) || "".equals(s.trim());
  }

  public static Boolean checkBooleanValue(String s) {
    return !IsNullOrEmpty(s) && ("1".equals(s.trim()) || "0".equals(s.trim()));
  }
  //	public static void main(String arg[]) {
  //		String a ="{\"msg\":\"更新成功\",\"code\":\"0000\"}";
  //		String b =JSONObject.toJSONString(a);
  //		System.out.println(b);
  //
  //		System.out.println("b=----json---=:"+JSONObject.parseObject(b));
  //	}

  public static JSONObject ToJson(String o) {
    if (o == null) return null;
    try {
      //			String a = JSONObject.toJSONString(o);
      return JSONObject.parseObject(o);
    } catch (Exception e) {
      return null;
    }
  }

  public static JSONArray ToJsonArray(Object o) {
    if (o == null) return new JSONArray();
    try {
      return (JSONArray) JSON.toJSON(o);
    } catch (Exception e) {
      return new JSONArray();
    }
  }

  public static String ToString(Object o) {
    if (o == null) return "";
    else if (o instanceof Timestamp) return o.toString().substring(0, 19);
    else return o.toString();
  }

  public static Integer ToInteger(Object o) {
    Integer integer = null;
    try {
      integer = Integer.valueOf(ToString(o));
    } catch (Exception e) {
      return integer;
    }
    return integer;
  }

  // 过滤单引号 返回String
  public static String Chk39(String Str) {
    if ((Str == null) || (Str.equals(""))) Str = "";
    else Str = Str.replaceAll("'", "&#39");
    return Str;
  }

  // 生成唯一ID 16 位
  public static String getGuid() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  // 生成唯一数字ID
  public static Long getNumGuid() {
    byte[] bytes = UUID.randomUUID().toString().replaceAll("-", "").getBytes();
    ByteBuffer buffer = ByteBuffer.allocate(8);
    buffer.put(bytes, 0, 8);
    buffer.flip(); // need f1lip
    return buffer.getLong();
  }

  public static String DateFormat(Date d, String f) {
    SimpleDateFormat sdf = new SimpleDateFormat(f, Locale.ENGLISH);
    if (d == null) return null;
    return sdf.format(d);
  }

  public static String DateFormat(Timestamp t, String f) {
    if (t == null) return "";
    SimpleDateFormat sdf = new SimpleDateFormat(f);
    Date date = new Date(t.getTime());
    return sdf.format(date);
  }

  public static Date toDate(String d, String f) {
    SimpleDateFormat sdf = new SimpleDateFormat(f);
    try {
      return sdf.parse(d);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static boolean CheckIsValid(String s, String v) {
    Pattern p = Pattern.compile(v);
    Matcher m = p.matcher(s);
    return m.matches();
  }

  public static boolean CheckMobile(String phone) {
    if (IsNullOrEmpty(phone)) return false;
    return Utils.CheckIsValid(phone, "^[1]+\\d{10}$");
  }

  public static Date addMin(Date d, int m) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(d);
    gc.add(Calendar.MINUTE, m);
    return gc.getTime();
  }

  public static Date addtime(Date d, int m, int type) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(d);
    gc.add(type, m);
    return gc.getTime();
  }

  public static JSONObject MapToJson(Map<String, Object> map) {
    return (JSONObject) JSON.toJSON(map);
  }

  public static <T> T map2bean(Map<String, Object> map, Class<T> clz) throws Exception {
    if (map == null) {
      return null;
    }
    T bean = null;
    try {
      String tStr = MapToJson(map).toJSONString();
      bean = JSONObject.parseObject(tStr, clz);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bean;
  }

  public static String readToEnd(BufferedReader read) {
    String result = "";
    String line;
    try {
      while ((line = read.readLine()) != null) {
        result += line;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static String MapToJsonWithOutTimestamp(Map<String, Object> map) {
    JSONObject res = new JSONObject();
    for (String key : map.keySet()) {
      if (map.get(key) == null) {
        map.put(key, "");
      }
    }
    res.putAll(map);
    return res.toString();
  }

  public static JSONArray ListToJsonWithRowId(List<Map<String, Object>> list) {
    int row = 1;
    for (Map<String, Object> map : list) {
      map.put("rowId", row + "");
      row++;
    }
    return (JSONArray) JSON.toJSON(list);
  }

  public static JSONArray ListToJson(List<Map<String, Object>> list) {
    return (JSONArray) JSON.toJSON(list);
  }

  public static ValueFilter filter =
      new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
          if (v == null) return "";
          return v;
        }
      };

  /**
   * @param code
   * @param msg
   * @param data
   * @return
   * @author Limin
   * @created 2018年6月28日 下午4:39:04
   * @lastModified
   * @history
   */
  public static String message(String code, String msg, Object data) {
    JSONObject mes = new JSONObject();
    mes.put("code", code);
    mes.put("msg", msg);
    if (data != null) mes.put("data", data);
    return JSON.toJSONString(mes, filter, SerializerFeature.DisableCircularReferenceDetect);
  }

  public static JSONObject messageObj(String code, String msg, Object data) {
    JSONObject mes = new JSONObject();
    mes.put("code", code);
    mes.put("msg", msg);
    if (data != null) mes.put("data", data);
    return mes;
  }

  public static String messageObjToString(JSONObject obj) {
    return JSON.toJSONString(obj, filter, SerializerFeature.DisableCircularReferenceDetect);
  }

  public static <T> String toPageMsg(Page<T> page) {
    JSONObject mes = new JSONObject();
    mes.put("code", "0000");
    mes.put("msg", "查询成功");
    mes.put("count", page.getTotal());
    mes.put("data", page.getRecords());
    return JSON.toJSONString(mes, filter, SerializerFeature.DisableCircularReferenceDetect);
  }

  public static boolean CheckIDCard18(String Id) {
    long n = 0;
    try {
      n = Long.parseLong(Id.substring(0, 17));
      if (n < Math.pow(10, 16)) return false;
      n = Long.parseLong(Id.replace("x", "0").replace("X", "0"));
    } catch (Exception e) {
      return false;
    }

    String address =
        "11x22x35x44x53x12x23x36x45x54x13x31x37x46x61x14x32x41x50x62x15x33x42x51x63x21x34x43x52x64x65x71x81x82x91";
    if (address.indexOf(Id.substring(0, 2)) == -1) {
      return false; // 省份验证
    }
    //		String[] arrVarifyCode = ("1,0,x,9,8,7,6,5,4,3,2").split(",");
    //		String[] Wi = ("7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2").split(",");
    //		char[] Ai = Id.substring(0, 17).toCharArray();
    //		int sum = 0;
    //		for (int i = 0; i < 17; i++) {
    //			sum += Integer.parseInt(Wi[i]) * Integer.parseInt(Ai[i] + "");
    //		}
    //		int y = -1;
    //		y = sum % 11;
    //		String v = Id.substring(17, 18).toLowerCase();
    //		if (!arrVarifyCode[y].equals(v)) {
    //			return false;// 校验码验证
    //		}
    return true; // 符合GB11643-1999标准
  }

  /// <summary>
  /// 验证15位身份证号
  /// </summary>
  /// <param name="Id">身份证号</param>
  /// <returns>验证成功为True，否则为False</returns>
  public static boolean CheckIDCard15(String Id) {
    long n = 0;
    try {
      n = Long.parseLong(Id);
      if (n < Math.pow(10, 14)) return false;
    } catch (Exception e) {
      return false;
    }
    String address =
        "11x22x35x44x53x12x23x36x45x54x13x31x37x46x61x14x32x41x50x62x15x33x42x51x63x21x34x43x52x64x65x71x81x82x91";
    if (address.indexOf(Id.substring(0, 2)) == -1) {
      return false; // 省份验证
    }
    String birth = Id.substring(6, 8) + "-" + Id.substring(8, 10) + "-" + Id.substring(10, 12);
    try {
      Utils.toDate("19" + birth, "yyyy-MM-dd");
    } catch (Exception e) {
      return false; // 生日验证
    }
    return true; // 符合15位身份证标准
  }

  public static JSONObject EtoJson(Object entity) {
    if (entity != null) {
      Field[] fields = entity.getClass().getDeclaredFields();
      Map<String, Object> map = new HashMap<String, Object>();
      for (Field field : fields) {
        String fieldName = field.getName();
        if (getValueByFieldName(fieldName, entity) != null)
          map.put(fieldName, getValueByFieldName(fieldName, entity).toString());
      }
      return MapToJson(map);
    } else return new JSONObject();
  }

  /**
   * 根据属性名获取该类此属性的值
   *
   * @param fieldName
   * @param object
   * @return
   */
  public static Object getValueByFieldName(String fieldName, Object object) {
    String firstLetter = fieldName.substring(0, 1).toUpperCase();
    String getter = "get" + firstLetter + fieldName.substring(1);
    try {
      Method method = object.getClass().getMethod(getter, new Class[] {});
      Object value = method.invoke(object, new Object[] {});
      return value;
    } catch (Exception e) {
      return null;
    }
  }

  public static boolean checkCardNO(String str) {
    if (str.length() == 18) {
      boolean check = CheckIDCard18(str);
      return check;
    } else if (str.length() == 15) {
      boolean check = CheckIDCard15(str);
      return check;
    } else {
      return false;
    }
  }

  // 判断日期是否格式正确
  public static Date TryParseDate(String date) {
    String[] formats = {
      "yyyy-MM-dd HH:mm:ss SSS",
      "yyyy-MM-dd HH:mm:ss",
      "yyyy-MM-dd HH:mm",
      "yyyy-MM-dd HH",
      "yyyy-MM-dd",
      "yyyy/MM/dd HH:mm:ss",
      "yyyy/MM/dd HH",
      "yyyy/MM/dd",
      "yyyy年MM月dd日"
    };
    Date time = null;
    if (IsNullOrEmpty(date)) return null;
    for (String format : formats) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      try {
        time = sdf.parse(date);
        break;
      } catch (ParseException e) {
        continue;
      }
    }
    return time;
  }

  // 通过日 月 判断星座
  @SuppressWarnings("deprecation")
  public static String GetZodiac(Date date) {
    if (date == null) return "";
    int month = date.getMonth() + 1;
    int day = date.getDate();
    String[] starArr = {
      "摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"
    };
    int[] DayArr = {20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};

    String star = day < DayArr[month - 1] ? starArr[month - 1] : starArr[month];
    return star;
  }

  // 如果最后一个字符串为参数 去掉
  public static String trimEnd(String s, String c) {
    if (s.endsWith(c)) {
      s = s.substring(0, s.length() - c.length());
    }
    return s;
  }

  // 如果第一个字符串为参数 去掉
  public static String trimStart(String s, String c) {
    if (s.startsWith(",")) {
      s = s.substring(1, s.length());
    }
    return s;
  }

  public static boolean CheckNumInt(String num) {
    if (Utils.IsNullOrEmpty(num)) return false;
    Pattern pattern = Pattern.compile("[0-9]+");
    Matcher isNum = pattern.matcher(num);
    return isNum.matches();
  }

  public static boolean CheckNumDouble(String num) {
    if (Utils.IsNullOrEmpty(num)) return false;
    Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
    Matcher isNum = pattern.matcher(num);
    return isNum.matches();
  }

  public static int GetAgebyCard(String IdNO) {
    int leh = IdNO.length();
    int age = 0;
    if (leh == 18) {
      String yyy = IdNO.substring(6, 10); // 出生的年份
      String now = DateFormat(new Date(), "yyyy");
      age = Integer.parseInt(now) - Integer.parseInt(yyy);
    }
    if (leh == 15) {
      String yyy = "19" + IdNO.substring(6, 8); // 出生的年份
      String now = DateFormat(new Date(), "yyyy");
      age = Integer.parseInt(now) - Integer.parseInt(yyy);
    }
    return age;
  }


  public static <T> boolean exist(List<T> list) {
    if (list != null && list.size() > 0) {
      return true;
    } else {
      return false;
    }
  }

  /*
   * 邮箱格式验证
   */
  public static boolean CheckEmail(String Str) {
    if (IsNullOrEmpty(Str)) return false;
    String pattern =
        "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(Str);
    return m.matches();
  }


  /***
   * encode by Base64
   */
  public static String encodeBase64(byte[] input) throws Exception {
    Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
    Method mainMethod = clazz.getMethod("encode", byte[].class);
    mainMethod.setAccessible(true);
    Object retObj = mainMethod.invoke(null, new Object[] {input});
    return (String) retObj;
  }

  /***
   * decode by Base64
   */
  public static String decodeBase64(String input) throws Exception {
    Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
    Method mainMethod = clazz.getMethod("decode", String.class);
    mainMethod.setAccessible(true);
    Object retObj = mainMethod.invoke(null, input);
    return new String((byte[]) retObj);
  }


  private static final Object Locker4PayOrder = new Object();

  public static String getPayNo(String PayWay) {
    synchronized (Locker4PayOrder) {
      Random ran = new Random();
      return PayWay
          + Utils.DateFormat(new Date(), "yyyyMMddHHmmssSSS")
          + (ran.nextInt(9000) + 1000);
    }
  }

  private static final Object Locker4Transfer = new Object();

  public static String getTransferNo() {
    synchronized (Locker4Transfer) {
      Random ran = new Random();
      return Utils.DateFormat(new Date(), "yyyyMMddHHmmss") + (ran.nextInt(9000) + 1000);
    }
  }

  // 判断json字符串
  public static boolean isJson(Object obj) {
    boolean flag = true;
    try {
      String jsonStr = JSONObject.toJSONString(obj);
      if (IsNullOrEmpty(jsonStr)) {
        return false;
      }
      JSONObject.parseObject(jsonStr);
    } catch (Exception e) {
      flag = false;
    }
    return flag;
  }

  // 字符串转化为BigDecimal
  public static BigDecimal toBigDecimal(Object temp) {
    BigDecimal b = BigDecimal.ZERO;
    try {
      b = new BigDecimal(Utils.ToString(temp));
    } catch (Exception e) {
      return b;
    }
    return b;
  }

  /**
   * 获取精确到秒的时间戳
   *
   * @return
   */
  public static int getSecondTimestamp(Date date) {
    if (null == date) {
      return 0;
    }
    String timestamp = String.valueOf(date.getTime());
    int length = timestamp.length();
    if (length > 3) {
      return Integer.valueOf(timestamp.substring(0, length - 3));
    } else {
      return 0;
    }
  }

  /**
   * 判断一个字符串是不是JsonArray字符串
   *
   * @param jsonString
   * @return
   */
  public static boolean isJsonArray(String jsonString) {
    try {
      JSONObject.parseArray(jsonString);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * 判断两字符是否相等，不为空
   *
   * @param sessionToken
   * @param token
   * @return
   * @author CuiYuan
   * @created 2018年7月26日 下午1:41:24
   * @lastModified
   * @history
   */
  public static boolean equals(String sessionToken, String token) {
    if (Utils.isEmptyObj(sessionToken, token)) {
      return false;
    }
    if (sessionToken.equals(token)) {
      return true;
    }
    return false;
  }

  /**
   * 正则匹配获取结果-String
   *
   * @author wq, 2020-08-13
   * @param str
   * @param regexStr
   * @param type: -1表示全部，0表示取首个匹配结果
   * @return
   */
  public static String regexText_string(String str, String regexStr, int type) {
    List<String> list = regexText_list(str, regexStr);
    if (CollectionUtils.isNotEmpty(list)) {
      if (type == -1) {
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
          builder.append(s);
        }
        return builder.toString();
      } else if (type == 0) {
        return list.get(0);
      }
    }
    return null;
  }

  /**
   * 正则匹配获取结果-List
   *
   * @author wq, 2020-08-13
   * @param str
   * @param regexStr
   * @return
   */
  public static List<String> regexText_list(String str, String regexStr) {
    if (StringUtils.isBlank(str) || StringUtils.isBlank(regexStr)) {
      return null;
    }
    if (StringUtils.countMatches(regexStr, "(") - StringUtils.countMatches(regexStr, "\\(")
        == StringUtils.countMatches(regexStr, "(?:")
            - StringUtils.countMatches(regexStr, "\\(?:")) {
      regexStr = "(" + regexStr + ")";
    }
    try {
      List<String> resultList = new ArrayList<>();
      Pattern pattern = Pattern.compile(regexStr, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(str);
      while (matcher.find()) {
        String[] groups = new String[matcher.groupCount() + 1];
        for (int i = 0; i < groups.length; i++) {
          groups[i] = matcher.group(i);
        }
        resultList.add(groups[1]);
      }
      return resultList;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

//  public static void main(String[] args) {
//    String str = "https://ans-edu.oss-cn-hangzhou.aliyuncs.com/crm/document/acv/WechatIMG36.png";
//    System.out.println(regexText_string(str, "http[s]?://ans-edu.oss-cn-hangzhou.aliyuncs.com/crm/document/acv/(.*)", 0));
//  }
}
