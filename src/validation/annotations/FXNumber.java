package validation.annotations;

import validation.FXAbstractValidator;
import validation.NumberValidator;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FXNumber  {
    public Class<? extends FXAbstractValidator> validation() default NumberValidator.class;

    public double min() default Double.MIN_VALUE;

    public double max() default Double.MAX_VALUE;

    public String message() default "This field must be a number!";
}
