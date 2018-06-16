package Transferencias;

public class LoginGerente {
    private String login;
    private String senha;

    public LoginGerente(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int validaLoginADM(){
        String comp = "admin";
        if(comp.equals(getLogin()) && comp.equals(getSenha())){
            return 1;
        }
        return 0;
    }
}
