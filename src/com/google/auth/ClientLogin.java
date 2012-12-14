package com.google.auth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class ClientLogin {
	public static boolean DEBUG = false;

	private static final String REQUEST_METHOD = "POST";
	private static final String REQUEST_CONTENT_TYPE = "application/x-www-form-urlencoded";
	private static final String REQUEST_CONTENT_TYPE_FIELD = "Content-Type";

	private static boolean isError(final HttpURLConnection connection) {
		try {
			if (connection.getResponseCode() != 200) {
				if (DEBUG) {
					System.err.println("Error: " + connection.getResponseCode()
							+ " " + connection.getResponseMessage());
				}
				return true;
			}
		} catch (final IOException e) {
			return true;
		}
		return false;
	}

	private static void prepareConnection(final HttpURLConnection connection) {
		try {
			connection.setRequestMethod(REQUEST_METHOD);
		} catch (final ProtocolException e) {
			if (DEBUG) {
				System.err.println("Could not enable POST-Request");
			}
			throw new RuntimeException("Could not enable POST-Request", e);
		}
		connection.setRequestProperty(REQUEST_CONTENT_TYPE_FIELD,
				REQUEST_CONTENT_TYPE);
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);
	}

	private static String readData(final HttpURLConnection connection,
			final boolean error) {
		try {
			InputStream is = null;
			if (error) {
				is = connection.getErrorStream();
			} else {
				is = connection.getInputStream();
			}
			final BufferedReader rd = new BufferedReader(new InputStreamReader(
					is));
			String line;
			final StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				if (DEBUG) {
					System.out.println(line);
				}
				response.append("\n");
			}
			rd.close();
			return response.substring(0, response.length() - 1);
		} catch (final IOException e) {
			if (DEBUG) {
				System.err.println("Could not read data!");
			}
			throw new RuntimeException("Could not read data", e);
		}
	}

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
		return readData(connection, isError(connection));
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

	private static void writeData(final HttpURLConnection connection,
			final String dataString) {
		try {
			final DataOutputStream stream = new DataOutputStream(
					connection.getOutputStream());
			stream.writeBytes(dataString);
			stream.flush();
			stream.close();
		} catch (final IOException e) {
			if (DEBUG) {
				System.err.println("Could not send data!");
			}
			throw new RuntimeException("Could not send data", e);
		}
	}
}
