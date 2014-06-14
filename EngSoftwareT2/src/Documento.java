import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.sql.SQLException;

public class Documento {
	private int id;
	private String title;
	private String body;
	private Timestamp d_criacao;
	private Timestamp d_alteracao;
	private int id_user = -1;

	private String readValue=null;
	private File documentFile;

	public Documento(int id, String title, String body, Timestamp timestamp, int id_user) throws Exception {
		if (title.equals(null)) {
			throw new NullPointerException();
		}
		if (body.equals(null)) {
			throw new NullPointerException();
		}
		if (id_user == -1) { 
			throw new Exception("id_user wrong");
		}
		if (timestamp.equals(null)) {
			throw new NullPointerException();
		}
		this.id = id;
		this.title = title;
		this.body = body;
		this.d_criacao = timestamp;
		this.id_user = id_user;
	}

	/*OVERLOAD: Vers�o que carrega com filepath em vez de hard coded title e body*/
	public Documento(int id, String filepath, Timestamp timestamp, int id_user) throws Exception {
		if (id_user == -1) {
			throw new Exception("id_user wrong");
		}
		if (timestamp.equals(null)) {
			throw new NullPointerException();
		}
		this.id = id;
		this.d_criacao = timestamp;
		this.id_user = id_user;	
		documentFile = new File(filepath);
		BufferedReader reader = null;
		try 
		{
			reader = new BufferedReader (new FileReader(documentFile) );
			// Linha 1: "title:"
			readValue=reader.readLine();
			// Linha 2: "sometitle"
			readValue=reader.readLine();
			title=readValue;
			readValue=reader.readLine();
			// Linha 4: "body:"
			readValue=reader.readLine();
			// Linhas 5+: "anything goes"
			readValue=reader.readLine();
			body="";
			while(readValue!=null)
			{
				body+=readValue+"\n";
				readValue=reader.readLine();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
		    try 
		    {
		        if (reader != null) 
		        {
		            reader.close();
		        }
		    } 
		    catch (Exception e) 
		    {
				e.printStackTrace();
		    }
		}

	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public int getID() {
		return id;
	}

	public int getUser() {
		return id_user;
	}

	public Timestamp getD_criacao() {
		return d_criacao;
	}

	public Timestamp getD_alteracao() {
		return d_alteracao;
	}

	public void addDoc(DBAccess dbaccess) throws Exception {
		if (title.equals(null)) {
			throw new NullPointerException();
		}
		if (body.equals(null)) {
			throw new NullPointerException();
		}
		if (id_user == -1) {
			throw new Exception("id_user wrong");
		}
		if (d_criacao.equals(null)) {
			throw new NullPointerException();
		} else {
			String sqlQuery = "INSERT INTO documentos VALUES( " + id + ", '"
					+ title + "','" + body + "', '" + d_criacao + "','"
					+ d_criacao + "', " + id_user + ")";
			dbaccess.runQuery(sqlQuery);
		}
	}

	public void updateDoc(DBAccess dba, int to_upd, String n, int id_user,int i, Timestamp d_alteracao) throws Exception {

		/*
		 * Se to_upd for: 0 -> update title 2 -> update body 3 -> update user?
		 */
		// update title
		if (to_upd == 0) {
			if (n.equals(null)) {
				throw new NullPointerException();
			}

			else {
				String sqlQuery = "UPDATE documentos SET title = '" + n
						+ "' WHERE id = " + i;
				dba.runQuery(sqlQuery);
				String sqlQuerytime_update = "UPDATE documentos SET d_alteracao = '"
						+ d_alteracao + "' WHERE id = " + i;
				dba.runQuery(sqlQuerytime_update);
			}
		}

		// update body
		if (to_upd == 2) {
			if (n.equals(null)) {
				throw new NullPointerException();
			} else {
				String sqlQuery = "UPDATE documentos SET body = '" + n
						+ "' WHERE id = " + i;
				dba.runQuery(sqlQuery);
				String sqlQuerytime_update = "UPDATE documentos SET d_alteracao = '"
						+ d_alteracao + "' WHERE id = " + i;
				dba.runQuery(sqlQuerytime_update);
			}
		}

		// update id_user
		if (to_upd == 3) {
			if (id_user == -1) {
				throw new Exception("id_user wrong");
			} else {
				String sqlQuery = "UPDATE documentos SET id_user = '" + id_user
						+ "' WHERE id = " + i;
				dba.runQuery(sqlQuery);
				String sqlQuerytime_update = "UPDATE documentos SET d_alteracao = '"
						+ d_alteracao + "' WHERE id = " + i;
				dba.runQuery(sqlQuerytime_update);
			}
		}
	}

	public void updateDocTitle(DBAccess dba, String n, int id,Timestamp d_alteracao) throws SQLException {


		if (n.equals(null)) {
			throw new NullPointerException();
		}

		else {
			String sqlQuery = "UPDATE documentos SET title = '" + n
					+ "' WHERE id = " + id;
			dba.runQuery(sqlQuery);
			String sqlQuerytime_update = "UPDATE documentos SET d_alteracao = '"
					+ d_alteracao + "' WHERE id = " + id;
			dba.runQuery(sqlQuerytime_update);
		}

	}

	public void updateDocBody(DBAccess dba, String n, int id, Timestamp d_alteracao) throws SQLException {
		if (n.equals(null)) {
			throw new NullPointerException();
		} else {
			String sqlQuery = "UPDATE documentos SET body = '" + n
					+ "' WHERE id = " + id;
			dba.runQuery(sqlQuery);
			String sqlQuerytime_update = "UPDATE documentos SET d_alteracao = '"
					+ d_alteracao + "' WHERE id = " + id;
			dba.runQuery(sqlQuerytime_update);
		}

	}

	public void updateDocId_user(DBAccess dba, int id_user,	int id, Timestamp d_alteracao) throws Exception {

		if (id_user == -1) {
			throw new Exception("id_user wrong");
		} else {
			String sqlQuery = "UPDATE documentos SET id_user = '" + id_user
					+ "' WHERE id = " + id;
			dba.runQuery(sqlQuery);
			String sqlQuerytime_update = "UPDATE documentos SET d_alteracao = '"
					+ d_alteracao + "' WHERE id = " + id;
			dba.runQuery(sqlQuerytime_update);
		}

	}


	public void deleteDoc(DBAccess dba, int i) throws SQLException {
		
		String sqlQuery = "DELETE FROM documentos WHERE id = " + i;
		dba.runQuery(sqlQuery);
	}

	public String toString(){
		return "Titulo: "+this.title+"; Id do doc: "+id+"; Id do user: "+id_user+id+"; Timestamp create: "+d_criacao;
	}

}