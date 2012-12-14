package com.google.android;

public class AndroidInfo {

	private final String email;
	private final String deviceCountry;
	private final String operatorCountry;
	private final String language;
	private final int sdkVersion;
	private final String androidId;

	public AndroidInfo(final String email, final String deviceCountryCode,
			final String operatorCountryCode, final String languageCode,
			final int sdkVersion, final String androidId) {
		this.email = email;
		this.deviceCountry = deviceCountryCode;
		this.operatorCountry = operatorCountryCode;
		this.language = languageCode;
		this.sdkVersion = sdkVersion;
		this.androidId = androidId;
	}

	public String getAndroidId() {
		return androidId;
	}

	public String getDeviceCountry() {
		return deviceCountry;
	}

	public String getEmail() {
		return email;
	}

	public String getLanguage() {
		return language;
	}

	public String getOperatorCountry() {
		return operatorCountry;
	}

	public int getSdkVersion() {
		return sdkVersion;
	}

	public String getSdkVersionString() {
		return sdkVersion + "";
	}

}
