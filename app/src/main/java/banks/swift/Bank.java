package banks.swift;

/**
 * Created by Pedro on 25/10/2014.
 */
public class Bank {

    private String name;
    private String region;
    private String swift;
    private String branch;

    public Bank(String name, String region, String swift, String branch) {
        this.name = name;
        this.region = region;
        this.swift = swift;
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
