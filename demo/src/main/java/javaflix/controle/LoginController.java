package javaflix.controle;
import java.io.IOException;
import java.util.ArrayList;

import javaflix.data.*;
import javaflix.modelo.User;
import javaflix.visao.StartJavaFlix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField userName;

    @FXML
    private TextField passwordField;

    @FXML
    private Label loginMessageLabel;
    
    @FXML
    private Button login;

    @FXML
    private Button cadastre;

    UserData UD = new UserData();
    @FXML
    void clickButtonStart(ActionEvent event) throws IOException {
        ArrayList<User> userAAA = new ArrayList<>();
        
        String user=userName.getText();
        String password=passwordField.getText();
        userAAA = UD.searchUser();
        boolean aux = false;
        for(int index=0;index<userAAA.size();index++){
            if(user.equals(userAAA.get(index).getUsername())){
                if(password.equals(userAAA.get(index).getSenha()))
                    aux = true;
            }
        }
        if(aux==false){
            System.out.println("nao foi");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../visao/sucessoLoginFXML.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("../visao/Assets/icon.png")));
        }else{
            System.out.println("foi");
            StartJavaFlix.changeScene("menu");
        }
    }
    @FXML
    void cadastrar(ActionEvent event) {
        StartJavaFlix.changeScene("cadastre-se");
    }

}



