/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import static Logica.Proceso.BLOQUEADO;
import static Logica.Proceso.EJECUCION;
import static Logica.Proceso.LISTO;
import java.util.ArrayList;
import java.util.Random;

public class ListaProceso {
    public ArrayList<Proceso> procesos = new ArrayList();
    public ArrayList<Recurso> recurso;
    public ListaProceso(){
        recurso = new ArrayList();
        Recurso r1 = new Recurso(1);
        Recurso r2 = new Recurso(2);
        Recurso r3 = new Recurso(3);
        recurso.add(r1);
        recurso.add(r2);
        recurso.add(r3);
        
    }
    public void aProceso(int tam, int num, String recu){
        Proceso tmpPro = new Proceso(tam, num, recu);
        tmpPro.aEstado(LISTO);        
        this.procesos.add(tmpPro);
    }
    public ArrayList<Byte> vEstadoProceso(){
        ArrayList<Byte> listaProcesos = new ArrayList<>();
        for (Proceso pro : this.procesos){
            listaProcesos.add(pro.vEstado());
        }
        return listaProcesos;
    }
    public String vProcBloqueado(){
        String listaProcesos="";
        for (Proceso pro : this.procesos){
            if(pro.vEstado() == BLOQUEADO){
                listaProcesos= listaProcesos.concat(pro.vNombre().concat(", "));
            }
        }
        return listaProcesos;
    }
    public String vProcListo(){
        String listaProcesos="";
        for (Proceso pro : this.procesos){
            if(pro.vEstado() == LISTO){
                listaProcesos= listaProcesos.concat(pro.vNombre().concat(", "));
            }
        }
        return listaProcesos;
    }
    public String vProcEjecucion(){
        String listaProcesos="";
        for (Proceso pro : this.procesos){
            if(pro.vEstado() == EJECUCION){
                listaProcesos = listaProcesos.concat(pro.vNombre().concat(", "));
            }
        }
        return listaProcesos;
    }
    public void cambiarEstProceso(int tiempo, int proc){
        for (Proceso pro: this.procesos){
            if(this.bloqueo(pro)){
                pro.aEstado(BLOQUEADO);
            }else if(pro.vNumPro() == proc && !pro.completo()){
               pro.aEstado(EJECUCION);
               pro.rTamanio();
               this.liberar(pro);
            }else if(!pro.completo())
                pro.aEstado(LISTO);
        }
    }
    public boolean bloqueo(Proceso p){//tiempo(part entera > 1) de bloqueo
        String[] re = p.recursosP.split(",");
        int con=0;
        for(String i : re){
            for(Recurso r : recurso){
                if(Integer.parseInt(i)==r.id && r.RetornarEstado()){
                    con++;
                }
            }
        }
        if(re.length == con){
            for(String i : re){
                for(Recurso r : recurso){
                    if(Integer.parseInt(i) == r.id){
                        r.aEstado(false);
                    }
                }
            }
            return false;
        }else return true;
            
       /* ;*/
    }
    public void liberar(Proceso p){
        String[] re = p.recursosP.split(",");
        int con=0;
        Random rn = new Random();
        int uso = (int)(rn.nextDouble()*10);
        for(String i : re){
            for(Recurso r : recurso){
                if(Integer.parseInt(i)==r.id 
                        && (uso==1 || uso ==2)){
                    r.aEstado(true);
                }
            }
        }
    }
    public boolean termina(){
        int con=0;
        for (Proceso pro : this.procesos){
            if(pro.vTamanio()==0)
                con++;
        }
        if(procesos.size() == con)
            return true;
        else
            return false;
    }
}
