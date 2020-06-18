package com.luckyun.tjam.prophaseMag.excelUtil.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;

@Component
public class Util {

    public HashMap<String, Object> convertToMap(Object obj)
            throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        if (fields.length > 0){
            for (int i = 0, len = fields.length; i < len; i++) {
                String varName = fields[i].getName();
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);

                Object o = fields[i].get(obj);
                if (o != null)
                    map.put(varName, o.toString());

                fields[i].setAccessible(accessFlag);
            }
        }
        return map;
    }
}