/**
 * Date: 2022/6/21
 * Time: 22:33
 *
 * @author jimas
 */
public class Son extends Parent {
    public Son() {
        System.out.println("son constructor");
    }

    @Override
    public void custominit(String name) {
        System.out.println("son name" + name);
    }

    public static void main(String[] args) {
        final Son son = new Son();
        System.out.println(son);
    }
}
