package com.example.EducationalApp;

import com.example.EducationalApp.Menu;
import com.example.EducationalApp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends Activity {
	
	
	Button CmprSeq,Process,Activity3,Activity4;
	TextView display;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initialiseM();
        CmprSeq.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					Class ourClass= Class.forName("com.example.EducationalApp.CompareSequenceActivity");
					Intent ourIntent= new Intent(Menu.this,ourClass);
					startActivity(ourIntent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		});
        Process.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					Class ourClass= Class.forName("com.example.EducationalApp.Process");
					Intent ourIntent= new Intent(Menu.this,ourClass);
					startActivity(ourIntent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		});
        Activity3.setOnClickListener(new View.OnClickListener() {
	
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		try{
        			Class ourClass= Class.forName("com.example.EducationalApp.Activity3");
        			Intent ourIntent= new Intent(Menu.this,ourClass);
        			startActivity(ourIntent);
        		}catch(ClassNotFoundException e){
        			e.printStackTrace();
        		}
        	}
        });
        Activity4.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		try{
        			Class ourClass= Class.forName("com.example.EducationalApp.Activity4");
        			Intent ourIntent= new Intent(Menu.this,ourClass);
        			startActivity(ourIntent);
        		}catch(ClassNotFoundException e){
        			e.printStackTrace();
        		}
        	}
        });
           
    }

	private void initialiseM() {
		// TODO Auto-generated method stub
		CmprSeq=(Button) findViewById(R.id.bSequenceComparison);
        Process=(Button) findViewById(R.id.bProcess);
        Activity3=(Button) findViewById(R.id.bActivity3);
        Activity4=(Button) findViewById(R.id.bActivity4);
	}
}