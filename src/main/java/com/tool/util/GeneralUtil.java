package com.tool.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;

import java.security.MessageDigest;
import java.util.List;
import java.util.Properties;

/**
 * WebFetcher : com.eland.fetcher.utils
 * -----
 * Description : 通用工具
 * Author : ChiYuan
 * Date : 2019/04/29 16:00
 */
public class GeneralUtil {

    private static Logger logger = LogManager.getLogger(GeneralUtil.class);

    /**
     * messageDigest5
     * @param source source
     * @return digest out with hex
     */
    public static String messageDigest5(String source) {
        String message = source;

        try {
            MessageDigest messageDigest;
            messageDigest = MessageDigest.getInstance("MD5");

            byte[] messageBytes = message.getBytes("UTF-8");
            messageDigest.update(messageBytes);
            byte byteData[] = messageDigest.digest();

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : byteData) {
                int positive = (b & 0xff) + 0x100;
                String hexString = Integer.toHexString(positive);
                stringBuilder.append(hexString.substring(1));
            }

            message = stringBuilder.toString();
        } catch (Exception e) {
            logger.error("Error in messageDigest5 : " + e.toString());
        }

        return message;
    }

    /**
     * Thread sleep millis
     * @param millis millis
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            logger.error((Message) null, e);
        }
    }

    /**
     * Thread sleep 在兩數之間取亂數的 millis
     * @param millis1 millis
     * @param millis2 millis
     */
    public static void sleep(long millis1, long millis2) {
        long random = (long) MathUtil.getRandomBetween((int) millis1, (int) millis2);
        sleep(random);
    }

    /**
     * 由 Properties 取得 key 的 value，並且轉為指定型態，若取出的 value 有問題，會用 defaultValue
     * @param properties properties
     * @param key key
     * @param c class
     * @param defaultValue defaultValue
     * @param <T> generic type
     * @return result
     */
    public static <T> T getSetting(Properties properties, String key, Class c, T defaultValue) {
        T value = defaultValue;

        try {
            if(properties!=null) {
                T t = GeneralUtil.getSetting(properties, key, c);
                value = t == null ? defaultValue : t;
            }
        } catch (Exception e) {
            logger.error((Message) null, e);
        }

        return value;
    }

    /**
     * 由 Properties 取得 key 的 value，並且轉為指定型態
     * @param properties properties
     * @param key key
     * @param c class
     * @param <T> generic type
     * @return result
     */
    public static <T> T getSetting(Properties properties, String key, Class c) {
        T t = null;

        try {
            String value = properties.getProperty(key);
            if (value != null) {

                Object o;

                if (c.equals(Integer.class) || c.equals(Integer.TYPE)) {
                    o = Integer.parseInt(value);
                } else if (c.equals(Long.class) || c.equals(Long.TYPE)) {
                    o = Long.parseLong(value);
                } else if (c.equals(Short.class) || c.equals(Short.TYPE)) {
                    o = Short.parseShort(value);
                } else if (c.equals(Float.class) || c.equals(Float.TYPE)) {
                    o = Float.parseFloat(value);
                } else if (c.equals(Double.class) || c.equals(Double.TYPE)) {
                    o = Double.parseDouble(value);
                } else if (c.equals(Character.class) || c.equals(Character.TYPE)) {
                    o = value.charAt(0);
                } else if (c.equals(Byte.class) || c.equals(Byte.TYPE)) {
                    o = Byte.parseByte(value);
                } else if (c.equals(Boolean.class) || c.equals(Boolean.TYPE)) {
                    o = Boolean.parseBoolean(value);
                } else {
                    o = value;
                }

                t = cast(o);
            }
        } catch (Exception e) {
            String message = "key : " + key + " ; " + "Type : " + c.getSimpleName();
            logger.error(message, e);
        }

        return t;
    }

    /**
     * 型別轉換
     * @param list
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> cast(List list) {
        return (List<T>) list;
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object o) {
        return (T) (o);
    }

}