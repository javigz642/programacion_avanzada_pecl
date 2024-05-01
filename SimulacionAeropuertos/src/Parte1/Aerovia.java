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
public class Aerovia {

    private ArrayList<Avion> aviones = new ArrayList<>();
    
    private Semaphore control = new Semaphore(1, true);
    private Semaphore control2aviones = new Semaphore(2, true);

    private JTextField jTextFieldAeroviaMadrid_Barcelona;
    private JTextField jTextFieldAeroviaBarcelona_Madrid;
    
    private TextLog logger;

    public Aerovia(JTextField jTextFieldAeroviaMadrid_Barcelona, JTextField jTextFieldAeroviaBarcelona_Madrid, TextLog logger) {
        this.jTextFieldAeroviaMadrid_Barcelona = jTextFieldAeroviaMadrid_Barcelona;
        this.jTextFieldAeroviaBarcelona_Madrid = jTextFieldAeroviaBarcelona_Madrid;
        this.logger = logger;
    }
    
    

    public void entrarAerovia(Avion avion) {

        try {
            control2aviones.acquire();
            control.acquire();
            aviones.add(avion);
            if (avion.getOrigen().getNombre().equals("Madrid")) {
                imprimirArrayAviones(jTextFieldAeroviaMadrid_Barcelona, aviones);
            } else {
                imprimirArrayAviones(jTextFieldAeroviaBarcelona_Madrid, aviones);
            }

            control.release();
            System.out.println(avion.getIdentificador() + " entrando AEROVIA " + avion.getOrigen().getNombre() + "-" + avion.getDestino().getNombre());
            Thread.sleep((int) (Math.random() * 15000) + 15001);
        } catch (InterruptedException ex) {
            Logger.getLogger(Aerovia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void abandonarAerovia(Avion avion) {

        try {
            control.acquire();
            aviones.remove(avion);
            if (avion.getOrigen().getNombre().equals("Madrid")) {
                imprimirArrayAviones(jTextFieldAeroviaMadrid_Barcelona, aviones);
            } else {
                imprimirArrayAviones(jTextFieldAeroviaBarcelona_Madrid, aviones);
            }
            control.release();
            control2aviones.release();
            System.out.println(avion.getIdentificador() + " abandonando AEROVIA " + avion.getDestino().getNombre() + "-" + avion.getOrigen().getNombre());
        } catch (InterruptedException ex) {
            Logger.getLogger(Aerovia.class.getName()).log(Level.SEVERE, null, ex);
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
