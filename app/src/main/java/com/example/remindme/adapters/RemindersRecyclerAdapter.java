package com.example.remindme.adapters;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remindme.R;
import com.example.remindme.models.Reminder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RemindersRecyclerAdapter extends RecyclerView.Adapter<RemindersRecyclerAdapter.ViewHolder> {
    private static final String TAG = "RemindersRecyclerAdapter";

    private List<Reminder> mReminders = new ArrayList<>();
    private OnReminderListener mOnReminderListener;

    public RemindersRecyclerAdapter(List<Reminder> reminder, OnReminderListener onReminderListener) {
        this.mReminders = reminder;
        this.mOnReminderListener = onReminderListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_reminders, viewGroup, false);
        return new ViewHolder(view, mOnReminderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        String title = mReminders.get(position).getTitle();
        String date = mReminders.get(position).getDate();
        viewHolder.title.setText(title);
        viewHolder.date.setText(date);

    }

    @Override
    public int getItemCount() {
        return mReminders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, date;
        OnReminderListener onReminderListener;

        public ViewHolder(@NonNull View itemView, OnReminderListener onReminderListener) {
            super(itemView);
            title = itemView.findViewById(R.id.reminder_title);
            date = itemView.findViewById(R.id.reminder_date);

            this.onReminderListener = onReminderListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onReminderListener.onReminderClick(mReminders.get(getAdapterPosition()));
        }
    }

    public interface OnReminderListener{
        void onReminderClick(Reminder reminder);
    }
}
