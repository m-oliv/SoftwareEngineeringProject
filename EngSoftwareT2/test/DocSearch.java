import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/*A class DocSearch recebe como argumento na cria��o um documento.*/
public class DocSearch {
	
	//allDocs vai ter todos os documentos.
	private ArrayList<Documento> allDocs;
	private DBAccess sourceDatabase;
	
	
	public DocSearch(DBAccess dba){
		/*N�o s�o precisos argumentos para instanciar porque os documentos s�o retirados da base de dados.*/
		sourceDatabase=dba;
		updateDB();
		
	}
	
	public void updateDB()
	{
		allDocs = new ArrayList<Documento>();
		try {
			/*Inicializa��o da base de dados.*/
			Connection conn = sourceDatabase.getConnection();
			Statement stmt = conn.createStatement();
			
			/*Query. Querem-se todos os dados de todos os documentos neste ponto, por isso o select � a tudo (*) */
			String sqlQuery = "SELECT * FROM documentos;";
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()){
				
				/*para cada resultado � acrescentado um documento, instanciado a partir da informa��o obtida
				 * da base de dados.*/
				
				int id=Integer.parseInt(rs.getString("id"));
				String title=rs.getString("title");
				String body=rs.getString("body");
				SimpleDateFormat create=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				Timestamp createTS=new Timestamp(create.parse(rs.getString("d_criacao")).getTime());
				//String modify=rs.getString("d_alteracao");
				int user=Integer.parseInt(rs.getString("id_user"));
				
				//Instancia um documento que serve para pesquisa.
				//Isto funciona para documentos _pequenos_ dado que os objectos s�o instanciados para a mem�ria.
				//Uma biblioteca extensa obrigava a verficiar doc a doc sem carregar em mem�ria e ir fazendo free/garbage collect.
				Documento current = new Documento(id,title,body, createTS, user);
				/*Acrescenta ao allDocs o documento que encontrou e continua o ciclo.*/
				allDocs.add(current);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*Procura no campo de t�tulo.*/
	public ArrayList<Integer> searchTitles(String toSearch){
		
		/*Para saber quanto anda no ciclo.*/
		int nDocs=allDocs.size();
		
		/*ArrayList vai conter os ids dos documentos que cont�m o t�tulo.*/
		ArrayList<Integer> docsContain = new ArrayList<Integer>();
		
		/*Itera sobre os documentos da base de dados, j� carregados no construtor desta classe.*/
		for(int i=0; i<nDocs; i++)
		{
			Documento current=allDocs.get(i);
			
			//o indexOf d� a posi��o da string onde encontra match (complexidade o(n)). -1 � o resultado se _n�o_
			//encontra na string o que est� no argumento. (toSearch)
			if(current.getTitle().indexOf(toSearch)!=-1)
			{
				//Se o documento TEM o t�tulo toSearch, acrescenta-o ao docsContain (a arraylist que guarda os resultados)
				docsContain.add(current.getID());
			}		
		}
	
		/*Retorna arraylist dos documentos que cont�m o que foi pesquisado. De momento, listados por ID.*/
		return docsContain;
	}

	
	public ArrayList<Integer> searchBodies(String toSearch){
		
		/*Para saber quanto anda no ciclo.*/
		int nDocs=allDocs.size();
		
		/*ArrayList vai conter os ids dos documentos que cont�m o body.*/
		ArrayList<Integer> docsContain = new ArrayList<Integer>();
		
		/*Itera sobre os documentos da base de dados, j� carregados no construtor desta classe.*/
		for(int i=0; i<nDocs; i++)
		{
			Documento current=allDocs.get(i);
			
			//o indexOf d� a posi��o da string onde encontra match (complexidade o(n)). -1 � o resultado se _n�o_
			//encontra na string o que est� no argumento. (toSearch)
			if(current.getBody().indexOf(toSearch)!=-1)
			{
				//Se o documento TEM o body toSearch, acrescenta-o ao docsContain (a arraylist que guarda os resultados)
				docsContain.add(current.getID());
			}		
		}
		
		/*Retorna arraylist dos documentos que cont�m o que foi pesquisado. De momento, listados por ID.*/
		return docsContain;
	}
	


	
	public ArrayList<Integer> searchGeneric(String toSearch){

		/*Para saber quanto anda no ciclo.*/
		int nDocs=allDocs.size();

		/*ArrayList vai conter os ids dos documentos que cont�m o toSearch.*/
		ArrayList<Integer> docsContain = new ArrayList<Integer>();

		/*Itera sobre os documentos da base de dados, j� carregados no construtor desta classe.*/
		for(int i=0; i<nDocs; i++)
		{
			Documento current=allDocs.get(i);
			
			//o indexOf d� a posi��o da string onde encontra match (complexidade o(n)). -1 � o resultado se _n�o_
			//encontra na string o que est� no argumento. (toSearch)
			if(current.getBody().indexOf(toSearch)!=-1)
			{
				//Se o documento TEM o body toSearch, acrescenta-o ao docsContain (a arraylist que guarda os resultados).
				//De notar que, se j� foi encontrado no t�tulo, o documento j� est� no docsContain, portanto
				//� terminada esta itera��o com continue para evitar que o mesmo documento se repita.
				docsContain.add(current.getID());
				continue;
			}

			if(current.getTitle().indexOf(toSearch)!=-1)
			{
				//Se o documento TEM o title toSearch, acrescenta-o ao docsContain (a arraylist que guarda os resultados).
				//De notar que, neste ponto, _n�o_ tem o toSearch no BODY, s� no title.
				docsContain.add(current.getID());
			}		
		}
		
		/*Retorna arraylist dos documentos que cont�m o que foi pesquisado. De momento, listados por ID.*/
		return docsContain;
	}
	

	public ArrayList<String> searchGeneric(int toSearch){
		
		/*Para saber quanto anda no ciclo.*/
		int nDocs=allDocs.size();

		/*ArrayList vai conter os t�tulos do documento que contem o toSearch. Dado que os ids s�o �nicos, o resultado DEVER� ser sempre
		 * de size 0 ou 1.*/
		ArrayList<String> docsContain = new ArrayList<String>();

		/*Itera sobre os documentos da base de dados, j� carregados no construtor desta classe.*/
		for(int i=0; i<nDocs; i++)
		{
			Documento current=allDocs.get(i);
			//Neste caso o id � um integer, portanto pode ser comparado direcatmente com ==.
			if(current.getID()==toSearch)
			{
				//Acrescenta o documento se o id � encontrado.
				docsContain.add(current.getTitle());
			}
		}
		/*Retorna arraylist dos documentos que cont�m o que foi pesquisado. De momento, listados por t�tulo.*/
		return docsContain;
	}
	
	
	public ArrayList<String> searchByDate(Timestamp toSearch){
		
		/*Para saber quanto anda no ciclo.*/
		int nDocs=allDocs.size();
		
		/*ArrayList vai conter os t�tulos dos documentos cuja data de cria��o excede toSearch.*/
		ArrayList<String> docsContain = new ArrayList<String>();

		/*Itera sobre os documentos da base de dados, j� carregados no construtor desta classe.*/
		for(int i=0; i<nDocs; i++)
		{
			Documento current=allDocs.get(i);
			//Timestamp.after(toSearch) d� true se toSearch for MENOR que Timestamp.
			if(current.getD_criacao().after(toSearch)){
				docsContain.add(current.getTitle());
			}
		}
		/*Retorna arraylist dos documentos que cont�m o que foi pesquisado. De momento, listados por t�tulo.*/
		return docsContain;
	}

	public String toString(){
		
		/*Para saber quanto anda no ciclo.*/
		int nDocs=allDocs.size();
		
		String result="";
		
		/*Itera sobre os documentos da base de dados, j� carregados no construtor desta classe.*/
		for(int i=0; i<nDocs; i++)
		{
			Documento current=allDocs.get(i);
			result+=current.toString()+";\n";
		}
		return result;
	}

}
