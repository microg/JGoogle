package com.google.tools;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

public class Client {
	public static boolean DEBUG = false;

	protected static final String REQUEST_CONTENT_TYPE = "application/x-www-form-urlencoded";
	protected static final String REQUEST_CONTENT_TYPE_FIELD = "Content-Type";
	protected static final String REQUEST_METHOD = "POST";

	protected static boolean isError(final HttpURLConnection connection) {
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

	protected static void prepareConnection(final HttpURLConnection connection) {
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

	protected static byte[] readData(final HttpURLConnection connection,
			final boolean error) {
		try {
			InputStream is = null;
			if (error) {
				is = connection.getErrorStream();
			} else {
				is = connection.getInputStream();
			}
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();
			final byte[] buff = new byte[1024];
			while (true) {
				final int nb = is.read(buff);
				if (nb < 0) {
					break;
				}
				bos.write(buff, 0, nb);
			}
			is.close();
			return bos.toByteArray();
		} catch (final IOException e) {
			if (DEBUG) {
				System.err.println("Could not read data!");
			}
			throw new RuntimeException("Could not read data", e);
		}
	}

	protected static void writeData(final HttpURLConnection connection,
			final String string) {
		try {
			final DataOutputStream stream = new DataOutputStream(
					connection.getOutputStream());
			stream.writeBytes(string);
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
