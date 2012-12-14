package com.google.market;

import java.net.URL;

public class RequestInfo {

	private final String authToken;

	private URL requestUrl;

	private final String service;

	public RequestInfo(final String authToken, final String requestUrl,
			final String service) {
		this.authToken = authToken;
		this.service = service;
		setRequestUrl(requestUrl);
	}

	public String getAuthToken() {
		return authToken;
	}

	public String getCookie() {
		return service + "=" + authToken;
	}

	public URL getRequestUrl() {
		return requestUrl;
	}

	public String getService() {
		return service;
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
