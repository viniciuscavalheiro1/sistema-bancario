package Cambio;

import BancoConexao.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SistemaCambio {
    private int numeroConta;
    private float saldo;
    private double dolar = 3.72;
    private double yen = 0.033;
    private double euro = 4.39;
    private double cambio; 
    private float sacar;

    public float getSacar() {
        return sacar;
    }

    public void setSacar(float sacar) {
        this.sacar = sacar;
    }

    public SistemaCambio(int numeroConta, float sacar) {
        this.numeroConta = numeroConta;
        this.sacar = sacar;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public double getDolar() {
        return dolar;
    }

    public void setDolar(double dolar) {
        this.dolar = dolar;
    }

    public double getYen() {
        return yen;
    }

    public void setYen(double yen) {
        this.yen = yen;
    }

    public double getEuro() {
        return euro;
    }

    public void setEuro(double euro) {
        this.euro = euro;
    }
    
    public void verificaSaldo(){
        Banco Conta = new Banco();
        Conta.conectar();
        
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        
        String sql = "SELECT * FROM Contas WHERE NumeroConta = ?";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setInt(1, getNumeroConta());
            
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
            preparedStatement.setInt(2, getNumeroConta());
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
    
    
    public void realizaCambio(){
        Scanner sc = new Scanner(System.in);
        verificaSaldo();
        sacaSaldo();
        int op = 0;
        if(getSaldo() >= getSacar()){
            sacaSaldo();
            System.out.println("Troca por qual moeda?");
            System.out.println(" 1 - Dolar ");
            System.out.println(" 2 - Yen   ");
            System.out.println(" 3 - Euro  ");
            System.out.println("OPCÃO: ");
            try{    
                op = sc.nextInt();
            }catch(Exception e){
                System.out.println("Informe um ID(numero) valido!");
            }
            switch(op){
                case 1:
                    setCambio(getSacar()/getDolar());
                    System.out.printf("A quantidade de Dolar é %.2f\n", getCambio());
                    break;
                case 2:
                    setCambio(getSacar()/getYen());
                    System.out.printf("A quantidade de Yen é %.2f\n", getCambio());
                    break;
                case 3:
                    setCambio(getSacar()/getEuro());
                    System.out.printf("A quantidade de Euro é %.2f\n", getCambio());
                    break;
                default:
                    break;
   
            }
                   
        }
    }
    
    
    
}
