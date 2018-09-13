package com.bcht.axletempmonitor.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcht.axletempmonitor.config.ShiroConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JsonToMapUtils {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseJsonToMap(String jsonStr){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.parseObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<Object> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = (JSONObject) it.next();
                    list.add(parseJsonToMap(json2.toString()));
                }
                map.put(k.toString(), list);
                logger.info("k.toString()="+k.toString()+"list="+list);
            } else {
                map.put(k.toString(), v);
                logger.info("k.toString()="+k.toString()+"v="+v);
            }
        }
        return map;
    }

    public static String mapToJson(Map<String, String> map) {
        Set<String> keys = map.keySet();
        String key = "";
        String value = "";
        StringBuffer jsonBuffer = new StringBuffer();
        jsonBuffer.append("{");
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            key = (String) it.next();
            value = map.get(key);
            jsonBuffer.append(key + ":" +"\""+ value+"\"");
            if (it.hasNext()) {
                jsonBuffer.append(",");
            }
        }
        jsonBuffer.append("}");
        return jsonBuffer.toString();
    }

}
