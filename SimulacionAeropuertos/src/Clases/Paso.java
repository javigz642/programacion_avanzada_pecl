package Clases;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* La clase Paso define un cerrojo con un Condition para la variable booleana cerrado
* que es comprobada por un proceso.
* Si vale false(abierto) el proceso puede continuar. Si es true(cerrado) el proceso se detiene
*
* La variable booleana soloUnHilo permite que al pararse el programa solo un hilo 
* imprima en el log.
*/
public class Paso {

    private boolean cerrado = false;
    private boolean soloUnHilo = true;

    private Lock cerrojo = new ReentrantLock();
    private Condition parar = cerrojo.newCondition();
    private TextLog logger;

    public Paso(TextLog logger) {
        this.logger = logger;
    }

    /**
     * Método que bloquea la ejecución hasta que se abra el paso.
     */
    public void mirar() {

        try {
            cerrojo.lock();
            while (cerrado) {
                try {
                    parar.await();
                } catch (InterruptedException ie) {
                }
            }
        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Método que abre el paso, permitiendo que los hilos continúen su
     * ejecución.
     */
    public void abrir() {
        try {
            cerrojo.lock();
            if (!soloUnHilo) {
                logger.log("REANUDANDO PROGRAMA... ", "**********************************************************************************************************");
                cerrado = false;
                soloUnHilo = true;
                parar.signalAll();
            }

        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Método que cierra el paso, deteniendo la ejecución de los hilos.
     */
    public void cerrar() {
        try {

            cerrojo.lock();
            if (soloUnHilo) {
                logger.log("PARANDO PROGRAMA... ", "**********************************************************************************************************");
                cerrado = true;
                soloUnHilo = false;
            }

        } finally {
            cerrojo.unlock();
        }
    }

}
