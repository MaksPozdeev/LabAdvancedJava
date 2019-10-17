package com.maksim.pozdeev.task_regexp;

import java.io.File;

public class Main {

    private static final String REGEX_FIND_PHONE_NUMBER = "\\+\\d\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}";
    private static final String REGEX_OLD_NUMBER_SEPARATOR = "\\+|\\(|\\)|\\s";

    public static void main(String[] args) {

        String txtFilePath1 = "src/main/resources/txtDocs/Text.txt";
        String txtFilePath2 = "src/main/resources/txtDocs/ContactInformations.txt";
        String newTxtFilePath = "src/main/resources/txtDocs/NewText.txt";

        FileHandler fileHandler = new FileHandler(REGEX_FIND_PHONE_NUMBER, REGEX_OLD_NUMBER_SEPARATOR);

        String strOld = fileHandler.readTxtFile(txtFilePath2);
//        System.out.println(strOld);
//        System.out.println("-------------------------------------------------------------------------");
        String strNew = fileHandler.replaceNumbersInLine(strOld);
//        System.out.println(strNew);
//        System.out.println("-------------------------------------------------------------------------");

        boolean b = fileHandler.saveNewString(strNew, newTxtFilePath);
        System.out.println(b);

    }
}
