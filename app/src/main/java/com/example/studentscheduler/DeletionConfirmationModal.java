package com.example.studentscheduler;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.studentscheduler.Database.Student;

public class DeletionConfirmationModal extends DialogFragment {

    Student student;
    MainViewModel viewModel;

    public DeletionConfirmationModal(Student student, MainViewModel viewModel) {
        super();
        this.student = student;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        setCancelable(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle("Are you sure?")
                .setMessage("Do you want to delete this student?")
                .setIcon(R.drawable.baseline_warning_24)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // NOTHING
                    }
                })
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        viewModel.delete(student);
                        Toast.makeText(getContext(), "Student deleted", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }
}