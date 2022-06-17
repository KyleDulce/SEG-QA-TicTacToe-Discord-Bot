package me.segQaGroupTen.discordBot.util;

import me.segQaGroupTen.discordBot.config.Value;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotationReaderUtilTests {
    @Test
    public void testGetFieldsFromClassWithAnnotation_success() {
        List<Field> actual = AnnotationReaderUtil.getFieldsFromClassWithAnnotation(Value.class, AnnotationReaderTestClass1.class);

        assertEquals(1, actual.size());
        assertEquals("hello", actual.get(0).getName());
    }

    @Test
    public void testGetFieldsFromClassWithAnnotation_noFieldFound_success() {
        List<Field> actual = AnnotationReaderUtil.getFieldsFromClassWithAnnotation(Override.class, AnnotationReaderTestClass1.class);

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetFieldsFromClassWithAnnotation_noFields_success() {
        List<Field> actual = AnnotationReaderUtil.getFieldsFromClassWithAnnotation(Value.class, AnnotationReaderTestClass2.class);

        assertEquals(0, actual.size());
    }

    public static class AnnotationReaderTestClass1 {
        @Value("ya")
        public int hello;
    }

    public static class AnnotationReaderTestClass2 {
    }
}
