/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 * Clase que representa una aerovía donde los aviones pueden entrar y abandonar.
 */
public class Aerovia {

    private ArrayList<Avion> aviones = new ArrayList<>();
    private String avionesDentro; 
    
    private Semaphore control = new Semaphore(1); 

    // Campos de la interfaz de usuario relacionados con la aerovía
    private JTextField jTextFieldAeroviaMadrid_Barcelona;
    private JTextField jTextFieldAeroviaBarcelona_Madrid;

    // Logger para registrar eventos en la aerovía
    private TextLog logger;

    public Aerovia(JTextField jTextFieldAeroviaMadrid_Barcelona, JTextField jTextFieldAeroviaBarcelona_Madrid, TextLog logger) {
        this.jTextFieldAeroviaMadrid_Barcelona = jTextFieldAeroviaMadrid_Barcelona;
        this.jTextFieldAeroviaBarcelona_Madrid = jTextFieldAeroviaBarcelona_Madrid;
        this.logger = logger;
    }

    /**
     * Método para que un avión entre en la aerovía.
     *
     * @param avion Avión que entra en la aerovía.
     */
    public void entrarAerovia(Avion avion) {

        try {
            control.acquire();
            aviones.add(avion);
            if (avion.getOrigen().getNombre().equals("Madrid")) {
                jTextFieldAeroviaMadrid_Barcelona.setText(getStringArrayAviones(aviones));

            } else {
                jTextFieldAeroviaBarcelona_Madrid.setText(getStringArrayAviones(aviones));
            }

            control.release();
            logger.log("Avion " + avion.getIdentificador() + " entrando AEROVIA " + avion.getOrigen().getNombre() + "-" + avion.getDestino().getNombre() + " ", avion.getOrigen().getNombre());
            Thread.sleep((int) (Math.random() * 15001) + 15000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Aerovia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para que un avión abandone la aerovía.
     *
     * @param avion Avión que abandona la aerovía.
     */
    public void abandonarAerovia(Avion avion) {

        try {
            control.acquire();
            aviones.remove(avion);
            if (avion.getOrigen().getNombre().equals("Madrid")) {
                jTextFieldAeroviaMadrid_Barcelona.setText(getStringArrayAviones(aviones));
            } else {
                jTextFieldAeroviaBarcelona_Madrid.setText(getStringArrayAviones(aviones));
            }
            control.release();
            logger.log("Avion " + avion.getIdentificador() + " abandonando AEROVIA " + avion.getOrigen().getNombre() + "-" + avion.getDestino().getNombre() + " ", avion.getDestino().getNombre());
        } catch (InterruptedException ex) {
            Logger.getLogger(Aerovia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para obtener una representación en cadena de los aviones en la
     * aerovía.
     *
     * @param arrayAviones ArrayList de aviones.
     * @return Una cadena que representa los aviones dentro de la aerovía.
     */
    public String getStringArrayAviones(ArrayList<Avion> arrayAviones) {
        String stringAux = "";
        String identificadorAux = "";
        int numeroPasejosAvionAux = 0;
        for (int i = 0; i < arrayAviones.size(); i++) {
            identificadorAux = arrayAviones.get(i).getIdentificador();
            numeroPasejosAvionAux = arrayAviones.get(i).getPasajerosActual();
            stringAux += identificadorAux + "(" + numeroPasejosAvionAux + ")" + " / ";
        }
        avionesDentro = stringAux;
        return avionesDentro;
    }

    //Método getter
    public String getStringArrayAviones() {
        return avionesDentro;
    }

}
