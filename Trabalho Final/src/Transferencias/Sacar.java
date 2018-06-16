package Transferencias;

import BancoConexao.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sacar {
    private int numCont;
    private float sacar;
    private float saldo;

    public Sacar(int numCont, float sacar) {
        this.numCont = numCont;
        this.sacar = sacar;
    }
    
    public float getSacar() {
        return sacar;
    }

    public void setSacar(float sacar) {
        this.sacar = sacar;
    }
            
    public int getNumCont() {
        return numCont;
    }

    public void setNumCont(int numCont) {
        this.numCont = numCont;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public void sacarDinheiro(){
        verificaSaldo();
        if(getSacar() > getSaldo()){
            System.out.println("Saldo insuficiente, saque não realizado!");
        }else{
            sacaSaldo();
            verificaSaldo();
            System.out.println("Saldo " + getSaldo());
        }
    }
    
    public void verificaSaldo(){
        Banco Conta = new Banco();
        Conta.conectar();
        
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        
        String sql = "SELECT * FROM Contas WHERE NumeroConta = ?";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setInt(1, getNumCont());
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                setSaldo(resultSet.getFloat("Saldo"));
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
    
    public void sacaSaldo(){
        Banco Conta = new Banco();
        Conta.conectar();
        float newSaldo = getSaldo()-getSacar();
        
        PreparedStatement preparedStatement = null;
        
        String sql = "UPDATE Contas SET Saldo = ? WHERE NumeroConta = ?";
        
        try{
            
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setFloat(1, newSaldo); 
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
