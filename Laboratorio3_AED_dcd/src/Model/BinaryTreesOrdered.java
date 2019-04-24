package Model;

import java.util.Iterator;

import Interfaces.Coleccion;
import Interfaces.VerticeArbolBinario;

public class BinaryTreesOrdered<T extends Comparable<T>>
extends ArbolBinario<T> {

/* Clase privada para iteradores de �rboles binarios ordenados. */
private class Iterador implements Iterator<T> {

    /* Pila para emular la pila de ejecuci�n. */
    private Pila<ArbolBinario<T>.Vertice> pila;

    /* Construye un iterador con el v�rtice recibido. */
    public Iterador() {
        pila = new Pila<ArbolBinario<T>.Vertice>();
        if (esVacio()) {
            return;
        }
        Vertice vi = raiz;
        while(vi != null){
            pila.mete(vi);
            vi = vi.izquierdo;
        }
    }

    /* Nos dice si hay un siguiente elemento. */
    @Override public boolean hasNext() {
        return !pila.esVacia();
    }

    /* Regresa el siguiente elemento del �rbol en orden. */
    @Override public T next() {
        Vertice v = pila.saca(), vi;
        if (v.hayDerecho()) {
            vi = v.derecho;
            while(vi != null){
                pila.mete(vi);
                vi = vi.izquierdo;
            }
        }
        return v.elemento;
        
    }

    /* No lo implementamos: siempre lanza una excepci�n. */
    @Override public void remove() {
        throw new UnsupportedOperationException();
    }
}

/**
 * Constructor sin par�metros. Para no perder el constructor sin par�metros
 * de {@link ArbolBinario}.
 */
public BinaryTreesOrdered() { super(); }

/**
 * Construye un �rbol binario ordenado a partir de una colecci�n. El �rbol
 * binario ordenado tiene los mismos elementos que la colecci�n recibida.
 * @param coleccion la colecci�n a partir de la cual creamos el �rbol
 *        binario ordenado.
 */
public BinaryTreesOrdered(Coleccion<T> coleccion) {
    super(coleccion);
}

/**
 * Auxiliar de agrega.
 * @param v Vertice donde estamos
 * @param elemento Elemento que se quiere agregar
 */
private void agrega(Vertice v, T elemento) {
    if (elemento.compareTo(v.elemento) <= 0) {
        if (!v.hayIzquierdo()) {
            v.izquierdo = nuevoVertice(elemento);
            v.izquierdo.padre = v;
            this.ultimoAgregado = v.izquierdo;
            this.elementos++;
            return;
        }
        this.agrega(v.izquierdo, elemento);
    } else {
        if (!v.hayDerecho()) {
            v.derecho = nuevoVertice(elemento);
            v.derecho.padre = v;
            this.ultimoAgregado = v.derecho;
            this.elementos++;
            return;
        }
        this.agrega(v.derecho, elemento);
    }
}

/**
 * Agrega un nuevo elemento al �rbol. El �rbol conserva su orden in-order.
 * @param elemento el elemento a agregar.
 */
@Override public void agrega(T elemento) {
    if (elemento == null) throw new IllegalArgumentException();
    if (this.esVacio()) {
        this.raiz = this.ultimoAgregado = nuevoVertice(elemento);
        this.elementos++;
    } else {
        this.agrega(this.raiz,  elemento);
    }
}

/**
 * Verifica si el vertice recibido es hijo izquierdo de su padre
 * @param v vertice que se verifica.
 * @throws <code> true </code> si lo es. <code> false </code> en otro caso.
 */    
private boolean esHijoIzquierdo(Vertice v) {
    if (!v.hayPadre()) {
        return false;
    }
    return v.padre.izquierdo == v;
}

/**
 * Verifica si el vertice recibido es hijo derecho de su padre
 * @param v vertice que se verifica.
 * @throws <code> true </code> si lo es. <code> false </code> en otro caso.
 */    
private boolean esHijoDerecho(Vertice v) {
    if (!v.hayPadre()) {
        return false;
    }
    return v.padre.derecho == v;
}


/**
 * Auxiliar de elimina. Elimina una hoja.
 * @param eliminar el elemento a eliminar que debe ser hoja.
 */
private void eliminaHoja(Vertice eliminar) {
    if (this.raiz == eliminar) {
        this.raiz = null;
        this.ultimoAgregado = null;
    } else if (this.esHijoIzquierdo(eliminar)) {
        eliminar.padre.izquierdo = null;
    } else {
        eliminar.padre.derecho = null;
    }
    this.elementos--;
}

/**
 * Auxiliar de elimina. Elimina vertice que no tiene hijo izquierdo.
 * @param eliminar el elemento a eliminar que debe no tener hijo izquierdo.
 */
private void eliminaSinHijoIzquierdo(Vertice eliminar) {
    if (this.raiz == eliminar) {
        this.raiz = this.raiz.derecho;
        eliminar.derecho.padre = null;
    } else {
        eliminar.derecho.padre = eliminar.padre;
        if (this.esHijoIzquierdo(eliminar)) {
            eliminar.padre.izquierdo = eliminar.derecho;
        } else {
            eliminar.padre.derecho = eliminar.derecho;
        }
    }
    this.elementos--;
}

/**
 * Auxiliar de elimina. Elimina vertice que no tiene hijo derecho.
 * @param eliminar el elemento a eliminar que debe no tener hijo derecho.
 */
private void eliminaSinHijoDerecho(Vertice eliminar) {
    if (this.raiz == eliminar) {
        this.raiz = this.raiz.izquierdo;
        eliminar.izquierdo.padre = null;
    } else {
        eliminar.izquierdo.padre = eliminar.padre;
        if (this.esHijoIzquierdo(eliminar)) {
            eliminar.padre.izquierdo = eliminar.izquierdo;
        } else {
            eliminar.padre.derecho = eliminar.izquierdo;
        }
    }
    this.elementos--;
}

/**
 * Elimina un elemento. Si el elemento no est� en el �rbol, no hace nada; si
 * est� varias veces, elimina el primero que encuentre (in-order). El �rbol
 * conserva su orden in-order.
 * @param elemento el elemento a eliminar.
 */
@Override public void elimina(T elemento) {
    Vertice eliminar = this.busca(this.raiz, elemento), vi;
    if (eliminar == null) {
        return;
    }
    if (eliminar.hayIzquierdo()) {
        vi = eliminar;
        eliminar = maximoEnSubarbol(eliminar.izquierdo);
        vi.elemento = eliminar.elemento;            
    }

    if (!eliminar.hayIzquierdo() && !eliminar.hayDerecho()) {
        this.eliminaHoja(eliminar);
    } else if (!eliminar.hayIzquierdo()) {
        this.eliminaSinHijoIzquierdo(eliminar);
    } else {
        this.eliminaSinHijoDerecho(eliminar);
    }
}

/**
 * Busca recursivamente un elemento, a partir del v�rtice recibido.
 * @param vertice el v�rtice a partir del cu�l comenzar la b�squeda. Puede
 *                ser <code>null</code>.
 * @param elemento el elemento a buscar a partir del v�rtice.
 * @return el v�rtice que contiene el elemento a buscar, si se encuentra en
 *         el �rbol; <code>null</code> en otro caso.
 */
@Override protected Vertice busca(Vertice vertice, T elemento) {
    Vertice iz;
    if (vertice == null) {
        return null;
    }
    iz = this.busca(vertice.izquierdo, elemento);
    if (iz != null) {
        return iz;
    }
    if ((vertice.elemento == null && elemento == null) || vertice.elemento.equals(elemento)) {
        return vertice;
    }
    return this.busca(vertice.derecho, elemento);
}

/**
 * Regresa el v�rtice m�ximo en el sub�rbol cuya ra�z es el v�rtice que
 * recibe.
 * @param vertice el v�rtice ra�z del sub�rbol del que queremos encontrar el
 *                m�ximo.
 * @return el v�rtice m�ximo el sub�rbol cuya ra�z es el v�rtice que recibe.
 */
protected Vertice maximoEnSubarbol(Vertice vertice) {
    while(vertice.hayDerecho()) {
        vertice = vertice.derecho;
    }
    return vertice;
}

/**
 * Regresa un iterador para iterar el �rbol. El �rbol se itera en orden.
 * @return un iterador para iterar el �rbol.
 */
@Override public Iterator<T> iterator() {
    return new Iterador();
}

/**
 * Gira el �rbol a la derecha sobre el v�rtice recibido. Si el v�rtice no
 * tiene hijo izquierdo, el m�todo no hace nada.
 * @param vertice el v�rtice sobre el que vamos a girar.
 */
public void giraDerecha(VerticeArbolBinario<T> vertice) {
    if (vertice == null || !vertice.hayIzquierdo()) {
        return;
    }
    Vertice v = this.vertice(vertice);
    Vertice vi = v.izquierdo;
    vi.padre = v.padre;
    if (v != this.raiz) {
        if (this.esHijoIzquierdo(v)) {
            vi.padre.izquierdo = vi;
        } else {
            vi.padre.derecho = vi;
        }
    } else {
        this.raiz = vi;
    }
    v.izquierdo = vi.derecho;
    if (vi.hayDerecho()) {
        vi.derecho.padre = v;
    }
    v.padre = vi;
    vi.derecho = v;
}

/**
 * Gira el �rbol a la izquierda sobre el v�rtice recibido. Si el v�rtice no
 * tiene hijo derecho, el m�todo no hace nada.
 * @param vertice el v�rtice sobre el que vamos a girar.
 */
public void giraIzquierda(VerticeArbolBinario<T> vertice) {
    if (vertice == null || !vertice.hayDerecho()) {
        return;
    }
    Vertice v = this.vertice(vertice);
    Vertice vd = v.derecho;
    vd.padre = v.padre;
    if (v != this.raiz) {
        if (this.esHijoIzquierdo(v)) {
            vd.padre.izquierdo = vd;
        } else {
            vd.padre.derecho = vd;
        }
    } else {
        this.raiz = vd;
    }
    v.derecho = vd.izquierdo;
    if (vd.hayIzquierdo()) {
        vd.izquierdo.padre = v;
    }
    v.padre = vd;
    vd.izquierdo = v;
}
}