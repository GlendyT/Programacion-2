package primerp;

public class Llanta {

    private int idLlanta;
    private int idVehiculo;
    private String marca;
    private int tamanio;
    private double presion;

    public Llanta() {
    }

    public Llanta(String marca, int tamanio, double presion) {
        this.marca = marca;
        this.tamanio = tamanio;
        this.presion = presion;
    }

    public Llanta(int idLlanta, int idVehiculo, String marca, int tamanio, double presion) {
        this.idLlanta = idLlanta;
        this.idVehiculo = idVehiculo;
        this.marca = marca;
        this.tamanio = tamanio;
        this.presion = presion;
    }

    public int getIdLlanta() {
        return idLlanta;
    }

    public void setIdLlanta(int idLlanta) {
        this.idLlanta = idLlanta;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public double getPresion() {
        return presion;
    }

    public void setPresion(double presion) {
        this.presion = presion;
    }

    public void mostrarInformacion() {
        System.out.println("ID de llanta: " + idLlanta);
        System.out.println("ID de vehiculo: " + idVehiculo);
        System.out.println("Marca de llanta: " + marca);
        System.out.println("Tamanio: " + tamanio + " pulgadas");
        System.out.println("Presion: " + presion + " PSI");
    }
}
