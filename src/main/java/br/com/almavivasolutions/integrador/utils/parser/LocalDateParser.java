package br.com.almavivasolutions.integrador.utils.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateParser {
	
	public static LocalDate toLocalPattern(String date) throws DateTimeParseException {
		if(date.isEmpty()) {
			return null;
		}
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, dateFormatter);
	}

}
