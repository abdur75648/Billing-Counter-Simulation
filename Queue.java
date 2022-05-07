public class Queue {
    private int front,rear;
    private Object queue_arr[];
    
    public Queue(){
        this.queue_arr = new Object[1];
        this.front = 0;
        this.rear = -1; // rear pointer 1 step ahead. So, max = max + 1
    }

    private void doubleSize(){
        Object[] new_queue_arr = new Object[2*this.queue_arr.length];
        System.arraycopy(this.queue_arr, 0, new_queue_arr, 0, this.queue_arr.length);
        this.queue_arr = new_queue_arr;
    }

    public int getSize() {
        return ((this.rear + 1) - this.front);
    }

    private boolean isFull(){
        return this.rear == queue_arr.length -1; // rear pointer 1 step ahead. So, max was actually max + 1
    }

    public boolean isEmpty(){
        return (this.getSize()<=0);
    }

    public void enqueue(Object new_data){
        if (this.isFull()){
            this.doubleSize();
        }
        this.rear = this.rear + 1;
        this.queue_arr[this.rear] = new_data;
        return;
    }

    public void enqueueInFront(Object new_data){
        if (this.front==0){
            if (this.isEmpty()) {
                this.enqueue(new_data);
                return;
            }
            else{
                Object[] new_queue_arr = new Object[this.queue_arr.length+1];
                System.arraycopy(this.queue_arr, 0, new_queue_arr, 1, this.queue_arr.length);
                this.queue_arr = new_queue_arr;
                this.rear+=1;
                this.queue_arr[this.front] = new_data;
                return;
            }
        }
        else{
            this.front-=1;
            this.queue_arr[this.front] = new_data;
        }
    }

    public Object dequeue(){
        if (this.isEmpty()){
            System.out.println("Error: Can't dequeue from an empty queue!");
            return null;
        }
        else{
            Object res = this.queue_arr[this.front];
            this.front = (this.front + 1);
            return res;
        }
    }

    public Object getFront(){
        if (this.isEmpty())
            return null;
        return this.queue_arr[this.front];
    }
 
    public Object getRear(){
        if (this.isEmpty())
            return null;
        return this.queue_arr[this.rear];
    }
}