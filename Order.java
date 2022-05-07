public class Order {
    public int ID;
    public int counterID;
    public int numb;
    public int next_or_prev_event_time;


    // For Orders on Griddle -> Next event time
    // For Orders before Griddle -> Prev event time
    public Order(int ID, int next_or_prev_event_time,int numb, int counterID){
        this.ID = ID;
        this.next_or_prev_event_time = next_or_prev_event_time;
        this.numb = numb;
        this.counterID= counterID;
    }
}