package com.arifsupriyanto.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
{
    private Button _loginButton;
    private EditText _idEditText;
    private EditText _passwordEditText;
    private Intent _menuIntent;
    private Intent _homeIntent;
    private String _id;
    private String _password;
    private String _url;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _loginButton = (Button) findViewById(R.id.loginButton);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpClient asyncHttpClient;

                _idEditText = (EditText) findViewById(R.id.idEdittext);
                _passwordEditText = (EditText) findViewById(R.id.passwordEdittext);

                _id = _idEditText.getText().toString();
                _password = _passwordEditText.getText().toString();
                _url = "https://stmikpontianak.net/011100862/login.php?id=" + _id + "&password=" + _password;
                
                asyncHttpClient = new AsyncHttpClient();
                asyncHttpClient.get(_url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String hasil = new String(responseBody);
                        
                        if (!hasil.equals("[{\"idCount\":\"1\"}]"))
                        {
                            Toast.makeText(getApplicationContext(), "ID dan password anda tidak cocok", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(getApplicationContext(), "Selamat Datang, " + _id, Toast.LENGTH_SHORT).show();
                        _menuIntent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(_menuIntent); //muncul halaman menu

                        Toast.makeText(getApplicationContext(), "Selamat Datang, " + _id, Toast.LENGTH_SHORT).show();
                        _homeIntent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(_homeIntent); //muncul halaman home
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
                    {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}