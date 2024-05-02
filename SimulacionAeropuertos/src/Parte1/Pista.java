/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Miguel
 */
public class Pista {
    
    private Avion[] aviones = new Avion[4];
    
    
    private Semaphore pista1 = new Semaphore(1, true);
    private Semaphore pista2 = new Semaphore(1, true);
    private Semaphore pista3 = new Semaphore(1, true);
    private Semaphore pista4 = new Semaphore(1, true);
    private Semaphore[] pistas = {pista1, pista2, pista3, pista4};


    private JTextField jTextFieldPista1Aeropuerto;
    private JTextField jTextFieldPista2Aeropuerto;
    private JTextField jTextFieldPista3Aeropuerto;
    private JTextField jTextFieldPista4Aeropuerto;
    
    private String nombreCiudad;
    private TextLog logger;
    private PasoPistas pasoPistas;
    
    public Pista(JTextField jTextFieldPista1Aeropuerto, JTextField jTextFieldPista2Aeropuerto,
            JTextField jTextFieldPista3Aeropuerto, JTextField jTextFieldPista4Aeropuerto, 
            String nombreCiudad, TextLog logger, PasoPistas pasoPistas) {
        this.jTextFieldPista1Aeropuerto = jTextFieldPista1Aeropuerto;
        this.jTextFieldPista2Aeropuerto = jTextFieldPista2Aeropuerto;
        this.jTextFieldPista3Aeropuerto = jTextFieldPista3Aeropuerto;
        this.jTextFieldPista4Aeropuerto = jTextFieldPista4Aeropuerto;
        this.nombreCiudad = nombreCiudad;
        this.logger = logger;
        this.pasoPistas = pasoPistas;
    }
    
    

    
    public void pedirPista(Avion avion, Paso paso) {
        
        try {
            if (avion.isEmbarque()) {
                pasoPistas.mirar(avion.getNumero()%4);
                pistas[avion.getNumero()%4].acquire();
                aviones[avion.getNumero()%4] = avion;
                imprimirArrayAviones(aviones, avion.getNumero()%4);
                //System.out.println(avion.getIdentificador() + " tiene libre la PISTA " + (avion.getNumero()+1) + " para despegar"); 
                despegar(avion,paso);
                
            } else {
                pasoPistas.mirar(avion.getNumero()%4);
                while (!pistas[avion.getNumero()%4].tryAcquire()) {
                    System.out.println(avion.getIdentificador() + " dando un rodeo para tener pista libre");
                    Thread.sleep((int) (Math.random() * 4000) + 1001);
                    
                }
                pasoPistas.mirar(avion.getNumero()%4);
                
                aviones[avion.getNumero()%4] = avion;
                imprimirArrayAviones(aviones, avion.getNumero()%4);
                //System.out.println(avion.getIdentificador() + " tiene libre la PISTA " + (avion.getNumero()+1) + " para aterrizar");
                aterrizar(avion);
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void despegar(Avion avion,Paso paso){
        
        try {
            //System.out.println(avion.getIdentificador() + " realizando verificaciones PISTA");
            Thread.sleep((int) (Math.random() * 2000) + 1001);
            paso.mirar();
            //System.out.println(avion.getIdentificador() + " despegando con " + avion.getPasajerosActual() + " pasajeros desde la PISTA " + (avion.getNumero()+1));
            Thread.sleep((int) (Math.random() * 4000) + 1001);
            paso.mirar();
            //System.out.println(avion.getIdentificador() + " ha dejado libre la PISTA " + (avion.getNumero()+1));
            aviones[avion.getNumero()%4] = null;
            imprimirArrayAviones(aviones, avion.getNumero()%4);
            pistas[avion.getNumero()%4].release();
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    private void aterrizar(Avion avion) {

        try {
            imprimirArrayAviones(aviones, avion.getNumero()%4);
            //System.out.println(avion.getIdentificador() + " aterrizando con " + avion.getPasajerosActual() + " pasajeros en la PISTA " + (avion.getNumero()+1));
            Thread.sleep((int) (Math.random() * 4000) + 1001); 
            //System.out.println(avion.getIdentificador() + " ha dejado libre la PISTA " + (avion.getNumero()+1));
            aviones[avion.getNumero()%4] = null;
            imprimirArrayAviones(aviones, avion.getNumero()%4);
            pistas[avion.getNumero()%4].release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    public void imprimirArrayAviones(Avion[] arrayAviones, int numeroPista) {
        String stringAux = "";
        if(arrayAviones[numeroPista] != null){
            stringAux = arrayAviones[numeroPista].getIdentificador();
        }
         numeroPista++;       

        switch (numeroPista) {
            case 1:
                jTextFieldPista1Aeropuerto.setText(stringAux);
                break;
            case 2:
                jTextFieldPista2Aeropuerto.setText(stringAux);
                break;
            case 3:
                jTextFieldPista3Aeropuerto.setText(stringAux);
                break;
            case 4:
                jTextFieldPista4Aeropuerto.setText(stringAux);
                break;
            default:
                throw new AssertionError();
        }
        
        //System.out.println(stringAux);
    }
    public void cerrarPasoPistas(int x){
        pasoPistas.cerrar(x);
    }
    public void abrirPasoPistas(int x){
        pasoPistas.abrir(x);
    }
}
