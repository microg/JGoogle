package com.google.android;

import com.google.auth.AuthClient;
import com.google.auth.DataField;
import com.google.auth.DataMapReader;
import com.google.auth.Request;
import com.google.auth.Response;
import com.google.tools.Client;

public class AndroidAuth extends AuthClient {
	public enum AuthType {
		AuthToken, EncryptedPassword, MasterToken, Password;

		public static AuthType fromInt(final int i) {
			switch (i) {
			case 1:
				return AuthToken;
			case 2:
				return EncryptedPassword;
			case 4:
				return Password;
			case 3:
			default:
				return MasterToken;
			}
		}

		public int toInt() {
			switch (this) {
			case AuthToken:
				return 1;
			case EncryptedPassword:
				return 2;
			case MasterToken:
				return 3;
			case Password:
				return 4;
			default:
				return 0;
			}
		}
	}

	private final static String ACCOUNT_TYPE_HOSTED_OR_GOOGLE = "HOSTED_OR_GOOGLE";
	private static final String REQUEST_URL_ANDROID_CLIENT_AUTH = "https://android.clients.google.com/auth";
	private final static String SERVICE_DEFAULT_AC2DM = "ac2dm";

	private final static String SOURCE_ANDROID = "android";

	private static Request createBaseRequest(final AndroidInfo info) {
		final Request request = new Request();
		request.setRequestUrl(REQUEST_URL_ANDROID_CLIENT_AUTH);
		request.putData(DataField.ACCOUNT_TYPE, ACCOUNT_TYPE_HOSTED_OR_GOOGLE);
		request.putData(DataField.SOURCE, SOURCE_ANDROID);
		request.putData(DataField.SERVICE, SERVICE_DEFAULT_AC2DM);
		request.putData(DataField.EMAIL, info.getEmail());
		if (info.getAndroidId() != 0 && info.getAndroidId() != Long.MIN_VALUE) {
			request.putData(DataField.ANDROID_ID, info.getAndroidIdHex());
		}
		request.putData(DataField.DEVICE_COUNTRY, info.getCountry());
		request.putData(DataField.OPERATOR_COUNTRY, info.getCountry());
		request.putData(DataField.LANGUAGE, info.getLanguage());
		request.putData(DataField.SDK_VERSION, info.getSdkVersionString());
		request.setUserAgent("GoogleLoginService/1.3 (" + info.getBuildDevice()
				+ " " + info.getBuildId() + ")");
		return request;
	}

	public static String getAuthToken(final AndroidInfo info,
			final String masterToken, final String service) {
		return getAuthToken(info, masterToken, service, AuthType.MasterToken);
	}

	public static String getAuthToken(final AndroidInfo info,
			final String auth, final String service, final AuthType authType) {
		final Request request = createBaseRequest(info);
		saveAuthInRequest(request, authType, auth);
		request.putData(DataField.SERVICE, service);
		final Response response = AuthClient.sendRequest(request);
		if (Client.DEBUG) {
			System.out.println(response.toString());
		}
		return response.getData(DataField.AUTH_TOKEN);
	}

	public static String getAuthToken(final AndroidInfo info,
			final String masterToken, final String service,
			final String packageName, final String packageSignature,
			final boolean storedPermission) {
		final DataMapReader response = getAuthTokenResponse(info, masterToken,
				service, packageName, packageSignature, storedPermission);
		return response.getData(DataField.AUTH_TOKEN);
	}

	public static DataMapReader getAuthTokenResponse(final AndroidInfo info,
			final String auth, final String service, final String packageName,
			final String packageSignature, final boolean storedPermission) {
		return getAuthTokenResponse(info, auth, service, packageName,
				packageSignature, storedPermission, AuthType.MasterToken);
	}

	public static DataMapReader getAuthTokenResponse(final AndroidInfo info,
			final String auth, final String service, final String packageName,
			final String packageSignature, final boolean storedPermission,
			final AuthType authType) {
		if (Client.DEBUG) {
			System.out.println("getAuthToken: " + service + " | " + hide(auth)
					+ " | " + packageName + " | " + packageSignature + " | "
					+ authType);
		}
		final Request request = createBaseRequest(info);
		saveAuthInRequest(request, authType, auth);
		request.putData(DataField.SERVICE, service);
		request.putData(DataField.PACKAGE_NAME, packageName);
		request.putData(DataField.PACKAGE_SIGNATURE, packageSignature);
		request.putData(DataField.STORED_PERMISSION, !storedPermission ? "1"
				: "0");
		final Response response = AuthClient.sendRequest(request);
		if (Client.DEBUG) {
			System.out.println(response.toString());
		}
		return response;
	}

	public static String getMasterToken(final AndroidInfo info,
			final String auth, final AuthType authType) {
		final DataMapReader response = getMasterTokenResponse(info, auth,
				authType);
		return response.getData(DataField.MASTER_TOKEN);
	}

	public static String getMasterToken(final AndroidInfo info,
			final String password, final AuthType authType, final String service) {
		final DataMapReader response = getMasterTokenResponse(info, password,
				authType, service);
		return response.getData(DataField.MASTER_TOKEN);
	}

	public static DataMapReader getMasterTokenResponse(final AndroidInfo info,
			final String auth, final AuthType authType) {
		return getMasterTokenResponse(info, auth, authType, null);
	}

	public static DataMapReader getMasterTokenResponse(final AndroidInfo info,
			final String auth, final AuthType authType, final String service) {
		final Request request = createBaseRequest(info);
		saveAuthInRequest(request, authType, auth);
		if (service != null) {
			request.putData(DataField.SERVICE, service);
		}
		final Response response = AuthClient.sendRequest(request);
		if (Client.DEBUG) {
			System.out.println(response.toString());
		}
		return response;
	}

	private static String hide(final String s) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (i % 2 == 1) {
				sb.append("*");
			} else {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}

	private static void saveAuthInRequest(final Request request,
			final AuthType type, final String auth) {
		switch (type) {
		case MasterToken:
			request.putData(DataField.MASTER_TOKEN, auth);
			break;
		case Password:
			request.putData(DataField.PASSWORD, auth);
			break;
		case EncryptedPassword:
			request.putData(DataField.ENCRYPTED_PASSWORD, auth);
			break;
		case AuthToken:
			request.putData(DataField.AUTH_TOKEN, auth);
			break;
		}
	}
}
