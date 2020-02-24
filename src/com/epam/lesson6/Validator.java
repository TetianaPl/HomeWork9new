package com.epam.lesson6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    static final int THIS_YEAR = 2020;
    static final float MIN_PERCENT = -100;

    public static void checkAuthor(String enteredAuthor) throws InvalidInputException {
        Pattern p = Pattern.compile("^[A-Za-zА-Яа-яІіЇїЄє\\s-]+$");
        Matcher m = p.matcher(enteredAuthor);
        if (enteredAuthor.trim().equals("")) {
            throw new InvalidInputException(0);
        }
        if (!m.matches()) {
            throw new InvalidInputException(1);
        }
    }

    public static void checkYear(int enteredYear) throws InvalidInputException {
        if (enteredYear < 0) {
            throw new InvalidInputException(2);
        } else if (enteredYear > THIS_YEAR) {
            throw new InvalidInputException(3);
        }
    }

    public static void checkPercent(float enteredPercent) throws InvalidInputException {
        if (enteredPercent < MIN_PERCENT) {
            throw new InvalidInputException(4);
        }
    }

    public static void checkFile(String fileName) throws InvalidInputException {
        Pattern pattern = Pattern.compile("(.+)?[><\\|\\?*/:\\\\\"](.+)?");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            throw new InvalidInputException(6);
        }
    }
}

