/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author CLUB ACRICAIN
 */
public class DB {


     public static Connection connecter(){
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+"Gestion_compagnies_aieriennes","postgres", "postgres");
            if(conn!=null){
                System.out.println("Connection Established");
            }
            else{
                System.out.println("Connection Failed");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    
}
     public void Create(Connection conn ,String name){
         Statement statement;
         try{
            String query = "CREATE TABLE " + name + " (empid SERIAL, login VARCHAR(200), password VARCHAR(200), PRIMARY KEY (empid))";
             statement=conn.createStatement();
             statement.executeUpdate(query);
             System.out.println("table created");
             
         }catch(Exception e){
             System.out.println(e);
             
             
         }
             
     }

}

    

