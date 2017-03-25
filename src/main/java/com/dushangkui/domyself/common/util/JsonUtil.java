package com.dushangkui.domyself.common.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.*;

/**
 * 此类描述的是：json 工具类
 * 
 * @author xujinfei
 */

public class JsonUtil extends JsonFormatter {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 
     * 此方法描述的是：根据key取得相应的值
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @return String
     */
    public static String getString(Map<String, Object> map, String key) {
        try {
            return (String) map.get(key);
        } catch (Exception e) {
            return JsonUtil.toString(map.get(key));
        }
    }

    /**
     * 
     * 此方法描述的是：取得list
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> getList(Map<String, Object> map, String key) {
        if (null == map) {
            return null;
        }
        try {
            return (List<Map<String, Object>>) map.get(key);
        } catch (Exception e) {
            return JsonUtil.toList(map.get(key));
        }

    }

    /**
     * 
     * 此方法描述的是：取得list
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMap(Map<String, Object> map, String key) {
        try {
            return (Map<String, Object>) map.get(key);
        } catch (Exception e) {
            return JsonUtil.toBean(map.get(key), Map.class);
        }

    }

    /**
     * 
     * 此方法描述的是：根据key取值int
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @param defaultValue
     *            默认值
     * @return int
     */
    public static int getInt(Map<String, Object> map, String key, int defaultValue) {
        try {
            return (Integer) map.get(key);
        } catch (Exception e) {
            try {
                return Integer.parseInt(JsonUtil.toString(map.get(key)));
            } catch (Exception e2) {
                return defaultValue;
            }
        }
    }

    /**
     * 
     * 此方法描述的是：根据key取BigDecial
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @param defaultValue
     *            默认值
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(Map<String, Object> map, String key, BigDecimal defaultValue) {
        return new BigDecimal(getDouble(map, key, defaultValue.doubleValue()));
    }

    /**
     * 
     * 此方法描述的是：根据key取BigDecial
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(Map<String, Object> map, String key) {
        return new BigDecimal(getDouble(map, key));
    }

    /**
     * 
     * 此方法描述的是：根据key取值int
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @return int
     */
    public static int getInt(Map<String, Object> map, String key) {
        return JsonUtil.getInt(map, key, 0);
    }

    /**
     * 
     * 此方法描述的是：根据key取得boolean值
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @param defaultValue
     *            默认值
     * @return boolean
     */
    public static boolean getBoolean(Map<String, Object> map, String key, boolean defaultValue) {
        try {
            return (Boolean) map.get(key);
        } catch (Exception e) {
            try {
                return Boolean.parseBoolean(JsonUtil.toString(map.get(key)));
            } catch (Exception e2) {
                return defaultValue;
            }
        }
    }

    /**
     * 
     * 此方法描述的是：根据key取得boolean值,默认为false
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @return boolean
     */
    public static boolean getBoolean(Map<String, Object> map, String key) {
        return getBoolean(map, key, false);
    }

    /**
     * 
     * 此方法描述的是：向obj数组中加新元素,
     * 
     * @param list
     *            list
     * @param obj
     *            增加的元素
     */
    @SuppressWarnings("unchecked")
    public static void add(List<HashMap<String, Object>> list, Object obj) {
        list.add(JsonUtil.toBean(list, HashMap.class));
    }

    /**
     * 
     * 此方法描述的是：根据key取得double值
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @param defaultValue
     *            默认值
     * @return double
     */
    public static double getDouble(Map<String, Object> map, String key, double defaultValue) {
        try {
            return (Double) map.get(key);
        } catch (Exception e) {
            try {
                return Double.parseDouble(JsonUtil.toString(map.get(key)));
            } catch (Exception e2) {
                return defaultValue;
            }
        }

    }

    /**
     * 
     * 此方法描述的是：根据key取得double值
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @return double
     */
    public static double getDouble(Map<String, Object> map, String key) {
        return getDouble(map, key, 0D);
    }

    /**
     * 
     * 此方法描述的是：根据key取得double值
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @param defaultValue
     *            默认值
     * @return long
     */
    public static long getLong(Map<String, Object> map, String key, long defaultValue) {
        try {
            return (Long) map.get(key);
        } catch (Exception e) {
            try {
                return Long.parseLong(JsonUtil.toString(map.get(key)));
            } catch (Exception e2) {
                return defaultValue;
            }
        }
    }

    /**
     * 
     * 此方法描述的是：根据key取得double值
     * 
     * @param map
     *            欲取值的map
     * @param key
     *            key
     * @return long
     */
    public static long getLong(Map<String, Object> map, String key) {
        return getLong(map, key, 0L);
    }

    /**
     * 
     * 此方法描述的是：将Object转化为Json格式字符串
     * 
     * @param obj
     *            欲转换的对象
     * @return String
     */
    public static String toString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        } else if (null == obj) {
            return null;
        }
        try {
            return JsonFormatter.toJsonString(obj);
        } catch (JsonGenerationException e) {
            LOG.error(e.getMessage(),e);
        } catch (JsonMappingException e) {
            LOG.error(e.getMessage(),e);
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 
     * 此方法描述的是：将Object转化为Json格式字符串
     * 
     * @param obj
     *            欲转换的对象
     * @param dateFormat
     *            日期format
     * @return String
     */
    public static String toString(Object obj, DateFormat dateFormat) {
        if (obj instanceof String) {
            return (String) obj;
        } else if (null == obj) {
            return null;
        }
        try {
            JsonFormatter.setDateFormat(dateFormat);
            return JsonFormatter.toJsonString(obj);
        } catch (JsonGenerationException e) {
            LOG.error(e.getMessage());
        } catch (JsonMappingException e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

    /**
     * 
     * 此方法描述的是：将传入的对象转换成指定的对象
     * 
     * @param <T>
     *            模板类
     * @param cls
     *            与转化的类
     * @param obj
     *            被转换的对象
     * @return T
     */
    public static <T> T toBean(Object obj, Class<T> cls) {
        if (null == obj) {
            return null;
        }
        try {
            return JsonFormatter.toObject(JsonUtil.toString(obj), cls);
        } catch (JsonParseException e) {
            LOG.error(e.getMessage());
        } catch (JsonMappingException e) {
            LOG.error(e.getMessage());
        } catch (JsonGenerationException e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

    /**
     *
     * 此方法描述的是：将传入的对象转换成指定的对象
     *
     * @param <T>
     *            模板类
     * @param cls
     *            与转化的类
     * @param obj
     *            被转换的对象
     * @return T
     */
    public static <T> T toBean(Object obj, TypeReference cls) {
        if (null == obj) {
            return null;
        }
        try {
            return JsonFormatter.toObject(JsonUtil.toString(obj), cls, true);
        } catch (JsonParseException e) {
            LOG.error(e.getMessage());
        } catch (JsonMappingException e) {
            LOG.error(e.getMessage());
        } catch (JsonGenerationException e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }
    /**
     * 
     * 此方法描述的是：字符串转换为List<map>
     * 
     * @param obj
     *            与转换的对象
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> toList(Object obj) {
        List<Map<String, Object>> lists = new LinkedList<Map<String, Object>>();
        List<Object> list = JsonUtil.toBean(obj, List.class);
        if (null != list) {
            for (Object object : list) {
                Map<String, Object> map = JsonUtil.toBean(object, HashMap.class);
                if (null != map) {
                    lists.add(map);
                }
            }
        }
        return lists;
    }

    /**
     * 
     * 此方法描述的是：字符串转换为List
     * 
     * @param <T>
     *            模板类
     * @param cls
     *            与转化的类
     * @param obj
     *            被转换的对象
     * @return T
     * 
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> toList(Object obj, Class<T> cls) {
        List<T> lists = new LinkedList<T>();
        List<Object> list = JsonUtil.toBean(obj, List.class);
        if (null != list) {
            for (Object object : list) {
                T t = JsonUtil.toBean(object, cls);
                if (null != t) {
                    lists.add(t);
                }
            }
        }
        return lists;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonObjectToMap(JSONObject json) {
        Map<String, Object> map = null;
        try {
            map = JsonUtil.toObject(json.toString(), Map.class);
        } catch (JsonParseException e1) {
        	 LOG.error(e1.getMessage(),e1);
        } catch (JsonMappingException e1) {
        	LOG.error(e1.getMessage(),e1);
        } catch (IOException e1) {
        	LOG.error(e1.getMessage(),e1);
        }
        return map;
    }

    public static List<Map<String, Object>> jsonArrayToList(JSONArray jsonArray) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            list.add(jsonObjectToMap(json));
        }
        return list;
    }

}
