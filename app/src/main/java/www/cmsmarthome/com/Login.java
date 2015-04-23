package www.cmsmarthome.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import InternetDetect.ConnectionDetector;

public class Login extends Activity {

    User u1 = new User();

    private String Status;
    private String UserDetailsID;//get ID
    private String TimmerID;

    //flag for Internet connection status
    Boolean isInternetPresent = false;
    // Connection detector class
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());
        // txtUsername & txtPassword
        final EditText txtUser = (EditText) findViewById(R.id.etUsername);
        final EditText txtPass = (EditText) findViewById(R.id.etPassword);
        // btnLogin
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        //btnRegister
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        final AlertDialog.Builder registerDialog = new AlertDialog.Builder(this);
        final AlertDialog.Builder checkEmty = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                isInternetPresent = cd.isConnectingToInternet();
                if (isInternetPresent) {
                    u1.checkLogin(txtUser.getText().toString().toLowerCase(), txtPass.getText().toString().toLowerCase());
                    Status = u1.getStatus();
                    UserDetailsID = u1.getID();
                    TimmerID = u1.getTimer_ID();

                    if (Status.equals("Yes")) {
                        Toast.makeText(Login.this, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();
                        Intent tabMau = new Intent(Login.this, Tab_Control.class);
                        tabMau.putExtra("UserDetailsID", UserDetailsID);
                        tabMau.putExtra("TimerID", TimmerID);
                        startActivity(tabMau);
                    } else {
                        txtUser.setText("");
                        txtPass.setText("");
                        txtUser.requestFocus();
                        Toast.makeText(Login.this, "ชื่อผู้ใช้ หรือ รหัสผ่าน ไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "กรุณาเชื่อมต่อ Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final View Viewlayout = inflater.inflate(R.layout.register_dialog, (ViewGroup) findViewById(R.id.layout_dialog));
                registerDialog.setIcon(R.drawable.ic_launcher);
                registerDialog.setTitle("สมัครสมาชิก");
                registerDialog.setView(Viewlayout);

                checkEmty.setTitle("เกิดข้อผิดพลาด!");
                checkEmty.setIcon(R.drawable.ic_launcher);
                checkEmty.setPositiveButton("ปิด", null);
                // Button OK
                registerDialog.setPositiveButton("สมัครสมาชิก", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        //close Dialog
                        dialog.dismiss();

                        String h;
                        //txtUsername,Password,IP and Port (Dialog)
                        EditText user = (EditText) Viewlayout.findViewById(R.id.rtName);
                        EditText pass = (EditText) Viewlayout.findViewById(R.id.rtPass);
                        EditText ip = (EditText) Viewlayout.findViewById(R.id.rtip);
                        EditText port = (EditText) Viewlayout.findViewById(R.id.rtport);
                        String usernameRT, passwordRT, ipRT, portRT;
                        usernameRT = user.getText().toString();
                        passwordRT = pass.getText().toString();
                        ipRT = ip.getText().toString();
                        portRT = port.getText().toString();
                        if (user.getText().length() == 0 || pass.getText().length() == 0 || ip.getText().length() == 0 || port.getText().length() == 0) {
                            Toast.makeText(Login.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                        } else {
                            u1.Insert(usernameRT, passwordRT, ipRT, portRT);
                            h = u1.getInsert();
                            Toast.makeText(Login.this, h, Toast.LENGTH_SHORT).show();
                        }
                    }
                })

                        // Button Cancel
                        .setNegativeButton("ยกเลิก",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                registerDialog.create();
                registerDialog.show();
            }
        });
    }//End onCreate
}//End Class Login
