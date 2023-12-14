package com.example.confirmation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText nomeEditText;
    private EditText emailEditText;
    private Button salvarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nomeEditText = findViewById(R.id.nomeEditText);
        emailEditText = findViewById(R.id.emailEditText);
        salvarButton = findViewById(R.id.salvarButton);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        nomeEditText.setText(prefs.getString("nome", ""));
        emailEditText.setText(prefs.getString("email", ""));

        salvarButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nome", nomeEditText.getText().toString());
            editor.putString("email", emailEditText.getText().toString());
            editor.apply();


            String nomeSalvo = prefs.getString("nome", "");
            String emailSalvo = prefs.getString("email", "");

            if (!nomeSalvo.isEmpty() && !emailSalvo.isEmpty()) {
                Toast.makeText(ProfileActivity.this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProfileActivity.this, "Erro ao salvar os dados", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
