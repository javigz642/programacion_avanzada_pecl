/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

/**
 *
 * @author ediso
 */
public class ProgramaPrincipalAutobuses extends Thread{
        private Ciudad madrid;
    private Ciudad barcelona;
        private Paso paso;

    public ProgramaPrincipalAutobuses(Ciudad madrid, Ciudad barcelona, Paso paso) {
        this.madrid = madrid;
        this.barcelona = barcelona;
        this.paso = paso;
    }


    
    public void run(){
                //El numero de autobuses se cambia luego el 100 es por pruebas
        //creacion de los hilos de autobuses
        for (int i = 0; i < 4000; i++) { //deberian ser 4000

            String identificador = "B";

            if (i % 2 == 0) {
                Autobus autobusMadrid = new Autobus(identificador, i, 0, madrid,paso);
                autobusMadrid.start();
//            } else {
//                Autobus autobusBarcelona = new Autobus(identificador, i, 0, barcelona,paso);
//                autobusBarcelona.start();
            }
            try {
                Thread.sleep((int) (Math.random() * 501) + 500);
            } catch (Exception e) {
            }
        }
    }
}
