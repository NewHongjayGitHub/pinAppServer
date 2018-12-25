package com.yangkw.pin.service.util;

import java.util.Random;

/**
 * 类CharUtil.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-22
 */
public class RandomUtil {
    private static Random random = new Random();
    private static StringBuffer sb = new StringBuffer();

    private RandomUtil() {
    }

    public static String getRandomString(Integer num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        return getString(num, base);
    }

    public static String getNumber(Integer num) {
        String base = "0123456789";
        return getString(num, base);
    }

    private static String getString(Integer num, String base) {
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        try {
            return sb.toString();
        } finally {
            sb.delete(0, sb.length());
        }
    }
}
