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
public class Cliente extends Persona
{
    
    int numero_tarjeta;
    int pin_tarjeta;
    int efectivo;
    int[] deposito;
        
    public void modificarDatos()
    {
        cambiarNumeroTelefono();
        cambiarDireccionVivienda();
        cambiarFotoPerfil();
        aniadirCuentaBancaria();
    }
    
    public void realizarOperacion()
    {
        resturarEfectivo();
        verDeposito();
        hacerTransferecia();
        pedirInformacion();
        validarTarjeta();
    }
    
    public void resturarEfectivo(){}
    
    public void verDeposito(){}
    
    public void hacerTransferecia(){}
    
    public void pedirInformacion()
    {
        AtencionCliente ac = new AtencionCliente();
        ac.informar();
    }
    
    public void validarTarjeta(){}
       
    public void aniadirCuentaBancaria(){}
  
}
