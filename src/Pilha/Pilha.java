package Pilha;

public class Pilha<T extends Comparable<T>> extends List<T> {
    private int k;

    public Pilha() {
        super();
        k=0;
    }

    /** Verifica se a pilha esta vazia
     *
     * @return Pilha vazia ou não
     */

    public boolean isEmpty(){
        return k == 0;
    }

    /** Empilha
     *
     * @param Dado Elemento a ser empilhado
     */
    public void push(T Dado){
        super.insertLeft(Dado);
        k++;
    }

    /** Desempilha
     *
     * @return Elemento mais ao topo
     */
    public T pop(){
        this.k--;
        return super.removeLeft();
    }

    /** Checa o elemento mais ao topo sem desempilhar
     *
     * @return Elemento Mais ao topo
     */
    public T top() {
        if (super.First != null){
            T aux = super.First.Dado;
            return aux;
        }
        return null;
    }
    public int getK(){
        return k;
    }
    public boolean compareTo(Pilha<T> X){
        if (k == X.getK())
            return top().compareTo(X.top()) <= 0;
        return k < X.getK();
    }
}
