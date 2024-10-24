/*
   The PolyNode class defines a nodes in PolyList double linked list. Each PolyNode 
   represents a single term in a polynomial. It contains fields for the coefficient 
   of a polynomial term, the exponent of a polynomial term, and references to the
   previous and next nodes in the linked list.

   It should not be necessary to modify this class definition.
*/
public class PolyNode {
    public int coefficient;
    public int exponent;
    public PolyNode next;
    public PolyNode previous;
    public int hashcode = this.hashCode();

    /* 
       DO NOT modify this method - the automated marking program uses it to mark
       your submission.
    */
    public boolean equals(PolyNode node) {
        boolean retval = false;

        if (node != null) {
            if ((coefficient == node.coefficient) && (exponent == node.exponent)) {
                retval = true;
            }
        }

        return retval;
    }

    public void reboot() {
        this.next = null;
        this.previous = null;
    }
    public PolyNode(){}

    public PolyNode(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.previous = null;
        this.next = null;
    }
    public PolyNode(int coefficient, int exponent,PolyNode next,PolyNode previous) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.previous = next;
        this.next = previous;
    }

    @Override
    public String toString() {
        return "____PointNode is ____" +
                "\n属性值(coe): " + this.coefficient +
                "\n属性值（exponent）: " + this.exponent +
                "\n当前节点:" + (this.hashCode()) +
                "\n前向节点: " + (this.previous != null ? this.previous.hashCode() : "null") +
                "\n后向节点: " + (this.next != null ? this.next.hashCode() : "null");
    }


}
