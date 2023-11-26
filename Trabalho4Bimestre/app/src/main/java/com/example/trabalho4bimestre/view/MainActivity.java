package com.example.trabalho4bimestre.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalho4bimestre.R;
import com.example.trabalho4bimestre.controller.LoginController;
import com.example.trabalho4bimestre.dao.LoginDao;
import com.example.trabalho4bimestre.dao.PedidosDao;
import com.example.trabalho4bimestre.model.Login;

public class MainActivity extends AppCompatActivity {

    private View viewAlert;
    private AlertDialog dialog;
    private LoginController loginController;
    private EditText edUsuario;
    private EditText edSenha;
    private TextView btCadastrarLogin;
    private TextView btLogin;
    private TextView tvErroLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginController = new LoginController(this);

        PedidosDao.getInstancia(this);
        LoginDao.getInstancia(this);

        btCadastrarLogin = findViewById(R.id.btCadastrarLogin);
        btCadastrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {abrirLogin();}
        });
    }

    public void abrirCadastroPedidos() {
        Intent intent = new Intent(MainActivity.this, PedidosActivity.class);
        startActivity(intent);
    }

    private void abrirCadastro() {
        viewAlert = getLayoutInflater().inflate(R.layout.dialog_cadastro, null);
        edUsuario = viewAlert.findViewById(R.id.edUsuario);
        edSenha = viewAlert.findViewById(R.id.edSenha);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CADASTRO");
        builder.setView(viewAlert);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Cadastrar", null);
        dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> {
            Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {salvarDados();}
            });
        });
        dialog.show();
    }

    public void abrirLogin(){
        viewAlert = getLayoutInflater().inflate(R.layout.dialog_login, null);
        edUsuario = viewAlert.findViewById(R.id.edUsuario);
        edSenha = viewAlert.findViewById(R.id.edSenha);
        tvErroLogin = viewAlert.findViewById(R.id.tvErroLogin);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("LOGIN");
        builder.setView(viewAlert);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Entrar", null);
        dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> {
            Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Login login = new Login();
                    login = loginController.validarLogin(edUsuario.getText().toString(), edSenha.getText().toString());
                    tvErroLogin.setText("");
                    if (login != null) {
                        if (edUsuario.getText().toString().equalsIgnoreCase(login.getUsuario()) && edSenha.getText().toString().equalsIgnoreCase(login.getSenha().toString())) {
                            abrirCadastroPedidos();
                        }
                    }else{
                        edUsuario.setText("");
                        edSenha.setText("");
                        tvErroLogin.setText("Usuario inválido!");
                    }
                }
            });
        });
        dialog.show();
    }

    public void salvarDados() {
        String retorno = loginController.salvarLogin(edUsuario.getText().toString(), edSenha.getText().toString());
        if (retorno != null) {
            if (retorno.contains("USUARIO")) {
                edUsuario.setError(retorno);
                edUsuario.requestFocus();
            }
            if (retorno.contains("SENHA")) {
                edSenha.setError(retorno);
                edSenha.requestFocus();
            }
        } else {
            Toast.makeText(this, "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }
    }
}