package www.cmsmarthome.com;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Graph {

    Calendar calendar = Calendar.getInstance();
    int cDay = calendar.get(Calendar.DATE);
    int cMonth = calendar.get(Calendar.MONTH);
    int cYear = calendar.get(Calendar.YEAR);

    String d = String.valueOf(cDay);
    String m = String.valueOf(ConvertMonth(cMonth));
    String y = String.valueOf(cYear);

    String BedRoom, ToiletRoom, SaloonRoom, OfficeRoom, CookRoom, ParkRoom, FontDoor;

    public static double xD1,xD2,xD3,xD4,xD5,xD6,xD7,resultDay;

    //Get Watt Lamp
    protected String wBedroom1 = "";
    protected String wBedroom2 = "";
    protected String wToiletroom1 = "";
    protected String wSaloonroom1 = "";
    protected String wSaloonroom2 = "";
    protected String wSaloonroom3 = "";
    protected String wSaloonroom4 = "";
    protected String wSaloonroom5 = "";
    protected String wOfficeroom1 = "";
    protected String wOfficeroom2 = "";
    protected String wCookroom1 = "";
    protected String wCookroom2 = "";
    protected String wParkroom1 = "";
    protected String wParkroom2 = "";
    protected String wFontDoor1 = "";
    //

    public static  int wBedRoom, wToilet, wSaloon, wOffice, wCook, wPark, wFontDoor;

    public int ConvertMonth(int month)
    {
        int cM;
        if(month <= 10)
        {
            cM = (month + 1) % 12;
        }
        else {cM = 12;}
        return cM;
    }

    //getTime Day
    public void getTimeRoomDay(String day, String month, String year, String status, String time_ID, String IP)
    {

        String Timer_ID = time_ID;
        String IP_Address = IP;

        String url = "http://www.cm-smarthome.com/android/sumTimeDay.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sID",Timer_ID ));
        params.add(new BasicNameValuePair("sIP",IP_Address ));
        params.add(new BasicNameValuePair("sDay",day ));
        params.add(new BasicNameValuePair("sMonth",month ));
        params.add(new BasicNameValuePair("sYear",year ));
        params.add(new BasicNameValuePair("sStatus",status ));

        String resultServer  = getHttpPost(url,params);

        JSONObject c;
        try {
            c = new JSONObject(resultServer);

            BedRoom = c.getString("BedRoom");
            ToiletRoom = c.getString("ToiletRoom");
            SaloonRoom = c.getString("SaloonRoom");
            OfficeRoom = c.getString("OfficeRoom");
            CookRoom = c.getString("CookRoom");
            ParkRoom = c.getString("ParkRoom");
            FontDoor = c.getString("FontDoor");

            calculateUnit(BedRoom,ToiletRoom,SaloonRoom,OfficeRoom, CookRoom,ParkRoom,FontDoor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }//End getTime Day

    //getTime Month
    public void getTimeRoomMonth(String month, String year, String status, String time_ID, String IP)
    {

        String Timer_ID = time_ID;
        String IP_Address = IP;

        String url = "http://www.cm-smarthome.com/android/sumTimeMonth.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sID",Timer_ID ));
        params.add(new BasicNameValuePair("sIP",IP_Address ));
        params.add(new BasicNameValuePair("sMonth",month ));
        params.add(new BasicNameValuePair("sYear",year ));
        params.add(new BasicNameValuePair("sStatus",status ));

        String resultServer  = getHttpPost(url,params);

        JSONObject c;
        try {
            c = new JSONObject(resultServer);

            BedRoom = c.getString("BedRoom");
            ToiletRoom = c.getString("ToiletRoom");
            SaloonRoom = c.getString("SaloonRoom");
            OfficeRoom = c.getString("OfficeRoom");
            CookRoom = c.getString("CookRoom");
            ParkRoom = c.getString("ParkRoom");
            FontDoor = c.getString("FontDoor");

            calculateUnit(BedRoom,ToiletRoom,SaloonRoom,OfficeRoom, CookRoom,ParkRoom,FontDoor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }//End getTime Month

    //getTime Year
    public void getTimeRoomYear(String year, String status, String time_ID, String IP)
    {

        String Timer_ID = time_ID;
        String IP_Address = IP;

        String url = "http://www.cm-smarthome.com/android/sumTimeYear.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sID",Timer_ID ));
        params.add(new BasicNameValuePair("sIP",IP_Address ));
        params.add(new BasicNameValuePair("sYear",year ));
        params.add(new BasicNameValuePair("sStatus",status ));

        String resultServer  = getHttpPost(url,params);

        JSONObject c;
        try {
            c = new JSONObject(resultServer);

            BedRoom = c.getString("BedRoom");
            ToiletRoom = c.getString("ToiletRoom");
            SaloonRoom = c.getString("SaloonRoom");
            OfficeRoom = c.getString("OfficeRoom");
            CookRoom = c.getString("CookRoom");
            ParkRoom = c.getString("ParkRoom");
            FontDoor = c.getString("FontDoor");

            calculateUnit(BedRoom,ToiletRoom,SaloonRoom,OfficeRoom, CookRoom,ParkRoom,FontDoor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }//End getTime Year

    //Get Watt
    public void getWatt(String ip)
    {
        String url = "http://www.cm-smarthome.com/android/getWatt.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sIP",ip));

        String resultServer  = getHttpPost(url,params);

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            wBedroom1 = c.getString("Bedroom1");
            wBedroom2 = c.getString("Bedroom2");

            wToiletroom1 = c.getString("Toiletroom");

            wSaloonroom1 = c.getString("Saloonroom1");
            wSaloonroom2 = c.getString("Saloonroom2");
            wSaloonroom3 = c.getString("Saloonroom3");
            wSaloonroom4 = c.getString("Saloonroom4");
            wSaloonroom5 = c.getString("Saloonroom5");

            wOfficeroom1 = c.getString("Officeroom1");
            wOfficeroom2 = c.getString("Officeroom2");

            wCookroom1 = c.getString("Cookroom1");
            wCookroom2 = c.getString("Cookroom2");

            wParkroom1 = c.getString("Parkroom1");
            wParkroom2 = c.getString("Parkroom2");

            wFontDoor1 = c.getString("Fontdoor");

            wBedRoom = (Integer.parseInt(wBedroom1)+Integer.parseInt(wBedroom2))/2;
            wToilet = Integer.parseInt(wToiletroom1);
            wSaloon = (Integer.parseInt(wSaloonroom1)+Integer.parseInt(wSaloonroom2)+Integer.parseInt(wSaloonroom3)
                      +Integer.parseInt(wSaloonroom4)+Integer.parseInt(wSaloonroom5))/5;
            wOffice = (Integer.parseInt(wOfficeroom1)+Integer.parseInt(wOfficeroom2))/2;
            wCook = (Integer.parseInt(wCookroom1)+Integer.parseInt(wCookroom2))/2;
            wPark = (Integer.parseInt(wParkroom1)+Integer.parseInt(wParkroom2))/2;
            wFontDoor = Integer.parseInt(wFontDoor1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //End Get Watt

    //update Watt
    public void setWatt(String wbr1,String wbr2,String wtr,String wsl1,String wsl2,String wsl3,String wsl4,String wsl5
            ,String wor1,String wor2,String wco1,String wco2,String wpr1,String wpr2,String wfd,String wip)
    {

        String url = "http://www.cm-smarthome.com/android/saveWatt.php";

        //String url = "http://192.168.2.143/project/sumTime.php";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("sIP",wip));
        params.add(new BasicNameValuePair("sBr1",wbr1));
        params.add(new BasicNameValuePair("sBr2",wbr2));
        params.add(new BasicNameValuePair("sToi",wtr));
        params.add(new BasicNameValuePair("sSl1",wsl1));
        params.add(new BasicNameValuePair("sSl2",wsl2));
        params.add(new BasicNameValuePair("sSl3",wsl3));
        params.add(new BasicNameValuePair("sSl4",wsl4));
        params.add(new BasicNameValuePair("sSl5",wsl5));
        params.add(new BasicNameValuePair("sOr1",wor1));
        params.add(new BasicNameValuePair("sOr2",wor2));
        params.add(new BasicNameValuePair("sCr1",wco1));
        params.add(new BasicNameValuePair("sCr2",wco2));
        params.add(new BasicNameValuePair("sPr1",wpr1));
        params.add(new BasicNameValuePair("sPr2",wpr2));
        params.add(new BasicNameValuePair("sFd",wfd));

        String resultServer  = getHttpPost(url,params);

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
    //End update Watt

    public void calculateUnit(String x1,String x2,String x3,String x4,String x5,String x6,String x7)
    {
        int rO = 1;
        int rT = 2;
        int rF = 5;
        double st = 1000.00;

        String[] part1 = x1.split(":");
        String part01 = part1[0];

        String[] part2 = x2.split(":");
        String part02 = part2[0];

        String[] part3 = x3.split(":");
        String part03 = part3[0];

        String[] part4 = x4.split(":");
        String part04 = part4[0];

        String[] part5 = x5.split(":");
        String part05 = part5[0];

        String[] part6 = x6.split(":");
        String part06 = part6[0];

        String[] part7 = x7.split(":");
        String part07 = part7[0];

        xD1 = ((wBedRoom * rT)/st)*Double.parseDouble(part01);
        xD2 = ((wToilet * rO)/st)*Double.parseDouble(part02);
        xD3 = ((wSaloon * rF)/st)*Double.parseDouble(part03);
        xD4 = ((wOffice * rT)/st)*Double.parseDouble(part04);
        xD5 = ((wCook * rT)/st)*Double.parseDouble(part05);
        xD6 = ((wPark * rT)/st)*Double.parseDouble(part06);
        xD7 = ((wFontDoor * rO)/st)*Double.parseDouble(part07);

        DecimalFormat df = new DecimalFormat("#.##");

        xD1 = Double.valueOf(df.format(xD1));
        xD2 = Double.valueOf(df.format(xD2));
        xD3 = Double.valueOf(df.format(xD3));
        xD4 = Double.valueOf(df.format(xD4));
        xD5 = Double.valueOf(df.format(xD5));
        xD6 = Double.valueOf(df.format(xD6));
        xD7 = Double.valueOf(df.format(xD7));

        resultDay = xD1+xD2+xD3+xD4+xD5+xD6+xD7;
        resultDay = Double.valueOf(df.format(resultDay));
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

    //Bar Graph to Day and Month
    public static class PlaceholderFragmentBarDM extends Fragment {

        public PlaceholderFragmentBarDM() {}

        private View mView;
        private GraphicalView mGraphView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mView = rootView;
            initData(xD1,xD2,xD3,xD4,xD5,xD6,xD7);
            return rootView;
        }

        private void initData(double x1, double x2 , double x3 ,double x4 ,double x5,double x6 ,double x7) {
            String[] months = {"ห้องนอน","ห้องน้ำ","ห้องนั่งเล่น","ห้องทำงาน","ห้องครัว","หน้าบ้าน","ที่จอดรถ"};

            int[] index = {1,2,3,4,5,6,7};

            double[] incomeA = {x1,x2,x3,x4,x5,x6,x7};

            XYSeries seriesA = new XYSeries("หน่วยไฟที่ใช้");

            int length = index.length;
            for (int i = 0; i < length; i++) {
                seriesA.add(index[i], incomeA[i]);
            }

            XYSeriesRenderer rendererA = new XYSeriesRenderer();
            rendererA.setColor(Color.rgb(178, 34, 34));
            rendererA.setDisplayChartValues(true);
            rendererA.setChartValuesTextSize(15);

            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            dataset.addSeries(seriesA);

            XYMultipleSeriesRenderer multipleSeriesRenderer = new XYMultipleSeriesRenderer();

            for (int i = 0; i < length; i++) {
                multipleSeriesRenderer.addXTextLabel(i + 1, months[i]);
            }
            multipleSeriesRenderer.setChartTitle("กราฟแท่ง แสดงหน่วยไฟที่ใช้");
            multipleSeriesRenderer.setYTitle("หน่วยไฟ");
            multipleSeriesRenderer.setZoomButtonsVisible(true);

            multipleSeriesRenderer.setXLabels(0);
            multipleSeriesRenderer.setBackgroundColor(Color.WHITE);
            multipleSeriesRenderer.setApplyBackgroundColor(true);
            multipleSeriesRenderer.setMarginsColor(Color.WHITE);
            multipleSeriesRenderer.setLabelsColor(Color.BLACK);
            multipleSeriesRenderer.setAxesColor(Color.GRAY);
            multipleSeriesRenderer.setYLabelsColor(0, Color.BLACK);
            multipleSeriesRenderer.setXLabelsColor(Color.BLACK);
            multipleSeriesRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
            multipleSeriesRenderer.setBarSpacing(0.05);

            multipleSeriesRenderer.addSeriesRenderer(rendererA);

            drawChart(dataset, multipleSeriesRenderer);
        }

        private void drawChart(XYMultipleSeriesDataset dataset,XYMultipleSeriesRenderer renderer) {

            if (null == mGraphView) {
                mGraphView = ChartFactory.getBarChartView(getActivity(), dataset, renderer, BarChart.Type.DEFAULT);

                RelativeLayout container = (RelativeLayout) mView.findViewById(R.id.graph_container);

                container.addView(mGraphView);
            } else {
                mGraphView.repaint();
            }
        }
    }//End Bar Graph to Day and Month

    //Line Graph to Day and Month
    public static class PlaceholderFragmentLineDM extends Fragment {

        public PlaceholderFragmentLineDM() {}

        private View mView;
        private GraphicalView mGraphView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mView = rootView;
            initData(xD1,xD2,xD3,xD4,xD5,xD6,xD7);
            return rootView;
        }

        private void initData(double x1, double x2 , double x3 ,double x4 ,double x5,double x6 ,double x7) {
            String[] months = {"ห้องนอน","ห้องน้ำ","ห้องนั่งเล่น","ห้องทำงาน","ห้องครัว","หน้าบ้าน","ที่จอดรถ"};

            int[] index = {1,2,3,4,5,6,7};

            double[] incomeA = {x1,x2,x3,x4,x5,x6,x7};

            XYSeries seriesA = new XYSeries("หน่วยไฟที่ใช้");

            int length = index.length;
            for (int i = 0; i < length; i++) {
                seriesA.add(index[i], incomeA[i]);
            }

            XYSeriesRenderer rendererA = new XYSeriesRenderer();
            rendererA.setPointStyle(PointStyle.CIRCLE);
            rendererA.setPointStrokeWidth(3);
            rendererA.setColor(Color.rgb(139, 37, 0));
            rendererA.setLineWidth(2);
            rendererA.setDisplayChartValues(true);
            rendererA.setChartValuesTextSize(15);
            rendererA.setChartValuesTextAlign(Paint.Align.RIGHT);

            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            dataset.addSeries(seriesA);

            XYMultipleSeriesRenderer multipleSeriesRenderer
                    = new XYMultipleSeriesRenderer();

            for (int i = 0; i < length; i++) {
                multipleSeriesRenderer.addXTextLabel(i + 1, months[i]);
            }
            multipleSeriesRenderer.setChartTitle("กราฟแท่ง แสดงหน่วยไฟที่ใช้");
            multipleSeriesRenderer.setYTitle("หน่วยไฟ");
            multipleSeriesRenderer.setZoomButtonsVisible(true);
            multipleSeriesRenderer.setXLabels(0);
            multipleSeriesRenderer.setBackgroundColor(Color.WHITE);
            multipleSeriesRenderer.setApplyBackgroundColor(true);
            multipleSeriesRenderer.setMarginsColor(Color.WHITE);
            multipleSeriesRenderer.setLabelsColor(Color.BLACK);
            multipleSeriesRenderer.setAxesColor(Color.GRAY);
            multipleSeriesRenderer.setYLabelsColor(0, Color.BLACK);
            multipleSeriesRenderer.setXLabelsColor(Color.BLACK);

            multipleSeriesRenderer.addSeriesRenderer(rendererA);

            drawChart(dataset, multipleSeriesRenderer);
        }

        private void drawChart(XYMultipleSeriesDataset dataset,
                               XYMultipleSeriesRenderer renderer) {

            if (null == mGraphView) {
                mGraphView =
                        ChartFactory.getLineChartView(getActivity(), dataset, renderer);

                RelativeLayout container =
                        (RelativeLayout) mView.findViewById(R.id.graph_container);

                container.addView(mGraphView);
            } else {
                mGraphView.repaint();
            }
        }
    }//End Line Graph to Day and Month

    //Bar Year
    public static class PlaceholderFragmentBar extends Fragment {

        public PlaceholderFragmentBar() {}

        private View mView;
        private GraphicalView mGraphView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mView = rootView;
            initData();
            return rootView;
        }

        private void initData() {
            String[] months = {
                    "ม.ค.", "ก.พ.", "มึ.ค.", "เม.ย.", "พ.ค.", "มิ.ย.",
                    "ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค."
            };

            int[] index = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12};

            int[] incomeA = { 150, 55, 70, 80, 120, 90, 200, 100, 180, 210, 250, 160};
            int[] incomeA1 = { 20, 30, 40, 50, 60,  70, 80,  90,  100, 200, 300, 55};
            int[] incomeA2 = { 80, 60,200, 50, 110, 95, 250, 180, 190, 200, 210, 220};
            int[] incomeA3 = { 80, 50, 75, 80, 125, 50, 215, 123, 124, 256, 257, 190};
            int[] incomeA4 = { 78, 60, 70, 85, 120, 100,210, 140, 185, 220, 250, 131};
            int[] incomeA5 = { 30, 40, 79, 95, 150, 190,220, 160, 186, 230, 260, 121};
            int[] incomeA6 = { 40, 30, 80, 100, 160,170,230, 200, 187, 280, 50, 146};

            XYSeries seriesA = new XYSeries("ห้องนอน");
            XYSeries seriesA1 = new XYSeries("ห้องนั่งเล่น");
            XYSeries seriesA2 = new XYSeries("ห้องน้ำ");
            XYSeries seriesA3 = new XYSeries("ห้องครัว");
            XYSeries seriesA4 = new XYSeries("ห้องทำงาน");
            XYSeries seriesA5 = new XYSeries("หน้าบ้าน");
            XYSeries seriesA6 = new XYSeries("ที่จอดรถ");


            int length = index.length;
            for (int i = 0; i < length; i++) {
                seriesA.add(index[i], incomeA[i]);
                seriesA1.add(index[i], incomeA1[i]);
                seriesA2.add(index[i], incomeA2[i]);
                seriesA3.add(index[i], incomeA3[i]);
                seriesA4.add(index[i], incomeA4[i]);
                seriesA5.add(index[i], incomeA5[i]);
                seriesA6.add(index[i], incomeA6[i]);
            }

            XYSeriesRenderer rendererA = new XYSeriesRenderer();
            rendererA.setColor(Color.rgb(178, 34, 34));
            rendererA.setDisplayChartValues(true);
            rendererA.setChartValuesTextSize(15);

            XYSeriesRenderer rendererA1 = new XYSeriesRenderer();
            rendererA1.setColor(Color.rgb(132, 112, 255));
            rendererA1.setDisplayChartValues(true);
            rendererA1.setChartValuesTextSize(15);

            XYSeriesRenderer rendererA2 = new XYSeriesRenderer();
            rendererA2.setColor(Color.rgb(176, 224, 230));
            rendererA2.setDisplayChartValues(true);
            rendererA2.setChartValuesTextSize(15);

            XYSeriesRenderer rendererA3 = new XYSeriesRenderer();
            rendererA3.setColor(Color.rgb(0, 255, 0));
            rendererA3.setDisplayChartValues(true);
            rendererA3.setChartValuesTextSize(15);

            XYSeriesRenderer rendererA4 = new XYSeriesRenderer();
            rendererA4.setColor(Color.rgb(0, 250, 154));
            rendererA4.setDisplayChartValues(true);
            rendererA4.setChartValuesTextSize(15);

            XYSeriesRenderer rendererA5 = new XYSeriesRenderer();
            rendererA5.setColor(Color.rgb(139, 105, 20));
            rendererA5.setDisplayChartValues(true);
            rendererA5.setChartValuesTextSize(15);

            XYSeriesRenderer rendererA6 = new XYSeriesRenderer();
            rendererA6.setColor(Color.rgb(205, 155, 155));
            rendererA6.setDisplayChartValues(true);
            rendererA6.setChartValuesTextSize(15);

            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            dataset.addSeries(seriesA);
            dataset.addSeries(seriesA1);
            dataset.addSeries(seriesA2);
            dataset.addSeries(seriesA3);
            dataset.addSeries(seriesA4);
            dataset.addSeries(seriesA5);
            dataset.addSeries(seriesA6);

            XYMultipleSeriesRenderer multipleSeriesRenderer
                    = new XYMultipleSeriesRenderer();

            for (int i = 0; i < length; i++) {
                multipleSeriesRenderer.addXTextLabel(i + 1, months[i]);
            }
            multipleSeriesRenderer.setChartTitle("กราฟแท่ง แสดงหน่วยไฟที่ใช้");
            multipleSeriesRenderer.setYTitle("หน่วยไฟ");
            multipleSeriesRenderer.setXTitle("ปี พ.ศ. 2558");
            multipleSeriesRenderer.setZoomButtonsVisible(true);

            multipleSeriesRenderer.setXLabels(0);
            multipleSeriesRenderer.setBackgroundColor(Color.WHITE);
            multipleSeriesRenderer.setApplyBackgroundColor(true);
            multipleSeriesRenderer.setMarginsColor(Color.WHITE);
            multipleSeriesRenderer.setLabelsColor(Color.BLACK);
            multipleSeriesRenderer.setAxesColor(Color.GRAY);
            multipleSeriesRenderer.setYLabelsColor(0, Color.BLACK);
            multipleSeriesRenderer.setXLabelsColor(Color.BLACK);
            multipleSeriesRenderer.setBarSpacing(0.4);
            multipleSeriesRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);

            multipleSeriesRenderer.addSeriesRenderer(rendererA);
            multipleSeriesRenderer.addSeriesRenderer(rendererA1);
            multipleSeriesRenderer.addSeriesRenderer(rendererA2);
            multipleSeriesRenderer.addSeriesRenderer(rendererA3);
            multipleSeriesRenderer.addSeriesRenderer(rendererA4);
            multipleSeriesRenderer.addSeriesRenderer(rendererA5);
            multipleSeriesRenderer.addSeriesRenderer(rendererA6);

            drawChart(dataset, multipleSeriesRenderer);
        }

        private void drawChart(XYMultipleSeriesDataset dataset,XYMultipleSeriesRenderer renderer) {

            if (null == mGraphView) {
                mGraphView = ChartFactory.getBarChartView(getActivity(), dataset, renderer, BarChart.Type.DEFAULT);

                RelativeLayout container = (RelativeLayout) mView.findViewById(R.id.graph_container);

                container.addView(mGraphView);
            } else {
                mGraphView.repaint();
            }
        }

    }//End Bar Year

    //Line Year
    public static class PlaceholderFragmentLine extends Fragment {

        public PlaceholderFragmentLine() {}

        private View mView;
        private GraphicalView mGraphView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mView = rootView;
            initData();
            return rootView;
        }

        private void initData() {
            String[] months = {
                    "ม.ค.", "ก.พ.", "มึ.ค.", "เม.ย.", "พ.ค.", "มิ.ย.",
                    "ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค."
            };

            int[] index = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12};

            int[] incomeA = {150, 55, 70, 80, 120, 90, 200, 100, 180, 210, 250, 160};
            int[] incomeA1 = { 20, 30, 40, 50, 60,  70, 80,  90,  100, 200, 300, 55};
            int[] incomeA2 = { 80, 60,200, 50, 110, 95, 250, 180, 190, 200, 210, 220};
            int[] incomeA3 = { 80, 50, 75, 80, 125, 50, 215, 123, 124, 256, 257, 190};
            int[] incomeA4 = { 78, 60, 70, 85, 120, 100,210, 140, 185, 220, 250, 131};
            int[] incomeA5 = { 30, 40, 79, 95, 150, 190,220, 160, 186, 230, 260, 121};
            int[] incomeA6 = { 40, 30, 80, 100, 160,170,230, 200, 187, 280, 50, 146};

            XYSeries seriesA = new XYSeries("ห้องนอน");
            XYSeries seriesA1 = new XYSeries("ห้องนั่งเล่น");
            XYSeries seriesA2 = new XYSeries("ห้องน้ำ");
            XYSeries seriesA3 = new XYSeries("ห้องครัว");
            XYSeries seriesA4 = new XYSeries("ห้องทำงาน");
            XYSeries seriesA5 = new XYSeries("หน้าบ้าน");
            XYSeries seriesA6 = new XYSeries("ที่จอดรถ");

            int length = index.length;
            for (int i = 0; i < length; i++) {
                seriesA.add(index[i], incomeA[i]);
                seriesA1.add(index[i], incomeA1[i]);
                seriesA2.add(index[i], incomeA2[i]);
                seriesA3.add(index[i], incomeA3[i]);
                seriesA4.add(index[i], incomeA4[i]);
                seriesA5.add(index[i], incomeA5[i]);
                seriesA6.add(index[i], incomeA6[i]);
            }

            XYSeriesRenderer rendererA = new XYSeriesRenderer();
            rendererA.setPointStyle(PointStyle.CIRCLE);
            rendererA.setPointStrokeWidth(3);
            rendererA.setColor(Color.rgb(139, 37, 0));
            rendererA.setLineWidth(2);
            rendererA.setDisplayChartValues(true);
            rendererA.setChartValuesTextSize(15);
            rendererA.setChartValuesTextAlign(Paint.Align.RIGHT);

            XYSeriesRenderer rendererA1 = new XYSeriesRenderer();
            rendererA1.setColor(Color.rgb(132, 112, 255));
            rendererA1.setPointStyle(PointStyle.CIRCLE);
            rendererA1.setPointStrokeWidth(3);
            rendererA1.setLineWidth(2);
            rendererA1.setDisplayChartValues(true);
            rendererA1.setChartValuesTextSize(15);
            rendererA1.setChartValuesTextAlign(Paint.Align.RIGHT);

            XYSeriesRenderer rendererA2 = new XYSeriesRenderer();
            rendererA2.setColor(Color.rgb(176, 224, 230));
            rendererA2.setPointStyle(PointStyle.CIRCLE);
            rendererA2.setPointStrokeWidth(3);
            rendererA2.setLineWidth(2);
            rendererA2.setDisplayChartValues(true);
            rendererA2.setChartValuesTextSize(15);
            rendererA2.setChartValuesTextAlign(Paint.Align.RIGHT);

            XYSeriesRenderer rendererA3 = new XYSeriesRenderer();
            rendererA3.setColor(Color.rgb(0, 255, 0));
            rendererA3.setPointStyle(PointStyle.CIRCLE);
            rendererA3.setPointStrokeWidth(3);
            rendererA3.setLineWidth(2);
            rendererA3.setDisplayChartValues(true);
            rendererA3.setChartValuesTextSize(15);
            rendererA3.setChartValuesTextAlign(Paint.Align.RIGHT);

            XYSeriesRenderer rendererA4 = new XYSeriesRenderer();
            rendererA4.setColor(Color.rgb(0, 250, 154));
            rendererA4.setPointStyle(PointStyle.CIRCLE);
            rendererA4.setPointStrokeWidth(3);
            rendererA4.setLineWidth(2);
            rendererA4.setDisplayChartValues(true);
            rendererA4.setChartValuesTextSize(15);
            rendererA4.setChartValuesTextAlign(Paint.Align.RIGHT);

            XYSeriesRenderer rendererA5 = new XYSeriesRenderer();
            rendererA5.setColor(Color.rgb(139, 105, 20));
            rendererA5.setPointStyle(PointStyle.CIRCLE);
            rendererA5.setPointStrokeWidth(3);
            rendererA5.setLineWidth(2);
            rendererA5.setDisplayChartValues(true);
            rendererA5.setChartValuesTextSize(15);
            rendererA5.setChartValuesTextAlign(Paint.Align.RIGHT);

            XYSeriesRenderer rendererA6 = new XYSeriesRenderer();
            rendererA6.setColor(Color.rgb(205, 155, 155));
            rendererA6.setPointStyle(PointStyle.CIRCLE);
            rendererA6.setPointStrokeWidth(3);
            rendererA6.setLineWidth(2);
            rendererA6.setDisplayChartValues(true);
            rendererA6.setChartValuesTextSize(15);
            rendererA6.setChartValuesTextAlign(Paint.Align.RIGHT);

            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            dataset.addSeries(seriesA);
            dataset.addSeries(seriesA1);
            dataset.addSeries(seriesA2);
            dataset.addSeries(seriesA3);
            dataset.addSeries(seriesA4);
            dataset.addSeries(seriesA5);
            dataset.addSeries(seriesA6);

            XYMultipleSeriesRenderer multipleSeriesRenderer
                    = new XYMultipleSeriesRenderer();

            for (int i = 0; i < length; i++) {
                multipleSeriesRenderer.addXTextLabel(i + 1, months[i]);
            }
            multipleSeriesRenderer.setChartTitle("กราฟเส้น แสดงหน่วยไฟที่ใช้");
            multipleSeriesRenderer.setYTitle("หน่วยไฟ");
            multipleSeriesRenderer.setXTitle("ปี พ.ศ. 2558");
            multipleSeriesRenderer.setZoomButtonsVisible(true);

            multipleSeriesRenderer.setXLabels(0);
            multipleSeriesRenderer.setBackgroundColor(Color.WHITE);
            multipleSeriesRenderer.setApplyBackgroundColor(true);
            multipleSeriesRenderer.setMarginsColor(Color.WHITE);
            multipleSeriesRenderer.setLabelsColor(Color.BLACK);
            multipleSeriesRenderer.setAxesColor(Color.GRAY);
            multipleSeriesRenderer.setYLabelsColor(0, Color.BLACK);
            multipleSeriesRenderer.setXLabelsColor(Color.BLACK);

            multipleSeriesRenderer.addSeriesRenderer(rendererA);
            multipleSeriesRenderer.addSeriesRenderer(rendererA1);
            multipleSeriesRenderer.addSeriesRenderer(rendererA2);
            multipleSeriesRenderer.addSeriesRenderer(rendererA3);
            multipleSeriesRenderer.addSeriesRenderer(rendererA4);
            multipleSeriesRenderer.addSeriesRenderer(rendererA5);
            multipleSeriesRenderer.addSeriesRenderer(rendererA6);

            drawChart(dataset, multipleSeriesRenderer);
        }

        private void drawChart(XYMultipleSeriesDataset dataset,
                               XYMultipleSeriesRenderer renderer) {

            if (null == mGraphView) {
                mGraphView =
                        ChartFactory.getLineChartView(getActivity(), dataset, renderer);

                RelativeLayout container =
                        (RelativeLayout) mView.findViewById(R.id.graph_container);

                container.addView(mGraphView);
            } else {
                mGraphView.repaint();
            }
        }
    } //End Line Year
}
