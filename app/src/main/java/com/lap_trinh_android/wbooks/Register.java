package com.lap_trinh_android.wbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lap_trinh_android.wbooks.database.DatabaseReadBook;
import com.lap_trinh_android.wbooks.model.Account;

public class Register extends AppCompatActivity {

    EditText edtReUsername, edtRePassword, edtReEmail;
    Button btnReRegister, btnReLogin;

    DatabaseReadBook DatabaseReadBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DatabaseReadBook = new DatabaseReadBook(this);

        AnhXa();
//        Click cho button đăng ký
        btnReRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtReUsername.getText().toString();
                String password = edtRePassword.getText().toString();
                String email = edtReEmail.getText().toString();

                Account account1 = CreateAccount();
                if (username.equals("") || password.equals("") || email.equals("")) {
                    Log.e("Thông báo", "Vui lòng nhập đầy đủ thông tin");
                } else { // Nếu đầy đủ thông tin nhập vào thì add tài khoản vào database
                    DatabaseReadBook.AddAccount(account1);
                    Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                }
            }
        });

//        Trở về đăng nhập
        btnReLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    Phương thức tạo tài khoản
    private Account CreateAccount() {
        String username = edtReUsername.getText().toString();
        String password = edtRePassword.getText().toString();
        String email = edtReEmail.getText().toString();
        int authorize = 1;

        Account acc = new Account(username, password, email, authorize);

        return acc;
    }

    private void AnhXa() {
        edtReEmail = findViewById(R.id.re_email);
        edtRePassword = findViewById(R.id.re_password);
        edtReUsername = findViewById(R.id.re_username);
        btnReRegister = findViewById(R.id.re_register);
        btnReLogin = findViewById(R.id.re_login);
    }
}