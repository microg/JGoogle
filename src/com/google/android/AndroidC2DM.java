package com.google.android;

import java.util.Collections;
import java.util.Map;

import com.google.c2dm.C2DMClient;

public class AndroidC2DM {
	public static final String register(AndroidInfo info, String app,
			String appCert, String sender, Map<String, String> extras) {
		if (extras == null) {
			extras = Collections.emptyMap();
		}
		return C2DMClient.sendRegister(info.getAndroidId(),
				info.getSecurityToken(), app, appCert, sender, extras);
	}
}
