/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ventanas;

/**
 *
 * @author LENOVO
 */
public class variablesGlobales {
   public static String adminUsername;
   public static String usuarioActual;
   
    public static String getAdminUsername() {
        return adminUsername;
    }

    public static void setAdminUsername(String nombreAdmin) {
        variablesGlobales.adminUsername = nombreAdmin;
    } 
    public static String getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(String nombreUsuario) {
        variablesGlobales.usuarioActual = nombreUsuario;
    }
}
