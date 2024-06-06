import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private int id;
    private List<Arista> aristas;

    public Vertice(int id) {
        this.id = id;
        this.aristas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public void agregarArista(Arista arista) {
        if (!aristas.contains(arista)) {  // Aseguramos que no se dupliquen aristas
            aristas.add(arista);
        }
    }

    @Override
    public String toString() {
        return "Vertice{" + "id=" + id + '}';
    }
}
