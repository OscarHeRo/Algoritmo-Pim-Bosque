import java.util.Objects;

public class Arista {
    private Vertice origen;
    private Vertice destino;
    private int peso;

    public Arista(Vertice origen, Vertice destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice getOrigen() {
        return origen;
    }

    public Vertice getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arista arista = (Arista) o;
        return peso == arista.peso &&
                Objects.equals(origen, arista.origen) &&
                Objects.equals(destino, arista.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origen, destino, peso);
    }

    @Override
    public String toString() {
        return "(" + origen.getId() + " - " + destino.getId() + " : " + peso + ")";
    }
}
