import AlteracoesBanco.AtualizacaoDados;
import AlteracoesBanco.Remover;
import java.util.Scanner;

import Contas.ContaCorrente;
import Contas.ContaCorrentePoupanca;
import Contas.ContaCorrenteSalario;
import Contas.ContaPoupanca;
import Contas.ContaSalario;
import Emprestimo.EmprestimoConsignado;
import Pesquisa.Busca;
import Transferencias.Depositar;
import Transferencias.Login;
import Transferencias.LoginGerente;
import Transferencias.Sacar;
import Transferencias.ValidaTransferencia;
import Cambio.SistemaCambio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

public class Principal {

    public static void main(String[] args) throws IOException {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int op;
        int TipoConta = 0;
        //Dados da conta o de baixo
        int NumConta = 0;
        String Nome;
        double Saldo;
        String senha;
        int Tipo;
        
        int opMenu = 0;
        do{
            System.out.println("Acesso ao Banco");
            System.out.println("1 - Gerente");
            System.out.println("2 - Cliente");
            System.out.println("0 - Sair");
            System.out.print("OPCAO: ");
            try{
                opMenu = sc.nextInt();
            }catch(Exception erro){
                System.out.println("ATENCÃO! Somente Numeros.");
            }
            if(opMenu == 1){
                System.out.println("Login Gerente");
                System.out.println("Login");
                String log = sc.next();
                System.out.println("Senha");
                String sen = sc.next();
                LoginGerente logGerente = new LoginGerente(log, sen);
                int c = logGerente.validaLoginADM();
                if(c == 1){
                    System.out.println("\nLogin efeituado com sucesso!");
                    while(true){
                        MenuCriaConta();
                        op = sc.nextInt();
                        if(op == 1) {
                            Tipo();
                            try{
                                TipoConta = sc.nextInt();
                            }catch(Exception erro){
                                System.out.println("ATENÇÂO! Somente Numeros.");
                            }
                            switch (TipoConta) {
                                case 1:
                                    {
                                        System.out.println("Numero da Conta");
                                        try{
                                            NumConta = sc.nextInt();
                                        }catch(Exception erro){
                                            System.err.println("ATENCÃO! Somente Numeros.");
                                            break;
                                        }
                                        System.out.println("Nome do Cliente");
                                        Nome = entrada.readLine();
                                        System.out.println("Senha da Conta");
                                        senha = sc.next();
                                        ContaCorrente c1 = new ContaCorrente(NumConta,Nome,0,senha,1);
                                        c1.Cad();
                                        break;
                                    }
                                case 2:
                                    {
                                        System.out.println("Numero da Conta");
                                        try{
                                            NumConta = sc.nextInt();
                                        }catch(Exception erro){
                                            System.err.println("ATENCÃO! Somente Numeros.");
                                        }
                                        System.out.println("Nome do Cliente");
                                        Nome = entrada.readLine();
                                        System.out.println("Senha da Conta");
                                        senha = sc.next();
                                        ContaPoupanca c2 = new ContaPoupanca(NumConta,Nome,0,senha,2);
                                        c2.Cad();
                                        break;
                                    }
                                case 3:
                                    {
                                        System.out.println("Numero da Conta");
                                        try{
                                            NumConta = sc.nextInt();
                                        }catch(Exception erro){
                                            System.err.println("ATENCÃO! Somente Numeros.");
                                        }
                                        System.out.println("Nome do Cliente");
                                        Nome = entrada.readLine();
                                        System.out.println("Senha da Conta");
                                        senha = sc.next();
                                        ContaCorrentePoupanca c3 = new ContaCorrentePoupanca(NumConta,Nome,0,senha,3);
                                        c3.Cad();
                                        break;
                                    }
                                case 4:
                                    {
                                        System.out.println("Numero da Conta");
                                        try{
                                            NumConta = sc.nextInt();
                                        }catch(Exception erro){
                                            System.err.println("ATENCÃO! Somente Numeros.");
                                        }
                                        System.out.println("Nome do Cliente");
                                        Nome = entrada.readLine();
                                        System.out.println("Senha da Conta");
                                        senha = sc.next();
                                        ContaSalario c4 = new ContaSalario(NumConta,Nome,0,senha,4);
                                        c4.Cad();
                                        break;
                                    }
                                case 5:
                                    {
                                        System.out.println("Numero da Conta");
                                        try{
                                            NumConta = sc.nextInt();
                                        }catch(Exception erro){
                                            System.err.println("ATENCÃO! Somente Numeros.");
                                            
                                        }
                                        System.out.println("Nome do Cliente");
                                        Nome = entrada.readLine();
                                        System.out.println("Senha da Conta");
                                        senha = sc.next();
                                        ContaCorrenteSalario c5 = new ContaCorrenteSalario(NumConta,Nome,0,senha,5);
                                        c5.Cad();
                                        break;
                                    }
                                default:
                                    break;
                            }
                        }if(op == 2){
                            System.out.println("Informe o numero da conta que deseja remover: ");
                            int numCont = sc.nextInt();
                            Remover remover = new Remover(numCont);
                            remover.removeConta();
                        }
                        if(op == 3){
                            Atualizacao();
                            int opcao = 0;
                            try{
                                opcao = sc.nextInt();
                            }catch(Exception e){
                                System.out.println("ATENCÃO! Somente numeros.");
                            }
                            if(opcao == 1){
                                System.out.println("Informe o numero da conta que deseja atualizar: ");
                                int numCont = 0;
                                try{
                                    numCont = sc.nextInt();
                                }catch(Exception e){
                                    System.out.println("ATENCÃO! Apenas numeros!");
                                }
                                System.out.println("Informe o saldo: ");
                                float saldo = 0;
                                try{
                                    saldo = sc.nextFloat();
                                }catch(Exception e){
                                    System.out.println("ATENCÃO! Apenas numeros!");
                                }
                                System.out.println("Informe o novo nome: ");
                                String newNome = entrada.readLine();
                                System.out.println("Informe a nova senha: ");
                                String newSenha = sc.next();
                                System.out.println("Informe o tipo da conta: ");
                                int tipo = 0;
                                try{
                                    tipo = sc.nextInt();
                                }catch(Exception e){
                                    System.out.println("ATENCÃO! Apenas numeros!");
                                }
                                AtualizacaoDados atualizacaoDados = new AtualizacaoDados(numCont, newNome, saldo, newSenha, tipo);
                                atualizacaoDados.atualizaNome();
                                atualizacaoDados.atualizaSaldo();
                                atualizacaoDados.atualizaSenha();
                                atualizacaoDados.atualizaTipo();
                                System.out.println("Dados cadastrados com sucesso!");
                            }

                            else if(opcao == 2){
                                System.out.println("Informe o numero da conta que deseja atualizar: ");
                                int numCont = 0; 
                                try{
                                    numCont = sc.nextInt();
                                }catch(Exception e){
                                    System.out.println("ATENCÃO! Apenas numeros!");
                                }
                                System.out.println("Informe o novo nome: ");
                                String newNome = entrada.readLine();
                                AtualizacaoDados atualizacaoDados = new AtualizacaoDados(numCont, newNome, 0, null, 0);
                                atualizacaoDados.atualizaNome();
                            }

                            else if(opcao == 3){
                                System.out.println("Informe o numero da conta que deseja atualizar: ");
                                int numCont = 0; 
                                try{
                                    numCont = sc.nextInt();
                                }catch(Exception e){
                                    System.out.println("ATENCÃO! Apenas numeros!");
                                }
                                System.out.println("Informe o saldo: ");
                                float saldo = sc.nextFloat();
                                AtualizacaoDados atualizacaoDados = new AtualizacaoDados(numCont, null, saldo, null, 0);
                                atualizacaoDados.atualizaSaldo();
                            }

                            else if(opcao == 4){
                                System.out.println("Informe o numero da conta que deseja atualizar: ");
                                int numCont = 0; 
                                try{
                                    numCont = sc.nextInt();
                                }catch(Exception e){
                                    System.out.println("ATENCÃO! Apenas numeros!");
                                }
                                System.out.println("Informe a nova senha: ");
                                String newSenha = sc.next();
                                AtualizacaoDados atualizacaoDados = new AtualizacaoDados(numCont, null, 0, newSenha, 0);
                                atualizacaoDados.atualizaSenha();
                            }

                            else if(opcao == 5){
                                System.out.println("Informe o numero da conta que deseja atualizar: ");
                                int numCont = 0; 
                                try{
                                    numCont = sc.nextInt();
                                }catch(Exception e){
                                    System.out.println("ATENCÃO! Apenas numeros!");
                                }
                                System.out.println("Informe o tipo da conta: ");
                                int tipo = sc.nextInt();
                                AtualizacaoDados atualizacaoDados = new AtualizacaoDados(numCont, null, 0, null, tipo);
                                atualizacaoDados.atualizaTipo();
                            }else{
                                System.out.println("Opção invalida!");
                            }


                        }else if(op == 4){
                            MostrarDados();
                            int menu = 0;
                            try{
                                menu = sc.nextInt();
                            }catch(Exception e){
                                System.out.println("ATENCÃO! Apenas numeros.");
                            }
                            switch (menu) {
                                case 1:
                                    {
                                        Busca busca = new Busca(0);
                                        busca.imprimirContas();
                                        break;
                                    }
                                case 2:
                                    {
                                        System.out.println("Informe o numero da conta:");
                                        int numCont = 0; 
                                        try{
                                            numCont = sc.nextInt();
                                        }catch(Exception e){
                                            System.out.println("ATENCÃO! Apenas numeros!");
                                        }
                                        Busca busca = new Busca(numCont);
                                        busca.imprimirContaEspecifica();
                                        break;
                                    }
                                default:
                                    System.out.println("OPÇÃO INVALIDA!");
                                    break;
                            }
                        }
                        else if(op == 0){
                            System.out.println("goodbye!");
                            break;
                        }
                    }
                }else{
                    System.out.println("Login ou Senha incorretos!");
                }
                break;
            }
            else if(opMenu == 2){
                    //Login do cliente
                    int numCount = 0;
                    int tipoConta;
                    System.out.println("Login");
                    System.out.print("numero da conta: ");
                    try{
                        numCount = sc.nextInt();
                    }catch(Exception erro){
                        System.err.println("Somente numeros!");
                    }
                    System.out.print("Senha: ");
                    String password = sc.next();
                    Login login = new Login(numCount, password);
                    int altentica = login.loginConta();
                    if(altentica == 1){
                        System.out.println("Longin efeituado com sucesso!");
                        tipoConta = login.getTipoConta();
                        int menuC = 0;
                        do{
                            MenuCliente();
                            try{
                                menuC = sc.nextInt();
                            }catch(Exception e){
                                System.err.println("ATENCÃO! Só numeros.");
                            }
                            switch(menuC){
                                case 1:
                                    float sac = 0;
                                    System.out.println("Informe a quantidade que deseja sacar:");
                                    try{
                                        sac = sc.nextFloat();
                                    }catch(Exception erro){
                                        System.out.println("Erro: digite somente numero!");
                                    }
                                    Sacar sacar = new Sacar(numCount, sac);
                                    sacar.sacarDinheiro();
                                    break;
                                case 2:
                                    int numCont2 = 0;
                                    System.out.println("Informe o numero da conta que deseja transferir valor:");
                                    try{
                                        numCont2 = sc.nextInt();
                                    }catch(Exception erro){
                                         System.out.println("Erro: digite somente numero!");
                                    }
                                    ValidaTransferencia validaTransferencia = new ValidaTransferencia(numCount, numCont2, tipoConta);
                                    validaTransferencia.movimentacao();
                                    break;
                                case 3:
                                    float deposito = 0;
                                    System.out.println("Informe o valor do deposito: ");
                                    try{
                                        deposito = sc.nextFloat();
                                    }catch(Exception erro){
                                        System.out.println("Erro: digite somente numero!");
                                                
                                    }
                                    Depositar depositar = new Depositar(numCount, deposito);
                                    depositar.depositaSaldo();
                                    depositar.verificaSaldo();
                                    System.out.println("Saldo conta: " + depositar.getSaldoAtual());
                                    break;
                                case 4:
                                    float sal = 0;
                                    try{
                                        System.out.println("Informe seu salario: ");
                                        sal = sc.nextFloat();
                                    }catch(Exception erro){
                                        System.err.println("Atencão! Somente numeros");
                                    }
                                    EmprestimoConsignado emp = new EmprestimoConsignado(sal, numCount, tipoConta);
                                    emp.realizaEmprestimo();
                                    break;
                                case 5:
                                    float dsacar = 0;
                                    try{
                                        System.out.println("Deja fazer o cambio em quantos reais?");
                                        dsacar = sc.nextFloat();
                                    }catch(Exception e){
                                        System.out.println("ERRO! ");
                                    }
                                    SistemaCambio sisCambio = new SistemaCambio(numCount, dsacar);
                                    sisCambio.realizaCambio();
                                    break;
                                case 0:
                                    break;
                            }
                        }while(menuC != 0);
                        
                        
                    }else{
                        System.out.println("Login ou Senha incorretos!");
                    }          
            }
        }while(opMenu != 0);
        
        

	}
	public static void MenuCriaConta() {
            System.out.println("\n 1-Criar(Cadastrar) ");
            System.out.println(" 2-Remover            ");
            System.out.println(" 3-Atualizar          ");
            System.out.println(" 4-Mostrar contas     ");
            System.out.println(" 0-Sair               ");
	}
	public static void Tipo() {
            System.out.println("\n  Tipo de conta         ");
            System.out.println(" 1-ContaCorrente          ");
            System.out.println(" 2-ContaPoupanca          ");
            System.out.println(" 3-ContaCorrentePoupanca  ");
            System.out.println(" 4-ContaSalario           ");
            System.out.println(" 5-ContaCorrenteSalario   ");
	}
        
        public static void Atualizacao(){
            System.out.println("\n 1 - Atualizar todos os dados ");
            System.out.println(" 2 - Atualizar somente o nome   ");
            System.out.println(" 3 - Atualizar somente o saldo  ");
            System.out.println(" 4 - Atualizar somente senha    ");
            System.out.println(" 5 - Atualizar somente tipo     ");
            System.out.println(" 0 - Sair                       ");
        }
        
        public static void MostrarDados(){
            System.out.println("\n 1 - Listar todas as contas      ");
            System.out.println(" 2 - Mostrar uma conta especifica ");
        }
              
        public static void MenuCliente(){
            System.out.println(" 1 - Sacar");
            System.out.println(" 2 - Transferencia");
            System.out.println(" 3 - Depositar");
            System.out.println(" 4 - Emprestimo Consignado");
            System.out.println(" 5 - Cambio");
            System.out.println(" 0 - Sair");
            System.out.println("OPCAO: ");
        }

}