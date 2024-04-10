/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;
// Esto es un cambio
/**
 *
 * @author ediso
 */
public class Avion extends Thread{
    private String identificador;
    private int numero;
    private final int pasajerosMax;

    //Constructor NO definitivo!!
    public Avion(String identificador, int numero) {
        this.identificador = identificador +"-"+ numero;
        this.numero = numero;
        this.pasajerosMax = (int) (Math.random()*200) +100;
    }
    
    public void run(){
        
    }
    
    
}
