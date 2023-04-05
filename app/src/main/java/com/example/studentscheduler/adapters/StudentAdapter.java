package com.example.studentscheduler.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Database.Student;
import com.example.studentscheduler.Interface.MainInterface;
import com.example.studentscheduler.R;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentItemHolder> {

    List<Student> students = new ArrayList<>();

    MainInterface mainInterface;

    public StudentAdapter(  MainInterface mainInterface) {
        this.mainInterface = mainInterface;
    }


    @NonNull
    @Override
    public StudentItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentItemHolder holder, int position) {
        Student currentItem = students.get(position);
        holder.textViewName.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    class StudentItemHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;

        public StudentItemHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.student_name);

            itemView.findViewById(R.id.view_student_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    mainInterface.onClickButton(pos, "view");
                }
            });

            itemView.findViewById(R.id.delete_student_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    mainInterface.onClickButton(pos, "delete");
                }
            });


        }
    }




}
