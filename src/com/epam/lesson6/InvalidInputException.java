package com.epam.lesson6;

public class InvalidInputException extends Exception {
    int issue;

    public InvalidInputException(int issue) {
        this.issue = issue;
    }

    @Override
    public String getMessage() {
        switch (issue) {
            case 0:
                return "\nThe entered data contains only blanks! Search can't be executed.";
            case 1:
                return "\nThe author name should contain only letters! Search can't be executed.";
            case 2:
                return "\nThe year should be positive! Search can't be executed.";
            case 3:
                return "\nThe year can't be more then " + Validator.THIS_YEAR + "! Search can't be executed.";
            case 4:
                return "\nThe percent can't be less then " + Validator.MIN_PERCENT + "! Revaluation can't be executed.";
            case 5:
                return "\nNo books found";
            default:
                return super.getMessage();
        }
    }

}
