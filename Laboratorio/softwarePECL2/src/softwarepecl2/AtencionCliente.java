/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarepecl2;

/**
 *
 * @author laura
 */
public class AtencionCliente extends Persona
{
    Cliente[] lista_clientes;
    
    public void modificarDatosCliente()
    {
        Cliente cliente = new Cliente();
        cliente.setInfoPersonal(infoPersonal);
        cambiarNumeroTelefonoCliente();
        cambiarDireccionViviendaCliente();
        cambiarFotoPerfilCliente();
        aniadirCuentaBancariaCliente();
    }
        
    public void cambiarNumeroTelefonoCliente()
    {
        Cliente cliente = new Cliente();
        cliente.cambiarNumeroTelefono();
    }
    
    public void cambiarDireccionViviendaCliente()
    {
        Cliente cliente = new Cliente();
        cliente.cambiarDireccionVivienda();
    }
    
    public void cambiarFotoPerfilCliente()
    {
        Cliente cliente = new Cliente();
        cliente.cambiarFotoPerfil();
    }
    
    public void aniadirCuentaBancariaCliente()
    {
        Cliente cliente = new Cliente();
        cliente.aniadirCuentaBancaria();
    }
    
    public void modificarLoContratado(){}
    
    public void darDeBajaCliente(){}
    
    public void darDeAltaCliente(){}
    
    public void accederInfoCliente()
    {
        Cliente cliente = new Cliente();
        cliente.getInfoPersonal();
    }
    
    public void informar(){}
}
