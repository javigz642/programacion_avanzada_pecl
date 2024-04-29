/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextField;

/**
 *
 * @author ediso
 */
public class Ciudad {
    
    protected Aeropuerto aeropuerto = new Aeropuerto(this);
    
    private String nombre;
    private ArrayList<Autobus> autobusesCirculando = new ArrayList<>();
    
    private Semaphore lockAutobusesCirculando = new Semaphore(1);
    

    //declaracion de los text field;
    JTextField jTextFieldTransferCiudadAutobusMadrid;
    JTextField jTextFieldTransferCiudadAutobusBarcelona;

    public Ciudad(String nombre) {
        this.nombre = nombre;

    }

    public void recogerPasajerosAutobus(Autobus a) {
        int pasajerosParada = (int) (Math.random() * 51);
        System.out.println("El autobus " + a.getIdentificador() + " va a recoger a " + pasajerosParada + " personas.");
    }

    public void irAeropuertoAutobus(Autobus a) {

        try {
            lockAutobusesCirculando.acquire();
            autobusesCirculando.add(a);
            System.out.println("El autobus " + a.getIdentificador() + " va hacia el aeropuerto de " + nombre);
            lockAutobusesCirculando.release();
        } catch (Exception e) {
        } finally {
            imprimirArrayAutobus();
            dormirAutobus(50000, 10000);
            System.out.println("El autobus " + a.getIdentificador() + " ha llegado al aeropuerto " + nombre);
        }
    }

    public void bajarPasajerosAutobus(Autobus a) {
        a.setPasajeros(0);
        System.out.println("Se han bajado todos los pasajeros del autobus " + a.getIdentificador());
    }

    public String getNombre() {
        return nombre;
    }

    public void dormirAutobus(int desde, int hasta) {
        try {
            int desdeAux = hasta - desde + 1;
            Thread.sleep((int) (Math.random() * desdeAux) + desde);
        } catch (Exception e) {
        }
    }
    
    public void imprimirArrayAutobus(){
        for (int i = 0; i < autobusesCirculando.size(); i++) {
            System.out.println(autobusesCirculando.get(i).getIdentificador() + " circulando");
        }
        System.out.println("");
    }
}
