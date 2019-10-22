package com.maksim.pozdeev.calculator.classes;

import com.maksim.pozdeev.calculator.exceptions.NotCorrectExpressionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Calculator {
    /*
     * Итак:
     * Удаляем все пробелы и повторяющиеся операции
     * Проверяем на валиность
     * Получаем RPN
     * Обрабатываем RPN (считаем результат)
     * Возвращаем результат
     * */
    private static final Logger logger = LogManager.getLogger();

    public static String calculate(String inputExp) throws Exception {
        String result;
        result = trimInputExpression(inputExp);
        if (checkValid(result)) {
            result = PostfixRecord.strToRPN(result);
            logger.info("Валидация прошла успешно");
        } else {
            logger.error("Чё-то пошло не так при валидации!!!");
            throw new NotCorrectExpressionException("Чё-то пошло не так при валидации!!!");
        }
        double dResult = calculateRPN(result);
        return Double.toString(dResult);
    }


    //  Удаляем все пробелы в выражении
    private static String trimInputExpression(String inputExp) {
        String result;
        result = inputExp.
                replaceAll("\\s", "").
                replaceAll("\\.+", ".").
                replaceAll("-+", "-").
                replaceAll("\\++", "+").
                replaceAll("\\*+", "*").
                replaceAll(":+", ":").
                replaceAll("\\^+", "^").
                replaceAll("\\(\\)", "");
        return result;
    }

    //  Проверяем строку на допустимые символы
    private static boolean checkValid(String mathExpression) throws NotCorrectExpressionException {

        if (mathExpression == null) {
            throw new NullPointerException("Вызов с параметром null: checkValid(null)");
        }

        Set<Character> validSetCharacters = new HashSet<>(Operations.BASIK_OPERATIONS.keySet());
        char[] arrCh = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '.'};
        for (char ch : arrCh) {
            validSetCharacters.add(ch);
        }
        int leftBrackets = 0;
        for (int i = 0; i < mathExpression.length(); i++) {
            if (validSetCharacters.contains(mathExpression.charAt(i))) {
                if (mathExpression.charAt(i) == '(') {
                    leftBrackets++;
                }
                if (mathExpression.charAt(i) == ')') {
                    leftBrackets--;
                    if (leftBrackets < 0) {
                        throw new NotCorrectExpressionException("Найдено некоррекно поставленная закрывающая скобка: )");
                    }
                }
            } else {
                throw new NotCorrectExpressionException("Найден некоррекный символ в выражении: " + mathExpression.charAt(i));
            }
        }
        if (leftBrackets != 0) {
            throw new NotCorrectExpressionException("Количество ( и ) не одинаково");
        }
        return true;
    }


    //    Считает выражение, записанное в обратной польской нотации
    private static double calculateRPN(String stringInputRPN) throws Exception {
        double doubleTmp1;
        double doubleTmp2;
        String strTmp;
        Deque<Double> stack = new ArrayDeque<>();
        StringTokenizer strTokenizer = new StringTokenizer(stringInputRPN);

        while (strTokenizer.hasMoreTokens()) {
            try {
                strTmp = strTokenizer.nextToken().trim();
                if (strTmp.length() == 1 && Operations.isOperation(strTmp.charAt(0))) {
                    if (stack.size() < 2) {
                        throw new Exception("Неверное количество данных в стеке для операции " + strTmp);
                    }
                    doubleTmp2 = stack.pop();
                    doubleTmp1 = stack.pop();
                    switch (strTmp.charAt(0)) {
                        case '+':
                            doubleTmp1 += doubleTmp2;
                            break;
                        case '-':
                            doubleTmp1 -= doubleTmp2;
                            break;
                        case '*':
                            doubleTmp1 *= doubleTmp2;
                            break;
                        case '/':
                            doubleTmp1 /= doubleTmp2;
                            break;
                        case '^':
                            doubleTmp1 = Math.pow(doubleTmp1, doubleTmp2);
                            break;
                        default: {
                            logger.error("Недопустимая операция " + strTmp);
                            throw new IllegalArgumentException("Недопустимая операция " + strTmp);
                        }
                    }
                    stack.push(doubleTmp1);
                } else {
                    doubleTmp1 = Double.parseDouble(strTmp);
                    stack.push(doubleTmp1);
                }
            } catch (Exception e) {
                logger.error("Недопустимый символ в выражении");
                throw new NotCorrectExpressionException("Недопустимый символ в выражении");
            }
        }

        if (stack.size() > 1) {
            logger.error("Количество операторов не соответствует количеству операндов");
            throw new NotCorrectExpressionException("Количество операторов не соответствует количеству операндов");
        }

        return stack.pop();
    }
}
