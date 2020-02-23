/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadorav3;

import java.io.Reader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import javax.script.*;
import java.io.Reader;



/**
 *
 * @author rafar
 */
public class FXMLDocumentController implements Initializable {

    

    @FXML
    private TextField pantalla;

    @FXML
    private TextField ultimaOperacion;


   private double operacion1=0.0;
   private double operacion2=0.0;
   private boolean realizarOperacion=false;
   private boolean shift = false;
   private double resultado=0;
   private String operacionActual = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clic_Siete(ActionEvent event) { imprimirDigito("7");}

    @FXML
    private void clic_Cero(ActionEvent event) {imprimirDigito("0"); }

    @FXML
    private void clic_Ocho(ActionEvent event) { imprimirDigito("8");}

    @FXML
    private void clic_Uno(ActionEvent event) {
        imprimirDigito("1");
    }

    @FXML
    private void clic_Dos(ActionEvent event) {
        imprimirDigito("2");
    }

    @FXML
    private void clic_Tres(ActionEvent event) {
        imprimirDigito("3");
    }

    @FXML
    private void clic_Cuatro(ActionEvent event) {
        imprimirDigito("4");
    }

    @FXML
    private void clic_Cinco(ActionEvent event) {
        imprimirDigito("5");
    }

    @FXML
    private void clic_Seis(ActionEvent event) {
        imprimirDigito("6");
    }

    @FXML
    private void clic_Nueve(ActionEvent event) {
        imprimirDigito("9");
    }


    @FXML
    private void clic_PosNeg(ActionEvent event) {

    }



    @FXML
    private void clic_Por(ActionEvent event) {
        imprimirDigito("*");
    }


    @FXML
    private void clic_Mas(ActionEvent event) {
        imprimirDigito("+");
    }
   

    @FXML
    private void clic_Menos(ActionEvent event) {
      imprimirDigito("-");
    }
   

    @FXML
    private void clic_Entre(ActionEvent event) {
        imprimirDigito("/");
    }

    @FXML
    private void clic_Shift(ActionEvent event) {
        shift = true;
    }

    @FXML
    private void clic_Raiz(ActionEvent event) {
            imprimirDigito("√(");
    }


    @FXML
    private void clic_Factorial(ActionEvent event) {
        if (validarPantalla())
            return;

        mandarOperacion("!");
    }


    @FXML
    private void clic_Ln(ActionEvent event) {
        imprimirDigito("Ln(");
    }

    @FXML
    private void clic_ParAbi(ActionEvent event) {
        imprimirDigito("(");
    }

    @FXML
    private void clic_ParCerr(ActionEvent event) {
        imprimirDigito(")");
    }

    @FXML
    private void clic_E(ActionEvent event) {
        imprimirDigito(String.valueOf(Math.E));
    }

    @FXML
    private void clic_PI(ActionEvent event) throws ScriptException {
        imprimirDigito(String.valueOf(Math.PI));
    }

    @FXML
    private void clic_Combinaciones(ActionEvent event){
        if (validarPantalla())
            return;

        operacionActual="C";
        mandarOperaciones();
        realizarOperacion=true;
    }


    @FXML
    private void clic_Tan(ActionEvent event) {

        if (shift){
            imprimirDigito("Tan-1(");
            shift=false;
        }
        else
            imprimirDigito("Tan(");
    }
    @FXML
    private void clic_Elevado(ActionEvent event) {
        operacionActual="^";
        mandarOperaciones();
        realizarOperacion=true;
    }



    @FXML
    private void clic_Sen(ActionEvent event) {
        if (shift) {
            imprimirDigito("Sen-1(");
            shift=false;
        }
        else
            imprimirDigito("Sen(");
    }

    @FXML
    private void clic_Cos(ActionEvent event) {

        if (shift){
            imprimirDigito("Cos-1(");
            shift=false;
        }
        else
            imprimirDigito("Cos(");
    }


    @FXML
    private void clic_Igual(ActionEvent event) throws ScriptException {
        if (validarPantalla())
            return;
        else {
            String operacionPantalla = pantalla.getText();
            tomarTodaString();
            operacionActual="";
            realizarOperacion=false;
            shift=false;
        }
    }


    @FXML
    private void clic_Del(ActionEvent event) {
        // Limpiamos la pantalla
        pantalla.setText("");
        realizarOperacion=false;
        shift = false;
        ultimaOperacion.setText("");
    }


    @FXML
    private void clic_Punto(ActionEvent event) {
        String digitoEnPantalla=pantalla.getText();

        if (validarPantalla())
            return;

        imprimirDigito(".");

    }


    //-----------------------METODOS --------------------------------------

    /**
     * Imprime un digito u operando en pantalla concatenado con los anteriores
     * @param numero u operando
     */
    private void imprimirDigito(String numero){
        String digitoEnPantalla=pantalla.getText();
        pantalla.setText(digitoEnPantalla + numero);
    }


    private boolean validarPantalla(){
        String digitoEnPantalla = pantalla.getText();

        if (digitoEnPantalla.isEmpty())
            return true;
        else
            return false;

    }

    /**
     * Se realiza una sola operacion por ejemplo (factorial y potencia)
     * @param operacion (tipo de operacion (factorial o potencia)
     */

    private void mandarOperacion(String operacion){
        String digitoEnPantalla=pantalla.getText();

        operacion1 = Double.parseDouble(digitoEnPantalla);
        pantalla.setText("");
        
        Operaciones op1 = new Operaciones(operacion1,operacion);
        ultimaOperacion.setText("Operacion: "+operacion +" "+ String.valueOf(operacion1));
        resultado = op1.realizarOperacion();
        pantalla.setText(String.valueOf(resultado));


    }


    /**
     * Se realizar varias operaciones (por ejemplo PI * 5) + (e / 2)
     */
    public void mandarOperaciones(){
        String digitoEnPantalla=pantalla.getText();
        DecimalFormat df = new DecimalFormat("#.00");

        if (realizarOperacion){
            operacion2 = Double.valueOf(digitoEnPantalla);
            Operaciones o1 = new Operaciones(operacion1,operacion2,operacionActual);
            ultimaOperacion.setText("Operacion: "+String.format("%.2f",operacion1) +" "+operacionActual+ " "+String.format("%.2f",operacion2));
            resultado = o1.realizarOperacion();
            pantalla.setText(String.valueOf(resultado));
            // pantalla.setText(String.valueOf((double) Math.round(resultado * 100d) / 100d));
            realizarOperacion=false;

        }else{
            operacion1 = Double.valueOf(digitoEnPantalla);
            pantalla.setText("");
            realizarOperacion = true;
            }

    }
    



    /**
     * Realiza toda la operacion
     * @throws ScriptException
     */
    private void tomarTodaString() throws ScriptException {

        ScriptEngine mgr = new EntradaCadena();

         if (realizarOperacion) {   // si existe una operacion diferente se sale de la funcion y manda llamar mandarOperaciones();
             mandarOperaciones();
            return;
         }


         ScriptEngineManager script = new ScriptEngineManager();
         ScriptEngine eng = script.getEngineByName("JavaScript");

         String realizarLAOP = pantalla.getText();
         ultimaOperacion.setText("Operacion: " + realizarLAOP);

         realizarLAOP=reemplazarOperaciones(realizarLAOP);

         double resultadoFinal = Double.parseDouble(String.valueOf(eng.eval(realizarLAOP)));
         pantalla.setText("R:"+resultadoFinal);





     }


    /**
     * Reemplaza las operaciones ingresadas por las funciones de las operaciones
     * @param realizarLAOP
     * @return realizarLAOP (con los reemplazos correspondientes)
     */
     private String reemplazarOperaciones(String realizarLAOP){
         realizarLAOP=realizarLAOP.replaceAll("√", "Math.sqrt");
         realizarLAOP=realizarLAOP.replaceAll("Sen-1", "Math.asin");
         realizarLAOP=realizarLAOP.replaceAll("Cos-1", "Math.acos");
         realizarLAOP=realizarLAOP.replaceAll("Tan-1", "Math.atan");
         realizarLAOP=realizarLAOP.replaceAll("Sen", "Math.sin");
         realizarLAOP=realizarLAOP.replaceAll("Cos", "Math.cos");
         realizarLAOP=realizarLAOP.replaceAll("Tan", "Math.tan");
         realizarLAOP=realizarLAOP.replaceAll("Ln", "Math.log");


        return realizarLAOP;
     }




    
}


    
