package validation;

import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import validation.annotations.FXRequired;
import validation.exceptions.ValidationException;

import java.lang.annotation.Annotation;

public class RequiredValidator extends FXAbstractValidator<TextInputControl, FXRequired> {

    public RequiredValidator() {
        super();
        this.eventTypes.add(KeyEvent.KEY_RELEASED);
    }

    public RequiredValidator(TextInputControl control, FXRequired annotation) {
        super(control, annotation);
        this.eventTypes.add(KeyEvent.KEY_RELEASED);
    }

    @Override
    public void validate(TextInputControl control, FXRequired annotation) throws ValidationException {
        if (control.isDisabled()) {
            this.isValid.set(true);
            return;
        }
        if (!control.isVisible()) {
            this.isValid.set(true);
            return;
        }

        boolean valid = control.getText().length() > 0;
        this.isValid.set(valid);

        if (!valid) {
            throw new ValidationException(annotation.message());
        }
    }
}
