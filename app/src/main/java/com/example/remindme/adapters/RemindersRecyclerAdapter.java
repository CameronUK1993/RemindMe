package com.example.remindme.adapters;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remindme.R;
import com.example.remindme.models.Reminder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RemindersRecyclerAdapter extends RecyclerView.Adapter<RemindersRecyclerAdapter.ViewHolder> {
    private static final String TAG = "RemindersRecyclerAdapte";

    private List<Reminder> mReminders = new ArrayList<>();
    private OnReminderListener mOnReminderListener;
    private OnDeleteListener mOnDeleteListener;


    public RemindersRecyclerAdapter(List<Reminder> reminder, OnReminderListener onReminderListener, OnDeleteListener onDeleteListener) {
        this.mReminders = reminder;
        this.mOnReminderListener = onReminderListener;
        this.mOnDeleteListener = onDeleteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_reminders, viewGroup, false);
        return new ViewHolder(view, mOnReminderListener, mOnDeleteListener);
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
        Button delete;
        OnReminderListener onReminderListener;
        OnDeleteListener onDeleteListener;

        public ViewHolder(@NonNull View itemView, OnReminderListener onReminderListener, OnDeleteListener onDeleteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.reminder_title);
            date = itemView.findViewById(R.id.reminder_date);
            delete = itemView.findViewById(R.id.delete_button);
            delete.setOnClickListener(this);
            this.onReminderListener = onReminderListener;
            this.onDeleteListener = onDeleteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: button clicked");
            onReminderListener.onReminderClick(mReminders.get(getAdapterPosition()));
        }
    }

    public interface OnReminderListener{
        void onReminderClick(Reminder reminder);
    }

    public interface OnDeleteListener{
        void onDeleteClick(Reminder reminder);
    }

}
