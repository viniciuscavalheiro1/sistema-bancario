package Transferencias;

import BancoConexao.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Transfere {
    protected int conta1;
    protected int conta2;
    protected float valorConta1;
    protected float valorConta2;
    
    public Transfere(int conta1, int conta2, float valorConta1, float ValorConta2){
        this.conta1 = conta1;
        this.conta2 = conta2;
        this.valorConta1 = valorConta1;
        this.valorConta2 = ValorConta2;
    }

   
    public int getConta1() {
        return conta1;
    }

    
    public void setConta1(int conta1) {
        this.conta1 = conta1;
    }

   
    public int getConta2() {
        return conta2;
    }

   
    public void setConta2(int conta2) {
        this.conta2 = conta2;
    }

    
    public float getValorConta1() {
        return valorConta1;
    }

    
    public void setValorConta1(float valorConta1) {
        this.valorConta1 = valorConta1;
    }

    public float getValorConta2() {
        return valorConta2;
    }

    public void setValorConta2(float valorConta2) {
        this.valorConta2 = valorConta2;
    }
    
    public void Transf(){
        Scanner sc = new Scanner(System.in);
         float t = 0;
         System.out.println("Valor que deseja transferir");
         try{
            t= sc.nextFloat();
         }catch(Exception erro){
             System.out.println("O valor é dado em numeros!");
         }
         if(t <= getValorConta1()){
             Transferencia(t);
         }else{
             System.out.println("Saldo Insuficiente");
         }
    }
    
    public void Transferencia(float t){
        float f = 0;
        Scanner sc = new Scanner(System.in);
        Banco Conta = new Banco();
        Conta.conectar();
        
         f = t;
         t = f + getValorConta2();
        PreparedStatement preparedStatement = null;
        
        String sql = "UPDATE Contas SET Saldo = ? WHERE NumeroConta = ?";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setFloat(1, t);
            preparedStatement.setInt(2, getConta2());
            
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
        outra(f);
    }
     public void outra(float f){
         Banco Conta = new Banco();
        Conta.conectar();
        
        f = getValorConta1() - f;
        PreparedStatement preparedStatement = null;
        
        String sql = "UPDATE Contas SET Saldo = ? WHERE NumeroConta = ?";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setFloat(1, f);
            preparedStatement.setInt(2, getConta1());
            
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
