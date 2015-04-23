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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateAccount_page extends Activity {

    User u1 = new User();
    ControlLamp_page c1 = new ControlLamp_page();
    private String UserDetailsID;
    private String statusUpdate;//รับ String ของ getStatusUpdate
    private String statusDelete;
    private String statusGetData;

    private String Username;
    private String IP_Address;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_account);

        final EditText setUsername = (EditText) findViewById(R.id.etUsername);
        final EditText setPassword = (EditText) findViewById(R.id.etPass);
        final EditText setIP = (EditText) findViewById(R.id.etIP);
        final EditText setPort = (EditText) findViewById(R.id.etPort);

        Intent intent = getIntent();
        UserDetailsID = intent.getStringExtra("UserDetailsID");

        u1.getData(UserDetailsID);
        statusGetData = u1.getStatusGetData();

        if (statusGetData.equals("Yes")) {
            setUsername.setText(u1.strUsername);
            setPassword.setText(u1.strPassword);
            setIP.setText(u1.strIP);
            setPort.setText(u1.strPort);

            int lengthUsername = setUsername.getText().length();
            setUsername.setSelection(lengthUsername);

        } else {
            setUsername.setText("Null");
            setPassword.setText("Null");
            setIP.setText("Null");
            setPort.setText("Null");
        }
        //

        final Button btnSave = (Button) findViewById(R.id.btnSave);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK);

        btnSave.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Username = u1.strUsername;
                IP_Address = u1.strIP;

                dialog.setTitle("อัพเดทบัญชีผู้ใช้นี้ !");
                dialog.setIcon(R.drawable.ic_launcher);
                dialog.setMessage("คุณต้องการอัพเดทบัญชีผู้ใช้นี่ จริง หรือ ?");
                dialog.setNegativeButton("ยกเลิก", null);
                dialog.setPositiveButton("ตกลง", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        // TODO Auto-generated method stub
                        u1.SaveData(UserDetailsID, setUsername.getText().toString(), setPassword.getText().toString()
                                , setIP.getText().toString(), setPort.getText().toString(), Username, IP_Address);
                        statusUpdate = u1.getStatusUpdate();

                        if (statusUpdate.equals("Yes")) {
                            Toast.makeText(UpdateAccount_page.this, "อัพเดทข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
                            Intent bLogin = new Intent(UpdateAccount_page.this, Login.class);
                            startActivity(bLogin);
                        } else {
                            Toast.makeText(UpdateAccount_page.this, "อัพเดทข้อมูลไม่สำเร็จ มีชื่อผู้ใช้นี้แล้ว", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        final Button btnDelete = (Button) findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Username = u1.strUsername;
                IP_Address = u1.strIP;

                dialog.setTitle("ลบบัญชีผู้ใช้นี้ !");
                dialog.setIcon(R.drawable.ic_launcher);
                dialog.setMessage("คุณต้องการลบบัญชีผู้ใช้นี่ จริง หรือ ?");
                dialog.setNegativeButton("ยกเลิก", null);
                dialog.setPositiveButton("ตกลง", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        // TODO Auto-generated method stub
                        u1.DeleteData(UserDetailsID, "", "", setIP.getText().toString(), setPort.getText().toString(), Username, IP_Address);
                        statusDelete = u1.getStatusDelete();

                        if (statusDelete.equals("Yes")) {
                            Toast.makeText(UpdateAccount_page.this, "ลบบัญชีผู้ใช้นี้แล้ว", Toast.LENGTH_SHORT).show();
                            Intent bLogin = new Intent(UpdateAccount_page.this, Login.class);
                            startActivity(bLogin);
                        } else {
                            Toast.makeText(UpdateAccount_page.this, "อัพเดทข้อมูลไม่สำเร็จ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

    }//End onCreate

    //BackPressed
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_DARK);
        dialog.setTitle("ออกจากระบบ");
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage("คุณต้องการออกจากระบบหรือไม่ ?");
        dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                c1.SaveBeforeForeApp();
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

    public void onStop() {
        super.onStop();
        c1.SaveBeforeForeApp();
    }
}//End Class Update_page
