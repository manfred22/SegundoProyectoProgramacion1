/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Paquetes
package Buscaminas;
//Importes
import Estructura.*;
import javax.swing.JOptionPane;

/**
 **
 ** @author Manfred Martineli Rojas
 ** @date 2016-07-24
 **/
public class Menu extends Object {
    // Variables Globales
    int opcion;
    int i;
    //Constructor lleno
    public Menu(int opcion, int i) {
        this.opcion = opcion;
        this.i = i;
    }
    //Constructor vasio
    public Menu() {}
     /**
     * Metodo de menu que llama a las clases
     **/
    public void menuBuscaminas(){
        do{
        try{
        opcion=Integer.parseInt(JOptionPane.showInputDialog(null,"Intrudusca la opcion que deceada"+"\n 1.Jugar"+"\n 2.Salir"));
        }catch(Exception opcion){
        }
        switch(opcion){//Menu de Seleccion
        //Caso 1
        case 1:{
        opcion=1;
        System.out.println("En un momento iniciara su partida");
        Tablero m = new Tablero();
        m.datos();
        m.mostablero();
        break;
        }//Cierre del caso 1
        //Caso 2
        case 2:{
        opcion=2;
        System.out.println("Salio");
        System.exit(0);
        break;
        }
        default:{//Valor por Defecto:
        System.out.println("Esto no existe o no es el numero de las opciones");
        break;//Quiebre
        }//Cierre del caso default
        }//Cierre del switch        
        }while(opcion<=1||opcion>=3);
        }//Cierre del Metodo
}//Fin de la clase menu {}