package com.sg.logincounter.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sg.logincounter.R;
import com.sg.logincounter.model.User;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder>{
    private ArrayList<User> users;

    public UsersAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list,viewGroup,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.textViewName.setText("Name: " +users.get(i).getName());
        userViewHolder.textViewEmail.setText("Email: " +users.get(i).getEmail());
        userViewHolder.textViewCounter.setText("Counter: "+users.get(i).getCounter());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewEmail;
        private TextView textViewCounter;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.tvname);
            textViewEmail=itemView.findViewById(R.id.tvemail);
            textViewCounter=itemView.findViewById(R.id.tvcounter);
        }
    }
}
