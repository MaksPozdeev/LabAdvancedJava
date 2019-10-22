package com.maksim.pozdeev.calculator.classes;

import com.maksim.pozdeev.calculator.Main;
import com.maksim.pozdeev.calculator.exceptions.NonexistentOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

class Operations {

    private static final Logger logger = LogManager.getLogger(Main.class);
    static final Map<Character, Integer> BASIK_OPERATIONS = new HashMap<>();

    private Operations() {
    }

    static {
        BASIK_OPERATIONS.put('+', 10);
        BASIK_OPERATIONS.put('-', 10);
        BASIK_OPERATIONS.put('*', 20);
        BASIK_OPERATIONS.put('/', 20);
        BASIK_OPERATIONS.put('^', 30);
    }

    static Integer getPrior(Character operation) {
        if (operation != null) {
            if (isOperation(operation)) {
                return BASIK_OPERATIONS.get(operation);
            } else {
                logger.error("Вызываемая операция: " + operation + " - не найдена");
                throw new NonexistentOperationException("Вызываемая операция: " + operation + " - не найдена");
            }
        } else {
            logger.error("Обращение к null: getPrior(null)");
            throw new NullPointerException("Обращение к null: getPrior(null)");
        }
    }

    static boolean isOperation(Character operation) {
        if (operation != null) {
            return BASIK_OPERATIONS.containsKey(operation);
        }
        return false;
    }
}
