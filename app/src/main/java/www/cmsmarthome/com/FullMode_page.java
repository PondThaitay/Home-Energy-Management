package www.cmsmarthome.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FullMode_page extends Activity{
	
	Control_Process c1 = new Control_Process();
	User u1 = new User();
	
	private String IP_Address;
	private String Port;
	private String UserDetailsID;
	
	private Button btnAllON;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_mode);
		
		Intent intent= getIntent();
    	UserDetailsID = intent.getStringExtra("UserDetailsID");
		    	
		//get IP and Port
		u1.getData(UserDetailsID);
		IP_Address = u1.strIP;
		Port = u1.strPort;
		//
		
		btnAllON = (Button)findViewById(R.id.btnAllon);
		btnAllON.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String msgFull = "$XPORT=ONALLL";
				c1.Control(IP_Address, Port, msgFull);
                Toast.makeText(getBaseContext(), "ทำงานโหมดสว่างเต็มที่แล้ว", Toast.LENGTH_SHORT).show();
			}
		});
	}//End onCreate

    //BackPressed
    public void onBackPressed() {
        final AlertDialog.Builder da1 = new AlertDialog.Builder(this.getParent(),AlertDialog.THEME_HOLO_DARK);
        TextView msg = new TextView(this);
        msg.setText("หากต้องการออกจาก App โดยสมบูรณ์กรุณากลับไปหน้าแรกก่อน");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextSize(18);
        msg.setTextColor(Color.WHITE);

        da1.setTitle("เกิดข้อผิดผลาด");
        da1.setIcon(R.drawable.ic_launcher);
        da1.setView(msg);
        da1.setPositiveButton("ปิด", null);
        da1.show();
    }
    //End BackPressed

}//End Class FullMode
