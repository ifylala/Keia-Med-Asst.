package com.clevmania.keia.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clevmania.keia.R;
import com.clevmania.keia.adapter.model.ChatMessage;

import java.util.ArrayList;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.ViewHolder> {
    private static final int SELF_MESSAGE = 100;
    private ArrayList<ChatMessage> chatList;

    public SymptomsAdapter(ArrayList<ChatMessage> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == SELF_MESSAGE) {

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message, parent, false);
        } else {

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_bot_message, parent, false);
        }

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage message = chatList.get(position);
        holder.messageView.setText(message.getMsg());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = chatList.get(position);
        if (message.getId()!=null && message.getId().equals("1")) {
            return SELF_MESSAGE;
        }

        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView messageView;

        public ViewHolder(View itemView) {
            super(itemView);
            messageView = itemView.findViewById(R.id.tv_interaction);
        }
    }
}
