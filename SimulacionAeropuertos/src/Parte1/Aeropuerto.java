package Parte1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ediso
 */
public class Aeropuerto extends Thread {

    protected Ciudad ciudad;

    protected Hangar hangar = new Hangar(this);
    protected AreaEstacionamiento areaEstacionamiento = new AreaEstacionamiento(this);
    protected Taller taller = new Taller(this);
    protected PuertaEmbarque puertaEmbarque = new PuertaEmbarque(this);
    protected AreaRodaje areaRodaje = new AreaRodaje(this);
    protected Pista pista = new Pista(this);
    protected Aerovia aerovia = new Aerovia(this);

    private int personasDentro = 300;
    
    private Semaphore control = new Semaphore(1, true);
    

    

    public Aeropuerto(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public void recogerPasajerosAutobus(Autobus a) {
        int pasajerosParada;
        do {
            pasajerosParada = (int) (Math.random() * 51);
        } while (pasajerosParada > personasDentro);


        try {
            System.out.println("Hay " + personasDentro + " dentro");

            if (personasDentro > 0) {
                control.acquire();
                personasDentro -= pasajerosParada;
                System.out.println("El autobus " + a.getIdentificador() + " va a recoger a " + pasajerosParada + " personas.");
                System.out.println("En el aeropuerto hay " + personasDentro + " personas.");
                control.release();
            }
            Thread.sleep((int) (Math.random() * 3000) + 2001);
        } catch (Exception e) {

        } finally {

            
        }
    }

    public void irCiudadAutobus(Autobus a) {

        try {
            System.out.println("El autobus " + a.getIdentificador() + " va hacia la ciudad de " + ciudad.getNombre());
            Thread.sleep((int) (Math.random() * 5000) + 5001);
            System.out.println("El autobus " + a.getIdentificador() + " ha llegado a la ciudad");
        } catch (Exception e) {
        }
    }

    public void bajarPasajerosAutobus(Autobus a) {

        try {
            control.acquire();
            personasDentro += a.getPasajeros();
        } catch (Exception e) {
        } finally {
            control.release();
        }
    }

    public int recogerPasajerosAvion(int pasajeros){
        
        int pasajerosCogidos = 0;

        try {
            control.acquire();
            if (personasDentro >= pasajeros) {
                System.out.println("PERSONAS DENTRO: " + personasDentro);
                personasDentro -= pasajeros;
                System.out.println("PERSONAS DENTRO 2: " + personasDentro);
                pasajerosCogidos = pasajeros;
                System.out.println("Cogiendo " + pasajerosCogidos);
            } else {
                
                pasajerosCogidos = personasDentro;
                System.out.println("Cogiendo menos del maximo: " + pasajerosCogidos);
                personasDentro = 0;  
                control.release();
            }
            
            if (pasajerosCogidos > 0){
                System.out.println("Esperando a que se suban los mamertos");
                Thread.sleep((int) (Math.random() * 2000) + 1001);
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Aeropuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pasajerosCogidos;
    }
    
    public void bajarPasajerosAvion(int pasajeros){
        try {
            control.acquire();
            personasDentro += pasajeros;
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Aeropuerto.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            control.release();
        }
    }

    public int getPersonasDentro() {
        return personasDentro;
    }

}
