/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Clases;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementación de la interfaz InterfaceInformar que proporciona métodos para
 * obtener información sobre el estado de los aeropuertos y controlar las
 * pistas.
 */
public class Informador extends UnicastRemoteObject implements InterfaceInformar {

    private Aeropuerto aeropuertoMadrid;
    private Aeropuerto aeropuertoBarcelona;

    protected Informador(Aeropuerto madrid, Aeropuerto barcelona) throws RemoteException {
        this.aeropuertoMadrid = madrid;
        this.aeropuertoBarcelona = barcelona;
    }

    public String numPasajerosAeropuertoMadrid() throws RemoteException {

        return aeropuertoMadrid.getPersonasDentro() + "";
    }

    public String numPasajerosAeropuertoBarcelona() throws RemoteException {

        return aeropuertoBarcelona.getPersonasDentro() + "";
    }

    public String numAvionesHangarMadrid() throws RemoteException {
        return aeropuertoMadrid.getHangar().getAvionesDentro() + "";
    }

    public String numAvionesHangarBarcelona() throws RemoteException {
        return aeropuertoBarcelona.getHangar().getAvionesDentro() + "";
    }

    public String numAvionesTallerMadrid() throws RemoteException {
        return aeropuertoMadrid.getTaller().getAvionesDentro() + "";
    }

    public String numAvionesTallerBarcelona() throws RemoteException {
        return aeropuertoBarcelona.getTaller().getAvionesDentro() + "";
    }

    public String numAvionesAreaEstacionamientoMadrid() throws RemoteException {
        return aeropuertoMadrid.getAreaEstacionamiento().getAvionesDentro() + "";
    }

    public String numAvionesAreaEstacionamientoBarcelona() throws RemoteException {
        return aeropuertoBarcelona.getAreaEstacionamiento().getAvionesDentro() + "";
    }

    public String numAvionesAreaRodajeMadrid() throws RemoteException {
        return aeropuertoMadrid.getAreaRodaje().getAvionesDentro() + "";
    }

    public String AvionesAeroviaMadrid_Barcelona() throws RemoteException {
        return aeropuertoMadrid.getAerovia().getStringArrayAviones();
    }

    public String AvionesAeroviaBarcelona_Madrid() throws RemoteException {
        return aeropuertoBarcelona.getAerovia().getStringArrayAviones();
    }

    public String numAvionesAreaRodajeBarcelona() throws RemoteException {
        return aeropuertoBarcelona.getAreaRodaje().getAvionesDentro() + "";
    }

    public void setCerrarPasoPistaXMadrid(int x) throws RemoteException {
        aeropuertoMadrid.getPista().cerrarPasoPistas(x);
    }

    public void setCerrarPasoPistaXBarcelona(int x) throws RemoteException {
        aeropuertoBarcelona.getPista().cerrarPasoPistas(x);
    }

    public void setAbrirPasoPistaXMadrid(int x) throws RemoteException {
        aeropuertoMadrid.getPista().abrirPasoPistas(x);
    }

    public void setAbrirPasoPistaXBarcelona(int x) throws RemoteException {
        aeropuertoBarcelona.getPista().abrirPasoPistas(x);
    }

}
