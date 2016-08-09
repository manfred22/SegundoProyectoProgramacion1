/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Paquetes
package Estructura;
//Importes
import javax.swing.JOptionPane;
/**
 **
 ** @author Manfred Martineli Rojas
 ** @date 2016-07-24
 **/
public class Menu{
    private int respuesta;
    private int opc=0;
    private boolean Salir=false;
    //
    public Menu() {}
    //
    public Menu(int respuesta) {
        this.respuesta = respuesta;
    }
    
    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public void setOpc(int opc) {
        this.opc = opc;
    }

    public void setSalir(boolean Salir) {
        this.Salir = Salir;
    }
    
    public int getRespuesta() {
        return respuesta;
    }

    public int getOpc() {
        return opc;
    }

    public boolean isSalir() {
        return Salir;
    }
    public void Menu(){
        do{
        respuesta=JOptionPane.showConfirmDialog(null,"Decea jugar el buscaminas?", "Confirmación",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.YES_NO_OPTION){
        JOptionPane.showMessageDialog(null,"En el siguiente mensaje intrudusca las dimenciones del tablero");
        Estructura.Buscaminas.a=Integer.parseInt(JOptionPane.showInputDialog("Hsola"));
        Estructura.Buscaminas.b=Integer.parseInt(JOptionPane.showInputDialog("Hsola"));
        Estructura.Buscaminas.minas=Integer.parseInt(JOptionPane.showInputDialog("Hsola"));
        Estructura.Buscaminas q = new Estructura.Buscaminas(Buscaminas.a, Buscaminas.b, Buscaminas.minas);
        System.out.println("Decidiste salir Adios!");
        System.exit(0);
        break;
            }
        }while(opc>=1);
    }
}
