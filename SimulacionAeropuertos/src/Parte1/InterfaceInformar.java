/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Parte1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceInformar extends Remote
{
    String numPasajerosAeropuertoMadrid() throws RemoteException;
    String numPasajerosAeropuertoBarcelona() throws RemoteException;
    String numAvionesHangarMadrid() throws RemoteException;
    String numAvionesHangarBarcelona() throws RemoteException;
    String numAvionesTallerMadrid() throws RemoteException;
    String numAvionesTallerBarcelona() throws RemoteException;
    String numAvionesAreaEstacionamientoMadrid() throws RemoteException;
    String numAvionesAreaEstacionamientoBarcelona() throws RemoteException;
    String numAvionesAreaRodajeMadrid() throws RemoteException;
    String numAvionesAreaRodajeBarcelona() throws RemoteException;
    String AvionesAeroviaMadrid_Barcelona() throws RemoteException;
    String AvionesAeroviaBarcelona_Madrid() throws RemoteException;
    void setCerrarPasoPistaXMadrid(int x) throws RemoteException;
    void setCerrarPasoPistaXBarcelona(int x) throws RemoteException;
    void setAbrirPasoPistaXMadrid(int x) throws RemoteException;
    void setAbrirPasoPistaXBarcelona(int x) throws RemoteException;


    
//    void cerrarPista1AeropuertoMadrid() throws RemoteException;
//    void abrirPista1AeropuertoMadrid() throws RemoteException;
//    void cerrarPista2AeropuertoMadrid() throws RemoteException;
//    void abrirPista2AeropuertoMadrid() throws RemoteException;
//    void cerrarPista3AeropuertoMadrid() throws RemoteException;
//    void abrirPista3AeropuertoMadrid() throws RemoteException;
//    void cerrarPista4AeropuertoMadrid() throws RemoteException;
//    void abrirPista4AeropuertoMadrid() throws RemoteException;
//    void cerrarPista1AeropuertoBarcelona() throws RemoteException;
//    void abrirPista1AeropuertoBarcelona() throws RemoteException;
//    void cerrarPista2AeropuertoBarcelona() throws RemoteException;
//    void abrirPista2AeropuertoBarcelona() throws RemoteException;
//    void cerrarPista3AeropuertoBarcelona() throws RemoteException;
//    void abrirPista3AeropuertoBarcelona() throws RemoteException;
//    void cerrarPista4AeropuertoBarcelona() throws RemoteException;
//    void abrirPista4AeropuertoBarcelona() throws RemoteException;


}
