/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paquete;

/**
 *
 * @author Bryan
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Principal {

    public static String[] opcion1 = new String[3];
    public static String[] opcion2 = new String[10];
    public static String[] opcion3 = new String[6];
    public static Object seleccion;
    public static double[] tiposDeCambio = new double[10];
    public static int confirmar;
    public static int cantidad=0;

    public static void main(String[] args) {
        metodo_principal();

    }
    
    public static void metodo_principal(){
        do {
            arreglo();
            ingresaropcion("Opciones de conversor",opcion1);
            if (seleccion.equals(opcion1[0])) {
                JOptionPane.showMessageDialog(null, "No es una opción válida");
            } else if (seleccion.equals(opcion1[1])) {
                    metodo_opcion1();
              } else if (seleccion.equals(opcion1[2])) {
                    metodo_opcion2();
              }
            
        } while (seleccion.equals("Seleccione"));
    }
    public static void metodo_opcion1(){
        
            recibir_cantidad("Ingresa la cantidad que desea convertir: ");
            System.out.println(" " + cantidad);
            if (cantidad<=0) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida");
                metodo_opcion1();
            } else {
                        
                arreglo2();
                ingresaropcion("Conversor de monedas",opcion2);
                cargarTiposDeCambio();
                conversion_moneda(seleccion);
                
                confirmar=JOptionPane.showConfirmDialog(null, "Desea continuar?");
                //Cancelar ---2
                //No-----1
                //Si----0
                switch(confirmar){
                    case 0: metodo_opcion1(); break;
                    case 1: JOptionPane.showMessageDialog(null, "Programa terminado"); break;
                    case 2: metodo_principal();break;
                }
            }
            cantidad=0;
        
        
    }
    
    public static void metodo_opcion2(){
        recibir_cantidad("Ingresa la cantidad que desea convertir: ");
        System.out.println(" " + cantidad);
        if (cantidad<=0) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida");
                metodo_opcion2();
            } else {
                arreglo3();
                ingresaropcion("Conversor de tiempo", opcion3);
                conversion_tiempo(seleccion);
                confirmar=JOptionPane.showConfirmDialog(null, "Desea continuar?");
                //Cancelar ---2
                //No-----1
                //Si----0
                switch(confirmar){
                    case 0: metodo_opcion2(); break;
                    case 1: JOptionPane.showMessageDialog(null, "Programa terminado"); break;
                    case 2: metodo_principal();break;
                }
            
        }
        cantidad=0;
    }
    
    public static void arreglo() {
        opcion1[0] = "Seleccione";
        opcion1[1] = "Conversor de moneda";
        opcion1[2] = "Conversor de tiempo";
    }
    public static void arreglo2(){
        opcion2[0] = "De soles a Dolar";
        opcion2[1] = "De soles a Euro";
        opcion2[2] = "De soles a Libras";
        opcion2[3] = "De soles a Yen";
        opcion2[4] = "De soles a Wong coreano";
        opcion2[5] = "De Dolar a soles";
        opcion2[6] = "De Euro a soles";
        opcion2[7] = "De Libras a soles";
        opcion2[8] = "De Yen a soles";
        opcion2[9] = "De wong coreano a soles";
    }
    
    public static void arreglo3(){
        opcion3[0] = "De milisegundos a segundos";
        opcion3[1] = "De milisegundos a minutos";
        opcion3[2] = "De milisegundos a horas";
        opcion3[3] = "De segundos a milisegundos";
        opcion3[4] = "De minutos a milisegundos";
        opcion3[5] = "De horas a milisegundos";
    }
    public static void ingresaropcion(String titulo,String[] opcion) {
        try {
            seleccion = JOptionPane.showInputDialog(null, "Selecciona una opción",
                    titulo, JOptionPane.QUESTION_MESSAGE, null, opcion, "Seleccione");

            if (seleccion == null) {
                // Si se cierra la ventana del JOptionPane
                System.out.println("Se ha cerrado la ventana del JOptionPane.");
                System.exit(0);
                
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir
            System.out.println("Error al mostrar el JOptionPane: " + e.getMessage());
            System.exit(1); // Salir del programa con código de error
        }
    }

    public static void recibir_cantidad(String mensaje) {
        try {
            String input  =JOptionPane.showInputDialog(mensaje);
            if (input!=null){
                cantidad = Integer.parseInt(input);
            }else{
                System.out.println("Se ha seleccionado la opcion cancelar/volver.");
                metodo_principal();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida");
            //recibir_cantidad();
        }
    }
    
    
    //Este metodo lee el contenido del archivo de texto "tipos_de_cambio"
    private static void cargarTiposDeCambio() {
    try (BufferedReader br = new BufferedReader(new FileReader("tipos_de_cambio.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String moneda = parts[0].trim();
                double tipoCambio = Double.parseDouble(parts[1].trim());
                asignarTipoDeCambio(moneda, tipoCambio);//Se guarda en cada elemento del arreglo(tiposdecambio) un tipo de cambio
            }
        }
    } catch (IOException e) {
        System.out.println("Error al cargar los tipos de cambio: " + e.getMessage());
        System.exit(1);
    }
}

    private static void asignarTipoDeCambio(String moneda, double tipoCambio) {
        if (moneda.equalsIgnoreCase("Sol-Dolar")) {
            tiposDeCambio[0] = tipoCambio;
        } else if (moneda.equalsIgnoreCase("Sol-Euro")) {
            tiposDeCambio[1] = tipoCambio;
        } else if (moneda.equalsIgnoreCase("Sol-Libra")) {
            tiposDeCambio[2] = tipoCambio;
        } else if (moneda.equalsIgnoreCase("Sol-Yen")) {
            tiposDeCambio[3] = tipoCambio;
        } else if (moneda.equalsIgnoreCase("Sol-WongCoreano")) {
            tiposDeCambio[4] = tipoCambio;
        }else if (moneda.equalsIgnoreCase("Dolar-Sol")) {
            tiposDeCambio[5] = tipoCambio;
        }else if (moneda.equalsIgnoreCase("Euro-Sol")) {
            tiposDeCambio[6] = tipoCambio;
        }else if (moneda.equalsIgnoreCase("Libra-Sol")) {
            tiposDeCambio[7] = tipoCambio;
        }else if (moneda.equalsIgnoreCase("Yen-Sol")) {
            tiposDeCambio[8] = tipoCambio;
        }else if (moneda.equalsIgnoreCase("WongCoreano-Sol")) {
            tiposDeCambio[9] = tipoCambio;
        }
    }
    private static void conversion_moneda(Object seleccion) {
        DecimalFormat df=new DecimalFormat("#.0");
        if (seleccion.equals(opcion2[0])) {
            double resultado = cantidad * tiposDeCambio[0];
            JOptionPane.showMessageDialog(null, cantidad + " soles son " + df.format(resultado) + " dólares.");
        } else if (seleccion.equals(opcion2[1])) {
            double resultado = cantidad * tiposDeCambio[1];
            JOptionPane.showMessageDialog(null, cantidad + " soles son " + df.format(resultado) + " euros.");
        } else if (seleccion.equals(opcion2[2])) {
            double resultado = cantidad * tiposDeCambio[2];
            JOptionPane.showMessageDialog(null, cantidad + " soles son " + df.format(resultado) + " libras.");
        } else if (seleccion.equals(opcion2[3])) {
            double resultado = cantidad * tiposDeCambio[3];
            JOptionPane.showMessageDialog(null, cantidad + " soles son " + df.format(resultado) + " yenes.");
        } else if (seleccion.equals(opcion2[4])) {
            double resultado = cantidad * tiposDeCambio[4];
            JOptionPane.showMessageDialog(null, cantidad + " soles son " + df.format(resultado) + " wong coreanos.");
        }else if (seleccion.equals(opcion2[5])) {
            double resultado = cantidad * tiposDeCambio[5];
            JOptionPane.showMessageDialog(null, cantidad + " dolares son " + df.format(resultado) + " soles.");
        } else if (seleccion.equals(opcion2[6])) {
            double resultado = cantidad * tiposDeCambio[6];
            JOptionPane.showMessageDialog(null, cantidad + " euros son " + df.format(resultado) + " soles.");
        } else if (seleccion.equals(opcion2[7])) {
            double resultado = cantidad * tiposDeCambio[7];
            JOptionPane.showMessageDialog(null, cantidad + " libras son " + df.format(resultado) + " soles.");
        } else if (seleccion.equals(opcion2[8])) {
            double resultado = cantidad * tiposDeCambio[8];
            JOptionPane.showMessageDialog(null, cantidad + " yenes son " + df.format(resultado) + " soles.");
        }else if (seleccion.equals(opcion2[9])) {
            double resultado = cantidad * tiposDeCambio[9];
            JOptionPane.showMessageDialog(null, cantidad + " wong coreanos son " + df.format(resultado) + " soles.");
        }
    }
    
    private static void conversion_tiempo(Object seleccion){
        DecimalFormat df=new DecimalFormat("#.00");
        if(seleccion.equals(opcion3[0])){
            double resultado = cantidad / 1000;
            JOptionPane.showMessageDialog(null, cantidad + " milisegundos son " + df.format(resultado) + " segundos.");
        }else if(seleccion.equals(opcion3[1])){
            double resultado = cantidad / 60000;
            JOptionPane.showMessageDialog(null, cantidad + " milisegundos son " + df.format(resultado) + " minutos.");
        }else if(seleccion.equals(opcion3[2])){
            double resultado = cantidad / 3600000;
            JOptionPane.showMessageDialog(null, cantidad + " milisegundos son " + df.format(resultado) + " horas.");
        }else if(seleccion.equals(opcion3[3])){
            double resultado = cantidad * 1000;
            JOptionPane.showMessageDialog(null, cantidad + " segundos son " + df.format(resultado) + " milisegundos.");
        }else if(seleccion.equals(opcion3[4])){
            double resultado = cantidad * 60000;
            JOptionPane.showMessageDialog(null, cantidad + " minutos son " + df.format(resultado) + " milisegundos.");
        }else if(seleccion.equals(opcion3[5])){
            double resultado = cantidad * 3600000;
            JOptionPane.showMessageDialog(null, cantidad + " horas son " + df.format(resultado) + " milisegundos.");
        }
    }

}

