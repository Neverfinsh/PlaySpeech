package com.gzhc365.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtils {

	public static String formatToYuan(Integer money) {
		if (money == null) {
			return "0.00";
		}
		DecimalFormat format = new DecimalFormat("0.00");
		return format.format(BigDecimal.valueOf(money).divide(BigDecimal.valueOf(100)).doubleValue());
	}
}
