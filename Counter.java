public class Counter{
    int counterID;
    Queue counterQueue;
     public Counter(int counterID) {
        this.counterID = counterID;
        this.counterQueue = new Queue();
     }
}