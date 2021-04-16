package com.tool.util;

import java.io.*;
import java.util.*;

/**
 * Description : 檔案相關工具
 * Author : ChiYuan
 * Date : 2019/04/29 16:00
 */
public class FileUtil {

    /**
     * Load Properties with specific charset
     *
     * @param file Target file name without file extension
     * @return Properties
     * @throws Exception
     */
    public static Properties loadProp(File file, String encoding) throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = FileUtil.class.getClassLoader().getResource(String.valueOf(file)).openStream();
        properties.load(inputStream);
        return properties;
    }

    /**
     * Load Properties
     *
     * @param fileName Target file name without file extension
     * @return Properties
     * @throws Exception
     */
    public static Properties loadProp(String fileName) throws Exception {

        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);
        Enumeration enumeration = resourceBundle.getKeys();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            String value = resourceBundle.getString(key);
            hashMap.put(key, value);
        }

        Properties properties = new Properties();
        properties.putAll(hashMap);

        return properties;
    }

    /**
     * Load Properties with specific Locale
     *
     * @param fileName Target file name without file extension
     * @param locale   Locale
     * @return Properties
     * @throws Exception
     */
    public static Properties loadProp(String fileName, Locale locale) throws Exception {

        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName, locale);
        Enumeration enumeration = resourceBundle.getKeys();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            String value = resourceBundle.getString(key);
            hashMap.put(key, value);
        }

        Properties properties = new Properties();
        properties.putAll(hashMap);

        return properties;
    }

    /**
     * Load Properties with specific charset
     *
     * @param fileName Target file name without file extension
     * @param charset  charset
     * @return Properties
     * @throws Exception
     */
    public static Properties loadProp(String fileName, String charset) throws Exception {

        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);
        Enumeration enumeration = resourceBundle.getKeys();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        while (enumeration.hasMoreElements()) {
            String key = new String(enumeration.nextElement().toString().getBytes("ISO-8859-1"), charset);
            String value = new String(resourceBundle.getString(key).getBytes("ISO-8859-1"), charset);
            hashMap.put(key, value);
        }

        Properties properties = new Properties();
        properties.putAll(hashMap);

        return properties;
    }

    /**
     * Load Properties with specific charset
     *
     * @param file Target file name without file extension
     * @return Properties
     * @throws Exception
     */
    public static Properties loadProp(File file) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        return properties;
    }

    /**
     * 載入檔案(忽略空白、#註解)
     *
     * @param file     file
     * @param encoding encoding
     * @return List
     * @throws Exception
     */
    public static List<String> loadFile(File file, String encoding) throws Exception {
        List<String> ret = new ArrayList<>();
        InputStreamReader fileReader = new InputStreamReader(FileUtil.class.getClassLoader().getResource(String.valueOf(file)).openStream(), encoding);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {

            //定義 # 開頭為註解。空行及註解不處理。
            if (line.startsWith("#") || line.equals("") || line.startsWith("[")) {
                continue;
            }

            ret.add(line);
        }

        return ret;
    }

    /**
     * 載入檔案(忽略空白、#註解)
     *
     * @param file file
     * @return List
     * @throws Exception
     */
    public static List<String> loadFile(File file) throws Exception {
        List<String> ret = new ArrayList<>();
        InputStreamReader fileReader = new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {

            //定義 # 開頭為註解。空行及註解不處理。
            if (line.startsWith("#") || line.equals("") || line.startsWith("[")) {
                continue;
            }

            ret.add(line);
        }

        return ret;
    }

    /**
     * 儲存檔案
     *
     * @param fileLocate
     * @param fileContent
     * @throws IOException
     */
    public static void saveFile(String fileLocate, String fileContent) throws IOException {
        saveFile(fileLocate, fileContent, false);
    }

    public static void saveFile(String fileLocate, String fileContent, boolean append) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileLocate, append);
        fileOutputStream.write(fileContent.getBytes("utf8"));
        fileOutputStream.close();
    }

}