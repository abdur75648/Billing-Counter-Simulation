public class Tester1 {
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
        // t = 0
        pr("time t=0");
        try{
            // Customer 1 arrives
            mm.arriveCustomer(1, 0, 3);
            // Customer 2 arrives
            mm.arriveCustomer(2, 0, 4);
            // Customer 3 arrives
            mm.arriveCustomer(3, 0, 5);
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 1
        pr("time t=1");
        try{
            // Query customer state
            pr(""+mm.customerState(2, 1)); // 2
            // Query griddle state
            pr(""+mm.griddleState(1)); // 3
            pr(""+mm.griddleWait(1));  // 0
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 2
        pr("time t=2");
        try{
            // Query griddle state
            pr(""+mm.griddleState(2)); // 6
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 3
        pr("time t=3");
        try{
            // Query customer state
            pr(""+mm.customerState(1, 3)); // 4
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 7
        pr("time t=7");
        try{
            // Query customer state
            pr(""+mm.customerState(2, 7)); // 4
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 10
        pr("time t=10");
        try{
            // Query griddle wait time
            pr(""+mm.griddleWait(10)); // 6
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 14
        pr("time t=14");
        try{
            // Query griddle state
            pr(""+mm.griddleState(14)); // 6
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 21
        pr("time t=21");
        try{
            // Query griddle state
            pr(""+mm.griddleState(21)); // 3
            pr(""+mm.isEmpty()); // False
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 23
        pr("time t=23");
        try{
            // Advance time
            mm.advanceTime(23);
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
            System.out.println(mm.customerWaitTime(2)); // 22
            System.out.println(mm.customerWaitTime(3)); // 23
            System.out.println(mm.avgWaitTime()); // 19.0
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

    }
}
