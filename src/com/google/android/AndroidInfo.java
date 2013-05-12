package com.google.android;

import com.google.tools.RequestInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class AndroidInfo extends RequestInfo{

	static Random rand = new Random();

	public static AndroidInfo baseNexus4() {
		return new AndroidInfo()
				.setBuildCpuAbi("armeabi-v7a")
				.setBuildCpuAbi2("armeabi")
				.setBuildFingerprint(
						"google/occam/mako:4.2.2/JDQ39/573038:user/release-keys")
				.setBuildHardware("MAKO")
				.setBuildBrand("Google")
				.setBuildId("JDQ39")
				.setBuildClientId("android-google")
				.setBuildTime(new Date().getTime() / 1000)
				.setBuildSdkVersion(17)
				.setBuildDevice("mako")
				.setBuildModel("Nexus 4")
				.setBuildManufacturer("LGE")
				.setBuildProduct("occam")
				.setBuildOtaInstalled(false)
				.setOperator("26207", "o2-de")
				.setTimeZone("Europe/Berlin")
				.setRoaming("mobile-notroaming")
				.setUserNumber(0)
				.setLoggingId(rand.nextLong())
				.setLocale(Locale.US)
				.setDeviceTouchScreen(3)
				.setDeviceKeyboardType(1)
				.setDeviceNavigation(1)
				.setDeviceScreenLayout(2)
				.setDeviceInputFeatures(0)
				.setDeviceGlEsVersion(131072)
				.setDeviceWidthPixels(768)
				.setDeviceHeightPixels(1280)
				.setDeviceSharedLibraries(
						Arrays.asList("android.test.runner",
								"com.android.future.usb.accessory",
								"com.android.location.provider",
								"com.android.nfc_extras",
								"com.google.android.maps",
								"com.google.android.media.effects",
								"com.google.widevine.software.drm",
								"javax.obex"))
				.setDeviceFeatures(
						Arrays.asList(
								"android.hardware.bluetooth",
								"android.hardware.camera",
								"android.hardware.camera.autofocus",
								"android.hardware.camera.flash",
								"android.hardware.camera.front",
								"android.hardware.faketouch",
								"android.hardware.location",
								"android.hardware.location.gps",
								"android.hardware.location.network",
								"android.hardware.microphone",
								"android.hardware.nfc",
								"android.hardware.screen.landscape",
								"android.hardware.screen.portrait",
								"android.hardware.sensor.accelerometer",
								"android.hardware.sensor.barometer",
								"android.hardware.sensor.compass",
								"android.hardware.sensor.gyroscope",
								"android.hardware.sensor.light",
								"android.hardware.sensor.proximity",
								"android.hardware.telephony",
								"android.hardware.telephony.gsm",
								"android.hardware.touchscreen",
								"android.hardware.touchscreen.multitouch",
								"android.hardware.touchscreen.multitouch.distinct",
								"android.hardware.touchscreen.multitouch.jazzhand",
								"android.hardware.usb.accessory",
								"android.hardware.usb.host",
								"android.hardware.wifi",
								"android.hardware.wifi.direct",
								"android.software.live_wallpaper",
								"android.software.sip",
								"android.software.sip.voip",
								"com.cyanogenmod.android",
								"com.cyanogenmod.nfc.enhanced",
								"com.google.android.feature.GOOGLE_BUILD",
								"com.nxp.mifare", "com.tmobile.software.themes"))
				.setDeviceLocales(
						Arrays.asList("af", "af_ZA", "am", "am_ET", "ar",
								"ar_EG", "bg", "bg_BG", "ca", "ca_ES", "cs",
								"cs_CZ", "da", "da_DK", "de", "de_AT", "de_CH",
								"de_DE", "de_LI", "el", "el_GR", "en", "en_AU",
								"en_CA", "en_GB", "en_NZ", "en_SG", "en_US",
								"es", "es_ES", "es_US", "fa", "fa_IR", "fi",
								"fi_FI", "fr", "fr_BE", "fr_CA", "fr_CH",
								"fr_FR", "hi", "hi_IN", "hr", "hr_HR", "hu",
								"hu_HU", "in", "in_ID", "it", "it_CH", "it_IT",
								"iw", "iw_IL", "ja", "ja_JP", "ko", "ko_KR",
								"lt", "lt_LT", "lv", "lv_LV", "ms", "ms_MY",
								"nb", "nb_NO", "nl", "nl_BE", "nl_NL", "pl",
								"pl_PL", "pt", "pt_BR", "pt_PT", "rm", "rm_CH",
								"ro", "ro_RO", "ru", "ru_RU", "sk", "sk_SK",
								"sl", "sl_SI", "sr", "sr_RS", "sv", "sv_SE",
								"sw", "sw_TZ", "th", "th_TH", "tl", "tl_PH",
								"tr", "tr_TR", "ug", "ug_CN", "uk", "uk_UA",
								"vi", "vi_VN", "zh_CN", "zh_TW", "zu", "zu_ZA"))
				.setGlExtensions(
						Arrays.asList("GL_EXT_debug_marker",
								"GL_EXT_discard_framebuffer",
								"GL_EXT_multi_draw_arrays",
								"GL_EXT_shader_texture_lod",
								"GL_EXT_texture_format_BGRA8888",
								"GL_IMG_multisampled_render_to_texture",
								"GL_IMG_program_binary", "GL_IMG_read_format",
								"GL_IMG_shader_binary",
								"GL_IMG_texture_compression_pvrtc",
								"GL_IMG_texture_format_BGRA8888",
								"GL_IMG_texture_npot",
								"GL_IMG_vertex_array_object",
								"GL_OES_EGL_image",
								"GL_OES_EGL_image_external",
								"GL_OES_blend_equation_separate",
								"GL_OES_blend_func_separate",
								"GL_OES_blend_subtract",
								"GL_OES_byte_coordinates",
								"GL_OES_compressed_ETC1_RGB8_texture",
								"GL_OES_compressed_paletted_texture",
								"GL_OES_depth24", "GL_OES_depth_texture",
								"GL_OES_draw_texture", "GL_OES_egl_sync",
								"GL_OES_element_index_uint",
								"GL_OES_extended_matrix_palette",
								"GL_OES_fixed_point",
								"GL_OES_fragment_precision_high",
								"GL_OES_framebuffer_object",
								"GL_OES_get_program_binary",
								"GL_OES_mapbuffer", "GL_OES_matrix_get",
								"GL_OES_matrix_palette",
								"GL_OES_packed_depth_stencil",
								"GL_OES_point_size_array",
								"GL_OES_point_sprite", "GL_OES_query_matrix",
								"GL_OES_read_format",
								"GL_OES_required_internalformat",
								"GL_OES_rgb8_rgba8", "GL_OES_single_precision",
								"GL_OES_standard_derivatives",
								"GL_OES_stencil8", "GL_OES_stencil_wrap",
								"GL_OES_texture_cube_map",
								"GL_OES_texture_env_crossbar",
								"GL_OES_texture_float",
								"GL_OES_texture_half_float",
								"GL_OES_texture_mirrored_repeat",
								"GL_OES_vertex_array_object",
								"GL_OES_vertex_half_float"));

	}

	public static AndroidInfo randomGalaxyNexus() {
		return new AndroidInfo()
				.setBuildCpuAbi("armeabi-v7a")
				.setBuildCpuAbi2("armeabi")
				.setBuildBootloader("PRIMELA03")
				.setBuildFingerprint(
						"google/yakju/maguro:4.1.1/JRO03C/398337:user/release-keys")
				.setBuildHardware("tuna")
				.setBuildId("JRO03C")
				.setBuildBrand("Google")
				.setBuildRadio("I9250XXLA2")
				.setBuildClientId("android-google")
				.setBuildTime(new Date().getTime() / 1000)
				.setBuildSdkVersion(16)
				.setBuildDevice("maguro")
				.setBuildModel("Galaxy Nexus")
				.setBuildManufacturer("Samsung")
				.setBuildProduct("yakju")
				.setBuildOtaInstalled(false)
				.setOperator("310260", "T-Mobile USA")
				.setTimeZone("America/New_York")
				.setRoaming("mobile-notroaming")
				.setUserNumber(0)
				.setLoggingId(rand.nextLong())
				.setOtaCert("71Q6Rn2DDZl1zPDVaaeEHItd")
				.setLocale(Locale.US)
				.setSerial(randomSerialNumber())
				.setImei(randomImei())
				.setDigest("1-929a0dca0eee55513280171a8585da7dcd3700f8")
				.setMacAddress(randomMacAddress())
				.setMeid(randomMeid())
				.setDeviceTouchScreen(3)
				.setDeviceKeyboardType(1)
				.setDeviceNavigation(1)
				.setDeviceScreenLayout(2)
				.setDeviceInputFeatures(0)
				.setDeviceGlEsVersion(131072)
				.setDeviceWidthPixels(720)
				.setDeviceHeightPixels(1184)
				.setDeviceSharedLibraries(
						Arrays.asList("android.test.runner",
								"com.android.future.usb.accessory",
								"com.android.location.provider",
								"com.android.nfc_extras",
								"com.google.android.maps",
								"com.google.android.media.effects",
								"com.google.widevine.software.drm",
								"javax.obex"))
				.setDeviceFeatures(
						Arrays.asList(
								"android.hardware.bluetooth",
								"android.hardware.camera",
								"android.hardware.camera.autofocus",
								"android.hardware.camera.flash",
								"android.hardware.camera.front",
								"android.hardware.faketouch",
								"android.hardware.location",
								"android.hardware.location.gps",
								"android.hardware.location.network",
								"android.hardware.microphone",
								"android.hardware.nfc",
								"android.hardware.screen.landscape",
								"android.hardware.screen.portrait",
								"android.hardware.sensor.accelerometer",
								"android.hardware.sensor.barometer",
								"android.hardware.sensor.compass",
								"android.hardware.sensor.gyroscope",
								"android.hardware.sensor.light",
								"android.hardware.sensor.proximity",
								"android.hardware.telephony",
								"android.hardware.telephony.gsm",
								"android.hardware.touchscreen",
								"android.hardware.touchscreen.multitouch",
								"android.hardware.touchscreen.multitouch.distinct",
								"android.hardware.touchscreen.multitouch.jazzhand",
								"android.hardware.usb.accessory",
								"android.hardware.usb.host",
								"android.hardware.wifi",
								"android.hardware.wifi.direct",
								"android.software.live_wallpaper",
								"android.software.sip",
								"android.software.sip.voip",
								"com.cyanogenmod.android",
								"com.cyanogenmod.nfc.enhanced",
								"com.google.android.feature.GOOGLE_BUILD",
								"com.nxp.mifare", "com.tmobile.software.themes"))
				.setDeviceLocales(
						Arrays.asList("af", "af_ZA", "am", "am_ET", "ar",
								"ar_EG", "bg", "bg_BG", "ca", "ca_ES", "cs",
								"cs_CZ", "da", "da_DK", "de", "de_AT", "de_CH",
								"de_DE", "de_LI", "el", "el_GR", "en", "en_AU",
								"en_CA", "en_GB", "en_NZ", "en_SG", "en_US",
								"es", "es_ES", "es_US", "fa", "fa_IR", "fi",
								"fi_FI", "fr", "fr_BE", "fr_CA", "fr_CH",
								"fr_FR", "hi", "hi_IN", "hr", "hr_HR", "hu",
								"hu_HU", "in", "in_ID", "it", "it_CH", "it_IT",
								"iw", "iw_IL", "ja", "ja_JP", "ko", "ko_KR",
								"lt", "lt_LT", "lv", "lv_LV", "ms", "ms_MY",
								"nb", "nb_NO", "nl", "nl_BE", "nl_NL", "pl",
								"pl_PL", "pt", "pt_BR", "pt_PT", "rm", "rm_CH",
								"ro", "ro_RO", "ru", "ru_RU", "sk", "sk_SK",
								"sl", "sl_SI", "sr", "sr_RS", "sv", "sv_SE",
								"sw", "sw_TZ", "th", "th_TH", "tl", "tl_PH",
								"tr", "tr_TR", "ug", "ug_CN", "uk", "uk_UA",
								"vi", "vi_VN", "zh_CN", "zh_TW", "zu", "zu_ZA"))
				.setGlExtensions(
						Arrays.asList("GL_EXT_debug_marker",
								"GL_EXT_discard_framebuffer",
								"GL_EXT_multi_draw_arrays",
								"GL_EXT_shader_texture_lod",
								"GL_EXT_texture_format_BGRA8888",
								"GL_IMG_multisampled_render_to_texture",
								"GL_IMG_program_binary", "GL_IMG_read_format",
								"GL_IMG_shader_binary",
								"GL_IMG_texture_compression_pvrtc",
								"GL_IMG_texture_format_BGRA8888",
								"GL_IMG_texture_npot",
								"GL_IMG_vertex_array_object",
								"GL_OES_EGL_image",
								"GL_OES_EGL_image_external",
								"GL_OES_blend_equation_separate",
								"GL_OES_blend_func_separate",
								"GL_OES_blend_subtract",
								"GL_OES_byte_coordinates",
								"GL_OES_compressed_ETC1_RGB8_texture",
								"GL_OES_compressed_paletted_texture",
								"GL_OES_depth24", "GL_OES_depth_texture",
								"GL_OES_draw_texture", "GL_OES_egl_sync",
								"GL_OES_element_index_uint",
								"GL_OES_extended_matrix_palette",
								"GL_OES_fixed_point",
								"GL_OES_fragment_precision_high",
								"GL_OES_framebuffer_object",
								"GL_OES_get_program_binary",
								"GL_OES_mapbuffer", "GL_OES_matrix_get",
								"GL_OES_matrix_palette",
								"GL_OES_packed_depth_stencil",
								"GL_OES_point_size_array",
								"GL_OES_point_sprite", "GL_OES_query_matrix",
								"GL_OES_read_format",
								"GL_OES_required_internalformat",
								"GL_OES_rgb8_rgba8", "GL_OES_single_precision",
								"GL_OES_standard_derivatives",
								"GL_OES_stencil8", "GL_OES_stencil_wrap",
								"GL_OES_texture_cube_map",
								"GL_OES_texture_env_crossbar",
								"GL_OES_texture_float",
								"GL_OES_texture_half_float",
								"GL_OES_texture_mirrored_repeat",
								"GL_OES_vertex_array_object",
								"GL_OES_vertex_half_float"));

	}

	public String getBuildId() {
		return buildId;
	}

	public AndroidInfo setBuildId(String id) {
		buildId = id;
		return this;
	}

	private static String randomImei() {
		final StringBuilder sb = new StringBuilder(15);
		for (int i = 0; i < 15; i++) {
			sb.append("0123456789".charAt(rand.nextInt(10)));
		}
		return sb.toString();
	}

	private static String randomMacAddress() {
		String mac = "b407f9";
		for (int i = 0; i < 6; i++) {
			mac += Integer.toString(rand.nextInt(16), 16);
		}
		return mac;
	}

	private static String randomMeid() {
		// http://en.wikipedia.org/wiki/International_Mobile_Equipment_Identity
		// We start with a known base, and generate random MEID
		String meid = "35503104";
		for (int i = 0; i < 6; i++) {
			meid += Integer.toString(rand.nextInt(10));
		}

		// Luhn algorithm (check digit)
		int sum = 0;
		for (int i = 0; i < meid.length(); i++) {
			int c = Integer.parseInt(String.valueOf(meid.charAt(i)));
			if ((meid.length() - i - 1) % 2 == 0) {
				c *= 2;
				c = c % 10 + c / 10;
			}

			sum += c;
		}
		final int check = (100 - sum) % 10;
		meid += Integer.toString(check);

		return meid;
	}

	private static String randomSerialNumber() {
		String serial = "3933E6";
		for (int i = 0; i < 10; i++) {
			serial += Integer.toString(rand.nextInt(16), 16);
		}
		serial = serial.toUpperCase();
		return serial;
	}

	private String buildBootloader; // Build.BOOTLOADER
	private String buildBrand; // Build.BRAND
	private String buildCpuAbi; // Build.CPU_ABI
	private String buildCpuAbi2; // BUILD.CPU_ABI2
	private String buildDevice; // Build.DEVICE
	private String buildFingerprint; // Build.FINGERPRINT
	private String buildHardware; // Build.HARDWARE
	private String buildId; // Build.ID
	private String buildManufacturer; // Build.MANUFACTURER
	private String buildModel; // Build.MODEL
	private boolean buildOtaInstalled; // fileExists("/system/recovery-from-boot.p")
	private String buildProduct; // Build.PRODUCT
	private String buildRadio; // Build.getRadioVersion()
	private int buildSdkVersion; // Build.VERSION.SDK_INT
	private String buildSerial; // Build.SERIAL
	private long buildTime = 0; // Build.TIME / 1000L
	private int deviceDensityDpi; // DisplayMetrics.densityDpi
	private List<String> deviceFeatures = new ArrayList<String>(); // PackageManager.getSystemAvailableFeatures()
	private int deviceGlEsVersion; // ConfigurationInfo.reqGlEsVersion
	private int deviceHeightPixels; // DisplayMetrics.heightPixels
	private int deviceInputFeatures; // ConfigurationInfo.reqInputFeatures
	private int deviceKeyboardType; // ConfigurationInfo.reqKeyboardType
	private List<String> deviceLocales = new ArrayList<String>(); // Context.getAssets().getLocales()
	private int deviceNavigation; // ConfigurationInfo.reqNavigation
	private int deviceScreenLayout; // ConfigurationInfo.screenLayout
	private List<String> deviceSharedLibraries = new ArrayList<String>();
	private int deviceTouchScreen;
	private int deviceWidthPixels;
	private String digest; // Gservices["digest"]
	private String email;
	private String esn;
	private List<String> glExtensions = new ArrayList<String>();
	private String imei;
	private Locale locale;
	private String macAddress;
	private String meid;
	private String otaCert;
	private String roaming;
	private long securityToken;
	private String simOperator;
	private String simOperatorName;
	private TimeZone timeZone;
	private int userNumber;

	public void addGlExtension(final String extension) {
		glExtensions.add(extension);
	}

	public long getAndroidId() {
		return getLong(KEY_ANDROID_ID_LONG);
	}

	public String getAndroidIdHex() {
		return get(KEY_ANDROID_ID_HEX);
	}

	public String getBuildBootloader() {
		return buildBootloader;
	}

	public String getBuildBrand() {
		return buildBrand;
	}

	public String getBuildClientId() {
		return get(KEY_CLIENT_ID);
	}

	public String getBuildCpuAbi() {
		return buildCpuAbi;
	}

	public String getBuildCpuAbi2() {
		return buildCpuAbi2;
	}

	public String getBuildDevice() {
		return buildDevice;
	}

	public String getBuildFingerprint() {
		return buildFingerprint;
	}

	public String getBuildHardware() {
		return buildHardware;
	}

	public String getBuildManufacturer() {
		return buildManufacturer;
	}

	public String getBuildModel() {
		return buildModel;
	}

	public boolean getBuildOtaInstalled() {
		return buildOtaInstalled;
	}

	public String getBuildProduct() {
		return buildProduct;
	}

	public String getBuildRadio() {
		return buildRadio;
	}

	public int getBuildSdkVersion() {
		return buildSdkVersion;
	}

	public String getBuildSerial() {
		return buildSerial;
	}

	public long getBuildTime() {
		return buildTime;
	}

	public String getCellOperator() {
		return get(KEY_CELL_OPERATOR_NUMERIC);
	}

	public String getCellOperatorName() {
		return get(KEY_CELL_OPERATOR_NAME);
	}

	public String getCountry() {
		if (locale != null) {
			return locale.getCountry();
		} else {
			return null;
		}
	}

	public int getDeviceDensityDpi() {
		return deviceDensityDpi;
	}

	public List<String> getDeviceFeatures() {
		return deviceFeatures;
	}

	public int getDeviceGlEsVersion() {
		return deviceGlEsVersion;
	}

	public List<String> getDeviceGlExtensions() {
		return glExtensions;
	}

	public boolean getDeviceHasFiveWayNavigation() {
		return (deviceInputFeatures & 2) > 0;
	}

	public boolean getDeviceHasHardKeyboard() {
		return (deviceInputFeatures & 1) > 0;
	}

	public int getDeviceHeightPixels() {
		return deviceHeightPixels;
	}

	public int getDeviceInputFeatures() {
		return deviceInputFeatures;
	}

	public int getDeviceKeyboardType() {
		return deviceKeyboardType;
	}

	public List<String> getDeviceLocales() {
		return deviceLocales;
	}

	public int getDeviceNavigation() {
		return deviceNavigation;
	}

	public int getDeviceScreenLayout() {
		return deviceScreenLayout;
	}

	public List<String> getDeviceSharedLibraries() {
		return deviceSharedLibraries;
	}

	public int getDeviceTouchScreen() {
		return deviceTouchScreen;
	}

	public int getDeviceWidthPixels() {
		return deviceWidthPixels;
	}

	public String getDigest() {
		return digest;
	}

	public String getEmail() {
		return email;
	}

	public String getEsn() {
		return esn;
	}

	public String getImei() {
		return imei;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public Locale getLocale() {
		return locale;
	}

	public String getLocaleString() {
		return locale.toString();
	}

	public long getLoggingId() {
		return getLong(KEY_LOGGING_ID);
	}

	public String getMacAddress() {
		return macAddress;
	}

	public String getMacAddressType() {
		return "wifi";
	}

	public String getMeid() {
		return meid;
	}

	public String getOtaCert() {
		return otaCert;
	}

	public String getRoaming() {
		return roaming;
	}

	public String getSdkVersionString() {
		return buildSdkVersion + "";
	}

	public long getSecurityToken() {
		return securityToken;
	}

	public String getSimOperator() {
		return simOperator;
	}

	public String getSimOperatorName() {
		return simOperatorName;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public AndroidInfo setUserNumber(int userNumber) {
		this.userNumber = userNumber;
		return this;
	}

	public String getTimeZoneString() {
		if (timeZone != null) {
			return timeZone.getID();
		} else {
			return null;
		}
	}

	public int getUserNumber() {
		return userNumber;
	}

	public AndroidInfo setAndroidId(final long androidId) {
		put(KEY_ANDROID_ID_HEX, Long.toHexString(androidId));
		put(KEY_ANDROID_ID_LONG, androidId);
		return this;
	}

	public AndroidInfo setAndroidId(final String androidId) {
		put(KEY_ANDROID_ID_LONG, Long.parseLong(androidId, 16));
		put(KEY_ANDROID_ID_HEX, androidId);
		return this;
	}

	public AndroidInfo setBuildBootloader(final String bootloader) {
		buildBootloader = bootloader;
		return this;
	}

	public AndroidInfo setBuildBrand(final String brand) {
		buildBrand = brand;
		return this;
	}

	public AndroidInfo setBuildClientId(final String client) {
		put(KEY_CLIENT_ID, client);
		return this;
	}

	public AndroidInfo setBuildCpuAbi(final String cpuabi) {
		buildCpuAbi = cpuabi;
		return this;
	}

	public AndroidInfo setBuildCpuAbi2(final String cpuabi) {
		buildCpuAbi2 = cpuabi;
		return this;
	}

	public AndroidInfo setBuildDevice(final String device) {
		buildDevice = device;
		return this;
	}

	public AndroidInfo setBuildFingerprint(final String buildId) {
		buildFingerprint = buildId;
		return this;
	}

	public AndroidInfo setBuildHardware(final String buildHardware) {
		this.buildHardware = buildHardware;
		return this;
	}

	public AndroidInfo setBuildManufacturer(final String manufacturer) {
		buildManufacturer = manufacturer;
		return this;
	}

	public AndroidInfo setBuildModel(final String model) {
		buildModel = model;
		return this;
	}

	public AndroidInfo setBuildOtaInstalled(final boolean buildOtaInstalled) {
		this.buildOtaInstalled = buildOtaInstalled;
		return this;
	}

	public AndroidInfo setBuildProduct(final String product) {
		buildProduct = product;
		return this;
	}

	public AndroidInfo setBuildRadio(final String radio) {
		buildRadio = radio;
		return this;
	}

	public AndroidInfo setBuildSdkVersion(final int sdkVersion) {
		buildSdkVersion = sdkVersion;
		return this;
	}

	public AndroidInfo setBuildSerial(final String buildSerial) {
		this.buildSerial = buildSerial;
		return this;
	}

	public AndroidInfo setBuildTime(final long buildTimestamp) {
		buildTime = buildTimestamp;
		return this;
	}

	public AndroidInfo setCellOperator(final String numeric) {
		put(KEY_CELL_OPERATOR_NUMERIC, numeric);
		return this;
	}

	public AndroidInfo setCellOperator(final String numeric, final String alpha) {
		setCellOperatorName(alpha);
		setCellOperator(numeric);
		return this;
	}

	public AndroidInfo setCellOperatorName(final String alpha) {
		put(KEY_CELL_OPERATOR_NAME, alpha);
		return this;
	}

	public AndroidInfo setDeviceDensityDpi(final int deviceDensityDpi) {
		this.deviceDensityDpi = deviceDensityDpi;
		return this;
	}

	public AndroidInfo setDeviceFeatures(final List<String> systemFeatures) {
		deviceFeatures = systemFeatures;
		if (systemFeatures == null) {
			deviceFeatures = new ArrayList<String>();
		}
		return this;
	}

	public AndroidInfo setDeviceGlEsVersion(final int deviceGlEsVersion) {
		this.deviceGlEsVersion = deviceGlEsVersion;
		return this;
	}

	public AndroidInfo setDeviceHeightPixels(final int deviceHeightPixels) {
		this.deviceHeightPixels = deviceHeightPixels;
		return this;
	}

	public AndroidInfo setDeviceInputFeatures(final int deviceInputFeatures) {
		this.deviceInputFeatures = deviceInputFeatures;
		return this;
	}

	public AndroidInfo setDeviceKeyboardType(final int deviceKeyboardType) {
		this.deviceKeyboardType = deviceKeyboardType;
		return this;
	}

	public AndroidInfo setDeviceLocales(final List<String> supportedLocales) {
		deviceLocales = supportedLocales;
		if (supportedLocales == null) {
			deviceLocales = new ArrayList<String>();
		}
		return this;
	}

	public AndroidInfo setDeviceNavigation(final int deviceNavigation) {
		this.deviceNavigation = deviceNavigation;
		return this;
	}

	public AndroidInfo setDeviceScreenLayout(final int deviceScreenLayout) {
		this.deviceScreenLayout = deviceScreenLayout;
		return this;
	}

	public AndroidInfo setDeviceSharedLibraries(
			final List<String> sharedLibraries) {
		deviceSharedLibraries = sharedLibraries;
		if (sharedLibraries == null) {
			deviceSharedLibraries = new ArrayList<String>();
		}
		return this;
	}

	public AndroidInfo setDeviceTouchScreen(final int deviceTouchScreen) {
		this.deviceTouchScreen = deviceTouchScreen;
		return this;
	}

	public AndroidInfo setDeviceWidthPixels(final int deviceWidthPixels) {
		this.deviceWidthPixels = deviceWidthPixels;
		return this;
	}

	public AndroidInfo setDigest(final String digest) {
		this.digest = digest;
		return this;
	}

	public AndroidInfo setEmail(final String email) {
		this.email = email;
		return this;
	}

	public AndroidInfo setEsn(final String esn) {
		this.esn = esn;
		return this;
	}

	public AndroidInfo setGlExtensions(final List<String> glExtensions) {
		this.glExtensions = glExtensions;
		if (glExtensions == null) {
			this.glExtensions = new ArrayList<String>();
		}
		return this;
	}

	public AndroidInfo setImei(final String imei) {
		this.imei = imei;
		return this;
	}

	public AndroidInfo setImeiRandom() {
		setImei(randomImei());
		return this;
	}

	public AndroidInfo setLocale(final Locale locale) {
		this.locale = locale;
		return this;
	}

	public AndroidInfo setLoggingId(final long loggingId) {
		put(KEY_LOGGING_ID, loggingId);
		return this;
	}

	public AndroidInfo setMacAddress(final String mac) {
		macAddress = mac;
		return this;
	}

	public AndroidInfo setMeid(final String meid) {
		this.meid = meid;
		return this;
	}

	public AndroidInfo setOperator(final String numeric, final String alpha) {
		setCellOperator(numeric, alpha);
		setSimOperator(numeric, alpha);
		return this;
	}

	public AndroidInfo setOtaCert(final String otaCert) {
		this.otaCert = otaCert;
		return this;
	}

	public AndroidInfo setRoaming(final String roaming) {
		this.roaming = roaming;
		return this;
	}

	public AndroidInfo setSecurityToken(final long securityToken) {
		this.securityToken = securityToken;
		return this;
	}

	public AndroidInfo setSerial(final String serial) {
		buildSerial = serial;
		return this;
	}

	public AndroidInfo setSimOperator(final String operatorNumeric) {
		simOperator = operatorNumeric;
		return this;
	}

	public AndroidInfo setSimOperator(final String numeric, final String alpha) {
		setSimOperatorName(alpha);
		setSimOperator(numeric);
		return this;
	}

	public AndroidInfo setSimOperatorName(final String operatorAlpha) {
		simOperatorName = operatorAlpha;
		return this;
	}

	public AndroidInfo setTimeZone(final String timeZone) {
		if (timeZone == null) {
			return setTimeZone((TimeZone) null);
		} else {
			return setTimeZone(TimeZone.getTimeZone(timeZone));
		}
	}

	public AndroidInfo setTimeZone(final TimeZone timeZone) {
		this.timeZone = timeZone;
		return this;
	}

}
