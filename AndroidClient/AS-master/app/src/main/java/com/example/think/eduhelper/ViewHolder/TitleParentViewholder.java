package com.example.think.eduhelper.ViewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.example.think.eduhelper.R;

public class TitleParentViewholder extends ParentViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public TextView courseName;
    public TextView subtitle;
    public ImageButton bt_detail;
    public TitleParentViewholder(@NonNull View itemView) {
        super(itemView);
        courseName = (TextView) itemView.findViewById(R.id.courseTitle);
        subtitle = (TextView) itemView.findViewById(R.id.parentTitle);
        bt_detail = (ImageButton) itemView.findViewById(R.id.expandRow);

    }
}
