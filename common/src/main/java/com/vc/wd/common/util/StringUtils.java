package com.vc.wd.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mwqi on 2014/6/8.
 */
public class StringUtils {
    public final static String UTF_8 = "UTF-8";

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return [0-9]{5,9}
     */
    public boolean isMobileNO(String mobiles) {
        try {
            Pattern p = Pattern
                    .compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            return m.matches();
        } catch (Exception e) {
        }
        return false;
    }
}
