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
public class Main extends Thread {
    
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
    private Paso paso;
    private PasoPistas pasoPistasBarcelona;
    private PasoPistas pasoPistasMadrid;
    public Main(JTextField jTextFieldAeroviaBarcelona_Madrid, JTextField jTextFieldAeroviaMadrid_Barcelona, 
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
            JTextField jTextFieldTransferCiudadAutobusMadrid, JTextField jTextFieldTransferCiudadAutobusBarcelona,
        Paso paso, PasoPistas pasoPistasBarcelona, PasoPistas pasoPistasMadrid) {
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
        this.paso = paso;
        this.pasoPistasBarcelona = pasoPistasBarcelona;
        this.pasoPistasMadrid = pasoPistasMadrid;
    }

    public void run() {
        TextLog logger = new TextLog("evolucionAeropuerto.txt");

        Hangar hangarMadrid = new Hangar(jTextFieldHangarAeropuertoMadrid, "Madrid", logger);
        AreaEstacionamiento areaEstacionamientoMadrid = new AreaEstacionamiento(jTextFieldAreaEstacionamientoAeropuertoMadrid, "Madrid", logger);
        PuertaEmbarque puertaEmbarqueMadrid = new PuertaEmbarque("Madrid", logger, jTextFieldGate1AeropuertoMadrid, jTextFieldGate2AeropuertoMadrid, jTextFieldGate3AeropuertoMadrid, jTextFieldGate4AeropuertoMadrid, jTextFieldGate5AeropuertoMadrid, jTextFieldGate6AeropuertoMadrid);
        Taller tallerMadrid = new Taller(jTextFieldTallerAeropuertoMadrid, "Madrid", logger);
        AreaRodaje areaRodajeMadrid = new AreaRodaje(jTextFieldAreaRodajeAeropuertoMadrid, "Madrid", logger);
        Pista pistaMadrid = new Pista(jTextFieldPista1AeropuertoMadrid, jTextFieldPista2AeropuertoMadrid, jTextFieldPista3AeropuertoMadrid, jTextFieldPista4AeropuertoMadrid, "Madrid", logger, pasoPistasMadrid);
        Aerovia aeroviaMadrid = new Aerovia(jTextFieldAeroviaMadrid_Barcelona, jTextFieldAeroviaBarcelona_Madrid, logger);

        Aeropuerto aeropuertoMadrid = new Aeropuerto(hangarMadrid,
                areaEstacionamientoMadrid, puertaEmbarqueMadrid, tallerMadrid, areaRodajeMadrid,
                pistaMadrid, aeroviaMadrid, jTextFieldGate1AeropuertoMadrid,
                jTextFieldGate2AeropuertoMadrid, jTextFieldGate3AeropuertoMadrid,
                jTextFieldGate4AeropuertoMadrid, jTextFieldGate5AeropuertoMadrid,
                jTextFieldGate6AeropuertoMadrid, jTextFieldNumeroPasajerosAeropuertoMadrid);

        Hangar hangarBarcelona = new Hangar(jTextFieldHangarAeropuertoBarcelona, "Barcelona", logger);
        AreaEstacionamiento areaEstacionamientoBarcelona = new AreaEstacionamiento(jTextFieldAreaEstacionamientoAeropuertoBarcelona, "Barcelona", logger);
        PuertaEmbarque puertaEmbarqueBarcelona = new PuertaEmbarque("Barcelona", logger, jTextFieldGate1AeropuertoBarcelona, jTextFieldGate2AeropuertoBarcelona, jTextFieldGate3AeropuertoBarcelona, jTextFieldGate4AeropuertoBarcelona, jTextFieldGate5AeropuertoBarcelona, jTextFieldGate6AeropuertoBarcelona);

        Taller tallerBarcelona = new Taller(jTextFieldTallerAeropuertoBarcelona, "Barcelona", logger);
        AreaRodaje areaRodajeBarcelona = new AreaRodaje(jTextFieldAreaRodajeAeropuertoBarcelona, "Barcelona", logger);
        Pista pistaBarcelona = new Pista(jTextFieldPista1AeropuertoBarcelona, jTextFieldPista2AeropuertoBarcelona, jTextFieldPista3AeropuertoBarcelona, jTextFieldPista4AeropuertoBarcelona, "Barcelona", logger, pasoPistasBarcelona);
        Aerovia aeroviaBarcelona = new Aerovia(jTextFieldAeroviaMadrid_Barcelona, jTextFieldAeroviaBarcelona_Madrid, logger);

        Aeropuerto aeropuertoBarcelona = new Aeropuerto(hangarBarcelona,
                areaEstacionamientoBarcelona, puertaEmbarqueBarcelona, tallerBarcelona, areaRodajeBarcelona,
                pistaBarcelona, aeroviaBarcelona, jTextFieldGate1AeropuertoBarcelona,
                jTextFieldGate2AeropuertoBarcelona, jTextFieldGate3AeropuertoBarcelona,
                jTextFieldGate4AeropuertoBarcelona, jTextFieldGate5AeropuertoBarcelona,
                jTextFieldGate6AeropuertoBarcelona, jTextFieldNumeroPasajerosAeropuertoBarcelona);

        Ciudad madrid = new Ciudad("Madrid", aeropuertoMadrid, jTextFieldTransferAeropuertoAutobusMadrid,
                jTextFieldTransferCiudadAutobusMadrid);
        Ciudad barcelona = new Ciudad("Barcelona", aeropuertoBarcelona, jTextFieldTransferAeropuertoAutobusBarcelona,
                jTextFieldTransferCiudadAutobusBarcelona);
        
        ProgramaPrincipalAviones pp1 = new ProgramaPrincipalAviones(madrid, barcelona,paso);
        ProgramaPrincipalAutobuses pp2 = new ProgramaPrincipalAutobuses(madrid, barcelona,paso);
        pp1.start();
        pp2.start();
        
        Servidor server = new Servidor(aeropuertoMadrid, aeropuertoBarcelona);
        server.start();
    }
}
