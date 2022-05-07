public class Customer {
    public int ID;
    public int counter;
    public int numb;
    public int state;
    public int arrival_t;
    public int wait_time;
    public int n_b_waiting;
    public int n_b_delivered;

    public Customer(int ID,  int arrival_t, int numb , int counter){
        this.ID = ID;
        this.arrival_t = arrival_t;
        this.wait_time = 0;
        this.numb = numb;
        this.counter = counter;
        this.state = counter;
        n_b_waiting = 0;
        n_b_delivered = 0;
    }
}
