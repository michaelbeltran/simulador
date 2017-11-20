/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
public class Recurso {
    public int id;
    boolean estado;
    public int idProc;
    public Recurso(int identificador){
        id=identificador;
        estado=true;
    }
    public boolean RetornarEstado(){
        return estado;
    }
    public void aEstado(boolean es, int pro){
        estado = es;
        idProc = pro;
    }
    
}
