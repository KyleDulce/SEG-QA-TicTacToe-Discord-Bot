package me.segQaGroupTen.discordBot.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class AnnotationReaderUtil {

    public static List<Field> getFieldsFromClassWithAnnotation(Class<? extends Annotation> annotationType,
                                                               Class<?> classToCheck) {
        LinkedList<Field> fields = new LinkedList<>();
        for(Field field : classToCheck.getDeclaredFields()) {
            if(field.isAnnotationPresent(annotationType)) {
                fields.add(field);
            }
        }
        return fields;
    }
}
