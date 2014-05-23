import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Utilizador {
	private int id;
	private String nome;
	
	public Utilizador(int i, String n){
		this.id = i;
		this.nome = n;
	}
	
	public String getName(){
		return nome;
	}
	
	public int getID(){
		return id;
	}
	
	public void addToDB(DBAccess dbaccess) throws SQLException{
		Connection conn = dbaccess.getConnection();
		Statement stmt = conn.createStatement();
		String sqlQuery = "INSERT INTO utilizadores VALUES( " + id + ", '" + nome + "')";
		stmt.execute(sqlQuery);
	}
}
