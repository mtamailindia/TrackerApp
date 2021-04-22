package com.mta.trackerapplication.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mta.trackerapplication.R;
import com.mta.trackerapplication.listener.UserItemClickListener;
import com.mta.trackerapplication.model.TrackerData;
import com.mta.trackerapplication.model.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.TrackerHolder> {

    private Context mContext;
    private List<User> mList;
    private UserItemClickListener mListener;

    public UserAdapter(Context mContext, List<User> mList, UserItemClickListener listener) {
        this.mContext = mContext;
        this.mList = mList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public TrackerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new TrackerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackerHolder holder, int position) {
        User data = mList.get(position);
        holder.tvName.setText(data.getName());
        holder.tvMobile.setText(data.getMobileNo());

        holder.btnAddExpence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onUserItemClick(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class TrackerHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvMobile;
        TextView tvAmount;
        Button btnAddExpence;

        public TrackerHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.name);
            tvMobile = itemView.findViewById(R.id.etMobile);
            tvAmount = itemView.findViewById(R.id.amount);
            btnAddExpence = itemView.findViewById(R.id.btnAddExpence);
        }
    }
}
