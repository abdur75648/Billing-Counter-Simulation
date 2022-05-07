public class Heap {
    private int heap_size;
    private Object heap_arr[];

    public Heap() {
        heap_size = 0;
        heap_arr = new Object[1];
        heap_arr[0] = null;
    }

    public boolean isEmpty(){
        return this.heap_size == 0;
    }

    public boolean isFull(){
        return this.heap_size == heap_arr.length-1;
    }

    private void doubleSize(){
        Object[] new_queue_arr = new Object[2*this.heap_arr.length];
        System.arraycopy(this.heap_arr, 0, new_queue_arr, 0, this.heap_arr.length);
        this.heap_arr = new_queue_arr;
    }

    public Object getMin() {
        if (this.isEmpty()) {
            return null;
        }
        return heap_arr[1];
    }

    private void swap(int i,int j){
        Object a = heap_arr[i];
        heap_arr[i] = heap_arr [j];
        heap_arr[j] = a;
    }
    
    private void percolateUp(){
        int i = heap_size;
        while (i>1 && ((Integer)heap_arr[i] < (Integer)heap_arr[i/2])) {
            swap(i, i/2);
            i = i/2;
        }
    }

    public void insertKey(Object key){
        if (this.isFull()) {
            this.doubleSize();
        }
        heap_size++;
        heap_arr[heap_size] = key;
        percolateUp();
    }

    private void percolateDown(){
        int i = 1;
        while ( i <= (heap_size/2) ) {
            if ( heap_arr[2*i] != null && heap_arr[2*i+1] != null ) {
                if ((Integer)heap_arr[i] > Math.min((Integer)heap_arr[2*i], (Integer)heap_arr[2*i+1])) {
                    if ( (Integer)heap_arr[2*i] < (Integer)heap_arr[2*i+1] ) {
                        swap(i, 2*i);
                        i = 2*i;
                    }
                    else {
                        swap(i, 2*i+1);
                        i = 2*i + 1;
                    }
                    
                }
                else break;
            }
            
            else if(heap_arr[2*i] != null && heap_arr[2*i+1] == null) {
                if ( (Integer)heap_arr[2*i] < (Integer)heap_arr[i] ) {
                    swap(i, 2*i);
                    i = 2*i;
                }
                else break;
            }
            
            else break;

        }
    }


    public void deleteMin(){
        heap_arr[1] = heap_arr[heap_size];
        heap_arr[heap_size] = null;
        heap_size--;
        percolateDown();
    }
}
