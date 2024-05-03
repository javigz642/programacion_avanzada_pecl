/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Clases;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Informador extends UnicastRemoteObject implements InterfaceInformar {

        private Aeropuerto aeropuertoMadrid;
    private Aeropuerto aeropuertoBarcelona;

    protected Informador(Aeropuerto madrid, Aeropuerto barcelona) throws RemoteException {
        this.aeropuertoMadrid = madrid;
        this.aeropuertoBarcelona = barcelona;
    }


    public String numPasajerosAeropuertoMadrid() throws RemoteException {

        return aeropuertoMadrid.getPersonasDentro()+"";
    }


    public String numPasajerosAeropuertoBarcelona() throws RemoteException {

        return aeropuertoBarcelona.getPersonasDentro()+"";
    }


    public String numAvionesHangarMadrid() throws RemoteException {

        return aeropuertoMadrid.hangar.getAvionesDentro()+""; 
    }


    public String numAvionesHangarBarcelona() throws RemoteException {

        return aeropuertoBarcelona.hangar.getAvionesDentro()+"";
    }


    public String numAvionesTallerMadrid() throws RemoteException {

        return aeropuertoMadrid.taller.getAvionesDentro()+""; 
    }


    public String numAvionesTallerBarcelona() throws RemoteException {

        return aeropuertoBarcelona.taller.getAvionesDentro()+""; 
    }
    public String numAvionesAreaEstacionamientoMadrid() throws RemoteException {

        return aeropuertoMadrid.areaEstacionamiento.getAvionesDentro()+""; 
    }


    public String numAvionesAreaEstacionamientoBarcelona() throws RemoteException {

        return aeropuertoBarcelona.areaEstacionamiento.getAvionesDentro()+""; 
    }
    public String numAvionesAreaRodajeMadrid() throws RemoteException {

        return aeropuertoMadrid.areaRodaje.getAvionesDentro()+""; 
    }

    public String AvionesAeroviaMadrid_Barcelona() throws RemoteException {

        return aeropuertoMadrid.aerovia.getStringArrayAviones();
    }
    public String AvionesAeroviaBarcelona_Madrid() throws RemoteException {

        return aeropuertoBarcelona.aerovia.getStringArrayAviones(); 
    }


    public String numAvionesAreaRodajeBarcelona() throws RemoteException {
       return aeropuertoBarcelona.areaRodaje.getAvionesDentro()+""; 
    }
    public void setCerrarPasoPistaXMadrid(int x) throws RemoteException {
        aeropuertoMadrid.pista.cerrarPasoPistas(x); 
    }
    public void setCerrarPasoPistaXBarcelona(int x) throws RemoteException {
       aeropuertoBarcelona.pista.cerrarPasoPistas(x);
    }
    public void setAbrirPasoPistaXMadrid(int x) throws RemoteException {
        aeropuertoMadrid.pista.abrirPasoPistas(x); 
    }
    public void setAbrirPasoPistaXBarcelona(int x) throws RemoteException {
       aeropuertoBarcelona.pista.abrirPasoPistas(x); 
    }



}
