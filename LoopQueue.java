public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;
    
    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if(getSize() + 1 == data.length){ //full
            //變大
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("it's empty. unble to dequeue.");
        }
        E e  = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size --;
        if(size == getCapacity()/4 && getCapacity()/2 != 0){
            resize(getCapacity()/2);
        }
        return e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("it's empty. unble to get element.");
        }
        return data[front];
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity+1];
        for(int i = 0; i < data.length; i++){
            newData[i] = data[(front + i) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        str.append("front [");
        for(int i = front; i!=tail ; i = (i+1) % data.length){
            str.append(data[i]);
            if((i + 1) % data.length != tail){
                str.append(", ");
            }
        }
        str.append("] tail");
        return str.toString();
    }
    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>(5);
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
