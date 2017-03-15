package com.example.EducationalApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Sequenceviews extends Activity{

		
	TextView seq1,seq2,seq3,seq4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewsequences);
		initialiseH();
		displayseq();
		
	}
	private void displayseq() {
		// TODO Auto-generated method stub
		String[] seq=new String[2];
		Intent intent = getIntent();
		seq= intent.getStringArrayExtra("strings");
		//seq=b.getStringArray(key);
		seq1.setText(""+seq[0]);
		seq2.setText(""+seq[1]);
		seq3.setText(""+seq[0]);
		seq4.setText(""+seq[1]);
	}

	private void initialiseH() {
		// TODO Auto-generated method stub
		seq1=(TextView) findViewById(R.id.hseq1);
		seq2=(TextView) findViewById(R.id.hseq2);
		seq3=(TextView) findViewById(R.id.hseq3);
		seq4=(TextView) findViewById(R.id.hseq4);
	}
	
	
}	





