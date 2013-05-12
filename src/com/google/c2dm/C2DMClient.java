package com.google.c2dm;

import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.tools.Client;

public class C2DMClient extends Client {
	private static final String URL = "https://android.clients.google.com/c2dm/register3";
	private static final String USER_AGENT = "AndroidC2DM/1.1";

	public static String sendRegister(long androidId, long securityToken,
			String app, String appCert, String sender,
			Map<String, String> extras) {
		HttpPost post = new HttpPost(URL);
		post.addHeader("Authorization", "AidLogin " + androidId + ":"
				+ securityToken);
		post.addHeader("app", app);
		post.addHeader(REQUEST_USER_AGENT_FIELD, USER_AGENT);
		for (String key : extras.keySet()) {
			post.addHeader("X-" + key, extras.get(key));
		}
		ArrayList<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		list.add(new BasicNameValuePair("app", app));
		list.add(new BasicNameValuePair("sender", sender));
		list.add(new BasicNameValuePair("cert", appCert));
		list.add(new BasicNameValuePair("device", Long.toString(androidId)));
		list.add(new BasicNameValuePair("device_user_id", "0"));
		HttpClient client = new DefaultHttpClient();
		try {
			post.setEntity(new UrlEncodedFormEntity(list));
			HttpResponse response = client.execute(post);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			if (DEBUG) {
				System.out.println(result);
			}
			if (result.endsWith("\n")) {
				result = result.substring(0, result.length() - 1);
			}
			if (result.startsWith("token")) {
				return result.split("=")[1];
			}
		} catch (Exception e) {
			if (DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
