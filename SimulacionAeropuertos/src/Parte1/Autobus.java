/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

/**
 *
 * @author ediso
 */
public class Autobus extends Thread{
    private String identificador;
    private int numero;
    private int pasajeros;
    private Ciudad ciudad;
    private Aeropuerto aeropuerto;
    
    
    
    //private final int pasajerosMax = 50;
   public void run(){
       ciudad.recogerPasajerosAutobus(this);
       ciudad.irAeropuertoAutobus(this);
       
   }
   public int getPasajeros(){
       return pasajeros;
   }
}
