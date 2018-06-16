
package Contas;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;

import BancoConexao.Banco;

public class ContaCorrenteSalario extends Conta{
	Banco ContCorrenteSalario = new Banco();
	public ContaCorrenteSalario(int NumConta,String nome,float Saldo,String senha,int tipo) {
		super(NumConta, nome, Saldo, senha, tipo);
	}
	public void Cad() {
		
		ContCorrenteSalario.conectar();
		String sql = "INSERT INTO Contas("
	            + "NumeroConta,"
	            + "Nome,"
	            + "Saldo,"
	            + "Senha,"
	            + "Tipo"
	            + ") VALUES(?,?,?,?,?)"
	            +";";
	   
	        JOptionPane.showMessageDialog(null,"Cadastrado!");
	        PreparedStatement prepare = ContCorrenteSalario.criarPreparedStatement(sql);
	        
	        try{
	        	prepare.setInt(1,getNumConta());
	        	prepare.setString(2,getNome());
	        	prepare.setFloat(3,getSaldo());
	        	prepare.setString(4,getSenha());
	        	prepare.setInt(5,getTipo());
	            prepare.executeUpdate();
	        }catch(SQLException e){
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
	        ContCorrenteSalario.desconectar();
	}
    
    
    
    
}
