package com.example.datktph26567_fn_mob304;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.datktph26567_fn_mob304.Model.Giay;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    private TextInputLayout ed_name, ed_hangSx, ed_loai, ed_size,ed_moTa;
    private Button btn_save,btn_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ed_name = findViewById(R.id.ed_name);
        ed_hangSx = findViewById(R.id.ed_hangSx);
        ed_loai = findViewById(R.id.ed_loai);
        ed_size = findViewById(R.id.ed_size);
        ed_moTa = findViewById(R.id.ed_description);

        btn_exit = findViewById(R.id.btn_exit);
        btn_save = findViewById(R.id.btn_save);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save(){
        String name = ed_name.getEditText().getText().toString().trim();
        String hangSx = ed_hangSx.getEditText().getText().toString().trim();
        String loai = ed_loai.getEditText().getText().toString().trim();
        String size = ed_size.getEditText().getText().toString().trim();
        String moTa = ed_moTa.getEditText().getText().toString().trim();

        if (name.isEmpty() || hangSx.isEmpty() || loai.isEmpty() || size.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        Giay newGiay = new Giay();
        newGiay.setName(name);
        newGiay.setHangSx(hangSx);
        newGiay.setLoai(loai);
        newGiay.setSize(size);
        newGiay.setMoTa(moTa);

       ThiService ManagerService = RetrofitClient.getService();
        Call<Giay> call = ManagerService.addLover(newGiay);
        call.enqueue(new Callback<Giay>() {
            @Override
            public void onResponse(Call<Giay> call, Response<Giay> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddActivity.this, "Thêm lover thành công", Toast.LENGTH_SHORT).show();

                    // Gửi kết quả về MainActivity và kết thúc Activity hiện tại
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Giay> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(AddActivity.this, "Có lỗi xảy ra khi thêm lover", Toast.LENGTH_SHORT).show();
            }
        });
    }
}