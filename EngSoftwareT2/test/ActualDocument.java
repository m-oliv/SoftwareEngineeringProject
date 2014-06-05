import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.sql.Date;


public class ActualDocument {


	private int id;
	private String title;
	private String body;   
	private Timestamp creation;
	private Timestamp modification;
//	private String user;    //Temporary; user is meant to be a data type
	private String readValue=null;
	private File documentFile;
	private String filepath;
	
	public ActualDocument(String fp)
	{
		setFilePath(fp);
	}
	
	public void setFilePath(String newFilepath)
	{
		this.filepath=newFilepath;
		initialize();
	}

	public void initialize()
	{	
		
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
			creation=new Timestamp(new Date(System.currentTimeMillis()).getTime());
			modification=creation;
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

	public void printData()
	{
		System.out.println(title);
		System.out.println(body);
		System.out.println(filepath);
		System.out.println(creation);
		System.out.println(modification);
		System.out.println(body.indexOf("justo vitae"));
	}
	
	public boolean searchTitle(String toSearch){
		
		int check = title.indexOf(toSearch);

		if(check==-1)
			return false;

		return true;
	}
	
	public boolean searchBody(String toSearch){
		
		int check = body.indexOf(toSearch);

		if(check==-1)
			return false;

		return true;
	}
	

	public boolean searchAll(String toSearch){
		
		int check = body.indexOf(toSearch);

		if(check==-1)
			return false;
		
		check = title.indexOf(toSearch);

		if(check==-1)
			return false;
		
		return true;
	}

	public boolean searchById(int toSearch){
		
		if(id!=toSearch)
			return false;
		return true;
	}
}
