package com.example.demoktra2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoktra2.UpdateDeleteActivity;
import com.example.demoktra2.adapter.RecycleViewAdapter;
import com.example.demoktra2.dal.SQLiteHelper;
import com.example.demoktra2.model.Item;
import com.example.demoktra2.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentList extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            recyclerView = view.findViewById(R.id.recycleView);
            adapter = new RecycleViewAdapter();
            db = new SQLiteHelper(getContext());
//            Date d = new Date();
//            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//            List<Item> list = db.getByDate(f.format(d));
            List<Item> list = db.getAll();
            adapter.setList(list);
            LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            adapter.setItemListener(this);
    }


    @Override
    public void onItemClick(View view, int position) {
        Item item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//        List<Item> list = db.getByDate(f.format(d));
        List<Item> list = db.getAll();
        adapter.setList(list);

    }
}
