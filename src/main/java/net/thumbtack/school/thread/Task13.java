package net.thumbtack.school.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task13 {
    public static void main(String[] args) {
        Formatter formatter = new Formatter();
        Date date = new Date(System.currentTimeMillis());

        new FormatterThread(formatter, date).start();
        new FormatterThread(formatter, date).start();
        new FormatterThread(formatter, date).start();
        new FormatterThread(formatter, date).start();
        new FormatterThread(formatter, date).start();

    }

}

class Formatter {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public void format(Date date) {
        System.out.println(simpleDateFormat.format(date));
    }
}

class FormatterThread extends Thread {
    private ThreadLocal<Formatter> threadLocal = new ThreadLocal<>();
    private Formatter formatter;
    private Date date;

    public FormatterThread(Formatter formatter, Date date) {
        this.formatter = formatter;
        this.date = date;
    }

    @Override
    public void run() {
        threadLocal.set(formatter);
        Formatter formatter = threadLocal.get();
        formatter.format(date);
    }
}
