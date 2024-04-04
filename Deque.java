public class Deque<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public Deque(int capacity) {
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public Deque() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void addLast(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public void addFront(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        front = front == 0 ? data.length - 1 : front - 1;
        data[front] = e;
        size++;
    }

    public E removeFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("deque is empty. no element to remove.");
        }
        E e = data[front];
        data[front] = null; //
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) //
            resize(getCapacity() / 2);
        return e;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("deque is empty. no element to remove.");
        }
        tail = tail == 0 ? data.length - 1 : tail - 1; //
        E e = data[tail];
        data[tail] = null;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return e;
    }

    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("deque is empty. no front element.");
        return data[front];
    }

    public E getLast() {
        if (isEmpty())
            throw new IllegalArgumentException("deque is empty. no tail element.");
        int idx = tail == 0 ? data.length - 1 : tail - 1;
        return data[idx];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        str.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            str.append(data[i]);
            if ((i + 1) % data.length != tail) {
                str.append(", ");
            }
        }
        str.append("] tail");
        return str.toString();
    }

    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0)
                dq.addLast(i);
            else
                dq.addFront(i);
            System.out.println(dq);
        }
        System.out.println();
        for (int i = 0; !dq.isEmpty(); i++) {
            if (i % 2 == 0)
                dq.removeFront();
            else
                dq.removeLast();
            System.out.println(dq);
        }
    }
}
