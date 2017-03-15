package com.example.EducationalApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends Activity {
	/////////////////////////////////////////
	///////////JAKSA!!!!! JUST TO GET YOUR ATTENTION
	///you can skip all this code until you find the Sequenceviews.onclicklistener
	//you'll the the method i used to pass the sequences into the subactivity
	//which you can use in the comparesequenceactivity.
	
	Button CmprSeq,Process,Test,Sequenceviews;
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
        Test.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					Class ourClass= Class.forName("com.example.EducationalApp.Test");
					Intent ourIntent= new Intent(Menu.this,ourClass);
					startActivity(ourIntent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		});
        Sequenceviews.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		try{
        			Class ourClass= Class.forName("com.example.EducationalApp.Sequenceviews");
        			Intent ourIntent= new Intent(Menu.this,ourClass);
        			String[] seqarray = new String[2];
        			seqarray[0]="qqqqqqqqqqqqqqqqqqqqqqqqqqqweqweqweqweqweasdasdqwqqq";
        			seqarray[1]="qpqwpkqwepqkwpekqwpekqpwekasdsfsfdqweqweqeasdqpekwpe";
        			ourIntent.putExtra("strings",seqarray);
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
        Test=(Button) findViewById(R.id.bTest);
        Sequenceviews=(Button) findViewById(R.id.bSequenceviews);
	}
}