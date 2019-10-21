package validation;

import javafx.scene.control.Control;
import validation.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnnotationHandler {
    //实现注解的处理
    public void handle(Object controller,Field field,Annotation validation){
        String name = null;
        try{
            name = this.getClassName(validation);
            if (name == null){
                return;
            }
            Control control = (Control) field.get(controller);
            if (control==null){
                throw new NullPointerException("没有找到: "+field.getName());
            }
        }catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(AnnotationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //设置某个控件的样式
    public static void mark(Control control,boolean valid,String errormessage){
        if (valid){
            if (control.getStyleClass().contains("aefx-validation-error")){
                control.getStyleClass().remove("aefx-validation-error");
            }else{
                control.getStyleClass().add("aefx-validation-error");
            }
        }
    }
    //获得某个注解类的名字
    private String getClassName(Annotation validation) throws IllegalAccessError{
        String name = null;
        if (validation instanceof FXRequired){
            FXRequired fxRequired = (FXRequired) validation;
            try {
                Method method = fxRequired.getClass().getMethod("validation");
                Class result = (Class) method.invoke(fxRequired,null);
                name = result.getName();
            } catch (NoSuchMethodException | SecurityException | InvocationTargetException |IllegalAccessException  ex) {
                Logger.getLogger(AnnotationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (validation instanceof FXString) {
            FXString fXString = (FXString) validation;
            try {
                Method method = fXString.getClass().getMethod("validation");
                Class result = (Class) method.invoke(fXString, (Object[]) null);
                name = result.getName();
            } catch (NoSuchMethodException | SecurityException | InvocationTargetException |IllegalAccessException  ex) {
                Logger.getLogger(AnnotationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (validation instanceof FXNotNull) {
            FXNotNull fXNotNull = (FXNotNull) validation;
            try {
                Method method = fXNotNull.getClass().getMethod("validation");
                Class result = (Class) method.invoke(fXNotNull, (Object[]) null);
                name = result.getName();
            } catch (NoSuchMethodException | SecurityException | InvocationTargetException |IllegalAccessException  ex) {
                Logger.getLogger(AnnotationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (validation instanceof FXNumber) {
            FXNumber fXNumber = (FXNumber) validation;
            try {
                Method method = fXNumber.getClass().getMethod("validation");
                Class result = (Class) method.invoke(fXNumber, (Object[]) null);
                name = result.getName();
            } catch (NoSuchMethodException | SecurityException | InvocationTargetException |IllegalAccessException  ex) {
                Logger.getLogger(AnnotationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (validation instanceof FXValidation) {
            FXValidation fXValidation = (FXValidation) validation;
            try {
                Method method = fXValidation.getClass().getMethod("validation");
                Class result = (Class) method.invoke(fXValidation, (Object[]) null);
                name = result.getName();
            } catch (NoSuchMethodException | SecurityException | InvocationTargetException |IllegalAccessException  ex) {
                Logger.getLogger(AnnotationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return name;
    }


}
