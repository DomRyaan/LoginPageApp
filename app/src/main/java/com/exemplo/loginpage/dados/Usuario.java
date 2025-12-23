package com.exemplo.loginpage.dados;

public class Usuario {
    private String nome;
    private String emailOrPhone;
    protected String password;

    public Usuario(String nome, String email, String password)
    {
        this.nome = nome;
        this.emailOrPhone = email;
        this.password = password;
    }

    public String getNome()
    {
        return this.nome;
    }

    public String getEmail()
    {
        return this.emailOrPhone;
    }

    public String getPassword()
    {
        return this.password;
    }
}
