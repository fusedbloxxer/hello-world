package probleme.liste;

import structures.GenericList;
import structures.GenericNode;

import java.util.stream.IntStream;

public class ProblemEleven {
    public static void main(String[] args) {
        GenericList<Integer> C = new GenericList<>();
        GenericList<Integer> A = new GenericList<>();
        GenericList<Integer> B = new GenericList<>();
        IntStream.range(1, 21).forEach(C::pushBack);
        C.print();

        split(A, B, C);
        A.print();
        B.print();
    }

    private static void split(GenericList<Integer> A, GenericList<Integer> B, GenericList<Integer> C) {
        GenericNode<Integer> even = C.getHead();
        GenericNode<Integer> odd = (even != null) ? even.getNext() : null;
        A.setHead(even);
        A.setTail(even);
        B.setHead(odd);
        B.setTail(odd);

        while (even != null && odd != null) {
            even.setNext(odd.getNext());
            if (odd.getNext() != null) {
                odd.setNext(odd.getNext().getNext());
            }

            A.setTail(even);
            B.setTail(odd);
            even = even.getNext();
            odd = odd.getNext();
        }

        C.setHead(null);
        C.setTail(null);
    }
}
