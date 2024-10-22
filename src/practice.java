import java.lang.reflect.Method;
import java.lang.reflect.Field;
public class practice {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        Class<?> clazz = person.getClass();

        // 获取字段对象
        Field field = clazz.getDeclaredField("name");

        // 设置为可访问（因为字段是 private 的）
        field.setAccessible(true);


        // 获取字段的值
        String name = (String) field.get(person);
        System.out.println("Before: " + name); // 输出：Before: John

        // 修改字段的值
        field.set(person, "Alice");
        System.out.println("After: " + field.get(person)); // 输出：After: Alice
    }
}
class Person {
    private String name = "John";
}
class Calculator {
    private int add(int a, int b) {
        return a + b;
    }
}
