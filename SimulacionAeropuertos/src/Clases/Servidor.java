/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Clase que representa un servidor de registro RMI. Este servidor registra un
 * objeto de tipo Informador que implementa la interfaz RMI. Extiende la clase
 * Thread para permitir la ejecución del servidor en un hilo separado.
 *
 */
public class Servidor extends Thread {

    private Aeropuerto aeropuertoMadrid;
    private Aeropuerto aeropuertoBarcelona;

    public Servidor(Aeropuerto aeropuertoMadrid, Aeropuerto aeropuertoBarcelona) {
        this.aeropuertoMadrid = aeropuertoMadrid;
        this.aeropuertoBarcelona = aeropuertoBarcelona;
    }

    public void run() {
        try {
            Informador obj = new Informador(aeropuertoMadrid, aeropuertoBarcelona); //Crea una instancia del objeto que implementa la interfaz, como objeto a registrar 
            Registry registry = LocateRegistry.createRegistry(1099); //Arranca rmiregistry local en el puerto 1099
            Naming.rebind("//localhost/ObjetoInformador", obj);
            System.out.println("El Objeto Informador ha quedado registrado");

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
