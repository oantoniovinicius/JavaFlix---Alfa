package javaflix.controle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javaflix.modelo.Filme;
import javaflix.visao.StartJavaFlix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class controllerSpecificScreen implements Initializable{

    Filme t;
    ArrayList<Filme> titulosFav = new ArrayList<>();


    @FXML
    private Button buttomInicioSpecific;

    @FXML
    private ImageView imagemFilme;

    @FXML
    private Label labelGen;

    @FXML
    private Label labelLancamento;

    @FXML
    private Label labelNota;

    @FXML
    private Label labelSinopse;

    @FXML
    private Label labelTitle;
    
    @FXML
    private Button buttomFavsSpecific;

    @FXML
    private Button AddbuttonFavs;

    @FXML
    void clickButtomFavsSpecific(ActionEvent event) {
        StartJavaFlix.changeScene("favs", null, titulosFav);
    }

    @FXML
    void buttonAddFavs(ActionEvent event) throws IOException {
        int aux=1;
        if(titulosFav.size() > 0){
            for (int i=0; i < titulosFav.size(); i++){
                if(titulosFav.get(i).getNome() == t.getNome()){
                    titulosFav.remove(i);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../visao/removedFavsFXML.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.show();
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("../visao/Assets/icon.png")));
                    aux=0;
                    break;
                }
            }
        }
        else{
            titulosFav.add(t);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../visao/addFavsFXML.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("../visao/Assets/icon.png")));
            aux=0;
        }
        if (aux == 1 && titulosFav.size() > 0){
            titulosFav.add(t);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../visao/addFavsFXML.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("../visao/Assets/icon.png")));
        }
    }


    @FXML
    void clickButtomInicioSpecific(ActionEvent event) {
        StartJavaFlix.changeScene("menu", null, titulosFav);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StartJavaFlix.addOnChangeScreenListener(new StartJavaFlix.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Filme userData, ArrayList<Filme> B) {
                if (newScreen.equals("specific")){
                    t = userData;
                    labelTitle.setText(userData.getNome());
                    imagemFilme.setImage(new Image(userData.getImagem()));
                    if (userData.getSinopse().isEmpty()){
                        labelSinopse.setText("Não foi Possível Encontrar Sinopse para este filme");   
                    }
                    else{
                        labelSinopse.setText(userData.getSinopse());
                    }
                    String notaConvertida = String.format("%.1f", userData.getNota());
                    String genero = "";
                    for(int i = 0; i < userData.getGenero().size(); i++){
                        if(userData.getGenero().get(i) != null){
                            genero += userData.getGenero().get(i) + ", ";
                        }
                    }
                    labelNota.setText(notaConvertida);
                    labelGen.setText(genero);
                    labelLancamento.setText(userData.getLancamento());
                }
            }
        });

        
    }


}
