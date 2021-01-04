package com.zzt.zxing.sample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QRCodeActivity extends AppCompatActivity {
    TextView tv_qrcode_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code);
        tv_qrcode_msg = findViewById(R.id.tv_qrcode_msg);
    }

    public void onClick(View view) {
        startActivityForResult(new Intent(QRCodeActivity.this, TestScanActivity.class), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            String qrcode = data.getStringExtra("qrcode");
            tv_qrcode_msg.setText(qrcode);
        }
    }
}