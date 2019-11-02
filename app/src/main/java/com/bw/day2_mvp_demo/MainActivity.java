package com.bw.day2_mvp_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.day2_mvp_demo.presenter.presenterimpl;
import com.bw.day2_mvp_demo.view.IView;

public class MainActivity extends AppCompatActivity implements IView {
    private EditText userName;
    private EditText userPwd;
    private Button bun;
    private presenterimpl presenterimpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterimpl = new presenterimpl();
        presenterimpl.onAttch(this);
        setContentView(R.layout.activity_main);


        userName = findViewById(R.id.user_name);
        userPwd = findViewById(R.id.user_pwd);
        bun = findViewById(R.id.bun);


        bun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = userName.getText().toString().trim();
                String pwd = userPwd.getText().toString().trim();

                if (phone.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入有效参数", Toast.LENGTH_SHORT).show();
                } else {
                    presenterimpl.startRequest(HttpUrl.url, phone, pwd);
                }

                /*if (phone.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入有效参数", Toast.LENGTH_SHORT).show();
                } else {
                    NetUtil.getInstance().doPost(HttpUrl.url, phone, pwd, new NetUtil.Shared() {
                        @Override
                        public void dopost(String json) {
                            Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void doerror(String error) {

                        }
                    });
                }*/
            }
        });

    }

    @Override
    public void onSuccess(String json) {
        Toast.makeText(this, json, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenterimpl != null) {
            presenterimpl = null;
        }
    }
}
