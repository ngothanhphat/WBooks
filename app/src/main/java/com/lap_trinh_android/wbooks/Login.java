package com.lap_trinh_android.wbooks;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lap_trinh_android.wbooks.database.DatabaseReadBook;

public class Login extends AppCompatActivity {

//    Tạo biến cho view đăng nhập
    EditText edtAccount, edtPassword;
    Button btnLogin, btnRegister;

//    Tạo đối tượng cho DatabaseReadBook
    DatabaseReadBook DatabaseReadBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AnhXa();
//        Đối tượng DatabaseReadBook
        DatabaseReadBook = new DatabaseReadBook(this);

//        Tạo sự kiện click button chuyển sang màn hình đăng ký với Intent
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Gán cho các biến là giá trị nhập vào từ editText
                String username = edtAccount.getText().toString();
                String password = edtPassword.getText().toString();

//                Sử dụng con trỏ để lấy dữ liệu, gọi tới getData() để lất tất cả tài khoản ở database
                Cursor cursor = DatabaseReadBook.getData();

//                Thực hiện vòng lặp để lấy dữ liệu từ cusor với moveToNext() di chuyển tiếp
                while (cursor.moveToNext()) {

//                    Lấy dữ liệu và gán vào tiếp, dữ liệu tài khoản ở ô 1 và mật khẩu ở ô 2, ở ô 0 là idtaikhoan, ô 3 là email và 4 là phân quyền
                    String dataUsername = cursor.getString(1);
                    String dataPassword = cursor.getString(2);

//                    Nếu tài khoản và mật khẩu nhập vào từ bàn phím khớp với database
                    if (dataUsername.equals(username) && dataPassword.equals(password)) {
//                        Lấy dữ liệu authorize và id_account
                        int authorize = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String name_account = cursor.getString(1);

//                        Chuyển qua view MainActivity
                        Intent intent= new Intent(Login.this, MainActivity.class);

//                        Gửi dữ liệu qua Activity là MainActivity
                        intent.putExtra("authorize", authorize);
                        intent.putExtra("idd", idd);
                        intent.putExtra("email", email);
                        intent.putExtra("name_account", name_account);

                        startActivity(intent);
                    }
                }
//                Thực hiện trả cusor về đầu
                cursor.moveToFirst();
//                Đóng khi không dùng
                cursor.close();
            }
        });
    }

    private void AnhXa() {
        edtPassword = findViewById(R.id.log_password);
        edtAccount = findViewById(R.id.log_username);
        btnRegister = findViewById(R.id.log_register);
        btnLogin = findViewById(R.id.log_login);
    }
}