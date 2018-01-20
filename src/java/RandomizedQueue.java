import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by kenvi on 2018/1/20.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Object[] items;
    private int head;
    private int tail;


    public RandomizedQueue() {
        size = 0;
        items = new Object[0];

    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;

    }
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        if (size + 1 >= items.length) {
            Object[] newItems = new Object[items.length << 1];
            copy(items, newItems, tail, head);
            tail = 0;
            head = tail + size;

            items = newItems;
        }

        items[head++] = item;
        size++;
    }

    private void copy(Object[] oldList, Object[] newList, int start, int end) {
        for (int i = start; i <= end; i++) {
            newList[i] = oldList[i-start];
        }
    }

    @SuppressWarnings (value = "unchecked")
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (size - 1 < items.length / 4) {
            Object[] newItems = new Object[items.length << 2];
            copy(items, newItems, tail, head);

            tail = 0;
            head = tail + size;
        }
        int index = StdRandom.uniform(tail, head);
        swap(index, head);
        size--;

        return (Item) items[head--];

    }

    private void swap(int i, int j) {
        Object temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    @SuppressWarnings("unchecked")
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();

        int index = StdRandom.uniform(tail, head);
        return (Item) items[index];

    }

    private class RandamizedQueueIterator implements Iterator<Item> {
        private int index;
        private final int[] randomIndex;

        public RandamizedQueueIterator() {
            index = 0;
            randomIndex = StdRandom.permutation(size);
        }

        @Override
        public boolean hasNext() {
            return index < randomIndex.length;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (Item) items[randomIndex[tail + index++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }
    }
    public Iterator<Item> iterator() {
        return new RandamizedQueueIterator();

    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();

        int max = 100;

        for (int i = 0; i <= max; i++) {
            randomizedQueue.enqueue(i);
        }

        for (int i : randomizedQueue) {
            System.out.print(i +",");
        }
        System.out.println("============");

        for (int i = 0; i <= max; i++) {
            randomizedQueue.dequeue();
        }

        System.out.println(randomizedQueue.size);
        randomizedQueue.sample();



    }


}
