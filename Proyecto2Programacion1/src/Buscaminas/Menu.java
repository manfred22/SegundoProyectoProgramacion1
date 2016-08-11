/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Paquetes
package Buscaminas;
//Importes
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 **
 ** @author Manfred Martineli Rojas
 ** @date 2016-07-24
 **/
public class Menu{
    //Variables Universales
    private int menu;
    private boolean repetir = false;
    private boolean otra = false;
    //Constructor vasio
    public Menu() {}
    //Constructor lleno para variable menu
    public Menu(int menu) {
        this.menu = menu;
    }
   //Metodo para menu que llama a la clase Buscaminas
    public void menu() throws Exception{   
            //Metodo Scanner para introducir datos del teclado
            Scanner s = new Scanner(System.in);
            do{//Ciclo repetitivo while do
            //Metodo Try Catch Finally
            try{//try
            menu=JOptionPane.showConfirmDialog(null,"Decea jugar","Menu",menu);
            }catch(Exception e){
            }finally{//
            //Metodo de seleccion por switch
            switch(menu){
                //caso 1
                case JOptionPane.YES_NO_OPTION:{
                    do{//Ciclo do
                    // Metodo TRY CATCH FINALLY
                    try{//TRY
                    JOptionPane.showMessageDialog(null,"Introdusca las dimenciones del tablero","Mensaje",JOptionPane.DEFAULT_OPTION);
                    System.out.println("Introdusca la dimencion del eje x");
                    Estructura.Buscaminas.x=s.nextInt();
                    System.out.println("Introdusca la dimencion del eje y");
                    Estructura.Buscaminas.y=s.nextInt();
                    System.out.println("Introdusca la cantidad de minas que decea introducir al tablero");
                    Estructura.Buscaminas.minas=s.nextInt();
                    if(Estructura.Buscaminas.x > 100 || Estructura.Buscaminas.y > 100 || Estructura.Buscaminas.minas > 100 || Estructura.Buscaminas.x <= 0 || Estructura.Buscaminas.y <= 0 || Estructura.Buscaminas.minas <= 0){
                    System.out.println("Sus dimenciones tienen datos que no funcionan, por esto se cerrara");
                    System.exit(0);
                    }else{
                    otra=false;
                    }//Cierre
                    }catch(InputMismatchException  e){//CATCH
                    System.out.println("Sus dimenciones tienen datos que no funcionan, por esto se cerrara");
                    System.exit(0);
                    }finally{
                    Estructura.Buscaminas hola = new Estructura.Buscaminas(Estructura.Buscaminas.x,Estructura.Buscaminas.y, Estructura.Buscaminas.minas);
                    System.out.println("");
                    System.out.println("Tablero completamente revelado");
                    hola.revelarTablero();
                    }//Cierre finally
                    }while(otra=true);
                    break;//Quiebre
                    }//Cierre caso 1
                // Caso 2
                case JOptionPane.NO_OPTION:{
                //salir
                System.exit(0);
                repetir=false;
                break;//Quiebre
                }//Cierre caso 2
                //caso default
                default:{
                System.out.println("");
                System.out.println("Esta opcion no se encuentra en el menu \nVuelva a digitar una de las opciones en el menu");
                System.out.println("");
                repetir=true;
                break;
                    }//Cierre de caso default
                }//Cierre de switch
            }//Cierre del finally del try catch
        }while(repetir==true);//Cierre del cilo do while
    } //Cierre Metodo menu
}//Cierre clase menu