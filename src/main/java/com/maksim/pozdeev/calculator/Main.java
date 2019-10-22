package com.maksim.pozdeev.calculator;

import com.maksim.pozdeev.calculator.classes.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String mathExpression = "13+(3.6*4.7)/3"; // 18.64
        System.out.println("Выражение: " + mathExpression);
        try {
            System.out.println(Calculator.calculate(mathExpression));
        } catch (Exception ex) {
            logger.error("Ошибка хз чего: " + ex);
        }
    }
}
