package Interfaces;

	public interface Colection<T> extends Iterable<T>{

	    /**
	     * Agrega un elemento a la colecci�n.
	     * @param elemento el elemento a agregar.
	     */
	    public void add(T elemento);

	    /**
	     * Elimina un elemento de la colecci�n.
	     * @param elemento el elemento a eliminar.
	     */
	    public void delete(T elemento);

	    /**
	     * Nos dice si un elemento est� contenido en la colecci�n.
	     * @param elemento el elemento que queremos verificar si est� contenido en
	     *                 la colecci�n.
	     * @return <code>true</code> si el elemento est� contenido en la colecci�n,
	     *         <code>false</code> en otro caso.
	     */
	    public boolean contain(T elemento);

	    /**
	     * Nos dice si el conjunto de elementos en la colecci�n es vac�o.
	     * @return <code>true</code> si el conjunto de elementos en la colecci�n es
	     *         vac�o, <code>false</code> en otro caso.
	     */
	    public boolean isEmpty();

	    /**
	     * Regresa el n�mero de elementos en la colecci�n.
	     * @return el n�mero de elementos en la colecci�n.
	     */
	    public int getElements();
	    
	}

