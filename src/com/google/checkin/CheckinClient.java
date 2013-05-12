package com.google.checkin;

import java.io.InputStream;

import com.google.checkin.proto.CheckIn;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.tools.Client;

public class CheckinClient extends Client {

	protected static final String REQUEST_CONTENT_TYPE = "application/x-protobuffer";
	protected static final String REQUEST_CONTENT_TYPE_FIELD = "Content-Type";

	public static CheckIn.CheckinResponse sendRequest(
			final CheckIn.CheckinRequest request, final String url) {

		byte[] bytes = null;
		try {
			final HttpPost post = new HttpPost(url);
			post.setHeader(REQUEST_CONTENT_TYPE_FIELD, REQUEST_CONTENT_TYPE);
			post.setEntity(new ByteArrayEntity(request.toByteArray()));
			post.setHeader("User-Agent",
					"Android-Checkin/2.0 (mako JDQ39); gzip");
			final DefaultHttpClient client = new DefaultHttpClient();
			final HttpResponse response = client.execute(post);
			final InputStream is = response.getEntity().getContent();
			bytes = readStreamToEnd(is);
			return CheckIn.CheckinResponse.parseFrom(bytes);
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
