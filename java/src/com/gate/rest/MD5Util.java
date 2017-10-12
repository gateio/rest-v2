package com.gate.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MD5Util {

	/**
	 * 鐢熸垚绛惧悕缁撴灉(鏂扮増鏈娇鐢�)
	 * 
	 * @param sArray
	 *            瑕佺鍚嶇殑鏁扮粍
	 * @return 绛惧悕缁撴灉瀛楃涓�
	 */
	public static String buildMysignV1(Map<String, String> sArray,
			String secretKey) {
		String mysign = "";
		try {
			String prestr = createLinkString(sArray); // 鎶婃暟缁勬墍鏈夊厓绱狅紝鎸夌収鈥滃弬鏁�=鍙傛暟鍊尖�濈殑妯″紡鐢ㄢ��&鈥濆瓧绗︽嫾鎺ユ垚瀛楃涓�
			prestr = prestr + "&secret_key=" + secretKey; // 鎶婃嫾鎺ュ悗鐨勫瓧绗︿覆鍐嶄笌瀹夊叏鏍￠獙鐮佽繛鎺ヨ捣鏉�
			mysign = getMD5String(prestr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mysign;
	}
	
	/**
	 * 鐢熸垚绛惧悕缁撴灉锛堣�佺増鏈娇鐢級
	 * 
	 * @param sArray
	 *            瑕佺鍚嶇殑鏁扮粍
	 * @return 绛惧悕缁撴灉瀛楃涓�
	 */
	public static String buildMysign(Map<String, String> sArray,
			String secretKey) {
		String mysign = "";
		try {
			String prestr = createLinkString(sArray); // 鎶婃暟缁勬墍鏈夊厓绱狅紝鎸夌収鈥滃弬鏁�=鍙傛暟鍊尖�濈殑妯″紡鐢ㄢ��&鈥濆瓧绗︽嫾鎺ユ垚瀛楃涓�
			prestr = prestr + secretKey; // 鎶婃嫾鎺ュ悗鐨勫瓧绗︿覆鍐嶄笌瀹夊叏鏍￠獙鐮佺洿鎺ヨ繛鎺ヨ捣鏉�
			mysign = getMD5String(prestr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mysign;
	}

	/**
	 * 鎶婃暟缁勬墍鏈夊厓绱犳帓搴忥紝骞舵寜鐓р�滃弬鏁�=鍙傛暟鍊尖�濈殑妯″紡鐢ㄢ��&鈥濆瓧绗︽嫾鎺ユ垚瀛楃涓�
	 * 
	 * @param params
	 *            闇�瑕佹帓搴忓苟鍙備笌瀛楃鎷兼帴鐨勫弬鏁扮粍
	 * @return 鎷兼帴鍚庡瓧绗︿覆
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {// 鎷兼帴鏃讹紝涓嶅寘鎷渶鍚庝竴涓�&瀛楃
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	/**
	 * 鐢熸垚32浣嶅ぇ鍐橫D5鍊�
	 */
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String getMD5String(String str) {
		try {
			if (str == null || str.trim().length() == 0) {
				return "";
			}
			byte[] bytes = str.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			bytes = messageDigest.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4] + ""
						+ HEX_DIGITS[bytes[i] & 0xf]);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
