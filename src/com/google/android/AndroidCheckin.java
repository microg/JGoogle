package com.google.android;

import com.google.checkin.CheckinClient;
import com.google.checkin.proto.CheckIn;

import java.util.*;

public class AndroidCheckin {

	private static final String checkInUrl = "https://android.clients.google.com/checkin";
	private static final int version = 3;

	private AndroidCheckin() {
	}

	private static Map<String, String> parseSettings(List<CheckIn.CheckinResponse.GservicesSetting> settings) {
		Map<String, String> map = new HashMap<String, String>();
		for (CheckIn.CheckinResponse.GservicesSetting setting : settings) {
			map.put(setting.getName().toStringUtf8(), setting.getValue().toStringUtf8());
		}
		return Collections.unmodifiableMap(map);
	}

	private static int inRangeOr(int val, int min, int max, int def) {
		return (val >= min && val <= max) ? val : def;
	}

	private static String orString(String s, String e) {
		return isKnown(s) ? s : e;
	}

	private static String orEmpty(String s) {
		return orString(s, "");
	}

	private static boolean isKnown(String s) {
		return (notEmpty(s) && !s.equalsIgnoreCase("unknown"));
	}

	private static boolean notEmpty(String s) {
		return (s != null && !s.isEmpty());
	}

	public static CheckinResponse checkin(final AndroidInfo info, final String authToken) {
		final long now = new Date().getTime();


		final CheckIn.CheckinRequest.Checkin.Build build = new CheckIn.CheckinRequest.Checkin.Build();
		if (notEmpty(info.getBuildFingerprint())) {
			build.setFingerprint(info.getBuildFingerprint());
		}
		if (notEmpty(info.getBuildProduct())) {
			build.setProduct(info.getBuildProduct());
		}
		if (notEmpty(info.getBuildBrand())) {
			build.setBrand(info.getBuildBrand());
		}
		if (notEmpty(info.getBuildRadio())) {
			build.setRadio(info.getBuildRadio());
		}
		if (notEmpty(info.getBuildBootloader())) {
			build.setBootloader(info.getBuildBootloader());
		}
		if (notEmpty(info.getBuildHardware())) {
			build.setHardware(info.getBuildHardware());
		}
		if (notEmpty(info.getBuildManufacturer())) {
			build.setManufacturer(info.getBuildManufacturer());
		}
		if (notEmpty(info.getBuildModel())) {
			build.setModel(info.getBuildModel());
		}
		if (notEmpty(info.getBuildDevice())) {
			build.setDevice(info.getBuildDevice());
		}
		build.setTime(info.getBuildTime());
		build.setPackageVersionCode(info.getBuildSdkVersion());
		build.setSdkVersion(info.getBuildSdkVersion());
		build.setClientId(info.getBuildClientId());
		build.setOtaInstalled(info.getBuildOtaInstalled());

		final CheckIn.CheckinRequest.Checkin checkin = new CheckIn.CheckinRequest.Checkin();
		checkin.setBuild(build);
		checkin.setLastCheckinMs(0);
		checkin.addEvent(new CheckIn.CheckinRequest.Checkin.Event().setTag("event_log_start").setTimeMs(now));
		checkin.setCellOperator(info.getCellOperator());
		checkin.setSimOperator(info.getSimOperator());
		checkin.setRoaming(info.getRoaming());
		checkin.setUserNumber(info.getUserNumber());

		final CheckIn.CheckinRequest.DeviceConfig deviceConfig =
				new CheckIn.CheckinRequest.DeviceConfig();
		deviceConfig.setTouchScreen(inRangeOr(info.getDeviceTouchScreen(), 0, 3, 0));
		deviceConfig.setKeyboardType(inRangeOr(info.getDeviceKeyboardType(), 0, 3, 0));
		deviceConfig.setNavigation(inRangeOr(info.getDeviceNavigation(), 0, 4, 0));
		deviceConfig.setWidthPixels(info.getDeviceWidthPixels());
		deviceConfig.setHeightPixels(info.getDeviceHeightPixels());
		deviceConfig.setScreenLayout(inRangeOr(info.getDeviceScreenLayout(), 0, 4, 0));
		deviceConfig.setHasHardKeyboard(info.getDeviceHasHardKeyboard());
		deviceConfig.setDensityDpi(info.getDeviceDensityDpi());
		deviceConfig.setGlEsVersion(info.getDeviceGlEsVersion());
		for (String s : info.getDeviceGlExtensions()) {
			deviceConfig.addGlExtension(s);
		}
		deviceConfig.addNativePlatform(info.getBuildCpuAbi());
		if (isKnown(info.getBuildCpuAbi2())) {
			deviceConfig.addNativePlatform(info.getBuildCpuAbi2());
		}
		for (String s : info.getDeviceSharedLibraries()) {
			deviceConfig.addSharedLibrary(s);
		}
		for (String s : info.getDeviceFeatures()) {
			deviceConfig.addAvailableFeature(s);
		}
		for (String s : info.getDeviceLocales()) {
			deviceConfig.addLocale(s);
		}

		deviceConfig.setHasFiveWayNavigation(info.getDeviceHasFiveWayNavigation());

		final CheckIn.CheckinRequest request = new CheckIn.CheckinRequest();
		request.setDigest(orEmpty(info.getDigest()));
		request.setImei(info.getImei());
		request.setLocale(info.getLocaleString());
		request.setTimeZone(info.getTimeZoneString());
		request.setCheckin(checkin);
		request.setDeviceConfiguration(deviceConfig);
		if (info.getAndroidId() == 0 || info.getSecurityToken() != 0) {
			request.setVersion(version);
		}
		request.addOtaCert(orString(info.getOtaCert(), "--no-output--"));
		request.setLoggingId(info.getLoggingId());
		if (isKnown(info.getMeid())) {
			request.setMeid(info.getMeid());
		}
		if (isKnown(info.getEsn())) {
			request.setEsn(info.getEsn());
		}
		if (isKnown(info.getBuildSerial())) {
			request.setSerial(info.getBuildSerial());
		}
		if (isKnown(info.getMacAddress()) && isKnown(info.getMacAddressType())) {
			request.addMacAddressType(info.getMacAddressType());
			request.addMacAddress(info.getMacAddress());
		}
		request.setFragment(0);

		if (info.getSecurityToken() != 0 && info.getSecurityToken() != Long.MIN_VALUE) {
			request.setSecurityToken(info.getSecurityToken());
		}
		if (info.getAndroidId() != 0 && info.getAndroidId() != Long.MIN_VALUE) {
			request.setAndroidId(info.getAndroidId());
		}
		if (info.getEmail() != null && !info.getEmail().isEmpty()) {
			request.addAccountCookie("[" + info.getEmail() + "]");
			if (authToken != null && !authToken.isEmpty()) {
				request.addAccountCookie(authToken);
			}
		}

		final CheckIn.CheckinResponse response = CheckinClient.sendRequest(request, checkInUrl);

		return new AndroidCheckin().new CheckinResponse(response);
	}

	public class CheckinResponse {
		private final long androidId;
		private final long securityToken;
		private final Map<String, String> settings;
		private final boolean marketEnabled;
		private final String digest;

		public CheckinResponse(CheckIn.CheckinResponse response) {
			this(response.getAndroidId(), response.getSecurityToken(), parseSettings(response.getSettingList()),
				 response.getMarketOk(), response.getDigest());
		}

		public CheckinResponse(final long androidId, final long securityToken, final Map<String, String> settings,
							   final boolean marketEnabled, final String digest) {
			this.androidId = androidId;
			this.securityToken = securityToken;
			this.settings = settings;
			this.marketEnabled = marketEnabled;
			this.digest = digest;
		}

		public String getDigest() {
			return digest;
		}

		public Map<String, String> getSettings() {
			return settings;
		}

		public boolean isMarketEnabled() {
			return marketEnabled;
		}

		public String getAndroidIdHex() {
			return Long.toHexString(androidId);
		}

		public long getAndroidId() {
			return androidId;
		}

		public long getSecurityToken() {
			return securityToken;
		}
	}
}
