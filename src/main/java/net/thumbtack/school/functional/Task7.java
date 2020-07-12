package net.thumbtack.school.functional;

import java.util.Date;
import java.util.function.Supplier;

public class Task7 {

    private static Supplier<Date> getCurrentDate = Date::new;

    public static void main(String[] args) {
        System.out.println(getCurrentDate.get());
    }
}
