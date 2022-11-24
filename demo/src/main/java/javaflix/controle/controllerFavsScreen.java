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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
public class controllerFavsScreen implements Initializable {

    ArrayList<Filme> titulos = new ArrayList<>();
    ArrayList<Filme> aux = new ArrayList<>();

    @FXML
    private Button buttomInicioSpecific;

    @FXML
    private ListView<Filme> listViewFavs;

    @FXML
    private ImageView menuBackground;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    void clickButtomInicioSpecific(ActionEvent event) {
        StartJavaFlix.changeScene("menu", null, aux);
        aux.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StartJavaFlix.addOnChangeScreenListener(new StartJavaFlix.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Filme x, ArrayList<Filme> arrayAdd) {
                if (newScreen.equals("favs")){
                        titulos.clear(); // Auxiliar Pesquisa
                        aux.clear();
                        aux.addAll(arrayAdd);
                        listViewFavs.getItems().clear();
                        listViewFavs.getItems().addAll(arrayAdd);
                        
                        titulos.addAll(arrayAdd); // Auxiliar Pesquisa
                }
            }
        });
        carregarObListFavs();
    }


    public void carregarObListFavs() { // Carregamento das Imagens/Lista de Filmes

            listViewFavs.setCellFactory(new javafx.util.Callback<ListView<Filme>,ListCell<Filme>>() {
                @Override
                public ListCell<Filme> call(ListView<Filme> param) {
                    ListCell<Filme> celula = new ListCell() {
    
                        @Override
                        protected void updateItem(Object item, boolean empty) {
                            super.updateItem(item, empty);
                            Filme titulo = ((Filme) item);
    
                            if (titulo != null) {
                                setText(titulo.getNome());
                                setGraphic(new ImageView(titulo.getImagem()));
                                setTooltip(new Tooltip(titulo.getNome()));
    
                            }
    
                        }
                    };
                    return celula;
                }   
            });
    }

    @FXML
    void search(ActionEvent event) throws IOException { // Função Para Busca de Filmes
        listViewFavs.getItems().clear();
        ArrayList<Filme> titulosPesquisa = new ArrayList<>();
        
        for (int i = 0; i < titulos.size(); i++) {
            if (titulos.get(i).getNome().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                titulosPesquisa.add(titulos.get(i));
            }
        }

        if (titulosPesquisa.isEmpty()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../visao/noMovieFXML.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("../visao/Assets/icon.png")));
        }
        listViewFavs.getItems().addAll(titulosPesquisa);
        carregarObListFavs();
    }

    @FXML
    Filme clickListViewFavs() { // Mudança Tela Específica
        Filme titulo = listViewFavs.getSelectionModel().getSelectedItem();
        StartJavaFlix.changeScene("specific", titulo, null);
        return titulo;
    }

}
