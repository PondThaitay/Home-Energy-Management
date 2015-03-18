package www.cmsmarthome.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import android.widget.Toast;

import InternetDetect.ConnectionDetector;

public class User {
	
private String StatusLogin;
private String StatusINsert;
private String StatusUpdate;
private String StatusDelete;
private String StatusGetData;
private String strUserDetailsID = "0";
private String Timer_ID = "0";
private String Timer_BackupID = "0";

//Get Data
protected String strMemberID = "";
protected String strUsername = "";
protected String strPassword = "";
protected String strIP = "";
protected String strPort = "";
//

//Login
public void checkLogin(String username,String password)
	{
		String url = "http://www.cm-smarthome.com/android/checkLogin.php";

        //String url = "http://192.168.2.143/project/checkLogin.php";

		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("strUser", username));
        params.add(new BasicNameValuePair("strPass", password));
      
    	String resultServer  = getHttpPost(url,params);
        
    	String strStatusID = "0";

    	JSONObject c;
		try {
			c = new JSONObject(resultServer);
        	strStatusID = c.getString("StatusID");
        	strUserDetailsID = c.getString("UserDetailsID");
            Timer_ID = c.getString("Timer_ID");
        	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Prepare Login
		if(strStatusID.equals("0"))
		{
			// Check
			StatusLogin="No";
		}
		else
		{
			StatusLogin="Yes";
		}
   	            
    }
	
public String  getStatus()
{
	return StatusLogin;
}

public String getID()
{
	return strUserDetailsID;
}

public String getTimer_ID()
    {
        return Timer_ID;
    }

public String getTimer_BackupID()
    {
        return Timer_BackupID;
    }
//end Login

//Insert
public void Insert(String Inuername,String Inpassword,String Inip,String Inport)
{
	String url = "http://www.cm-smarthome.com/android/saveADDData.php";

    //String url = "http://192.168.2.143/project/saveADDData.php";

	List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("sUsername",Inuername));
    params.add(new BasicNameValuePair("sPassword",Inpassword));
    params.add(new BasicNameValuePair("sIP",Inip));
    params.add(new BasicNameValuePair("sPort",Inport));
    
    String resultServer  = getHttpPost(url,params);
    
	String strStatusID = "0";
	//String strError = "Unknow Status!";
	
	JSONObject c;
	try {
		c = new JSONObject(resultServer);
    	strStatusID = c.getString("StatusID");
    	//strError = c.getString("Error");
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// Prepare Save Data
	if(strStatusID.equals("0"))
	{
        StatusINsert="ชื่อผู้ใช้นี้ใช้งานแล้ว";
	}
	else
	{
	    StatusINsert="เพิ่มข้อมูลผู้ใช้สำเร็จ";
	}
}

public String getInsert()
{
	return StatusINsert;
}
//end Insert

//get Data
public void getData(String UserDetailsID)
{
	String url = "http://www.cm-smarthome.com/android/getByMemberID.php";

    //String url = "http://192.168.2.143/project/getByMemberID.php";

	List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("sUserDetailsID", UserDetailsID));

	String resultServer  = getHttpPost(url,params);
	
	JSONObject c;
	try {
		c = new JSONObject(resultServer);
		strMemberID = c.getString("UserDetailsID");
		strUsername = c.getString("Username");
		strPassword = c.getString("Password");
		strIP = c.getString("IP_Address");
		strPort = c.getString("Port");
		
		if(!strMemberID.equals(""))
		{
			StatusGetData = "Yes";
		}
		else
		{
			StatusGetData = "No";
		}
    	
	} catch (JSONException e) {
		e.printStackTrace();
	}
}
public String getStatusGetData()
{
	return StatusGetData;
}
//

//Update
public void SaveData(String id,String u,String p,String ip,String port,String Ouser,String Oip)
{     
 		String url = "http://www.cm-smarthome.com/android/updateData.php";

        //String url = "http://192.168.2.143/project/updateData.php";

		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sUserDetailsID",id));
        params.add(new BasicNameValuePair("sUsername", u));
        params.add(new BasicNameValuePair("sPassword", p));
        params.add(new BasicNameValuePair("sIP", ip));
        params.add(new BasicNameValuePair("sPort", port));
        params.add(new BasicNameValuePair("oUsername", Ouser));
        params.add(new BasicNameValuePair("oIP", Oip));

        String resultServer  = getHttpPost(url,params);
        
    	String strStatusID = "0";

    	JSONObject c;
		try {
			c = new JSONObject(resultServer);
        	strStatusID = c.getString("StatusID");
        	//strError = c.getString("Error");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Prepare Save Data
		if(strStatusID.equals("0"))
		{
            StatusUpdate="No";
		}
		else
		{
			StatusUpdate="Yes";
		}
}
public String getStatusUpdate()
    {
        return StatusUpdate;
    }
//end Update
public void DeleteData(String id,String u,String p,String ip,String port,String Ouser,String Oip)
{
    String url = "http://www.cm-smarthome.com/android/deleteData.php";

    //String url = "http://192.168.2.143/project/deleteData.php";

    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("sUserDetailsID",id));
    params.add(new BasicNameValuePair("sUsername", u));
    params.add(new BasicNameValuePair("sPassword", p));
    params.add(new BasicNameValuePair("sIP", ip));
    params.add(new BasicNameValuePair("sPort", port));
    params.add(new BasicNameValuePair("oUsername", Ouser));
    params.add(new BasicNameValuePair("oIP", Oip));

    String resultServer  = getHttpPost(url,params);

    String strStatusID = "0";

    JSONObject c;
    try {
        c = new JSONObject(resultServer);
        strStatusID = c.getString("StatusID");
        //strError = c.getString("Error");
    } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    // Prepare Save Data
    if(strStatusID.equals("0"))
    {
        StatusDelete="No";
    }
    else
    {
        StatusDelete="Yes";
    }
}

public String getStatusDelete()
    {
        return StatusDelete ;
    }
//Delete User

//End Delet User

public String getHttpPost(String url,List<NameValuePair> params) 
	{
		StringBuilder str = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

			try 
				{
					httpPost.setEntity(new UrlEncodedFormEntity(params));
					HttpResponse response = client.execute(httpPost);
					StatusLine statusLine = response.getStatusLine();
					
					int statusCode = statusLine.getStatusCode();
	
						if (statusCode == 200)// Status OK
							{ 
								HttpEntity entity = response.getEntity();
								InputStream content = entity.getContent();
								BufferedReader reader = new BufferedReader(new InputStreamReader(content));
								String line;
									
									while ((line = reader.readLine()) != null) 
										{
											str.append(line);
										}
							} 
						else 
							{
								Log.e("Log", "Failed to download result..");
							}
				} 
			catch (ClientProtocolException e) 
				{
					e.printStackTrace();
				} 
			catch (IOException e) 
				{
					e.printStackTrace();
				}
return str.toString();
	}

}//End Class User
