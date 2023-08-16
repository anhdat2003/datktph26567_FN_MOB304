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

public class UpdateActivity extends AppCompatActivity {
    private Giay giay;
    private TextInputLayout ed_name, ed_hangSx, ed_loai, ed_size,ed_moTa;
    private Button btn_save,btn_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        giay = getIntent().getParcelableExtra("giay");
        if (giay == null) {
            Toast.makeText(this, "Dữ liệu Lover không hợp lệ", Toast.LENGTH_SHORT).show();
            finish();
           return;
        }
        ed_name = findViewById(R.id.ed_name);
        ed_hangSx = findViewById(R.id.ed_hangSx);
        ed_loai = findViewById(R.id.ed_loai);
        ed_size = findViewById(R.id.ed_size);
        ed_moTa = findViewById(R.id.ed_description);

        btn_exit = findViewById(R.id.btn_exit);
        btn_save = findViewById(R.id.btn_save);

        ed_name.getEditText().setText(giay.getName());
        ed_hangSx.getEditText().setText(giay.getHangSx());
        ed_loai.getEditText().setText(giay.getLoai());
        ed_size.getEditText().setText(giay.getSize());
        ed_moTa.getEditText().setText(giay.getMoTa());

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
    }
    private void saveChanges() {
        String name = ed_name.getEditText().getText().toString().trim();
        String hangSx = ed_hangSx.getEditText().getText().toString().trim();
        String loai = ed_loai.getEditText().getText().toString().trim();
        String size = ed_size.getEditText().getText().toString().trim();
        String moTa = ed_moTa.getEditText().getText().toString().trim();

        if (name.isEmpty() || hangSx.isEmpty() || loai.isEmpty() || size.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        giay.setName(name);
        giay.setHangSx(hangSx);
        giay.setLoai(loai);
        giay.setSize(size);
        giay.setMoTa(moTa);

        update(giay);
    }
    private void update(final Giay updated) {
        ThiService ManagerService = RetrofitClient.getService();
        Call<Giay> call = ManagerService.updateLover(updated.getId(), updated);

        call.enqueue(new Callback<Giay>() {
            @Override
            public void onResponse(Call<Giay> call, Response<Giay> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    // Trả kết quả RESULT_OK về MainActivity để cập nhật lại RecyclerView
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(UpdateActivity.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Giay> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(UpdateActivity.this, "Xảy ra lỗi khi cập nhật!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}