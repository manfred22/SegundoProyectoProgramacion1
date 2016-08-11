/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Paquetes
package Estructura;
//Importes
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 **
 ** @author Manfred Martineli Rojas
 ** @date 2016-07-24
 **/
//Clase Buscaminas
public class Buscaminas {
    Scanner s = new Scanner(System.in);//Metodo Scanner
    //Variables globales
    public static int x;
    public static int y;
    public celda[][] tablero;//Arreglo del tablero
    public String resp = " ";
    public int fila =  0;
    public int columna = 0;
    public static int minas;
    public boolean gano = false;
    public boolean fallar = false;
    //Clase Llamada Celda
    public class celda {
            //Variables finales
            final int banderas = 1;
            final int pregunta = 2;
            final int impreso = 3;
            public boolean oculto = true;
            public final boolean mina;
            public int tipoBandera;
            public int vecinos = 0;
    //Contructor lleno para variable tipo boolean de mina 
    public celda(boolean mina) {
                this.mina = mina;
            }//Cierre del contructor
            //Metodo setBandera
            public void setBandera(int miTipoBandera){
                if(miTipoBandera == 1){
                    this.tipoBandera = miTipoBandera;
                }else if (miTipoBandera == 2) {
                    this.tipoBandera = miTipoBandera;
                }else if (miTipoBandera == 3) {
                    oculto = true;
                    this.vecinos = miTipoBandera;
                }
            }//Cierre del metodo setBandera
            //Metodo para gettipoBandera
            public boolean gettipoBandera(int i) {
                if(i == 1){
                    return true;
                }else
                return false;
            }//Cierre del metodo para gettipoBandera
        }//Cierre de Metodo celda
        //Metodo para revelar Tablero
        public void revelarTablero(){
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[0].length; j++) {
                    celda f = tablero[i][j];
                    f.oculto = false;
                }//Cierre del segundo ciclo for
            }//Cierre del primer ciclo for
            //Metodo para mostrar el tablero
            mostrarTablero();
        }//Cierre del metodo revelar Tablero
        //Metodo para revelar las celdas del tablero
        private void revelarCelda(int i, int j){          
                    celda f = tablero[i][j];
                    f.oculto = false;
                    f.setBandera(0);
        }//Cierre del metodo revelarCelda
        //Metodo para guardar las datos en las varianles y llenar el tablero con los datos
        public Buscaminas(int x,int y, int númeroDeMinas) {
            this.x = x;
            this.y = y;
            enteroTablero(númeroDeMinas);
            mostrarTablero();
            while(!gano && !fallar){
                pregunta();
                mostrarTablero();
            }//Cierre del ciclo while
        }//Cierre de metodo Buscaminas
        //Metodo para preguntar las cordenadas y para seleccionar si se destapa la celda o se marca
        private void pregunta(){
            try{
            System.out.println("¿Que decea, marcar o revelar una celda?");
            System.out.println("");
            System.out.println("Opciones");
            System.out.println("Digite una opcion \n 1.revelar celda \n 2.Marcar celda \n 3.Salir");
            resp = s.next();
            if(resp.equals("3")){
                System.exit(0);
            }else{
            System.out.println("====");
            System.out.println("Digite la cordenada de la fila deceada: ");
            fila = s.nextInt()-1;
            System.out.println("====");
            System.out.println("Digite la cordenada de la columna deceada: ");
            columna = s.nextInt()-1;
            System.out.println("====");
            }
            }catch(InputMismatchException e){
                System.out.println("Los datos que digito no funcionan, por esto se cerrara");
                System.exit(0);
            }finally{
            if(fila > y || columna > x){//ciclo
            System.out.println("");
            System.out.println("Lo que digito no se encuentra en los margenes del tablero,\nintrodusca de nuevo los datos correspondientes.");
            System.out.println("");
            mostrarTablero();
            pregunta();
            }else{//Cierre cierre y continuacion si los datos son validos
            entradaDeDatos(fila,columna,true);
                        }//Cierre del ciclo
                    }//Cierre del try catch
            }//Cierre del ciclo
        // Metodo para guardar todo en el tablero
        private void enteroTablero(int númeroDeMinas) {
            List<celda> minas = new ArrayList<celda>();
            for (int i = 0; i < y * x; i++) {
                minas.add(new celda(i < númeroDeMinas));
            }
            Collections.shuffle(minas);
            tablero = new celda[y][x];
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    tablero[i][j] = (i == 0 || j == 0 || i == y || j == x)
                            ? new celda(false)
                            : minas.remove(0);
                }//Cierre ciclo for
            }//Cierre ciclo for
            calcularVecinos();
        }//Cierre del metodo
        //Metodo para validar los datos introducidos en el metodo pregunta
        private void entradaDeDatos(int fila, int columna, boolean revealMines){
            celda f = tablero[fila][columna];
            if (resp.equals("r")|| resp.equals("R")|| resp.equals("1")){
                if (f.mina && !revealMines) {
                    return;
                }
            if (f.oculto) { 
                revelarCelda(fila,columna);
                if(f.mina){
                    hafallado();
                }else if (f.vecinos == 0){
                        if (DentroDeLosLímites(fila + 1,columna)){
                            celda Vecino = tablero[fila + 1][columna];
                            if (Vecino.oculto && Vecino.vecinos == 0) {
                                entradaDeDatos(fila + 1,columna,false);
                            }else if (Vecino.oculto && !Vecino.mina){
                                revelarCelda(fila + 1,columna);
                            }
                        }
                        if (DentroDeLosLímites(fila - 1,columna)){
                            celda Vecino = tablero[fila - 1][columna];
                            if (Vecino.oculto && Vecino.vecinos == 0) {
                                entradaDeDatos(fila - 1,columna,false);
                            }else if (Vecino.oculto && !Vecino.mina){
                                revelarCelda(fila - 1,columna);
                            }
                        }
                        if (DentroDeLosLímites(fila,columna - 1)){
                            celda Vecino = tablero[fila][columna - 1];
                            if (Vecino.oculto && Vecino.vecinos == 0) 
                            entradaDeDatos(fila,columna - 1,false);
                            else if (Vecino.oculto && !Vecino.mina){
                                revelarCelda(fila,columna-1);
                            }
                        }
                        if (DentroDeLosLímites(fila , columna + 1)){
                            celda Vecino = tablero[fila][columna + 1];
                            if (Vecino.oculto && Vecino.vecinos == 0) 
                            entradaDeDatos(fila,columna + 1,false);   
                            else if (Vecino.oculto && !Vecino.mina){
                                revelarCelda(fila,columna+1);
                            }
                        }
                        if (DentroDeLosLímites(fila - 1,columna - 1)){
                            celda Vecino = tablero[fila - 1][columna - 1];
                            if (Vecino.oculto && Vecino.vecinos == 0) 
                            entradaDeDatos(fila - 1,columna - 1,false);
                            else if (Vecino.oculto && !Vecino.mina){
                                revelarCelda(fila -1 ,columna - 1);
                            }
                        }
                        if (DentroDeLosLímites(fila + 1,columna - 1)){
                            celda Vecino = tablero[fila + 1][columna - 1];
                            if (Vecino.oculto && Vecino.vecinos == 0) 
                            entradaDeDatos(fila + 1,columna - 1,false);
                            else if (Vecino.oculto && !Vecino.mina){
                                revelarCelda(fila + 1 ,columna - 1);
                            }
                        }
                        if (DentroDeLosLímites(fila + 1,columna + 1)){
                            celda Vecino = tablero[fila + 1][columna + 1];
                            if (Vecino.oculto && Vecino.vecinos == 0) 
                            entradaDeDatos(fila + 1,columna + 1,false);
                            else if (Vecino.oculto && !Vecino.mina){
                                revelarCelda(fila + 1 ,columna + 1);
                            }
                        }
                        if (DentroDeLosLímites(fila - 1,columna + 1) && f.oculto){
                            celda Vecino = tablero[fila - 1][columna + 1];
                            if (Vecino.oculto && Vecino.vecinos == 0) 
                            entradaDeDatos(fila - 1,columna + 1,false);   
                            else if (Vecino.oculto && !Vecino.mina){
                                revelarCelda(fila - 1 ,columna + 1);
                            }//Cierre
                        }//Cierre
                    }//Cierre
                }//Cierre
            }if(resp.equals("m") || resp.equals("M") || resp.equals("2") ){
                if (f.oculto) {
                    f.setBandera(1);
                    revelarCelda(fila,columna);

                }else if(f.tipoBandera == 1){
                    f.setBandera(2);
                }else if(f.tipoBandera == 2){
                    f.setBandera(3);
                }else if(f.tipoBandera == 3){
                    f.setBandera(1);
                }//Cierre
            }else{//Cierre y continuacion
            }//Cierre
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                if(f.gettipoBandera(1) == f.mina)
                    gano = true;
                    }//Cierre
                }//Cierre
            }//Cierre metodo
        //Metodo que analiza si los datos que introducimos se encuentran en las dimenciones del arreglo
        private boolean DentroDeLosLímites(int fila, int columna){
            if((fila < y && fila >= 0) && (columna < x && columna >= 0)){
                return true;
            }//Cierre
            return false;
        }//Cierre
        // Metodo tipo mensaje para mostrar que fallo
        private void hafallado(){
            System.out.println("Perdiste la partida");
            JOptionPane.showMessageDialog(null,"Perdiste la partida xD","Aviso",JOptionPane.ERROR_MESSAGE);
            fallar = true;
        }//Cierre del metodo
        //Metodo para ver las celdas al rededor de la cordenada seleccionada
        private void calcularVecinos() {
            int count = 0;
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {            
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if(DentroDeLosLímites(i + di,j + dj))
                                if (tablero[i + di][j + dj].mina) count++;  
                        }//Cierre
                    }//Cierre
                    tablero[i][j].vecinos = count;
                    count = 0;
                }//Cierre
            }//Cierre
        }//Cierre del metodo}
        //Metodo para mostrar el menu en la consola y su orden respectivo
        private void mostrarTablero() {
            System.out.println("         Tablero         ");
            System.out.println("filas");
            for(int i = 0; i < y; i++) {
                System.out.printf("%2d| ",i + 1);
               for(int j = 0; j < x; j++) {
                   celda f = tablero[i][j];
                   if(f.oculto || f.tipoBandera == 3) {
                       System.out.printf("%3s","[ ]");
                   }  else if (f.mina) {
                       System.out.printf("%3s","[M]");
                   } else if (f.tipoBandera == 1){
                       System.out.printf("%3s","[X]");
                   }else if (f.tipoBandera == 2){
                       System.out.printf("%3s","[X]");
                   }else{
                       calcularVecinos();
                       System.out.printf("%3s","["+f.vecinos +"]");
                   }//Cierre
               }//Cierre
                System.out.println();
            }//Cierre
            System.out.print("    ");
            for(int i = 0; i < y; i++) {
                System.out.printf("%3s",i + 1 + " ");
            }//Cierre
            System.out.print(" |columnas");
            System.out.println();
        }//Cierre
}//Cierre clase Buscaminas