# Android Login App - Encrypted Storage 🔐
Este é um projeto simples de autenticação desenvolvido para a plataforma Android, focado na implementação de armazenamento seguro de credenciais localmente, além de implementar um UI/UX boa para o usuário.

## 🚀 Funcionalidades
Activity de Registro: Criação de novos usuários.

Activity de Login: Validação de acesso.

Persistência Segura: Uso de criptografia para salvar os dados.

## 🛠️ Tecnologias Utilizadas
Linguagem: Java

Android Jetpack: EncryptedSharedPreferences (Biblioteca de Segurança).

Layout: XML (ConstraintLayout).

## 🔒 Por que EncryptedSharedPreferences?
Neste projeto, optei por não utilizar o SharedPreferences convencional, pois ele armazena dados em arquivos XML de texto simples. Com a biblioteca de Security do Jetpack, os dados são protegidos usando um esquema de criptografia de duas camadas:

Chaves de criptografia: Gerenciadas pelo Android Keystore.

Dados: Criptografados com algoritmos modernos (AES256_SIV e AES256_GCM).

## 📸 Screenshots
<div style="align: center;">
<table>
  <tr>
    <td>Tela de Login</td>
    <td>Tela de Registro</td>
  </tr>
  <tr>
    <td><img src="./img/telalogin.jpg" width="200"></td>
    <td><img src="./img/telaregistrar.jpg" width="200"></td>
  </tr>
</table>
</div>