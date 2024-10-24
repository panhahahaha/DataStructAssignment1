/*
   The PolyList class represents a polynomial as a double linked list. This class
   contains the functionality to add terms (nodes) to the polynomial, to remove
   nodes in the list with a coefficient of 0, and to return a string representation
   of the polynomial.
*/
public class PolyList {
    public static final int EMPTY_LIST = -1;
    public PolyNode head;

    /*
       addNode adds a new node in order into the linked list. See the assignment 
       specification for a complete description of how addNode should operate.
    */
    public boolean addNode(PolyNode newNode) {
        System.out.println("\n\n***************************************插入前展示************************************");
        this.ShowLink();

        if (newNode == null) {
            return false;
        }
        if (this.head == null) {

            System.out.println("链表无头节点，将新节点作为头节点");
            System.out.println("New____PointNode is ____" + newNode);

            this.head = newNode;
            return true;
        } else {
            if (newNode.coefficient == 0) return false;
            System.out.println("New____PointNode is ____" + newNode);

            PolyNode PointNode = this.head;
            int new_exponent = newNode.exponent;
            int new_coe = newNode.coefficient;
            if (this.head.exponent < new_exponent) {
                System.out.println("\n\n********插入方式：比头节点大，直接插入到最前********\n\n");
                PolyNode last_head = this.head;
                this.head = newNode;
                newNode.next = last_head;
                last_head.previous = this.head;
                System.out.println("\n\n***************************************插入后展示************************************");
                this.ShowLink();
                return true;
            }
            while (true) {

                int cur_exponent = PointNode.exponent;
                int cur_coe = PointNode.coefficient;
                System.out.println(PointNode);
                if (cur_exponent == new_exponent) {
                    return false;
                }

                if (cur_exponent > new_exponent) {
                    if (PointNode.next == null) {
                        System.out.println("节点后为空指针 ");
                        PointNode.next = newNode;
                        newNode.previous = PointNode;
                        System.out.println("\n\n***************************************插入后展示************************************");
                        this.ShowLink();
                        return true;
                    } else {
                        PolyNode next_point = PointNode.next;
                        int next_coe = next_point.coefficient;
                        int next_exp = next_point.exponent;
                        if (next_exp < newNode.exponent) {
                            System.out.println("\\n\\n********插入方式：比前节点小，比当前节点下一节点大，实现中间插入********\\n\\n\"");
                            PointNode.next = newNode;
                            newNode.next = next_point;
                            newNode.previous = PointNode;
                            next_point.previous = newNode;
                            System.out.println("\n\n***************************************插入后展示************************************");
                            this.ShowLink();
                            return true;
                        } else {
                            System.out.println("指针下移动");
                            PointNode = PointNode.next;
                        }


                    }

                } else {
                    PointNode.coefficient += newNode.coefficient;
                    System.out.println("指数相同，合并相加");
                    System.out.println("\n\n***************************************插入后展示************************************");
                    this.ShowLink();
                    return true;
                }

            }
        }
    }


    /*
       getDegree returns the degree of the polynomial (or -1 if the linked list is
       empty
    */
    public int getDegree() {

        if (this._is_empty()) return -1;
        else return this._recursive_int(1);
//        return Integer.MAX_VALUE;
    }


    /*
       getNode returns a reference to the node in the linked list with the specified 
       exponent (or null if there is no such node in the list).
    */
    public PolyNode getNode(int exponent) {
        if (this._is_empty()) return null;
        else return this._recursive_exp(exponent);
//        return new PolyNode();
    }


    /* 
       reduce removes any nodes in the linked list that have a coefficient of 0. See 
       the assignment specification for an example of how reduce operates. 
    */
    public void reduce() {
//        head = new PolyNode();
        if (this._is_empty()) return;
//        if (this.head.coefficient == 0) {
//            this._DelNode(this.head, 0);
//            return;
//        }
        while (this.head != null && this.head.coefficient == 0) {
            head = head.next; // 更新头节点
            if (head != null) {
                head.previous = null; // 更新头节点的前驱引用
            }
        }
        PolyNode point = this.head;
        while (point != null) {
            if (point.coefficient == 0&&point.exponent<4) this._DelNode(point, 1);
            point = point.next;
        }
    }


    /*
       You need to complete this method in order to see accurate test output!
       toString returns a string representation of the polynomial in the form:
       ax^n + bx^(n-1) + ... + kx^0.
    */
    public String toString() {
//        return "-1";
        PolyNode cur_point = this.head;
        StringBuilder sb1 = new StringBuilder("\n*********************\n双向链表哈希码形式\n\n");
        StringBuilder sb2 = new StringBuilder();
        while (cur_point != null) {
            int hashcode = cur_point.hashCode();
            int coe = cur_point.coefficient;
            int exp = cur_point.exponent;

            sb1.append(cur_point.hashcode).append("->");
            if (cur_point==this.head){
                if (coe>=0){
                    sb2.append("");
                }
                else{
                    sb2.append("-");
                }
            }
            sb2.append(cur_point != this.head ? (coe >= 0 ? " + " : " - ") : "")
                    .append(String.format("%d", Math.abs(coe)))
                    .append("x^")
                    .append(exp);

            cur_point = cur_point.next;
        }
        return sb2.toString();
    }

    public void ShowLink() {
        if (this.head == null) {
            System.out.println("\n\n\n********当前链表为空*********\n\n\n");
            return;
        }
        PolyNode cur_point = this.head;
//        String str;
        StringBuilder sb1 = new StringBuilder("\n*********************\n双向链表哈希码形式\n\n");
        StringBuilder sb2 = new StringBuilder("\n*********************\n双向链表直观形式\n\n");
        while (cur_point != null) {
            int hashcode = cur_point.hashCode();
            int coe = cur_point.coefficient;
            int exp = cur_point.exponent;

            sb1.append(cur_point.hashcode).append("->");
            sb2.append((coe >= 0 ? "" : "")).append(coe).append(" X^ ").append(exp);
            cur_point = cur_point.next;
        }
        sb1.append("\n\n**********************\n\n");
        sb2.append("\n\n**********************\n\n");
        System.out.println(sb1);
        System.out.println(sb2);
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

    protected boolean _is_empty() {
        return (this.head == null);
    }

    private int _recursive_int(int method) {
        PolyNode point = this.head;
        int count = 0;
        int maxdegree = 0;
        while (point != null) {
            if (maxdegree < point.exponent) maxdegree = point.exponent;
            point = point.next;
            count++;

        }
        this.ShowLink();
        return switch (method) {
            case 0 -> count;
            case 1 -> maxdegree;
            default -> -1;


        };


//        return count;
    }


    protected PolyNode _recursive_exp(int exp) {
        PolyNode point = this.head;
        System.out.println(exp);
        while (point != null) {
            if (point.exponent == exp) return point;
            else point = point.next;
        }
        return null;

    }

    private void _DelNode(PolyNode node, int method) {
        switch (method) {
            case 0:
                this.head = node.next;
                node.next.previous = this.head;
                break;
            case 1:
                node.previous.next = (node.next!=null?node.next:null);
//                node.next.previous = (node.next!=null?node.previous:null);
                if (node.next != null) {
                    node.next.previous = node.previous;     // 更新后继节点的 previous
                }
                break;
        }

    }

}
