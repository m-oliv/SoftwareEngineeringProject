import java.security.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Documento {
	private int id;
	private String title;
	private String body;
	private Timestamp time;
	private String user;
	
	public Documento(int id, String title, String body, Timestamp time, String user){
		if(title.equals(null)){
			throw new NullPointerException();
		}
		if(body.equals(null)){
			throw new NullPointerException();
		}
		this.id = id;
		this.title = title;
		this.body = body;
		this.time = time;
		this.user = user;
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
	public String getUser(){
		return user;
	}
	public Timestamp getTime(){
		return time;
	}
	
	public void addDoc(DBAccess dbaccess) throws SQLException{
		if(title.equals(null)){
			throw new NullPointerException();
		}
		else{
			Connection conn = dbaccess.getConnection();
			Statement stmt = conn.createStatement();
			String sqlQuery = "INSERT INTO documentos VALUES( " + id + ", '" + title + "','" + body  + "', '" + time + "', '" + user + "')";
			stmt.execute(sqlQuery);
		}
	}
	public void updateDoc(DBAccess dba, int to_upd,String n, int i) throws SQLException{
		
		/* Se to_upd for:
		 *  0 -> update title
		 *  1 -> update id
		 *  2 -> update body
		 *  3 -> updete user?
		 */
		
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		
		// update title
		if(to_upd == 0){
			if(n.equals(null)){
				throw new NullPointerException();
			}
			else{
				String sqlQuery = "UPDATE documentos SET title = '" + n + "' WHERE id = "+ i;
				stmt.execute(sqlQuery);
			}
		}
		
		// update id
		if(to_upd == 1){
			String sqlQuery = "UPDATE documentos SET id = " + i + " WHERE nome = '"+ n +"'";
			stmt.execute(sqlQuery);
		}
		if(to_upd == 2){
			if(n.equals(null)){
				throw new NullPointerException();
			}
			else{
				String sqlQuery = "UPDATE documentos SET body = '" + n + "' WHERE id = "+ i;
				stmt.execute(sqlQuery);
			}
		}
		if(to_upd == 3){
			if(n.equals(null)){
				throw new NullPointerException();
			}
			else{
				String sqlQuery = "UPDATE documentos SET user = '" + n + "' WHERE id = "+ i;
				stmt.execute(sqlQuery);
			}
		}
	}

	public void deleteDoc(DBAccess dba, int i) throws SQLException {
		Connection conn = dba.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "DELETE FROM documentos WHERE id = "+ i;
		stmt.execute(sqlQuery);
	}


	
}
