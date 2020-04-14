package com.learn.forever.core.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * @author : neling chen
 * @version 1.0
 * @projectName : bw-common
 * @className : ToStringHourSerializer
 * @date 2019/8/12 10:53
 * @E-mail: chenneling@oxyzgroup.com
 * @copyright : 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class ToStringHourSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        String strVal = "";
        if (object instanceof Long) {
            BigDecimal result = BigDecimal.valueOf((Long) object);
            result = result.divide(BigDecimal.valueOf(1000L * 60L * 60L), 2, BigDecimal.ROUND_HALF_UP);
            strVal = result.toPlainString();
        }
        out.writeString(strVal);
    }
}
