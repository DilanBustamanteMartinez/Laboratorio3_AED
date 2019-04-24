package Model;
import java.util.NoSuchElementException;

import Interfaces.Coleccion;
import Interfaces.IVertexBinaryTree;
public abstract class BinaryTree<T> implements Coleccion<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class Vertex implements IVertexBinaryTree<T> {

        /** El elemento del vértice. */
        public T element;
        /** El padre del vértice. */
        public Vertex father;
        /** El izquierdo del vértice. */
        public Vertex left;
        /** El derecho del vértice. */
        public Vertex right;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public Vertex(T element) {
            this.element = element;
        }

        /**
         * Regresa una representación en cadena del vértice.
         * @return una representación en cadena del vértice.
         */
        public String toString() {
            return this.element.toString();
        }

        /**
         * Nos dice si el vértice tiene un padre.
         * @return <tt>true</tt> si el vértice tiene padre,
         *         <tt>false</tt> en otro caso.
         */
        @Override public boolean isFather() {
            return this.father != null;
        }

        /**
         * Nos dice si el vértice tiene un izquierdo.
         * @return <tt>true</tt> si el vértice tiene izquierdo,
         *         <tt>false</tt> en otro caso.
         */
        @Override public boolean LeftSon() {
            return this.left != null;
        }

        /**
         * Nos dice si el vértice tiene un derecho.
         * @return <tt>true</tt> si el vértice tiene derecho,
         *         <tt>false</tt> en otro caso.
         */
        @Override public boolean RightSon() {
            return this.right != null;
        }

        /**
         * Regresa el padre del vértice.
         * @return el padre del vértice.
         * @throws NoSuchElementException si el vértice no tiene padre.
         */
        @Override public IVertexBinaryTree<T> getFather() {
            if (!this.isFather()) {
                throw new NoSuchElementException();
            }
            return this.father;
        }

        /**
         * Regresa el izquierdo del vértice.
         * @return el izquierdo del vértice.
         * @throws NoSuchElementException si el vértice no tiene izquierdo.
         */
        @Override public IVertexBinaryTree<T> getLeft() {
            if (!this.LeftSon()) {
                throw new NoSuchElementException();
            }
            return this.left;
        }

        /**
         * Regresa el derecho del vértice.
         * @return el derecho del vértice.
         * @throws NoSuchElementException si el vértice no tiene derecho.
         */
        @Override public IVertexBinaryTree<T> getRight() {
            if (!this.RightSon()) {
                throw new NoSuchElementException();
            }
            return this.right;
        }

        /**
         * Regresa el elemento al que apunta el vértice.
         * @return el elemento al que apunta el vértice.
         */
        @Override public T get() {
            return this.element;
        }

        /**
         * Auxiliar de equals. Compara vertice por vertice.
         * @param v1 Vertice de arbol 1
         * @param v2 Vertice de arbol 2
         * @return <code>true</code> si arbol 1 y arbol 2
         *         son iguales; <code>false</code> en otro caso.
         */
        private boolean equals(Vertex v1, Vertex v2) {
            if (v1 == null && v2 == null) {
                return true;
            }
            if ((v1 == null && v2 != null) || (v1 != null && v2 == null) || !v1.element.equals(v2.element)) {
                return false;
            }
            return equals(v1.left, v2.left) && equals(v1.right, v2.right);
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>. Las clases que extiendan {@link Vertex} deben
         * sobrecargar el método {@link Vertex#equals}.
         * @param o el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link Vertex}, su elemento es igual al elemento de éste
         *         vértice, y los descendientes de ambos son recursivamente
         *         iguales; <code>false</code> en otro caso.
         */
        @Override public boolean equals(Object o) {
            if (o == null)
                return false;
            if (getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked") Vertex vertice = (Vertex)o;
            return equals(this, vertice);
        }
    }

    /** La raíz del árbol. */
    protected Vertex raiz;
    /** El número de elementos */
    protected int elementos;
    /** El vértice del último elemento agegado. */
    protected Vertex ultimoAgregado;

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros.
     */
    public BinaryTree() {}

    /**
     * Construye un árbol binario a partir de una colección. El árbol binario
     *  tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario.
     */
    public BinaryTree(Coleccion<T> coleccion) {
        for(T e:coleccion) {
            this.agrega(e);
        }
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link Vertex}. Para
     * crear vértices se debe utilizar este método en lugar del operador
     * <code>new</code>, para que las clases herederas de ésta puedan
     * sobrecargarlo y permitir que cada estructura de árbol binario utilice
     * distintos tipos de vértices.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice con el elemento recibido dentro del mismo.
     */
    protected Vertex nuevoVertice(T elemento) {
        return new Vertex(elemento);
    }

    private int profundidad(Vertex v) {
        if (v == null) {
            return -1;
        }
        return 1 + Math.max(this.profundidad(v.left), this.profundidad(v.right));
    }

    /**
     * Regresa la profundidad del árbol. La profundidad de un árbol es la
     * longitud de la ruta más larga entre la raíz y una hoja.
     * @return la profundidad del árbol.
     */
    public int profundidad() {
        return profundidad(this.raiz);
    }

    /**
     * Regresa el número de elementos que se han agregado al árbol.
     * @return el número de elementos en el árbol.
     */
    public int getElementos() {
        return this.elementos;
    }

    /**
     * Regresa el vértice que contiene el último elemento agregado al árbol.
     * @return el vértice que contiene el último elemento agregado al árbol.
     */
    public IVertexBinaryTree<T> getUltimoVerticeAgregado() {
        return this.ultimoAgregado;
    }

    /**
     * Auxiliar de contiene.
     * @param elemento el elemento que queremos comprobar si está en el árbol.
     * @param v el vertice donde estamos parados actualmente
     * @return <code>true</code> si el elemento está en el árbol;
     *         <code>false</code> en otro caso.
     */
    private boolean contiene(T elemento, Vertex v){
        if (v == null) {
            return false;
        }
        if (v.element.equals(elemento)) {
            return true;
        }
        return contiene(elemento, v.left) || contiene(elemento, v.right);
    }

    /**
     * Nos dice si un elemento está en el árbol binario.
     * @param elemento el elemento que queremos comprobar si está en el árbol.
     * @return <code>true</code> si el elemento está en el árbol;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        return contiene(elemento, this.raiz);
    }

    /**
     * Busca un elemento en el árbol. Si lo encuentra, regresa el vértice que lo
     * contiene; si no, regresa <tt>null</tt>.
     * @param elemento el elemento a buscar.
     * @return un vértice que contiene el elemento buscado si lo encuentra;
     *         <tt>null</tt> en otro caso.
     */
    public IVertexBinaryTree<T> busca(T elemento) {
        /* Busca recursivamente. */
        return busca(raiz, elemento);
    }

    /**
     * Busca recursivamente un elemento, a partir del vértice recibido.
     * @param vertice el vértice a partir del cuál comenzar la búsqueda. Puede
     *                ser <code>null</code>.
     * @param elemento el elemento a buscar a partir del vértice.
     * @return el vértice que contiene el elemento a buscar, si se encuentra en
     *         el árbol; <code>null</code> en otro caso.
     */
    protected Vertex busca(Vertex vertice, T elemento) {
        if (vertice == null) {
            return null;
        }
        if (vertice.element.equals(elemento)) {
            return vertice;
        }
        Vertex iz = busca(vertice.left, elemento);
        return ((iz != null) ? iz : busca(vertice.right, elemento));
    }

    /**
     * Regresa el vértice que contiene la raíz del árbol.
     * @return el vértice que contiene la raíz del árbol.
     * @throws NoSuchElementException si el árbol es vacío.
     */
    public IVertexBinaryTree<T> raiz() {
        if (this.esVacio()) {
            throw new NoSuchElementException();
        }
        return this.raiz;
    }

    /**
     * Regresa el número de elementos en el árbol.
     * @return el número de elementos en el árbol.
     */
    @Override public boolean esVacio() {
        return this.raiz == null;
    }

    /**
     * Compara el árbol con un objeto.
     * @param o el objeto con el que queremos comparar el árbol.
     * @return <code>true</code> si el objeto recibido es un árbol binario y los
     *         árboles son iguales; <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") BinaryTree<T> arbol = (BinaryTree<T>)o;
        if (this.esVacio()) {
            return arbol.esVacio();
        }
        return this.raiz.equals(arbol.raiz);
    }

    /**
     * Regresa una representación en cadena del árbol.
     * @return una representación en cadena del árbol.
     */
    @Override public String toString() {
        if (elementos == 0)
            return "";
        /* Necesitamos la profundidad para saber cuántas ramas puede haber. */
        int p = profundidad() + 1;
        /* true == dibuja rama, false == dibuja espacio. */
        boolean[] rama = new boolean[p];
        for (int i = 0; i < p; i++)
            /* Al inicio, no dibujamos ninguna rama. */
            rama[i] = false;
        String s = aCadena(raiz, 0, rama);
        return s.substring(0, s.length()-1);
    }

    /* Método auxiliar recursivo que hace todo el trabajo. */
    private String aCadena(Vertex vertice, int nivel, boolean[] rama) {
        /* Primero que nada agregamos el vertice a la cadena. */
        String s = vertice + "\n";
        /* A partir de aquí, dibujamos rama en este nivel. */
        rama[nivel] = true;
        if (vertice.left != null && vertice.right != null) {
            /* Si hay vertice izquierdo Y derecho, dibujamos ramas o
             * espacios. */
            s += espacios(nivel, rama);
            /* Dibujamos el conector al hijo izquierdo. */
            s += "├─›";
            /* Recursivamente dibujamos el hijo izquierdo y sus
               descendientes. */
            s += aCadena(vertice.left, nivel+1, rama);
            /* Dibujamos ramas o espacios. */
            s += espacios(nivel, rama);
            /* Dibujamos el conector al hijo derecho. */
            s += "└─»";
            /* Como ya dibujamos el último hijo, ya no hay rama en este
               nivel. */
            rama[nivel] = false;
            /* Recursivamente dibujamos el hijo derecho y sus descendientes. */
            s += aCadena(vertice.right, nivel+1, rama);
        } else if (vertice.left != null) {
            /* Dibujamos ramas o espacios. */
            s += espacios(nivel, rama);
            /* Dibujamos el conector al hijo izquierdo. */
            s += "└─›";
            /* Como ya dibujamos el último hijo, ya no hay rama en este
               nivel. */
            rama[nivel] = false;
            /* Recursivamente dibujamos el hijo izquierdo y sus
               descendientes. */
            s += aCadena(vertice.left, nivel+1, rama);
        } else if (vertice.right != null) {
            /* Dibujamos ramas o espacios. */
            s += espacios(nivel, rama);
            /* Dibujamos el conector al hijo derecho. */
            s += "└─»";
            /* Como ya dibujamos el último hijo, ya no hay rama en este
               nivel. */
            rama[nivel] = false;
            /* Recursivamente dibujamos el hijo derecho y sus descendientes. */
            s += aCadena(vertice.right, nivel+1, rama);
        }
        return s;
    }

    /* Dibuja los espacios (incluidas las ramas, de ser necesarias) que van
       antes de un vértice. */
    private String espacios(int n, boolean[] rama) {
        String s = "";
        for (int i = 0; i < n; i++)
            if (rama[i])
                /* Rama: dibújala. */
                s += "│  ";
            else
                /* No rama: dibuja espacio. */
                s += "   ";
        return s;
    }

    /**
     * Convierte el vértice (visto como instancia de {@link
     * IVertexBinaryTree}) en vértice (visto como instancia de {@link
     * Vertex}). Método auxiliar para hacer esta audición en un único lugar.
     * @param vertice el vértice de árbol binario que queremos como vértice.
     * @return el vértice recibido visto como vértice.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         Vertex}.
     */
    protected Vertex vertice(IVertexBinaryTree<T> vertice) {
        /* No necesitamos suprimir advertencias porque Vertice no es
         * genérica. */
        Vertex v = (Vertex)vertice;
        return v;
    }
}
