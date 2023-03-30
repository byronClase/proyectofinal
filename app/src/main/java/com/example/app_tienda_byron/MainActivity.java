package com.example.app_tienda_byron;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText urlEditText;
    private Button loginButton;
    final static String EXTRA_CORRECT = "com.example.app_tienda_byron.yourUrl";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginButton=findViewById(R.id.loginButton);
        Intent intent = new Intent(this, ShowActivity.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEditText = findViewById(R.id.usernameEditText);
                passwordEditText = findViewById(R.id.passwordEditText);
                urlEditText = findViewById(R.id.urlEditText);
                loginButton = findViewById(R.id.loginButton);
                String password;
                String username;
                String url;
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                url = urlEditText.getText().toString();
                final String yourUrl = "http://192.168.0.2/byron/componentes_tienda.json";

                if(username.equalsIgnoreCase("byron") && password.equals("1234") && url.equals(yourUrl)){
                    intent.putExtra(EXTRA_CORRECT, yourUrl);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Has iniciado sesion correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Contraseña/usuario/url incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}