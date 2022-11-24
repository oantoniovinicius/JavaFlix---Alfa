package javaflix.controle;

import javaflix.visao.StartJavaFlix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class controllerStartScreen {

    @FXML
    private ImageView javaflixBackground;

    @FXML
    private ImageView startButton;

    @FXML
    void clickButtonStart(ActionEvent event) {
        StartJavaFlix.changeScene("login");
    }
}
