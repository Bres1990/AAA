package com.jmj_studio.project;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.*;
import android.view.ViewGroup;
import android.view.View;
import android.widget.BaseExpandableListAdapter;

import java.util.*;

public class GalleryAdapter extends BaseExpandableListAdapter{

    private Context ctx;
    private ArrayList<ParentClass> parentClassOrginal;
    private ArrayList<ParentClass> parentClassCopy;


    public GalleryAdapter(Context ctx, ArrayList<ParentClass> parentClassOrginal){
        this.ctx=ctx;
        this.parentClassCopy = new ArrayList<ParentClass>();
        parentClassCopy.addAll(parentClassCopy);
        this.parentClassOrginal = new ArrayList<ParentClass>();
        parentClassOrginal.addAll(parentClassCopy);
    }


    @Override
    public Object getChild(int groupPosition, int child){
        ArrayList<ChildClass> childList=parentClassCopy.get(groupPosition).getChildClassList();
        return childList.get(child); }

    @Override
    public long getChildId(int groupPosition, int child){
        return child; }

    @Override
    public View getChildView(int groupPosition, int child, boolean lastChild, View convertView, ViewGroup parentView){

        ChildClass childCl= (ChildClass) getChild(groupPosition, child);

        if(convertView==null){
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflator.inflate(R.layout.child_layout, null);
        }

        TextView name=(TextView) convertView.findViewById(R.id.child_txt);
        name.setText(childCl.getName().trim());

        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<ChildClass> childCl = parentClassCopy.get(groupPosition).getChildClassList();
        return childCl.size();
    }

    @Override
    public Object getGroup(int groupPosition){
        return parentClassCopy.get(groupPosition);
    }

    @Override
    public int getGroupCount(){
        return parentClassCopy.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition; }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parentView){

        ParentClass parentCl= (ParentClass) getGroup(groupPosition);

        if(convertView==null){
            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflator.inflate(R.layout.parent_layout, null);
        }

        TextView parent_textview = (TextView) convertView.findViewById(R.id.parent_txt);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(parentCl.getName().trim());

        return convertView;
    }

    @Override
    public boolean hasStableIds(){
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }

    public void filterData(String query){

        query.toLowerCase();
        parentClassCopy.clear();

        if(query.isEmpty()){
            parentClassCopy.addAll(parentClassOrginal);
        }
        else{
            for(ParentClass parentCl: parentClassCopy){
                ArrayList <ChildClass> childList = parentCl.getChildClassList();
                ArrayList <ChildClass> newList = new ArrayList<ChildClass>();
                for(ChildClass childCl: childList){
                    if(childCl.getName().toLowerCase().contains(query)){
                        newList.add(childCl);
                    }
                }
                if(newList.size()>0){
                    ParentClass parCl=new ParentClass(parentCl.getName(), newList);
                    parentClassCopy.add(parCl);
                }
            }
        }

        notifyDataSetChanged();

    }
}