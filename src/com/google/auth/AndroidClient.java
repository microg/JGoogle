package com.google.auth;

public class AndroidClient implements Constants {
	public static boolean DEBUG = false;

	private static Request createRequest(final AndroidDataSet dataSet) {
		final Request request = new Request();
		request.setRequestUrl(REQUEST_URL_ANDROID_CLIENT_LOGIN);
		request.putData(DataField.ACCOUNT_TYPE, "HOSTED_OR_GOOGLE");
		request.putData(DataField.SOURCE, "android");
		request.putData(DataField.SERVICE, "ac2dm");
		request.putData(dataSet);
		return request;
	}

	public static String getAuthToken(final AndroidDataSet dataSet,
			final String masterToken, final String service) {
		final Request request = createRequest(dataSet);
		request.putData(DataField.MASTER_TOKEN, masterToken);
		request.putData(DataField.SERVICE, service);
		final Response response = ClientLogin.sendRequest(request);
		if (DEBUG) {
			System.out.println(response.toString());
		}
		return response.getData(DataField.AUTH_TOKEN);
	}

	public static String getAuthToken(final AndroidDataSet dataSet,
			final String masterToken, final String service,
			final String packageName, final String packageSignature,
			final boolean storedPermission) {
		final Request request = createRequest(dataSet);
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
		return response.getData(DataField.AUTH_TOKEN);
	}

	public static String getMasterToken(final AndroidDataSet dataSet,
			final String password) {
		return getMasterToken(dataSet, password, false);
	}

	public static String getMasterToken(final AndroidDataSet dataSet,
			final String password, final boolean encrypted) {
		if (password == null || password.isEmpty() || dataSet == null) {
			return null;
		}
		final Request request = createRequest(dataSet);
		if (!encrypted) {
			request.putData(DataField.PASSWORD, password);
		} else {
			request.putData(DataField.ENCRYPTED_PASSWORD, password);
		}
		final Response response = ClientLogin.sendRequest(request);
		if (DEBUG) {
			System.out.println(response.toString());
		}
		return response.getData(DataField.MASTER_TOKEN);
	}
}
