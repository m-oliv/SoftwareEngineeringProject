import java.sql.SQLException;

public class Utilizador {
	private int id;
	private String nome;
	
	public Utilizador(int i, String n){
		// cada utilizador tem um ID e um nome (o nome nao pode ser null)
		if(n.equals(null)){
			throw new NullPointerException();
		}
		this.id = i;
		this.nome = n;
	}
	
	public String getName(){
		// retornar o nome do utilizador
		return nome;
	}
	
	public int getID(){
		// retornar o ID do utilizador
		return id;
	}
	
	public void addUser(DBAccess dbaccess) throws SQLException{
		// adicionar um novo utilizador a base de dados
		
		if(nome.equals(null)){
			// se o nome for null, lanca uma excepcao
			throw new NullPointerException(); 
		}
		else{
			// caso contrario, adicionar o utilizador a BD
			String sqlQuery = "INSERT INTO utilizadores VALUES( " + id + ", '" + nome + "')";
			dbaccess.runQuery(sqlQuery);
		}
	}
	
	public void updateUser(DBAccess dba, int to_upd,String n, int i) throws SQLException{
		
		// actualizar um utilizador na base de dados (apenas o nome)
		
			if(n.equals(null)){
				throw new NullPointerException(); // se o nome for null, lanca excepcao
			}
			else{
				// caso contrario, faz update do nome (para o utilizador cujo ID e fornecido)
				String sqlQuery = "UPDATE utilizadores SET nome = '" + n + "' WHERE id = "+ i;
				dba.runQuery(sqlQuery);
			}

	}

	public void deleteUser(DBAccess dba, int i) throws SQLException {

		//remove da base de dados o utilizador que tem o ID fornecido
		String sqlQuery = "DELETE FROM utilizadores WHERE id = "+ i;
		dba.runQuery(sqlQuery);
	}

	public String toString(){
		// retorna uma string com a informacao do utilizador
		return "ID: "+id+"; Nome: "+nome+";";
	}
}
