/**
 * TODO ADD DESCRIPTION
 * Date: 2022/6/21
 * Time: 22:32
 *
 * @author jimas
 */
public class Parent {
    protected String name;

    public Parent() {
        System.out.println("parent constructor" );
        custominit(name);
    }

    public void custominit(String name) {
        System.out.println("name" + name);
    }
}
