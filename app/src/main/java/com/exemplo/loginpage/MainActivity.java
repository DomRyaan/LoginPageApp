package com.exemplo.loginpage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private final String EMAIL = "ryan@gmail.com";
    private final String SENHA = "rf123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void autenticar(View view)
    {
    EditText email = findViewById(R.id.emailId);
    String emailFormatado = email.getText().toString();

    EditText senha = findViewById(R.id.passwordId);
    String senhaFormatado = senha.getText().toString();

    TextView notificacao = findViewById(R.id.notificaçãoId);

    if (emailFormatado.equals(EMAIL) && senhaFormatado.equals(SENHA))
    {
        notificacao.setText("Autenticação realizado com sucesso!");
    }
    else {
        notificacao.setText("Autenticação falhou");
    }

    }

}