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
 *
 * @author sombe
 */
public class Taller {

    private ArrayList<Avion> aviones = new ArrayList<>();
    private int avionesDentro;
    private Semaphore espaciosTaller = new Semaphore(20, true);
    private Semaphore control = new Semaphore(1, true);

    private JTextField jTextFieldTallerAeropuerto;

    private String nombreCiudad;
    private TextLog logger;

    public Taller(JTextField jTextFieldTallerAeropuerto, String nombreCiudad, TextLog logger) {
        this.jTextFieldTallerAeropuerto = jTextFieldTallerAeropuerto;
        this.nombreCiudad = nombreCiudad;
        this.logger = logger;
    }

    public void entrarTaller(Avion avion) {

        try {
            espaciosTaller.acquire();
            control.acquire();
            avion.getDestino().getAeropuerto().getAreaEstacionamiento().salirAreaEstacionamiento(avion);
            aviones.add(avion);
            imprimirArrayAviones(jTextFieldTallerAeropuerto, aviones);
            logger.log("Avion " + avion.getIdentificador() + " entrando TALLER ", nombreCiudad);
            Thread.sleep(1000);
            avionesDentro++;
            control.release();
            if (avion.getVuelos() % 15 == 0) {
                logger.log("Avion " + avion.getIdentificador() + " realizando REVISION PRFUNDA ", nombreCiudad);
                Thread.sleep((int) (Math.random() * 5000) + 5001);
            } else {
                logger.log("Avion " + avion.getIdentificador() + " realizando REVISION NORMAL ", nombreCiudad);
                Thread.sleep((int) (Math.random() * 4000) + 1001);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salirTaller(Avion avion) {

        try {
            control.acquire();
            logger.log("Avion " + avion.getIdentificador() + " saliendo TALLER ", nombreCiudad);
            Thread.sleep(1000);
            aviones.remove(avion);
            avionesDentro--;
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
    }

    public int getAvionesDentro() {
        int avionesAux = 0;

        avionesAux = avionesDentro;

        return avionesAux;
    }

}
