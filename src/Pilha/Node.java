package Pilha;


public class Node<T extends Comparable<T>> {
    T Dado;
    Node<T> Prox;

    public Node(T dado, Node<T> p) {
        Dado = dado;
        Prox = p;
    }

    public T getDado() {
        return Dado;
    }

    public void setDado(T dado) {
        Dado = dado;
    }

    public Node<T> getProx() {
        return Prox;
    }

    public void setProx(Node<T> prox) {
        Prox = prox;
    }

    @Override
    public String toString() {
        return Dado.toString();
    }
}
