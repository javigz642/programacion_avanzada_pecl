package Parte1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ediso
 */
public class Aeropuerto extends Thread {



    private int personasDentro = 3000;
    

    //declaracion de los jtextField


    private JTextField jTextFieldAerovia;
    private JTextField jTextFieldAreaEstacionamientoAeropuerto;
    private JTextField jTextFieldAreaRodajeAeropuerto;
    private JTextField jTextFieldGate1Aeropuerto;
    private JTextField jTextFieldGate2Aeropuerto;
    private JTextField jTextFieldGate3Aeropuerto;
    private JTextField jTextFieldGate4Aeropuerto;
    private JTextField jTextFieldGate5Aeropuerto;
    private JTextField jTextFieldGate6Aeropuerto;
    private JTextField jTextFieldHangarAeropuerto;
    private JTextField jTextFieldNumeroPasajerosAeropuerto;
    private JTextField jTextFieldPista1Aeropuerto;
    private JTextField jTextFieldPista2Aeropuerto;
    private JTextField jTextFieldPista3Aeropuerto;
    private JTextField jTextFieldPista4Aeropuerto;
    private JTextField jTextFieldTallerAeropuerto;

    protected Hangar hangar = new Hangar(this, jTextFieldHangarAeropuerto);
    protected AreaEstacionamiento areaEstacionamiento = new AreaEstacionamiento(this);
    protected Taller taller = new Taller(this);
    protected PuertaEmbarque puertaEmbarque = new PuertaEmbarque(this);
    protected AreaRodaje areaRodaje = new AreaRodaje(this);
    protected Pista pista = new Pista(this);
    protected Aerovia aerovia = new Aerovia(this);

    private Semaphore control = new Semaphore(1, true);

    public Aeropuerto(JTextField jTextFieldAerovia, JTextField jTextFieldAreaEstacionamientoAeropuerto, JTextField jTextFieldAreaRodajeAeropuerto, JTextField jTextFieldGate1Aeropuerto, JTextField jTextFieldGate2Aeropuerto, JTextField jTextFieldGate3Aeropuerto, JTextField jTextFieldGate4Aeropuerto, JTextField jTextFieldGate5Aeropuerto, JTextField jTextFieldGate6Aeropuerto, JTextField jTextFieldHangarAeropuerto, JTextField jTextFieldNumeroPasajerosAeropuerto, JTextField jTextFieldPista1Aeropuerto, JTextField jTextFieldPista2Aeropuerto, JTextField jTextFieldPista3Aeropuerto, JTextField jTextFieldPista4Aeropuerto, JTextField jTextFieldTallerAeropuerto) {
        this.jTextFieldAerovia = jTextFieldAerovia;
        this.jTextFieldAreaEstacionamientoAeropuerto = jTextFieldAreaEstacionamientoAeropuerto;
        this.jTextFieldAreaRodajeAeropuerto = jTextFieldAreaRodajeAeropuerto;
        this.jTextFieldGate1Aeropuerto = jTextFieldGate1Aeropuerto;
        this.jTextFieldGate2Aeropuerto = jTextFieldGate2Aeropuerto;
        this.jTextFieldGate3Aeropuerto = jTextFieldGate3Aeropuerto;
        this.jTextFieldGate4Aeropuerto = jTextFieldGate4Aeropuerto;
        this.jTextFieldGate5Aeropuerto = jTextFieldGate5Aeropuerto;
        this.jTextFieldGate6Aeropuerto = jTextFieldGate6Aeropuerto;
        this.jTextFieldHangarAeropuerto = jTextFieldHangarAeropuerto;
        this.jTextFieldNumeroPasajerosAeropuerto = jTextFieldNumeroPasajerosAeropuerto;
        this.jTextFieldPista1Aeropuerto = jTextFieldPista1Aeropuerto;
        this.jTextFieldPista2Aeropuerto = jTextFieldPista2Aeropuerto;
        this.jTextFieldPista3Aeropuerto = jTextFieldPista3Aeropuerto;
        this.jTextFieldPista4Aeropuerto = jTextFieldPista4Aeropuerto;
        this.jTextFieldTallerAeropuerto = jTextFieldTallerAeropuerto;
        
        imprimirPasajerosEnAeropuerto(jTextFieldNumeroPasajerosAeropuerto, personasDentro);
    }



    
    public void recogerPasajerosAutobus(Autobus a) {
        int pasajerosParada;
        do {
            pasajerosParada = (int) (Math.random() * 51);
        } while (pasajerosParada > personasDentro);

        try {
            System.out.println("Hay " + personasDentro + " dentro");
            Thread.sleep((int) (Math.random() * 3000) + 2001);
            if (personasDentro > 0) {
                control.acquire();
                personasDentro -= pasajerosParada;
                imprimirPasajerosEnAeropuerto(jTextFieldNumeroPasajerosAeropuerto, personasDentro);
                System.out.println("El autobus " + a.getIdentificador() + " va a recoger a " + pasajerosParada + " personas.");
                System.out.println("En el aeropuerto hay " + personasDentro + " personas.");
                control.release();
            }

        } catch (Exception e) {

        } 
    }

    public void irCiudadAutobus(Autobus a) {

        try {
            System.out.println("El autobus " + a.getIdentificador() + " va hacia la ciudad de " + a.getCiudad().getNombre());
            Thread.sleep((int) (Math.random() * 5000) + 5001);
            System.out.println("El autobus " + a.getIdentificador() + " ha llegado a la ciudad");
        } catch (Exception e) {
        }
    }

    public void bajarPasajerosAutobus(Autobus a) {

        try {
            control.acquire();
            personasDentro += a.getPasajeros();
            imprimirPasajerosEnAeropuerto(jTextFieldNumeroPasajerosAeropuerto, personasDentro);
        } catch (Exception e) {
        } finally {
            control.release();
        }
    }

    public int recogerPasajerosAvion(int pasajeros) {

        int pasajerosCogidos = 0;
        
        try {
            control.acquire();
            if (personasDentro >= pasajeros) {
                //System.out.println("Personas en el aeropuerto: " + personasDentro);
                personasDentro -= pasajeros;
                //System.out.println("Personas en el aeropuerto tras EMBARQUE: " + personasDentro);
                pasajerosCogidos = pasajeros;
                //System.out.println("Cogiendo " + pasajerosCogidos);
            } else {

                pasajerosCogidos = personasDentro;
                //System.out.println("Cogiendo menos del maximo: " + pasajerosCogidos);
                personasDentro = 0;     
            }
            control.release();
            
            if (pasajerosCogidos > 0){
                System.out.println("Esperando a que se suban los mamertos");
                Thread.sleep((int) (Math.random() * 2000) + 1001);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Aeropuerto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pasajerosCogidos;
    }

    public void bajarPasajerosAvion(int pasajeros) {
        try {
            control.acquire();
            //System.out.println("DENTRO: " + personasDentro);
            personasDentro += pasajeros;
            //System.out.println("DENTRO 2: " + personasDentro);
            control.release();
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Aeropuerto.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
        }
    }
    public void imprimirPasajerosEnAeropuerto(JTextField j,int personas){
        j.setText(personas+"");
    }
    public int getPersonasDentro() {
        return personasDentro;
    }

}
