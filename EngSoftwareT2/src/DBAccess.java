import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {
	static String JDBC_DRIVER;  
	static String DB_URL;
	
	Connection conn = null;
	   
	public DBAccess(String jdbc_driver, String dbURL, String user, String password) throws ClassNotFoundException, SQLException{
		JDBC_DRIVER = jdbc_driver;
		DB_URL = dbURL;
		
	    Class.forName( JDBC_DRIVER );
	    conn = DriverManager.getConnection(dbURL, user, password);
	}; 
	
	public Connection getConnection(){
		return conn;
	}
	
	public void initialize() throws SQLException{		
		Statement stmt = getConnection().createStatement();
		String sqlQuery = "CREATE TABLE utilizadores (id int not null, nome varchar(255) not null, primary key(id))";
		stmt.execute(sqlQuery);
		Statement stmt2 = getConnection().createStatement();
		String sqlQuery2 = "CREATE TABLE documentos (id int not null, title varchar(255), "
				+ "body varchar(1000), d_criacao timestamp not null, d_alteracao timestamp not null, id_resp int not null, "
				+ "primary key(id, id_user), foreign key(id_user) references utilizadores(id))";
		stmt2.execute(sqlQuery2);
	}
	
	public void runQuery(String query) throws SQLException{
		Statement stmt = getConnection().createStatement();
		stmt.execute(query);
		
	}
	
}
