package www.cmsmarthome.com;

import android.util.Log;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SumTime {

    //Get Data
    protected String strBedroom1 = "";
    protected String strBedroom2 = "";

    protected String strToiletroom1 = "";

    protected String strSaloonroom1 = "";
    protected String strSaloonroom2 = "";
    protected String strSaloonroom3 = "";
    protected String strSaloonroom4 = "";
    protected String strSaloonroom5 = "";

    protected String strOfficeroom1 = "";
    protected String strOfficeroom2 = "";

    protected String strCookroom1 = "";
    protected String strCookroom2 = "";

    protected String strParkroom1 = "";
    protected String strParkroom2 = "";

    protected String strFontDoor1 = "";
    //

    //Get Maximum Time
    protected String mBedroom1 = "";
    protected String mBedroom2 = "";

    protected String mToiletroom1 = "";

    protected String mSaloonroom1 = "";
    protected String mSaloonroom2 = "";
    protected String mSaloonroom3 = "";
    protected String mSaloonroom4 = "";
    protected String mSaloonroom5 = "";

    protected String mOfficeroom1 = "";
    protected String mOfficeroom2 = "";

    protected String mCookroom1 = "";
    protected String mCookroom2 = "";

    protected String mParkroom1 = "";
    protected String mParkroom2 = "";

    protected String mFontDoor1 = "";
    //

    //split :
    protected String spBedroom1 = "";
    protected String spBedroom2 = "";

    protected String spToiletroom1 = "";

    protected String spSaloonroom1 = "";
    protected String spSaloonroom2 = "";
    protected String spSaloonroom3 = "";
    protected String spSaloonroom4 = "";
    protected String spSaloonroom5 = "";

    protected String spOfficeroom1 = "";
    protected String spOfficeroom2 = "";

    protected String spCookroom1 = "";
    protected String spCookroom2 = "";

    protected String spParkroom1 = "";
    protected String spParkroom2 = "";

    protected String spFontDoor1 = "";
    //end split :

    //Update Time to Timer DB
    public boolean UpdateData(String Timeinput1, String Timeinput2, String Timeinput3, String Timeinput4, String Timeinput5
            , String Timeinput6, String Timeinput7, String Timeinput8, String Timeinput9, String Timeinput10, String Timeinput11
            , String Timeinput12, String Timeinput13, String Timeinput14, String Timeinput15, String Date, String ID) {

        String url = "http://www.cm-smarthome.com/android/updateData1.php";

        //String url = "http://192.168.2.143/project/updateData1.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sTimerID", ID));
        params.add(new BasicNameValuePair("sTimer1", Timeinput1));
        params.add(new BasicNameValuePair("sTimer2", Timeinput2));
        params.add(new BasicNameValuePair("sTimer3", Timeinput3));
        params.add(new BasicNameValuePair("sTimer4", Timeinput4));
        params.add(new BasicNameValuePair("sTimer5", Timeinput5));
        params.add(new BasicNameValuePair("sTimer6", Timeinput6));
        params.add(new BasicNameValuePair("sTimer7", Timeinput7));
        params.add(new BasicNameValuePair("sTimer8", Timeinput8));
        params.add(new BasicNameValuePair("sTimer9", Timeinput9));
        params.add(new BasicNameValuePair("sTimer10", Timeinput10));
        params.add(new BasicNameValuePair("sTimer11", Timeinput11));
        params.add(new BasicNameValuePair("sTimer12", Timeinput12));
        params.add(new BasicNameValuePair("sTimer13", Timeinput13));
        params.add(new BasicNameValuePair("sTimer14", Timeinput14));
        params.add(new BasicNameValuePair("sTimer15", Timeinput15));
        params.add(new BasicNameValuePair("sDate", Date));

        String resultServer = getHttpPost(url, params);

        String strStatusID = "0";
        String strError = "Unknow Status!";

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strStatusID = c.getString("StatusID");
            strError = c.getString("Error");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }
    //End Method Update Time to Timer DB

    //get Data From Timer DB
    public void getDataTimer(String time_ID, String IP) {

        String Timer_ID = time_ID;
        String IP_Address = IP;

        String url = "http://www.cm-smarthome.com/android/sumTime.php";

        //String url = "http://192.168.2.143/project/sumTime.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sID", Timer_ID));
        params.add(new BasicNameValuePair("sIP", IP_Address));

        String resultServer = getHttpPost(url, params);

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strBedroom1 = c.getString("BedRoom1");
            strBedroom2 = c.getString("BedRoom2");

            strToiletroom1 = c.getString("ToiletRoom1");

            strSaloonroom1 = c.getString("SaloonRoom1");
            strSaloonroom2 = c.getString("SaloonRoom2");
            strSaloonroom3 = c.getString("SaloonRoom3");
            strSaloonroom4 = c.getString("SaloonRoom4");
            strSaloonroom5 = c.getString("SaloonRoom5");

            strOfficeroom1 = c.getString("OfficeRoom1");
            strOfficeroom2 = c.getString("OfficeRoom2");

            strCookroom1 = c.getString("CookRoom1");
            strCookroom2 = c.getString("CookRoom2");

            strParkroom1 = c.getString("ParkRoom1");
            strParkroom2 = c.getString("ParkRoom2");

            strFontDoor1 = c.getString("FontDoor1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //End Method getTime From Timer DB

    //Save Time to timer_backup DB after Exit App
    public void BackupTime(String Username, String IP, String Bedroom1, String Bedroom2, String Toiletroom1, String Saloonroom1, String Saloonroom2, String Saloonroom3
            , String Saloonroom4, String Saloonroom5, String Offineroom1, String Officeroom2, String Cookroom1, String Cookroom2, String Parkroom1, String Parkroom2
            , String Fontdoor1, String Date) {
        String url = "http://www.cm-smarthome.com/android/backupTime.php";
        //String url = "http://192.168.2.143/project/backupTime.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sUsername", Username));
        params.add(new BasicNameValuePair("sIP", IP));
        params.add(new BasicNameValuePair("sBedRoom1", Bedroom1));
        params.add(new BasicNameValuePair("sBedRoom2", Bedroom2));
        params.add(new BasicNameValuePair("sToiletRoom1", Toiletroom1));
        params.add(new BasicNameValuePair("sSaloonRoom1", Saloonroom1));
        params.add(new BasicNameValuePair("sSaloonRoom2", Saloonroom2));
        params.add(new BasicNameValuePair("sSaloonRoom3", Saloonroom3));
        params.add(new BasicNameValuePair("sSaloonRoom4", Saloonroom4));
        params.add(new BasicNameValuePair("sSaloonRoom5", Saloonroom5));
        params.add(new BasicNameValuePair("sOfficeRoom1", Offineroom1));
        params.add(new BasicNameValuePair("sOfficeRoom2", Officeroom2));
        params.add(new BasicNameValuePair("sCookRoom1", Cookroom1));
        params.add(new BasicNameValuePair("sCookRoom2", Cookroom2));
        params.add(new BasicNameValuePair("sParkRoom1", Parkroom1));
        params.add(new BasicNameValuePair("sParkRoom2", Parkroom2));
        params.add(new BasicNameValuePair("sFontDoor1", Fontdoor1));
        params.add(new BasicNameValuePair("sDate", Date));

        String resultServer = getHttpPost(url, params);

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
    }
    //End Save Time

    //update MaxTime
    public void setMaxTime(String br1, String br2, String tr, String sl1, String sl2, String sl3, String sl4, String sl5
            , String or1, String or2, String co1, String co2, String pr1, String pr2, String fd, String ip) {

        String url = "http://www.cm-smarthome.com/android/saveMaxTime.php";

        //String url = "http://192.168.2.143/project/sumTime.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sIP", ip));
        params.add(new BasicNameValuePair("sBr1", br1));
        params.add(new BasicNameValuePair("sBr2", br2));
        params.add(new BasicNameValuePair("sToi", tr));
        params.add(new BasicNameValuePair("sSl1", sl1));
        params.add(new BasicNameValuePair("sSl2", sl2));
        params.add(new BasicNameValuePair("sSl3", sl3));
        params.add(new BasicNameValuePair("sSl4", sl4));
        params.add(new BasicNameValuePair("sSl5", sl5));
        params.add(new BasicNameValuePair("sOr1", or1));
        params.add(new BasicNameValuePair("sOr2", or2));
        params.add(new BasicNameValuePair("sCr1", co1));
        params.add(new BasicNameValuePair("sCr2", co2));
        params.add(new BasicNameValuePair("sPr1", pr1));
        params.add(new BasicNameValuePair("sPr2", pr2));
        params.add(new BasicNameValuePair("sFd", fd));

        String resultServer = getHttpPost(url, params);

        String strStatusID = "0";
        String strError = "Unknow Status!";

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strStatusID = c.getString("StatusID");
            strError = c.getString("Error");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //End update MaxTime

    //Get Maximum Time
    public void getMaxTime(String ip) {
        String url = "http://www.cm-smarthome.com/android/maxTime.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sIP", ip));

        String resultServer = getHttpPost(url, params);

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            mBedroom1 = c.getString("Bedroom1");
            mBedroom2 = c.getString("Bedroom2");

            mToiletroom1 = c.getString("Toiletroom");

            mSaloonroom1 = c.getString("Saloonroom1");
            mSaloonroom2 = c.getString("Saloonroom2");
            mSaloonroom3 = c.getString("Saloonroom3");
            mSaloonroom4 = c.getString("Saloonroom4");
            mSaloonroom5 = c.getString("Saloonroom5");

            mOfficeroom1 = c.getString("Officeroom1");
            mOfficeroom2 = c.getString("Officeroom2");

            mCookroom1 = c.getString("Cookroom1");
            mCookroom2 = c.getString("Cookroom2");

            mParkroom1 = c.getString("Parkroom1");
            mParkroom2 = c.getString("Parkroom2");

            mFontDoor1 = c.getString("Fontdoor");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //End Get Maximum Time

    //Reset Time to timer_backup DB
    public boolean ResetData(String IP, String Lamp) {

        String url = "http://www.cm-smarthome.com/android/resetData.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sIP", IP));
        params.add(new BasicNameValuePair("sLamp", Lamp));

        String resultServer = getHttpPost(url, params);

        String strStatusID = "0";
        String strError = "Unknow Status!";

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strStatusID = c.getString("StatusID");
            strError = c.getString("Error");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }
    //End Reset Time to timer_backup DB

    public void SplitString(String input1, String pinput1, String input2, String pinput2, String input3, String pinput3, String input4, String pinput4
            , String input5, String pinput5, String input6, String pinput6, String input7, String pinput7, String input8, String pinput8
            , String input9, String pinput9, String input10, String pinput10, String input11, String pinput11, String input12, String pinput12
            , String input13, String pinput13, String input14, String pinput14, String input15, String pinput15) {
        String br0[] = input1.split(":");
        String partbr0 = br0[0];
        String pbr0[] = pinput1.split(":");
        String part0 = pbr0[0];
        String br1[] = input2.split(":");
        String partbr1 = br1[0];
        String pbr1[] = pinput2.split(":");
        String part1 = pbr1[0];

        String br2[] = input3.split(":");
        String partbr2 = br2[0];
        String pbr2[] = pinput3.split(":");
        String part2 = pbr2[0];

        String br3[] = input4.split(":");
        String partbr3 = br3[0];
        String pbr3[] = pinput4.split(":");
        String part3 = pbr3[0];
        String br4[] = input5.split(":");
        String partbr4 = br4[0];
        String pbr4[] = pinput5.split(":");
        String part4 = pbr4[0];
        String br5[] = input6.split(":");
        String partbr5 = br5[0];
        String pbr5[] = pinput6.split(":");
        String part5 = pbr5[0];
        String br6[] = input7.split(":");
        String partbr6 = br6[0];
        String pbr6[] = pinput7.split(":");
        String part6 = pbr6[0];
        String br7[] = input8.split(":");
        String partbr7 = br7[0];
        String pbr7[] = pinput8.split(":");
        String part7 = pbr7[0];

        String br8[] = input9.split(":");
        String partbr8 = br8[0];
        String pbr8[] = pinput9.split(":");
        String part8 = pbr8[0];
        String br9[] = input10.split(":");
        String partbr9 = br9[0];
        String pbr9[] = pinput10.split(":");
        String part9 = pbr9[0];

        String br10[] = input11.split(":");
        String partbr10 = br10[0];
        String pbr10[] = pinput11.split(":");
        String part10 = pbr10[0];
        String br11[] = input12.split(":");
        String partbr11 = br11[0];
        String pbr11[] = pinput12.split(":");
        String part11 = pbr11[0];

        String br12[] = input13.split(":");
        String partbr12 = br12[0];
        String pbr12[] = pinput13.split(":");
        String part12 = pbr12[0];
        String br13[] = input14.split(":");
        String partbr13 = br13[0];
        String pbr13[] = pinput14.split(":");
        String part13 = pbr13[0];

        String br14[] = input15.split(":");
        String partbr14 = br14[0];
        String pbr14[] = pinput15.split(":");
        String part14 = pbr14[0];


        int x0 = Integer.valueOf(partbr0);
        int y0 = Integer.valueOf(part0);
        int x1 = Integer.valueOf(partbr1);
        int y1 = Integer.valueOf(part1);

        int x2 = Integer.valueOf(partbr2);
        int y2 = Integer.valueOf(part2);

        int x3 = Integer.valueOf(partbr3);
        int y3 = Integer.valueOf(part3);
        int x4 = Integer.valueOf(partbr4);
        int y4 = Integer.valueOf(part4);
        int x5 = Integer.valueOf(partbr5);
        int y5 = Integer.valueOf(part5);
        int x6 = Integer.valueOf(partbr6);
        int y6 = Integer.valueOf(part6);
        int x7 = Integer.valueOf(partbr7);
        int y7 = Integer.valueOf(part7);

        int x8 = Integer.valueOf(partbr8);
        int y8 = Integer.valueOf(part8);
        int x9 = Integer.valueOf(partbr9);
        int y9 = Integer.valueOf(part9);

        int x10 = Integer.valueOf(partbr10);
        int y10 = Integer.valueOf(part10);
        int x11 = Integer.valueOf(partbr11);
        int y11 = Integer.valueOf(part11);

        int x12 = Integer.valueOf(partbr12);
        int y12 = Integer.valueOf(part12);
        int x13 = Integer.valueOf(partbr13);
        int y13 = Integer.valueOf(part13);

        int x14 = Integer.valueOf(partbr14);
        int y14 = Integer.valueOf(part14);

        String waning = "1";
        String normal = "0";

        if (x0 >= (y0 - 24)) {
            spBedroom1 = waning;
        } else {
            spBedroom1 = normal;
        }

        if (x1 >= (y1 - 24)) {
            spBedroom2 = waning;
        } else {
            spBedroom2 = normal;
        }

        if (x2 >= (y2 - 24)) {
            spToiletroom1 = waning;
        } else {
            spToiletroom1 = normal;
        }

        if (x3 >= (y3 - 24)) {
            spSaloonroom1 = waning;
        } else {
            spSaloonroom1 = normal;
        }

        if (x4 >= (y4 - 24)) {
            spSaloonroom2 = waning;
        } else {
            spSaloonroom2 = normal;
        }

        if (x5 >= (y5 - 24)) {
            spSaloonroom3 = waning;
        } else {
            spSaloonroom3 = normal;
        }

        if (x6 >= (y6 - 24)) {
            spSaloonroom4 = waning;
        } else {
            spSaloonroom4 = normal;
        }

        if (x7 >= (y7 - 24)) {
            spSaloonroom5 = waning;
        } else {
            spSaloonroom5 = normal;
        }

        if (x8 >= (y8 - 24)) {
            spOfficeroom1 = waning;
        } else {
            spOfficeroom1 = normal;
        }

        if (x9 >= (y9 - 24)) {
            spOfficeroom2 = waning;
        } else {
            spOfficeroom2 = normal;
        }

        if (x10 >= (y10 - 24)) {
            spCookroom1 = waning;
        } else {
            spCookroom1 = normal;
        }

        if (x11 >= (y11 - 24)) {
            spCookroom2 = waning;
        } else {
            spCookroom2 = normal;
        }

        if (x12 >= (y12 - 24)) {
            spParkroom1 = waning;
        } else {
            spParkroom1 = normal;
        }

        if (x13 >= (y13 - 24)) {
            spParkroom2 = waning;
        } else {
            spParkroom2 = normal;
        }

        if (x14 >= (y14 - 24)) {
            spFontDoor1 = waning;
        } else {
            spFontDoor1 = normal;
        }
    }

    public String getHttpPost(String url, List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}//End Class SumTime
