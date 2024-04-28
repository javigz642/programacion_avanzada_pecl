/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.ArrayList;

/**
 *
 * @author Miguel
 */
public class AreaRodaje {
    
    private Aeropuerto aeropuerto;
    
    private ArrayList<Avion> aviones = new ArrayList<>();

    public AreaRodaje(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    
    public void entrarAreaRodaje(Avion avion) throws InterruptedException{
        
        aviones.add(avion);
        System.out.println(avion.getIdentificador() + " ha entrado al area de rodaje del aeropuerto de " + aeropuerto.ciudad.getNombre());
        Thread.sleep((int) (Math.random() * 4000) + 1001);
        
    }
    
    
}
