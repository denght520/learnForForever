package com.learn.forever.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @projectName：bw-common
* @className：IpUtil
* @author : yangyingyong
* @date Apr 29, 2019 8:48:20 PM
* @version 1.0
* @E-mail:yangyingyong@oxyzgroup.com
* @Copyright: 版权所有 (C) 2019 蓝鲸淘.
* @return  ip工具类
*/
public final class IpUtil {

    private static Logger LOG = LoggerFactory.getLogger(IpUtil.class);

	/**
	 * 获取本机的对外的ip地址(考虑多网口情况)
	 *
	 * @return
	 */
	public static List<String> getLocalHostIp4List() {
		List<String> list = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
			for (; n.hasMoreElements(); ) {
				NetworkInterface e = n.nextElement();
				Enumeration<InetAddress> a = e.getInetAddresses();
				for (; a.hasMoreElements(); ) {
					InetAddress addr = a.nextElement();
					if (addr.isLoopbackAddress() || !(addr instanceof Inet4Address)) {
						continue;
					}
					list.add(addr.getHostAddress());
				}
			}
		} catch (Exception e) {
		    LOG.error("getLocalHostIp4List ",e);
		}
		return list;
	}

	private static Pattern IP_PATTERN = Pattern.compile("(^(\\d{1,3}|\\*\\.\\d{1,3}|\\*\\.\\d{1,3}|\\*\\.\\d{1,3}|\\*))");

    public static Boolean isIp(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        Matcher matcher = IP_PATTERN.matcher(str);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    private static final String UNKNOWN = "unknown";

    /**
     * 获取客户端ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr1(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0,ip.indexOf(","));
        }
        return ip;
    }

    /**
     * 获取客户端ip
     * @author 旺仔
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String result = request.getHeader("X-Real-IP");
        if (result == null || result.length() == 0
            || "unknown".equalsIgnoreCase(result)) {
          result = request.getHeader("x-forwarded-for");
          // 防止出现多次代理后的ip
          if (result != null && result.length() != 0) {
            String[] tmp = result.split(",", -1);
            if (tmp != null && tmp.length >0)
              result = tmp[0];
          }
        }
        if (result == null || result.length() == 0
            || "unknown".equalsIgnoreCase(result)) {
          result = request.getHeader("Proxy-Client-IP");
        }
        if (result == null || result.length() == 0
            || "unknown".equalsIgnoreCase(result)) {
          result = request.getHeader("WL-Proxy-Client-IP");
        }
        if (result == null || result.length() == 0
            || "unknown".equalsIgnoreCase(result)) {
          result = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (!"".equals(result) && result != null) {
          if (result.indexOf(".") != -1) { // 没有"."肯定是非IPv4格式
            Pattern pat = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
            Matcher mat = pat.matcher(result);
            result = null;
            while (mat.find()) {
              result = mat.group(0);
              break;
            }
          } else
            result = null;
        }
        if ("".equals(result) || result == null)
          result = request.getRemoteAddr();
        return result;
      }


}
