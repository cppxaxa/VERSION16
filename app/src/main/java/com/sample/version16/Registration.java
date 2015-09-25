package com.sample.version16;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registration extends ActionBarActivity {

    Button btnBack, btnRegister;
    EditText etUser, etPassword, etConfirmPassword;
    Bundle userDetail;
    Intent returnToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnBack = (Button)findViewById(R.id.btnBack);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        etUser = (EditText)findViewById(R.id.etUser);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etConfirmPassword = (EditText)findViewById(R.id.etConfirmPassword);

        returnToMain = new Intent();
        userDetail = new Bundle();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToMain.putExtra("userDetail", userDetail);

                setResult(RESULT_OK, returnToMain);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etConfirmPassword.getText().toString().equals(etPassword.getText().toString())) {
                    userDetail.putString("user", etUser.getText().toString());
                    userDetail.putString("password", etPassword.getText().toString());
                    userDetail.putString("action", "save");

                    returnToMain.putExtra("userDetail", userDetail);

                    setResult(RESULT_OK, returnToMain);
                    finish();
                }
                else{
                    etConfirmPassword.setText("");
                    Toast.makeText(getApplicationContext(), "Please re-Enter your password correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
