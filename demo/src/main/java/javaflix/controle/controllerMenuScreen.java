package javaflix.controle;

import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
//import java.util.*;
//import info.movito.themoviedbapi.TmdbApi;
//import info.movito.themoviedbapi.TmdbMovies;
//import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
//import info.movito.themoviedbapi.model.Genre;
//import info.movito.themoviedbapi.model.MovieDb;
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
import javafx.util.Callback;


public class controllerMenuScreen implements Initializable {
    ArrayList<Filme> titulosFav = new ArrayList<>();
    @FXML
    private ImageView menuBackground;

    @FXML
    private ListView<Filme> listViewTitulos;

    @FXML
    private Button buttomFavsSpecific;

    @FXML
    private Button buttomInicioSpecific;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    void clickButtomFavsSpecific(ActionEvent event) {
        for (int i=0; i < titulosFav.size(); i++){
         System.out.println("Filme"+i);
         System.out.println(titulosFav.get(i).getNome());   
        }
        StartJavaFlix.changeScene("favs", null, titulosFav);
        titulosFav.clear();
    }

    @FXML
    void clickButtomInicioSpecific(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StartJavaFlix.addOnChangeScreenListener(new StartJavaFlix.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Filme userData, ArrayList<Filme> B) {
                if (newScreen.equals("menu")){
                    titulosFav.clear();
                    if(B != null){
                        titulosFav.addAll(B);
                    }
                }
            }
        });
        carregarObListTitulo(1);
        
    }

    /*public void cadastrarFilme(Filme titulo){

        try {
            FileOutputStream fileStream = new FileOutputStream("movie.ser", true);
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(titulo);
            os.close();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
        catch(SecurityException e) {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }*/

    public void carregarObListTitulo(int verify) { // Carregamento das Imagens/Lista de Filmes
        /*TmdbMovies movies = new TmdbApi("79f8ad578459c24567ff304e079010d9").getMovies();
        //String teste = "https://image.tmdb.org/t/p/w200/oR4Hzc17SqjfPzKbR8Qcy5QxZJF.jpg";
        String baseURL = "https://image.tmdb.org/t/p/w200";
        for(int i = 10004; i < 10033; i++){
            MovieDb movie = movies.getMovie(i, "pt-br", MovieMethod.images);
            String titulo = movie.getTitle(); //titulo
            float nota = movie.getVoteAverage(); // nota
            List<Genre> genero = movie.getGenres(); //genero
            String lancamento = movie.getReleaseDate(); // lancamento
            String descricao = movie.getOverview(); // descrição
            String imagefullURL = baseURL.concat(movie.getPosterPath()); // image
            //Artwork actual = getImage.getImages(ArtworkType.POSTER).get(0);
            //imagefullURL = baseURL.concat(actual.getFilePath());
            Filme fullMovie = new Filme(titulo, nota, genero, lancamento, descricao, imagefullURL);
            //listViewTitulos.getItems().add(fullMovie);
            cadastrarFilme(fullMovie);
            //System.out.println(imprimir());

        }*/
        if (verify == 1) {
            InicializarFilmes();
        }

        listViewTitulos.setCellFactory(new Callback<ListView<Filme>, ListCell<Filme>>() { // Carregamento da ListView

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
    public ArrayList<Filme> listarFilmes() { // Auxiliar Para Pesquisar Filmes
        ArrayList<Filme> titulos = null;
        ObjectInputStream lerObj = null;
        try {
            titulos = new ArrayList<Filme>();
            FileInputStream Titulo = new FileInputStream("movie.ser");
            
            while (Titulo.available() > 0) {
                lerObj = new ObjectInputStream(Titulo);
                Filme d = (Filme) lerObj.readObject();
                titulos.add(d);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(lerObj != null)
                try{
                    lerObj.close();
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }
        }
        return titulos;
    }
    
    public ListView<Filme> InicializarFilmes() { // Utilizado para Inicializar Filmes na Primeira
        FileInputStream Titulo = null;
        ObjectInputStream lerObj = null;
        try {
            Titulo = new FileInputStream("movie.ser");
            while (Titulo.available() > 0) {
                lerObj = new ObjectInputStream(Titulo);
                Filme d = (Filme) lerObj.readObject();
                listViewTitulos.getItems().add(d);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(lerObj != null)
                try{
                    lerObj.close();
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }
        }
       return listViewTitulos; 
    }
    @FXML
    Filme clickListViewTitulos() { // Mudança Tela Específica
        Filme titulo = listViewTitulos.getSelectionModel().getSelectedItem();
        StartJavaFlix.changeScene("specific", titulo, null);
        return titulo;
    }
    
    @FXML
    void search(ActionEvent event) throws IOException { // Função Para Busca de Filmes
        listViewTitulos.getItems().clear();
        ArrayList<Filme> titulos = listarFilmes();
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

        listViewTitulos.getItems().addAll(titulosPesquisa);

        carregarObListTitulo(0);
    }
}