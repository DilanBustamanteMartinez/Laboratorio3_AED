package Interfaces;

	public interface Coleccion<T> extends Iterable<T>{

	    /**
	     * Agrega un elemento a la colección.
	     * @param elemento el elemento a agregar.
	     */
	    public void agrega(T elemento);

	    /**
	     * Elimina un elemento de la colección.
	     * @param elemento el elemento a eliminar.
	     */
	    public void elimina(T elemento);

	    /**
	     * Nos dice si un elemento está contenido en la colección.
	     * @param elemento el elemento que queremos verificar si está contenido en
	     *                 la colección.
	     * @return <code>true</code> si el elemento está contenido en la colección,
	     *         <code>false</code> en otro caso.
	     */
	    public boolean contiene(T elemento);

	    /**
	     * Nos dice si el conjunto de elementos en la colección es vacío.
	     * @return <code>true</code> si el conjunto de elementos en la colección es
	     *         vacío, <code>false</code> en otro caso.
	     */
	    public boolean esVacio();

	    /**
	     * Regresa el número de elementos en la colección.
	     * @return el número de elementos en la colección.
	     */
	    public int getElementos();
	}

