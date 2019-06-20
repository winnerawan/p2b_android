/*
 * Copyright (c) 2018. Winnerawan T - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential|
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2018
 */

package id.ac.unipma.eapt.ui.student;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import id.ac.unipma.eapt.R;
import id.ac.unipma.eapt.data.network.model.Program;

import java.util.List;

public class ProgramStudyAdapter extends ArrayAdapter<Program> {

    private int groupid;
    List<Program> userTypes;
    private LayoutInflater inflater;

    public ProgramStudyAdapter(Activity context, int groupid, int id, List<Program> userTypes) {
        super(context, id, userTypes);
        this.userTypes = userTypes;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid = groupid;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View itemView = inflater.inflate(groupid, parent, false);

        TextView textView = itemView.findViewById(R.id.txt_account_type);
        textView.setText(userTypes.get(position).getName());

//        ImageView imageView = itemView.findViewById(R.id.icon_account_type);
//
//        switch (userTypes.get(position).getName().toLowerCase()) {
//            case "mahasiswa":
//                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_type_citizen));
//                break;
//            case "umum":
//                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_type_organization));
//                break;
//            default:
//                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_type_citizen));
//                break;
//        }
        return itemView;
    }

    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);

    }
}