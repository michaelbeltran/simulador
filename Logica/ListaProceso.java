/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import static Logica.Configuracion.BLOQUEADO;
import static Logica.Configuracion.EJECUCION;
import static Logica.Configuracion.LISTO;
import static Logica.Configuracion.TERMINADO;
import java.util.ArrayList;
import java.util.Random;

public class ListaProceso {

    public ArrayList<Proceso> procesos = new ArrayList();
    public ArrayList<Recurso> recurso;
    public Memoria memoria;

    public ListaProceso() {
        recurso = new ArrayList();
        Recurso r1 = new Recurso(1);
        Recurso r2 = new Recurso(2);
        Recurso r3 = new Recurso(3);
        recurso.add(r1);
        recurso.add(r2);
        recurso.add(r3);
        memoria = new Memoria();
    }

    public void aProceso(int num,String nom, int tam,boolean prio, String recP) {
        Proceso tmpPro = new Proceso(num, nom, tam, prio, recP);
        tmpPro.aEstado(LISTO);
        this.procesos.add(tmpPro);
        this.memoria.aProceso(tmpPro);
    }

    public ArrayList<Byte> vEstadoProceso() {
        ArrayList<Byte> listaProcesos = new ArrayList<>();
        for (Proceso pro : this.procesos) {
            listaProcesos.add(pro.vEstado());
        }
        return listaProcesos;
    }
    public void cambiarEstProceso(int tiempo, int proc) {
        for (Proceso pro : this.procesos) {
            if (this.bloqueo(pro)) {
                pro.aEstado(BLOQUEADO);
            } else if (pro.vIdPro() == proc && pro.vEstado() != TERMINADO) {
                pro.aEstado(EJECUCION);
                this.liberar(pro);
                if(this.memoria.procesoMemoria(proc))
                    pro.aEstado(TERMINADO);
            } else if (pro.vEstado() != TERMINADO) {
                pro.aEstado(LISTO);
            }
        }
    }

    public boolean bloqueo(Proceso p) {
        if(p.recursosP.length() == 0)
            return false;
        String[] re = p.recursosP.split(",");
        int con = 0;
        for (String i : re) {
            for (Recurso r : recurso) {
                if (Integer.parseInt(i) == r.id && (r.RetornarEstado() || r.idProc == p.vIdPro())) {
                    con++;
                }
            }
        }
        if (re.length == con) {
            for (String i : re) {
                for (Recurso r : recurso) {
                    if (Integer.parseInt(i) == r.id) {
                        r.aEstado(false,p.vIdPro());
                    }
                }
            }
            return false;
        } else {
            return true;
        }
    }

    public void liberar(Proceso p) {
        if(p.recursosP.length() == 0)
            return;
        String[] re = p.recursosP.split(",");
        Random rn = new Random();
        int uso = (int) (rn.nextDouble() * 10);
        for (String i : re) {
            for (Recurso r : recurso) {
                if (Integer.parseInt(i) == r.id
                        && (uso == 1 || uso == 2)) {
                    r.aEstado(true,0);
                }
            }
        }
    }

    public boolean termina() {
        int con = 0;
        for(Proceso pro : this.procesos) {
            if (pro.vEstado() == TERMINADO) {
                con++;
            }
        }
        return procesos.size() == con;
    }
}
