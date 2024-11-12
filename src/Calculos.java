public class Calculos {
    private Integer opcion;
    private String divisa1;
    private String divisa2;
    private Double consultaAPI;
    private double cantidad;

    public Calculos(String opcion, ConsultaAPI consultaAPI, double cantidad) {
        this.opcion = Integer.valueOf(opcion); // opcion elegida por el usuario
        this.divisa1 = obtendivisa1();
        this.divisa2 = obtendivisa2();
        this.consultaAPI = consultaAPI.buscaDivisa(divisa1, divisa2); //valior unitario
        this.cantidad = cantidad; // valor introducido por el usuario
    }

    public String obtendivisa1() {

        if (opcion == 1 || opcion == 3 || opcion == 5) {
            return "USD";
        }

        if (opcion == 2) {
            return "ARS";
        }

        if (opcion == 4) {
            return "BRL";
        }

        if (opcion == 6) {
            return "COP";
        }

        else {
            return null;
        }
    }

    public String obtendivisa2() {
        if (opcion == 2 || opcion == 4 || opcion == 6) {
            return "USD";
        }
        if (opcion == 1) {
            return "ARS";
            }

        if (opcion == 3) {
            return "BRL";
        }

        if (opcion == 5) {
            return "COP";
        }

        else {
            return null;
        }
    }

    public double calculaValorFinalDivisa() {
            return cantidad * consultaAPI;
    }
}

