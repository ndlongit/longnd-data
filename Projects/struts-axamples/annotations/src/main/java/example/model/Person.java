package example.model;

/**
 * Models a Person who registers.
 * 
 * @author bruce phillips
 * 
 */
public class Person {

    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "First Name: " + this.getFirstName() + " Last Name:  " + this.getLastName() + " Email:      " + this.getEmail() + " Age:      "
                + this.getAge();
    }
}
