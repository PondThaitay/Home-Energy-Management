package www.cmsmarthome.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;

import android.util.Log;

public class Control_Process {
	
	private Socket socket = null;
	private final String DEBUG_TAG = "Home Energy Management";
	//5555555555555
	private String StatusLamp1;
	private String StatusLamp2;
	private String StatusLamp3;
	private String StatusLamp4;
	private String StatusLamp5;
	private String StatusLamp6;
	private String StatusLamp7;
	private String StatusLamp8;
	private String StatusLamp9;
	private String StatusLamp10;
	private String StatusLamp11;
	private String StatusLamp12;
	private String StatusLamp13;
	private String StatusLamp14;
	private String StatusLamp15;
	//
	private String messageStatus;

    //Method Control
	public void Control (String ip,String port,String msg)
	{	
		String message = msg;
		String IP_Address = ip;
		String Port = port;
		try{
			 socket = new Socket(IP_Address,Integer.valueOf(Port));
			 PrintWriter out = new PrintWriter(new BufferedWriter(
			 new OutputStreamWriter(socket.getOutputStream())),true);	
			 out.println(message);	 
			 out.close();
			 socket.close();	                    
		}
		catch (Exception e)
		{
			 Log.e(DEBUG_TAG, e.toString());
		}
	} 
    //End Method Control

//Method CheckStatus
public void CheckStatus(String ip, String port)
	{
	messageStatus = "$XPORTREADSTATE";
	String IP_Address = ip;
	String Port = port;
				try
				{
					socket = new Socket(IP_Address,Integer.valueOf(Port));

					PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())),true);

					out.println(messageStatus);

					BufferedReader in = new BufferedReader(new InputStreamReader( socket.getInputStream() ) );

					StatusLamp1=in.readLine(); 
					StatusLamp2=in.readLine();
					StatusLamp3=in.readLine();
					StatusLamp4=in.readLine();
					StatusLamp5=in.readLine();
					StatusLamp6=in.readLine();
					StatusLamp7=in.readLine();
					StatusLamp8=in.readLine();
					StatusLamp9=in.readLine();
					StatusLamp10=in.readLine(); 
					StatusLamp11=in.readLine();
					StatusLamp12=in.readLine();
					StatusLamp13=in.readLine();
					StatusLamp14=in.readLine();
					StatusLamp15=in.readLine();

                    in.close();
		        		 out.close();
		        		 socket.close();
		        }
		        	catch (Exception e)
		        		   {
		        				Log.e(DEBUG_TAG, e.toString());
		        		   }
	}
//End Method CheckStatus

    public String getStatusLamp1()
{
	return StatusLamp1;
}

    public String getStatusLamp2()
{
	return StatusLamp2;
}

    public String getStatusLamp3()
{
	return StatusLamp3;
}

    public String getStatusLamp4()
{
	return StatusLamp4;
}

    public String getStatusLamp5()
{
	return StatusLamp5;
}

    public String getStatusLamp6()
{
	return StatusLamp6;
}

    public String getStatusLamp7()
{
	return StatusLamp7;
}

    public String getStatusLamp8()
{
	return StatusLamp8;
}

    public String getStatusLamp9()
{
	return StatusLamp9;
}

    public String getStatusLamp10()
{
	return StatusLamp10;
}

    public String getStatusLamp11()
{
	return StatusLamp11;
}

    public String getStatusLamp12()
{
	return StatusLamp12;
}

    public String getStatusLamp13()
{
	return StatusLamp13;
}

    public String getStatusLamp14()
{
	return StatusLamp14;
}

    public String getStatusLamp15()
{
	return StatusLamp15;
}

}//End Class Control_Process