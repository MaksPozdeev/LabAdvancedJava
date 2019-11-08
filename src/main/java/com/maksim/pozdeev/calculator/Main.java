package com.maksim.pozdeev.calculator;

import com.maksim.pozdeev.calculator.classes.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
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
