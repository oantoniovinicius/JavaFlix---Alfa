package javaflix.controle;
import javaflix.modelo.*;
import javaflix.visao.StartJavaFlix;

import java.io.IOException;
import java.util.ArrayList;

import javaflix.data.*;
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

public class CadastroController {

    @FXML
    private TextField userRegister;
    
    @FXML
    private Button voltar;

    @FXML
    private TextField passwordRegister;

    @FXML
    private Label registerMessageLabel;

    @FXML
    private Button cadastrar;

    UserData UD = new UserData();

    @FXML
    void cadastrarBut(ActionEvent event) throws IOException {
        String userR=userRegister.getText();
        String passwordR=passwordRegister.getText();

        User usuario = new User(userR, passwordR);
        
        insertUser(usuario);
        ArrayList<User> userReadSU = searchUser();
        boolean aux = verifData(usuario, userReadSU);
        
        if(!(aux)){
            System.out.println("Cadastro invalido/n"+userReadSU.size());
        }else{
            System.out.println("Cadastro realizado/n"+userReadSU.size());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../visao/sucessoCadastroFXML.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("../visao/Assets/icon.png")));
        }

    }
    @FXML
    void voltar(ActionEvent event) {
        StartJavaFlix.changeScene("login");
    }

    public ArrayList<User> insertUser(User u){
        return UD.insertUser(u);
    }
    public ArrayList<User> searchUser(){
        return UD.searchUser();
    }
    public boolean verifData(User user,ArrayList<User> userReadSU ){
        return UD.verifData(user, userReadSU);
    }

}
