package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * Clase genérica para listas doblemente ligadas.
 * </p>
 *
 * <p>
 * Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.
 * </p>
 *
 * <p>
 * Las listas no aceptan a <code>null</code> como elemento.
 * </p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            // Aquí va su código.
            this.elemento = elemento;
            anterior = null;
            siguiente = null;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            // Aquí va su código.
            anterior = null;
            siguiente = cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override
        public boolean hasNext() {
            // Aquí va su código.
            return siguiente != null;

        }

        /* Nos da el elemento siguiente. */
        @Override
        public T next() {
            // Aquí va su código.
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T e = siguiente.elemento;
            anterior = siguiente;
            siguiente = siguiente.siguiente;
            return e;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override
        public boolean hasPrevious() {
            // Aquí va su código.
            return anterior != null;

        }

        /* Nos da el elemento anterior. */
        @Override
        public T previous() {
            // Aquí va su código.
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            T e = anterior.elemento;
            siguiente = anterior;
            anterior = anterior.anterior;
            return e;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override
        public void start() {
            // Aquí va su código.
            siguiente = cabeza;
            anterior = null;
        }

        /* Mueve el iterador al final de la lista. */
        @Override
        public void end() {
            // Aquí va su código.
            anterior = rabo;
            siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * 
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
        return longitud;

    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * 
     * @return el número elementos en la lista.
     */
    @Override
    public int getElementos() {
        // Aquí va su código.
        return longitud;

    }

    /**
     * Nos dice si la lista es vacía.
     * 
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override
    public boolean esVacia() {
        // Aquí va su código.
        return longitud == 0;
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void agrega(T elemento) {
        // Aquí va su código.
        try {

            if (elemento == null) {
                throw new IllegalArgumentException("El elemento es nulo");
            }
            if (esVacia()) {
                cabeza = new Nodo(elemento);
                rabo = cabeza;
                longitud++;
            } else {
                Nodo nuevo = new Nodo(elemento);
                rabo.siguiente = nuevo;
                nuevo.anterior = rabo;
                rabo = nuevo;
                longitud++;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El elemento es nulo");
        }
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        // Aquí va su código.
        try {

            if (elemento == null) {
                throw new IllegalArgumentException("El elemento es nulo");
            }
            if (esVacia()) {
                cabeza = new Nodo(elemento);
                rabo = cabeza;
                longitud++;
            } else {
                Nodo nuevo = new Nodo(elemento);
                rabo.siguiente = nuevo;
                nuevo.anterior = rabo;
                rabo = nuevo;
                longitud++;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El elemento es nulo");
        }
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        // Aquí va su código.
        try {
            if (elemento == null) {
                throw new IllegalArgumentException();
            }
            if (esVacia()) {
                cabeza = new Nodo(elemento);
                rabo = cabeza;
                longitud++;
            } else {
                Nodo nuevo = new Nodo(elemento);
                cabeza.anterior = nuevo;
                nuevo.siguiente = cabeza;
                cabeza = nuevo;
                longitud++;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * 
     * @param i        el índice dónde insertar el elemento. Si es menor que 0 el
     *                 elemento se agrega al inicio de la lista, y si es mayor o
     *                 igual
     *                 que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        // Aquí va su código.
        try {
            if (elemento == null) {
                throw new IllegalArgumentException();
            }
            if (esVacia()) {
                cabeza = new Nodo(elemento);
                rabo = cabeza;
                longitud++;
            } else if (i <= 0) {
                agregaInicio(elemento);
            } else if (i >= longitud) {
                agregaFinal(elemento);
            } else {
                Nodo aux = cabeza;
                for (int j = 0; j < i; j++) {
                    aux = aux.siguiente;
                }
                Nodo nuevo = new Nodo(elemento);
                nuevo.anterior = aux.anterior;
                nuevo.siguiente = aux;
                aux.anterior.siguiente = nuevo;
                aux.anterior = nuevo;
                longitud++;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * 
     * @param elemento el elemento a eliminar.
     */
    @Override
    public void elimina(T elemento) {
        // Aquí va su código.
        Nodo nodo = buscaNodo(elemento);
        if (nodo != null) {
            if (nodo == cabeza) {
                eliminaPrimero();
            } else if (nodo == rabo) {
                eliminaUltimo();
            } else {
                nodo.anterior.siguiente = nodo.siguiente;
                nodo.siguiente.anterior = nodo.anterior;
                longitud--;
            }
        }
    }

    /*
     * Método auxiliar que busca un nodo en la lista. Si el nodo no está en la
     * lista, regresa <code>null</code>.
     */

    private Nodo buscaNodo(T elemento) {
        // Aquí va su código.
        try {
            Nodo aux = cabeza;
            while (aux != null) {
                if (aux.elemento.equals(elemento)) {
                    return aux;
                }
                aux = aux.siguiente;
            }
            return null;
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * 
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
        try {

            if (esVacia()) {
                throw new ExcepcionIndiceInvalido();
            }
            T elemento = cabeza.elemento;
            if (longitud == 1) {
                cabeza = null;
                rabo = null;
            } else {
                cabeza = cabeza.siguiente;
                cabeza.anterior = null;
            }
            longitud--;
            return elemento;
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * 
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
        try {
            if (esVacia()) {
                throw new ExcepcionIndiceInvalido();
            }
            T elemento = rabo.elemento;
            if (longitud == 1) {
                cabeza = null;
                rabo = null;
            } else {
                rabo = rabo.anterior;
                rabo.siguiente = null;
            }
            longitud--;
            return elemento;
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Nos dice si un elemento está en la lista.
     * 
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene(T elemento) {
        // Aquí va su código.
        Nodo aux = cabeza;
        while (aux != null) {
            if (aux.elemento.equals(elemento)) {
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }

    /**
     * Regresa la reversa de la lista.
     * 
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        // Aquí va su código.
        Lista<T> reversa = new Lista<T>();

        Nodo aux = cabeza;
        while (aux != null) {
            reversa.agregaInicio(aux.elemento);
            aux = aux.siguiente;
        }
        return reversa;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * 
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        // Aquí va su código.
        Lista<T> copia = new Lista<T>();
        Nodo aux = cabeza;
        while (aux != null) {
            copia.agregaFinal(aux.elemento);
            aux = aux.siguiente;
        }
        return copia;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override
    public void limpia() {
        // Aquí va su código.
        try {
            cabeza = null;
            rabo = null;
            longitud = 0;
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Regresa el primer elemento de la lista.
     * 
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        // Aquí va su código.
        try {
            if (esVacia()) {
                throw new ExcepcionIndiceInvalido();
            }
            return cabeza.elemento;
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Regresa el último elemento de la lista.
     * 
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        // Aquí va su código.
        try {
            if (esVacia()) {
                throw new ExcepcionIndiceInvalido();
            }
            return rabo.elemento;
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * 
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *                                 igual que el número de elementos en la lista.
     */
    public T get(int i) {
        // Aquí va su código.
        if (i < 0 || i >= longitud) {
            throw new ExcepcionIndiceInvalido();
        }
        Nodo aux = cabeza;
        for (int j = 0; j < i; j++) {
            aux = aux.siguiente;
        }
        return aux.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * 
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        // Aquí va su código.
        Nodo aux = cabeza;
        int i = 0;
        while (aux != null) {
            if (aux.elemento.equals(elemento)) {
                return i;
            }
            aux = aux.siguiente;
            i++;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * 
     * @return una representación en cadena de la lista.
     */
    @Override
    public String toString() {
        // Aquí va su código.
        if (esVacia()) {
            return "[]";
        }
        String s = "[";
        Nodo aux = cabeza;
        while (aux != null) {
            s += aux.elemento + ", ";
            aux = aux.siguiente;
        }
        return s.substring(0, s.length() - 2) + "]";
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * 
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked")
        Lista<T> lista = (Lista<T>) objeto;
        // Aquí va su código.
        if (longitud != lista.longitud) {
            return false;
        }
        Nodo aux = cabeza;
        Nodo aux2 = lista.cabeza;
        while (aux != null) {
            if (!aux.elemento.equals(aux2.elemento)) {
                return false;
            }
            aux = aux.siguiente;
            aux2 = aux2.siguiente;
        }
        return true;

    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * 
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * 
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
