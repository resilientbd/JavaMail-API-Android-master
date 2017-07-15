package com.brandonjenniges.javamailapidemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registrationActivity extends AppCompatActivity {

    Button done;
    EditText email,epassword,epasswordrepeat,name,username,password,passwordrepeat;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        db=new Database(getBaseContext());
        email=(EditText) findViewById(R.id.email);
        epassword=(EditText) findViewById(R.id.epassword);
        epasswordrepeat=(EditText) findViewById(R.id.epasswordRepeat);
        name=(EditText) findViewById(R.id.fullName);
        username=(EditText) findViewById(R.id.userName);
        password=(EditText) findViewById(R.id.password);
        passwordrepeat=(EditText) findViewById(R.id.passwordRepeat);

        done=(Button) findViewById(R.id.btnDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inEmail="";
                String inEpassword="";
                String inEpasswordRepeat="";
                String inName="";
                String inUserName="";
                String inPassword="";
                String inPasswordRepeat="";
                try{
                    inEmail=email.getText().toString();
                    inEpassword=epassword.getText().toString();
                    inEpasswordRepeat=epasswordrepeat.getText().toString();
                    inName=name.getText().toString();
                    inUserName=username.getText().toString();
                    inPassword=password.getText().toString();
                    inPasswordRepeat=passwordrepeat.getText().toString();
                }catch (Exception e)
                {
                    Toast.makeText(getBaseContext(),"Error: "+e,Toast.LENGTH_LONG).show();
                }



                if(inEpassword.equals(inEpasswordRepeat))
                {
                    if(inPassword.equals(inPasswordRepeat))
                    {
                        boolean insert=db.insertAll(inEmail,inEpassword,inName,inUserName,inPassword);
                        if(insert==true)
                        {
                            Toast.makeText(getBaseContext(),"REGISTRATION SUCCESSFULL !!",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(registrationActivity.this,FirstActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(getBaseContext(),"REGISTRATION FAILED !!",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getBaseContext(),"SOFTWARE PASSWORD NOT MATCHED !!",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getBaseContext(),"EMAIL PASSWORD NOT MATCHED !!",Toast.LENGTH_LONG).show();

            }
        });
    }
}
