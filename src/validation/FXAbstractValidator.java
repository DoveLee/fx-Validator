package validation;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventType;
import javafx.scene.control.Control;
import validation.exceptions.ValidationException;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public abstract class FXAbstractValidator<T extends Control,A extends Annotation> {
    protected  T control;
    protected  A annotation;

    public FXAbstractValidator(){

    }
    public  FXAbstractValidator(T control,A annotation){
        this.control = control;
        this.annotation = annotation;
    }

    protected final BooleanProperty isValid = new SimpleBooleanProperty(false);

    protected List<EventType> eventTypes = new ArrayList<>();

    public abstract void validate(T control,A annotation) throws ValidationException;

    public void validate() throws ValidationException{
        this.validate(this.control, this.annotation);
    }

    public List<EventType> getEventTypes() {
        return eventTypes;
    }

    public BooleanProperty isValidProperty() {
        return isValid;
    }

    public T getControl() {
        return control;
    }

    public void setControl(T control) {
        this.control = control;
    }

    public A getAnnotation() {
        return annotation;
    }

    public void setAnnotation(A annotation) {
        this.annotation = annotation;
    }
}
