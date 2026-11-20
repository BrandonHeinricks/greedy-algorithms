package uni;

public class ProductoSubasta {
    private int valor;
    private int sesion;

    public ProductoSubasta(int valor, int sesion) {
        this.valor = valor;
        this.sesion = sesion;
    }

    public int getValor() {
        return valor;
    }

    public int getSesion() {
        return sesion;
    }

    @Override
    public String toString() {
        return "ProductoSubasta{valor=" + valor + ", sesion=" + sesion + "}";
    }
}