/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author ediso
 */
public class ProgramaPrincipalAutobuses extends Thread{
        private Ciudad madrid;
        private Ciudad barcelona;
        private Paso paso;
        private TextLog logger;

    public ProgramaPrincipalAutobuses(Ciudad madrid, Ciudad barcelona, Paso paso, TextLog logger) {
        this.madrid = madrid;
        this.barcelona = barcelona;
        this.paso = paso;
        this.logger = logger;
    }


    
    public void run(){
                
        for (int i = 0; i < 4000; i++) { //deberian ser 4000

            String identificador = "B";

            if (i % 2 == 0) {
                Autobus autobusMadrid = new Autobus(identificador, i, 0, madrid, paso, logger);
                autobusMadrid.start();
            } else {
                Autobus autobusBarcelona = new Autobus(identificador, i, 0, barcelona,paso, logger);
                autobusBarcelona.start();
            }
            try {
                Thread.sleep((int) (Math.random() * 501) + 500);
            } catch (Exception e) {
            }
        }
    }
}
