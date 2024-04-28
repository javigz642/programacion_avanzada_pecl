package Parte1;

import java.util.concurrent.atomic.AtomicInteger;
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

    protected Ciudad ciudad;

    protected Hangar hangar = new Hangar(this);
    protected AreaEstacionamiento areaEstacionamiento = new AreaEstacionamiento(this);
    protected Taller taller = new Taller(this);

    private AtomicInteger personasDentro;

    private Lock puertaSalida = new ReentrantLock();

    public Aeropuerto(Ciudad ciudad) {

        this.ciudad = ciudad;
    }

    public void recogerPasajerosAutobus(Autobus a) {

        int pasajerosParada;
        do {
            pasajerosParada = (int) (Math.random() * 51);
        } while (pasajerosParada > personasDentro.get());

        puertaSalida.lock();

        try {
            System.out.println("Hay " + personasDentro + " dentro");

            if (personasDentro.get() > 0) {
                personasDentro.addAndGet(pasajerosParada);
                System.out.println("El autobus " + a.getIdentificador() + " va a recoger a " + pasajerosParada + " personas.");

            }
            Thread.sleep((int) (Math.random() * 3000) + 2001);
        } catch (Exception e) {

        } finally {
            puertaSalida.unlock();
            
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
        puertaSalida.lock();
        try {
            personasDentro.addAndGet(a.getPasajeros());
        } catch (Exception e) {
        } finally {
            puertaSalida.unlock();
        }
    }

    public int recogerPasajerosAvion(int pasajeros) throws InterruptedException {

        int pasajerosCogidos;

        if (personasDentro.get() >= pasajeros) {
            personasDentro.addAndGet(pasajeros);
            pasajerosCogidos = pasajeros;
        } else {
            pasajerosCogidos = personasDentro.get();
            personasDentro.set(0);

        }
        Thread.sleep((int) (Math.random() * 2000) + 1001);
        return pasajerosCogidos;
    }

    public int getPersonasDentro() {
        return personasDentro.get();
    }

}
