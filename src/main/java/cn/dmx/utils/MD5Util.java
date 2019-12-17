package cn.dmx.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.security.MessageDigest;

/**
 * @Author: 张帅轲
 * @Date: 2019/6/25 11:21
 * @Description:   MD5加密
 */
public class MD5Util {
	
	
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 返回大写MD5
	 *
	 * @param origin
	 *            要转化的md5字符串
	 * @param charsetname
	 *            转化过程中使用的字符集
	 * @return 返回大写MD5
	 */
	private static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString.toUpperCase();
	}

	/***
	 * 调用这个函数进行md5非对称加密
	 * 
	 * @param origin
	 *            要加密的string字符串
	 * @return 调用MD5Encode，返回大写MD5字符串
	 */
	public static String MD5EncodeUtf8(String origin) {
		// 加盐操作，防止通过MD5字典进行解密
		origin = origin + "geelysdafaqj23ou89ZXcj@#$@#$#@KJdjklj;D../dSF.,";
		return MD5Encode(origin, "utf-8");
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };


	/**
	 * 	用户注册时加密用户的密码
	 * @param username 用户名
	 * @param password 密码
	 * @return 返回盐值与加密后的密码 第一个是盐值  第二个 是加密后的密码
	 */
	public static String[] encryptPassword(String username,String password)
	{
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成盐值
		salt = username+salt;
		int hashIterations = 2;//加密的次数
		String hashAlgorithmName = "md5";//加密方式
		Object simpleHash = new SimpleHash(hashAlgorithmName, password,
				salt, hashIterations);
		String[] strings = new String[]{salt, simpleHash.toString()};
		return strings;
	}


	public static void main(String[] args) {
		String[] shuaikes = encryptPassword("zhoutian", "111111");
		System.out.println(shuaikes[0]);
		System.out.println(shuaikes[1]);
	}


}

