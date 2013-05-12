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

	protected static final String REQUEST_CONTENT_TYPE = "application/x-www-form-urlencoded";
	protected static final String REQUEST_CONTENT_TYPE_FIELD = "Content-Type";
	protected static final String REQUEST_USER_AGENT_FIELD = "User-Agent";
	protected static final String REQUEST_ACCEPT_FIELD = "Accept";
	protected static final String REQUEST_ACCEPT_ENCODING_FIELD = "Accept-Encoding";
	protected static final String REQUEST_METHOD = "POST";
	public static boolean DEBUG = false;

	protected static boolean isError(final HttpURLConnection connection) {
		try {
			if (connection.getResponseCode() != 200) {
				if (DEBUG) {
					System.err
						  .println("Error: " + connection.getResponseCode() + " " + connection.getResponseMessage());
				}
				return true;
			}
		} catch (final IOException e) {
			return true;
		}
		return false;
	}

	protected static void prepareConnection(final HttpURLConnection connection, final boolean gzip) {
		try {
			connection.setRequestMethod(REQUEST_METHOD);
		} catch (final ProtocolException e) {
			if (DEBUG) {
				System.err.println("Could not enable POST-Request");
			}
			throw new RuntimeException("Could not enable POST-Request", e);
		}
		connection.setRequestProperty(REQUEST_CONTENT_TYPE_FIELD, REQUEST_CONTENT_TYPE);
		connection.setRequestProperty(REQUEST_ACCEPT_ENCODING_FIELD, gzip ? "gzip" : "identity");
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);
	}

	protected static byte[] readData(final HttpURLConnection connection, final boolean gunzip) {
		return readData(connection, isError(connection), gunzip);
	}

	protected static byte[] readData(final HttpURLConnection connection, final boolean error, final boolean gunzip) {
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
			if (is == null) {
				if (DEBUG) {
					System.out.println("InputStream is null ?!");
				}
			}
			return readStreamToEnd(is);
		} catch (final Exception e) {
			if (DEBUG) {
				System.err.println("Could not read data!");
			}
			throw new RuntimeException("Could not read data", e);
		}
	}

	protected static void setUserAgent(HttpURLConnection connection, RequestInfo info) {
		connection.setRequestProperty(REQUEST_USER_AGENT_FIELD, info.get(RequestInfo.KEY_HTTP_USER_AGENT));
	}

	protected static void setUserAgent(HttpURLConnection connection, String userAgent) {
		connection.setRequestProperty(REQUEST_USER_AGENT_FIELD, userAgent);
	}

	protected static byte[] readStreamToEnd(final InputStream is) throws IOException {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		if (is != null) {
			final byte[] buff = new byte[1024];
			while (true) {
				final int nb = is.read(buff);
				if (nb < 0) {
					break;
				}
				bos.write(buff, 0, nb);
			}
			is.close();
		}
		return bos.toByteArray();
	}

	protected static void beforeRequest(final HttpURLConnection connection) {
		if (Client.DEBUG) {
			for (String key : connection.getRequestProperties().keySet()) {
				System.out.println("Connection Header: " + key + " = " + connection.getRequestProperty(key));
			}
		}
	}

	protected static void writeData(final HttpURLConnection connection, final byte[] bytes, final boolean gzip) {
		beforeRequest(connection);
		try {
			OutputStream os = connection.getOutputStream();
			if (gzip) {
				os = new GZIPOutputStream(os);
			}
			/*
			 * final DataOutputStream stream = new DataOutputStream(os);
			 * stream.writeBytes(string);
			 */
			os.write(bytes);
			os.flush();
			os.close();
		} catch (final IOException e) {
			if (DEBUG) {
				System.err.println("Could not send data!");
				e.printStackTrace();
			}
			throw new RuntimeException("Could not send data", e);
		}
	}

	protected static void writeData(final HttpURLConnection connection, final String string, final boolean gzip) {
		if (Client.DEBUG) {
			System.out.println("Sending" + (gzip ? "(gzipped)" : "") + ": " + string);
		}
		writeData(connection, string.getBytes(), gzip);
	}
}
