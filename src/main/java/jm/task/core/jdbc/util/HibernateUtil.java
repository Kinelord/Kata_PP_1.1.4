package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


// Специальный Java class для инициализации Hibernate
public class HibernateUtil {
    // Фабрика для создания сессии
    private static SessionFactory sessionFactory;

/*    private static SessionFactory sessionFactory = initSessionFactory();

// initSessionFactory() вызывается на этапе компиляции, статичная инициализация
private static SessionFactory initSessionFactory() {
    if(sessionFactory == null){
        try {
            System.out.println("Метод initSessionFactory в работе");
            return new Configuration()
                    .configure(new File("src\\main\\resources\\hibernate.cfg.xml"))
                    .buildSessionFactory();
        } catch (Throwable tr) {
            System.err.println("Initial SessionFactory creation failed." + tr);
            throw new ExceptionInInitializerError(tr);
        }
    } else {
        return sessionFactory;
    }
}

// Этот метод вызываем, когда требуется SessionFactory
public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
        initSessionFactory();
    }
    return sessionFactory;
}*/
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
/*                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml").build();

Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();

sessionFactory = metadata.getSessionFactoryBuilder().build();*/

            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

    // Закрыть все соединения с помощью SessionFactory
    public static void close() {
        getSessionFactory().close();
    }


}
