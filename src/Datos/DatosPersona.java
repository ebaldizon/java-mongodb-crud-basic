/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import conexionmongodb.Persona;

/**
 *
 * @author Emanuel
 */
public class DatosPersona {
    ServerMongo SM; 
    
    public DatosPersona()
    {
     SM = new ServerMongo();
     if(SM.getEstado() == false)
     {
         SM.conectar();
         SM.setCollection("personas");
         
     }
    }
    
    public BasicDBObject createSchema(Persona p)
    {
        BasicDBObject document = new BasicDBObject();
        
        document.put("id", p.getId());
        document.put("nombre", p.getNombre());
        document.put("paterno", p.getPaterno());
        document.put("materno", p.getMaterno());
        document.put("telefono",p.getTelefono());
        document.put("correo",p.getCorreo());
        document.put("direccion",p.getDireccion());
        return document;
    }
    
     public void create(Persona p)
    {
        BasicDBObject document = new BasicDBObject();
        document = createSchema(p);
        SM.getCollection().insert(document);
    }
     
     public String read()
    {
        String busqueda = "";
        
        DBCursor cursor = SM.getCollection().find();
        while(cursor.hasNext())
        {
            busqueda = busqueda+"\n"+cursor.next();
        }
        return busqueda;
    }
    
    public void update(Persona oldP, Persona newP)
    {   BasicDBObject documentSearch = new BasicDBObject();
        documentSearch.append("id", oldP.getId());
        
        BasicDBObject newDocument =  new BasicDBObject();
        newDocument.put("id", newP.getId());
        newDocument.put("nombre", newP.getNombre());
        newDocument.put("paterno", newP.getPaterno());
        newDocument.put("materno", newP.getMaterno());
        newDocument.put("telefono",newP.getTelefono());
        newDocument.put("correo",newP.getCorreo());
        newDocument.put("direccion",newP.getDireccion());
      
        SM.getCollection().update(documentSearch, newDocument);
    }
    
    public void delete(Persona p)
    {
        BasicDBObject document= new BasicDBObject();
        document.append("id", p.getId());
        SM.getCollection().remove(document);
    }

    
}
