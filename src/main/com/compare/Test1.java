package compare;

import java.util.Collection;

public class Test1<T> extends AbstractComparable {
    public Test1(){
        super(TestBean1.class);
    }
    @Override
    public void update(Collection toUpdate) {
        System.out.println("2.update");
    }

    @Override
    public void save(Collection toSave) {

    }

}
