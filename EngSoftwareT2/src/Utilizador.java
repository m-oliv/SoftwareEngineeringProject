import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Utilizador {
	private int id;
	private String nome;
	
	public Utilizador(int i, String n){
		if(n.equals(null)){
			throw new NullPointerException();
		}
		this.id = i;
		this.nome = n;
	}
	
	public String getName(){
		return nome;
	}
	
	public int getID(){
		return id;
	}
	
	public void addUser(DBAccess dbaccess) throws SQLException{
		if(nome.equals(null)){
			throw new NullPointerException();
		}
		else{
			Connection conn = dbaccess.getConnection();
			Statement stmt = conn.createStatement();
			String sqlQuery = "INSERT INTO utilizadores VALUES( " + id + ", '" + nome + "')";
			stmt.execute(sqlQuery);
		}
	}
	public void updateUser(DBAccess dba, int to_upd,String n, int i) throws SQLException{
		
		/* Se to_upd for:
		 *  0 -> update nome
		 *  1 -> update id
		 */
		
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		
		// update nome
		if(to_upd == 0){
			if(n.equals(null)){
				throw new NullPointerException();
			}
			else{
				String sqlQuery = "UPDATE utilizadores SET nome = '" + n + "' WHERE id = "+ i;
				stmt.execute(sqlQuery);
			}
		}
		
		// update id
		if(to_upd == 1){
			String sqlQuery = "UPDATE utilizadores SET id = " + i + " WHERE nome = '"+ n +"'";
			stmt.execute(sqlQuery);
		}
	}

	public void deleteUser(DBAccess dba, int i) throws SQLException {
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "DELETE FROM utilizadores WHERE id = "+ i;
		stmt.execute(sqlQuery);
	}

/*	Funcoes de update antigas
 * 
	public void updateNameDB(DBAccess dba, String n, int i) throws SQLException {
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "UPDATE utilizadores SET nome = '" + n + "' WHERE id = "+ i;
		stmt.execute(sqlQuery);
	}

	public void updateIDDB(DBAccess dba, String n, int i) throws SQLException {
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "UPDATE utilizadores SET id = " + i + " WHERE nome = '"+ n +"'";
		stmt.execute(sqlQuery);
	}
	*/

	
}
