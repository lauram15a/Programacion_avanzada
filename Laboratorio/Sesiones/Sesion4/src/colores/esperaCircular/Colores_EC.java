/*ESPERA CIRCULAR: Cada proceso espera la liberación de un 
recurso por otro proceso, que a su vez espera un tercero y 
así sucesivamente hasta completar el círculo con el primer 
proceso.
*
*
*RUPUTURA DE LA ESPERA CIRCULAR
*
*La espera circular se produce en el ejemplo de los pintores,
porque se puede dar la circunstancia de que los cuatro 
pintores tengan cogido el primer color y estén esperando 
por el segundo, pero el segundo color del pintor i es siempre
el primero del pintor i+1, para todos ellos.
*
*La forma más simple de romper la espera circular es hacer 
que uno de los pintores se comporte de forma diferente al 
resto (pintor zurdo). Si por ejemplo el pintor 0 toma primero
el color 1 y después el color 0, ya no será posible la espera
circular.
*
*Por tanto, modificando únicamente el comportamiento de los 
pintores del problema original, para que el pintor 0 (o 
cualquiera de ellos), tome los colores al revés, tendremos 
resuelto el  problema:
*
*Con esta pequeña modificación en el programa original, 
logramos evitar que se produzca el interbloqueo.

*/

package colores.esperaCircular;

import colores.*;

public class Colores_EC extends javax.swing.JFrame
{
    /** Creates new form Coloress */
    public Colores_EC()
    {
        initComponents();
        Paleta_EC mezclas = new Paleta_EC();
        Pintor_EC p1 = new Pintor_EC(jButton1,mezclas,0);
        Pintor_EC p2 = new Pintor_EC(jButton2,mezclas,1);
        Pintor_EC p3 = new Pintor_EC(jButton3,mezclas,2);
        Pintor_EC p4 = new Pintor_EC(jButton4,mezclas,3);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Colores");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(816, 338));
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    /**
    * @param args the command line arguments
    */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Colores_EC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    // End of variables declaration                   
}
