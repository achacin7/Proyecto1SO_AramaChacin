package logica;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaz.HomePage;

/**
 *
 * @autores: Anthony Chacin, carné: 20171110998 Elías Arama, carné: 20171110178
 */
public class ProductorBateria extends Productor {

    public ProductorBateria(Almacen almacen, Semaphore sP, Semaphore sC, Semaphore sMutex, int proximoProducir, int valor, Parametros param, int tp) {
        super(almacen, sP, sC, sMutex, proximoProducir, valor, param, tp);
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Determinar de acuerdo al tipo de productor cuanto tiempo va a tradar por cada cosa que produzca
                sP.acquire();
                Thread.sleep(param.getUnDiaEnSegs()*1000);
                sMutex.acquire();
                HomePage.textFieldAlmacenBaterias.setText("");
                HomePage.textFieldAlmacenBaterias.setText(String.valueOf(this.almacen.getCantUnidades()));
                this.producir();
                //Thread.sleep(param.getUnDiaEnSegs() * 1000);
                sMutex.release();
                sC.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(ProductorBateria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
