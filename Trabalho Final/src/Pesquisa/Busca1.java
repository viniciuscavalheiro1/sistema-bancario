package Pesquisa;

import BancoConexao.Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Busca1 {
    private int numConta;

    public Busca1(int numConta){
        this.numConta = numConta;
    }
    
    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }
    
    
    public void imprimirContas(){
        Banco Conta = new Banco();
        ResultSet resultSet = null;
        Statement statement = null;
        
        Conta.conectar();
        //String query = "SELECT * FROM Contas where NumeroConta = ?";
        String query = "SELECT * FROM Contas";
        
        statement = Conta.criarStatement();
        
        try{
            resultSet = statement.executeQuery(query);
            
            System.out.println("CONTAS CADASTRADAS");
            
            while(resultSet.next()){
                System.out.println("___________________________________");
                System.out.println(resultSet.getInt("NumeroConta"));
                System.out.println(resultSet.getString("Nome"));
                System.out.println("___________________________________");
            }
        }catch(SQLException e){
            System.out.println("Erro " + e);
        }finally{
            try{
                resultSet.close();
                statement.close();
                Conta.desconectar();
            }catch(SQLException ex){
                System.out.println("Erro no fechamento do banco " + ex);
            }
        }
        
    }
    
    public void imprimirContaEspecifica(){
        Banco Conta = new Banco();
        Conta.conectar();
        
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        
        String sql = "SELECT * FROM Contas WHERE NumeroConta = ?";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setInt(1, getNumConta());
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                System.out.println("Pessoa encontrada!");
                System.out.println("______________________________________________");
                System.out.println("\n Nome - " + resultSet.getString("Nome"));
                if(resultSet.getFloat("Tipo") == 1){
                    System.out.println(" Tipo - Conta Corrente");
                }else if(resultSet.getFloat("Tipo") == 2){
                    System.out.println(" Tipo - Conta Poupanca");
                }else if(resultSet.getFloat("Tipo") == 3){
                    System.out.println(" Tipo - Conta Corrente/Poupanca");
                }else if(resultSet.getFloat("Tipo") == 4){
                    System.out.println(" Tipo - Conta Salario");
                }else if(resultSet.getFloat("Tipo") == 5){
                    System.out.println(" Tipo - Conta Corrente/Salario");
                }
                System.out.println("______________________________________________");
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                resultSet.close();
                preparedStatement.close();
                Conta.desconectar();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
    }
    
 
}
