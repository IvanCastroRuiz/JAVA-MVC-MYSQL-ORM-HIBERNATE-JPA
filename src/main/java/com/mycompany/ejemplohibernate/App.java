package com.mycompany.ejemplohibernate;

// Java Program to Illustrate App File 
import com.mycompany.ejemplohibernate.controlador.SongController;

import com.mycompany.ejemplohibernate.modelo.HibernateConexion;
import com.mycompany.ejemplohibernate.modelo.Song;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


 
// Main class
public class App {
 
    // Main driver method
    public static void main(String[] args) throws Exception
    {
        
        
        
        
        /*ConexionModelo conexion = ConexionModelo.getConexion(); 
        SongController songController = new SongController(conexion.getEntityManagerFactory());
        
          
        
        List<Song> lista = songController.findAll();
        
        lista.stream().forEach((elemento)-> {
            System.out.println("Nombre: " + elemento.getSongName());
        });  */
           

        //Declaración de Servicios de Acceso a Datos//
        
 
        /*// Create Configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Song.class);
 
        // Create Session Factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
 
        // Initialize Session Object
        Session session = sessionFactory.openSession();*/
 
        
        
        HibernateConexion sessionFactory = new HibernateConexion();
        Session session = sessionFactory.conexion().openSession() ;
        System.out.println("Conexion: " + session.getEntityManagerFactory());
        
        /*Song song1 = new Song();
 
        song1.setId(6);
        song1.setSongName("Sun Tzu");
        song1.setArtist("ROK");
 
        session.beginTransaction();
 
        // Here we have used
        // save() method of JPA
        session.save(song1);
 
        session.getTransaction().commit();*/
        
        SongController songController = new SongController(session.getEntityManagerFactory());
        List<Song> lista = songController.findAll();
        
        lista.stream().forEach((elemento)-> {
            System.out.println("songId: " + elemento.getId() + 
                               "\nNombre: " + elemento.getSongName() +
                               "\nArtist: " + elemento.getArtist());
        }); 
    }
}
