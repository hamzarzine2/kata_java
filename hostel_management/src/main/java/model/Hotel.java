package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Hotels")
public class Hotel {
    //change id to uuid or auto id ?
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String adress;


    public Hotel(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.adress = builder.adress;
    }

    public Hotel() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public static class Builder {
        private int id;
        private String name;
        private String adress;


        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder adress(String adress) {
            this.adress = adress;
            return this;
        }

        public Hotel build() {
            return new Hotel(this);
        }

    }

}
