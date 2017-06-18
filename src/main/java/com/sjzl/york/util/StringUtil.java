package com.sjzl.york.util;

import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/18 16:21
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static String param2String(Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        for (String key : param.keySet()) {
            String value = param.get(key);
            sb.append(key);
            sb.append("=");
            sb.append(value);
        }
        return sb.toString();
    }

    public static String object2Json(Object obj) {
        if (obj == null) {
            return "null";
        }
        return JSONObject.fromObject(obj).toString();
    }

    /**
     * 获取非null的字符串(如果为null,则转为空字符串)
     *
     * @param str
     * @return
     */
    public static String cleanNullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 将值为null的整数改为0
     *
     * @param num
     * @return
     */
    public static int cleanNullToZero(Integer num) {
        if (num == null) {
            return 0;
        }
        return num;
    }

    /**
     * 将值为null的长整数改为0
     *
     * @param num
     * @return
     */
    public static long cleanNullToZero(Long num) {
        if (num == null) {
            return 0L;
        }
        return num;
    }

    /**
     * 将值为null的Double改为0
     *
     * @param num
     * @return
     */
    public static double cleanNullToZero(Double num) {
        if (num == null) {
            return 0d;
        }
        return num;
    }

    /**
     * 将值为null的Float改为0
     *
     * @param num
     * @return
     */
    public static float cleanNullToZero(Float num) {
        if (num == null) {
            return 0f;
        }
        return num;
    }

    /**
     * 对象转换成String
     * 当obj为null时返回null
     *
     * @param obj
     * @return
     */
    public static <T> String object2String(T obj) {
        if (null == obj) {
            return null;
        }

        return obj.toString();
    }

    private static final String PHONE_NUM_REG = "((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))(\\D*\\d){8}";
    private static final String TELL_NUM_REG = "((\\((\\D{0,3}\\d){3,4}\\))|((\\D{0,3}\\d){3,4}-?))(\\D{0,3}\\d){7,8}";

    /**
     * 判断字符串是否包含手机号
     *
     * @param targetstr
     * @return
     */
    public static boolean containsPhoneNum(String targetstr) {
        Pattern p = Pattern.compile(PHONE_NUM_REG);
        Matcher matcher = p.matcher(targetstr);
        return matcher.find();
    }

    /**
     * 判断字符串是否包含电话号码
     *
     * @param targetstr
     * @return
     */
    public static boolean containsTellNum(String targetstr) {
        Pattern p = Pattern.compile(TELL_NUM_REG);
        Matcher matcher = p.matcher(targetstr);
        return matcher.find();
    }

    /**
     * 是否是指定长度的字符串
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isNumStr(String str, int length) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }
        Pattern p = Pattern.compile("\\d{" + length + "}");
        Matcher matcher = p.matcher(str);
        return matcher.matches();
    }

    /**
     * 将map转换为json数据
     *
     * @param map
     * @return
     */
    public static String map2Json(Map map) {
        JSONObject jo = JSONObject.fromObject(map);
        return jo.toString();
    }

    /**
     * 如果值为null,则返回0
     *
     * @param num
     * @return
     */
    public static Short cleanNullToZero(Short num) {
        if (num == null) {
            return 0;
        }
        return num;
    }


    /**
     * 如果值为null,则返回0
     *
     * @param num
     * @return
     */
    public static BigDecimal cleanNullToZero(BigDecimal num) {
        if (num == null) {
            return BigDecimal.ZERO;
        }
        return num;
    }


    /**
     * 去除字符串左右两遍的空格
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        if (str != null) {
            return str.trim();
        }
        return str;
    }

    /**
     * 返回指定长度的字符串
     *
     * @param length
     * @return
     */
    private static char ch[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3'};//有重复，因为需要凑足数组长度为64

    private static Random random = new Random();

    public static synchronized String createRandomString(int length) {
        if (length > 0) {
            int index = 0;
            char[] temp = new char[length];
            int num = random.nextInt();

            for (int i = 0; i < length % 5; i++) {
                temp[index++] = ch[num & 63];//取后面六位，记得对应的二进制是以补码形式存在的。
                num >>= 6;//63的二进制为:111111
                // 为什么要右移6位？因为数组里面一共有64个有效字符。为什么要除5取余？因为一个int型要用4个字节表示，也就是32位。
            }

            for (int i = 0; i < length / 5; i++) {
                num = random.nextInt();
                for (int j = 0; j < 5; j++) {
                    temp[index++] = ch[num & 63];
                    num >>= 6;
                }
            }
            return new String(temp, 0, length);
        } else if (length == 0) {
            return "";
        } else {
            throw new IllegalArgumentException();
        }
    }
}
