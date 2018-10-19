
package logica;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Aplicacion;

/**
 *
 * @autores: Anthony Chacin, carné: 20171110998
 *            Elías Arama, carné: 20171110178
 */
public class Gerente extends Thread{
    
    Random r = new Random();
    private Semaphore sContador;
    private Cronometrador cronometrador;
    private double horaMayor;
    private double horaMenor;
    private int horasDurmiendo;
    private int diaEnSegundos;
    private String status;
    private Parametros param;
    
    public Gerente(Semaphore sContador, Parametros param, Cronometrador cronometrador){
        
        this.sContador = sContador;
        this.param = param;
        this.diaEnSegundos = this.param.getUnDiaEnSegs();
        this.horaMayor = ((this.diaEnSegundos*1000)/24)*18;
        this.horaMenor = ((this.diaEnSegundos*1000)/24)*6; 
        this.cronometrador = cronometrador;
    }
    
    @Override
    public void run(){
        
        int menor = (int)this.horaMenor;
        int mayor = (int)this.horaMayor;
        this.horasDurmiendo = r.nextInt((mayor-menor)) + menor;
        String estatus1 = "Durmiendo";
        String estatus2 = "Despachando";
        
        while(true){
            
            try {
                this.sContador.acquire();
                if(this.cronometrador.getContador() != 0){
                    sContador.release();
                    this.status = estatus1;
                    
                }else{
                    this.status = estatus2;
                    
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
        }
    }
    
}
