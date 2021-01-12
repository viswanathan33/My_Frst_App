package com.example.myfirstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItem> mExampleList;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1=itemView.findViewById(R.id.item1);
            mTextView2=itemView.findViewById(R.id.item2);
            mTextView3=itemView.findViewById(R.id.item3);
            mTextView4=itemView.findViewById(R.id.item4);
        }
    }
public ExampleAdapter(ArrayList<ExampleItem> exampleList){
        mExampleList=exampleList;
}
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ExampleViewHolder evh=new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem=mExampleList.get(position);
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        holder.mTextView3.setText(currentItem.getText3());
        holder.mTextView4.setText(currentItem.getText4());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
