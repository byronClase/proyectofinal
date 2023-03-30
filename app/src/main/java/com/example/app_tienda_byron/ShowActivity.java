package com.example.app_tienda_byron;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowActivity extends AppCompatActivity {
    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        mTextViewResult = findViewById(R.id.textViewPrincipal);

        Intent intent = getIntent();
        String yourUrl = intent.getStringExtra(MainActivity.EXTRA_CORRECT);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_option_1:
                    // Acción para la opción 1
                    mTextViewResult.setText("");
                    showJson(yourUrl);
                    return true;
                case R.id.menu_option_2:
                    // Acción para la opción 2
                    mTextViewResult.setText("nada");
                    return true;
                case R.id.menu_option_3:
                    // Acción para la opción 3
                    mTextViewResult.setText("nadaaaa");
                    return true;
                case R.id.menu_option_4:
                    // Acción para la opción 4
                    mTextViewResult.setText("nadaasdas");
                    return true;
                default:
                    return false;
            }
        });

    }

    public void showJson(String yourUrl) {

        if (yourUrl.equals("http://192.168.0.2/byron/componentes_tienda.json")) {
            mTextViewResult = findViewById(R.id.textViewPrincipal);
            RequestQueue mQueue = Volley.newRequestQueue(this);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    yourUrl,
                    null,
                    response -> {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String nombre = jsonObject.getString("nombre");
                                float precio = jsonObject.getInt("precio");
                                int unidades = jsonObject.getInt("unidades_disponibles");
                                double iva = jsonObject.getDouble("iva");


                                String result =
                                        "ID: " + id + "\n" +
                                                "Nombre: " + nombre + "\n" +
                                                "Precio: " + precio + "\n" +
                                                "Unidades: " + unidades + "\n" +
                                                "IVA: " + iva + "\n\n";

                                mTextViewResult.append(result);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    },
                    Throwable::printStackTrace
            );

            mQueue.add(jsonArrayRequest);
        }
    }
}