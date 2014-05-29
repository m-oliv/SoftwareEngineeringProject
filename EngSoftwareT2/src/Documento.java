import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Documento {
	private int id;
	private String title;
	private String body;
	private Date inputdate;
	
	public Documento(int i, String n, String b){
		if(n.equals(null)){
			throw new NullPointerException();
		}
		if(b.equals(null)){
			throw new NullPointerException();
		}
		this.id = i;
		this.title = n;
		this.body = b;
	}
	
	public String getTitle(){
		return title;
	}
	public String getBody(){
		return body;
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
