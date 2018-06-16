package Emprestimo;

import BancoConexao.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmprestimoConsignado {
    private float salario;
    private int numeroConta;
    private float limiteEmprestimo;
    private int parcelas;
    private float saldoAtual;
    private int tipo;


    public EmprestimoConsignado(float salario, int numeroConta, int tipo) {
        this.salario = salario;
        this.numeroConta = numeroConta;
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public float getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(float saldoAtual) {
        this.saldoAtual = saldoAtual;
    }
    
    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public float getLimiteEmprestimo() {
        return limiteEmprestimo;
    }

    public void setLimiteEmprestimo(float limiteEmprestimo) {
        this.limiteEmprestimo = limiteEmprestimo;
    }
    
    public void calculaCredito(){
        setLimiteEmprestimo(getSalario()*4);
    }
    
    public void parcelas(){
        verificaSaldo();
        calculaCredito();
        Scanner sc = new Scanner(System.in);
        System.out.printf("[1] - 4x %.2f\n", getLimiteEmprestimo()/4);
        System.out.printf("[2] - 5x %.2f\n", getLimiteEmprestimo()/5);
        System.out.printf("[3] - 6x %.2f\n", getLimiteEmprestimo()/6);
        System.out.printf("[4] - 7x %.2f\n", getLimiteEmprestimo()/7);
        System.out.printf("[5] - 8x %.2f\n", getLimiteEmprestimo()/8);
        System.out.printf("[6] - 9x %.2f\n", getLimiteEmprestimo()/9);
        System.out.printf("[7] - 10x %.2f\n", getLimiteEmprestimo()/10);
        System.out.printf("[8] - 11x %.2f\n", getLimiteEmprestimo()/11);
        System.out.printf("[9] - 12x %.2f\n", getLimiteEmprestimo()/12);
        System.out.printf("OPCÃO:");
        int op = sc.nextInt();
        setParcelas(op);
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
    
    public void depositaEmprestimo(){
        Banco Conta = new Banco();
        Conta.conectar();
        verificaSaldo();
        calculaCredito();
        PreparedStatement preparedStatement = null;
        
        String sql = "UPDATE Contas SET Saldo = ? WHERE NumeroConta = ?";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setFloat(1, (getLimiteEmprestimo()+getSaldoAtual())); 
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
    
    public void realizaEmprestimo(){
        verificaSaldo();
        calculaCredito();
        if(getTipo() == 5){
            parcelas();
            depositaEmprestimo();
            System.out.println("Parabéns! Seu emprestimo foi realizado.");
        }else{
            System.err.println("Desculpe! Esse tipo de conta nao pode fazer emprestimos.");
        }
        
    }
}
