package com.maksim.pozdeev.reflection_and_annotations.classes;

import com.maksim.pozdeev.reflection_and_annotations.annotations.Secured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class UseAnnotation {

    private static final Logger logger = LoggerFactory.getLogger(UseAnnotation.class);

    public static void main(String[] args) {
        String className = "com.maksim.pozdeev.reflection_and_annotations.classes.Entity";
        classProcessing(className);
    }

    static void classProcessing(String className) {
        try {
            Class<?> myAnnotatedClass = Class.forName(className);
            Method[] methodsPublic = myAnnotatedClass.getMethods();
            Method[] methodsPrivate = myAnnotatedClass.getDeclaredMethods();
            Method[] methodsAll = Stream.concat(Arrays.stream(methodsPublic), Arrays.stream(methodsPrivate)).toArray(Method[]::new);
            for (Method method : methodsAll) {
                System.out.print("Method name: " + method.getName() + ". ");
                if (method.isAnnotationPresent(Secured.class)) {
                    System.out.print("Аннотация @Secured найдена.  ");
                    Secured secured = method.getAnnotation(Secured.class);
                    System.out.println("Параметры:  id=" + secured.id() + " style= " + secured.style());
                } else {
                    System.out.println("Аннотация @Secured НЕ найдена");
                    logger.info("Аннотация @Secured НЕ найдена");
                }
            }
        } catch (ClassNotFoundException ex) {
//            System.err.println("1й catch: " + ex.getMessage());
            logger.error("Error message", ex);
        }
    }
}
