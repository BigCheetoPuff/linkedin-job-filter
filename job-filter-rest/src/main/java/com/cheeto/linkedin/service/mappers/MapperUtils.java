package com.cheeto.linkedin.service.mappers;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

public class MapperUtils {

    public static BigDecimal toBigDecimal(Double value) {
        return value == null ? null : BigDecimal.valueOf(value);
    }

    public static Double toDouble(BigDecimal value) {
        return value == null ? null : value.doubleValue();
    }
    
    public static void assertUpdatedSingleRow(int rows) {
        if (rows == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No rows updated");
        } else if (rows > 1) {
            throw new IllegalStateException(
                    "Expected 1 row affected but got " + rows);
        }
    }

    public static void assertUpdatedManyRows(int actual, int expected) {
        if (actual != expected) {
            throw new IllegalStateException(
                    "Expected" + expected + " row affected but got " + actual);
        }
    }
}
