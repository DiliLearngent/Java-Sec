package org.dili.springlearn.object;

public class Person {
    private String username;
    private Cat cat;
    private Dog dog;

    public Person(String username, Cat cat, Dog dog) {
        this.username = username;
        this.cat = cat;
        this.dog = dog;
    }

    public Person() {

    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", cat=" + cat +
                ", dog=" + dog +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
