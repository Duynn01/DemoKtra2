package com.example.demoktra2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoktra2.adapter.RecycleViewAdapter;
import com.example.demoktra2.dal.SQLiteHelper;
import com.example.demoktra2.R;
import com.example.demoktra2.model.People;

public class FragmentInfo extends Fragment  {
    private RecycleViewAdapter adapter;
    private RecyclerView recycleView;
    private SQLiteHelper db;
    private TextView tvName,tvDes;
    private ImageView img;
//    implements RecycleViewAdapter.ItemListener
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = view.findViewById(R.id.tvName);
        tvDes = view.findViewById(R.id.tvDes);
        img= view.findViewById(R.id.img);
        tvName.setText("Họ và tên: Nguyễn Ngọc Duy");
        tvDes.setText("Mô tả bản thân: chơi bóng giỏi");
        img.setImageResource(R.drawable.img);
    }
    public void onItemClick(View view, int position) {

    }
}
