/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author sombe
 */
public class Hangar {

    private ArrayList<Avion> aviones = new ArrayList<>();
    private int avionesDentro;
    private String nombreCiudad;
    private TextLog logger;
    
    private Semaphore control = new Semaphore(1);
    
    private JTextField jTextFieldHangarAeropuerto;  

    public Hangar(JTextField jTextFieldHangarAeropuerto, String nombreCiudad, TextLog logger) {
        this.nombreCiudad = nombreCiudad;
        this.jTextFieldHangarAeropuerto = jTextFieldHangarAeropuerto;
        this.logger = logger;
    }
    
      public void entrarHangar(Avion avion){
        
        
        try {
            control.acquire();
            aviones.add(avion);
            avionesDentro++;
            imprimirArrayAviones(jTextFieldHangarAeropuerto, aviones);
            control.release();
            //System.out.println(avion.getIdentificador()+ " entrando HANGAR");

        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void salirHangar(Avion avion){
        
        try {
            control.acquire();
            aviones.remove(avion);
            avionesDentro--;
            imprimirArrayAviones(jTextFieldHangarAeropuerto, aviones);
            control.release();
            //System.out.println(avion.getIdentificador()+ " saliendo HANGAR");
        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void reposar (Avion avion){
        
        try {
            control.acquire();
            aviones.add(avion);
            avionesDentro++;
            imprimirArrayAviones(jTextFieldHangarAeropuerto, aviones);
            control.release();
            //System.out.println(avion.getIdentificador()+ " reposando HANGAR");
            Thread.sleep((int) (Math.random() * 15000) + 15001);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void imprimirArrayAviones(JTextField jTextFieldDestino,ArrayList<Avion> arrayAviones) {
        String stringAux = "";
        for (int i = 0; i < arrayAviones.size(); i++) {
            stringAux += arrayAviones.get(i).getIdentificador() + " / ";
        }
        jTextFieldDestino.setText(stringAux);
        //System.out.println(stringAux);
    }
    
        public int getAvionesDentro() {
        int avionesAux = 0;
  
            avionesAux = avionesDentro;
  
        return avionesAux;
    }

    
}
