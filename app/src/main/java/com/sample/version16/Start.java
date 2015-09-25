package com.sample.version16;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.datatype.Duration;


public class Start extends ActionBarActivity {

    Button btnLogin, btnRegister, btnLogout;
    TextView tvOR, tvWelcome;
    Bundle userDetail;
    Bundle allUserDetail[] = new Bundle[50];
    int userId = -1;
    int userCounter = 0;
    Boolean loggedInStatus = false;

    void setMode(int m){
        if(m==0)
        {
            btnLogin.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.INVISIBLE);
            tvOR.setVisibility(View.VISIBLE);
            tvWelcome.setVisibility(View.VISIBLE);
            tvWelcome.setText("Please choose any of the following to have complete experience");
        }
        else if(m==1)
        {
            btnLogin.setVisibility(View.INVISIBLE);
            btnRegister.setVisibility(View.INVISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
            tvOR.setVisibility(View.INVISIBLE);
            tvWelcome.setVisibility(View.VISIBLE);
            try {
                tvWelcome.setText("Welcome, " + userDetail.getString("user"));
            }catch (Exception e){
                Toast.makeText(this.getApplicationContext(), "We didn't got UserDetails", Toast.LENGTH_SHORT).show();
                setMode(0);
            }

            //btnLogin.setVisibility(View.VISIBLE);
            //btnRegister.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loggedInStatus = false;
        setMode(0);

        try {

            userDetail = data.getBundleExtra("userDetail");

            if(userDetail.getString("action").equals("logout")) {
                loggedInStatus = false;
                Toast.makeText(getApplicationContext(), "You have been successfully logged out. See you soon.", Toast.LENGTH_SHORT).show();
                allUserDetail[userId].putBundle("userDetail", userDetail);
                //Toast.makeText(getApplicationContext(), Boolean.toString(allUserDetail[userId].getBundle("userDetail").getBoolean("accommodation")), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), (Integer.toString(userId)), Toast.LENGTH_SHORT).show();

            }else if (userDetail.getString("action").equals("login")) {
                //tvWelcome.setText("SavedData: ");
                loggedInStatus = false;
                for (int i = 0; i < userCounter; i++) {
                    //tvWelcome.setText(tvWelcome.getText().toString()+" "+Integer.toString(i));
                    try {
                        //tvWelcome.setText(tvWelcome.getText().toString()+allUserDetail[i].getString("user")+allUserDetail[i].getString("password"));
                        if (allUserDetail[i].getString("user").equals(userDetail.getString("user")) && allUserDetail[i].getString("password").equals(userDetail.getString("password"))) {
                            loggedInStatus = true;
                            userId = i;
                            //Toast.makeText(this.getApplicationContext(), "You have successfully logged in", Toast.LENGTH_SHORT).show();
                            setMode(1);

                            Intent accountIntent = new Intent(Start.this, Account.class);
                            accountIntent.putExtra("userDetail", allUserDetail[i]);

                            startActivityForResult(accountIntent, 1);

                            break;
                        }
                    } catch (Exception e) {
                    }
                }

                if (!loggedInStatus)
                    Toast.makeText(this.getApplicationContext(), "Your username or password is wrong", Toast.LENGTH_SHORT).show();

            } else if (userDetail.getString("action").equals("save")) {
                if(!userDetail.getString("user").equals("")) {
                    setMode(0);
                    allUserDetail[userCounter] = new Bundle();
                    allUserDetail[userCounter++].putAll(userDetail);
                    Toast.makeText(this.getApplicationContext(), "You have been successfully registered", Toast.LENGTH_SHORT).show();
                }
            }

        }catch (Exception e){  }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        tvOR = (TextView)findViewById(R.id.tvOR);
        tvWelcome = (TextView)findViewById(R.id.tvWelcome);
        btnLogout = (Button)findViewById(R.id.btnLogout);

        setMode(0);

        userCounter++;
        //userId++;
        allUserDetail[0] = new Bundle();
        allUserDetail[0].putString("user", "a");
        allUserDetail[0].putString("password", "a");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivity = new Intent(Start.this, Login.class);
                startActivityForResult(loginActivity, 1);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggedInStatus = false;
                setMode(0);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(Start.this, Registration.class);
                startActivityForResult(registerActivity, 2);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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
