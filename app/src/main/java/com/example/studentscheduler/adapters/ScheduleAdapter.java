package com.example.studentscheduler.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Database.Schedule;
import com.example.studentscheduler.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleItemViewHolder> {

    List<Schedule> scheduleList = new ArrayList<>();

    @NonNull
    @Override
    public ScheduleAdapter.ScheduleItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new ScheduleItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleItemViewHolder holder, int position) {
        Schedule currentItem = scheduleList.get(position);
        holder.textViewSubject.setText(currentItem.getSubject());
        holder.textViewTime.setText(String.valueOf(currentItem.getTime()));
        holder.textViewWeekday.setText(currentItem.getDayOfTheWeek());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
        notifyDataSetChanged();
    }

    class ScheduleItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewSubject, textViewTime, textViewWeekday;

        public ScheduleItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewSubject = itemView.findViewById(R.id.subject_tv);
            textViewTime = itemView.findViewById(R.id.time_tv);
            textViewWeekday = itemView.findViewById(R.id.weekday_tv);
        }
    }
}
