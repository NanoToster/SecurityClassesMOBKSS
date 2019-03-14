package ru.vsu.domains;

public class SecurityGroup {
    private int id;
    private String name;
    private int howMuchPlusWeNeed;
    private int conformity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowMuchPlusWeNeed() {
        return howMuchPlusWeNeed;
    }

    public void setHowMuchPlusWeNeed(int howMuchPlusWeNeed) {
        this.howMuchPlusWeNeed = howMuchPlusWeNeed;
    }

    public int getConformity() {
        return conformity;
    }

    public void setConformity(int conformity) {
        this.conformity = conformity;
    }

    public void incConformity(){
        this.conformity++;
    }

    public SecurityGroup(int id, String name, int howMuchPlusWeNeed) {
        this.id = id;
        this.name = name;
        this.howMuchPlusWeNeed = howMuchPlusWeNeed;
        this.conformity = 0;
    }
}
