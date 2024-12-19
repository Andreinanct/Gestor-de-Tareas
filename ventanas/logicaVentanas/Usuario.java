/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ventanas.logicaVentanas;

/**
 *
 * @author Jhoel Rivera
 */
public class Usuario {
    private String username;
    private String password;
    private String rol;
    private Lista listaTareas;
    

    public Usuario(String username, String password,String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String nuevo) {
       password = nuevo;
    }
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
    public void setUsername(String nuevo) {
       username = nuevo;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + rol;
    }
}

