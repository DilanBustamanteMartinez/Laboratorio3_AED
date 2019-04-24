package Interfaces;

import java.util.List;

public interface IredBlackTree <T,K> {
	
	public  void insert(T value,K key);
	public void delete (T elemt);
	public List<T> getPreOrder();
	public boolean exist(T elemt, K key);
    public T search(T value, K key);
    public T getRoot();
    public int getWeight();
    public int getHeight();
    public T getMin();
    public T getMax();
	

}
