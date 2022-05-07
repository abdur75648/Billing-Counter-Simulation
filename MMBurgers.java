public class MMBurgers implements MMBurgersInterface {

    // Main Data Strctures
    BillingCounterHeap countersHeap;
    AvlTree database; // Database of Customers
    // OrdersHeap beforeGriddle; // After Counter, before Griddle
    Queue queueOfOrdersBeforeGriddle; // After Counter, before Griddle
    Queue onGriddle;
    Queue service_queue; // After Griddle, before Delivery

    int K;
    int M;
    int globalTime;
    boolean k_set;
    boolean m_set;
    int waitingNumBurgersBeforeGriddle;
    int numBurgersOnGriddle;
    int totalWaitTimeForSimulation;
	int totalHeadcount;

    public MMBurgers(){
        countersHeap = new BillingCounterHeap();
        database = new AvlTree();
        queueOfOrdersBeforeGriddle = new Queue();
        onGriddle = new Queue();
        service_queue = new Queue();
        globalTime = 0;
        k_set = false;
        m_set = false;
        waitingNumBurgersBeforeGriddle = 0;
        numBurgersOnGriddle = 0;
        totalWaitTimeForSimulation = 0;
        totalHeadcount = 0;
    }

    public boolean isEmpty(){

        for (int i = 1; i < K+1; i++) {
            Counter each_counter = this.countersHeap.heap_arr[i];
            if (each_counter.counterQueue.getSize()!=0) {
                return false;
            }
        }
        

        if(!queueOfOrdersBeforeGriddle.isEmpty()){
            return false;
        }

        if (!onGriddle.isEmpty()) {
            return false;
        }

        if (!onGriddle.isEmpty()) {
            return false;
        }
        return true;
    } 
    
    public void setK(int k) throws IllegalNumberException{
        if (k_set) {
            throw new IllegalNumberException("K can be set only once!");
        }
        if(k<=0){
			throw new IllegalNumberException("Illegal Value For K");
		}
        else{
            k_set = true;
            this.K = k;
            this.countersHeap.buildCounterheap(K);
        }
    }   
    
    public void setM(int m) throws IllegalNumberException{
        if (m_set) {
            throw new IllegalNumberException("M can be set only once!");
        }
        if(m<=0){
			throw new IllegalNumberException("Illegal Value For M");
		}
        else{
            m_set = true;
            this.M = m;
        }	
    }

    public void advanceTime(int t) throws IllegalNumberException{

        
        if (t<=globalTime) {
            throw new IllegalNumberException("Cannot go back in time!");
        }

        while (globalTime<=t) {

            // 1. Billing specialist on some counters print an order and sends it to the chef; the customer leaves the queue
            for (int i = K; i > 0; i--) { // Iterating in reverse order so that higehr counter order gets more priotity if equal time
                Counter each_Counter = this.countersHeap.heap_arr[i];
                // Iterating through all counter queues
                if (each_Counter.counterQueue.getSize()>0) {
                    personsInQueue first_person = (personsInQueue) each_Counter.counterQueue.getFront();
                    if (first_person.exit_t==globalTime) {
                        first_person = (personsInQueue) each_Counter.counterQueue.dequeue();
                        int ID = first_person.ID;
                        Customer the_person_data = database.search(ID);
                        the_person_data.state = K+1;
                        the_person_data.n_b_waiting = the_person_data.numb;
                        Order his_order = new Order(ID, first_person.exit_t, the_person_data.numb, the_person_data.counter);
                        queueOfOrdersBeforeGriddle.enqueue(his_order);
                        //beforeGriddle.insertKey(his_order);
                        waitingNumBurgersBeforeGriddle += the_person_data.numb;
                    }
                }
            }
            for (int i = this.countersHeap.heap_size/2; i>0; i--) {
                this.countersHeap.percolateDown(i);
            }

            // 2. A cooked patty/patties for a customer is/are removed from the griddle.
            while (!onGriddle.isEmpty()) {
                Order best_cooked_order = (Order) onGriddle.getFront();
                if (best_cooked_order.next_or_prev_event_time==globalTime) {
                    best_cooked_order = (Order) onGriddle.dequeue();
                    numBurgersOnGriddle = numBurgersOnGriddle - best_cooked_order.numb;
                    best_cooked_order.next_or_prev_event_time = best_cooked_order.next_or_prev_event_time + 1;
                    service_queue.enqueue(best_cooked_order);
                }
                else break;
            }

            // 3. The chef puts another patty/patties for a customer on the griddle.
            while ((!queueOfOrdersBeforeGriddle.isEmpty()) && (numBurgersOnGriddle!=M)) {
                Order earliest_order = (Order) queueOfOrdersBeforeGriddle.getFront();
                if (earliest_order.numb<=(M-numBurgersOnGriddle)) {
                    earliest_order = (Order) queueOfOrdersBeforeGriddle.dequeue();
                    earliest_order.next_or_prev_event_time = globalTime + 10;
                    numBurgersOnGriddle += earliest_order.numb;
                    waitingNumBurgersBeforeGriddle -= earliest_order.numb;
                    onGriddle.enqueue(earliest_order);
                }
                else { // If earliest_order.numb > (M-numBurgersOnGriddle)
                    earliest_order = (Order) queueOfOrdersBeforeGriddle.getFront();
                    earliest_order.numb -=  M-numBurgersOnGriddle;
                    Order toPutOnGriddle = new Order(earliest_order.ID, globalTime+10, M-numBurgersOnGriddle , earliest_order.counterID);
                    numBurgersOnGriddle += toPutOnGriddle.numb;
                    waitingNumBurgersBeforeGriddle -= toPutOnGriddle.numb;
                    onGriddle.enqueue(toPutOnGriddle);
                }
            }

            // 4. A newly arrived customer joins a queue
            // Implemented before counter using  arriveCustomer(int id, int t, int numb)

            // 5. Cooked burgers are delivered to customers and they leave
            while (!service_queue.isEmpty()) {
                Order finishedOrder = (Order) service_queue.getFront();
                if (finishedOrder.next_or_prev_event_time==globalTime) {
                    finishedOrder = (Order) service_queue.dequeue();
                    Customer receiver = database.search(finishedOrder.ID);
                    receiver.n_b_delivered += finishedOrder.numb;
                    receiver.n_b_waiting -= finishedOrder.numb;
                    if (receiver.n_b_waiting==0) {
                        receiver.state = K+2;
                        receiver.wait_time = globalTime - receiver.arrival_t;
                        totalWaitTimeForSimulation += receiver.wait_time;
                        totalHeadcount ++;
                    }
                }
                else break;
            }

            
            // Do something
            globalTime+=1;
        }
        globalTime-=1;
        
    } 

    public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException{
        if (t<globalTime) {
            throw new IllegalNumberException("Cannot go back in time!");
        }
        if (id<=0) {
            throw new IllegalNumberException("Customer can ID only be a positive integer!");
        }
        if (numb<=0){
            throw new IllegalNumberException("Invalid Order!");
        }

        if (t>globalTime) {
            this.advanceTime(t);
        }
        Counter bestCounter = countersHeap.getMin();
        Customer new_Customer = new Customer(id, t, numb, bestCounter.counterID);
        int exit_t = 0;
        exit_t = this.globalTime + (bestCounter.counterID) * (bestCounter.counterQueue.getSize() + 1);
        personsInQueue personGoingToQueue = new personsInQueue(id, exit_t);
        bestCounter.counterQueue.enqueue(personGoingToQueue);
        countersHeap.percolateDown();
        database.insertCustomer(new_Customer);
    } 

    public int customerState(int id, int t) throws IllegalNumberException{
        if (t<globalTime) {
            throw new IllegalNumberException("Cannot go back in time!");
        }
        Customer theCustomerToBeSearched = database.search(id);
        if (theCustomerToBeSearched==null) {
            return 0;
        }

        if (t>globalTime) {
            this.advanceTime(t);
        }
        return theCustomerToBeSearched.state;
    } 

    public int griddleState(int t) throws IllegalNumberException{
        if (t<globalTime) {
            throw new IllegalNumberException("Cannot go back in time!");
        }

        if (t>globalTime) {
            this.advanceTime(t);
        }

        return numBurgersOnGriddle;
    } 

    public int griddleWait(int t) throws IllegalNumberException{
        if (t<globalTime) {
            throw new IllegalNumberException("Cannot go back in time!");
        }

        if (t>globalTime) {
            this.advanceTime(t);
        }

        return waitingNumBurgersBeforeGriddle;
    } 

    public int customerWaitTime(int id) throws IllegalNumberException{
        Customer theCustomerToBeSearched = database.search(id);
        if (theCustomerToBeSearched==null) {
            throw new IllegalNumberException("This person does NOT exist in our database!");
        }
        if (globalTime>0 && !this.isEmpty()) {
            throw new IllegalNumberException("Not a good time to ask this! Ask at the end of the simulation");
        }
        return theCustomerToBeSearched.wait_time;
    } 

	public float avgWaitTime(){
        /*
        if (globalTime>0 && !this.isEmpty()) {
            throw new IllegalNumberException("Not a good time to ask this! Ask at the end of the simulation");
        }
        */
        return ((float)totalWaitTimeForSimulation)/((float)totalHeadcount);
    } 

    
}
