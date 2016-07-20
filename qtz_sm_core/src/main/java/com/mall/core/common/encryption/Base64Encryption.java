package com.mall.core.common.encryption;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;


public class Base64Encryption {
	
	/**
	 *  加解密的密钥
	 */
	private static final String CRYPTO_KEY = "26eb83f073fe4b1aa6e15d636d5b38df";

	/**
	 * 加密算法实现
	 */
	public static String encode(String enc) {
		if (StringUtils.isEmpty(enc)){
			return enc;
		}
		synchronized (new Object()) {
			byte[] h = Base64.encodeBase64(CRYPTO_KEY.getBytes());
			byte[] e = enc.getBytes();
			int a = 0, r = 0, q = 0, w = 0 ;
			int l = e.length, o = h.length;
			byte[] s = new byte[o + l];
			for (int i = 0; i < s.length; i++) {
				if (w < o && i % 2 == 0) {
					s[i] = h[w++];
				} else if (q < l) {
					s[i] = e[q++];
				} else {
					s[i] = h[w++];
				}
			}
			for (int i = 0; i < s.length; i++) {
				if (o < l) {
					if (r < o) {
						if (i % 2 == 0)
							r++;
						else
							a++;
					} else {
						if (a < l)
							a++;
					}
				} else {
					if (a < l) {
						if (i % 2 == 0)
							r++;
						else
							a++;
					} else {
						if (r < o) {
						}
					}
				}
			}

			for (int i = 0; i < s.length; i++) {
				if (i < h.length)
					s[i] += h[i];
				for (byte b : h) {
					s[i] = (byte) (s[i] ^ b);
				}
			}
			return Base64.encodeBase64String(s);
		}
	}

	/**
	 * 解密算法实现
	 * 
	 * @param dec
	 * @return
	 */
	public static String decode(String dec) {
		if (StringUtils.isEmpty(dec.trim()))
			return dec;
		synchronized (new Object()) {
			byte[] k = Base64.encodeBase64(CRYPTO_KEY.getBytes());
			byte[] s = Base64.decodeBase64(dec);
			byte[] m = new byte[s.length - k.length];
			for (int i = 0; i < s.length; i++) {
				for (byte b : k) {
					s[i] = (byte) (s[i] ^ b);
				}
				if (i < k.length)
					s[i] -= k[i];
			}

			int l = k.length, o = m.length;
			int z = 0, c = 0;
			for (int i = 0; i < s.length; i++) {
				if (z < o & i % 2 != 0) {
					m[z++] = s[i];
				} else if (c < l) {
					c++;
				} else if (z < o) {
					m[z++] = s[i];
				}
			}
			return new String(m);
		}
	}
	public static void main(String[] args) {
		System.out.println(decode("lmDSsSwkwvKR3Nr2UDAPvRA6yAyhoVxGhCXnX0d10LHJjxI5IQJu+jZZILbSMyVhHVXQZ2qIYPZYgH+qyHjtdnrHtmcWys+y+CCM0jmox5c="));
	}
}
