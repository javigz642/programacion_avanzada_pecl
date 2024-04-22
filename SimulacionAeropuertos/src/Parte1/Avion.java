/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;
// Esto es un cambio
/**
 *
 * @author ediso
 */
public class Avion extends Thread{
    private String identificador;
    private int numero;
    private final int pasajerosMax;
    private Hangar hangar;

    //Constructor NO definitivo!!
    public Avion(String identificador, int numero, Hangar hangar) {
        this.identificador = identificador +"-"+ String.format("%04d", numero);
        this.numero = numero;
        this.pasajerosMax = (int) (Math.random()*201) +100;
        this.hangar = hangar;
    }
    
    public void run(){
        
        hangar.entrarHangar(this);
        
    }

    public String getIdentificador() {
        return identificador;
    }

    
    
    
    
    
}
