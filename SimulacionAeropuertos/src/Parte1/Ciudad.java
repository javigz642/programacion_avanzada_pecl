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
    
    private String nombre;
    
    protected Aeropuerto aeropuerto = new Aeropuerto(this);
    

    public Ciudad(String nombre) {
        this.nombre = nombre;
        
    }


    public void recogerPasajerosAutobus(Autobus a){
        int pasajerosParada = (int) (Math.random()*51);
        System.out.println("El autobus " + a.getIdentificador() + " va a recoger a " + pasajerosParada + " personas.");
    }
    public void irAeropuertoAutobus(Autobus a){
        try {
            System.out.println("El autobus " + a.getIdentificador() + " va hacia el aeropuerto de " + nombre);
            Thread.sleep((int) (Math.random()*5001)+5000);
            System.out.println("El autobus " + a.getIdentificador() + " ha llegado al aeropuerto " + nombre);
        } catch (Exception e) {
        }
    }
    
    public void bajarPasajerosAutobus(Autobus a){
        a.setPasajeros(0);
        System.out.println("Se han bajado todos los pasajeros del autobus " + a.getIdentificador());
    }
    
    public String getNombre(){
        return nombre;
    }
}
