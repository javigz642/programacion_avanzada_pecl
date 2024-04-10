/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

/**
 *
 * @author ediso
 */
public class Ciudad {
    public void recogerPasajerosAutobus(Autobus a){
        int pasajerosParada = (int) (Math.random()*50);
        System.out.println("El autobus va a recoger a " + pasajerosParada + " personas.");
    }
    public void irAeropuertoAutobus(Autobus a){
        try {
            System.out.println("El autobus va hacia el aeropuerto.");
            Thread.sleep((int) (Math.random()*5001)+5000);
            System.out.println("El autobus ha llegado al aeropuerto.");
        } catch (Exception e) {
        }
    }
}
