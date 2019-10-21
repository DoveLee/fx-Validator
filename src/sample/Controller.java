package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import validation.annotations.FXNotNull;

public class Controller {
    @FXML
    @FXNotNull
    private ComboBox combo;

    @FXML
    private void initialize(){
        System.out.println("1234");
    }
}
