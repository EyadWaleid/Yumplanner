package com.example.yumplanner.presentation.details.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;

import java.util.ArrayList;
import java.util.List;

public class StepsCookingAdaptor extends RecyclerView.Adapter<StepsCookingAdaptor.StepViewHolder> {


   List<String>steps=new ArrayList<>();
    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cook_step_item, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        holder.tvStepNumber.setText(String.valueOf(position+1));

        holder.tvStepTitle.setText(steps.get(position));
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }
    public  void setStepList(List<String>steps){
        this.steps=steps;

    }

    public static class StepViewHolder extends RecyclerView.ViewHolder {
        TextView tvStepNumber, tvStepTitle;
        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStepNumber = itemView.findViewById(R.id.tvStepNumber);
            tvStepTitle = itemView.findViewById(R.id.tvStepTitle);
        }
    }
}