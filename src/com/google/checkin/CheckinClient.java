package com.google.checkin;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.checkin.proto.CheckIn.AndroidCheckinRequest;
import com.google.checkin.proto.CheckIn.AndroidCheckinResponse;
import com.google.tools.Client;

public class CheckinClient extends Client {

	protected static final String REQUEST_CONTENT_TYPE = "application/x-protobuffer";
	protected static final String REQUEST_CONTENT_TYPE_FIELD = "Content-Type";

	public static AndroidCheckinResponse sendRequest(
			AndroidCheckinRequest request, String url) {

		byte[] bytes = null;
		try {
			HttpPost post = new HttpPost(url);
			post.setHeader(REQUEST_CONTENT_TYPE_FIELD, REQUEST_CONTENT_TYPE);
			post.setEntity(new ByteArrayEntity(request.toByteArray()));
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			InputStream is = response.getEntity().getContent();
			bytes = readStreamToEnd(is);
			return AndroidCheckinResponse.parseFrom(bytes);
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

}
