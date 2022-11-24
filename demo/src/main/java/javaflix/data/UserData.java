package javaflix.data;
import javaflix.modelo.*;

import java.io.*;
import java.util.*;


public class UserData implements Serializable{
    private ArrayList<User> usuario = new ArrayList<>();

    private final void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        aInputStream.defaultReadObject();
    }

    public ArrayList<User> insertUser(User user) {
        FileOutputStream fluxo = null;
        ObjectOutputStream obs = null;
        try {
            fluxo = new FileOutputStream("Users.ser", true);
            obs = new ObjectOutputStream(fluxo);
            usuario.add(user);
            obs.writeObject(user);
        } catch (FileNotFoundException e) {
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
        return usuario;
    }

    public ArrayList<User> searchUser(){
        ArrayList<User> userRead = null;
        FileInputStream fluxo = null;
        ObjectInputStream obs = null;
        try {
            userRead = new ArrayList<>();
            fluxo = new FileInputStream("Users.ser");

            while (fluxo.available() > 0) {
                obs = new ObjectInputStream(fluxo);
                User u = (User) obs.readObject();
                userRead.add(u);
                for (int i = 0; i < userRead.size(); i++) {
                    for(int j = i+1; j < userRead.size(); j++){
                        if(userRead.get(i) == userRead.get(j) || userRead.get(j) == null)
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

    public boolean verifData(User user,ArrayList<User> userReadSU){
        boolean aux = false;
        for(int index = 0; index < userReadSU.size(); index++){
            if(user.getUsername().equals(userReadSU.get(index).getUsername())){
                if(user.getSenha().equals(userReadSU.get(index).getSenha())){
                    aux = true;
                    return aux;
                }
            }         
        }
        return aux;
    }
}
