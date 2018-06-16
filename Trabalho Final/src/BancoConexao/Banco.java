package BancoConexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {
    Connection c = null;
    public boolean conectar(){


    try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:B.db");
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
    return true;
}  
    
    public boolean desconectar(){
    
        
        try{
                    
            c.close();         
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
            return true;    
        }
        
        public Statement criarStatement() {
            
            try{
                return this.c.createStatement();
                        
            } catch(SQLException e){
                return null;
            }
        }
        
        public Connection GetC(){        
            return this.c;
        }
        
        public PreparedStatement criarPreparedStatement(String sql) {
            
            try{
                return this.c.prepareStatement(sql);
                        
            }catch(SQLException e){
            	e.printStackTrace();
                return null;
            }
        }

}
