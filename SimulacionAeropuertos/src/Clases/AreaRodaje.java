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
 * @author Miguel
 */
public class AreaRodaje {
    
    private ArrayList<Avion> aviones = new ArrayList<>();
private int avionesDentro;
    private Semaphore control = new Semaphore(1);

    private JTextField jTextFieldAreaRodajeAeropuerto;

    private String nombreCiudad;
    private TextLog logger;

    public AreaRodaje(JTextField jTextFieldAreaRodajeAeropuerto, String nombreCiudad, TextLog logger) {
        this.jTextFieldAreaRodajeAeropuerto = jTextFieldAreaRodajeAeropuerto;
        this.nombreCiudad = nombreCiudad;
        this.logger = logger;
    }

    public void entrarAreaRodaje(Avion avion) {

        try {
            control.acquire();
            aviones.add(avion);
            avionesDentro++;
            imprimirArrayAviones(jTextFieldAreaRodajeAeropuerto, aviones);
            control.release();
            //System.out.println(avion.getIdentificador() + " entrando RODAJE");
            if (avion.isEmbarque()) {
                Thread.sleep((int) (Math.random() * 4000) + 1001);
                //System.out.println(avion.getIdentificador() + " haciendo comprobaciones RODAJE");
            }
//            else{
//                Thread.sleep(2000);
//            }

        } catch (InterruptedException ex) {
            Logger.getLogger(AreaRodaje.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salirAreaRodaje(Avion avion) {
        try {
            control.acquire();
            aviones.remove(avion);
            avionesDentro--;
            imprimirArrayAviones(jTextFieldAreaRodajeAeropuerto, aviones);
            control.release();
            //System.out.println(avion.getIdentificador() + " saliendo RODAJE");
        } catch (InterruptedException ex) {
            Logger.getLogger(AreaRodaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void imprimirArrayAviones(JTextField jTextFieldDestino, ArrayList<Avion> arrayAviones) {
        String stringAux = "";
        for (int i = 0; i < arrayAviones.size(); i++) {
            stringAux += arrayAviones.get(i).getIdentificador() + " / ";
        }
        jTextFieldDestino.setText(stringAux);
        //System.out.println(stringAux);
    }

    public int getAvionesDentro() {
        int avionesAux = 0;

        avionesAux = avionesDentro;

        return avionesAux;
    }
}
