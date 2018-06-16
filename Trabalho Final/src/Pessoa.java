
	public class Pessoa {
		protected int NumeroConta;
		protected String Nome;
		protected double Saldo;
		protected String Senha;
		protected int Tipo;
		
		public Pessoa(int numeroConta, String nome, double saldo, String senha, int tipo) {
			NumeroConta = numeroConta;
			Nome = nome;
			Saldo = saldo;
			Senha = senha;
			Tipo = tipo;
		}
		
		public int getNumeroConta() {
			return NumeroConta;
		}
		public void setNumeroConta(int numeroConta) {
			NumeroConta = numeroConta;
		}
		public String getNome() {
			return Nome;
		}
		public void setNome(String nome) {
			Nome = nome;
		}
		public double getSaldo() {
			return Saldo;
		}
		public void setSaldo(double saldo) {
			Saldo = saldo;
		}
		public String getSenha() {
			return Senha;
		}
		public void setSenha(String senha) {
			Senha = senha;
		}
		public int getTipo() {
			return Tipo;
		}
		public void setTipo(int tipo) {
			Tipo = tipo;
		}
		public String toString() {
			return "NumeroConta=" + getNumeroConta() + ", Nome=" + getNome() + ", Saldo=" + getSaldo() + ", Senha=" + getSenha()
					+ ", Tipo=" + getTipo();
		}
	}

