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
			String sqlQuery = "INSERT INTO utilizadores VALUES( " + id + ", '" + nome + "')";
			dbaccess.runQuery(sqlQuery);
		}
	}
	public void updateUser(DBAccess dba, int to_upd,String n, int i) throws SQLException{
		
		/* Se to_upd for:
		 *  0 -> update nome
		 *  1 -> update id
		 */
		
		// update nome
		if(to_upd == 0){
			if(n.equals(null)){
				throw new NullPointerException();
			}
			else{
				String sqlQuery = "UPDATE utilizadores SET nome = '" + n + "' WHERE id = "+ i;
				dba.runQuery(sqlQuery);
			}
		}
//		
//		// update id
//		if(to_upd == 1){
//			String sqlQuery = "UPDATE utilizadores SET id = " + i + " WHERE nome = '"+ n +"'";
//			dba.runQuery(sqlQuery);
//		}
	}

	public void deleteUser(DBAccess dba, int i) throws SQLException {

		String sqlQuery = "DELETE FROM utilizadores WHERE id = "+ i;
		dba.runQuery(sqlQuery);
	}

	public String toString(){
		return "ID: "+id+"; Nome: "+nome+";";
	}
}
