package javaflix.data;
import javaflix.modelo.*;

import java.io.*;
import java.util.*;


public class UserData implements Serializable{
    private ArrayList<User> usuario = new ArrayList<>();
    private ArrayList<User> userRead = new ArrayList<>();

    private final void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        aInputStream.defaultReadObject();
    }

    public boolean insertUser(User user) {
        boolean aux;
        FileOutputStream fluxo = null;
        ObjectOutputStream obs = null;
        try {
            fluxo = new FileOutputStream("Users.ser", true);
            obs = new ObjectOutputStream(fluxo);
            usuario.add(user);
            obs.writeObject(user);
            aux = true;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
            aux = false;
        } catch (IOException x) {
            System.out.println(x.getMessage());
            aux = false;
        }catch (NullPointerException r) {
            System.out.println(r.getMessage());
            aux = false;
        }finally{
            if(obs != null)
                try{
                    obs.close();
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }
        }
        return aux;
    }

    public ArrayList<User> searchUser(){
        FileInputStream fluxo = null;
        ObjectInputStream obs = null;
        try {
            fluxo = new FileInputStream("Users.ser");
            while (fluxo.available() > 0) {
                obs = new ObjectInputStream(fluxo);
                User u = (User) obs.readObject();
                userRead.add(u);
                for (int i = 0; i < userRead.size(); i++) {
                    for(int j = 1; j < userRead.size(); j++){
                        if(userRead.get(i) == userRead.get(j))
                            userRead.remove(userRead.get(j));
                    }

                }
            }
        }catch (ClassNotFoundException x) {
            System.out.println(x.getMessage());
        }catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException x) {
            System.out.println(x.getMessage());
        }catch (NullPointerException r) {
            System.out.println(r.getMessage());
        }finally{
            if(obs != null)
                try{
                    obs.close();
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }
        }
        return userRead;
    }

    public boolean verifData(String nome, String senha, int index){
        boolean aux = false;
        if(nome.equals(userRead.get(index).getUsername())){
            if(senha.equals(userRead.get(index).getSenha()))
                aux = true;
        }
        return aux;
    }
}
