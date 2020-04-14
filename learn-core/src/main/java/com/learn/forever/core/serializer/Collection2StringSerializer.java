package com.learn.forever.core.serializer;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import lombok.Data;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * 集合类型转换为','逗号分割的字符串
 *
 * @projectName: bw-common
 * @className: Collection2StringSerializer
 * @author: 陆锡书
 * @date: 2019-06-19 09:44
 * @version: 1.0
 * @E-mail: luxishu@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Data
public class Collection2StringSerializer implements ObjectSerializer {

    private static final String SPILT = ",";
    private static final Collection2StringSerializer instance = new Collection2StringSerializer();

    /**
     * specified type.
     *
     * @param serializer
     * @param object     src the object that needs to be converted to Json.
     * @param fieldName  parent object field name
     * @param fieldType  parent object field type
     * @param features   parent object field serializer features
     * @throws IOException
     */
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object instanceof Collection){
            Collection<?> list = (Collection<?>) object;
            StringBuilder sb = new StringBuilder();
            list.stream().forEach(s->sb.append(s).append(SPILT));
            sb.deleteCharAt(sb.lastIndexOf(SPILT));
            out.writeString(sb.toString());
        }else {
            out.writeString(object.toString());
        }
    }

}
