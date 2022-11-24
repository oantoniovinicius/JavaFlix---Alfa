package javaflix.modelo;

import java.io.Serializable;

public class User implements Serializable{
    private String username;
    private String senha;

    public User (String username, String senha){
        this.username = username;
        this.senha = senha;
    }
    public String getUsername() {
        return username;
    }
    public String getSenha() {
        return senha;
    }


}
