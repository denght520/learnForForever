package com.learn.forever.core.util;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author : yangwangsen
 * @version 1.0
 * @projectName：bw-common
 * @className：ToStringSerializer
 * @date 2019/5/23 16:52
 * @E-mail:yangwangsen@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class ToStringSerializer implements ObjectSerializer {

    public static final ToStringSerializer instance = new ToStringSerializer();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType,
                      int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        String strVal = object.toString();
        out.writeString(strVal);
    }
}
