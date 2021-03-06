package Model;

import java.awt.Color;
import java.util.List;

import Interfaces.IredBlackTree;
import Interfaces.IVertexBinaryTree;

public class RedBlackTree<T extends Comparable<T>> extends BinaryTreesOrdered<T> {

	
/**
 * Clase interna protegida para v�rtices de �rboles rojinegros. La �nica
 * diferencia con los v�rtices de �rbol binario, es que tienen un campo para
 * el color del v�rtice.
 */
public class ARNVertex extends BinaryTree<T>.Vertex {

    /** El color del v�rtice. */
    public Color color;

    /**
     * Constructor �nico que recibe un elemento.
     * @param elemento el elemento del v�rtice.
     */
    public ARNVertex(T elemento) {
        super(elemento);
        if (elemento == null) {
            this.color = color.BLACK;
        } else {
            this.color = color.RED;
        }
    }

    /**
     * Regresa una representaci�n en cadena del v�rtice rojinegro.
     * @return una representaci�n en cadena del v�rtice rojinegro.
     */
    public String toString() {
        return ((esNegro(this)) ? "N":"R") + "{" +  ((this.element == null) ? "null":this.element.toString()) + "}";
        // Aqu� va su c�digo.
    }

    /**
     * Auxiliar de equals. Compara vertice por vertice.
     * @param v1 Vertice de arbol 1
     * @param v2 Vertice de arbol 2
     * @return <code>true</code> si arbol 1 y arbol 2
     *         son iguales; <code>false</code> en otro caso.
     */
    public boolean equals(ARNVertex v1, ARNVertex v2) {
        if (v1 == null && v2 == null) {
            return true;
        }
        if ((v1 == null && v2 != null) || (v1 != null && v2 == null) || !v1.element.equals(v2.element) || v1.color != v2.color) {
            return false;
        }
        return equals(VertexARN(v1.left), VertexARN(v2.left)) && equals(VertexARN(v1.right), VertexARN(v2.right));
    }

    /**
     * Compara el v�rtice con otro objeto. La comparaci�n es
     * <em>recursiva</em>.
     * @param o el objeto con el cual se comparar� el v�rtice.
     * @return <code>true</code> si el objeto es instancia de la clase
     *         {@link ARNVertex}, su elemento es igual al elemento de
     *         �ste v�rtice, los descendientes de ambos son recursivamente
     *         iguales, y los colores son iguales; <code>false</code> en
     *         otro caso.
     */
    @Override public boolean equals(Object o) {
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") ARNVertex vertice = (ARNVertex)o;
        return this.equals(this, vertice);
    }
}

/**
 * Construye un nuevo v�rtice, usando una instancia de {@link
 * ARNVertex}.
 * @param elemento el elemento dentro del v�rtice.
 * @return un nuevo v�rtice rojinegro con el elemento recibido dentro del
 *         mismo.
 */
@Override public  Vertex nuevoVertice(T elemento) {
    return new ARNVertex(elemento);
}

/**
 * Convierte el v�rtice (visto como instancia de {@link
 * IVertexBinaryTree}) en v�rtice (visto como instancia de {@link
 * ARNVertex}). M�todo auxililar para hacer esta audici�n en un �nico
 * lugar.
 * @param vertice el v�rtice de �rbol binario que queremos como v�rtice
 *                rojinegro.
 * @return el v�rtice recibido visto como v�rtice rojinegro.
 * @throws ClassCastException si el v�rtice no es instancia de {@link
 *         ARNVertex}.
 */
private ARNVertex VertexARN(IVertexBinaryTree<T> vertice) {
    ARNVertex v = (ARNVertex)vertice;
    return v;
}

/**
 * Regresa el color del v�rtice rojinegro.
 * @param vertice el v�rtice del que queremos el color.
 * @return el color del v�rtice rojinegro.
 * @throws ClassCastException si el v�rtice no es instancia de {@link
 *         ARNVertex}.
 */
public Color getColor(IVertexBinaryTree<T> vertice) {
    ARNVertex verticeRN = this.VertexARN(vertice);
    return verticeRN.color;
}

/**
 * Verifica si el vertice recibido es hijo izquierdo de su padre
 * @param v vertice que se verifica.
 * @throws <code> true </code> si lo es. <code> false </code> en otro caso.
 */    
private boolean esHijoIzquierdo(Vertex v) {
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
private boolean esHijoDerecho(Vertex v) {
    if (!v.isFather()) {
        return false;
    }
    return v.father.right == v;
}

public  void rebalanceoAgrega (ARNVertex vertice) {
    ARNVertex padre, tio, abuelo, aux;
    // Caso 1
    if (!vertice.isFather()) {
        vertice.color = Color.BLACK;
        return;
    }
    
    // Caso 2
    padre = this.VertexARN(vertice.father);
    if (padre.color == Color.BLACK) {
        return;
    }
    // Caso 3
    abuelo = this.VertexARN(padre.father);
    if (this.esHijoIzquierdo(padre)) {
        tio = this.VertexARN(abuelo.right);
    } else {
        tio = this.VertexARN(abuelo.left);
    }
    if (tio != null && tio.color == Color.RED) {
        tio.color = Color.BLACK;
        padre.color = Color.BLACK;
        abuelo.color = Color.RED;
        this.rebalanceoAgrega(abuelo);
        return;
    }
    // Caso 4
    if (this.esHijoIzquierdo(vertice) != this.esHijoIzquierdo(padre)) {
        if (this.esHijoIzquierdo(padre)) {
            super.LeftRotate(padre);
        } else {
            super.RightRotate(padre);
        }
        aux = padre;
        padre = vertice;
        vertice = aux;
    }
    // Caso 5
    padre.color = Color.BLACK;
    abuelo.color = Color.RED;
    if (this.esHijoIzquierdo(vertice)) {
        this.RightRotate(abuelo);
    } else {
        this.LeftRotate(abuelo);
    }
}

/**
 * Agrega un nuevo elemento al �rbol. El m�todo invoca al m�todo {@link
 * BinaryTreesOrdered#agrega}, y despu�s balancea el �rbol recoloreando
 * v�rtices y girando el �rbol como sea necesario.
 * @param elemento el elemento a agregar.
 */
@Override public void add(T elemento) {
    ARNVertex ultimoAgregadoRN;
    super.add(elemento);
    ultimoAgregadoRN = this.VertexARN(this.ultimoAgregado);
    this.rebalanceoAgrega(ultimoAgregadoRN);
}

/**
 * Auxiliar de elimina. Elimina una hoja.
 * @param eliminar el elemento a eliminar que debe ser hoja.
 */
public  void eliminaHoja(Vertex eliminar) {
    if (this.raiz == eliminar) {
        this.raiz = null;
        this.ultimoAgregado = null;
    } else if (this.esHijoIzquierdo(eliminar)) {
        eliminar.father.left = null;
    } else {
        eliminar.father.right = null;
    }
}

/**
 * Auxiliar de elimina. Elimina vertice que no tiene hijo izquierdo.
 * @param eliminar el elemento a eliminar que debe no tener hijo izquierdo.
 */
private void eliminaSinHijoIzquierdo(Vertex eliminar) {
    if (this.raiz == eliminar) {
        this.raiz = this.raiz.right;
        eliminar.right.father = null;
    } else {
        eliminar.right.father = eliminar.father;
        if (this.esHijoIzquierdo(eliminar)) {
            eliminar.father.left = eliminar.right;
        } else {
            eliminar.father.right = eliminar.right;
        }
    }
    this.elementos--;
}

/**
 * Auxiliar de elimina. Elimina vertice que no tiene hijo derecho.
 * @param eliminar el elemento a eliminar que debe no tener hijo derecho.
 */
private void eliminaSinHijoDerecho(Vertex eliminar) {
    if (this.raiz == eliminar) {
        this.raiz = this.raiz.left;
        eliminar.left.father = null;
    } else {
        eliminar.left.father = eliminar.father;
        if (this.esHijoIzquierdo(eliminar)) {
            eliminar.father.left = eliminar.left;
        } else {
            eliminar.father.right = eliminar.left;
        }
    }
    this.elementos--;
}

/**
 * Auxiliar que dice si 2 vertices tienen diferente color.
 * @param v1 VerticeRojinegro
 * @param v2 VerticeRojinegro
 * @return <code> true </code> si lo es. <code> false </code> en otro caso.
 */
private boolean sonVerticesBicoloreados(ARNVertex v1, ARNVertex v2) {
    return this.esNegro(v1) != this.esNegro(v2);
}

/**
 * Auxiliar de Elimina. Sube el unico vertice que puede tener el vertice padre.
 * @param padre Vertice que sera remplazado por su unico hijo.
 **/
private void subirUnicoHijo(Vertex padre) {
    if (!padre.LeftSon()) {
        this.eliminaSinHijoIzquierdo(padre);
    } else {
        this.eliminaSinHijoDerecho(padre);
    }
}

/**
 * Auxiliar que regresa el hijo de un vertice que solo tiene un hijo.
 * @param padre vertice de quien queremos su hijo
 * @return unico hijo de padre
 */
private ARNVertex getUnicoHijo(ARNVertex padre) {
    if (padre.LeftSon()) {
        return VertexARN(padre.left);
    }
    return VertexARN(padre.right);
}

/**
 * Auxiliar de Elimina. Obtiene al hermano del vertice.
 * @param vertice de quien se quiere obtener su hermano.
 * @return La referencia del vertice que es hermano del vertice.
 **/
private ARNVertex getHermano(ARNVertex vertice) {
    if (this.esHijoIzquierdo(vertice)) {
        return VertexARN(vertice.father.right);
    }
    return VertexARN(vertice.father.left);
}

/**
 * Auxiliar de Elimina. Dice si el vertice es negro tambi�n
 * checando si la referencia es null.
 * @param vertice a evaluar.
 * @return <code>true</code> si el vertice es negro, <code>false</code>
 * cualquier otra cosa.
 **/
private boolean esNegro(ARNVertex vertice) {
    return vertice == null || vertice.color == Color.BLACK;
}

/**
 * Auxiliar de Elimina. Revalancea el arbol en caso de que se necesite en
 * el metodo de elimina. Se divide en 6 casos.
 * @param vertice VerticeRojiNegro desde donde se va a balancear.
 **/
private void rebalanceoElimina(ARNVertex vertice) {
    ARNVertex hermano, padre, sobrinoIzq, sobrinoDer;
    /**
     * Caso 1
     *
     * El padre es null
     *
     **/
    if (!vertice.isFather()) {
        // Asignamos la raiz al vertice.
        this.raiz = vertice;
        // Terminamos
        return;
    }
    padre = VertexARN(vertice.father);
    hermano = this.getHermano(vertice);
    /**
     * Caso 2
     *
     * El hermano es Rojo
     *
     **/
    if (!this.esNegro(hermano)) {
        // Coloreamos la hermano de Negro.
        hermano.color = Color.BLACK;
        // Coloreamos la padre de Rojo.
        padre.color = Color.RED;
        // Giramos sobre al padre en la direccion del vertice.
        if (this.esHijoIzquierdo(vertice)) {
            super.LeftRotate(padre);
        } else {
            super.RightRotate(padre);
        }
        // Cambiamos referencias de padre y hermano.
        padre = VertexARN(vertice.father);
        hermano = this.getHermano(vertice);
    }
    sobrinoIzq = VertexARN(hermano.left);
    sobrinoDer = VertexARN(hermano.right);
    /**
     * Caso 3
     *
     * El padre, el hermano y los sobrinos son Negros.
     *
     **/
    if (this.esNegro(hermano) && this.esNegro(sobrinoIzq) && this.esNegro(sobrinoDer)) {
        if (this.esNegro(padre)) {
            // Coloreamos al hermano de Rojo.
            hermano.color = Color.RED;
            // Hacemos recursion sobre el padre.
            this.rebalanceoElimina(padre);
            // Terminamos
            return;
        }
        /**
         * Caso 4
         *
         * El hermano y los sobrinos son Negros y el Padre es Rojo.
         *
         **/
        // Coloreamos al padre de Negro.
        padre.color = Color.BLACK;
        // Coloreamos al hermano de Rojo.
        hermano.color = Color.RED;
        // Terminados.
        return;
    }
    /**
     * Caso 5
     *
     * Los sobrinos son bicoloreados y el sobrino cruzado es Negro.
     *
     **/
    if (this.sonVerticesBicoloreados(sobrinoIzq, sobrinoDer) && (
        // Evaluando si un sobrino es cruzado
        (this.esNegro(sobrinoIzq) && this.esHijoDerecho(vertice)) || (this.esNegro(sobrinoDer) && this.esHijoIzquierdo(vertice)))) {
        // Coloreamos al sobrino Rojo de Negro
        if (!this.esNegro(sobrinoIzq)) {
            sobrinoIzq.color = Color.BLACK;
        } else {
            sobrinoDer.color = Color.BLACK;
        }
        // Coloreamos al hermano de Rojo
        hermano.color = Color.RED;
        //Giramos sobre el hermano en la direccion contraria al vertice
        if (this.esHijoIzquierdo(vertice)) {
            super.RightRotate(hermano);
        } else {
            super.LeftRotate(hermano);
        }
        hermano = this.getHermano(vertice);
        sobrinoIzq = VertexARN(hermano.left);
        sobrinoDer = VertexARN(hermano.right);
    }
    /**
     * Caso 6
     *
     * El sobrino cruzado es Rojo
     *
     **/
    // Coloreamos al hermano del color del padre
    hermano.color = padre.color;
    // Coloreamos al padre de negro
    padre.color = Color.BLACK;
    // Coloreamos al sobrino cruzado de Negro
    if (this.esHijoIzquierdo(vertice)) {
        sobrinoDer.color = Color.BLACK;
    } else {
        sobrinoIzq.color = Color.BLACK;
    }
    // Giramos sobre el padre en la direccion del vertice
    if (this.esHijoIzquierdo(vertice)) {
        super.LeftRotate(padre);
    } else {
        super.RightRotate(padre);
    }
}

/**
 * Auxiliar de Elimina. Elimina el posible vertice fantasma que pueda haber.
 * @param eliminar VerticeRojinegro que queremos ver si es fantasma
 **/
private void eliminarFantasma(ARNVertex eliminar) {
    if (eliminar.element == null) {
        eliminaHoja(eliminar);
    }
}

/**
 * Elimina un elemento del �rbol. El m�todo elimina el v�rtice que contiene
 * el elemento, y recolorea y gira el �rbol como sea necesario para
 * rebalancearlo.
 * @param elemento el elemento a eliminar del �rbol.
 */
@Override public void delete(T elemento) {
    ARNVertex aux, hijo;
    // Buscamos el vertice que tiene el elemento que queremos eliminar.
    ARNVertex eliminar = this.VertexARN(super.busca(elemento));
    // Si no lo encontro, simplemente terminamos.
    if (eliminar == null) {
        return;
    }
    // Si tiene hijo izquierdo.
    if (eliminar.LeftSon()) {
        // Obtenemos el Vertice que es maximo en el subarbol izquierdo del vertice que
        // queremos eliminar.
        aux = VertexARN(MaxSubtree(eliminar.left));
        // Intercambiamos el elemento que tiene el vertice que queremos eliminar
        // con el del maximo en el subarbol izquierdo.
        eliminar.element = aux.element;
        // Ahora ya queremos eliminar al maximo del subarbol izquierdo.
        eliminar = aux;
    }
    // Vertificamos si el que queremos eliminar es hoja.
    if (!eliminar.LeftSon() && !eliminar.RightSon()) {
        // Creamos un vertice fantasma y lo ponemos como hijo del vertice que queremos
        // eliminar.
        eliminar.left = this.nuevoVertice(null);
        eliminar.left.father = eliminar;
    }
    // En esta parte el vertice que queremos eliminar siempre tiene solo un hijo.
    // Obtenemos el unico hijo que tiene el vertice que queremos eliminar.
    hijo = getUnicoHijo(eliminar);
    // Subimos el unico hijo del vertice que queremos eliminar.
    this.subirUnicoHijo(eliminar);
    // Si tenian diferentes colores el hijo y el vertice que queremos eliminar, rebalanceamos.
    if (!this.sonVerticesBicoloreados(eliminar, hijo)) {
        hijo.color = Color.BLACK;
        this.rebalanceoElimina(hijo);
    } else {
        hijo.color = Color.BLACK;
    }
    // Eliminamos el vertice fantasma si lo hay
    this.eliminarFantasma(hijo);
    // FIN :)
}
}