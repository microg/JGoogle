package com.google.android;

import com.google.auth.ClientLogin;
import com.google.auth.DataField;
import com.google.auth.DataMapReader;
import com.google.auth.Request;
import com.google.auth.Response;

public class AuthClient {
	public static boolean DEBUG = false;

	private final static String ACCOUNT_TYPE_HOSTED_OR_GOOGLE = "HOSTED_OR_GOOGLE";
	private final static String SOURCE_ANDROID = "android";
	private final static String SERVICE_DEFAULT_AC2DM = "ac2dm";
	private static final String REQUEST_URL_ANDROID_CLIENT_AUTH = "https://android.clients.google.com/auth";

	private static Request createBaseRequest(final AndroidInfo info) {
		final Request request = new Request();
		request.setRequestUrl(REQUEST_URL_ANDROID_CLIENT_AUTH);
		request.putData(DataField.ACCOUNT_TYPE, ACCOUNT_TYPE_HOSTED_OR_GOOGLE);
		request.putData(DataField.SOURCE, SOURCE_ANDROID);
		request.putData(DataField.SERVICE, SERVICE_DEFAULT_AC2DM);
		request.putData(DataField.EMAIL, info.getEmail());
		request.putData(DataField.ANDROID_ID, info.getAndroidId());
		request.putData(DataField.DEVICE_COUNTRY, info.getDeviceCountry());
		request.putData(DataField.OPERATOR_COUNTRY, info.getOperatorCountry());
		request.putData(DataField.LANGUAGE, info.getLanguage());
		request.putData(DataField.SDK_VERSION, info.getSdkVersionString());
		return request;
	}

	public static String getAuthToken(final AndroidInfo dataSet,
			final String masterToken, final String service) {
		final Request request = createBaseRequest(dataSet);
		request.putData(DataField.MASTER_TOKEN, masterToken);
		request.putData(DataField.SERVICE, service);
		final Response response = ClientLogin.sendRequest(request);
		if (DEBUG) {
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
			final String masterToken, final String service,
			final String packageName, final String packageSignature,
			final boolean storedPermission) {
		final Request request = createBaseRequest(info);
		request.putData(DataField.MASTER_TOKEN, masterToken);
		request.putData(DataField.SERVICE, service);
		request.putData(DataField.PACKAGE_NAME, packageName);
		request.putData(DataField.PACKAGE_SIGNATURE, packageSignature);
		request.putData(DataField.STORED_PERMISSION, !storedPermission ? "1"
				: "0");
		final Response response = ClientLogin.sendRequest(request);
		if (DEBUG) {
			System.out.println(response.toString());
		}
		return response;
	}

	public static String getMasterToken(final AndroidInfo info,
			final String password, final boolean encrypted) {
		if (password == null || password.isEmpty() || info == null) {
			return null;
		}
		final DataMapReader response = getMasterTokenResponse(info, password,
				encrypted);
		return response.getData(DataField.MASTER_TOKEN);
	}

	public static DataMapReader getMasterTokenResponse(final AndroidInfo info,
			final String password, final boolean encrypted) {
		if (password == null || password.isEmpty() || info == null) {
			return null;
		}
		final Request request = createBaseRequest(info);
		if (!encrypted) {
			request.putData(DataField.PASSWORD, password);
		} else {
			request.putData(DataField.ENCRYPTED_PASSWORD, password);
		}
		final Response response = ClientLogin.sendRequest(request);
		if (DEBUG) {
			System.out.println(response.toString());
		}
		return response;
	}
}
