package AlteracoesBanco;

import BancoConexao.Banco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.sqlite.SQLiteException;

public class Remover {
    private int numCont;
    
    public Remover(int numCont){
        this.numCont = numCont;
    }
    
    public void setNumeroConta(int numeroConta){
        this.numCont = numeroConta;
    }
    
    public int getNumeroConta(){
        return numCont;
    }
        
    public void removeConta(){
        Banco Conta = new Banco();
        Conta.conectar();
        
        PreparedStatement preparedStatement = null;
        
        String sql = "DELETE FROM Contas WHERE NumeroConta = ?;";
        
        try{
            preparedStatement = Conta.criarPreparedStatement(sql);
            preparedStatement.setInt(1, getNumeroConta());
            
            preparedStatement.executeUpdate();
            
            System.out.println("Conta Removida com sucesso!");
            
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
