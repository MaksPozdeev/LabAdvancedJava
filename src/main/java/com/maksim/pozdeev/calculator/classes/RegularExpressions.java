package com.maksim.pozdeev.calculator.classes;

public class RegularExpressions {
    static final String DELIMITER_REGEX = "([\\+\\-\\*\\/\\^\\(\\)]|$)";

    public static final String NUMBERS_REG_EX = "(\\d+([\\.]\\d+)?)";
    public static final String OPERATORS_REG_EX = "[\\+\\-\\*\\/^]";


    private RegularExpressions() {
    }
}
