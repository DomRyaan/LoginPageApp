package com.exemplo.loginpage.dados;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SecureUserPreferences {
    private SharedPreferences sharedPreferences;
    private final String PRE_NAME = "user_date";
    private final String KEY_NAME = "name";
    private final String KEY_EMAIL = "email";
    private final String KEY_PASSWORD = "password";

    public SecureUserPreferences(Context context)
    {
        try
        {
            // 1. Gera ou Recupera a Chave Mestra para criptografia AES-256
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            // 2. Inicializa o SharedPreferences Criptografado
            sharedPreferences = EncryptedSharedPreferences.create(
                    "dados_seguros_usuario", // Nome do Arquivo
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // Criptografia das Chaves
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // Criptografia dos Valores
            );
        } catch (GeneralSecurityException | IOException e)
        {
            Toast.makeText(context, "Error no arquivo de armazenamento, tente novamente mais tarde", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    public boolean salvarUser(Usuario usuario)
    {
        System.out.println("DEBUG: Salvando Dados");
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(KEY_NAME, usuario.getNome());
            editor.putString(KEY_EMAIL, usuario.getEmail());
            editor.putString(KEY_PASSWORD, usuario.getPassword());

            editor.apply();
            System.out.println("DEBUG: SUCESSO AO CADASTRAR USUÁRIO");
            return true;
        } catch (Exception e){
            System.out.println("Erro ao salvar!");
            return false;
        }
    }

    public boolean usuarioCadastrado(String nome, String emailOrPhone)
    {
        String emailOrPhoneUser = this.sharedPreferences.getString(KEY_EMAIL, "");
        String nomeUser = this.sharedPreferences.getString(KEY_NAME, "");

        if (emailOrPhoneUser.equals(emailOrPhone) && nomeUser.equals(nome))
        {
            return true;
        }

        return false;
    }

    public Usuario recuperarUser()
    {
        String emailOrPhoneUser = sharedPreferences.getString(KEY_EMAIL, "");
        String nomeUser = sharedPreferences.getString(KEY_NAME, "");
        String passwordUser = sharedPreferences.getString(KEY_PASSWORD, "");

        return new Usuario(nomeUser, emailOrPhoneUser, passwordUser);
    }

    public void deletarUser()
    {
        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.remove(KEY_NAME);
        edit.remove(KEY_EMAIL);
        edit.remove(KEY_PASSWORD);

        edit.apply();

    }

}
