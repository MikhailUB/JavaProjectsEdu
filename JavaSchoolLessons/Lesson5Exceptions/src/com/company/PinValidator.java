package com.company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;

public class PinValidator {
    private final int PIN_VALUE = 1111;
    private final int MAX_INVALID_COUNT = 3;
    private int invalidCount = 0;

    public void inputPin(int pin) throws InvalidPinException, AccountIsLockedException {
        if (pin != PIN_VALUE) {
            invalidCount++;
            if (invalidCount < MAX_INVALID_COUNT)
                throw new InvalidPinException("неверный PIN, после 3-х неверных попыток счет блокируется");

            invalidCount = 0;
            throw new AccountIsLockedException("Счет заблокирован до " + LocalDateTime.now().plusSeconds(5));
        }
        invalidCount = 0;
    }
}
