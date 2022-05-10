# Billing-Counter-Simulation
A Java program for event based simulation of the billing counter of a burger restaurant (McMohan's Burger)

## Goal:
We want to develope a simulation system for a big and famous burger restaurant called McMahon's Burgers, which gets a lot of customers leading to long queues in billing as well as food preparation. We would like to know some statistics like average waiting time, average queue length etc., so that proper steps to improve customer convenience can be taken.

![image](https://user-images.githubusercontent.com/66300465/167696056-a5700888-3859-4166-b6c7-d32e1e31e21b.png)

## Description
 - Customers arrive randomly and are automatically assigned contiguous integer ids, starting from 1
 - A new customer always joins the billing queue with the smallest length at that time (If there are multiple billing queues with the same
smallest lengths, then the lowest numbered queue of those is chosen by the customer)
- If two customers arrive at the same time, one of them joins the queue first, then the next one joins (We need to decide whom to add first)
- The billing specialist on the billing counter k will take k units of time in completing the order
- After the order is completed, the customer order is printed automatically and sent to the chef, who prepares the burgers in the sequence he receives them
- If two orders arrive simultaneously then the chef chooses the order from the higher numbered billing queue first
- The chef has a large griddle on which at most M burger patties can be cooked simultaneously
- Each burger patty gets cooked in exactly 10 units of time
- Whenever a patty is cooked another patty can start cooking in that instant
- Upon cooking, the burger is delivered to the customer in 1 unit of time
- Whenever a customer gets all their burgers, they leave the restaurant instantaneously (it’s a take-away restaurant with no dine-in)

## What to do
Our goal is to simulate this whole process. The simulation has to be driven by events. Events in the simulation environment are arrival/departure of a customer, completion of payment for an order, completion of one or more burgers, putting burgers on the griddle, etc. For each customer, we've to maintain their state: waiting in the queue, waiting for food, or have already left the building. We will also have to maintain a global clock, which will move forward after all events of a given time point are simulated. We can assume time as discrete integers starting at 0.

If there are multiple events happening at the same time instant, the events are executed in the following order:
1. Billing specialist prints an order and sends it to the chef; the customer leaves the queue.
2. A cooked patty/patties for a customer is/are removed from the griddle.
3. The chef puts another patty/patties for a customer on the griddle.
4. A newly arrived customer joins a queue.
5. Cooked burgers are delivered to customers and they leave

## Implementatiom
Using heaps, queues and trees we've implemented a simulation model with the following operations:
 - **public boolean isEmpty();** *Returns true if there is no further events to simulate*
 - **public void setK(int k);** *The number of billing queues in the restaurant is k. This will remain constant for the whole simulation. Would be called only once*
 - **public void setM(int m);** *At most m burgers can be cooked in the griddle at a given time. This will remain constant for the whole simulation. Would be called only once*
 - **public void advanceTime(int t);** *Run the simulation forward simulating all events until (and including) time t.*
 - **public void arriveCustomer(int id, int t, int numb);** *A customer with ID=id arrives at time t. They want to order numb number of burgers. Note that an id will not be repeated in a simulation. Time cannot be lower than the time mentioned in the previous command. Numb must be positive. IDs are consecutive*
 - **public int customerState(int id, int t);** *Print the state of the customer id at time t. Output 0 if customer has not arrived until time t. Output the queue number k (between 1 to K) if a customer is waiting in the kth billing queue. Output K+1 if the customer is waiting for food. Output K+2 if the customer has received their order by time t. Note that time cannot be lower than the time mentioned in the previous command*
 - **public int griddleState(int t);** *Print the number of burger patties on the griddle at time t. Note that t cannot be lower than the time mentioned in the previous command*
 - **public int griddleWait(int t);** *Print the number of burger patties waiting to be cooked at time t, i.e., number of burgers for which order has been placed but cooking hasn’t started. Note that t cannot be lower than the time mentioned in the previous command*
 - **public int customerWaitTime(int id);** *Print the total wait time of customer id from arriving at the restaurant to getting the food. These queries will be made at the end of the simulation*
 - **public float avgWaitTime();** *Returns the average wait time per customer after the simulation completes. This query will be made at the end of the simulation*

 ## Note
 - Anything has not been imported from *java.utils*. Do NOT copy any part of the code at all anywhere
 - The code has been written completely by me and I have its copyright ownership
 - This code is a solution of assignment given in a IITD Course, COL106 ([Course Webpage](https://www.cse.iitd.ac.in/~parags/teaching/col106)). I've uploaded it here, only for being used as a help by those who're not registered in the course, if they get stuck somewhere.
