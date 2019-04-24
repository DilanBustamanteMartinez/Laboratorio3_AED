package Interfaces;

public interface VerticeArbolBinario<T> {


    /**
     * Nos dice si el v�rtice tiene v�rtice padre.
     * @return <tt>true</tt> si el v�rtice tiene v�rtice padre, <tt>false</tt>
     *         en otro caso.
     */
    public boolean hayPadre();

    /**
     * Nos dice si el v�rtice tiene v�rtice izquierdo.
     * @return <tt>true</tt> si el v�rtice tiene v�rtice izquierdo,
     *         <tt>false</tt> en otro caso.
     */
    public boolean hayIzquierdo();

    /**
     * Nos dice si el v�rtice tiene v�rtice derecho.
     * @return <tt>true</tt> si el v�rtice tiene v�rtice derecho, <tt>false</tt>
     *         en otro caso.
     */
    public boolean hayDerecho();

    /**
     * Regresa el v�rtice padre del v�rtice.
     * @return el v�rtice padre del v�rtice.
     * @throws NoSuchElementException si el v�rtice no tiene padre.
     */
    public VerticeArbolBinario<T> getPadre();

    /**
     * Regresa el v�rtice izquierdo del v�rtice.
     * @return el v�rtice izquierdo del v�rtice.
     * @throws NoSuchElementException si el v�rtice no tiene izquierdo.
     */
    public VerticeArbolBinario<T> getIzquierdo();

    /**
     * Regresa el v�rtice derecho del v�rtice.
     * @return el v�rtice derecho del v�rtice.
     * @throws NoSuchElementException si el v�rtice no tiene derecho.
     */
    public VerticeArbolBinario<T> getDerecho();

    /**
     * Regresa el elemento que contiene el v�rtice.
     * @return el elemento que contiene el v�rtice.
     */
    public T get();
}

