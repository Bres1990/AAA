package com.jmj_studio.project;

import java.util.ArrayList;

/**
 * Created by Asia on 03.05.2015.
 */
public class ParentClass {
    private String name="";
    private ArrayList <ChildClass> childClassList = new ArrayList<ChildClass>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildClass> getChildClassList() {
        return childClassList;
    }

    public void setChildClassList(ArrayList<ChildClass> childClassList) {
        this.childClassList = childClassList;
    }

    public ParentClass(String name, ArrayList<ChildClass> childClassList){
        super();
        this.name=name;
        this.childClassList = childClassList;
    }


}
