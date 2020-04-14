package com.learn.forever.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

/**
* @projectName：bw-common
* @className：HTTPUtil
* @author : huangziwang
* @date 2019年6月6日 下午8:30:11
* @version 1.0
* @E-mail:huangziwang@oxyzgroup.com
* @Copyright: 版权所有 (C) 2019 蓝鲸淘.
* @return
*/
public class HTTPUtil {
    private static Logger LOG = LoggerFactory.getLogger(HTTPUtil.class);


	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url	发送请求的URL
	 * @param param	请求参数，请求参数是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param,Map<String, String> headMap) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setConnectTimeout(10000);
			if (headMap != null ) {
				Iterator<Entry<String, String>> it = headMap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, String> entry = it.next();
					String key = entry.getKey();
					String value = entry.getValue();
					connection.setRequestProperty(key, value);
				}
			}
			// 建立实际的连接
			connection.connect();
//			// 获取所有响应头字段
//			Map<String, List<String>> map = connection.getHeaderFields();
//			// 遍历所有的响应头字段
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println(e);
			LOG.error("发送GET请求出现异常！",e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url	发送请求的 URL
	 * @param param	请求参数
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param,Map<String, String> headMap) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if (headMap != null) {
				Iterator<Entry<String, String>> it = headMap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, String> entry = it.next();
					String key = entry.getKey();
					String value = entry.getValue();
					conn.setRequestProperty(key, value);
				}
			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			LOG.error("发送 POST 请求出现异常！",e);
		}
		//使用finally块来关闭输出流、输入流
		finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}

	   /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static byte[] sendPostToByte(String url, String param,Map<String, String> headMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            if (headMap != null) {
                Iterator<Entry<String, String>> it = headMap.entrySet().iterator();
                while (it.hasNext()) {
                    Entry<String, String> entry = it.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    conn.setRequestProperty(key, value);
                }
            }
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();

         // 开始获取数据
//           BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
//           OutputStream os = new FileOutputStream(new File("D:/test/" + "1111" + ".png"));
//           int len;
//           byte[] arr = new byte[1024];
//           while ((len = bis.read(arr)) != -1) {
//               os.write(arr, 0, len);
//               os.flush();
//           }
//           os.close();
//           bis.close();
            byte[] buffer = readInputStream(conn.getInputStream());
            return buffer;
        } catch (Exception e) {
            LOG.error("发送 POST 请求出现异常！",e);
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return null;
    }

	// 支付宝
	public static void main(String[] args) {
		String json = "{\"gmt_create\":\"2017-10-18 11:26:39\",\"buyer_email\":\"1783729872@qq.com\",\"notify_time\":\"2017-10-18 11:26:40\",\"gmt_payment\":\"2017-10-18 11:26:40\",\"seller_email\":\"shihaichina@163.com\",\"quantity\":\"1\",\"subject\":\"订单号:20171018112622643065\",\"use_coupon\":\"N\",\"sign\":\"Z2z80UTQbwG+Fd9gRfaYuOPguzZtOadcQDOo46auuBToqsWgJaPpvlV38dWH5ebaX29toMMhcH1IqygEelsuAY9GiGuLYy13vP6EHEnEA+/zG+/WPS/LOMNEsmTC3EQ1EB7JqadDlPcjCpHGgPC75XvPb4GjaSDF5F3OzRRDIzM=\",\"discount\":\"0.00\",\"body\":\"订单号:20171018112622643065\",\"buyer_id\":\"2088212679880702\",\"notify_id\":\"f1a6415a0feaef74c21cdf0305f3639lei\",\"notify_type\":\"trade_status_sync\",\"payment_type\":\"1\",\"out_trade_no\":\"20171018112622643065\",\"price\":\"188.00\",\"trade_status\":\"TRADE_SUCCESS\",\"total_fee\":\"188.00\",\"trade_no\":\"2017101821001004700224559222\",\"sign_type\":\"RSA\",\"seller_id\":\"2088121118263069\",\"is_total_fee_adjust\":\"N\"}";
		JSONObject jsonO = JSON.parseObject(json);
		System.out.println(jsonO.toJSONString());
		Map<String, Object> jsonObject = (Map<String, Object>)jsonO;

		String param = createLinkString(jsonObject);
		System.out.println(param);
		Map<String, String> headMap = new HashMap<String, String>();
		String result = sendGet("http://pay3.ggzlive.com/shop/pay/aliPayNotifyUrl/v1.htm?", param, headMap);
		System.out.println(result);
	}
	   /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream)
            throws IOException {
        byte[] buffer = new byte[1024 * 2];
        int len = 0;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1)
                bos.write(buffer, 0, len);
        } catch (Exception e) {

        } finally {
            if (bos != null) {
                bos.close();
            }
        }
        return bos.toByteArray();
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, Object> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key).toString();
            if ("signature".equals(key)) {
            	try {
					value = URLEncoder.encode(value, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
            }
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                try {
					prestr = prestr + key + "=" + URLEncoder.encode(value, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                try {
					prestr = prestr + key + "=" + URLEncoder.encode(value, "utf-8") + "&";
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }

        return prestr;
    }
}
