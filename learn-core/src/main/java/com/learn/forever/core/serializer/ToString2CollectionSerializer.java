package com.learn.forever.core.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @projectName: bw-common
 * @className: ToString2CollectionSerializer
 * @author: 陆锡书
 * @date: 2020/1/6 17:15
 * @version: 1.0
 * @E-mail: luxishu@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class ToString2CollectionSerializer implements ObjectSerializer {

    public static com.bluewhale.serializer.ToString2CollectionSerializer instance = new com.bluewhale.serializer.ToString2CollectionSerializer();
    private static final String SPILT = ",";

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
            throws IOException {
        SerializeWriter out = serializer.out;
        if ((object instanceof String)) {
            String value = (String) object;
            value = value.replaceAll("，", SPILT);
            String[] array = value.split(SPILT);
            if (array != null && array.length > 0){
                out.write(Lists.newArrayList(array));
            }
        } else {
            out.write(object.toString());
        }
    }
}
