package AlteracoesBanco;

import BancoConexao.Banco;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizacaoDados {
    private int numCont;
    private String nome;
    private float Saldo;
    private String senha;
    private int tipo;
    
    public AtualizacaoDados(int numCont, String nome, float Saldo, String senha, int tipo){
        this.nome = nome; 
        this.numCont = numCont;
        this.Saldo = Saldo;
        this.senha = senha;
        this.tipo = tipo;
        
    }

    public int getNumCont() {
        return numCont;
    }

    public void setNumCont(int numCont) {
        this.numCont = numCont;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSaldo() {
        return Saldo;
    }

    public void setSaldo(float Saldo) {
        this.Saldo = Saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
   
    public void atualizaNome(){
        Banco Conta = new Banco();
        Conta.conectar();
        
        PreparedStatement preparedStatement = null;
        
        String sql = "UPDATE Contas SET Nome = ? WHERE NumeroConta = ?";
        
        try{
            
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setString(1, getNome()); 
            preparedStatement.setInt(2, getNumCont());
            preparedStatement.executeUpdate();            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                preparedStatement.close();
                Conta.desconectar();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
    }
    
    public void atualizaSaldo(){
        Banco Conta = new Banco();
        Conta.conectar();
        
        PreparedStatement preparedStatement = null;
        
        String sql = "UPDATE Contas SET Saldo = ? WHERE NumeroConta = ?";
        
        try{
            
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setFloat(1, getSaldo()); 
            preparedStatement.setInt(2, getNumCont());
            preparedStatement.executeUpdate();            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                preparedStatement.close();
                Conta.desconectar();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
    }
    
    public void atualizaSenha(){
        Banco Conta = new Banco();
        Conta.conectar();
        
        PreparedStatement preparedStatement = null;
        
        String sql = "UPDATE Contas SET Senha = ? WHERE NumeroConta = ?";
        
        try{
            
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setString(1, getSenha()); 
            preparedStatement.setInt(2, getNumCont());
            preparedStatement.executeUpdate();            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                preparedStatement.close();
                Conta.desconectar();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
    }
    
    public void atualizaTipo(){
        Banco Conta = new Banco();
        Conta.conectar();
        
        PreparedStatement preparedStatement = null;
        
        String sql = "UPDATE Contas SET Tipo = ? WHERE NumeroConta = ?";
        
        try{
            
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setInt(1, getTipo()); 
            preparedStatement.setInt(2, getNumCont());
            preparedStatement.executeUpdate();            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                preparedStatement.close();
                Conta.desconectar();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
    }
}
