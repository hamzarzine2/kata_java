package main;

import model.Hotel;
import utils.HibernateUtils;

public class Main {


    public static void main(String[] args) {
        HibernateUtils.saveHotel(new Hotel.Builder().adress("test").name("test").build());
        HibernateUtils.saveHotel(new Hotel.Builder().adress("test").name("test").build());
        HibernateUtils.saveHotel(new Hotel.Builder().adress("test").name("test").build());
        HibernateUtils.saveHotel(new Hotel.Builder().adress("test").name("test").build());
        HibernateUtils.shutDownSessionFactory();
    }


}
