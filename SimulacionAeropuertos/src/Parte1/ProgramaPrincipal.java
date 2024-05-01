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
public class ProgramaPrincipal extends Thread {

    private JTextField jTextFieldAeroviaBarcelona_Madrid;
    private JTextField jTextFieldAeroviaMadrid_Barcelona;
    private JTextField jTextFieldAreaEstacionamientoAeropuertoBarcelona;
    private JTextField jTextFieldAreaEstacionamientoAeropuertoMadrid;
    private JTextField jTextFieldAreaRodajeAeropuertoBarcelona;
    private JTextField jTextFieldAreaRodajeAeropuertoMadrid;
    private JTextField jTextFieldGate1AeropuertoBarcelona;
    private JTextField jTextFieldGate1AeropuertoMadrid;
    private JTextField jTextFieldGate2AeropuertoBarcelona;
    private JTextField jTextFieldGate2AeropuertoMadrid;
    private JTextField jTextFieldGate3AeropuertoBarcelona;
    private JTextField jTextFieldGate3AeropuertoMadrid;
    private JTextField jTextFieldGate4AeropuertoBarcelona;
    private JTextField jTextFieldGate4AeropuertoMadrid;
    private JTextField jTextFieldGate5AeropuertoBarcelona;
    private JTextField jTextFieldGate5AeropuertoMadrid;
    private JTextField jTextFieldGate6AeropuertoBarcelona;
    private JTextField jTextFieldGate6AeropuertoMadrid;
    private JTextField jTextFieldHangarAeropuertoBarcelona;
    private JTextField jTextFieldHangarAeropuertoMadrid;
    private JTextField jTextFieldNumeroPasajerosAeropuertoBarcelona;
    private JTextField jTextFieldNumeroPasajerosAeropuertoMadrid;
    private JTextField jTextFieldPista1AeropuertoBarcelona;
    private JTextField jTextFieldPista1AeropuertoMadrid;
    private JTextField jTextFieldPista2AeropuertoBarcelona;
    private JTextField jTextFieldPista2AeropuertoMadrid;
    private JTextField jTextFieldPista3AeropuertoBarcelona;
    private JTextField jTextFieldPista3AeropuertoMadrid;
    private JTextField jTextFieldPista4AeropuertoBarcelona;
    private JTextField jTextFieldPista4AeropuertoMadrid;
    private JTextField jTextFieldTallerAeropuertoBarcelona;
    private JTextField jTextFieldTallerAeropuertoMadrid;
    private JTextField jTextFieldTransferAeropuertoAutobusBarcelona;
    private JTextField jTextFieldTransferAeropuertoAutobusMadrid;
    private JTextField jTextFieldTransferCiudadAutobusMadrid;
    private JTextField jTextFieldTransferCiudadAutobusBarcelona;

    public ProgramaPrincipal(JTextField jTextFieldAeroviaBarcelona_Madrid, JTextField jTextFieldAeroviaMadrid_Barcelona,
            JTextField jTextFieldAreaEstacionamientoAeropuertoBarcelona, JTextField jTextFieldAreaEstacionamientoAeropuertoMadrid,
            JTextField jTextFieldAreaRodajeAeropuertoBarcelona, JTextField jTextFieldAreaRodajeAeropuertoMadrid,
            JTextField jTextFieldGate1AeropuertoBarcelona, JTextField jTextFieldGate1AeropuertoMadrid,
            JTextField jTextFieldGate2AeropuertoBarcelona, JTextField jTextFieldGate2AeropuertoMadrid,
            JTextField jTextFieldGate3AeropuertoBarcelona, JTextField jTextFieldGate3AeropuertoMadrid,
            JTextField jTextFieldGate4AeropuertoBarcelona, JTextField jTextFieldGate4AeropuertoMadrid,
            JTextField jTextFieldGate5AeropuertoBarcelona, JTextField jTextFieldGate5AeropuertoMadrid,
            JTextField jTextFieldGate6AeropuertoBarcelona, JTextField jTextFieldGate6AeropuertoMadrid,
            JTextField jTextFieldHangarAeropuertoBarcelona, JTextField jTextFieldHangarAeropuertoMadrid,
            JTextField jTextFieldNumeroPasajerosAeropuertoBarcelona, JTextField jTextFieldNumeroPasajerosAeropuertoMadrid,
            JTextField jTextFieldPista1AeropuertoBarcelona, JTextField jTextFieldPista1AeropuertoMadrid,
            JTextField jTextFieldPista2AeropuertoBarcelona, JTextField jTextFieldPista2AeropuertoMadrid,
            JTextField jTextFieldPista3AeropuertoBarcelona, JTextField jTextFieldPista3AeropuertoMadrid,
            JTextField jTextFieldPista4AeropuertoBarcelona, JTextField jTextFieldPista4AeropuertoMadrid,
            JTextField jTextFieldTallerAeropuertoBarcelona, JTextField jTextFieldTallerAeropuertoMadrid,
            JTextField jTextFieldTransferAeropuertoAutobusBarcelona, JTextField jTextFieldTransferAeropuertoAutobusMadrid,
            JTextField jTextFieldTransferCiudadAutobusMadrid, JTextField jTextFieldTransferCiudadAutobusBarcelona) {
        this.jTextFieldAeroviaBarcelona_Madrid = jTextFieldAeroviaBarcelona_Madrid;
        this.jTextFieldAeroviaMadrid_Barcelona = jTextFieldAeroviaMadrid_Barcelona;
        this.jTextFieldAreaEstacionamientoAeropuertoBarcelona = jTextFieldAreaEstacionamientoAeropuertoBarcelona;
        this.jTextFieldAreaEstacionamientoAeropuertoMadrid = jTextFieldAreaEstacionamientoAeropuertoMadrid;
        this.jTextFieldAreaRodajeAeropuertoBarcelona = jTextFieldAreaRodajeAeropuertoBarcelona;
        this.jTextFieldAreaRodajeAeropuertoMadrid = jTextFieldAreaRodajeAeropuertoMadrid;
        this.jTextFieldGate1AeropuertoBarcelona = jTextFieldGate1AeropuertoBarcelona;
        this.jTextFieldGate1AeropuertoMadrid = jTextFieldGate1AeropuertoMadrid;
        this.jTextFieldGate2AeropuertoBarcelona = jTextFieldGate2AeropuertoBarcelona;
        this.jTextFieldGate2AeropuertoMadrid = jTextFieldGate2AeropuertoMadrid;
        this.jTextFieldGate3AeropuertoBarcelona = jTextFieldGate3AeropuertoBarcelona;
        this.jTextFieldGate3AeropuertoMadrid = jTextFieldGate3AeropuertoMadrid;
        this.jTextFieldGate4AeropuertoBarcelona = jTextFieldGate4AeropuertoBarcelona;
        this.jTextFieldGate4AeropuertoMadrid = jTextFieldGate4AeropuertoMadrid;
        this.jTextFieldGate5AeropuertoBarcelona = jTextFieldGate5AeropuertoBarcelona;
        this.jTextFieldGate5AeropuertoMadrid = jTextFieldGate5AeropuertoMadrid;
        this.jTextFieldGate6AeropuertoBarcelona = jTextFieldGate6AeropuertoBarcelona;
        this.jTextFieldGate6AeropuertoMadrid = jTextFieldGate6AeropuertoMadrid;
        this.jTextFieldHangarAeropuertoBarcelona = jTextFieldHangarAeropuertoBarcelona;
        this.jTextFieldHangarAeropuertoMadrid = jTextFieldHangarAeropuertoMadrid;
        this.jTextFieldNumeroPasajerosAeropuertoBarcelona = jTextFieldNumeroPasajerosAeropuertoBarcelona;
        this.jTextFieldNumeroPasajerosAeropuertoMadrid = jTextFieldNumeroPasajerosAeropuertoMadrid;
        this.jTextFieldPista1AeropuertoBarcelona = jTextFieldPista1AeropuertoBarcelona;
        this.jTextFieldPista1AeropuertoMadrid = jTextFieldPista1AeropuertoMadrid;
        this.jTextFieldPista2AeropuertoBarcelona = jTextFieldPista2AeropuertoBarcelona;
        this.jTextFieldPista2AeropuertoMadrid = jTextFieldPista2AeropuertoMadrid;
        this.jTextFieldPista3AeropuertoBarcelona = jTextFieldPista3AeropuertoBarcelona;
        this.jTextFieldPista3AeropuertoMadrid = jTextFieldPista3AeropuertoMadrid;
        this.jTextFieldPista4AeropuertoBarcelona = jTextFieldPista4AeropuertoBarcelona;
        this.jTextFieldPista4AeropuertoMadrid = jTextFieldPista4AeropuertoMadrid;
        this.jTextFieldTallerAeropuertoBarcelona = jTextFieldTallerAeropuertoBarcelona;
        this.jTextFieldTallerAeropuertoMadrid = jTextFieldTallerAeropuertoMadrid;
        this.jTextFieldTransferAeropuertoAutobusBarcelona = jTextFieldTransferAeropuertoAutobusBarcelona;
        this.jTextFieldTransferAeropuertoAutobusMadrid = jTextFieldTransferAeropuertoAutobusMadrid;
        this.jTextFieldTransferCiudadAutobusMadrid = jTextFieldTransferCiudadAutobusMadrid;
        this.jTextFieldTransferCiudadAutobusBarcelona = jTextFieldTransferCiudadAutobusBarcelona;
    }

    public void run() {
        Aeropuerto aeropuertoMadrid = new Aeropuerto(jTextFieldAeroviaMadrid_Barcelona,
                jTextFieldAreaEstacionamientoAeropuertoMadrid,
                jTextFieldAreaRodajeAeropuertoMadrid,
                jTextFieldGate1AeropuertoMadrid,
                jTextFieldGate2AeropuertoMadrid,
                jTextFieldGate3AeropuertoMadrid,
                jTextFieldGate4AeropuertoMadrid,
                jTextFieldGate5AeropuertoMadrid,
                jTextFieldGate6AeropuertoMadrid,
                jTextFieldHangarAeropuertoMadrid,
                jTextFieldNumeroPasajerosAeropuertoMadrid,
                jTextFieldPista1AeropuertoMadrid,
                jTextFieldPista2AeropuertoMadrid,
                jTextFieldPista3AeropuertoMadrid,
                jTextFieldPista4AeropuertoMadrid,
                jTextFieldTallerAeropuertoMadrid
        );
        Aeropuerto aeropuertoBarcelona = new Aeropuerto(
                jTextFieldAeroviaBarcelona_Madrid,
                jTextFieldAreaEstacionamientoAeropuertoBarcelona,
                jTextFieldAreaRodajeAeropuertoBarcelona,
                jTextFieldGate1AeropuertoBarcelona,
                jTextFieldGate2AeropuertoBarcelona,
                jTextFieldGate3AeropuertoBarcelona,
                jTextFieldGate4AeropuertoBarcelona,
                jTextFieldGate5AeropuertoBarcelona,
                jTextFieldGate6AeropuertoBarcelona,
                jTextFieldHangarAeropuertoBarcelona,
                jTextFieldNumeroPasajerosAeropuertoBarcelona,
                jTextFieldPista1AeropuertoBarcelona,
                jTextFieldPista2AeropuertoBarcelona,
                jTextFieldPista3AeropuertoBarcelona,
                jTextFieldPista4AeropuertoBarcelona,
                jTextFieldTallerAeropuertoBarcelona
        );
        Ciudad madrid = new Ciudad("Madrid",aeropuertoMadrid, jTextFieldTransferAeropuertoAutobusMadrid, jTextFieldTransferCiudadAutobusMadrid);
        Ciudad barcelona = new Ciudad("Barcelona",aeropuertoBarcelona, jTextFieldTransferAeropuertoAutobusBarcelona, jTextFieldTransferCiudadAutobusBarcelona);
        

//
//
//
        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; //letras para el identificador del avion
//        
//        //creacion de los hilos de aviones
//
        for (int i=0; i<4 ;i+=2){  //deberian ser 8000
            
            int r1 = (int) (Math.random()*26);
            int r2 = (int) (Math.random()*26);
            String identificador = ""+abc[r1]+abc[r2]+"";
            
            if (i%2==0){
                Avion avion = new Avion(identificador, i, madrid, barcelona);
                avion.start();
            }
//            else{
//                Avion avion = new Avion(identificador, i, barcelona, madrid);
//                avion.start();
//            }
            
        }
        //El numero de autobuses se cambia luego el 100 es por pruebas
        //creacion de los hilos de autobuses
//        for (int i = 0; i < 4; i++) { //deberian ser 4000
//
//            String identificador = "B";
//
//            if (i % 2 == 0) {
//                Autobus autobusMadrid = new Autobus(identificador, i, 0, madrid);
//                autobusMadrid.start();
//            } 
//            else {
//                Autobus autobusBarcelona = new Autobus(identificador, i, 0, barcelona);
//                autobusBarcelona.start();
//            }
//
//        }
    }
}
