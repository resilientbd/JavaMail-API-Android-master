package com.brandonjenniges.javamailapidemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    Button registration,login;
    EditText username,password;
    String getEmail,getEpassword,getName,getUsername,getPassword;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        db=new Database(getBaseContext());

        registration=(Button) findViewById(R.id.btnRegistration);
        login=(Button) findViewById(R.id.btnLogin);

        username=(EditText) findViewById(R.id.inpUsername);
        password=(EditText) findViewById(R.id.inpPassword);

        ClearAll();
        addbutttonlistener();
    }
    public void ClearAll()
    {
        username.setText("");
        password.setText("");
    }
    public void addbutttonlistener()
    {
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstActivity.this,registrationActivity.class);
                finish();
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inpUsername=username.getText().toString();
                String inpPassword=password.getText().toString();
                try{
                    Cursor rs= db.findData(inpUsername,inpPassword);
                    if(rs.getCount()>0)
                    {
                        while (rs.moveToNext())
                        {
                            getEmail=rs.getString(0);
                            getEpassword=rs.getString(1);
                            getName=rs.getString(2);
                            getUsername=rs.getString(3);
                            getPassword=rs.getString(4);

                        }
                        Toast.makeText(getBaseContext(),"Welcome: "+getName+" !!",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                        intent.putExtra("dbEmail",getEmail);
                        intent.putExtra("dbEpassword",getEpassword);
                        intent.putExtra("dbName",getName);
                        intent.putExtra("dbUsername",getUsername);
                        intent.putExtra("dbPassword",getPassword);
                        finish();
                        startActivity(intent);
                }
                else Toast.makeText(getBaseContext(),"NO DATA FOUND",Toast.LENGTH_LONG).show();

                }catch (Exception e)
                {
                    Toast.makeText(getBaseContext(),"Error: "+e,Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
