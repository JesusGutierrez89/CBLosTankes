package com.example.cblostankes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;

    // Lista para almacenar usuarios registrados
    private List<User> registeredUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar algunos usuarios
        registeredUsers.add(new User("Pepe", "1234"));
        registeredUsers.add(new User("Juan", "1234"));

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        // Listener para el botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (register(username, password)) {
                    Toast.makeText(LoginActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Listener para el botón de inicio de sesión
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (authenticate(username, password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    ArrayList<String> nombres = new ArrayList<>();
                    for (User user : registeredUsers) {
                        nombres.add(user.getUsername());
                    }
                    intent.putStringArrayListExtra("nombres", nombres);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean register(String username, String password) {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username)) {
                return false; // El usuario ya existe
            }
        }
        registeredUsers.add(new User(username, password));
        return true;
    }

    private boolean authenticate(String username, String password) {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Clase interna para representar un usuario
    private static class User {
        private String username;
        private String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}