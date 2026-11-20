package uni;

public class AgujeroRana {
    private int valor; // valor del agujero de la mesa
    private int cantidad; // número de discos introducidos en el agujero

    public AgujeroRana(int valor, int cantidad) {
        this.valor = valor;
        this.cantidad = cantidad;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "AgujeroRana{valor=" + valor + ", cantidad=" + cantidad + "}";
    }
}