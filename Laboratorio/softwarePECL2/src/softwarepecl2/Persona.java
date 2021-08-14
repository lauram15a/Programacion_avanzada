/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarepecl2;

import java.io.File;

/**
 *
 * @author laura
 */
public class Persona 
{
    String nombre;
    String identificacion;
    String contrasenia;
    int numero_telefono;
    String direccion_vivienda;
    File fotoPerfil;
    String infoPersonal;
    
    public void logear(){}
    
    public void modificarDatosPersonales()
    {
        cambiarNumeroTelefono();
        cambiarDireccionVivienda();
        cambiarFotoPerfil();
    }
    
    public void cambiarNumeroTelefono(){}    
    public void cambiarDireccionVivienda(){}    
    public void cambiarFotoPerfil(){}

    public String getInfoPersonal() {
        return infoPersonal;
    }

    public void setInfoPersonal(String infoPersonal) {
        this.infoPersonal = infoPersonal;
    }
    
        
}
