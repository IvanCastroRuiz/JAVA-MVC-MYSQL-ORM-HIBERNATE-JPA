package com.mycompany.ejemplohibernate.modelo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateConexion {
    
    public SessionFactory conexion(){
        
        SessionFactory sessionFactory = null;
        
        try {
            //Declaración de Servicios de Acceso a Datos//

            // Create Configuration
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Song.class);

            // Create Session Factory
            sessionFactory = configuration.buildSessionFactory();

            // Initialize Session Object
            //session = sessionFactory.openSession();
            
            return sessionFactory;
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return sessionFactory;
        
    }

}
