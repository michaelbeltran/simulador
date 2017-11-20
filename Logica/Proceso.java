package Logica;
import static Logica.Configuracion.TERMINADO;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Random;


public class Proceso {    
    
    private int idProceso;
    private String nombre;
    private int tamanio;
    private boolean prioridad;
    private byte estado;
    public String recursosP;
    public int marcos;
    public Proceso( int num,String nom, int tam,boolean prio, String recP){
        this.idProceso = num;        
        this.nombre = nom;
        this.tamanio = tam;
        this.prioridad = prio;
        this.recursosP = recP;
        this.marcos = (int)Math.ceil((double)tam/(double)Configuracion.TAM_MAX_PROC);
    }
    public int vIdPro(){
        return this.idProceso;
    }
    public String vNombre(){
        return this.nombre;
    }
    public int vTamanio(){
        return this.tamanio;
    }
    public boolean vPriori(){
        return this.prioridad;
    }
    public void aEstado(byte est){
            this.estado = est;
    }
    public byte vEstado(){
        return this.estado;
    }
    public String vRecu(){
        return this.recursosP;
    }
    public int vMarco(){
        return this.marcos;
    }
}
