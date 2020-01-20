package com.exam.client.examclient.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Helper {

	private Helper() {
		
	}
	
	private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";
	
	private static final DateTimeFormatter newFormat = DateTimeFormatter.ofPattern(DATE_FORMAT_DEFAULT);
	
	public static String dateFormat(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(newFormat);
	}
	
	public static Date parseToDate(String date) throws ParseException {
		return new SimpleDateFormat(DATE_FORMAT_DEFAULT).parse(date);
	}
	
	public static String addYearsToDate(Date birthdayDate, int years) {
		
		return birthdayDate
				.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate()
				.plusYears(years).format(newFormat);
	}
}
