package services;

import java.sql.Date;
import java.time.LocalDate;

public class MembershipFactory {
    public static Date calculateExpiryDate(int typeId) {
        LocalDate expiry;

        expiry = switch (typeId) {
            case 1, 3 -> LocalDate.now().plusMonths(1);
            case 2, 4 -> LocalDate.now().plusMonths(6);
            case 5    -> LocalDate.now().plusYears(1);
            default   -> LocalDate.now().plusDays(30);
        };

        return Date.valueOf(expiry);
    }
}