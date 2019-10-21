package validation.annotations;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Control;
import validation.FXAbstractValidator;
import validation.NotNullValidator;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FXNotNull {

    public  Class<? extends FXAbstractValidator> validation() default NotNullValidator.class;

    public Class<? extends Control>[] applicableFor() default {ChoiceBox.class, ComboBoxBase.class};

    public String message() default "This field must not be null";
}
