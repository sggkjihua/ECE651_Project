package com.example.think.eduhelper.Adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.example.think.eduhelper.Models.TitleChild;
import com.example.think.eduhelper.Models.TitleParent;
import com.example.think.eduhelper.R;
import com.example.think.eduhelper.ViewHolder.TitleParentViewholder;

import java.util.List;
import java.util.zip.Inflater;

public class TaskAdaptor extends ExpandableRecyclerAdapter<TitleParent, TitleChild,TitleParentViewholder, com.example.think.eduhelper.ViewHolder.ChildViewHolder> {
    LayoutInflater layoutInflater;

    public TaskAdaptor(Context context, List<TitleParent> parentItemList){
        super(parentItemList);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TitleParentViewholder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_parent, parentViewGroup, false);
        return new TitleParentViewholder(view);
    }

    @NonNull
    @Override
    public com.example.think.eduhelper.ViewHolder.ChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_children, childViewGroup, false);
        return new com.example.think.eduhelper.ViewHolder.ChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull TitleParentViewholder parentViewHolder, int parentPosition, @NonNull TitleParent parent) {
        TitleParent titleParent = parent;
        parentViewHolder.courseName.setText(titleParent.getTitle());
        parentViewHolder.subtitle.setText(titleParent.getMaintopic());
    }

    @Override
    public void onBindChildViewHolder(@NonNull com.example.think.eduhelper.ViewHolder.ChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull TitleChild child) {
        TitleChild titleChild = child;
        childViewHolder.detailText.setText(titleChild.getDetailText());
    }
}
