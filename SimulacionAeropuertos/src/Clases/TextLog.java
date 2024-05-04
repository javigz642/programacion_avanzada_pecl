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

/**
 *
 * @author Miguel
 */
public class TextLog {

    private String nombreArchivo;

    public TextLog(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void log(String message, String nombreCiudad) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            SimpleDateFormat formatoT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = formatoT.format(new Date());

            writer.write("[" + timestamp + "] " + message + "- " + nombreCiudad);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
