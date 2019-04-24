package Model;

import java.util.Iterator;

import Interfaces.Colection;
import Interfaces.IVertexBinaryTree;

public class BinaryTreesOrdered<T extends Comparable<T>>
extends BinaryTree<T> {

/* Clase privada para iteradores de árboles binarios ordenados. */
private class Iterador implements Iterator<T> {

    /* Pila para emular la pila de ejecución. */
    private Pila<BinaryTree<T>.Vertex> pila;

    /* Construye un iterador con el vértice recibido. */
    public Iterador() {
        pila = new Pila<BinaryTree<T>.Vertex>();
        if (isEmpty()) {
            return;
        }
        Vertex vi = raiz;
        while(vi != null){
            pila.mete(vi);
            vi = vi.left;
        }
    }

    /* Nos dice si hay un siguiente elemento. */
    @Override public boolean hasNext() {
        return !pila.esVacia();
    }

    /* Regresa el siguiente elemento del árbol en orden. */
    @Override public T next() {
        Vertex v = pila.saca(), vi;
        if (v.RightSon()) {
            vi = v.right;
            while(vi != null){
                pila.mete(vi);
                vi = vi.left;
            }
        }
        return v.element;
        
    }

    /* No lo implementamos: siempre lanza una excepción. */
    @Override public void remove() {
        throw new UnsupportedOperationException();
    }
}

/**
 * Constructor sin parámetros. Para no perder el constructor sin parámetros
 * de {@link BinaryTree}.
 */
public BinaryTreesOrdered() { super(); }

/**
 * Construye un árbol binario ordenado a partir de una colección. El árbol
 * binario ordenado tiene los mismos elementos que la colección recibida.
 * @param coleccion la colección a partir de la cual creamos el árbol
 *        binario ordenado.
 */
public BinaryTreesOrdered(Colection<T> coleccion) {
    super(coleccion);
}

/**
 * Auxiliar de agrega.
 * @param v Vertice donde estamos
 * @param elemento Elemento que se quiere agregar
 */
private void add(Vertex v, T elemento) {
    if (elemento.compareTo(v.element) <= 0) {
        if (!v.LeftSon()) {
            v.left = nuevoVertice(elemento);
            v.left.father = v;
            this.ultimoAgregado = v.left;
            this.elementos++;
            return;
        }
        this.add(v.left, elemento);
    } else {
        if (!v.RightSon()) {
            v.right = nuevoVertice(elemento);
            v.right.father = v;
            this.ultimoAgregado = v.right;
            this.elementos++;
            return;
        }
        this.add(v.right, elemento);
    }
}

/**
 * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
 * @param elemento el elemento a agregar.
 */
@Override public void add(T elemento) {
    if (elemento == null) throw new IllegalArgumentException();
    if (this.isEmpty()) {
        this.raiz = this.ultimoAgregado = nuevoVertice(elemento);
        this.elementos++;
    } else {
        this.add(this.raiz,  elemento);
    }
}

/**
 * Verifica si el vertice recibido es hijo izquierdo de su padre
 * @param v vertice que se verifica.
 * @throws <code> true </code> si lo es. <code> false </code> en otro caso.
 */    
private boolean isLeftSon(Vertex v) {
    if (!v.isFather()) {
        return false;
    }
    return v.father.left == v;
}

/**
 * Verifica si el vertice recibido es hijo derecho de su padre
 * @param v vertice que se verifica.
 * @throws <code> true </code> si lo es. <code> false </code> en otro caso.
 */    
private boolean isRightSon(Vertex v) {
    if (!v.isFather()) {
        return false;
    }
    return v.father.right == v;
}


/**
 * Auxiliar de elimina. Elimina una hoja.
 * @param delete el elemento a eliminar que debe ser hoja.
 */
private void deleteLeave(Vertex delete) {
    if (this.raiz == delete) {
        this.raiz = null;
        this.ultimoAgregado = null;
    } else if (this.isLeftSon(delete)) {
        delete.father.left = null;
    } else {
        delete.father.right = null;
    }
    this.elementos--;
}

/**
 * Auxiliar de elimina. Elimina vertice que no tiene hijo izquierdo.
 * @param delete el elemento a eliminar que debe no tener hijo izquierdo.
 */
private void deleteWithoutLetfSon(Vertex delete) {
    if (this.raiz == delete) {
        this.raiz = this.raiz.right;
        delete.right.father = null;
    } else {
        delete.right.father = delete.father;
        if (this.isLeftSon(delete)) {
            delete.father.left = delete.right;
        } else {
            delete.father.right = delete.right;
        }
    }
    this.elementos--;
}

/**
 * Auxiliar de elimina. Elimina vertice que no tiene hijo derecho.
 * @param delete el elemento a eliminar que debe no tener hijo derecho.
 */
private void deleteWithoutRightSon(Vertex delete) {
    if (this.raiz == delete) {
        this.raiz = this.raiz.left;
        delete.left.father = null;
    } else {
        delete.left.father = delete.father;
        if (this.isLeftSon(delete)) {
            delete.father.left = delete.left;
        } else {
            delete.father.right = delete.left;
        }
    }
    this.elementos--;
}

/**
 * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
 * está varias veces, elimina el primero que encuentre (in-order). El árbol
 * conserva su orden in-order.
 * @param delete es el elemento a eliminar.
 */
@Override public void delete(T delete) {
    Vertex eliminar = this.search(this.raiz, delete), vi;
    if (eliminar == null) {
        return;
    }
    if (eliminar.LeftSon()) {
        vi = eliminar;
        eliminar = MaxSubtree(eliminar.left);
        vi.element = eliminar.element;            
    }

    if (!eliminar.LeftSon() && !eliminar.RightSon()) {
        this.deleteLeave(eliminar);
    } else if (!eliminar.LeftSon()) {
        this.deleteWithoutLetfSon(eliminar);
    } else {
        this.deleteWithoutRightSon(eliminar);
    }
}

/**
 * Busca recursivamente un elemento, a partir del vértice recibido.
 * @param vertex el vértice a partir del cuál comenzar la búsqueda. Puede
 *                ser <code>null</code>.
 * @param element el elemento a buscar a partir del vértice.
 * @return el vértice que contiene el elemento a buscar, si se encuentra en
 *         el árbol; <code>null</code> en otro caso.
 */
@Override protected Vertex search(Vertex vertex, T element) {
    Vertex iz;
    if (vertex == null) {
        return null;
    }
    iz = this.search(vertex.left, element);
    if (iz != null) {
        return iz;
    }
    if ((vertex.element == null && element == null) || vertex.element.equals(element)) {
        return vertex;
    }
    return this.search(vertex.right, element);
}

/**
 * Regresa el vértice máximo en el subárbol cuya raíz es el vértice que
 * recibe.
 * @param vertice el vértice raíz del subárbol del que queremos encontrar el
 *                máximo.
 * @return el vértice máximo el subárbol cuya raíz es el vértice que recibe.
 */
protected Vertex MaxSubtree(Vertex vertice) {
    while(vertice.RightSon()) {
        vertice = vertice.right;
    }
    return vertice;
}

/**
 * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
 * @return un iterador para iterar el árbol.
 */
@Override public Iterator<T> iterator() {
    return new Iterador();
}

/**
 * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
 * tiene hijo izquierdo, el método no hace nada.
 * @param vertex el vértice sobre el que vamos a girar.
 */
public void RightRotate(IVertexBinaryTree<T> vertex) {
    if (vertex == null || !vertex.LeftSon()) {
        return;
    }
    Vertex v = this.vertice(vertex);
    Vertex vi = v.left;
    vi.father = v.father;
    if (v != this.raiz) {
        if (this.isLeftSon(v)) {
            vi.father.left = vi;
        } else {
            vi.father.right = vi;
        }
    } else {
        this.raiz = vi;
    }
    v.left = vi.right;
    if (vi.RightSon()) {
        vi.right.father = v;
    }
    v.father = vi;
    vi.right = v;
}

/**
 * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
 * tiene hijo derecho, el método no hace nada.
 * @param Vertex el vértice sobre el que vamos a girar.
 */
public void LeftRotate(IVertexBinaryTree<T> Vertex) {
    if (Vertex == null || !Vertex.RightSon()) {
        return;
    }
    Vertex v = this.vertice(Vertex);
    Vertex vd = v.right;
    vd.father = v.father;
    if (v != this.raiz) {
        if (this.isLeftSon(v)) {
            vd.father.left = vd;
        } else {
            vd.father.right = vd;
        }
    } else {
        this.raiz = vd;
    }
    v.right = vd.left;
    if (vd.LeftSon()) {
        vd.left.father = v;
    }
    v.father = vd;
    vd.left = v;
}
}