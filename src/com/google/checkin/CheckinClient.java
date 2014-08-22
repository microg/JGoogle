package com.google.checkin;

import com.google.android.AndroidContext;
import com.google.tools.Client;
import com.squareup.wire.Wire;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

public class CheckinClient extends Client {

	protected static final String REQUEST_CONTENT_TYPE = "application/x-protobuffer";
	protected static final String REQUEST_CONTENT_TYPE_FIELD = "Content-Type";
	private static final String checkInUrl = "https://android.clients.google.com/checkin";
	private static final int version = 3;
	private final Wire wire = new Wire();

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

	public Response sendRequest(final Request request, final String url) {

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
			return wire.parseFrom(bytes, Response.class);
		} catch (final Exception e) {
			if (DEBUG_ERROR) {
				e.printStackTrace(System.err);
				if (bytes != null) {
					System.err.println(new String(bytes));
				}
			}
			throw new RuntimeException(e);
		}

	}

	public CheckinResponse checkin(final AndroidContext info, final String authToken) {
		final long now = new Date().getTime();

		final Request.Checkin.Build.Builder build = new Request.Checkin.Build.Builder();
		if (notEmpty(info.getBuildFingerprint())) {
			build.fingerprint(info.getBuildFingerprint());
		}
		if (notEmpty(info.getBuildProduct())) {
			build.product(info.getBuildProduct());
		}
		if (notEmpty(info.getBuildBrand())) {
			build.brand(info.getBuildBrand());
		}
		if (notEmpty(info.getBuildRadio())) {
			build.radio(info.getBuildRadio());
		}
		if (notEmpty(info.getBuildBootloader())) {
			build.bootloader(info.getBuildBootloader());
		}
		if (notEmpty(info.getBuildHardware())) {
			build.hardware(info.getBuildHardware());
		}
		if (notEmpty(info.getBuildManufacturer())) {
			build.manufacturer(info.getBuildManufacturer());
		}
		if (notEmpty(info.getBuildModel())) {
			build.model(info.getBuildModel());
		}
		if (notEmpty(info.getBuildDevice())) {
			build.device(info.getBuildDevice());
		}
		build.time(info.getBuildTime()).packageVersionCode(info.getBuildSdkVersion()).sdkVersion(info.getBuildSdkVersion()).clientId(info.getBuildClientId()).otaInstalled(info.getBuildOtaInstalled());

		final Request.Checkin checkin = new Request.Checkin.Builder().build(build.build()).lastCheckinMs(0L).event(Arrays.asList(new Request.Checkin.Event.Builder().tag("event_log_start").timeMs(now).build())).cellOperator(info.getCellOperator()).simOperator(info.getSimOperator()).roaming(info.getRoaming()).userNumber(info.getUserNumber()).build();

		final Request.DeviceConfig.Builder deviceConfig = new Request.DeviceConfig.Builder();
		deviceConfig.touchScreen(inRangeOr(info.getDeviceTouchScreen(), 0, 3, 0));
		deviceConfig.keyboardType(inRangeOr(info.getDeviceKeyboardType(), 0, 3, 0));
		deviceConfig.navigation(inRangeOr(info.getDeviceNavigation(), 0, 4, 0));
		deviceConfig.widthPixels(info.getDeviceWidthPixels());
		deviceConfig.heightPixels(info.getDeviceHeightPixels());
		deviceConfig.screenLayout(inRangeOr(info.getDeviceScreenLayout(), 0, 4, 0));
		deviceConfig.hasHardKeyboard(info.getDeviceHasHardKeyboard());
		deviceConfig.densityDpi(info.getDeviceDensityDpi());
		deviceConfig.glEsVersion(info.getDeviceGlEsVersion());
		deviceConfig.glExtension(info.getDeviceGlExtensions());

		if (isKnown(info.getBuildCpuAbi2())) {
			deviceConfig.nativePlatform(Arrays.asList(info.getBuildCpuAbi(), info.getBuildCpuAbi2()));
		} else {
			deviceConfig.nativePlatform(Arrays.asList(info.getBuildCpuAbi()));
		}
		deviceConfig.sharedLibrary(info.getDeviceSharedLibraries());
		deviceConfig.availableFeature(info.getDeviceFeatures());
		deviceConfig.locale(info.getDeviceLocales());
		deviceConfig.hasFiveWayNavigation(info.getDeviceHasFiveWayNavigation());

		final Request.Builder request = new Request.Builder().digest(orEmpty(info.getDigest())).imei(info.getImei()).locale(info.getLocaleString()).timeZone(info.getTimeZoneString()).checkin(checkin).deviceConfiguration(deviceConfig.build()).otaCert(Arrays.asList(orString(info.getOtaCert(), "--no-output--"))).loggingId(info.getLoggingId()).fragment(0);

		if (info.getAndroidId() == 0 || info.getSecurityToken() != 0) {
			request.version(version);
		}
		if (isKnown(info.getMeid())) {
			request.meid(info.getMeid());
		}
		if (isKnown(info.getEsn())) {
			request.esn(info.getEsn());
		}
		if (isKnown(info.getBuildSerial())) {
			request.serial(info.getBuildSerial());
		}
		if (isKnown(info.getMacAddress()) && isKnown(info.getMacAddressType())) {
			request.macAddressType(Arrays.asList(info.getMacAddressType()));
			request.macAddress(Arrays.asList(info.getMacAddress()));
		}

		if (info.getSecurityToken() != 0 && info.getSecurityToken() != Long.MIN_VALUE) {
			request.securityToken(info.getSecurityToken());
		}
		if (info.getAndroidId() != 0 && info.getAndroidId() != Long.MIN_VALUE) {
			request.androidId(info.getAndroidId());
		}
		if (info.getEmail() != null && !info.getEmail().isEmpty()) {
			if (authToken != null && !authToken.isEmpty()) {
				request.accountCookie(Arrays.asList(authToken, "[" + info.getEmail() + "]"));
			} else {
				request.accountCookie(Arrays.asList("[" + info.getEmail() + "]"));
			}
		}

		final Response response = sendRequest(request.build(), checkInUrl);
		System.out.println(response);

		if (response == null) {
			return null;
		} else {
			return new CheckinResponse(response);
		}
	}

}
