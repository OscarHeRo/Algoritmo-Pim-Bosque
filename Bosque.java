import java.util.ArrayList;
import java.util.List;

public class Bosque {
    private List<List<Arista>> arboles;

    public Bosque() {
        this.arboles = new ArrayList<>();
    }

    public void agregarArbol(List<Arista> arbol) {
        arboles.add(arbol);
    }

    @Override
public String toString() {
    StringBuilder builder = new StringBuilder();
    int totalArboles = arboles.size();
    for (int i = 0; i < totalArboles; i++) {
        for (Arista arista : arboles.get(i)) {
            builder.append(arista).append("\n");
        }
    }
    return builder.toString();
}

}
