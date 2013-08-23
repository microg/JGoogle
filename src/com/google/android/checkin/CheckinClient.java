package com.google.android.checkin;

import com.google.android.AndroidContext;
import com.google.tools.Client;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.Date;

public class CheckinClient extends Client {

	protected static final String REQUEST_CONTENT_TYPE = "application/x-protobuffer";
	protected static final String REQUEST_CONTENT_TYPE_FIELD = "Content-Type";
	private static final String checkInUrl = "https://android.clients.google.com/checkin";
	private static final int version = 3;

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

	public CheckInProto.CheckinResponse sendRequest(final CheckInProto.CheckinRequest request, final String url) {

		byte[] bytes = null;
		try {
			final HttpPost post = new HttpPost(url);
			post.setHeader(REQUEST_CONTENT_TYPE_FIELD, REQUEST_CONTENT_TYPE);
			post.setEntity(new ByteArrayEntity(request.toByteArray()));
			post.setHeader("User-Agent", "Android-Checkin/2.0 (mako JDQ39); gzip");
			final DefaultHttpClient client = new DefaultHttpClient();
			final HttpResponse response = client.execute(post);
			final InputStream is = response.getEntity().getContent();
			bytes = readStreamToEnd(is);
			return CheckInProto.CheckinResponse.parseFrom(bytes);
		} catch (final Exception e) {
			if (DEBUG) {
				e.printStackTrace(System.err);
				if (bytes != null) {
					System.err.println(new String(bytes));
				}
			}
			return null;
		}

	}

	public CheckinResponse checkin(final AndroidContext info, final String authToken) {
		final long now = new Date().getTime();


		final CheckInProto.CheckinRequest.Checkin.Build build = new CheckInProto.CheckinRequest.Checkin.Build();
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

		final CheckInProto.CheckinRequest.Checkin checkin = new CheckInProto.CheckinRequest.Checkin();
		checkin.setBuild(build);
		checkin.setLastCheckinMs(0);
		checkin.addEvent(new CheckInProto.CheckinRequest.Checkin.Event().setTag("event_log_start").setTimeMs(now));
		checkin.setCellOperator(info.getCellOperator());
		checkin.setSimOperator(info.getSimOperator());
		checkin.setRoaming(info.getRoaming());
		checkin.setUserNumber(info.getUserNumber());

		final CheckInProto.CheckinRequest.DeviceConfig deviceConfig = new CheckInProto.CheckinRequest.DeviceConfig();
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

		final CheckInProto.CheckinRequest request = new CheckInProto.CheckinRequest();
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

		final CheckInProto.CheckinResponse response = sendRequest(request, checkInUrl);

		return new CheckinResponse(response);
	}

}
