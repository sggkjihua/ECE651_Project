package com.example.think.eduhelper.ViewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.think.eduhelper.R;

public class ChildViewHolder extends com.bignerdranch.expandablerecyclerview.ChildViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public TextView detailText;
    public Button accept;
    public ChildViewHolder(@NonNull final View itemView) {
        super(itemView);
        detailText = (TextView) itemView.findViewById(R.id.detail);
        accept = (Button) itemView.findViewById(R.id.bt_accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),"Task has been added to your list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
