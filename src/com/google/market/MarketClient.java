package com.google.market;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.google.play.proto.PlayStore.Request;
import com.google.play.proto.PlayStore.Response;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.tools.Base64;
import com.google.tools.Client;

public class MarketClient extends Client {

	public static boolean DEBUG = false;

	private final static int PROTOCOL_VERSION = 2;
	private final static String REQUEST_COOKIE_FIELD = "Cookie";

	/*
	 * private final static String REQUEST_USER_AGENT_FIELD = "User-Agent";
	 * private final static String REQUEST_ACCEPT_CHARSET_FIELD =
	 * "Accept-Charset"; private final static String REQUEST_ACCEPT_CHARSET =
	 * "utf-8;q=0.7,*;q=0.7";
	 */

	protected static void prepareConnection(final HttpURLConnection connection,
			final RequestInfo info) {
		prepareConnection(connection);
		connection.setRequestProperty(REQUEST_COOKIE_FIELD, info.getCookie());
		/*
		 * connection.setRequestProperty(REQUEST_USER_AGENT_FIELD,
		 * info.getUserAgent());
		 * connection.setRequestProperty(REQUEST_ACCEPT_CHARSET_FIELD,
		 * REQUEST_ACCEPT_CHARSET);
		 */
	}

	public static Response sendRequest(final Request request,
			final RequestInfo info) {
		try {
			return Response.parseFrom(sendString(
					"version="
							+ PROTOCOL_VERSION
							+ "&request="
							+ Base64.encodeBytes(request.toByteArray(),
									Base64.URL_SAFE), info));
		} catch (final InvalidProtocolBufferException e) {
			return null;
		} catch (final IOException e) {
			return null;
		}
	}

	private static byte[] sendString(final HttpURLConnection connection,
			final String string, final RequestInfo info) {
		prepareConnection(connection, info);
		writeData(connection, string);
		return readData(connection, isError(connection));
	}

	private static byte[] sendString(final String string, final RequestInfo info) {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) info.getRequestUrl()
					.openConnection();
			return sendString(connection, string, info);
		} catch (final IOException e) {
			if (DEBUG) {
				System.err.println("Could not open Connection!");
			}
			throw new RuntimeException("Could not open Connection!", e);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
