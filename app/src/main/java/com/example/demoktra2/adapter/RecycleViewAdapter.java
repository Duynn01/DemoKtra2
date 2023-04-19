package com.example.demoktra2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoktra2.model.Item;
import com.example.demoktra2.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter  extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    private List<Item> list;
    private ItemListener itemListener;
    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Item getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.title2.setText(item.getTitle2());
        holder.date.setText(item.getDate());
        holder.category.setText(item.getCategory());
        holder.category2.setText(item.getCategory2());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title,category,title2,date,category2;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            title2 = view.findViewById(R.id.tvTitle2);
            date = view.findViewById(R.id.tvDate);
            category = view.findViewById(R.id.tvCategory);
            category2 = view.findViewById(R.id.tvCategory2);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface ItemListener{
        void onItemClick(View view,int position);
    }
}
