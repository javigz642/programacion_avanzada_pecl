/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.rmi.*;
import java.io.*;
import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Cliente extends Thread {

    private boolean[] pasoPistas = {false, false, false, false, false, false, false, false};
    private boolean[] pasoPistasAux = {false, false, false, false, false, false, false, false};

    private JTextField jTextFieldAeroviaBarcelona_Madrid;
    private JTextField jTextFieldAeroviaMadrid_Barcelona;
    private JTextField jTextFieldAvionesAreaEstacionamientoBarcelona;
    private JTextField jTextFieldAvionesAreaEstacionamientoMadrid;
    private JTextField jTextFieldAvionesAreaRodajeBarcelona;
    private JTextField jTextFieldAvionesAreaRodajeMadrid;
    private JTextField jTextFieldAvionesHangarBarcelona;
    private JTextField jTextFieldAvionesHangarMadrid;
    private JTextField jTextFieldAvionesTallerBarcelona;
    private JTextField jTextFieldAvionesTallerMadrid;
    private JTextField jTextFieldPasajerosAeropuertoBarcelona;
    private JTextField jTextFieldPasajerosAeropuertoMadrid;

    public Cliente(JTextField jTextFieldAeroviaBarcelona_Madrid, JTextField jTextFieldAeroviaMadrid_Barcelona, JTextField jTextFieldAvionesAreaEstacionamientoBarcelona, JTextField jTextFieldAvionesAreaEstacionamientoMadrid, JTextField jTextFieldAvionesAreaRodajeBarcelona, JTextField jTextFieldAvionesAreaRodajeMadrid, JTextField jTextFieldAvionesHangarBarcelona, JTextField jTextFieldAvionesHangarMadrid, JTextField jTextFieldAvionesTallerBarcelona, JTextField jTextFieldAvionesTallerMadrid, JTextField jTextFieldPasajerosAeropuertoBarcelona, JTextField jTextFieldPasajerosAeropuertoMadrid) {
        this.jTextFieldAeroviaBarcelona_Madrid = jTextFieldAeroviaBarcelona_Madrid;
        this.jTextFieldAeroviaMadrid_Barcelona = jTextFieldAeroviaMadrid_Barcelona;
        this.jTextFieldAvionesAreaEstacionamientoBarcelona = jTextFieldAvionesAreaEstacionamientoBarcelona;
        this.jTextFieldAvionesAreaEstacionamientoMadrid = jTextFieldAvionesAreaEstacionamientoMadrid;
        this.jTextFieldAvionesAreaRodajeBarcelona = jTextFieldAvionesAreaRodajeBarcelona;
        this.jTextFieldAvionesAreaRodajeMadrid = jTextFieldAvionesAreaRodajeMadrid;
        this.jTextFieldAvionesHangarBarcelona = jTextFieldAvionesHangarBarcelona;
        this.jTextFieldAvionesHangarMadrid = jTextFieldAvionesHangarMadrid;
        this.jTextFieldAvionesTallerBarcelona = jTextFieldAvionesTallerBarcelona;
        this.jTextFieldAvionesTallerMadrid = jTextFieldAvionesTallerMadrid;
        this.jTextFieldPasajerosAeropuertoBarcelona = jTextFieldPasajerosAeropuertoBarcelona;
        this.jTextFieldPasajerosAeropuertoMadrid = jTextFieldPasajerosAeropuertoMadrid;

    }

    public void run() {

        String respuesta = "";
        try {
            InterfaceInformar obj = (InterfaceInformar) Naming.lookup("//localhost/ObjetoInformador");

            while (true) {
                try {
                    mirarSiPararAlgunaPista(obj);
                    respuesta = obj.numPasajerosAeropuertoMadrid();
                    jTextFieldPasajerosAeropuertoMadrid.setText(respuesta);

                    respuesta = obj.numPasajerosAeropuertoBarcelona();
                    jTextFieldPasajerosAeropuertoBarcelona.setText(respuesta);

                    respuesta = obj.numAvionesAreaEstacionamientoMadrid();
                    jTextFieldAvionesAreaEstacionamientoMadrid.setText(respuesta);

                    respuesta = obj.numAvionesAreaEstacionamientoBarcelona();
                    jTextFieldAvionesAreaEstacionamientoBarcelona.setText(respuesta);

                    respuesta = obj.numAvionesAreaRodajeMadrid();
                    jTextFieldAvionesAreaRodajeMadrid.setText(respuesta);

                    respuesta = obj.numAvionesAreaRodajeBarcelona();
                    jTextFieldAvionesAreaRodajeBarcelona.setText(respuesta);

                    respuesta = obj.numAvionesHangarMadrid();
                    jTextFieldAvionesHangarMadrid.setText(respuesta);

                    respuesta = obj.numAvionesHangarBarcelona();
                    jTextFieldAvionesHangarBarcelona.setText(respuesta);

                    respuesta = obj.numAvionesTallerMadrid();
                    jTextFieldAvionesTallerMadrid.setText(respuesta);

                    respuesta = obj.numAvionesTallerBarcelona();
                    jTextFieldAvionesTallerBarcelona.setText(respuesta);

                    respuesta = obj.AvionesAeroviaMadrid_Barcelona();
                    jTextFieldAeroviaMadrid_Barcelona.setText(respuesta);

                    respuesta = obj.AvionesAeroviaBarcelona_Madrid();
                    jTextFieldAeroviaBarcelona_Madrid.setText(respuesta);

                } catch (ConnectException ce) {
                    System.out.println("Se ha perdido la conexion con el servidor, reinicie el programa");
                } catch (Exception e) {
                    System.out.println("Excepción : " + e.getMessage());
                    e.printStackTrace();
                }

                sleep(1000); //Para que dé tiempo a leer la respuesta antes de que se cierre la ventana
            }

        } catch (Exception e) {
            System.out.println("Excepción : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setPasoPistasNumero(int numeroPista) {

        pasoPistas[numeroPista - 1] = !pasoPistas[numeroPista - 1];

    }

    /**
     * Método para verificar si se debe detener alguna pista y en consecuencia
     * realizar el acceso o la denegacion del paso a la pista.
     *
     * @param obj Objeto para comunicarse con el servidor.
     */
    public void mirarSiPararAlgunaPista(InterfaceInformar obj) {
        try {
            // Comprobar si ha habido cambios en el estado de las pistas
            for (int i = 0; i < pasoPistas.length; i++) {
                boolean pasoActual = pasoPistas[i];
                boolean pasoAnterior = pasoPistasAux[i];

                // Si hay cambios en el estado de la pista se actua
                if (pasoActual != pasoAnterior) {
                    if (pasoActual) {
                        if (i < 4) {
                            obj.setCerrarPasoPistaXMadrid(i);
                        } else {
                            obj.setCerrarPasoPistaXBarcelona(i - 4);
                        }
                    } else {
                        if (i < 4) {
                            obj.setAbrirPasoPistaXMadrid(i);
                        } else {
                            obj.setAbrirPasoPistaXBarcelona(i - 4);
                        }
                    }
                    pasoPistasAux[i] = pasoActual;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
