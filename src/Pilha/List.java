package Pilha;


public class List<T extends Comparable<T>> {
    protected Node<T> First;

    public void insertLeft(T Dado){
        First = new Node(Dado,First);
    }
    public T removeLeft(){
        if(First != null ){
            if (First.getProx()!= null){
                T Aux = First.getDado();
                First = First.getProx();
                return Aux;
            }else{
                T Aux = First.getDado();
                First = null;
                return Aux;
            }
        }
        return null;
    }
    public boolean isEmpty(){
        return First == null;
    }
}
