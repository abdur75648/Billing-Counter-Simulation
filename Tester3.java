// Just to checker Exception cases

public class Tester3 {
    static void pr(String n)
    {
        System.out.println(n);
    }
    public static void main(String[] args){
        
        MMBurgers mm = new MMBurgers();
        pr("\n--Started simulation Tester1--");

        // Set number of counters and griddle capacity
        try{
            mm.setK(3);
            mm.setM(6);
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        try{
            mm.setK(4);
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        try{
            mm.setM(5);
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 0
        pr("time t=0 before arrival of customers");
        try{
            // Query customer state
            pr(""+mm.customerState(2, 1)); // 0
            // Query griddle state
            pr(""+mm.griddleState(1)); // 0
            pr(""+mm.griddleWait(1));  // 0
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        try {
            System.out.println("\nt = 10\n");
            mm.advanceTime(10);
        }

        catch (IllegalNumberException e) {
            System.out.println(e);
        }

        // t = 1
        pr("time t=1 [Back in time]");
        try{
            // Customer 1 coudln't arrive in time
            mm.arriveCustomer(1, 0, 3);
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 10
        pr("time t=10");
        try{
            // Customer 1 arrives at 10
            mm.arriveCustomer(1, 10, 3);
            // Customer 2 arrives at 11
            mm.arriveCustomer(2, 11, 4);
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 1
        pr("time t=1");
        try{
            // Query customer state
            pr(""+mm.customerState(2, 1));
            // Query griddle state
            pr(""+mm.griddleState(1));
            pr(""+mm.griddleWait(1)); 
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 2
        pr("time t=2");
        try{
            // Query griddle state
            pr(""+mm.griddleState(2));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 3
        pr("time t=3");
        try{
            // Query customer state
            pr(""+mm.customerState(1, 3));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 7
        pr("time t=7");
        try{
            // Query customer state
            pr(""+mm.customerState(2, 7));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 10
        pr("time t=10");
        try{
            // Query griddle wait time
            pr(""+mm.griddleWait(10));
            System.out.println("Customer Arrived!");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 14
        pr("time t=14");
        try{
            // Query griddle state
            pr(""+mm.griddleState(14));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 30
        pr("time t=30");
        try{
            // Query griddle state
            pr(""+mm.griddleState(30)); // 1
            pr(""+mm.isEmpty()); // False
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 31
        pr("time t=31");
        try{
            // Advance time
            mm.advanceTime(31);
            pr(""+mm.isEmpty()); // True
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // End of simulation
        pr("\n--End of simulation--");
        

        // Query wait times
        try{
            System.out.println(mm.customerWaitTime(1)); // 12
            System.out.println(mm.avgWaitTime()); // 12.0
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

    }
}
