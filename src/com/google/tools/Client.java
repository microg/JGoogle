package com.google.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

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
			final boolean error, final boolean gunzip) {
		try {
			InputStream is = null;
			if (error) {
				is = connection.getErrorStream();
			} else {
				is = connection.getInputStream();
				if (gunzip) {
					is = new GZIPInputStream(is);
				}
			}
			return readStreamToEnd(is);
		} catch (final IOException e) {
			if (DEBUG) {
				System.err.println("Could not read data!");
			}
			throw new RuntimeException("Could not read data", e);
		}
	}

	protected static byte[] readStreamToEnd(InputStream is) throws IOException {
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
	}

	protected static void writeData(final HttpURLConnection connection,
			final String string, boolean gzip) {
		try {
			OutputStream os = connection.getOutputStream();
			if (gzip) {
				os = new GZIPOutputStream(os);
			}
			/*
			 * final DataOutputStream stream = new DataOutputStream(os);
			 * stream.writeBytes(string);
			 */
			os.write(string.getBytes());
			os.flush();
			os.close();
		} catch (final IOException e) {
			if (DEBUG) {
				System.err.println("Could not send data!");
			}
			throw new RuntimeException("Could not send data", e);
		}
	}
}
