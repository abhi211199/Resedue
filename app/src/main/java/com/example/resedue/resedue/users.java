package com.example.resedue.resedue;

public class users{

    private String name;
    private String address;
    private String mob;
    private String id;
    private String wt;
    private String sell;
    private String ref;
    private String status;



    public users(String name, String address, String mob, String id, String wt, String sell, String ref, String status) {
        this.name = name;
        this.address = address;
        this.mob = mob;
        this.id = id;
        this.wt = wt;
        this.sell = sell;
        this.ref = ref;
        this.status = status;
    }

    public users() {

        }

        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
