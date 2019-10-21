package validation;

import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import validation.annotations.FXNumber;
import validation.exceptions.ValidationException;

public class NumberValidator extends FXAbstractValidator<TextInputControl, FXNumber> {

    public NumberValidator() {
        super();
        this.eventTypes.add(KeyEvent.KEY_RELEASED);
    }
    @Override
    public void validate(TextInputControl control, FXNumber annotation) throws ValidationException {
        if (control.isDisabled()) {
            this.isValid.set(true);
            return;
        }
        if (!control.isVisible()) {
            this.isValid.set(true);
            return;
        }

        boolean valid = false;

        try {
            Number n = Double.parseDouble(control.getText());

            valid = true;
            if (annotation.min() != Double.MIN_VALUE) {
                valid = valid && (n.doubleValue() >= annotation.min());
            }
            if (annotation.max() != Double.MAX_VALUE) {
                valid = valid && (n.doubleValue() <= annotation.max());
            }
        } catch (NumberFormatException e) {
            // nothing to do, validator remains invalid.
        }
        this.isValid.set(valid);

        if (!valid) {
            throw new ValidationException(annotation.message());
        }
    }
}
