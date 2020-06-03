package com.example.remindme.adapters;

import android.text.Layout;
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

public class RemindersRecyclerAdapter extends RecyclerView.Adapter<RemindersRecyclerAdapter.ViewHolder> {

    private ArrayList<Reminder> mReminder = new ArrayList<>();

    public RemindersRecyclerAdapter(ArrayList<Reminder> reminder) {
        this.mReminder = reminder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_reminders, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.title.setText(mReminder.get(position).getTitle());
        viewHolder.time.setText(mReminder.get(position).getTime());
        viewHolder.date.setText(mReminder.get(position).getDate());
        viewHolder.repeat.setText(mReminder.get(position).getRepeat());
    }

    @Override
    public int getItemCount() {
        return mReminder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, date, time, repeat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.reminder_title);
            time = itemView.findViewById(R.id.reminder_timestamp);
        }
    }
}
