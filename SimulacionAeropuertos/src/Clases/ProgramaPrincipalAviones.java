/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import javax.swing.JTextField;

/**
 *
 * @author ediso
 */
public class ProgramaPrincipalAviones extends Thread {

    private Ciudad madrid;
    private Ciudad barcelona;
    private Paso paso;

    public ProgramaPrincipalAviones(Ciudad madrid, Ciudad barcelona, Paso paso) {
        this.madrid = madrid;
        this.barcelona = barcelona;
        this.paso = paso;
    }

    public void run() {



//
//
//
        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //letras para el identificador del avion
//        
//        //creacion de los hilos de aviones
//
        for (int i = 0; i < 8000; i++) {  //deberian ser 8000

            int r1 = (int) (Math.random() * 26);
            int r2 = (int) (Math.random() * 26);
            String identificador = "" + abc[r1] + abc[r2] + "";

            if (i % 2 == 0) {
                Avion avion = new Avion(identificador, i, madrid, barcelona,paso);
                avion.start();
            } else {
                Avion avion = new Avion(identificador, i, barcelona, madrid,paso);
                avion.start();
            }
            try {
                Thread.sleep((int) (Math.random() * 2001) + 1000);
            } catch (Exception e) {
            }

        }

    }
}

