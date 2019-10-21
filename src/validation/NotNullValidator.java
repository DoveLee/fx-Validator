package validation;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Control;
import validation.annotations.FXNotNull;
import validation.exceptions.FXValidatorException;
import validation.exceptions.ValidationException;

public class NotNullValidator extends FXAbstractValidator<Control, FXNotNull> {

    public NotNullValidator(){
        super();
    }

    public NotNullValidator(Control control,FXNotNull annotation){
        super(control,annotation);
        Class<? extends Control>[] applicableFor = annotation.applicableFor();
        boolean isApplicable = false;
        for (Class<? extends Control> applicableFor1 : applicableFor){
            if (applicableFor1.isInstance(control)){
                isApplicable = true;
                break;
            }
        }
        if (!isApplicable){
            throw  new FXValidatorException(annotation.annotationType().getName() + "is not applicable on" + control.getClass().getName());
        }
    }
    @Override
    public void validate(Control control, FXNotNull annotation) throws ValidationException {
        if (control.isDisable()){
            this.isValid.set(true);
            return;
        }
        if (!control.isVisible()){
            this.isValid.set(true);
            return;
        }
        boolean valid = false;

        if (control instanceof ChoiceBox){
            ChoiceBox choiceBox = (ChoiceBox) control;
            valid = choiceBox.getValue() != null;
        }else if (control instanceof ComboBoxBase){
            ComboBoxBase comboBoxBase = (ComboBoxBase) control;
            valid = comboBoxBase.getValue() != null;
        }

        this.isValid.set(valid);
        if (!valid){
            throw new ValidationException(annotation.message());
        }
    }
}
