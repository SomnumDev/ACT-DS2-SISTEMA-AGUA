package modelo;
import java.io.Serializable;

public class Tarifas implements Serializable {
    private double[] tarifas;
    public Tarifas() {
        tarifas = new double[8];
    }
    public void setTarifa(int tipo, double monto) {
        if(tipo >= 1 && tipo <= 8) {
            tarifas[tipo - 1] = monto;
        }
    }

    public double getTarifa(int tipo) {
        if(tipo >= 1 && tipo <= 8) {
            return tarifas[tipo - 1];
        }
        return 0.0;
    }
    public double[] getArregloTarifas() {
        return tarifas;
    }
    public void setArregloTarifas(double[] tarifas) {
        this.tarifas = tarifas;
    }
}