/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Miguel
 */
public class AreaEstacionamiento {
    
    private ArrayList<Avion> aviones = new ArrayList<>();
    
    private Semaphore control = new Semaphore(1);
    
    private JTextField jTextFieldAreaEstacionamientoAeropuerto;

    public AreaEstacionamiento(JTextField jTextFieldAreaEstacionamientoAeropuerto) {
        this.jTextFieldAreaEstacionamientoAeropuerto = jTextFieldAreaEstacionamientoAeropuerto;
    }
    

    public void entrarArea(Avion avion) {

        try {
            control.acquire();
            aviones.add(avion);
            imprimirArrayAviones(jTextFieldAreaEstacionamientoAeropuerto, aviones);
            control.release();
            System.out.println(avion.getIdentificador() + " entrando ESTACIONAMIENTO");
            if(!avion.isEmbarque()){
                System.out.println(avion.getIdentificador() + " realizando comprobaciones ESTACIONAMIENTO (tras Desembarque)");
                Thread.sleep((int) (Math.random() * 4000) + 1001); 
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void salirArea(Avion avion) {

        try {
            control.acquire();
            aviones.remove(avion);
            imprimirArrayAviones(jTextFieldAreaEstacionamientoAeropuerto, aviones);
            control.release();
            System.out.println(avion.getIdentificador() + " saliendo ESTACIONAMIENTO");
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
        System.out.println(stringAux);
    }

}
