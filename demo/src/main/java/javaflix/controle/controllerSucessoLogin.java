package javaflix.controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class controllerSucessoLogin {

    @FXML
    private Button OkButton;

    @FXML
    private AnchorPane anchorPaneNoMovie;

    @FXML
    void clickOkButton(ActionEvent event) {
        Stage stage = (Stage) OkButton.getScene().getWindow(); //Obtendo a janela atual
        stage.close(); //Fechando o Stage
    }

}
