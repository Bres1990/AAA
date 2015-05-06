package com.jmj_studio.project;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import java.util.ArrayList;

//import android.support.v7.app.ActionBarActivity;

public class MainActivity extends Activity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener
{
    private SearchView searchV;
    private GalleryAdapter gallAdapter;
    private ExpandableListView Exp_list;
    private ArrayList<ParentClass> parentClassCopy=new ArrayList<ParentClass>();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchV= (SearchView) findViewById(R.id.searchView);
        searchV.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchV.setIconifiedByDefault(false);
        searchV.setOnQueryTextListener(this);
        searchV.setOnCloseListener(this);

        displayList();

    }

    private void expandAll(){
        int count = gallAdapter.getGroupCount();
        for(int i=0; i<count; i++){
            Exp_list.expandGroup(i);
        }
    }

    private void displayList(){
        loadSomeData();

        Exp_list= (ExpandableListView) findViewById(R.id.exp_list);

        gallAdapter= new GalleryAdapter(MainActivity.this, parentClassCopy);

        Exp_list.setAdapter(gallAdapter);
    }

    private void loadSomeData(){

        ArrayList<ChildClass> childClassArrayList=new ArrayList<ChildClass>();
        ChildClass childCl;
        childCl=new ChildClass("xxx");
        childClassArrayList.add(childCl);
        childCl=new ChildClass("xxy");
        childClassArrayList.add(childCl);

        ParentClass parCl;
        parCl=new ParentClass("aaa", childClassArrayList);
        parentClassCopy.add(parCl);
        parCl=new ParentClass("aaa", childClassArrayList);
        parentClassCopy.add(parCl);

        childClassArrayList=new ArrayList<ChildClass>();
        childCl=new ChildClass("xxx");
        childClassArrayList.add(childCl);

        parCl=new ParentClass("aaa", childClassArrayList);
        parentClassCopy.add(parCl);

    }

    @Override
    public boolean onClose() {

        gallAdapter.filterData("");
        expandAll();

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        gallAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        gallAdapter.filterData(newText);
        expandAll();
        return false;
    }
}