


package calculadorav3;

import javax.script.*;
import java.io.Reader;


public class Operaciones extends Operaciones_Generales{


    //-------Constructor para hacer 2 operaciones
    public Operaciones(double operacion1, double operacion2, String operador) {
        this.operacion1=operacion1;
        this.operacion2=operacion2;
        this.TipoDeOperacion=operador;
    }

    //--------Constructor para hacer 1 sola operacion
    public Operaciones(double operacion1 , String operador) {
        this.operacion1=operacion1;
        this.TipoDeOperacion=operador;
    }

    @Override
    public double realizarOperacion() {
        return super.realizarOperacion();
    }

        public static void main(String args[]){


            System.out.println(Math.log1p(5));


        }



        }







