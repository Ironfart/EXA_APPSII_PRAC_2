package com.example.exa_appsii_prac_2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    private int resource;
    ArrayList<User> user;

    public UserAdapter(Context context, int resource, ArrayList<User> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.user = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtId, txtUser, txtName, txtLastN;

        if(convertView == null){
            LayoutInflater iInflator = ((Activity)context).getLayoutInflater();
            convertView = iInflator.inflate(resource,parent,false);
        }

        //txtId = convertView.findViewById(R.id.txtVwId);
        txtUser = convertView.findViewById(R.id.txtVwUsername);
        txtName = convertView.findViewById(R.id.txtVwName);
        txtLastN = convertView.findViewById(R.id.txtVwLastName);

        //txtId.setText(amigo.get(position).getId());
        txtUser.setText(user.get(position).getUsername());
        txtName.setText(user.get(position).getName());
        txtLastN.setText(user.get(position).getLastN());

        return convertView;
    }
}
