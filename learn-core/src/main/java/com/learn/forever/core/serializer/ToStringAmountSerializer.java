package com.learn.forever.core.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.bluewhale.util.AmountUtils;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 金额Long转String并且分转元
 *
 * @projectName: bw-common
 * @className: ToStringAmountSerializer
 * @author: 陆锡书
 * @date: 2019-06-06 14:30
 * @version: 1.0
 * @E-mail: luxishu@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class ToStringAmountSerializer implements ObjectSerializer {

    public static final com.bluewhale.serializer.ToStringAmountSerializer instance = new com.bluewhale.serializer.ToStringAmountSerializer();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        String strVal = object.toString();
        if (object instanceof Long) {
            strVal = AmountUtils.changeF2Y((Long) object);
        }
        out.writeString(strVal);
    }
}
