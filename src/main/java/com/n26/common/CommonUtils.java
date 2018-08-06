package com.n26.common;

import java.math.BigDecimal;

import com.n26.exception.JsonParseException;

public class CommonUtils {

	public static BigDecimal convertoToBigDecimal(String amount) {
		try {
			return new BigDecimal(amount);
		} catch (Exception ex) {
			throw new JsonParseException();
		}
	}
}
