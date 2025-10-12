import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculos {
    private Integer opcion;
    private String divisaBase;
    private String divisaDestino;
    private double valorUnitario;
    private double cantidad;
    //Creacion del array divisasBase
    private String[] divisasBase = {"USD", "ARS", "BRL", "COP"};

    //Crear un ArrayList
    private List<String> lista = new ArrayList<>();

    //Crear nueva lista
    List<String> listaDivisas = Arrays.asList(divisasBase);

    public Calculos(String opcion, ConsultaAPI consultaAPI, double cantidad) {
        // opcion elegida por el usuario
        this.opcion = Integer.valueOf(opcion);
        this.divisaBase = obtendivisaBase();
        this.divisaDestino = obtendivisaDestino();

        //valior unitario
        this.valorUnitario = consultaAPI.buscaDivisa(divisaBase, divisaDestino);

        // valor introducido por el usuario
        this.cantidad = cantidad;
    }

    public String obtendivisaBase() {
        return switch (opcion) {
            case 1, 3, 5 -> listaDivisas.get(0);
            case 2 -> listaDivisas.get(1);
            case 4 -> listaDivisas.get(2);
            case 6 -> listaDivisas.get(3);
            default -> null;
        };
    }

    public String obtendivisaDestino() {
        return switch (opcion) {
            case 2, 4, 6 -> listaDivisas.get(0);
            case 1 -> listaDivisas.get(1);
            case 3 -> listaDivisas.get(2);
            case 5 -> listaDivisas.get(3);
            default -> null;
        };
    }

    public double calculaValorFinalDivisa() {
        return cantidad * valorUnitario;
    }
}
