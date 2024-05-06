/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa el programa principal para la gestión de aviones. Genera
 * aviones de manera aleatoria y los asigna a las ciudades de Madrid y Barcelona
 * para su despegue o aterrizaje.
 *
 */
public class ProgramaPrincipalAviones extends Thread {

    private Ciudad madrid;
    private Ciudad barcelona;
    
    private Paso paso;
    
    private TextLog logger;

    public ProgramaPrincipalAviones(Ciudad madrid, Ciudad barcelona, Paso paso, TextLog logger) {
        this.madrid = madrid;
        this.barcelona = barcelona;
        this.paso = paso;
        this.logger = logger;
    }

    public void run() {

        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //Caracteres para el identificador del avion

        for (int i = 0; i < 8000; i++) {
            try {
                paso.mirar();
                int r1 = (int) (Math.random() * 26);
                int r2 = (int) (Math.random() * 26);
                String identificador = "" + abc[r1] + abc[r2] + "";

                if (i % 2 == 0) {
                    Avion avion = new Avion(identificador, i, madrid, barcelona, paso, logger);
                    avion.start();
                } else {
                    Avion avion = new Avion(identificador, i, barcelona, madrid, paso, logger);
                    avion.start();
                }

                Thread.sleep((int) (Math.random() * 01) + 0);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProgramaPrincipalAviones.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
