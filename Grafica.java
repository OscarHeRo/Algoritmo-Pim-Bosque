import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grafica {
    private Map<Integer, Vertice> vertices;

    public Grafica() {
        this.vertices = new HashMap<>();
    }

    public void agregarVertice(int id) {
        if (!vertices.containsKey(id)) {
            vertices.put(id, new Vertice(id));
        }
    }

    public void agregarArista(int origen, int destino, int peso) {
        if (!vertices.containsKey(origen)) {
            agregarVertice(origen);
        }
        if (!vertices.containsKey(destino)) {
            agregarVertice(destino);
        }
        Arista arista = new Arista(vertices.get(origen), vertices.get(destino), peso);
        vertices.get(origen).agregarArista(arista);
        vertices.get(destino).agregarArista(arista); // Aseguramos la arista bidireccional
    }

    public void cargarDesdeArchivo(String rutaArchivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea = reader.readLine();
            if (linea != null) {
                String[] idVertices = linea.split(",");
                for (String idVertice : idVertices) {
                    agregarVertice(Integer.parseInt(idVertice.trim()));
                }

                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(":");
                    String[] verticesArista = partes[0].split(",");
                    int origen = Integer.parseInt(verticesArista[0].trim());
                    int destino = Integer.parseInt(verticesArista[1].trim());
                    int peso = Integer.parseInt(partes[1].trim());
                    agregarArista(origen, destino, peso);
                }
            }
        }
    }

    /**
 * Implementa el algoritmo de Prim para encontrar el bosque generador de peso mínimo de este grafo.
 * 
 * El algoritmo de Prim es un algoritmo greedy que encuentra el árbol generador de peso mínimo para un grafo conectado y ponderado.
 * Este algoritmo comienza desde un vértice arbitrario y se expande al vértice adyacente con la arista de menor peso.
 * Si el grafo no es completamente conectado, este método encontrará el bosque generador de peso mínimo, que es un conjunto de árboles generadores de peso mínimo para cada componente conectado del grafo.
 * 
 * Este método devuelve una lista de bosques, donde cada bosque es un conjunto de aristas que representan un árbol en el bosque generador de peso mínimo.
 * 
 * @return Una lista de bosques que representan el bosque generador de peso mínimo de este grafo.
 */
public List<Bosque> prim() {
    // Inicializar una lista para almacenar los bosques generadores de peso mínimo
    List<Bosque> bosques = new ArrayList<>();
    
    // Conjunto para mantener un registro de los vértices visitados durante el proceso
    Set<Integer> verticesVisitados = new HashSet<>();
    
    // Cola de prioridad para seleccionar las aristas con el menor peso
    PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(Arista::getPeso));

    // Iterar sobre todos los vértices del grafo
    for (Vertice vertice : vertices.values()) {
        // Si el vértice actual no ha sido visitado, comenzar un nuevo bosque
        if (!verticesVisitados.contains(vertice.getId())) {
            // Crear un nuevo bosque
            Bosque bosque = new Bosque();
            
            // Lista para almacenar las aristas del árbol actual
            List<Arista> arbolActual = new ArrayList<>();
            
            // Agregar todas las aristas del vértice actual a la cola de prioridad
            colaPrioridad.addAll(vertice.getAristas());
            
            // Marcar el vértice actual como visitado
            verticesVisitados.add(vertice.getId());

            // Mientras la cola de prioridad no esté vacía
            while (!colaPrioridad.isEmpty()) {
                // Extraer la arista con el peso mínimo de la cola de prioridad
                Arista arista = colaPrioridad.poll();
                
                // Obtener los vértices de origen y destino de la arista
                Vertice origen = arista.getOrigen();
                Vertice destino = arista.getDestino();

                // Verificar si ambos vértices ya han sido visitados
                if (verticesVisitados.contains(origen.getId()) && verticesVisitados.contains(destino.getId())) {
                    // Si ambos vértices ya han sido visitados, continuar con la siguiente iteración
                    continue;
                }

                // Agregar los vértices al conjunto de visitados y la arista al árbol actual
                verticesVisitados.add(origen.getId());
                verticesVisitados.add(destino.getId());
                arbolActual.add(arista);

                // Agregar las aristas adyacentes al vértice destino a la cola de prioridad
                colaPrioridad.addAll(destino.getAristas());
            }

            // Agregar el árbol actual al bosque de árboles generadores de peso mínimo
            bosque.agregarArbol(arbolActual);
            bosques.add(bosque);
        }
    }

    // Devolver la lista de bosques generadores de peso mínimo
    return bosques;
}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Vertice vertice : vertices.values()) {
            builder.append(vertice).append(" -> ").append(vertice.getAristas()).append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Grafica <ruta_archivo>");
            return;
        }
    
        String rutaArchivo = args[0];
        Grafica grafica = new Grafica();
        try {
            grafica.cargarDesdeArchivo(rutaArchivo);
            System.out.println(grafica);
            List<Bosque> bosques = grafica.prim();
            System.out.println("Bosques Generadores de Peso Minimo:");
            for (int i = 0; i < bosques.size(); i++) {
                System.out.println("Arbol " + (i + 1) + ":");
                System.out.println(bosques.get(i));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
}
