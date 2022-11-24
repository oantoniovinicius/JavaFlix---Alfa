package javaflix.modelo;
import java.io.*;
import java.util.List;

public class Titulo implements Serializable {
    private int codigo;
    private static int contador = 0;
    private String nome;
    private float nota;
    private String lancamento;
    private String sinopse;
    private String imagem;
    private List genero;

    
    
    public Titulo(String nome, float nota, List genero, String lancamento, String sinopse, String imagem){
        this.codigo = ++contador;
        this.nome = nome;
        this.nota = nota;
        this.lancamento = lancamento;
        this.sinopse = sinopse;
        this.imagem = imagem;
        this.genero = genero;
    }
    
    
    //getters
    public String getImagem() {
        return imagem;
    }

    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public float getNota() {
        return nota;
    }
    public String getLancamento() {
        return lancamento;
    }
    public String getSinopse() {
        return sinopse;
    }
    public List getGenero(){
        return genero;
    }
    @Override
    public String toString() {
        return super.toString();
    }
    
    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setNota(float nota) {
        this.nota = nota;
    }
    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    public void setGenero(List genero){
        this.genero = genero;
    }

    public String imprimir(){
        return "Nome: " + this.getNome() + "/ Codigo: " + this.getCodigo() + 
        "/ Nota: " + this.getNota() + "/ Lancamento: " + this.getLancamento() +
        "/ Tipo do Título: " + "\nSinopse: " + this.getSinopse() + "\nSinopse: " + this.getImagem() ;
    }
    
}
    