package Transferencias;

import BancoConexao.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ValidaTransferencia {
    protected int numCont1;
    protected int numCont2;
    protected float saldoConta1;
    protected float saldoConta2;
    protected int tipo;
    protected int tipoTrans;


    public ValidaTransferencia(int numCont1, int numCont2, int tipo) {
        this.numCont1 = numCont1;
        this.numCont2 = numCont2;
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipoTrans() {
        return tipoTrans;
    }

    public void setTipoTrans(int tipoTrans) {
        this.tipoTrans = tipoTrans;
    }

    public int getNumCont1() {
        return numCont1;
    }

    public void setNumCont1(int numCont1) {
        this.numCont1 = numCont1;
    }

    public int getNumCont2() {
        return numCont2;
    }

    public void setNumCont2(int numCont2) {
        this.numCont2 = numCont2;
    }

    public float getSaldoConta1() {
        return saldoConta1;
    }

    public void setSaldoConta1(float saldoConta1) {
        this.saldoConta1 = saldoConta1;
    }

    public float getSaldoConta2() {
        return saldoConta2;
    }

    public void setSaldoConta2(float saldoConta2) {
        this.saldoConta2 = saldoConta2;
    }
    
    public void setaSaldo(){
        setSaldoConta1(verificaSaldo(getNumCont1()));
        setSaldoConta2(verificaSaldo(getNumCont2()));
    }
    
    public float verificaSaldo(int numCont){
        Banco Conta = new Banco();
        Conta.conectar();
        float saldo = 0;
        
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        
        String sql = "SELECT * FROM Contas WHERE NumeroConta = ?";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setInt(1, numCont);
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                saldo = resultSet.getFloat("Saldo");
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
        return saldo;
        
    }
    
    public void movimentacao(){        
        setaSaldo();
        if(getTipo() == 1 && getTipoTrans() != 2 && getTipoTrans() != 4){
            Transfere transfere = new Transfere(getNumCont1(), getNumCont2(), getSaldoConta1(), getSaldoConta2());
            transfere.Transf();            
        }else if(getTipo() == 2){
            System.out.println("Desculpe! \nEsse tipo de conta nao pode realizar trasnferências.");
        }else if(getTipo() == 3 && getTipoTrans() != 2 && getTipoTrans() != 4){
            Transfere transfere = new Transfere(getNumCont1(), getNumCont2(), getSaldoConta1(), getSaldoConta2());
            transfere.Transf(); 
        }else if(getTipo() == 4 && getTipoTrans() == 1 && getTipoTrans() != 3){
            Transfere transfere = new Transfere(getNumCont1(), getNumCont2(), getSaldoConta1(), getSaldoConta2());
            transfere.Transf();
        }else if(getTipo() == 5 && getTipoTrans() != 2 && getTipoTrans() != 4){
            Transfere transfere = new Transfere(getNumCont1(), getNumCont2(), getSaldoConta1(), getSaldoConta2());
            transfere.Transf();
        }            
    }
    
    public void resgataTipo(){
        Banco Conta = new Banco();
        Conta.conectar();
        
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        
        String sql = "SELECT * FROM Contas WHERE NumeroConta = ?";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setInt(1, getNumCont2());
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                setTipoTrans(resultSet.getInt("Tipo"));
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
