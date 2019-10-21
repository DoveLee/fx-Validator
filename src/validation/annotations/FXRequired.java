package validation.annotations;

import validation.FXAbstractValidator;
import validation.RequiredValidator;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FXRequired {
    public Class<? extends FXAbstractValidator> validation() default RequiredValidator.class;

    public boolean required() default false;

    public String message() default "This field must not be empty!";
}
