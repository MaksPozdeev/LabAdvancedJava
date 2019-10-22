package com.maksim.pozdeev.calculator.classes;

import com.maksim.pozdeev.calculator.exceptions.NotCorrectExpressionException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PostfixRecord {
    /* Преобразование строки в обратную польскую нотацию (RPN)
     * На вход должно подаваться обработанная строка:
     * - без символов табуляции
     * - проверенная на валидность */

    static String strToRPN(String stringInput) throws Exception {

        List<String> stringTokensList = new ArrayList<>();
        Pattern pattern = Pattern.compile(RegularExpressions.DELIMITER_REGEX);
        Matcher matcher = pattern.matcher(stringInput);
        int startPos = 0;
        int endPos;
        String strTmp;
        while (matcher.find()) {
            endPos = matcher.start();
//          Получаем всё до оператора и добавляем в list
            strTmp = stringInput.substring(startPos, endPos);
            if (!strTmp.isEmpty()) {
                stringTokensList.add(strTmp);
            }
            if (!matcher.group().isEmpty()) {
                stringTokensList.add(matcher.group());
            }
            startPos = matcher.end();
        }

        StringBuilder sbStackImitation = new StringBuilder();
        StringBuilder sbOut = new StringBuilder();

        char chrIn;
        char chrTmp;

        for (int i = 0; i < stringInput.length(); i++) {
            chrIn = stringInput.charAt(i);
//            если это операция
            if (Operations.isOperation(chrIn)) {
                while (sbStackImitation.length() > 0) {
                    chrTmp = sbStackImitation.substring(sbStackImitation.length() - 1).charAt(0);

                    if (Operations.isOperation(chrTmp) && (Operations.getPrior(chrIn) <= Operations.getPrior(chrTmp))) {
                        sbOut.append(" ").append(chrTmp).append(" ");
                        sbStackImitation.setLength(sbStackImitation.length() - 1);
                    } else {
                        sbOut.append(" ");
                        break;
                    }
                }
                sbOut.append(" ");
                sbStackImitation.append(chrIn);
            } else if ('(' == chrIn) {
                sbStackImitation.append(chrIn);
            } else if (')' == chrIn) {
                chrTmp = sbStackImitation.substring(sbStackImitation.length() - 1).charAt(0);
                while ('(' != chrTmp) {
                    if (sbStackImitation.length() < 1) {
                        throw new NotCorrectExpressionException("Ещё какая-то ошибка со скобками при их разборе. Проверьте выражения.");
                    }
                    sbOut.append(" ").append(chrTmp);
                    sbStackImitation.setLength(sbStackImitation.length() - 1);
                    chrTmp = sbStackImitation.substring(sbStackImitation.length() - 1).charAt(0);
                }
                sbStackImitation.setLength(sbStackImitation.length() - 1);
            } else {
                // Если символ не оператор - добавляем в выходную последовательность
                sbOut.append(chrIn);
            }
        }

        // Если в стеке остались операторы, добавляем их в входную строку
        while (sbStackImitation.length() > 0) {
            sbOut.append(" ").append(sbStackImitation.substring(sbStackImitation.length() - 1));
            sbStackImitation.setLength(sbStackImitation.length() - 1);
        }
        return sbOut.toString();
    }
}
