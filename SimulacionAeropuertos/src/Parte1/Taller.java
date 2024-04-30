/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sombe
 */
public class Taller {
    
    protected Aeropuerto aeropuerto;
    
    private ArrayList<Avion> aviones = new ArrayList<>();
    
    private Semaphore espaciosTaller = new Semaphore(20, true);
    private Semaphore control = new Semaphore(1, true);
    
    
    public Taller(Aeropuerto aeropuerto){
        
        this.aeropuerto = aeropuerto;
    }
    
    public void entrarTaller (Avion avion){
        
        try {
            espaciosTaller.acquire();
            control.acquire();
            System.out.println(avion.getIdentificador() + " entrando TALLER");
            Thread.sleep(1000);
            aviones.add(avion);
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
            control.release();
            espaciosTaller.release();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
