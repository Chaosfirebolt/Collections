package test.util;

/**
 * Created by ChaosFire on 3.9.2017 г.
 */
public class PersonTest {

    private String name;
    private int age;

    public PersonTest(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    @Override
    public String toString() {
        return this.name + " -> " + this.age;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        this.age = age;
    }
}