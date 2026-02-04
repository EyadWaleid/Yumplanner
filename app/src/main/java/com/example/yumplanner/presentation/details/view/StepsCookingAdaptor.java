package com.example.yumplanner.presentation.details.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumplanner.R;

import java.util.List;

public class StepsCookingAdaptor extends RecyclerView.Adapter<StepsCookingAdaptor.StepViewHolder> {



    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cook_step_item, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        holder.tvStepNumber.setText(String.valueOf(position));
        holder.tvStepTitle.setText("Start Meal");
    }

    @Override
    public int getItemCount() {
        return 5;
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