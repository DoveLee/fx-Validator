package validation.annotations;

import validation.FXAbstractValidator;
import validation.NumberValidator;
import validation.StringValidator;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FXString {
    public Class<? extends FXAbstractValidator> validation() default StringValidator.class;

    public int minLength() default 0;

    public int maxLength() default 0;

    public String pattern() default "";

    public String messageMinLength() default "Please enter at least %d characters.";

    public String messageMaxLength() default "Please enter max. %d characters.";

    public String messagePattern() default "Please enter correct Input.";
}
