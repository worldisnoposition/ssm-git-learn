package compare;

public class TestBean1 {
    @Key
    private String id;
    @Comparable
    private String hello;
    @Comparable
    private Integer testInt;

    public TestBean1(String id, String hello, Integer testInt) {
        this.id = id;
        this.hello = hello;
        this.testInt = testInt;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public Integer getTestInt() {
        return testInt;
    }

    public void setTestInt(Integer testInt) {
        this.testInt = testInt;
    }
}
