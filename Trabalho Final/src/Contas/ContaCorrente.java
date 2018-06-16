package Contas;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;

import BancoConexao.Banco;

public class ContaCorrente extends Conta{
    Banco ContCorrente = new Banco();
    public ContaCorrente(int NumConta,String nome,float Saldo,String senha,int tipo) {
            super(NumConta, nome, Saldo, senha, tipo);
    }
    public void Cad() {
        ContCorrente.conectar();
        String sql = "INSERT INTO Contas("
            + "NumeroConta,"
            + "Nome,"
            + "Saldo,"
            + "Senha,"
            + "Tipo"
            + ") VALUES(?,?,?,?,?)"
            +";";


        PreparedStatement prepare = ContCorrente.criarPreparedStatement(sql);

        try{
            prepare.setInt(1,getNumConta());
            prepare.setString(2,getNome());
            prepare.setFloat(3,getSaldo());
            prepare.setString(4,getSenha());
            prepare.setInt(5,getTipo());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null,"Cadastrado!");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao Cadastrar");
        }finally{
            if(prepare != null){
                try {
                    prepare.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar o banco");
                }
            }
        }
        ContCorrente.desconectar();
    }    
}
