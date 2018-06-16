package Transferencias;

import BancoConexao.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Depositar {
    private int numCont;
    private float Deposito;
    private float saldoAtual;

    public float getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(float saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public Depositar(int numCont, float Deposito) {
        this.numCont = numCont;
        this.Deposito = Deposito;
    }

    public int getNumCont() {
        return numCont;
    }

    public void setNumCont(int numCont) {
        this.numCont = numCont;
    }

    public float getSaldo() {
        return Deposito;
    }

    public void setSaldo(float Saldo) {
        this.Deposito = Saldo;
    }
       
    public void depositaSaldo(){
        Banco Conta = new Banco();
        Conta.conectar();
        verificaSaldo();
        PreparedStatement preparedStatement = null;
        
        String sql = "UPDATE Contas SET Saldo = ? WHERE NumeroConta = ?";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setFloat(1, (getSaldo()+getSaldoAtual())); 
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
                setSaldoAtual(resultSet.getFloat("Saldo"));
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
