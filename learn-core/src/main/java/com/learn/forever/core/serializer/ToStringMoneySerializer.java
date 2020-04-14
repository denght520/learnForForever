package com.learn.forever.core.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 金钱分转 元 1000  10
 *
 * @author : yangwangsen
 * @version 1.0
 * @projectName：bw-common
 * @className：test
 * @date 2019/6/11 21:37
 * @E-mail:yangwangsen@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class ToStringMoneySerializer implements ObjectSerializer {

    public static final com.bluewhale.serializer.ToStringMoneySerializer instance = new com.bluewhale.serializer.ToStringMoneySerializer();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        String strVal = object.toString();
        if (object instanceof Long) {
            Long l = (Long) object;
            strVal = String.valueOf(l / 100.0);
            out.writeString(strVal);
        }

    }

}
