package javaflix.visao;

import java.io.IOException;
import java.util.ArrayList;

import javaflix.modelo.Filme;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StartJavaFlix extends Application {
    private static Stage stage;
    private static Scene startScene;
    private static Scene loginScreen;
    private static Scene cadastroScreen;
    private static Scene menuScene;
    private static Scene specificScene;
    private static Scene favsScene;

    @Override
    public void start(Stage stagePrimary) throws IOException {
        stage = stagePrimary;
        stagePrimary.setTitle("JavaFlix - 2022");
        stagePrimary.getIcons().add(new Image(getClass().getResourceAsStream("Assets/icon.png")));


        Parent fxmlStart = FXMLLoader.load(getClass().getResource("startScreenFXML.fxml"));
        startScene = new Scene(fxmlStart);
        
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("loginScreenFXML.fxml"));
        loginScreen= new Scene (fxmlLogin);
        
        Parent fxmlCadastro = FXMLLoader.load(getClass().getResource("cadastroScreenFXML.fxml"));
        cadastroScreen= new Scene (fxmlCadastro);

        Parent fxmlMenu = FXMLLoader.load(getClass().getResource("MenuScreenFXML.fxml"));
        menuScene = new Scene(fxmlMenu);
        menuScene.getStylesheets().add("style.css");

        Parent fxmlSpecific = FXMLLoader.load(getClass().getResource("specificScreenFXML.fxml"));
        specificScene = new Scene(fxmlSpecific);

        Parent fxmlFavs = FXMLLoader.load(getClass().getResource("favsScreenFXML.fxml"));
        favsScene = new Scene(fxmlFavs);

        stagePrimary.setScene(startScene);
        stagePrimary.setResizable(false);
        stagePrimary.show();
    }

    public static void changeScene(String scr, Filme userData, ArrayList<Filme> arrayT) {
        switch (scr) {
            case "start":
                stage.setScene(startScene);
                notifyAllListeners("main", userData, null);
                break;
            case "login":
                stage.setScene(loginScreen);
                notifyAllListeners("login", userData, null);
                break;
            case "cadastre-se":
                stage.setScene(cadastroScreen);
                notifyAllListeners("cadastre-se", userData, null);
                break;
            case "cadastrar":
                stage.setScene(loginScreen);
                notifyAllListeners("cadastrar", userData, null);
                break;
            case "menu":
                stage.setScene(menuScene);
                notifyAllListeners("menu", userData, arrayT);
                break;
            case "specific":
                stage.setScene(specificScene);
                notifyAllListeners("specific", userData, null);
                break;
                case "favs":
                stage.setScene(favsScene);
                notifyAllListeners("favs", null, arrayT);
                break;
        }
    }

    public static void changeScene(String scr) {
        changeScene(scr,null, null);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static ArrayList<onChangeScreen> listeners = new ArrayList<>();

    public static interface onChangeScreen {
        void onScreenChanged(String newScreen, Filme userData, ArrayList<Filme> arrayT);
    }

    public static void addOnChangeScreenListener(onChangeScreen newListener) {
        listeners.add(newListener);
    }

    public static void notifyAllListeners(String newScreen, Filme userData, ArrayList<Filme> arrayT) {
        for (onChangeScreen l : listeners)
            l.onScreenChanged(newScreen, userData, arrayT);
    }

}
