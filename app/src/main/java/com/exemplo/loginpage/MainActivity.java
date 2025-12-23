package com.exemplo.loginpage;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.exemplo.loginpage.dados.SecureUserPreferences;
import com.exemplo.loginpage.dados.Usuario;

public class MainActivity extends AppCompatActivity {
    SecureUserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPreferences = new SecureUserPreferences(this);
    }

    public void autenticar(View view)
    {
    EditText email = findViewById(R.id.emailOrPhoneId);
    String emailFormatado = email.getText().toString();

    EditText senha = findViewById(R.id.passwordId);
    String senhaFormatado = senha.getText().toString();

    if(emailFormatado.isEmpty() || senhaFormatado.isEmpty())
    {
        Toast.makeText(this, "Preencha todos os Campos!", Toast.LENGTH_SHORT).show();
        return;
    }

    TextView notificacao = findViewById(R.id.notificaçãoId);
    GradientDrawable backgroundNotificacao = (GradientDrawable) notificacao.getBackground();

    Usuario usuarioCadastrado = userPreferences.recuperarUser();

    if (emailFormatado.equals(usuarioCadastrado.getEmail()) && senhaFormatado.equals(usuarioCadastrado.getPassword()))
    {
        notificacao.setText("Autenticação bem sucedida");
        int corSucess = ContextCompat.getColor(this, R.color.sucess);
        backgroundNotificacao.setColor(corSucess);
        Toast.makeText(MainActivity.this, "Autenticação bem sucedida", Toast.LENGTH_SHORT).show();
    } else {
        notificacao.setText("Email ou Senha errado!!");
        int corError = ContextCompat.getColor(this, R.color.error);
        backgroundNotificacao.setColor(corError);
        Toast.makeText(MainActivity.this, "Autenticação falhou", Toast.LENGTH_SHORT).show();
    }

    notificacao.setVisibility(View.VISIBLE);
    }

    public void signUpPage(View view)
    {
        System.out.println("Mudando de Activity");
        Intent intent = new Intent(this, registrar.class);
        startActivity(intent);
    }

}