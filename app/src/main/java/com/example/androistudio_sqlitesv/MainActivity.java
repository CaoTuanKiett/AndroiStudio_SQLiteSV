package com.example.androistudio_sqlitesv;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView maSV , tenSV , lop , khoa ;
    Button insert , update , delete , view ;
    Helper helper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        khoa = findViewById(R.id.MaKhoa);
        maSV = findViewById(R.id.MaSV);
        tenSV = findViewById(R.id.tenSV);
        lop = findViewById(R.id.lopSH);

        insert = findViewById(R.id.button4);
        update = findViewById(R.id.button5);
        delete = findViewById(R.id.button6);
        view = findViewById(R.id.button7);
        helper = new Helper(this);
        Boolean checkInsertCate = helper.insertValueCategory("CNS","Công Nghệ Số");

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkInsertComputer = helper.insertValuecomputer(maSV.getText().toString() , tenSV.getText().toString() , lop.getText().toString(),"CNS");
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkInsertComputer = helper.updateValuecomputer(maSV.getText().toString() , tenSV.getText().toString() , lop.getText().toString(),"CNS");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkInsertComputer = helper.deleteValuecomputer(maSV.getText().toString());
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = helper.getvalueComputer();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No entry", Toast.LENGTH_SHORT).show();
                    return ;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("maSV : " + res.getString(1) + "\n");
                    buffer.append("tenSV : " + res.getString(1) + "\n");
                    buffer.append("lop : " + res.getString(2) + "\n");
                    buffer.append("maKhoa : " + res.getString(3) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("sinhvien");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}