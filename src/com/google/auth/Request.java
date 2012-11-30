package com.google.auth;

import java.net.URL;

public class Request extends DataMapWriter implements Constants {

	private URL requestUrl;

	public Request() {
		super();
	}

	public Request(final URL requestUrl, final DataMap dataMap) {
		super(dataMap);
		setRequestUrl(requestUrl);
	}

	public URL getRequestUrl() {
		return requestUrl;
	}

	@Override
	public void recycle() {
		super.recycle();
		setRequestUrl(REQUEST_URL_GOOGLE_CLIENT_LOGIN);
	}

	public Response send() {
		return ClientLogin.sendRequest(this);
	}

	public void setRequestUrl(final String requestUrl) {
		if (requestUrl == null || requestUrl.isEmpty()) {
			throw new RuntimeException("RequestUrl should not be empty!");
		}
		try {
			setRequestUrl(new URL(requestUrl));
		} catch (final Exception e) {
			throw new RuntimeException("RequestUrl should be a valid URL!", e);
		}
	}

	public void setRequestUrl(final URL requestUrl) {
		if (requestUrl == null) {
			throw new RuntimeException("RequestUrl should not be null!");
		}
		this.requestUrl = requestUrl;
	}
}
