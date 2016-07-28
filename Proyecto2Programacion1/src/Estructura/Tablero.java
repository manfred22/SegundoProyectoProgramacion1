/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author manfred
 */
public class Tablero {
    private String temp;
    private int x;
    private int y;
    private int minas;
    private int tablero[][];
    //
    public Tablero() {}
    //
    public Tablero(String temp, int x, int y, int minas, int[][] tablero) {
        this.temp = temp;
        this.x = x;
        this.y = y;
        this.minas = minas;
        this.tablero = tablero;
    }
    //
    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMinas(int minas) {
        this.minas = minas;
    }
    //
    public String getTemp() {
        return temp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMinas() {
        return minas;
    }

    public int[][] getTablero() {
        return tablero;
    }
    //Metodos
    public void datos(){
    temp=JOptionPane.showInputDialog("Intrudusca la cantidad de filas que decea");
    x=Integer.parseInt(temp);
    temp=JOptionPane.showInputDialog("Intrudusca la cantidad de columnas que decea");
    y=Integer.parseInt(temp);
    minas = x*2;
    }
    public void mostablero(){
    Tablero create=new Tablero();
    tablero = crearGrid(x, y);
    tablero = insertarMinas(tablero, x, y, minas);
    tablero = buscarMinas(tablero, x, y);
    imprimirTablero(tablero, x, y);
    }
    public static int[][] crearGrid(int x,int y){
        int[][] cadena = new int[x][y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                cadena[i][j] = 0;
            }
        }
        return cadena;
    }

    public static void imprimirTablero(int[][] tablero,int x,int y){
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(tablero[i][j] == -1)
                    System.out.print("*");
                else
                System.out.print(tablero[i][j]);
            }
            System.out.println("");
        }
    }

    public static int[][] insertarMinas(int[][] tablero,int x,int y, int minas){
        int cuenta = 0, posicionX,posicionY;
        Random rand = new Random();
        while(cuenta<minas){
            posicionX = rand.nextInt(x);
            posicionY = rand.nextInt(y);
            if(tablero[posicionX][posicionY]!=-1){
                tablero[posicionX][posicionY]=-1;
                cuenta++;
            }
        }
        return tablero;
    }

    public static int[][] buscarMinas(int[][] tablero,int x,int y){
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(tablero[i][j] == -1)
                    tablero = llenarCasillasContiguas(tablero, i, j,x,y);
            }
        }
        return tablero;
    }

    public static int[][] llenarCasillasContiguas(int[][] tablero,int x, int y, int sizeX, int sizeY){
        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                if(i>=0 && j>=0 && i<sizeX && j<sizeY){
                if(tablero[i][j] != -1)
                    tablero[i][j]++;
                }
            }
        }
        return tablero;
    }
}