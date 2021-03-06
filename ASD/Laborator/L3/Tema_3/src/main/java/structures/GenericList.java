package structures;

public class GenericList<T> {
    private GenericNode<T> head, tail;
    private int size;

    public GenericList() {
        head = tail = null;
    }

    public void reverse(boolean withMemory) {
        if (withMemory) {
            GenericList<T> list = new GenericList<>();
            for (GenericNode<T> node = head; node != null; node = node.getNext()) {
                list.pushFront(node.getValue());
            }
            list.print();
        } else {
            GenericNode<T> node = head;
            while (node != null && node.getNext() != null) {
                GenericNode<T> temp = node.getNext();
                node.setNext(temp.getNext());
                temp.setNext(head);
                head = temp;
            }
            print();
        }
    }

    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            GenericNode<T> node = head;
            while (index != 0 && node != null) {
                node = node.getNext();
                --index;
            }

            if (index != 0) {
                throw new IndexOutOfBoundsException();
            }
            return node.getValue();
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        } else if (index == 0) {
            removeFront();
        } else if (index == size - 1) {
            removeBack();
        } else {
            --size;
            GenericNode<T> node = head;
            while (index-- != 1) {
                node = node.getNext();
            }
            node.setNext(node.getNext().getNext());
        }
    }

    public void removeFront() {
        if (head != null) {
            --size;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.getNext();
            }
        }
    }

    public void removeBack() {
        if (head != null) {
            --size;
            if (head == tail) {
                head = tail = null;
            } else {
                GenericNode<T> node = head;
                while (node.getNext() != tail) {
                    node = node.getNext();
                }
                node.setNext(null);
                tail = node;
            }
        }
    }

    public void pushBack(T element) {
        ++size;
        if (head == null) {
            head = tail = new GenericNode<>(element);
        } else {
            tail.setNext(new GenericNode<>(element));
            tail = tail.getNext();
        }
    }

    public void pushFront(T element) {
        ++size;
        if (head == null) {
            head = tail = new GenericNode<T>(element);
        } else {
            head = new GenericNode<T>(element, head);
        }
    }

    public void print() {
        System.out.print("The List is: ");
        for (GenericNode<T> node = head; node != null; node = node.getNext()) {
            System.out.print(node.getValue() + " ");
        }
        System.out.println();
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public GenericNode<T> getHead() {
        return head;
    }

    public void setHead(GenericNode<T> head) {
        this.head = head;
    }

    public GenericNode<T> getTail() {
        return tail;
    }

    public void setTail(GenericNode<T> tail) {
        this.tail = tail;
    }

    public void incSize() {
        ++size;
    }

    public void decSize() {
        --size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCircular(boolean isCircular) {
        if (tail != null) {
            if (isCircular) {
                tail.setNext(head);
            } else {
                tail.setNext(null);
            }
        } else {
            System.out.println("List cannot be made circular !");
        }
    }

    public boolean isCircular() {
        return tail != null && tail.getNext() == head;
    }
}