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
 * Clase que representa un area de rodaje para aviones en un aeropuerto.
 */
public class AreaRodaje {
    
    private ArrayList<Avion> aviones = new ArrayList<>();
    private String nombreCiudad;
    private int avionesDentro;

    private Semaphore control = new Semaphore(1);
    
    // Campos de la interfaz de usuario relacionado con el AreaRodaje
    private JTextField jTextFieldAreaRodajeAeropuerto;
    
    // Logger para registrar eventos en el AreaRodaje
    private TextLog logger;

    public AreaRodaje(JTextField jTextFieldAreaRodajeAeropuerto, String nombreCiudad, TextLog logger) {
        this.jTextFieldAreaRodajeAeropuerto = jTextFieldAreaRodajeAeropuerto;
        this.nombreCiudad = nombreCiudad;
        this.logger = logger;
    }

    /**
     * Metodo para que un avion entre en el area de rodaje.
     *
     * @param avion Avion que entra en el area de rodaje.
     */
    public void entrarAreaRodaje(Avion avion) {

        try {
            control.acquire();
            aviones.add(avion);
            avionesDentro++;
            imprimirArrayAviones(jTextFieldAreaRodajeAeropuerto, aviones);
            control.release();
            logger.log("Avion " + avion.getIdentificador() + " entrando RODAJE ", nombreCiudad);

            if (avion.isEmbarque()) {

                logger.log("Avion " + avion.getIdentificador() + " haciendo comprobaciones RODAJE ", nombreCiudad);
                Thread.sleep((int) (Math.random() * 4000) + 1001);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(AreaRodaje.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo para que un avion salga del area de rodaje.
     *
     * @param avion Avion que sale del area de rodaje.
     */
    public void salirAreaRodaje(Avion avion) {
        try {
            control.acquire();
            aviones.remove(avion);
            avionesDentro--;
            imprimirArrayAviones(jTextFieldAreaRodajeAeropuerto, aviones);
            control.release();
            logger.log("Avion " + avion.getIdentificador() + " saliendo RODAJE ", nombreCiudad);

        } catch (InterruptedException ex) {
            Logger.getLogger(AreaRodaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para imprimir la lista de aviones en el área de rodaje en un
     * JTextField.
     *
     * @param jTextFieldDestino JTextField donde se imprimirá la lista de
     * aviones.
     * @param arrayAviones ArrayList de aviones en el área de rodaje.
     */
    public void imprimirArrayAviones(JTextField jTextFieldDestino, ArrayList<Avion> arrayAviones) {
        String stringAux = "";
        for (int i = 0; i < arrayAviones.size(); i++) {
            stringAux += arrayAviones.get(i).getIdentificador() + " / ";
        }
        jTextFieldDestino.setText(stringAux);
    }

    //Métodos getter
    public int getAvionesDentro() {
        int avionesAux = 0;

        avionesAux = avionesDentro;

        return avionesAux;
    }
}
