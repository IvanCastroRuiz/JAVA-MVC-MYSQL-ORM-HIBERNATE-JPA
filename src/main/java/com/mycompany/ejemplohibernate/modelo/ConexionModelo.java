package com.mycompany.ejemplohibernate.modelo;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionModelo {
    
    private static ConexionModelo conexion;
    private final EntityManagerFactory fabricaConexion;
    
    private ConexionModelo() {
        fabricaConexion = Persistence.createEntityManagerFactory("demo_PU");
    }
    
    public static ConexionModelo getConexion () {
        
        if (conexion == null){
            conexion = new ConexionModelo ();
        }
        
       return conexion; 
    }
    
    public EntityManagerFactory getEntityManagerFactory(){
        return fabricaConexion;
    }
    
    public static String Ver (String value){
        return value;
    }
    
}
