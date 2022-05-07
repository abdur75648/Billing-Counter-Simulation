class BinaryNode{
    Customer cstmr;
    int height;
    BinaryNode left;
    BinaryNode right;
    public BinaryNode(Customer data){
        cstmr = data;
        left = null;
        right = null;
        height = 0;
    }
}

/**
 * AVlTree
 */

public class AvlTree {

    BinaryNode root;
    public AvlTree(){
        root = null;
    }

    protected int size(BinaryNode root){
        if (root==null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }
    
    private BinaryNode searchRecursive(BinaryNode root,int cstmrID){
        if (root == null || cstmrID==root.cstmr.ID) {
            return root;
        }
        else if (cstmrID < root.cstmr.ID) {
            return searchRecursive(root.left, cstmrID);
        }
        else {
            return searchRecursive(root.right, cstmrID);
        }
    }

    public Customer search(int cstmrID){
        BinaryNode search_result = searchRecursive(root,cstmrID);
        if (search_result==null) {
            return null;
        }
        else return searchRecursive(root,cstmrID).cstmr;
    }

    // Helper functions
    private int getHeight(BinaryNode root){
        if (root==null) {
            return -1;
        }
        else {
            return root.height;
        }
    }
    private BinaryNode clockwise(BinaryNode root){
        BinaryNode var;
        var = root.left;
        root.left = var.right;
        var.right = root;
        root.height = 1 + Math.max(getHeight(root.left),getHeight(root.right));
        var.height = 1 + Math.max(getHeight(var.left),getHeight(var.right));
        return var;
    }
    private BinaryNode anticlockwise(BinaryNode root){
        BinaryNode var;
        var = root;
        root = var.right;
        var.right = root.left;
        root.left = var;
        var.height = 1 + Math.max(getHeight(var.left),getHeight(var.right));
        root.height = 1 + Math.max(getHeight(root.left),getHeight(root.right));
        return root;
    }

    private BinaryNode insertRecursive(BinaryNode root, Customer cstmr){
        if (root==null) {
            root = new BinaryNode(cstmr);
            return root;
        }
        
        if (root.cstmr.ID > cstmr.ID) root.left = insertRecursive(root.left, cstmr);
        else if (root.cstmr.ID < cstmr.ID) root.right= insertRecursive(root.right, cstmr);
        else return root;;

        root.height = 1 + Math.max(getHeight(root.left),getHeight(root.right));

        ///// Rotations Now /////
        if (getHeight(root.left) - getHeight(root.right) > 1) { // Left - X case
            if (cstmr.ID < root.left.cstmr.ID) { // Left - Left case
                // Clockwise Root
                root = clockwise(root);
            }
            else { // Left - Right case
                // Anti-clockwise Root.left
                root.left = anticlockwise(root.left);
                // Clockwise Root
                root = clockwise(root);
            }
        }
        else if (getHeight(root.right) - getHeight(root.left) > 1){ // Right - X case
            if (cstmr.ID > root.right.cstmr.ID) { // Right - Right case
                // Anti-clockwise Root
                root = anticlockwise(root);
            }
            else { // Right - Left case
                // Clockwise Root.right
                root.right = clockwise(root.right);
                root = anticlockwise(root);
            }
        }

        return root;
    }
    void insertCustomer(Customer cstmr){
        root = insertRecursive(root, cstmr);
    }

    /*
    public void printCustomerDetails(int ID){
        System.out.println("----------");
        Customer cstmr = this.search(ID);
        if (cstmr==null){
            System.out.print("TCustomer NOT found");
        }
        
        System.out.println("ID = " + cstmr.ID);
        System.out.println("Counter = " + cstmr.counter);
        System.out.println("Numb = " + cstmr.numb);
        System.out.println("State = " + cstmr.state);
        System.out.println("Arrival Time = " + cstmr.arrival_t);
        System.out.println("Wait Time = " + cstmr.wait_time);
        System.out.println("Burgers Waiting = " + cstmr.n_b_waiting);
        System.out.println("Burgers Completed = " + cstmr.n_b_delivered);
    }
    */
}
