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
        Aeropuerto aeropuertoMadrid = new Aeropuerto(100,madrid);
        Aeropuerto aeropuertoBarcelona = new Aeropuerto(100,barcelona);
        Hangar hangarBarcelona = new Hangar("Barcelona");
        Hangar hangarMadrid = new Hangar("Madrid");
        Taller tallerBarcelona = new Taller("Barcelona");
        Taller tallerMadrid = new Taller("Madrid");

        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

//        for (int i=0; i<100;i++){
//            
//            int r1 = (int) (Math.random()*26);
//            int r2 = (int) (Math.random()*26);
//            String identificador = ""+abc[r1]+abc[r2]+"";
//            
//            if (i%2==0){
//                Avion avion = new Avion(identificador, i, hangarMadrid);
//                avion.start();
//            }
//            else{
//                Avion avion = new Avion(identificador, i, hangarBarcelona);
//                avion.start();
//            }
//            
//        }
        //El numero de autobuses se cambia luego el 100 es por pruebas
        for (int i = 0; i < 50; i++) {

            String identificador = "B";

            if (i % 2 == 0) {
                Autobus autobusMadrid = new Autobus(identificador, i, 0, madrid, aeropuertoMadrid);
                autobusMadrid.start();
            }
            else{
                Autobus autobusBarcelona = new Autobus(identificador, i, 0, barcelona, aeropuertoBarcelona);
                autobusBarcelona.start();
            }

        }
    }
}
