package com.example.datktph26567_fn_mob304;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.datktph26567_fn_mob304.Adapter.GiayAdapter;
import com.example.datktph26567_fn_mob304.Model.Giay;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_ADD_LOVER = 1;
    public static final int REQUEST_EDIT_LOVER = 2;
    private RecyclerView recyclerView;

    private List<Giay> giayList;
    private GiayAdapter giayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        giayList = new ArrayList<>();

        giayAdapter = new GiayAdapter(giayList, new GiayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Giay giay) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("giay", giay); // Đính kèm đối tượng Lover vào Intent
                startActivityForResult(intent, REQUEST_EDIT_LOVER);
            }
        });
        giayAdapter.setOnDeleteClickListener(new GiayAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Giay giay) {
                showConfirmDeleteDialog(giay);
            }
        });
        recyclerView.setAdapter(giayAdapter);
         fetch();
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, REQUEST_ADD_LOVER);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_LOVER && resultCode == RESULT_OK) {
            fetch();
        } else if (requestCode == REQUEST_EDIT_LOVER && resultCode == RESULT_OK) {
            fetch();
        }
    }
    private void fetch() {
        ThiService ManagerService = RetrofitClient.getService();
        Call<List<Giay>> call = ManagerService.getListLover();

        call.enqueue(new Callback<List<Giay>>() {
            @Override
            public void onResponse(Call<List<Giay>> call, Response<List<Giay>> response) {
                if (response.code() == 200) {
                    giayList.clear();
                    giayList.addAll(response.body());
                    giayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Giay>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void showConfirmDeleteDialog(final Giay giay) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn xóa Lover này?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteLover(giay);
            }
        });
        builder.setNegativeButton("Không", null);
        builder.show();
    }

    private void deleteLover(final Giay giay) {
        ThiService ManagerService = RetrofitClient.getService();
        Call<Void> call = ManagerService.deleteLover(giay.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    giayList.remove(giay);
                    giayAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Xảy ra lỗi khi xóa!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}