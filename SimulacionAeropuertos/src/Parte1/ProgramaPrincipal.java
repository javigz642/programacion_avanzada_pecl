/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;
import javax.swing.JTextField;
/**
 *
 * @author ediso
 */
public class ProgramaPrincipal extends Thread{
    JTextField jTextFieldTransferAeropuertoAutobusMadrid;
    public ProgramaPrincipal(JTextField jTextFieldTransferAeropuertoAutobusMadrid){
        this.jTextFieldTransferAeropuertoAutobusMadrid = jTextFieldTransferAeropuertoAutobusMadrid;
    }
    public void run() {


//        Ciudad barcelona = new Ciudad("Barcelona",jTextFieldTransferAeropuertoAutobusMadrid);
        Ciudad madrid = new Ciudad("Madrid",jTextFieldTransferAeropuertoAutobusMadrid);



        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //letras para el identificador del avion
        
        //creacion de los hilos de aviones

//        for (int i=0; i<4;i+=2){  //deberian ser 8000
//            
//            int r1 = (int) (Math.random()*26);
//            int r2 = (int) (Math.random()*26);
//            String identificador = ""+abc[r1]+abc[r2]+"";
//            
//            if (i%2==0){
//                Avion avion = new Avion(identificador, i, madrid, barcelona);
//                avion.start();
//            }
//            else{
//                Avion avion = new Avion(identificador, i, barcelona, madrid);
//                avion.start();
//            }
//            
//        }
        //El numero de autobuses se cambia luego el 100 es por pruebas
        //creacion de los hilos de autobuses
        for (int i = 0; i < 4; i++) { //deberian ser 4000

            String identificador = "B";

            if (i % 2 == 0) {
                Autobus autobusMadrid = new Autobus(identificador, i, 0, madrid);
                autobusMadrid.start();
            }
//            else{
//                Autobus autobusBarcelona = new Autobus(identificador, i, 0, barcelona);
//                autobusBarcelona.start();
//            }

        }
    }
}
