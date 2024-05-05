/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * Clase TextLog para registrar eventos y acciones en un archivo de texto.
 * Permite escribir mensajes con un timestamp y el nombre de la ciudad asociada
 * al evento. Utiliza un archivo de texto para almacenar los registros de
 * eventos.
 *
 */
public class TextLog {

    private String nombreArchivo;

    private SimpleDateFormat formatoTiempo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat formatoDocumento = new SimpleDateFormat("HH-mm-ss");

    private Semaphore control = new Semaphore(1, true);

    public TextLog() {
        String fechaActual = formatoDocumento.format(new Date());
        this.nombreArchivo = "evolucionAeropuerto" + fechaActual + ".txt";
    }

    /**
     * Método para registrar un mensaje en el archivo de texto. El mensaje
     * incluye un timestamp y el nombre de la ciudad asociada al evento.
     *
     * @param message Mensaje a registrar en el archivo de texto.
     * @param nombreCiudad Nombre de la ciudad asociada al evento.
     */
    public void log(String message, String nombreCiudad) {
        try {
            control.acquire();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
                String timestamp = formatoTiempo.format(new Date());
                writer.write("[" + timestamp + "] " + message + "- " + nombreCiudad);
                writer.newLine();
            }
            control.release();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
