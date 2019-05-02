package com.esraa.hp.webserviceex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class UserAdapter extends ArrayAdapter<UserDetails> {

    public UserAdapter(@NonNull Context context, @NonNull ArrayList<UserDetails> users) {
        super(context, 0,users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_row,parent,false);
        TextView name=convertView.findViewById(R.id.txt1);
        TextView likes=convertView.findViewById(R.id.txt2);
        ImageView profileImage=convertView.findViewById(R.id.img);

        UserDetails userDetails=getItem(position);
        name.setText(userDetails.getName());
        likes.append(" "+userDetails.getLikes());
        Picasso.with(getContext()).load(userDetails.getImageUrl()).into(profileImage);
        return convertView;
    }
}
