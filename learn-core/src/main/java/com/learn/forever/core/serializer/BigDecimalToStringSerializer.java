package com.learn.forever.core.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * @author : neling chen
 * @version 1.0
 * @projectName : bw-common
 * @className : BigDecimalToStringSerializer
 * @date 2019/8/18 10:46
 * @E-mail: chenneling@oxyzgroup.com
 * @copyright : 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class BigDecimalToStringSerializer implements ObjectSerializer {
    public static final BigDecimalToStringSerializer instance = new BigDecimalToStringSerializer();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        String strVal = "";
        if (object instanceof BigDecimal) {
            BigDecimal b = (BigDecimal) object;
            strVal = b.stripTrailingZeros().toPlainString();
        }
        if (StringUtils.isBlank(strVal)) {
            strVal = "0";
        }
        out.writeString(strVal);
    }
}
