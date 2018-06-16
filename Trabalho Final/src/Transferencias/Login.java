package Transferencias;

import BancoConexao.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private int numeroConta;
    private String senha;
    private int TipoConta;
    
    public Login(int numeroConta, String senha) {
        this.numeroConta = numeroConta;
        this.senha = senha;
    }

    public int getTipoConta() {
        return TipoConta;
    }

    public void setTipoConta(int TipoConta) {
        this.TipoConta = TipoConta;
    }
    
    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int loginConta(){
        Banco Conta = new Banco();
        Conta.conectar();
        
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        
        String sql = "SELECT Senha, Tipo FROM Contas WHERE NumeroConta = ?";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setInt(1, getNumeroConta());
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                if(resultSet.getString("Senha").equals(getSenha())){
                    setTipoConta(resultSet.getInt("Tipo"));
                    return 1;
                }
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
        return 0;
    }

    
}
