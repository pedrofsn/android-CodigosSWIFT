package banks.swift.model;

/**
 * Created by Pedro on 25/10/2014.
 */
public class Bank {

    private String name;
    private String city;
    private String swift;
    private String branch;

    public Bank(String name, String city, String swift, String branch) {
        this.name = name;
        this.city = city;
        this.swift = swift;
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
