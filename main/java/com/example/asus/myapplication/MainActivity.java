package com.example.asus.myapplication;
import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText em,ph,pw,msg;
    Button b1,b2;
    SharedPreferences datas;
    public static final String mydata="data";
    public static final String emaili="emaild";
    public static final String phon="mobilenod";
    public static final String pasw="passwordd";
    public static final String mse="messaged";
    public static final String ir="ikey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
em=(EditText)findViewById(R.id.email);
ph=(EditText)findViewById(R.id.phno);
pw=(EditText)findViewById(R.id.password);
msg=(EditText)findViewById(R.id.message1);
b1=(Button)findViewById(R.id.submit);
        b2=(Button)findViewById(R.id.update);
        datas=getSharedPreferences(mydata,Context.MODE_PRIVATE);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_SMS,Manifest.permission.MEDIA_CONTENT_CONTROL,Manifest.permission.INTERNET,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},1);
        String r=datas.getString(ir,"");
if(r.equals("1")){
    b1.setVisibility(View.INVISIBLE);
    b2.setVisibility(View.VISIBLE);
    em.setEnabled(false);
    ph.setEnabled(false);
    pw.setEnabled(false);
    msg.setEnabled(false);
}
else {
    b2.setVisibility(View.INVISIBLE);
    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String mail = em.getText().toString();
            String mno = ph.getText().toString();
            String paw = pw.getText().toString();
            String meg = msg.getText().toString();
            String dis="1";
            if(mail.equals("")||mno.equals("")||paw.equals("")||meg.equals("")){
                Toast.makeText(getApplicationContext(),"Please enter all the field",Toast.LENGTH_LONG).show();
            }
            else {
                datas = getSharedPreferences(mydata, MODE_PRIVATE);
                Editor editor = datas.edit();
                editor.putString(emaili, mail);
                editor.putString(phon, mno);
                editor.putString(pasw, paw);
                editor.putString(mse, meg);
                editor.putString(ir, dis);
                editor.commit();
                String r = datas.getString(ir, "");
                Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_LONG).show();
            }
        }
    });
}
b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2.setText("ok");
                pw.setEnabled(true);
                String p=datas.getString(pasw,"");
                String w = pw.getText().toString();
                if (w.equals(p)) {
                    em.setEnabled(true);
                    ph.setEnabled(true);
                    pw.setEnabled(false);
                    msg.setEnabled(true);
                    b2.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.VISIBLE);
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String mail = em.getText().toString();
                            String mno = ph.getText().toString();
                            String meg = msg.getText().toString();
                            String dis="1";
                            if(mail.equals("")||mno.equals("")||meg.equals("")){
                                Toast.makeText(getApplicationContext(),"Please enter all the field",Toast.LENGTH_LONG).show();
                            }
                            else {
                                datas = getSharedPreferences(mydata, MODE_PRIVATE);
                                Editor editor = datas.edit();
                                editor.putString(emaili, mail);
                                editor.putString(phon, mno);
                                editor.putString(mse, meg);
                                editor.putString(ir, dis);
                                editor.commit();
                                String r = datas.getString(ir, "");
                                Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(getApplicationContext(),"enter the correct password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
