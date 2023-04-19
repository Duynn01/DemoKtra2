package com.example.demoktra2;

import android.app.DatePickerDialog;
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

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    public Spinner sp,sp2;
    private EditText eTitle,eTitle2,eDate;
    private Button btUpdate,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        eDate.setOnClickListener(this);
    }

    private void initView() {
        sp=findViewById(R.id.spCategory);
        eTitle=findViewById(R.id.tvTitle);
        eTitle2=findViewById(R.id.tvTitle2);
        eDate=findViewById(R.id.tvDate);
        btUpdate=findViewById(R.id.btUpdate);
        btCancel=findViewById(R.id.btCancel);
        sp2=findViewById(R.id.spCategory2);
        sp.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.category)));
        sp2.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,getResources().getStringArray(R.array.category2)));

    }

    @Override
    public void onClick(View view) {
        if(view==eDate){
            final Calendar c=Calendar.getInstance();
            int year= c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        if(view==btCancel){
            finish();
        }
        if(view==btUpdate){
            String t =eTitle.getText().toString();
            String t2 =eTitle2.getText().toString();
            String d =eDate.getText().toString();
            String c = sp.getSelectedItem().toString();
            String c2 =sp2.getSelectedItem().toString();
            if(!t.isEmpty() && !t2.isEmpty()){
                Item i = new Item(t,t2,d,c,c2);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }

        }
    }
}