package com.learn.forever.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @projectName：bw-common
 * @className：StringZipUtil
 * @author : huangziwang
 * @date 2019年6月3日 下午8:19:42
 * @version 1.0
 * @E-mail:huangziwang@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return 字符串压缩--压缩率在30%，原字符串1836长度，压缩后长度712
 */
public class StringZipUtil {

    /**
     * @description:压缩--压缩率在30%，原字符串1836长度，压缩后长度712
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年6月3日 下午8:20:11
     * @param str
     * @return
     */
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = null;
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            gzip.close();
            return out.toString("ISO-8859-1");
        } catch (IOException e) {

        }
        return str;
    }

    /**
     * @description:解压缩
     * @author: huangziwang
     * @version: v0.1
     * @time: 2019年6月3日 下午8:20:20
     * @param str
     * @return
     */
    public static String uncompress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(
                    str.getBytes("ISO-8859-1"));
            GZIPInputStream gunzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
            return out.toString();
        } catch (IOException e) {

        }
        return str;
    }

    public static void main(String[] args) throws IOException {
        String compress = "{\"insert\":true,\"itemChild\":{\"brief\":\"商品简介1558677652227\",\"carouselImageIds\":\"1123107798989668399,1123053955216048193\",\"carouselVideoIds\":\"1123107798989668399,1123053955216048193\",\"deno\":\"个\",\"detailsImageIds\":\"1123107798989668399,1123053955216048193\",\"fictitiousSalesMultiple\":1,\"listImageId\":1123107798989668399,\"marketImageId\":1123107798989668399,\"mobileDesc\":\"test\",\"propsSerialize\":\"[{\\\"id\\\":6567,\\\"name\\\":\\\"颜色\\\",\\\"value\\\":\\\"绿色\\\",\\\"valueId\\\":92154,\\\"aliasName\\\":\\\"自定义颜色\\\",\\\"aliasValue\\\":\\\"自定义绿色\\\"},{\\\"id\\\":6568,\\\"name\\\":\\\"尺码\\\",\\\"value\\\":\\\"XXS\\\",\\\"valueId\\\":92166,\\\"aliasName\\\":\\\"自定义尺码\\\",\\\"aliasValue\\\":\\\"自定义尺寸\\\"},{\\\"id\\\":6569,\\\"name\\\":\\\"12*4\\\",\\\"value\\\":\\\"规格值A\\\",\\\"valueId\\\":92175,\\\"aliasName\\\":\\\"自定义规格\\\",\\\"aliasValue\\\":\\\"自定义规格值\\\"}]\",\"skuSerialize\":\"[{\\\"id\\\":\\\"6567\\\",\\\"name\\\":\\\"颜色\\\",\\\"subList\\\":[{\\\"id\\\":\\\"92154\\\",\\\"name\\\":\\\"绿色\\\"}]},{\\\"id\\\":\\\"6568\\\",\\\"name\\\":\\\"尺码\\\",\\\"subList\\\":[{\\\"id\\\":\\\"92166\\\",\\\"name\\\":\\\"XXS\\\"}]},{\\\"id\\\":\\\"6569\\\",\\\"name\\\":\\\"12*4\\\",\\\"subList\\\":[{\\\"id\\\":\\\"92175\\\",\\\"name\\\":\\\"规格值A\\\"}]}]\"},\"itemMain\":{\"brandId\":1,\"categoryId\":9,\"crossBorderFlag\":0,\"freightTemplateId\":1,\"keywords\":\"测试商品关键字1558677652227\",\"name\":\"测试商品1558677652227\",\"packageFlag\":0,\"shopId\":1,\"stockMode\":0,\"subtitle\":\"测试商品副标题1558677652227\",\"thirdType\":1},\"itemNoDistributionList\":[{\"countryRegionId\":1}],\"itemSkuList\":[{\"barCode\":\"10001\",\"costPrice\":10000,\"imageId\":1123107798989668399,\"marketPrice\":20000,\"old\":false,\"rebateRatio\":0.1,\"shopCode\":\"20001\",\"specSerialize\":\"[{\\\"id\\\":6567,\\\"name\\\":\\\"颜色\\\",\\\"value\\\":\\\"绿色\\\",\\\"valueId\\\":92154,\\\"aliasName\\\":\\\"自定义颜色\\\",\\\"aliasValue\\\":\\\"自定义绿色\\\"},{\\\"id\\\":6568,\\\"name\\\":\\\"尺码\\\",\\\"value\\\":\\\"XXS\\\",\\\"valueId\\\":92166,\\\"aliasName\\\":\\\"自定义尺码\\\",\\\"aliasValue\\\":\\\"自定义尺寸\\\"},{\\\"id\\\":6569,\\\"name\\\":\\\"12*4\\\",\\\"value\\\":\\\"规格值A\\\",\\\"valueId\\\":92175,\\\"aliasName\\\":\\\"自定义规格\\\",\\\"aliasValue\\\":\\\"自定义规格值\\\"}]\",\"stock\":1000,\"weight\":1}]}";
        long s = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String uncompress1 = compress(compress);
            String compress1 = uncompress(uncompress1);
//            System.out.println("---" + uncompress1);
//            System.out.println("---" + compress1);
//            System.out.println(uncompress1.length());
//            System.out.println(compress1.length());
        }
        System.out.println(System.currentTimeMillis() - s);
    }
}
