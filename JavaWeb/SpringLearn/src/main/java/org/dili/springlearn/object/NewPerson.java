package org.dili.springlearn.object;

import javax.annotation.Resource;

public class NewPerson {
    private String username;
    // @Autowired
    // @Qualifier("cat1")
    // private Cat cat;
    // @Autowired
    // @Qualifier("dog1")
    // private Dog dog;

    @Resource(name="cat1")
    private Cat cat;
    @Resource
    private  Dog dog;

    public NewPerson(String username, Cat cat, Dog dog) {
        this.username = username;
        this.cat = cat;
        this.dog = dog;
    }

    public NewPerson() {

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
