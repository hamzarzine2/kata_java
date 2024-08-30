package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

public class Main {


    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        Main.setUp();
    }

    private static void setUp() {
        // Create the ServiceRegistry from hibernate.cfg.xml
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // Load settings from hibernate.cfg.xml
                .build();

        SessionFactory sessionFactory = null;
        Dotenv dotenv = Dotenv.load();

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
        configuration.setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"));
        configuration.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.addAnnotatedClass(Hotel.class);

        sessionFactory = configuration.buildSessionFactory();
        try {


            // Open session and begin transaction
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();

                // Persist the Hotel object
                session.persist(new Hotel.Builder().name("test hotel").adress("rue du cochons").build());
                session.persist(new Hotel.Builder().name("hostel cheddar").adress("street").build());

                // Commit transaction
                transaction.commit();
            }

        } catch (Exception e) {
            // Handle exceptions and rollback
            e.printStackTrace();
            if (sessionFactory != null) {
                sessionFactory.close();
            }
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    // Method using JPA (if needed)
    public static void saveHotel(Hotel hotel) {
        // Step 1: Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hotelPU");

        // Step 2: Create EntityManager
        EntityManager em = emf.createEntityManager();

        // Step 3: Begin Transaction
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();  // Start transaction
            em.persist(hotel);    // Persist the hotel object
            transaction.commit(); // Commit transaction
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
        } finally {
            em.close(); // Close EntityManager
            emf.close(); // Close EntityManagerFactory
        }
    }
}
