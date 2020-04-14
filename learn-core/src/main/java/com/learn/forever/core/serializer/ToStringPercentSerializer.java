package com.learn.forever.core.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * 百分比 BigDecimal 0.1 转 10
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
public class ToStringPercentSerializer implements ObjectSerializer {

    public static final com.bluewhale.serializer.ToStringPercentSerializer instance = new com.bluewhale.serializer.ToStringPercentSerializer();

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
            strVal = b.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString();
        }
        if (StringUtils.isBlank(strVal)) {
            strVal = "0";
        }
        out.writeString(strVal);
    }

}
