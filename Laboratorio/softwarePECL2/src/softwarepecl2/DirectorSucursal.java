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
public class DirectorSucursal extends Persona
{
    
    AtencionCliente[] lista_trabajadores;
    int cuetas_sucursal;
    
    public void gestionarTrabajadores()
    {
        aniadirTrabajador();
        buscarTrabajador();
    }
    
    public void aniadirTrabajador()
    {
        AtencionCliente ac = new AtencionCliente();
    }
    
    public AtencionCliente buscarTrabajador()
    {
        AtencionCliente ac = new AtencionCliente();
        ac.getInfoPersonal();
        modificarInfoTrabajador(ac);
        eliminarTrabajador(ac);
        return ac;
    }
    
    public void modificarInfoTrabajador(AtencionCliente ac)
    {
        ac.setInfoPersonal(infoPersonal);
    }
    
    public void eliminarTrabajador(AtencionCliente ac){}

    public int getCuetas_sucursal() {
        return cuetas_sucursal;
    }
     
}
