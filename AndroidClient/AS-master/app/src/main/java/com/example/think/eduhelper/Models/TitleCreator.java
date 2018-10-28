package com.example.think.eduhelper.Models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class TitleCreator {
    static TitleCreator titleCreator;
    List<TitleParent> titleParents;

    public TitleCreator(Context context){
        titleParents = new ArrayList<>();
        for (int i=0;i<100;i++){
            TitleParent title = new TitleParent(String.format("ECE%d",650+i),String.format("Subtitle example #%d",i));
            titleParents.add(title);
        }
    }
    public TitleCreator get(Context context){
        if (titleCreator ==null){
            titleCreator = new TitleCreator(context);
        }return titleCreator;
    }

    public List<TitleParent> getall(){
        return titleParents;
    }
}
