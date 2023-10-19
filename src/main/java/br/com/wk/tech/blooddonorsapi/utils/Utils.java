package br.com.wk.tech.blooddonorsapi.utils;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public class Utils {

    public LocalDateTime convertStringDateToLocalDateTime(String date) {
        var parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, parser).atStartOfDay();
    }
}
