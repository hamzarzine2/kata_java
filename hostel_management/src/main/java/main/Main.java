package main;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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
