package com.mall.core.common;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Title:SystemUtil</p>
 * <p>Description:(系统工具类)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author jackgrays
 * @version v1.0
 */
public final class SystemUtil {

    /**
     * 配置文件位置
     */
    private static final String baseFile = "config/base.properties";

    private static final Properties prop = new Properties();

    static{
        try {
            InputStream in = SystemUtil.class.getClassLoader().getResourceAsStream(baseFile);
            if (null != in) {
                prop.load(new InputStreamReader(in,"UTF-8"));
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getBaseValue(String key) {
        return prop.getProperty(key);
    }

    public static String getBaseValue(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (null == obj) {
            return true;
        }else{
            if (obj instanceof String) {
                return ((String) obj).length() == 0;
            }else
            if (obj instanceof Collection) {
                return ((Collection<?>) obj).size() == 0;
            }else
            if (obj instanceof Map) {
                return ((Map<?, ?>) obj).size() == 0;
            }else {
                return false;
            }
        }
    }

    /**
     * 写pid文件到linux文件系统，和resources下配合
     */
    public static void writePidToFileForLinux() {
        if (System.getProperty("os.name").toLowerCase().equals("linux")) {
            String fileName = System.getProperty("pid.file");
            if (null == fileName)
                return;//the console mode doesn't need it
//                throw new IllegalStateException("for the linux system,you need to set the system env \"pid.file\" ");
            File file = new File(fileName);
            file.deleteOnExit();
            try {
                FileWriter fw = new FileWriter(file);
                fw.write(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
                fw.flush();
                fw.close();
            } catch (IOException e) {
                throw new IllegalAccessError("can't write to the pid.file \""
                        + file.getAbsolutePath() + "\"");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> BeanToMap(Object pojo) {
        Map<K, V> ret = new HashMap<K, V>();
        try {
            Method[] methods = pojo.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    Object value = method.invoke(pojo, (Object[]) null);
                    ret.put((K) field, (V) (null == value ? null : value));
                }
            }
        } catch (Exception ignored) {
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(getBaseValue("123", "12"));
        System.out.println(getBaseValue("qq"));
        System.out.println(getBaseValue("port"));
        String str = "";
        System.out.println(isNullOrEmpty(str));
    }

}
