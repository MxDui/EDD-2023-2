package mx.unam.ciencias.edd;

import java.util.Iterator;

/**
 * <p>
 * Clase para árboles binarios ordenados. Los árboles son genéricos, pero
 * acotados a la interfaz {@link Comparable}.
 * </p>
 *
 * <p>
 * Un árbol instancia de esta clase siempre cumple que:
 * </p>
 * <ul>
 * <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 * descendientes por la izquierda.</li>
 * <li>Cualquier elemento en el árbol es menor o igual que todos sus
 * descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>>
        extends ArbolBinario<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los vértices en DFS in-order. */
        private Pila<Vertice> pila;

        /* Inicializa al iterador. */
        private Iterador() {
            // Aquí va su código.
            pila = new Pila<ArbolBinario<T>.Vertice>();
            if (esVacia()) {
                return;
            }
            Vertice v = raiz;
            while (v != null) {
                pila.mete(v);
                v = v.izquierdo;
            }
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
            // Aquí va su código.
            return !pila.esVacia();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override
        public T next() {
            // Aquí va su código.
            Vertice v = pila.saca();
            Vertice vi;
            if (v.hayDerecho()) {
                vi = v.derecho;
                while (vi != null) {
                    pila.mete(vi);
                    vi = vi.izquierdo;
                }
            }
            return v.elemento;

        }
    }

    /**
     * El vértice del último elemento agegado. Este vértice sólo se puede
     * garantizar que existe <em>inmediatamente</em> después de haber agregado
     * un elemento al árbol. Si cualquier operación distinta a agregar sobre el
     * árbol se ejecuta después de haber agregado un elemento, el estado de esta
     * variable es indefinido.
     */
    protected Vertice ultimoAgregado;

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioOrdenado() {
        super();
    }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * 
     * @param coleccion la colección a partir de la cual creamos el árbol
     *                  binario ordenado.
     */
    public ArbolBinarioOrdenado(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * 
     * @param elemento el elemento a agregar.
     */
    @Override
    public void agrega(T elemento) {
        // Aquí va su código.
        if (elemento == null)
            throw new IllegalArgumentException();
        Vertice v = nuevoVertice(elemento);
        if (raiz == null) {
            raiz = v;
            ultimoAgregado = v;
            elementos++;
            return;
        }
        Vertice aux = raiz;
        while (true) {
            if (aux.elemento.compareTo(elemento) > 0) {
                if (aux.hayIzquierdo())
                    aux = aux.izquierdo;
                else {
                    aux.izquierdo = v;
                    v.padre = aux;
                    ultimoAgregado = v;
                    elementos++;
                    return;
                }
            } else {
                if (aux.hayDerecho())
                    aux = aux.derecho;
                else {
                    aux.derecho = v;
                    v.padre = aux;
                    ultimoAgregado = v;
                    elementos++;
                    return;
                }
            }
        }
    }

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * 
     * @param elemento el elemento a eliminar.
     */
    @Override
    public void elimina(T elemento) {
        // Aquí va su código.
        Vertice v = (ArbolBinario<T>.Vertice) busca(elemento);
        if (v == null)
            return;
        if (v.hayIzquierdo() && v.hayDerecho()) {
            Vertice aux = intercambiaEliminable(v);
            eliminaVertice(aux);
        } else
            eliminaVertice(v);
        elementos--;

    }

    /**
     * Intercambia el elemento de un vértice con dos hijos distintos de
     * <code>null</code> con el elemento de un descendiente que tenga a lo más
     * un hijo.
     * 
     * @param vertice un vértice con dos hijos distintos de <code>null</code>.
     * @return el vértice descendiente con el que vértice recibido se
     *         intercambió. El vértice regresado tiene a lo más un hijo distinto
     *         de <code>null</code>.
     */
    protected Vertice intercambiaEliminable(Vertice vertice) {
        // Aquí va su código.
        Vertice aux = vertice.derecho;
        while (aux.hayIzquierdo())
            aux = aux.izquierdo;
        T e = vertice.elemento;
        vertice.elemento = aux.elemento;
        aux.elemento = e;
        return aux;

    }

    /**
     * Elimina un vértice que a lo más tiene un hijo distinto de
     * <code>null</code> subiendo ese hijo (si existe).
     * 
     * @param vertice el vértice a eliminar; debe tener a lo más un hijo
     *                distinto de <code>null</code>.
     */
    protected void eliminaVertice(Vertice vertice) {
        // Aquí va su código.
        if (vertice.hayIzquierdo()) {
            if (vertice.padre == null) {
                raiz = vertice.izquierdo;
                raiz.padre = null;
            } else {
                if (vertice.padre.izquierdo == vertice)
                    vertice.padre.izquierdo = vertice.izquierdo;
                else
                    vertice.padre.derecho = vertice.izquierdo;
                vertice.izquierdo.padre = vertice.padre;
            }
        } else if (vertice.hayDerecho()) {
            if (vertice.padre == null) {
                raiz = vertice.derecho;
                raiz.padre = null;
            } else {
                if (vertice.padre.izquierdo == vertice)
                    vertice.padre.izquierdo = vertice.derecho;
                else
                    vertice.padre.derecho = vertice.derecho;
                vertice.derecho.padre = vertice.padre;
            }
        } else {
            if (vertice.padre == null)
                raiz = null;
            else {
                if (vertice.padre.izquierdo == vertice)
                    vertice.padre.izquierdo = null;
                else
                    vertice.padre.derecho = null;
            }
        }
    }

    /**
     * Busca un elemento en el árbol recorriéndolo in-order. Si lo encuentra,
     * regresa el vértice que lo contiene; si no, regresa <code>null</code>.
     * 
     * @param elemento el elemento a buscar.
     * @return un vértice que contiene al elemento buscado si lo
     *         encuentra; <code>null</code> en otro caso.
     */
    @Override
    public VerticeArbolBinario<T> busca(T elemento) {
        // Aquí va su código.
        return busca(raiz, elemento);
    }

    /*
     * Método auxiliar recursivo para buscar un elemento en el árbol.
     */

    private Vertice busca(Vertice v, T elemento) {
        if (v == null)
            return null;
        if (v.elemento.equals(elemento))
            return v;
        Vertice aux = busca(v.izquierdo, elemento);
        if (aux != null)
            return aux;
        return busca(v.derecho, elemento);
    }

    /**
     * Regresa el vértice que contiene el último elemento agregado al
     * árbol. Este método sólo se puede garantizar que funcione
     * <em>inmediatamente</em> después de haber invocado al método {@link
     * agrega}. Si cualquier operación distinta a agregar sobre el árbol se
     * ejecuta después de haber agregado un elemento, el comportamiento de este
     * método es indefinido.
     * 
     * @return el vértice que contiene el último elemento agregado al árbol, si
     *         el método es invocado inmediatamente después de agregar un
     *         elemento al árbol.
     */
    public VerticeArbolBinario<T> getUltimoVerticeAgregado() {
        return ultimoAgregado;
    }

    /**
     * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
     * tiene hijo izquierdo, el método no hace nada.
     * 
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
        // Aquí va su código.

        if (vertice.hayIzquierdo()) {
            Vertice v = vertice(vertice);
            Vertice izq = v.izquierdo;
            if (v.padre == null) {
                raiz = izq;
                raiz.padre = null;
            } else {
                if (v.padre.izquierdo == v)
                    v.padre.izquierdo = izq;
                else
                    v.padre.derecho = izq;
                izq.padre = v.padre;
            }
            v.padre = izq;
            v.izquierdo = izq.derecho;
            if (izq.hayDerecho())
                izq.derecho.padre = v;
            izq.derecho = v;
        }

    }

    /**
     * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
     * tiene hijo derecho, el método no hace nada.
     * 
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        // Aquí va su código.
        if (vertice.hayDerecho()) {
            Vertice v = vertice(vertice);
            Vertice der = v.derecho;
            if (v.padre == null) {
                raiz = der;
                raiz.padre = null;
            } else {
                if (v.padre.izquierdo == v)
                    v.padre.izquierdo = der;
                else
                    v.padre.derecho = der;
                der.padre = v.padre;
            }
            v.padre = der;
            v.derecho = der.izquierdo;
            if (der.hayIzquierdo())
                der.izquierdo.padre = v;
            der.izquierdo = v;
        }
    }

    /**
     * Realiza un recorrido DFS <em>pre-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * 
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPreOrder(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
        dfsPreOrder(raiz, accion);
    }

    private void dfsPreOrder(Vertice v, AccionVerticeArbolBinario<T> accion) {
        if (v == null)
            return;
        accion.actua(v);
        dfsPreOrder(v.izquierdo, accion);
        dfsPreOrder(v.derecho, accion);
    }

    /**
     * Realiza un recorrido DFS <em>in-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * 
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsInOrder(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
        dfsInOrder(raiz, accion);
    }

    private void dfsInOrder(Vertice v, AccionVerticeArbolBinario<T> accion) {
        if (v == null)
            return;
        dfsInOrder(v.izquierdo, accion);
        accion.actua(v);
        dfsInOrder(v.derecho, accion);
    }

    /**
     * Realiza un recorrido DFS <em>post-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * 
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPostOrder(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
        dfsPostOrder(raiz, accion);
    }

    private void dfsPostOrder(Vertice v, AccionVerticeArbolBinario<T> accion) {
        if (v == null)
            return;
        dfsPostOrder(v.izquierdo, accion);
        dfsPostOrder(v.derecho, accion);
        accion.actua(v);
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * 
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }
}
