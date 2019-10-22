package validation;

import javafx.scene.control.ComboBox;
import validation.annotations.FXNotNull;

import java.lang.reflect.Field;

public class AnnotionHandle {
    public static void annotionHanle(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        for (Field f:fields)
            if (f.isAnnotationPresent(FXNotNull.class)) {
                System.out.println(f);
            }
    }
}
