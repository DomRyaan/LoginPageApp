package com.exemplo.loginpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.text.style.ClickableSpan;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.exemplo.loginpage.dados.SecureUserPreferences;
import com.exemplo.loginpage.dados.Usuario;
import com.google.android.material.textfield.TextInputEditText;

public class registrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        TextView termoPrivacidadeLink = findViewById(R.id.linkTermoPoliticaId);
        TextView signIn = findViewById(R.id.signInLinkID);

        // 1. Crie o texto completo que será exibido no CheckBox
        String textoCompleto = "Eu concordo com os Termos de Uso e Políticas de Privacidade";
        SpannableString spannableString = new SpannableString(textoCompleto);

        // 2. Defina a parte que será o primeiro link
        ClickableSpan termoSpan = setSpanLink("Abrir Termos de Uso");

        // 3. Definindo o segundo link ("Politica de Privacidade)
        ClickableSpan privacidadeSpan = setSpanLink("Abrir Políticas de Privacidade");

        // 4. Aplicando os links no texto
        apllyLinkOnText(spannableString, privacidadeSpan, textoCompleto, "Políticas de Privacidade");
        apllyLinkOnText(spannableString, termoSpan, textoCompleto, "Termos de Uso");


        // 5. Defina o texto estilizado no CheckBox e habilitar o movimento dos links
        termoPrivacidadeLink.setText(spannableString);
        termoPrivacidadeLink.setMovementMethod(LinkMovementMethod.getInstance());

        String naoTemConta = "Você tem uma conta? Entre aqui";

        SpannableString spanLogIn = new SpannableString(naoTemConta);

        ClickableSpan linkEntre = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                System.out.println("Link 'Entre aqui' Clicado");
                Intent intent = new Intent(registrar.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds)
            {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.link_color));
            }
        };

        apllyLinkOnText(spanLogIn, linkEntre, naoTemConta, "Entre aqui");

        signIn.setText(spanLogIn);
        signIn.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void apllyLinkOnText(SpannableString spannableString,  ClickableSpan span, String stringCompleta, String Substring)
    {
        // aplica link no texto
        int[] indexSubString = getIndexSubString(stringCompleta, Substring);

        spannableString.setSpan(span, indexSubString[0], indexSubString[1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public int[] getIndexSubString(String textoCompleto, String parteTexto)
    {
        int inicioTexto = textoCompleto.indexOf(parteTexto);
        int fimTexto = inicioTexto + parteTexto.length();

        int[] indexTextos = {inicioTexto, fimTexto};

        return  indexTextos;
    }

    public ClickableSpan setSpanLink(String string)
    {
        return new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget)
            {
                Toast.makeText(registrar.this, string, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds)
            {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.link_color));
            }
        };
    }

    public void criarConta(View view)
    {
        SecureUserPreferences database = new SecureUserPreferences(this);
        TextInputEditText nomeEditText = findViewById(R.id.nameId);
        TextInputEditText emailOrPhoneEditText = findViewById(R.id.emailOrPhoneId);
        TextInputEditText passwordEditText = findViewById(R.id.passwordId);
        CheckBox checkBox = findViewById(R.id.checkBoxServiceId);

        boolean textosVazio = nomeEditText.getText().toString().isEmpty() || emailOrPhoneEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty();

        if (textosVazio){
            Toast.makeText(this, "Preencha os campos!!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!checkBox.isChecked())
        {
            Toast.makeText(this, "Você precisa estár de acordo com nossos Termos de Uso e Política de Privacidade", Toast.LENGTH_LONG).show();
            return;
        }


        String nomeUser = nomeEditText.getText().toString();
        String emailOrPhoneUser = emailOrPhoneEditText.getText().toString();
        String passwordUser = passwordEditText.getText().toString();

        if (database.usuarioCadastrado(nomeUser, emailOrPhoneUser))
        {
            Toast.makeText(this, "Esse usuário já esta cadastrado!", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario usuario = new Usuario(nomeUser, emailOrPhoneUser, passwordUser);
        if (database.salvarUser(usuario)) {
            Toast.makeText(this, "Cadastro Realizado com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Desculpa, houve um erro ao cadastrar suas credencias", Toast.LENGTH_SHORT).show();
        }
    }
}