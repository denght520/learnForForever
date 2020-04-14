package com.learn.forever.core.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.bluewhale.util.AmountUtils;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Jaxling
 * @version V1.0
 * @className: LongToStripZeroAmountSerializer
 * @description Long类型的金额转字符串并且去掉无用的0
 * @date 2019/8/26 11:51
 */
public class ToStripZeroAmountSerializer implements ObjectSerializer {

    public static final com.bluewhale.serializer.ToStripZeroAmountSerializer instance = new com.bluewhale.serializer.ToStripZeroAmountSerializer();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        String strVal = object.toString();
        if (object instanceof Long) {
            strVal = AmountUtils.stripZeroString((Long) object);
        }
        out.writeString(strVal);
    }
}
