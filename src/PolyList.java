/*
   The PolyList class represents a polynomial as a double linked list. This class
   contains the functionality to add terms (nodes) to the polynomial, to remove
   nodes in the list with a coefficient of 0, and to return a string representation
   of the polynomial.
*/
public class PolyList {
    public static final int EMPTY_LIST = -1;
    private PolyNode head;

    /*
       addNode adds a new node in order into the linked list. See the assignment 
       specification for a complete description of how addNode should operate.
    */
    public boolean addNode(PolyNode newNode) {

        if (newNode == null) {
            return false;
        }
        if (this.head == null) {

            System.out.println("链表无头节点，将新节点作为头节点");
            System.out.println("New____PointNode is ____" + newNode);

            head = newNode;
            return true;
        } else {
            System.out.println("New____PointNode is ____" + newNode);

            PolyNode PointNode = this.head;
            int new_exponent = newNode.exponent;
            int new_coe = newNode.coefficient;
            while (true) {

                int cur_exponent = PointNode.exponent;
                int cur_coe = PointNode.coefficient;
                System.out.println(PointNode);

                if (cur_exponent > new_exponent) {
                    if (PointNode.next == null) {
                        System.out.println("节点后为空指针 ");
                        PointNode.next = newNode;
                        newNode.previous = PointNode;
                        return true;
                    }
//                    else if (cur_exponent - new_exponent > 1) {
//                        PointNode.next = newNode;
//                        newNode.previous = PointNode;
//                        PointNode.next.previous = newNode;
//                        return true;
//                    }
                    else {
                        System.out.println("指针下移动");
                        PointNode = PointNode.next;
                    }
                } else if (cur_exponent < new_exponent) {
                    System.out.println("进入前插入");
                    PolyNode last_head = this.head;
                    this.head = newNode;
                    newNode.next = last_head;
                    last_head.previous = this.head;
                    return true;
                } else {
                    System.out.println("错误异常，无法插入");
                    return false;
                }

            }
        }
    }


    /*
       getDegree returns the degree of the polynomial (or -1 if the linked list is
       empty
    */
    public int getDegree() {
        return Integer.MAX_VALUE;
    }


    /*
       getNode returns a reference to the node in the linked list with the specified 
       exponent (or null if there is no such node in the list).
    */
    public PolyNode getNode(int exponent) {
        return new PolyNode();
    }


    /* 
       reduce removes any nodes in the linked list that have a coefficient of 0. See 
       the assignment specification for an example of how reduce operates. 
    */
    public void reduce() {
        head = new PolyNode();
    }


    /*
       You need to complete this method in order to see accurate test output!
       toString returns a string representation of the polynomial in the form:
       ax^n + bx^(n-1) + ... + kx^0.
    */
    public String toString() {
        return "-1";
    }


    /* 
       DO NOT modify this method - the automated marking program uses it to mark
       your submission
    */
    public boolean equals(PolyList poly) {
        boolean retval = false;
        boolean identicalNodes = true;
        PolyNode current = head;
        PolyNode compareNode = null;

        if (poly != null) {
            compareNode = poly.head;

            while ((current != null) && (compareNode != null) && identicalNodes) {
                identicalNodes = false;

                if (current.equals(compareNode)) {
                    identicalNodes = true;
                }

                compareNode = compareNode.next;
                current = current.next;
            }

            if (identicalNodes && (current == null) && (compareNode == null)) {
                retval = true;
            }
        }

        return retval;
    }
}
