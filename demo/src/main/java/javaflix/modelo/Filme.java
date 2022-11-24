package javaflix.modelo;

import java.util.List;

public class Filme extends Titulo {
    private double duracaoEmSegs;

    public Filme(String nome, float nota,List genero,String lancamento, String sinopse, String imagem){
        super(nome,nota,genero,lancamento,sinopse, imagem);
    }

    public double getDuracaoEmSegs(){
        return duracaoEmSegs;
    }

    public String converteSegsHora(double duracaoEmSegs){
        long horas; 
        long minutos;
        long segundos;

        horas = ((duracaoEmSegs/3600) >= 1 )? (long)(duracaoEmSegs/3600) : 0;
        duracaoEmSegs -= horas*3600;

        minutos = ((duracaoEmSegs/60) >= 1 )? (long)(duracaoEmSegs/60) : 0;
        duracaoEmSegs -= minutos*60;

        segundos = (long)duracaoEmSegs;

        return horas + "/" + minutos + "/" + segundos;  
    }

    public String imprimir(){
        return "Nome: " + super.getNome() + "/ Codigo: " + super.getCodigo() + 
        "/ Nota: " + super.getNota() + "/ Lancamento: " + super.getLancamento() +
        "/ Tipo do Título: " +"/ Duração do Filme: " + converteSegsHora(this.duracaoEmSegs) +
        "\nSinopse: " + super.getSinopse();
    }
}

