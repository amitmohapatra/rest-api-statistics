package com.n26.common;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

import com.n26.exception.JsonParseException;

public class CustomTimeUtils {

	public static long getTimeDiffInSecs(LocalDateTime localDateTime) {
		long diffSecs = Duration.between(localDateTime, LocalDateTime.now()).getSeconds();
		return diffSecs;
	}

	public static boolean isFutureDate(String utcTime) {
		LocalDateTime convertedLocalTime = getLocalTime(utcTime);
		return convertedLocalTime.isAfter(LocalDateTime.now());
	}

	public static LocalDateTime getLocalTime(String utcTime) {
		try {
			Instant fromIso8601 = Instant.parse(utcTime);
			LocalDateTime convertedLocalTime = LocalDateTime.ofInstant(fromIso8601, ZoneId.systemDefault());
			return convertedLocalTime;
		} catch (DateTimeParseException ex) {
			throw new JsonParseException();
		}
	}

}
