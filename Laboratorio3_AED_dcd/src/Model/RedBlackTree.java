package Model;

import java.awt.Color;
import java.util.List;

import Interfaces.IredBlackTree;
import Interfaces.VerticeArbolBinario;

public class RedBlackTree<T extends Comparable<T>> extends BinaryTreesOrdered<T> {

/**
 * Clase interna protegida para v�rtices de �rboles rojinegros. La �nica
 * diferencia con los v�rtices de �rbol binario, es que tienen un campo para
 * el color del v�rtice.
 */
protected class VerticeRojinegro extends ArbolBinario<T>.Vertice {

    /** El color del v�rtice. */
    public Color color;

    /**
     * Constructor �nico que recibe un elemento.
     * @param elemento el elemento del v�rtice.
     */
    public VerticeRojinegro(T elemento) {
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
        return ((esNegro(this)) ? "N":"R") + "{" +  ((this.elemento == null) ? "null":this.elemento.toString()) + "}";
        // Aqu� va su c�digo.
    }

    /**
     * Auxiliar de equals. Compara vertice por vertice.
     * @param v1 Vertice de arbol 1
     * @param v2 Vertice de arbol 2
     * @return <code>true</code> si arbol 1 y arbol 2
     *         son iguales; <code>false</code> en otro caso.
     */
    private boolean equals(VerticeRojinegro v1, VerticeRojinegro v2) {
        if (v1 == null && v2 == null) {
            return true;
        }
        if ((v1 == null && v2 != null) || (v1 != null && v2 == null) || !v1.elemento.equals(v2.elemento) || v1.color != v2.color) {
            return false;
        }
        return equals(verticeRojinegro(v1.izquierdo), verticeRojinegro(v2.izquierdo)) && equals(verticeRojinegro(v1.derecho), verticeRojinegro(v2.derecho));
    }

    /**
     * Compara el v�rtice con otro objeto. La comparaci�n es
     * <em>recursiva</em>.
     * @param o el objeto con el cual se comparar� el v�rtice.
     * @return <code>true</code> si el objeto es instancia de la clase
     *         {@link VerticeRojinegro}, su elemento es igual al elemento de
     *         �ste v�rtice, los descendientes de ambos son recursivamente
     *         iguales, y los colores son iguales; <code>false</code> en
     *         otro caso.
     */
    @Override public boolean equals(Object o) {
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") VerticeRojinegro vertice = (VerticeRojinegro)o;
        return this.equals(this, vertice);
    }
}

/**
 * Construye un nuevo v�rtice, usando una instancia de {@link
 * VerticeRojinegro}.
 * @param elemento el elemento dentro del v�rtice.
 * @return un nuevo v�rtice rojinegro con el elemento recibido dentro del
 *         mismo.
 */
@Override protected Vertice nuevoVertice(T elemento) {
    return new VerticeRojinegro(elemento);
}

/**
 * Convierte el v�rtice (visto como instancia de {@link
 * VerticeArbolBinario}) en v�rtice (visto como instancia de {@link
 * VerticeRojinegro}). M�todo auxililar para hacer esta audici�n en un �nico
 * lugar.
 * @param vertice el v�rtice de �rbol binario que queremos como v�rtice
 *                rojinegro.
 * @return el v�rtice recibido visto como v�rtice rojinegro.
 * @throws ClassCastException si el v�rtice no es instancia de {@link
 *         VerticeRojinegro}.
 */
private VerticeRojinegro verticeRojinegro(VerticeArbolBinario<T> vertice) {
    VerticeRojinegro v = (VerticeRojinegro)vertice;
    return v;
}

/**
 * Regresa el color del v�rtice rojinegro.
 * @param vertice el v�rtice del que queremos el color.
 * @return el color del v�rtice rojinegro.
 * @throws ClassCastException si el v�rtice no es instancia de {@link
 *         VerticeRojinegro}.
 */
public Color getColor(VerticeArbolBinario<T> vertice) {
    VerticeRojinegro verticeRN = this.verticeRojinegro(vertice);
    return verticeRN.color;
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

private void rebalanceoAgrega (VerticeRojinegro vertice) {
    VerticeRojinegro padre, tio, abuelo, aux;
    // Caso 1
    if (!vertice.hayPadre()) {
        vertice.color = Color.BLACK;
        return;
    }
    // Caso 2
    padre = this.verticeRojinegro(vertice.padre);
    if (padre.color == Color.BLACK) {
        return;
    }
    // Caso 3
    abuelo = this.verticeRojinegro(padre.padre);
    if (this.esHijoIzquierdo(padre)) {
        tio = this.verticeRojinegro(abuelo.derecho);
    } else {
        tio = this.verticeRojinegro(abuelo.izquierdo);
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
            super.giraIzquierda(padre);
        } else {
            super.giraDerecha(padre);
        }
        aux = padre;
        padre = vertice;
        vertice = aux;
    }
    // Caso 5
    padre.color = Color.BLACK;
    abuelo.color = Color.RED;
    if (this.esHijoIzquierdo(vertice)) {
        this.giraDerecha(abuelo);
    } else {
        this.giraIzquierda(abuelo);
    }
}

/**
 * Agrega un nuevo elemento al �rbol. El m�todo invoca al m�todo {@link
 * BinaryTreesOrdered#agrega}, y despu�s balancea el �rbol recoloreando
 * v�rtices y girando el �rbol como sea necesario.
 * @param elemento el elemento a agregar.
 */
@Override public void agrega(T elemento) {
    VerticeRojinegro ultimoAgregadoRN;
    super.agrega(elemento);
    ultimoAgregadoRN = this.verticeRojinegro(this.ultimoAgregado);
    this.rebalanceoAgrega(ultimoAgregadoRN);
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
 * Auxiliar que dice si 2 vertices tienen diferente color.
 * @param v1 VerticeRojinegro
 * @param v2 VerticeRojinegro
 * @return <code> true </code> si lo es. <code> false </code> en otro caso.
 */
private boolean sonVerticesBicoloreados(VerticeRojinegro v1, VerticeRojinegro v2) {
    return this.esNegro(v1) != this.esNegro(v2);
}

/**
 * Auxiliar de Elimina. Sube el unico vertice que puede tener el vertice padre.
 * @param padre Vertice que sera remplazado por su unico hijo.
 **/
private void subirUnicoHijo(Vertice padre) {
    if (!padre.hayIzquierdo()) {
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
private VerticeRojinegro getUnicoHijo(VerticeRojinegro padre) {
    if (padre.hayIzquierdo()) {
        return verticeRojinegro(padre.izquierdo);
    }
    return verticeRojinegro(padre.derecho);
}

/**
 * Auxiliar de Elimina. Obtiene al hermano del vertice.
 * @param vertice de quien se quiere obtener su hermano.
 * @return La referencia del vertice que es hermano del vertice.
 **/
private VerticeRojinegro getHermano(VerticeRojinegro vertice) {
    if (this.esHijoIzquierdo(vertice)) {
        return verticeRojinegro(vertice.padre.derecho);
    }
    return verticeRojinegro(vertice.padre.izquierdo);
}

/**
 * Auxiliar de Elimina. Dice si el vertice es negro tambi�n
 * checando si la referencia es null.
 * @param vertice a evaluar.
 * @return <code>true</code> si el vertice es negro, <code>false</code>
 * cualquier otra cosa.
 **/
private boolean esNegro(VerticeRojinegro vertice) {
    return vertice == null || vertice.color == Color.BLACK;
}

/**
 * Auxiliar de Elimina. Revalancea el arbol en caso de que se necesite en
 * el metodo de elimina. Se divide en 6 casos.
 * @param vertice VerticeRojiNegro desde donde se va a balancear.
 **/
private void rebalanceoElimina(VerticeRojinegro vertice) {
    VerticeRojinegro hermano, padre, sobrinoIzq, sobrinoDer;
    /**
     * Caso 1
     *
     * El padre es null
     *
     **/
    if (!vertice.hayPadre()) {
        // Asignamos la raiz al vertice.
        this.raiz = vertice;
        // Terminamos
        return;
    }
    padre = verticeRojinegro(vertice.padre);
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
            super.giraIzquierda(padre);
        } else {
            super.giraDerecha(padre);
        }
        // Cambiamos referencias de padre y hermano.
        padre = verticeRojinegro(vertice.padre);
        hermano = this.getHermano(vertice);
    }
    sobrinoIzq = verticeRojinegro(hermano.izquierdo);
    sobrinoDer = verticeRojinegro(hermano.derecho);
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
            super.giraDerecha(hermano);
        } else {
            super.giraIzquierda(hermano);
        }
        hermano = this.getHermano(vertice);
        sobrinoIzq = verticeRojinegro(hermano.izquierdo);
        sobrinoDer = verticeRojinegro(hermano.derecho);
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
        super.giraIzquierda(padre);
    } else {
        super.giraDerecha(padre);
    }
}

/**
 * Auxiliar de Elimina. Elimina el posible vertice fantasma que pueda haber.
 * @param eliminar VerticeRojinegro que queremos ver si es fantasma
 **/
private void eliminarFantasma(VerticeRojinegro eliminar) {
    if (eliminar.elemento == null) {
        eliminaHoja(eliminar);
    }
}

/**
 * Elimina un elemento del �rbol. El m�todo elimina el v�rtice que contiene
 * el elemento, y recolorea y gira el �rbol como sea necesario para
 * rebalancearlo.
 * @param elemento el elemento a eliminar del �rbol.
 */
@Override public void elimina(T elemento) {
    VerticeRojinegro aux, hijo;
    // Buscamos el vertice que tiene el elemento que queremos eliminar.
    VerticeRojinegro eliminar = this.verticeRojinegro(super.busca(elemento));
    // Si no lo encontro, simplemente terminamos.
    if (eliminar == null) {
        return;
    }
    // Si tiene hijo izquierdo.
    if (eliminar.hayIzquierdo()) {
        // Obtenemos el Vertice que es maximo en el subarbol izquierdo del vertice que
        // queremos eliminar.
        aux = verticeRojinegro(maximoEnSubarbol(eliminar.izquierdo));
        // Intercambiamos el elemento que tiene el vertice que queremos eliminar
        // con el del maximo en el subarbol izquierdo.
        eliminar.elemento = aux.elemento;
        // Ahora ya queremos eliminar al maximo del subarbol izquierdo.
        eliminar = aux;
    }
    // Vertificamos si el que queremos eliminar es hoja.
    if (!eliminar.hayIzquierdo() && !eliminar.hayDerecho()) {
        // Creamos un vertice fantasma y lo ponemos como hijo del vertice que queremos
        // eliminar.
        eliminar.izquierdo = this.nuevoVertice(null);
        eliminar.izquierdo.padre = eliminar;
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