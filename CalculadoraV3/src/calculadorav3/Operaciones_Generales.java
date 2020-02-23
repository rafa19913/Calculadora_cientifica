package calculadorav3;

public abstract class Operaciones_Generales {

    public String TipoDeOperacion;
    public double operacion1;
    public double operacion2;



    /**
     * Buscamos la operacion a realizar
     * leeyendo TipoDeOperacion en caso de no ser una operacion regresar un 0
     * @return operacion
     */
    public double realizarOperacion() {

        switch ( TipoDeOperacion ) {
            case "+":
                return operacion1 + operacion2;

            case "-":
                return operacion1 - operacion2;

            case "x":
                return operacion1 * operacion2;

            case "÷":
                return operacion1 / operacion2;

            case "^":
                return Math.pow(operacion1,operacion2);

            case "%":
                return (operacion1 + operacion2) / 100;

            case "!":
            case "P":
                return factorialDeUnNumero(operacion1);

            case "Sen":
                return Math.sin(operacion1);

            case "Cos":
                return Math.cos(operacion1);

            case "Tan":
                return Math.tan(operacion1);

            case "Sen-1":
                return Math.asin(operacion1);

            case "Cos-1":
                return Math.acos(operacion1);

            case "Tan-1":
                return Math.atan(operacion1);

            case "√":
                return Math.pow(operacion1, 1.0/operacion2);

            case "Log":
                return Math.log10(operacion1);

            case "Ln":
                return Math.log(operacion1);
            case "C":
                if (operacion2>operacion1)
                    return 0;
                return factorialDeUnNumero(operacion1) / (factorialDeUnNumero(operacion2)*(factorialDeUnNumero(operacion1-operacion2)));

        }
        return 0;

    }



    /**
    * calculamos el factorial de un numero recibido
    * @return facotiral de operacion1
    */
    private double factorialDeUnNumero(double numero){
        int num = (int) numero; // Convertimos operacion1 a entero
        int factorial=1;

        while (num!=0){
            factorial*=num;
            num--;
        }
        return factorial;
    }


}
