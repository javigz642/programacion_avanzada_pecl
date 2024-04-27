package Parte1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ediso
 */
public class Aeropuerto extends Thread {

    private int personasDentro;
    private Lock puertaSalida ;
    private Condition vacio;
    private Ciudad ciudad;

    public Aeropuerto(int personasDentro, Ciudad ciudad) {
        puertaSalida = new ReentrantLock();
        vacio = puertaSalida.newCondition();
        this.personasDentro = personasDentro;
        this.ciudad = ciudad;
    }

    public void recogerPasajerosAutobus(Autobus a) {

        int pasajerosParada;
        do{
         pasajerosParada = (int) (Math.random() * 50);
        }while(pasajerosParada > personasDentro);
        
        puertaSalida.lock();
        
        try {
            System.out.println("Hay "+  personasDentro+ " dentro");
            while (personasDentro == 0) { 
                System.out.println("El autobus " + a.getIdentificador() + "se queda esperando a mas pasajeros");
                vacio.await(); 
            }
            
            personasDentro -= pasajerosParada;
            System.out.println("El autobus " + a.getIdentificador() + " va a recoger a " + pasajerosParada + " personas.");
            Thread.sleep((int) (Math.random() * 3001) + 2000);
        } catch (Exception e) {
            
        }finally{
            puertaSalida.unlock();
        }  
    }

    public void irCiudadAutobus(Autobus a) {
        
        try {
            System.out.println("El autobus " + a.getIdentificador() + " va hacia la ciudad de " + ciudad.getNombre());
            Thread.sleep((int) (Math.random() * 5001) + 5000);
            System.out.println("El autobus " + a.getIdentificador() + " ha llegado a la ciudad");
        } catch (Exception e) {
        }
    }
    
    public void bajarPasajerosAutobus(Autobus a){
        puertaSalida.lock();
        try {
            personasDentro += a.getPasajeros();
        } catch (Exception e) {
        }finally{
            puertaSalida.unlock();
        }
    }
}
