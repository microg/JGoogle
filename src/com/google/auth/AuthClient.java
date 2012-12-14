package com.google.auth;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.tools.Client;

public class AuthClient extends Client {
	public static boolean DEBUG = false;

	private static DataMap sendData(final URL url, final DataMapReader data) {
		return DataMap.fromUrlDataString(sendString(url, data.toString()));

	}

	public static Response sendRequest(final Request request) {
		return new Response(sendData(request.getRequestUrl(), request));
	}

	private static String sendString(final HttpURLConnection connection,
			final String dataString) {
		prepareConnection(connection);
		writeData(connection, dataString);
		return new String(readData(connection, isError(connection)));
	}

	private static String sendString(final URL url, final String dataString) {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
			return sendString(connection, dataString);
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
