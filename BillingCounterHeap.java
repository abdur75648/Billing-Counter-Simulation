public class BillingCounterHeap {
    public int heap_size;
    public Counter heap_arr[];

    public BillingCounterHeap() {
        heap_size = 0;
        heap_arr = new Counter[1];
        heap_arr[0] = null;
    }

    public void buildCounterheap(int K){
        heap_size = K;
        heap_arr = new Counter[K+2];
        heap_arr[0] = null;
        for (int i = 1; i < K+1; i++) {
            Counter c = new Counter(i);
            this.heap_arr[i] = c;
        }
        
        for (int i = heap_size/2; i>0; i--) {
            this.percolateDown(i);
        }
    }

    public boolean isEmpty(){
        return this.heap_size == 0;
    }

    public boolean isFull(){
        return this.heap_size == heap_arr.length-1;
    }

    private void doubleSize(){
        Counter[] new_queue_arr = new Counter[2*this.heap_arr.length];
        System.arraycopy(this.heap_arr, 0, new_queue_arr, 0, this.heap_arr.length);
        this.heap_arr = new_queue_arr;
    }

    public Counter getMin() {
        if (this.isEmpty()) {
            return null;
        }
        return heap_arr[1];
    }

    private void swap(int i,int j){
        Counter a = heap_arr[i];
        heap_arr[i] = heap_arr [j];
        heap_arr[j] = a;
    }


    private boolean comparator(Counter a, Counter b){
        if (a.counterQueue.getSize()==b.counterQueue.getSize()) {
            return (a.counterID < b.counterID);
        }
        else return (a.counterQueue.getSize() < b.counterQueue.getSize());
    }

    private Counter minComparator(Counter a, Counter b){
        if (a.counterQueue.getSize()==b.counterQueue.getSize()) {
            if (a.counterID < b.counterID) {
                return a;
            } else {
                return b;
            }
        }
        else if (a.counterQueue.getSize() < b.counterQueue.getSize()) {
            return a;    
        }
        else {
            return b;
        }
    }
    
    public void percolateUp(){
        int i = heap_size;
        while (i>1 && ( comparator(heap_arr[i], heap_arr[i/2]))) {
            swap(i, i/2);
            i = i/2;
        }
    }

    public void insertKey(Counter key){
        if (this.isFull()) {
            this.doubleSize();
        }
        heap_size++;
        heap_arr[heap_size] = key;
        percolateUp();
    }


    public void percolateDown(int V){
        int i = V;
        while ( i <= (heap_size/2) ) {
            if ( heap_arr[2*i] != null && heap_arr[2*i+1] != null ) {
                if ( comparator(minComparator(heap_arr[2*i], heap_arr[2*i + 1]), heap_arr[i])) {
                    if ( comparator(heap_arr[2*i], heap_arr[2*i+1])) {
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
                if ( comparator(heap_arr[2*i], heap_arr[i]) ) {
                    swap(i, 2*i);
                    i = 2*i;
                }
                else break;
            }
            
            else break;

        }
    }

    public void percolateDown(){
        int i = 1;
        while ( i <= (heap_size/2) ) {
            if ( heap_arr[2*i] != null && heap_arr[2*i+1] != null ) {
                if ( comparator(minComparator(heap_arr[2*i], heap_arr[2*i + 1]), heap_arr[i])) {
                    if ( comparator(heap_arr[2*i], heap_arr[2*i+1])) {
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
                if ( comparator(heap_arr[2*i], heap_arr[i]) ) {
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
