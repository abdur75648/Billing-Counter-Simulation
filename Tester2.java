
public class Tester2 {
    static void pr(String n)
    {
        System.out.println(n);
    }
    public static void main(String[] args){
        MMBurgers mm = new MMBurgers();
        System.out.println("\n--Started simulation Tester2--");

        // Set number of counters and griddle capacity
        try{
            mm.setK(2);
            mm.setM(8);
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }
        // t = 0
        pr("time t=0");
        try{
            // Customer 1 arrives
            mm.arriveCustomer(1, 0, 5);
            // Customer 2 arrives
            mm.arriveCustomer(2, 0, 6);
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 1
        pr("time t=1");

        try{
            // Customer 3 arrives
            mm.arriveCustomer(3, 1, 4);
            // Customer 4 arrives
            mm.arriveCustomer(4, 1, 5);
            // Query customer state
            System.out.println(mm.customerState(3, 1));
            // Query customer state
            System.out.println(mm.customerState(4, 1));
            // Query griddle state
            System.out.println(mm.griddleState(1));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 2
        pr("time t= 2");
        try{
            // Query griddle state
            pr(""+mm.griddleWait(2));
        
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 3
        pr("time t= 3");
        try{
            // Query griddle state
            pr(""+mm.griddleWait(3));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 4
        pr("time t = 4");
        try{
            // Query griddle state
            pr(""+mm.griddleWait(3));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }


        // t = 5
        pr("time t=5");
        try{
            // Query customer state
            System.out.println(mm.customerState(1, 5));
            // Query griddle state
            pr(""+mm.griddleState(5));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 6
        pr("time t=6");
        
        try{
            // Query customer state
            pr(""+mm.customerState(2, 6));
            // Query griddle state
            pr(""+mm.griddleWait(6));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 10
        pr("time t = 10");
        try{
            // Query griddle state
            pr(""+mm.griddleWait(10));
            pr(""+mm.griddleState(10));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 11
        pr("time t = 11");
        try{
            // Query griddle state
            pr(""+mm.griddleWait(11));
            pr(""+mm.griddleState(11));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 12
        pr("time t=12");
        try{
            // Query griddle state
            pr(""+mm.griddleWait(12));
            pr(""+mm.griddleState(12));
            //pr(""+mm.griddleState(11));
            pr(""+mm.customerWaitTime(3));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 25
        pr("time t=25");
        try{
            // Query griddle state
            pr(""+mm.griddleState(25));
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 32
        pr("time t=32");
         try{
            // Query griddle state
            mm.advanceTime(32);
            pr(""+mm.isEmpty());
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // End of simulation
        System.out.println("\n--End of simulation--");
        pr(""+mm.isEmpty());

        // Query wait times
        try{
            pr(""+mm.customerWaitTime(1));
            pr(""+mm.customerWaitTime(2));
            pr(""+mm.customerWaitTime(3));
            pr(""+mm.customerWaitTime(4));
            pr(""+mm.avgWaitTime());
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

    }
}
