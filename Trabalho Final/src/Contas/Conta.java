package Contas;

import java.util.Scanner;

import BancoConexao.Banco;

public class Conta {
    Scanner n = new Scanner(System.in);
    Banco ContCorrente = new Banco();
    protected int NumConta;
    protected String Nome;
    protected float Saldo;
    protected String senha;
    protected int Tipo;
    
    public Conta(int NumConta,String nome,float Saldo,String senha,int tipo){
    	this.NumConta = NumConta;
    	this.Nome = nome;
    	this.Saldo = Saldo;
    	this.senha = senha;
    	this.Tipo = tipo;
    }
    
    public int getNumConta() {
            return NumConta;
    }
    
    public void setNumConta(int numConta) {
            NumConta = numConta;
    }
    
    public String getNome() {
            return Nome;
    }
    
    public void setNome(String nome) {
            Nome = nome;
    }
    
    public float getSaldo() {
            return Saldo;
    }
    
    public void setSaldo(float saldo) {
            Saldo = saldo;
    }
    
    public String getSenha() {
            return senha;
    }
    
    public void setSenha(String senha) {
            this.senha = senha;
    }
    
    public int getTipo() {
            return Tipo;
    }
    
    public void setTipo(int tipo) {
            Tipo = tipo;
    }
    
}
