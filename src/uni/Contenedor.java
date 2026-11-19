package uni;

class Contenedor {
    private int tiempo; // tiempo en minutos que se tarda en recuperar el contenedor
    private int dias; //número de días que proporciona alimento

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public Contenedor(int tiempo, int dias) {
        this.tiempo = tiempo;
        this.dias = dias;
    }


    //metodo necesario para 2022-DICIEMBRE

    public float getBeneficio(){
        return (float)this.dias / (float)this.tiempo;
    }
}