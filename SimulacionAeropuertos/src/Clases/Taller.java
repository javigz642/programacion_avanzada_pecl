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
 * Clase que representa un taller de mantenimiento de aviones en un aeropuerto.
 * Los aviones pueden entrar y salir del taller para realizar revisiones y
 * reparaciones. El taller tiene un límite de capacidad de aviones que pueden
 * estar dentro simultáneamente.
 *
 */
public class Taller {

    private ArrayList<Avion> aviones = new ArrayList<>();
    private int avionesDentro;
    private String nombreCiudad;
    
    private Semaphore espaciosTaller = new Semaphore(20, true);
    private Semaphore control = new Semaphore(1, true);

    private JTextField jTextFieldTallerAeropuerto;

    private TextLog logger;
    private Paso paso;

    public Taller(JTextField jTextFieldTallerAeropuerto, String nombreCiudad, TextLog logger) {
        this.jTextFieldTallerAeropuerto = jTextFieldTallerAeropuerto;
        this.nombreCiudad = nombreCiudad;
        this.logger = logger;
    }

    /**
     * Método para que un avión entre en el taller. El avión realiza una
     * revisión o reparación, y se agrega al listado de aviones en el taller.
     *
     * @param avion Avión que entra en el taller.
     */
    public void entrarTaller(Avion avion, Paso paso) {

        try {
            espaciosTaller.acquire();
            control.acquire();
            logger.log("Avion " + avion.getIdentificador() + " entrando TALLER ", nombreCiudad);
            Thread.sleep(1000);
            avion.getDestino().getAeropuerto().getAreaEstacionamiento().salirAreaEstacionamiento(avion,paso);
            aviones.add(avion);
            imprimirArrayAviones(jTextFieldTallerAeropuerto, aviones);
            paso.mirar();

            avionesDentro++;
            control.release();
            if (avion.getVuelos() % 15 == 0) {
                logger.log("Avion " + avion.getIdentificador() + " realizando REVISION PRFUNDA ", nombreCiudad);
                Thread.sleep((int) (Math.random() * 5000) + 5001);
            } else {
                logger.log("Avion " + avion.getIdentificador() + " realizando REVISION NORMAL ", nombreCiudad);
                Thread.sleep((int) (Math.random() * 4000) + 1001);
            }
            paso.mirar();

        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para que un avión salga del taller. El avión sale del taller
     * después de la revisión o reparación y se elimina del listado de aviones
     * en el taller.
     *
     * @param avion Avión que sale del taller.
     */
    public void salirTaller(Avion avion, Paso paso) {

        try {
            control.acquire();
            logger.log("Avion " + avion.getIdentificador() + " saliendo TALLER ", nombreCiudad);
            Thread.sleep(1000);
            aviones.remove(avion);
            avionesDentro--;
            imprimirArrayAviones(jTextFieldTallerAeropuerto, aviones);
            paso.mirar();
            control.release();
            espaciosTaller.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para imprimir la lista de aviones en el taller en un campo de
     * texto.
     *
     * @param jTextFieldDestino Campo de texto donde se mostrará la información
     * sobre los aviones en el taller.
     * @param arrayAviones Lista de aviones en el taller.
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
