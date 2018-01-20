import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by kenvi on 2018/1/19.
 */
public class Deque<Item> implements Iterable<Item> {
    private int size;
    private final DequeItem<Item> head;
    private final DequeItem<Item> tail;

    public Deque() {
        size = 0;
        head = new DequeItem<Item>();
        tail = new DequeItem<Item>();
        head.next = tail;
        head.prev = null;

        tail.prev = head;
        tail.next = null;

    }
    public boolean isEmpty() {
        return size == 0;

    }
    public int size() {
        return size;
    }
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        DequeItem<Item> newItem = new DequeItem<Item>();
        newItem.value = item;

        newItem.next = head.next;
        newItem.prev = head;
        head.next = newItem;
        newItem.next.prev = newItem;

        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        DequeItem<Item> newItem = new DequeItem<Item>();
        newItem.value = item;

        newItem.next = tail;
        newItem.prev = tail.prev;
        tail.prev = newItem;
        newItem.prev.next = newItem;

        size++;

    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        DequeItem<Item> item = head.next;
        head.next = item.next;
        item.next.prev = head;
        item.prev = null;
        item.next = null;

        size--;
        return item.value;

    }
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        DequeItem<Item> item = tail.prev;
        item.prev.next = tail;
        tail.prev = item.prev;
        item.next = null;
        item.prev = null;

        size--;

        return item.value;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    private class DequeItem<Item> {
        private Item value;
        private DequeItem<Item> next;
        private DequeItem<Item> prev;
    }

    private class DequeIterator implements Iterator<Item> {

        private int index;
        private DequeItem<Item> cur;

        public DequeIterator() {
            index = 0;
            cur = head;
        }



        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                cur = cur.next;
                index++;
                return cur.value;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }




    }
    public static void main(String[] args)  {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);

        for (int i : deque) {
            System.out.println(i);
        }

        deque.removeFirst();
        for (int i : deque) {
            System.out.println(i);
        }
        deque.removeLast();
        for (int i : deque) {
            System.out.println(i);
        }



    }
}
