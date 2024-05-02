/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author ediso
 */
public class Ciudad {

    protected Aeropuerto aeropuerto;

    private String nombre;
    private ArrayList<Autobus> autobusesHaciaAeropuerto = new ArrayList<>();
    private ArrayList<Autobus> autobusesHaciaCiudad = new ArrayList<>();
    
    Random random = new Random();
    int pasajerosParada;

    private Semaphore SemAutobusesHaciaAeropuerto = new Semaphore(1);
    private Semaphore SemAutobusesHaciaCiudad = new Semaphore(1);

    //declaracion de los text field;
    JTextField jTextFieldTransferAeropuertoAutobus;
    JTextField jTextFieldTransferCiudadAutobus;

    public Ciudad(String nombre,Aeropuerto aeropuerto, JTextField jTextFieldTransferAeropuertoAutobus, JTextField jTextFieldTransferCiudadAutobus) {
        this.nombre = nombre;
        this.aeropuerto = aeropuerto;
        this.jTextFieldTransferAeropuertoAutobus = jTextFieldTransferAeropuertoAutobus;
        this.jTextFieldTransferCiudadAutobus = jTextFieldTransferCiudadAutobus;
    }

    public void recogerPasajerosCiudadAutobus(Autobus a) {
        pasajerosParada = random.nextInt(51);
        System.out.println("El autobus " + a.getIdentificador() + " va a recoger a " + pasajerosParada + " personas.");
    }

    public void irAeropuertoAutobus(Autobus a, Paso paso) {

        try {
            SemAutobusesHaciaAeropuerto.acquire();

            autobusesHaciaAeropuerto.add(a);
            imprimirArrayAutobus(jTextFieldTransferAeropuertoAutobus,autobusesHaciaAeropuerto);
            paso.mirar();
            System.out.println("El autobus " + a.getIdentificador() + " va hacia el aeropuerto de " + nombre);
            SemAutobusesHaciaAeropuerto.release();
            dormirAutobus(5000, 10000);
            paso.mirar();
            SemAutobusesHaciaAeropuerto.acquire();
            autobusesHaciaAeropuerto.remove(a);
            SemAutobusesHaciaAeropuerto.release();

        } catch (Exception e) {
        } finally {

            System.out.println("El autobus " + a.getIdentificador() + " ha llegado al aeropuerto " + nombre);
        }
    }

    public void bajarPasajerosAlAeropuertoAutobus(Autobus a,Paso paso) {
        try {
            SemAutobusesHaciaAeropuerto.acquire();
            autobusesHaciaAeropuerto.remove(a);
            aeropuerto.bajarPasajerosAutobus(a);
            paso.mirar();
            imprimirArrayAutobus(jTextFieldTransferAeropuertoAutobus, autobusesHaciaAeropuerto);
            SemAutobusesHaciaAeropuerto.release();

        } catch (Exception e) {
        } finally {

        }
        a.setPasajeros(0);
        System.out.println("Se han bajado todos los pasajeros del autobus " + a.getIdentificador());
    }

    public void recogerPasajerosAeropuertoAutobus(Autobus a) {
        try {
            aeropuerto.recogerPasajerosAutobus(a);

        } catch (Exception e) {
        } finally {

        }

    }

    public void irCiudadAutobus(Autobus a,Paso paso) {
        try {
            SemAutobusesHaciaCiudad.acquire();
            autobusesHaciaCiudad.add(a);
            paso.mirar();
            imprimirArrayAutobus(jTextFieldTransferCiudadAutobus, autobusesHaciaCiudad);
            System.out.println("El autobus " + a.getIdentificador() + " va hacia la ciudad de " + nombre);
            SemAutobusesHaciaCiudad.release();
            dormirAutobus(5000, 10000);
            SemAutobusesHaciaCiudad.acquire();
            autobusesHaciaCiudad.remove(a);
            paso.mirar();
            imprimirArrayAutobus(jTextFieldTransferCiudadAutobus, autobusesHaciaCiudad);
            SemAutobusesHaciaCiudad.release();

        } catch (Exception e) {
        } finally {

        }
        a.setPasajeros(0);
        System.out.println("Se han bajado todos los pasajeros del autobus " + a.getIdentificador());
    }

    public void bajarPasajerosACiudadAutobus(Autobus a) {
        try {

            a.setPasajeros(0);



        } catch (Exception e) {
        } finally {

            System.out.println("El autobus " + a.getIdentificador() + " ha llegado a su destino " + nombre);
        }
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

    public void imprimirArrayAutobus(JTextField jTextFieldDestino,ArrayList<Autobus> arrayBuses) {
        String stringAux = "";
        for (int i = 0; i < arrayBuses.size(); i++) {
            stringAux += arrayBuses.get(i).getIdentificador() + " / ";
            System.out.println(arrayBuses.get(i).getIdentificador() + " circulando");

        }
        jTextFieldDestino.setText(stringAux);
        System.out.println(stringAux);
    }
}
