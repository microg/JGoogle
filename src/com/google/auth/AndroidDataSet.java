package com.google.auth;

public class AndroidDataSet extends DataMapReader implements Constants {

	private static DataMap createDataMap(final String email,
			final String deviceCountryCode, final String operatorCountryCode,
			final String languageCode, final String sdkVersionCode,
			final String androidId) {
		final DataMap dataMap = new DataMap();
		dataMap.put(DataField.EMAIL, email);
		dataMap.put(DataField.DEVICE_COUNTRY, deviceCountryCode);
		dataMap.put(DataField.OPERATOR_COUNTRY, operatorCountryCode);
		dataMap.put(DataField.LANGUAGE, languageCode);
		dataMap.put(DataField.SDK_VERSION, sdkVersionCode);
		dataMap.put(DataField.ANDROID_ID, androidId);
		return dataMap;
	}

	public AndroidDataSet(final String email, final String deviceCountryCode,
			final String operatorCountryCode, final String languageCode,
			final String sdkVersionCode, final String androidId) {
		super(createDataMap(email, deviceCountryCode, operatorCountryCode,
				languageCode, sdkVersionCode, androidId));
	}

}
