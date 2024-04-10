package Parte1;

import java.util.concurrent.locks.Lock;

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
    private Lock puertaSalida;

    public void recogerPasajerosAutobus(Autobus a) {
        int pasajerosParada = (int) (Math.random() * 50);
        System.out.println("El autobus va a recoger a " + pasajerosParada + " personas.");
    }

    public void irCiudadAutobus(Autobus a) {
        
        try {
            System.out.println("El autobus va hacia la ciudad.");
            Thread.sleep((int) (Math.random() * 5001) + 5000);
            System.out.println("El autobus ha llegado a la ciudad");
        } catch (Exception e) {
        }
    }
    public void bajarPersonas(Autobus a){
        puertaSalida.lock();
        try {
            personasDentro += a.getPasajeros();
        } catch (Exception e) {
        }finally{
            puertaSalida.unlock();
        }
    }
}
