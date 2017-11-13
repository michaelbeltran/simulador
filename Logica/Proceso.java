/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Random;

/**
 *
 * @author Sebastian
 */
public class Proceso {    
    public static final byte EJECUCION=0;
    public static final byte BLOQUEADO=1;
    public static final byte LISTO=2;
    public static final byte TERMINADO=3;
    private String nombre;
    private byte estado;
    private int tamanio;
    private int numProceso;
    public String recursosP;
    public Proceso(int tam, int num, String recP){
        this.tamanio = tam;
        this.nombre = "P"+Integer.toString(num);
        this.numProceso = num;
    }//
    public int vNumPro(){
        return this.numProceso;
    }
    public void aEstado(byte est){
            this.estado = est;
    }

    public void rTamanio(){//reducir tamaño
        this.tamanio--;//si es por unidades
    }
    public int vTamanio(){
        return this.tamanio;
    }
    public byte vEstado(){
        return this.estado;
    }
    public String vNombre(){
        return this.nombre;
    }
    
    public boolean completo(){
        if(this.tamanio <= 0){
            this.estado = TERMINADO;
            return true;
        }
        return false;
    }
	
/*
java.util.concurrent.TimeUnit
TimeUnit.SECONDS.sleep(1);
*/
    
}
