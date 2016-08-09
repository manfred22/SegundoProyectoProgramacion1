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
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 **
 ** @author Manfred Martineli Rojas
 ** @date 2016-07-24
 **/
public class Buscaminas {
    Scanner s = new Scanner(System.in);
    char resp =' ';
    int fila =  0;
    int columna = 0;
    int respuesta;
    static int a;
    static int b;
    static int minas;
    public boolean gano = false;
    public boolean fallo = false;
    public boolean reintentar = false;
            
    private static class celda {
    final int marcado = 1;
    final int pregunta = 2;
    final int blanco = 3;
    public boolean oculto = true;
    public final boolean mina;
    public int tipoBandera;
    public int vecinos = 0;
            public celda(boolean mina) {
            this.mina= mina;
            }
            public void setbandera(int miTipoBandera){
                if(miTipoBandera == 1){
                    this.tipoBandera = miTipoBandera;
                }else if (miTipoBandera == 2) {
                    this.tipoBandera = miTipoBandera;
                }else if (miTipoBandera == 3) {
                    oculto = true;
                    this.vecinos = miTipoBandera;
                }
            }
            public boolean gettipoBandera(int i) {
                if(i == 1){
                    return true;
                }else
                return false;
            }
        }
        private celda[][] tablero;
        
        private void revelarTablero(){
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[0].length; j++) {
                    celda f = tablero[i][j];
                    f.oculto = false;
                }
            }
            mostrarTablero();
        }
        private void revelarCelda(int i, int j){          
                    celda m = tablero[i][j];
                    m.oculto = false;
                    m.setbandera(0);
        }
        Buscaminas(int b, int a, int numerodeminas) {
            enteroDelBorde(numerodeminas);
            mostrarTablero();
            while(!gano && !fallo){
                Preguntas();
                mostrarTablero();
            }
        }
        private void Preguntas(){
            do{
            reintentar=false;
            System.out.println("Utilidades");
            System.out.println("Para matcar una casilla digite la letra: m");
            System.out.println("Para revelar una casilla digite la letra: r");
            System.out.println("si queieres salir digita la letra s");
            System.out.println("--?--");
            resp = s.next().charAt(0);
            if(resp=='r'||resp=='m'||resp=='R'||resp=='M'){
            }else{
                System.out.println("Ud no introdujo el dato requerido vuelva a introducirlo");
            resp = s.next().charAt(0);
            }
            System.out.println("--------------------------------");
            reintentar=false;
             System.out.println("Intrudusca el numero de la fila: ");
             fila = s.nextInt() - 1;
             System.out.println("Intrudusca el numero de la columna: ");
             columna = s.nextInt() - 1;
             if(fila > a || columna > b){
                 System.out.println("El numero que introdujo no se encuentra entre las dimenciones del tablero.");
                 mostrarTablero();
                 Preguntas();
                    entradaUsuario(fila,columna,true);
            }
            }while(reintentar);
            respuesta=JOptionPane.showConfirmDialog(null,"Decea salir de la partida?", "Confirmación",
            JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_NO_OPTION)
            {
            System.exit(0);
            }
            }
            private void enteroDelBorde(int numerodeminas) {
            List<celda> minas = new ArrayList<celda>();
            for (int i = 0; i < a * b; i++) {
                minas.add(new celda(i < numerodeminas));
            }
            Collections.shuffle(minas);
            tablero = new celda[a][b];
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    tablero[i][j] = (i == 0 || j == 0 || i == a || j == b)
                            ? new celda(false)
                            : minas.remove(0);
                }
            }
            calcularvecinos();
        }
        // F>HIDDEN IS DONE ONLY FOR ONE POS, MUST FIX
            private void entradaUsuario(int fila, int columna, boolean revelaMinas){
            celda m = tablero[fila][columna];
            if (resp == 'r'||resp=='R'||resp=='1') {
                if (m.mina && !revelaMinas) {
                    return;
                }
            if (m.oculto) { 
                revelarCelda(fila,columna);

                if(m.mina){
                    haFallado();   
                }else if (m.vecinos == 0){

                        if (esDentroDeLosLimites(fila + 1,columna)){
                            celda vecinos = tablero[fila + 1][columna];
                            if (vecinos.oculto && vecinos.vecinos == 0) {
                                entradaUsuario(fila + 1,columna,false);
                            }else if (vecinos.oculto && !vecinos.mina){
                                revelarCelda(fila + 1,columna);
                            }
                        }
                        if (esDentroDeLosLimites(fila - 1,columna)){
                            celda vecinos = tablero[fila - 1][columna];
                            if (vecinos.oculto && vecinos.vecinos == 0) {
                                entradaUsuario(fila - 1,columna,false);
                            }else if (vecinos.oculto && !vecinos.mina){
                                revelarCelda(fila - 1,columna);
                            }
                        }
                        if (esDentroDeLosLimites(fila,columna - 1)){
                            celda vecinos = tablero[fila][columna - 1];
                            if (vecinos.oculto && vecinos.vecinos == 0) 
                            entradaUsuario(fila,columna - 1,false);
                            else if (vecinos.oculto && !vecinos.mina){
                                revelarCelda(fila,columna-1);
                            }
                        }
                        if (esDentroDeLosLimites(fila , columna + 1)){
                            celda vecinos = tablero[fila][columna + 1];
                            if (vecinos.oculto && vecinos.vecinos == 0) 
                            entradaUsuario(fila,columna + 1,false);   
                            else if (vecinos.oculto && !vecinos.mina){
                                revelarCelda(fila,columna+1);
                            }
                        }
                        if (esDentroDeLosLimites(fila - 1,columna - 1)){
                            celda vecinos = tablero[fila - 1][columna - 1];
                            if (vecinos.oculto && vecinos.vecinos == 0) 
                            entradaUsuario(fila - 1,columna - 1,false);
                            else if (vecinos.oculto && !vecinos.mina){
                                revelarCelda(fila -1 ,columna - 1);
                            }
                        }
                        if (esDentroDeLosLimites(fila + 1,columna - 1)){
                            celda vecinos = tablero[fila + 1][columna - 1];
                            if (vecinos.oculto && vecinos.vecinos == 0) 
                            entradaUsuario(fila + 1,columna - 1,false);
                            else if (vecinos.oculto && !vecinos.mina){
                                revelarCelda(fila + 1 ,columna - 1);
                            }
                        }
                        if (esDentroDeLosLimites(fila + 1,columna + 1)){
                            celda vecinos = tablero[fila + 1][columna + 1];
                            if (vecinos.oculto && vecinos.vecinos == 0) 
                            entradaUsuario(fila + 1,columna + 1,false);
                            else if (vecinos.oculto && !vecinos.mina){
                                revelarCelda(fila + 1 ,columna + 1);
                            }
                        }
                        if (esDentroDeLosLimites(fila - 1,columna + 1) && m.oculto){
                            celda vecinos = tablero[fila - 1][columna + 1];
                            if (vecinos.oculto && vecinos.vecinos == 0) 
                            entradaUsuario(fila - 1,columna + 1,false);   
                            else if (vecinos.oculto && !vecinos.mina){
                                revelarCelda(fila - 1 ,columna + 1);
                            }
                        }

                }

            }
            }
            if(resp == 'm'||resp =='M'|| resp=='2'){
                if (m.oculto) {
                    m.setbandera(1);
                    revelarCelda(fila,columna);

                }else if(m.tipoBandera == 1){
                    m.setbandera(2);
                }else if(m.tipoBandera == 2){
                    m.setbandera(3);
                }else if(m.tipoBandera == 3){
                    m.setbandera(1);
                }

            }
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                if(m.gettipoBandera(1) == m.mina)
                    gano = true;
                    }
                }
            }
        private boolean esDentroDeLosLimites(int fila, int columna){
            if((fila > a && fila >= 0) && (columna > b && columna >= 0)){
                return true;
            }
            return false;
        }



        private void haFallado(){
            JOptionPane.showMessageDialog(null,"Perdiste");
            System.out.println("Perdiste XD! :(");
            fallo = true;
        }


        private void calcularvecinos() {
            int count = 0;
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {            
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if(esDentroDeLosLimites(i + di,j + dj))
                                if (tablero[i + di][j + dj].mina) count++;  
                        }
                    }
                    tablero[i][j].vecinos = count;
                    count = 0;
                }
            }

        }
        private void mostrarTablero() {
            for(int i = 0; i < b; i++) {
                System.out.printf("%2d|",i + 1);
               for(int j = 0; j < b; j++) {
                   celda f = tablero[i][j];
                   if(f.oculto || f.tipoBandera == 3) {
                       System.out.printf("[%3s]"," ");
                   }  else if (f.mina) {
                       System.out.printf("[%3s]"," * ");
                   } else if (f.tipoBandera == 1){
                       System.out.printf("[%3s]"," X ");
                   }else if (f.tipoBandera == 2){
                       System.out.printf("[%3s]"," ? ");
                   }else{
                       calcularvecinos();
                       System.out.printf("[%3s]", f.vecinos + " ");
                   }
               }          
               System.out.println();
            }
            System.out.print("  ");
            for(int i = 0; i < a; i++) {
                System.out.printf("%3s",i + 1 + " ");
            }
            System.out.println();
  }
}//Fin de la clase Busacaminas {}