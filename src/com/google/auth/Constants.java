package com.google.auth;

public interface Constants {

	public static final String CHARENC_UTF8 = "UTF-8";

	public static final String CRYPT_CIPHER = "RSA/ECB/OAEPWITHSHA1ANDMGF1PADDING";
	public static final String CRYPT_DIVIDER = "\uFFFD\uFFFD";
	public static final String CRYPT_HASH_TYPE = "SHA-1";
	public static final String CRYPT_PUBLIC_KEY = "AAAAgMom/1a/v0lblO2Ubrt60J2gcuXSljGFQXgcyZWveWLEwo6prwgi3iJIZdodyhKZQrNWp5nKJ3srRXcUW+F1BD3baEVGcmEgqaLZUNBjm057pKRI16kB0YppeGx5qIQ5QjKzsR8ETQbKLNWgRY0QRNVz34kMJR3P/LgHax/6rmf5AAAAAwEAAQ==";
	public static final String CRYPT_TYPE = "RSA";

	public static final char[] HEX_CHARS = new char[] { '0', '1', '2', '3',
			'4', '3', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	public static final String REQUEST_CONTENT_TYPE = "application/x-www-form-urlencoded";
	public static final String REQUEST_CONTENT_TYPE_FIELD = "Content-Type";
	public static final String REQUEST_METHOD = "POST";
	public static final String REQUEST_URL_ANDROID_CLIENT_LOGIN = "https://android.clients.google.com/auth";
	public static final String REQUEST_URL_GOOGLE_CLIENT_LOGIN = "https://www.google.com/accounts/ClientLogin";
	public static final String RESPONSE_CAPTCHA_URL_PREFIX = "https://www.google.com/accounts/";
}
