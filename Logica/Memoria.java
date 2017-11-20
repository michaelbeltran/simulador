package Logica;
import static Logica.Configuracion.*;
import java.util.ArrayList;
import java.util.Random;

public class Memoria {
    int tmMV = MV/MARCO;
    int tmML = ML/MARCO;
    public int[][] memVirtual = new int[tmMV][2];//[idProceso, pagina]
    public int[][] memLogica = new int[tmML][2];
    public ArrayList<Integer[]> procesos = new ArrayList();
    public void aProceso(Proceso pro){
        Random ind = new Random();
        int v, l;
        for(int i = 0; i < pro.marcos; i++){
            if(i<PAGMV){
                v=(int)((ind.nextDouble()*1000) % tmMV);
                while(memVirtual[v][0] != 0){
                    v=(int)((ind.nextDouble()*1000) % tmMV);
                }
                memVirtual[v][0] = pro.vIdPro();
                memVirtual[v][1] = i;
            }else{
                l=(int)((ind.nextDouble()*1000) % tmML);
                while(memLogica[l][0] != 0){
                    l=(int)((ind.nextDouble()*1000) % tmML);
                }
                memLogica[l][0] = pro.vIdPro();
                memLogica[l][1] = i;
            }                
        }
        Integer p[] = new Integer[2];
        p[0] = pro.vIdPro();
        p[1] = pro.vMarco()-1;
        procesos.add(p);
    }
    public boolean procesoMemoria(int idPro){//retorna true si este proceso ya acabo
        int[] ind = new int[PAGMV];
        int[] pag = new int[PAGMV];
        int cont=0, tem;
        for(int i=0; i<tmMV;i++){
            if(memVirtual[i][0] == idPro){
                ind[cont] = i;//obtiene los indices del proceso actual en la MV
                pag[cont] = memVirtual[i][1];//obtiene las paginas del proceso actual en la MV
                cont++;
            }
        }
        for(Integer[] a:procesos){
            if(a[0] == idPro && mayor(pag) == a[1])
                return true;
        }
        //cambia de sitio las paginas 
        for(int i=0; i < tmML;i++){
            for(int j=0; j<ind.length; j++){
                if(memLogica[i][0]==idPro && memLogica[i][1] == (pag[j]+PAGMV)){
                    tem = memVirtual[ind[j]][1];
                    memVirtual[ind[j]][1] = memLogica[i][1];
                    memLogica[i][1] = tem;
                }
            }
        }
        return false;
    }
    public boolean primeraP(int idPro){
        for(int i=0; i<tmMV;i++){
            if(memLogica[i][0] == idPro && memLogica[i][1] == 1){
                return true;
            }
        }
        return false;
    }
    private int mayor(int[] n){
        int nm=n[0];
        for(int i=1; i < n.length; i++){
            if(n[i]>nm)
                nm = n[i];
        }
        return nm;
    }
    public int[][] memoriaV(){
        return memVirtual;
    }
    public int[][] memoriaL(){
        return memLogica;
    }
}
