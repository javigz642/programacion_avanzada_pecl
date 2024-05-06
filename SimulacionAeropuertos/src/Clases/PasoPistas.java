/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* La clase Paso define un cerrojo con un Condition para la variable booleana cerrado
* que es comprobada por un proceso.
* Si vale false(abierto) el proceso puede continuar. Si es true(cerrado) el proceso se detiene
*
* El array booleano soloUnHilo permite que al pararse una determinada pista solo un hilo 
* perteneciente a esa pista imprima en el log.
*/
public class PasoPistas {

    
    private String nombreCiudad;

    private Lock cerrojo = new ReentrantLock();
    private Condition parar = cerrojo.newCondition();
    
    private boolean cerrado[] = {false, false, false, false};
    private boolean soloUnHilo[] = {true, true, true, true};
    
    private TextLog logger;

    public PasoPistas(TextLog logger, String nombreCiudad) {
        this.logger = logger;
        this.nombreCiudad = nombreCiudad;
    }

    public void mirar(int numero) {
        try {
            cerrojo.lock();
            while (cerrado[numero]) {
                try {
                    parar.await();
                } catch (InterruptedException ie) {
                }
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void abrir(int numero) {

        try {
            cerrojo.lock();
            if (!soloUnHilo[numero]) {
                logger.log("Reanudando PISTA " + (numero + 1), nombreCiudad);
                cerrado[numero] = false;
                soloUnHilo[numero] = true;
                parar.signalAll();
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void cerrar(int numero) {

        try {

            cerrojo.lock();
            if (soloUnHilo[numero]) {
                logger.log("Parando PISTA " + (numero + 1), nombreCiudad);
                cerrado[numero] = true;
                soloUnHilo[numero] = false;
            }

        } finally {
            cerrojo.unlock();
        }
    }
}
