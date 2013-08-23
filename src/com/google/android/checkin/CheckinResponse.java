package com.google.android.checkin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckinResponse {
	private final long androidId;
	private final long securityToken;
	private final Map<String, String> settings;
	private final boolean marketEnabled;
	private final String digest;

	public CheckinResponse(CheckInProto.CheckinResponse response) {
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

	private static Map<String, String> parseSettings(List<CheckInProto.CheckinResponse.GservicesSetting> settings) {
		Map<String, String> map = new HashMap<String, String>();
		for (CheckInProto.CheckinResponse.GservicesSetting setting : settings) {
			map.put(setting.getName().toStringUtf8(), setting.getValue().toStringUtf8());
		}
		return Collections.unmodifiableMap(map);
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
