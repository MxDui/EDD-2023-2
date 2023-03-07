package mx.unam.ciencias.edd.proyecto1;

/**
 * Clase para colas genéricas.
 */
public class Cola<T> extends MeteSaca<T> {

    /**
     * Regresa una representación en cadena de la cola.
     * 
     * @return una representación en cadena de la cola.
     */
    @Override
    public String toString() {
        // Aquí va su código.
        String s = "";
        MeteSaca<T> cola = new Cola<>();
        while (!esVacia()) {
            T elemento = saca();
            s += String.valueOf(elemento) + ",";
            cola.mete(elemento);
        }
        while (!cola.esVacia()) {
            mete(cola.saca());
        }
        return s;

    }

    /**
     * Agrega un elemento al final de la cola.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void mete(T elemento) {
        // Aquí va su código.
        if (elemento == null)
            throw new IllegalArgumentException();
        Nodo nodo = new Nodo(elemento);
        if (cabeza == null) {
            cabeza = nodo;
            rabo = nodo;
        } else {
            rabo.siguiente = nodo;
            rabo = nodo;
        }
    }
}
