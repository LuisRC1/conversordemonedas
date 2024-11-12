import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaAPI consultaAPI = new ConsultaAPI();
        while (true) {
            try {
                String opcion = "";
                boolean opcionValida = false;
                while(!opcionValida) {
                    System.out.println("""
                        ****************************************\
                        
                        Sea bienvenido al "Conversor de monedas"\
                        
                        
                        1) Dolar ==> Peso argentino\
                        
                        2) Peso argentino ==> Dolar\
                        
                        3) Dolar ==> Real brasileño\
                        
                        4) Real brasileño ==> Dolar\
                        
                        5) Dolar ==> Peso colombiano\
                        
                        6) Peso colombiano ==> Dolar\
                        
                        7) Salir\
                        
                        
                        Elija una opción válida\
                        
                        ****************************************""");
                    opcion = scanner.nextLine();
                    if (opcion.matches("[1-7]")) {
                        opcionValida = true;
                    } else {
                        System.out.println("Error: Opción inválida. Por favor, elija un número entre 1 y 7.");
                    }
                }

                if (opcion.equals("7")) {
                    System.out.println("Finalizando la aplicación");
                    break;
                }

                System.out.println("Ingresa el valor que deseas convertir:");

                var entrada = scanner.nextLine();
                double cantidad;

                try {
                    cantidad = Double.parseDouble(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Error: La entrada no es un número válido.");
                    continue; // Vuelve al inicio del bucle
                }
                Calculos calculos = new Calculos(opcion, consultaAPI, cantidad);
                System.out.println("El valor " + cantidad + " [" + calculos.obtendivisa1()+ "]" + " corresponde al valor final de ==> " + calculos.calculaValorFinalDivisa() + " [" + calculos.obtendivisa2()+ "]");
            } catch (NumberFormatException e) {
                System.out.println("Número no encontrado " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Finalizando la aplicación");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
        System.out.println("Finalizó la ejecución del programa");
    }
}
