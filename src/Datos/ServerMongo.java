/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class ServerMongo {
    private DB db;
    private DBCollection collection;
    private boolean estate = false;
    
    private String address = "localhost";
    private int port = 27017;
    private String nameDB = "myMongoDB";
    private String nameTable = "";
    
    
    public void conectar()
    {
        try {
            Mongo mongodb = new Mongo(this.address, this.port);
            db = mongodb.getDB(this.nameDB);
            //collection = db.getCollection(nameTable);
            this.estate = true;
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        }
    }
    
    public DBCollection getCollection()
    {
        return this.collection;
    }
    
    public boolean setCollection(String nameTable)
    {
        if(getEstado() == true)
        {
            this.collection = db.getCollection(nameTable);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean getEstado()
    {
        return this.estate;
    }
    //Investigar si se puede usar un CRUD solamente con db.getCollection, una vez establecida la conexion.
    
    protected String getAddress()
    {
        return this.address;
    }
    
    protected void setAddress(String address)
    {
        this.address = address;
    }
    
    protected int getPort()
    {
        return this.port;
    }
    
    protected void setPort(int port)
    {
        this.port = port;
    }
    
    protected String getNameDB()
    {
        return this.nameDB;
    }
    
    protected void setNameDB(String nameDB)
    {
        this.nameDB = nameDB;
    }
}
