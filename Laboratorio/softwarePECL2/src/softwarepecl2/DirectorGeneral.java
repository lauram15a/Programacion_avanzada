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
public class DirectorGeneral extends Persona
{
    AtencionCliente[] lista_trabajadores;
    DirectorSucursal[] lista_directores_sucursales;
    Cliente[] lista_clientes;
    
    public void gestionarTrabajadores()
    {
        DirectorSucursal ds = new DirectorSucursal();
        aniadirDirectorSucursal();
        buscarDirctorSucursal(ds);
    }
    
    public void aniadirDirectorSucursal()
    {
        DirectorSucursal ds = new DirectorSucursal();
    }
    
    public void buscarDirctorSucursal(DirectorSucursal ds)
    {
        ds.getInfoPersonal();
        modificarInfoDirectorSucursal(ds);
        eliminarDirectosSucursal(ds);
    } 
    
    public void modificarInfoDirectorSucursal(DirectorSucursal ds)
    {
        ds.setInfoPersonal("");
    }
    
    public void eliminarDirectosSucursal(DirectorSucursal ds){}
       
    public void gestionarSucursal()
    {
        crearOferta();
        accederCuentasSucursal();
        accerderTrabajadoresSucursal();
        accerderClientes();
    }
    
    public void crearOferta(){}
    
    public void accederCuentasSucursal()
    {
        DirectorSucursal ds = new DirectorSucursal();
        ds.getCuetas_sucursal();
    }
    
    public void accerderTrabajadoresSucursal()
    {
        AtencionCliente ac = new AtencionCliente();
        ac.getInfoPersonal();
    }
    
    public void accerderClientes()
    {
        Cliente cliente = new Cliente();
        cliente.getInfoPersonal();
    }
    
    public void gestionarBolsa ()
    {
        comprarAcciones();
        venderAcciones();
    }
    
    public void comprarAcciones(){}
    public void venderAcciones(){}
}
