package com.example.demoktra2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demoktra2.dal.SQLiteHelper;
import com.example.demoktra2.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    public Spinner sp,sp2;
    private EditText eTitle,eTitle2,eDate;
    private Button btUpdate,btBack,btRemove;
    private Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btUpdate.setOnClickListener(this);
        btBack.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        eDate.setOnClickListener(this);
        Intent intent =getIntent();
        item=(Item) intent.getSerializableExtra("item");
        eTitle.setText(item.getTitle());
        eTitle2.setText(item.getTitle2());
        eDate.setText(item.getDate());
        int p=0;
        for(int i=0; i<sp.getCount();i++){
            if(sp.getItemAtPosition(i).toString().equalsIgnoreCase(item.getCategory())){
                p=i;
                break;
            }
        }
        int p2=0;
        for(int i=0; i<sp2.getCount();i++){
            if(sp2.getItemAtPosition(i).toString().equalsIgnoreCase(item.getCategory2())){
                p2=i;
                break;
            }
        }

    }

    private void initView() {
        sp=findViewById(R.id.spCategory);
        sp2=findViewById(R.id.spCategory2);
        eTitle=findViewById(R.id.tvTitle);
        eTitle2=findViewById(R.id.tvTitle2);
        eDate=findViewById(R.id.tvDate);
        btUpdate=findViewById(R.id.btUpdate);
        btBack=findViewById(R.id.btBack);
        btRemove=findViewById(R.id.btRemove);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.category)));
        sp2.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.category2)));

    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db= new SQLiteHelper(this);
        if(view==eDate){
            final Calendar c=Calendar.getInstance();
            int year= c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int y, int m, int d) {
                    String date="";
                    if(d>8){
                        if(m>8){
                            date = d +"/"+(m+1)+"/"+y;
                        }else{
                            date=d+"/0"+(m+1)+"/"+y;
                        }
                    }else{
                        if(m>8){
                            date = d +"/"+(m+1)+"/"+y;
                        }else{
                            date="0"+d+"/0"+(m+1)+"/"+y;
                        }

                    }

                    eDate.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(view==btBack){
            finish();
        }
        if(view==btUpdate){
            String t =eTitle.getText().toString();
            String t2 =eTitle2.getText().toString();
            String d =eDate.getText().toString();
            String c =sp.getSelectedItem().toString();
            String c2 =sp2.getSelectedItem().toString();
            if(!t.isEmpty() && !t2.isEmpty()){
                int id = item.getId();
                Item i = new Item(id,t,t2,d,c,c2);
                db = new SQLiteHelper(this);
                db.update(i);
                finish();
            }

        }
        if(view==btRemove){
            int id= item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo xóa");
            builder.setMessage("Bạn có chắc muốn xóa "+item.getTitle()+" không?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteHelper bb = new SQLiteHelper(getApplicationContext());
                    bb.delete(id);
                    finish();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}