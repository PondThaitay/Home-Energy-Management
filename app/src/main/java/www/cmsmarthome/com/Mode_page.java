package www.cmsmarthome.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.*;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Mode_page extends Activity {

    Control_Process c1 = new Control_Process();
    ControlLamp_page c2 = new ControlLamp_page();
    User u1 = new User();

    private String IP_Address;
    private String Port;
    private String UserDetailsID;

    private Button btnSaveEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saving_mode);

        Intent intent = getIntent();
        UserDetailsID = intent.getStringExtra("UserDetailsID");

        //get IP and Port
        u1.getData(UserDetailsID);
        IP_Address = u1.strIP;
        Port = u1.strPort;
        //

        btnSaveEn = (Button) findViewById(R.id.btnSaveEN);
        btnSaveEn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String msgSave = "$XPORT=SAVEEN";
                c1.Control(IP_Address, Port, msgSave);
                Toast.makeText(getBaseContext(), "ทำงานโหมดประหยัดพลังงานแล้ว", Toast.LENGTH_SHORT).show();
            }
        });

    } //End onCreate

    //BackPressed
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this.getParent(), AlertDialog.THEME_HOLO_DARK);
        dialog.setTitle("ออกจากระบบ");
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage("คุณต้องการออกจากระบบหรือไม่ ?");
        dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                c2.SaveBeforeForeApp();
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        dialog.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
    //End BackPressed

   /* public void onStop() {
        super.onStop();
        c2.SaveBeforeForeApp();
    }*/
}//End Class Mode_page

