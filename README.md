# Algoritmo-Pim-Bosque

Este es una implementación del algoritmo de Prim para encontrar el bosque generador de peso mínimo.

## Uso

1. **Compilación del código:** Para compilar el código, ejecuta el siguiente comando en la terminal:

   ```
   javac Grafica.java
   ```

2. **Ejecución del programa:** Una vez que el código ha sido compilado, puedes ejecutar el programa proporcionando el archivo de grafo como argumento de línea de comandos. El formato del archivo de grafo debe seguir el siguiente patrón:
   
   ```
   <lista_de_vertices>
   <lista_de_aristas>
   ```

   Por ejemplo:
   
   ```
   1, 2, 3, 4, 5, 6
   1, 2: 1
   1, 3: 2
   2, 3: 3
   4, 5: 4
   4, 6: 5
   5, 6: 6
   ```

   Para ejecutar el programa, usa el siguiente comando:

   ```
   java Grafica grafo.txt
   ```

## Implementación

La clase `Grafica` representa el grafo y contiene los métodos necesarios para cargar un grafo desde un archivo, aplicar el algoritmo de Prim para encontrar el bosque generador de peso mínimo y mostrar el resultado. 

El algoritmo de Prim se implementa en el método `prim()`, que devuelve una lista de bosques, donde cada bosque es un conjunto de aristas que representan un árbol en el bosque generador de peso mínimo.

## Autor

- Oscar David Hernández Rodríguez
- No. Cuenta: 42002945
