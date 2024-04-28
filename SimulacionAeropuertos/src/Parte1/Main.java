/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

/**
 *
 * @author ediso
 */
public class Main {

    public static void main(String[] args) {


        Ciudad barcelona = new Ciudad("Barcelona");
        Ciudad madrid = new Ciudad("Madrid");

        //HOLA

        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //letras para el identificador del avion
        
        //creacion de los hilos de aviones

        for (int i=0; i<10;i++){  //deberian ser 8000
            
            int r1 = (int) (Math.random()*26);
            int r2 = (int) (Math.random()*26);
            String identificador = ""+abc[r1]+abc[r2]+"";
            
            if (i%2==0){
                Avion avion = new Avion(identificador, i, madrid);
                avion.start();
            }
            else{
                Avion avion = new Avion(identificador, i, barcelona);
                avion.start();
            }
            
        }
        //El numero de autobuses se cambia luego el 100 es por pruebas
        //creacion de los hilos de autobuses
//        for (int i = 0; i < 50; i++) { //deberian ser 4000
//
//            String identificador = "B";
//
//            if (i % 2 == 0) {
//                Autobus autobusMadrid = new Autobus(identificador, i, 0, madrid, aeropuertoMadrid);
//                autobusMadrid.start();
//            }
//            else{
//                Autobus autobusBarcelona = new Autobus(identificador, i, 0, barcelona, aeropuertoBarcelona);
//                autobusBarcelona.start();
//            }
//
//        }
    }
}
