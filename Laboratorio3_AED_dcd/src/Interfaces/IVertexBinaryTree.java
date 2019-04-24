package Interfaces;

public interface IVertexBinaryTree<T> {

    public boolean isFather();
    public boolean LeftSon();
    public boolean RightSon();
    public IVertexBinaryTree<T> getFather();
    public IVertexBinaryTree<T> getLeft();
    public IVertexBinaryTree<T> getRight();
    public T get();
}

