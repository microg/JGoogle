package com.google.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.EnumMap;

public class DataMap extends EnumMap<DataField, String> implements Constants {

	private static final long serialVersionUID = 7378197191935938976L;
	public static boolean DEBUG = false;

	public static DataMap fromUrlDataString(final String string) {
		final String[] parts = string.split("&");
		final DataMap dataMap = new DataMap();
		for (final String part : parts) {
			final int split = part.indexOf("=");
			if (split > 0) {
				try {
					final String key = URLDecoder.decode(
							part.substring(0, split), CHARENC_UTF8);
					final String value = URLDecoder.decode(
							part.substring(split + 1), CHARENC_UTF8);
					dataMap.put(DataField.fromInternalName(key), value);
				} catch (final Throwable t) {
					if (DEBUG) {
						System.err.println(part);
					}
				}
			} else {
				if (DEBUG) {
					System.err.println(part);
				}
			}
		}
		return dataMap;
	}

	public DataMap() {
		super(DataField.class);
	}

	public DataMap(final EnumMap<DataField, String> clone) {
		super(clone);
	}

	@Override
	public DataMap clone() {
		return new DataMap(super.clone());
	}

	public String getUrlDataString() {
		final StringBuilder builder = new StringBuilder();
		for (final DataField field : keySet()) {
			String value = get(field);
			if (value != null && !value.isEmpty()) {
				try {
					final String key = URLEncoder.encode(
							field.toInternalName(), CHARENC_UTF8);
					value = URLEncoder.encode(value, CHARENC_UTF8);
					builder.append(key).append("=").append(value).append("&");
				} catch (final UnsupportedEncodingException e) {
					// Ignore and go next
				}
			}
		}
		return builder.substring(0, builder.length() - 1);
	}

}
