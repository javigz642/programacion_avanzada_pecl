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
 * Clase que representa un área de estacionamiento para aviones en un
 * aeropuerto.
 */
public class AreaEstacionamiento {

    private ArrayList<Avion> aviones = new ArrayList<>();
    private int avionesDentro;
    private String nombreCiudad;

    private Semaphore control = new Semaphore(1);
    
    // Campos de la interfaz de usuario relacionado con el AreaEstacionamiento
    private JTextField jTextFieldAreaEstacionamientoAeropuerto;
    
    // Logger para registrar eventos en el AreaEstacionamiento
    private TextLog logger;

    public AreaEstacionamiento(JTextField jTextFieldAreaEstacionamientoAeropuerto, String nombreCiudad, TextLog logger) {
        this.jTextFieldAreaEstacionamientoAeropuerto = jTextFieldAreaEstacionamientoAeropuerto;
        this.nombreCiudad = nombreCiudad;
        this.logger = logger;
    }

    /**
     * Método para que un avión entre en el área de estacionamiento.
     *
     * @param avion Avión que entra en el área de estacionamiento.
     */
    public void entrarAreaEstacionamiento(Avion avion) {

        try {
            control.acquire();
            aviones.add(avion);
            avionesDentro++;
            imprimirArrayAviones(jTextFieldAreaEstacionamientoAeropuerto, aviones);
            control.release();
            logger.log("Avion " + avion.getIdentificador() + " entrando ESTACIONAMIENTO ", nombreCiudad);

            if (!avion.isEmbarque()) {
                logger.log("Avion " + avion.getIdentificador() + " realizando comprobaciones ESTACIONAMIENTO (tras Desembarque) ", nombreCiudad);
                Thread.sleep((int) (Math.random() * 4000) + 1001);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para que un avión salga del área de estacionamiento.
     *
     * @param avion Avión que sale del área de estacionamiento.
     */
    public void salirAreaEstacionamiento(Avion avion, Paso paso) {

        try {
            control.acquire();
            aviones.remove(avion);
            avionesDentro--;
            paso.mirar();
            imprimirArrayAviones(jTextFieldAreaEstacionamientoAeropuerto, aviones);
            control.release();
            logger.log("Avion " + avion.getIdentificador() + " saliendo ESTACIONAMIENTO ", nombreCiudad);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para imprimir la lista de aviones en el área de estacionamiento en
     * un JTextField.
     *
     * @param jTextFieldDestino JTextField donde se imprimirá la lista de
     * aviones.
     * @param arrayAviones ArrayList de aviones en el área de estacionamiento.
     */
    public void imprimirArrayAviones(JTextField jTextFieldDestino, ArrayList<Avion> arrayAviones) {
        String stringAux = "";
        for (int i = 0; i < arrayAviones.size(); i++) {
            stringAux += arrayAviones.get(i).getIdentificador() + " / ";
        }
        jTextFieldDestino.setText(stringAux);
    }

    //Método getter
    public int getAvionesDentro() {
        int avionesAux = 0;

        avionesAux = avionesDentro;

        return avionesAux;
    }
}
