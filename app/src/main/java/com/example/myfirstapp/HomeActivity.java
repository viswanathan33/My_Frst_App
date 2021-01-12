package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myfirstapp.common.Constants;
import com.example.myfirstapp.model.UserInfo;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences preferences;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Gson gson=new Gson();
        preferences=getSharedPreferences(Constants.BundleKey.share_pref,MODE_PRIVATE);
        String json =preferences.getString(Constants.BundleKey.USER_INFO,"");
        UserInfo userInfo=gson.fromJson(json,UserInfo.class);

        ArrayList<ExampleItem> exampleList=new ArrayList<>();
        exampleList.add(new ExampleItem("viswa","21",userInfo.getEmail(),"male"));
        exampleList.add(new ExampleItem("viswa","21","viswa@gmail.com","male"));
        exampleList.add(new ExampleItem("viswa","21","viswa@gmail.com","male"));
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //TextView textView=(TextView)findViewById(R.id.user4);
       // TextView textView1=(TextView)findViewById(R.id.user6);
        //Log.d("TAG","data===>"+json);
       // textView.setText(userInfo.getEmail());
       // textView1.setText(userInfo.getPassword());


        /*String eid=preferences.getString(Constants.BundleKey.sh_pre_mail,"    ");
        String passw=preferences.getString(Constants.BundleKey.sh_pre_password,"");
        if(eid!=null||passw!=null) {
            textView1.setText(eid);
            textView1.setText(passw);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.drop_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        /*Log.d("TAG","data===>"+id);
        if(id==R.id.item1){
            Toast.makeText(getApplicationContext(),"item 1 selected",Toast.LENGTH_SHORT).show();
        }
        return true;*/
        switch (id){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"item 1 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"item 2 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.apply();
                editor.commit();
                Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                //Toast.makeText(getApplicationContext(),"logout selected",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}