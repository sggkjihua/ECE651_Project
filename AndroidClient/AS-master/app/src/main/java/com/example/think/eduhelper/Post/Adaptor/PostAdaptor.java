package com.example.think.eduhelper.Post.Adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.example.think.eduhelper.Post.model.ItemView.TitleChild;
import com.example.think.eduhelper.Post.model.ItemView.TitleParent;
import com.example.think.eduhelper.R;
import com.example.think.eduhelper.Post.ui.ViewHolder_Posts.TitleParentViewholder;

import java.util.List;

public class PostAdaptor extends ExpandableRecyclerAdapter<TitleParent, TitleChild,TitleParentViewholder, com.example.think.eduhelper.Post.ui.ViewHolder_Posts.ChildViewHolder> {
    LayoutInflater layoutInflater;

    public PostAdaptor(Context context, List<TitleParent> parentItemList){
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
    public com.example.think.eduhelper.Post.ui.ViewHolder_Posts.ChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_children, childViewGroup, false);
        return new com.example.think.eduhelper.Post.ui.ViewHolder_Posts.ChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull TitleParentViewholder parentViewHolder, int parentPosition, @NonNull TitleParent parent) {
        TitleParent titleParent = parent;
        parentViewHolder.courseName.setText(titleParent.getTitle());
        parentViewHolder.subtitle.setText(titleParent.getMaintopic());
    }

    @Override
    public void onBindChildViewHolder(@NonNull com.example.think.eduhelper.Post.ui.ViewHolder_Posts.ChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull TitleChild child) {
        TitleChild titleChild = child;
        childViewHolder.detailText.setText(titleChild.getDetailText());
    }
}
