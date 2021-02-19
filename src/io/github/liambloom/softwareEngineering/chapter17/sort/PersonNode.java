package io.github.liambloom.softwareEngineering.chapter17.sort;

public class PersonNode {
    private String lastName;
    private String firstName;
    private String middleName;
    private String idNum;

    public PersonNode(String lastName, String firstName, String middleName, String idNum) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.idNum = idNum;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getIdNum() {
        return idNum;
    }
}
