package validation;

import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import validation.annotations.FXString;
import validation.exceptions.ValidationException;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

public class StringValidator extends FXAbstractValidator<TextInputControl, FXString> {
    private Pattern pattern;

    public StringValidator() {
        super();
        this.pattern = null;
        this.eventTypes.add(KeyEvent.KEY_RELEASED);
    }

    public StringValidator(TextInputControl control, FXString annotation) {
        super(control, annotation);
        this.pattern = null;
        this.eventTypes.add(KeyEvent.KEY_RELEASED);

        if (annotation.pattern().length() > 0) {
            this.createRegex(annotation);
        }
    }

    private void createRegex(FXString annotation) {
        this.pattern = Pattern.compile(annotation.pattern());
    }
    @Override
    public void validate(TextInputControl control, FXString annotation) throws ValidationException {
        if (control.isDisabled()) {
            this.isValid.set(true);
            return;
        }
        if (!control.isVisible()) {
            this.isValid.set(true);
            return;
        }

        boolean valid = true;

        // 1. minLength?
        if (annotation.minLength() > 0) {
            valid = valid && control.getText().length() >= annotation.minLength();

            this.isValid.set(valid);
            if (!valid) {
                String msg = annotation.messageMinLength();
                if (annotation.messageMinLength().contains("%d")) {
                    msg = String.format(annotation.messageMinLength(), annotation.minLength());
                }
                throw new ValidationException(msg);
            }
        }

        // 2. maxLength?
        if (annotation.maxLength() > 0) {
            valid = valid && control.getText().length() <= annotation.maxLength();
            this.isValid.set(valid);
            if (!valid) {
                String msg = annotation.messageMaxLength();
                if (annotation.messageMaxLength().contains("%d")) {
                    msg = String.format(annotation.messageMaxLength(), annotation.maxLength());
                }
                throw new ValidationException(msg);
            }
        }

        // 3. pattern?
        if (annotation.pattern().length() > 0) {
            // check for necessary lazy initialization:
            if (this.pattern == null) {
                this.createRegex(annotation);
            }

            valid = valid && this.pattern.matcher(control.getText()).matches();
            this.isValid.set(valid);
            if (!valid) {
                String msg = annotation.messagePattern();
                throw new ValidationException(msg);
            }
        }

    }
}
