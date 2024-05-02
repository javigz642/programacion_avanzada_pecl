/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parte1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// La clase Paso define un cerrojo con un Condition para la variable booleana cerrado
// que es comprobada por un proceso.
// Si vale false(abierto) el proceso puede continuar. Si es true(cerrado) el proceso se detiene
public class PasoPistas
{
    private boolean cerrado[]={false,false,false,false};
    private Lock cerrojo = new ReentrantLock();
    private Condition parar = cerrojo.newCondition();

    public void mirar(int numero)
    {
        try{
            
            cerrojo.lock();
            //imprimir();

            while(cerrado[numero])
            {
                try
                {
                    parar.await();
                } catch(InterruptedException ie){ }
            }
        }
        finally {
            cerrojo.unlock();
        }
    }
    
    public void abrir(int numero)
    {
        
        try
        {
            cerrojo.lock();
            cerrado[numero]=false;
            parar.signalAll();
        }
        finally
        {
            cerrojo.unlock();
        }
    }
    
    
    public void cerrar(int numero)
    {

        try
        {
            cerrojo.lock();
            cerrado[numero]=true;
        }
        finally
        {
            cerrojo.unlock();
        }
    }
    public void imprimir(){
        String aux = "";
        for (int i = 0; i < cerrado.length; i++) {
            aux += (cerrado[i]?"true":"false") + "/";
        }
        System.out.println(aux);
    }
}