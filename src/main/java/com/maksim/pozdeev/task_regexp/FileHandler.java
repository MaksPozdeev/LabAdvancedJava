package com.maksim.pozdeev.task_regexp;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FileHandler {
    private String regexFindPhoneNumber;
    private String regexOldNumberSeparator;

    FileHandler(String regexFindPhoneNumber, String regexOldNumberSeparator) {
        this.regexFindPhoneNumber = regexFindPhoneNumber;
        this.regexOldNumberSeparator = regexOldNumberSeparator;
    }

    String readTxtFile(String txtFilePath) {
        String resultStr = "";
        try (BufferedReader bufReader = new BufferedReader(new FileReader(new File(txtFilePath)))) {
            StringBuilder strBuilder = new StringBuilder();
            String tmpStr;
            while ((tmpStr = bufReader.readLine()) != null) {
                strBuilder.append(tmpStr);
                strBuilder.append(System.lineSeparator());
            }
            resultStr = strBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    String replaceNumbersInLine(String searchLine) {
        StringBuilder strBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile(regexFindPhoneNumber);
        Matcher matcher = pattern.matcher(searchLine);
        int startPosition = 0;
        String textStr;
        String newNumber;
        while (matcher.find()) {
            textStr = searchLine.substring(startPosition, matcher.start());
            strBuilder.append(textStr);
            startPosition = matcher.end();

            newNumber = matcher.group().replaceAll(regexOldNumberSeparator, "");
            strBuilder.append(newNumber);
        }
        return strBuilder.toString();
    }

    boolean saveNewString(String stringToSave, String newTxtFilePath) {
        boolean result = false;
        File newTxtFile = new File(newTxtFilePath);
        try (FileWriter fw = new FileWriter(newTxtFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            pw.print(stringToSave);
            result = true;
        } catch (IOException e) {
            System.err.println("ошибка открытия потока " + e);
        }
        return result;
    }
}
