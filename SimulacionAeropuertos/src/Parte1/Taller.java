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
 * @author sombe
 */
public class Taller {
    

    
    private ArrayList<Avion> aviones = new ArrayList<>();
    
    private Semaphore espaciosTaller = new Semaphore(20, true);
    private Semaphore control = new Semaphore(1, true);
    
        private JTextField jTextFieldTallerAeropuerto;

    public Taller(JTextField jTextFieldTallerAeropuerto) {
        this.jTextFieldTallerAeropuerto = jTextFieldTallerAeropuerto;
    }
    

    
    public void entrarTaller (Avion avion){
        
        try {
            espaciosTaller.acquire();
            control.acquire();
            System.out.println(avion.getIdentificador() + " entrando TALLER");
            Thread.sleep(1000);
            aviones.add(avion);
            imprimirArrayAviones(jTextFieldTallerAeropuerto, aviones);
            control.release();
            if (avion.getVuelos()%15 == 0){
                System.out.println(avion.getIdentificador() + " realizando REVISION PROFUNDA");
                Thread.sleep((int) (Math.random() * 5000) + 5001);
            }
            else{
                System.out.println(avion.getIdentificador() + " realizando REVISION NORMAL");
                Thread.sleep((int) (Math.random() * 4000) + 1001);
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void salirTaller (Avion avion){
        
        try {
            control.acquire();
            System.out.println(avion.getIdentificador() + " saliendo TALLER");
            Thread.sleep(1000);
            aviones.remove(avion);
            imprimirArrayAviones(jTextFieldTallerAeropuerto, aviones);
            control.release();
            espaciosTaller.release();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        public void imprimirArrayAviones(JTextField jTextFieldDestino, ArrayList<Avion> arrayAviones) {
        String stringAux = "";
        for (int i = 0; i < arrayAviones.size(); i++) {
            stringAux += arrayAviones.get(i).getIdentificador() + " / ";
        }
        jTextFieldDestino.setText(stringAux);
        System.out.println(stringAux);
    }
    
}
