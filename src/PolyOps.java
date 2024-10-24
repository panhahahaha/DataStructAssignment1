import java.util.ArrayList;
import java.util.Iterator;
/*
   The PolyOps class defines methods for adding, subtracting and multiplying 
   polynomials.
*/
public class PolyOps {
    /*
       addPolys adds two polynomials and returns the result. If either of the method 
       arguments are invalid then addPolys throws an IllegalArgumentException. 
    */
    public PolyList addPolys(PolyList poly1, PolyList poly2) throws IllegalArgumentException {
//        if(poly1._is_empty()) throw new IllegalArgumentException();
//        if(poly2._is_empty()) throw new IllegalArgumentException();
        //使用上面两句话会出现空指针异常。
        if (poly1 == null || poly2 == null) {
            throw new IllegalArgumentException();
        }
        if (this.hasNegativeExponents(poly1) || this.hasNegativeExponents(poly2)) throw new IllegalArgumentException();
        return this.addPolys_d(poly1, poly2);
    }


    /*
       subtractPolys subtracts two polynomials and returns the result. If either
       of the method arguments are invalid then subtractPolys throws an
       IllegalArgumentException.
    */
    public PolyList subtractPolys(PolyList poly1, PolyList poly2) throws IllegalArgumentException {
        if (poly1 == null || poly2 == null) {
            throw new IllegalArgumentException();
        }
        if (this.hasNegativeExponents(poly1) || this.hasNegativeExponents(poly2)) {
            throw new IllegalArgumentException();
        }
        return this.subPolys_d(poly1,poly2);

    }


    /*
       multiplyPolys multiplies two polynomials and returns the result. If either
       of the method arguments are invalid then multiplyPolys throws an 
       IllegalArgumentException. 
    */
    public PolyList multiplyPolys(PolyList poly1, PolyList poly2) throws IllegalArgumentException {
        if (poly1 == null || poly2 == null) {
            throw new IllegalArgumentException();
        }
        if (this.hasNegativeExponents(poly1) || this.hasNegativeExponents(poly2)) throw new IllegalArgumentException();
        ArrayList<PolyList> lst = new ArrayList<>();
        PolyNode head_poly1 = poly1.head;
        System.out.println(this.Show_ops("乘法操作",poly1,poly2));
        while(head_poly1!=null){
            lst.add(this.multiple(head_poly1,poly2));
            head_poly1 = head_poly1.next;
        }
        System.out.println();
        Iterator<PolyList> iterator = lst.iterator();
        PolyList sum_poly = iterator.next();
        while(iterator.hasNext()){
            sum_poly = this.addPolys(sum_poly,iterator.next());

        }
        return sum_poly;


    }

    private PolyList addPolys_d(PolyList poly1, PolyList poly2) {
        System.out.println("\n\n\n相加前两个链表展示 \nNumber One:");
        poly1.reduce();
        poly1.ShowLink();
        System.out.println("\nNumber two:");
        poly2.ShowLink();
        PolyNode head1 = poly1.head;
        PolyNode head2 = poly2.head;
        while (head1 != null) {
            int head1_exp = head1.exponent;
            System.out.println("Head1_exp:" + head1_exp);
            if (poly2._recursive_exp(head1_exp) == null) {
                //说明不互相存在，插入
                PolyNode node = poly1._recursive_exp(head1_exp);
                PolyNode newNode = new PolyNode(head1.coefficient, head1.exponent);
//                System.out.printf("%s,%s,%s,",node,newNode,head1);
//                node.reboot();
//                System.out.println("if node"+node);
                poly2.addNode(newNode);

            } else {
                //合并相加
                System.out.println("\n\n###执行合并相加###\n\n");
                PolyNode node1 = poly2._recursive_exp(head1_exp);
                System.out.println("node1" + node1);
                System.out.println("head1" + head1);
                int num = poly2._recursive_exp(head1_exp).coefficient;
                System.out.println("num is " + num);
                System.out.println("head1.coefficient is " + head1.coefficient);
                poly2._recursive_exp(head1_exp).coefficient += head1.coefficient;
            }
            head1 = head1.next;

        }
        poly2.ShowLink();
        return poly2;
    }

    private PolyList subPolys_d(PolyList poly1, PolyList poly2){
        /*大致逻辑：poly1-poly2,找到共同存在的次数进行合并，不共同存在的进行添加*/
        System.out.println(this.Show_ops("减法操作",poly1,poly2));
        PolyNode head1 = poly1.head;
        PolyNode head2 = poly2.head;
        while(head2!=null){
            int coe_2 = head2.coefficient;
            int exp_2 = head2.exponent;
            //find the exp whether in the poly2
            if (poly1._recursive_exp(exp_2)==null){
                // the node not in the poly1,poly2 not change that,poly1 need!
                PolyNode new_node = new PolyNode(-head2.coefficient,head2.exponent);//Opposite the coefficient
                poly1.addNode(new_node);
            }
            else{
                PolyNode merge_node = poly1._recursive_exp(exp_2);
                int coe_1 = merge_node.coefficient;
                int exp_1 = merge_node.exponent;
                merge_node.coefficient-=coe_2;//merge the coefficient



            }
            head2 = head2.next;
        }
        poly1.reduce();
        System.out.println("\n\n\n更新完毕"+poly1);
        return poly1;
    }
    private PolyList multiple(PolyNode node, PolyList poly){

        PolyNode head = poly.head;
        int factor_coe = node.coefficient;
        int factor_exp = node.exponent;
        PolyList new_poly = new PolyList();
        while(head!=null){
            int cur_coe = head.coefficient;
            int cur_exp = head.exponent;
            int multi_coe = cur_coe*factor_coe;
            int multi_exp = cur_exp+factor_exp;
            PolyNode multi_node = new PolyNode(multi_coe,multi_exp);
            new_poly.addNode(multi_node);
            head = head.next;
        }
        return new_poly;

    }

    public boolean hasNegativeExponents(PolyList poly) {
        PolyNode current = poly.head;
        while (current != null) {
            if (current.exponent < 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public String Show_ops(String str,PolyList poly1,PolyList poly2){
        StringBuilder sb = new StringBuilder(String.format("\n\n\n###进行%s操作###\n\n\n",str));
        sb.append("Poly1:").append(poly1.toString()).append("\n\n\n");
        sb.append("Poly2:").append(poly2.toString()).append("\n\n\n");
        return sb.toString();

    }
}
