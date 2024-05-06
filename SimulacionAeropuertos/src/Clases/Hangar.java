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

public class Hangar {

    private ArrayList<Avion> aviones = new ArrayList<>();
    private int avionesDentro;
    private String nombreCiudad;

    private Semaphore control = new Semaphore(1);
    
    private JTextField jTextFieldHangarAeropuerto;
    
    private TextLog logger;

    public Hangar(JTextField jTextFieldHangarAeropuerto, String nombreCiudad, TextLog logger) {
        this.nombreCiudad = nombreCiudad;
        this.jTextFieldHangarAeropuerto = jTextFieldHangarAeropuerto;
        this.logger = logger;
    }

    /**
     * Método para que un avión entre en el hangar.
     *
     * @param avion Avión que entra en el hangar.
     */
    public void entrarHangar(Avion avion) {

        try {
            control.acquire();
            aviones.add(avion);
            avionesDentro++;
            imprimirArrayAviones(jTextFieldHangarAeropuerto, aviones);
            control.release();
            logger.log("Avion " + avion.getIdentificador() + " entrando HANGAR ", nombreCiudad);

        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para que un avión salga del hangar.
     *
     * @param avion Avión que sale del hangar.
     */
    public void salirHangar(Avion avion) {

        try {
            control.acquire();
            aviones.remove(avion);
            avionesDentro--;
            imprimirArrayAviones(jTextFieldHangarAeropuerto, aviones);
            control.release();
            logger.log("Avion " + avion.getIdentificador() + " saliendo HANGAR ", nombreCiudad);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para que un avión repose en el hangar.
     *
     * @param avion Avión que reposa en el hangar.
     */
    public void reposar(Avion avion) {

        try {
            control.acquire();
            aviones.add(avion);
            avionesDentro++;
            imprimirArrayAviones(jTextFieldHangarAeropuerto, aviones);
            control.release();
            logger.log("Avion " + avion.getIdentificador() + " reposando HANGAR ", nombreCiudad);
            Thread.sleep((int) (Math.random() * 15000) + 15001);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hangar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para imprimir la lista de aviones dentro del hangar en un
     * JTextField.
     *
     * @param jTextFieldDestino JTextField donde se imprimirá la lista de
     * aviones.
     * @param arrayAviones Lista de aviones dentro del hangar.
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
