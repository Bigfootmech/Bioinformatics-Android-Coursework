package com.example.EducationalApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CompareSequenceActivity extends Activity{

	Button Cmpr, left, right;
	EditText input1,input2;
	String seq1, sum;
	String current_gaps;
	String seq2;
	int AS=0;
	int ABS=0, ABS2;
	char sequence1[],sequence2[];
	char q, d;
	char amino_alphabet62[]= {'C', 'S', 'T', 'P', 'A', 'G', 'N', 'D', 'E', 'Q', 'H', 'R', 'K', 'M', 'I', 'L', 'V', 'F', 'Y', 'W', '-'};
	char amino_alphabet45or80[]= {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V', 'B', 'J', 'Z', 'X', '*'};
	char DNA_alphabet[]= {'A', 'C', 'G', 'T'};
	char RNA_alphabet[]= {'A', 'C', 'G', 'U'};
	TextView display1, display2, align_comparison, tvSeq1, tvSeq2, RES;
	CheckBox DNA,RNA; 
	Spinner Matrices;
	int[][] Blosum45 = new int[][]
		 {{5, -2, -1, -2, -1, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -2, -2,  0, -1, -1, -1, -1, -5},
		 {-2,  7,  0, -1, -3,  1,  0, -2,  0, -3, -2,  3, -1, -2, -2, -1, -1, -2, -1, -2, -1, -3,  1, -1, -5},
		 {-1,  0,  6,  2, -2,  0,  0,  0,  1, -2, -3,  0, -2, -2, -2,  1,  0, -4, -2, -3,  5, -3,  0, -1, -5},
		 {-2, -1,  2,  7, -3,  0,  2, -1,  0, -4, -3,  0, -3, -4, -1,  0, -1, -4, -2, -3,  6, -3,  1, -1, -5},
		 {-1, -3, -2, -3, 12, -3, -3, -3, -3, -3, -2, -3, -2, -2, -4, -1, -1, -5, -3, -1, -2, -2, -3, -1, -5},
		 {-1,  1,  0,  0, -3,  6,  2, -2,  1, -2, -2,  1,  0, -4, -1,  0, -1, -2, -1, -3,  0, -2,  4, -1, -5},
		 {-1,  0,  0,  2, -3,  2,  6, -2,  0, -3, -2,  1, -2, -3,  0,  0, -1, -3, -2, -3,  1, -3,  5, -1, -5},
		 { 0, -2,  0, -1, -3, -2, -2,  7, -2, -4, -3, -2, -2, -3, -2,  0, -2, -2, -3, -3, -1, -4, -2, -1, -5},
		 {-2,  0,  1,  0, -3,  1,  0, -2, 10, -3, -2, -1,  0, -2, -2, -1, -2, -3,  2, -3,  0, -2,  0, -1, -5},
		 {-1, -3, -2, -4, -3, -2, -3, -4, -3,  5,  2, -3,  2,  0, -2, -2, -1, -2,  0,  3, -3,  4, -3, -1, -5},
		 {-1, -2, -3, -3, -2, -2, -2, -3, -2,  2,  5, -3,  2,  1, -3, -3, -1, -2,  0,  1, -3,  4, -2, -1, -5},
		 {-1,  3,  0,  0, -3,  1,  1, -2, -1, -3, -3,  5, -1, -3, -1, -1, -1, -2, -1, -2,  0, -3,  1, -1, -5},
		 {-1, -1, -2, -3, -2,  0, -2, -2,  0,  2,  2, -1,  6,  0, -2, -2, -1, -2,  0,  1, -2,  2, -1, -1, -5},
		 {-2, -2, -2, -4, -2, -4, -3, -3, -2,  0,  1, -3,  0,  8, -3, -2, -1,  1,  3,  0, -3,  1, -3, -1, -5},
		 {-1, -2, -2, -1, -4, -1,  0, -2, -2, -2, -3, -1, -2, -3,  9, -1, -1, -3, -3, -3, -2, -3, -1, -1, -5},
		 { 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -3, -1, -2, -2, -1,  4,  2, -4, -2, -1,  0, -2,  0, -1, -5},
		 { 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -1, -1,  2,  5, -3, -1,  0,  0, -1, -1, -1, -5},
		 {-2, -2, -4, -4, -5, -2, -3, -2, -3, -2, -2, -2, -2,  1, -3, -4, -3, 15,  3, -3, -4, -2, -2, -1, -5},
		 {-2, -1, -2, -2, -3, -1, -2, -3,  2,  0,  0, -1,  0,  3, -3, -2, -1,  3,  8, -1, -2,  0, -2, -1, -5},
		 { 0, -2, -3, -3, -1, -3, -3, -3, -3,  3,  1, -2,  1,  0, -3, -1,  0, -3, -1,  5, -3,  2, -3, -1, -5},
		 {-1, -1,  5,  6, -2,  0,  1, -1,  0, -3, -3,  0, -2, -3, -2,  0,  0, -4, -2, -3,  5, -3,  1, -1, -5},
		 {-1, -3, -3, -3, -2, -2, -3, -4, -2,  4,  4, -3,  2,  1, -3, -2, -1, -2,  0,  2, -3,  4, -2, -1, -5},
		 {-1,  1,  0,  1, -3,  4,  5, -2,  0, -3, -2,  1, -1, -3, -1,  0, -1, -2, -2, -3,  1, -2,  5, -1, -5},
		 {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -5},
		 {-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5,  1}};
	int[][] Blosum62 = new int[][] 
			{{9, -1, -1, -3,     0,	-3,	-3,	-3,	-4,	-3,	-3,	-3,	-3,	-1,	-1,	-1,	-1,	-2,	-2,	-2},
			{-1,  4,  1, -1,	 1,	0,	1,	0,	0,	0,	-1,	-1,	0,	-1,	-2,	-2,	-2,	-2,	-2,	-3},
			{-1,  1,  4,  1,    -1,	1,	0,	-1,	0,	0,	0,	-1,	0,	-1,	-2,	-2,	-2,	-2,	-2,	-3},
			{-3, -1,  1,  7,	-1,	-2,	-1,	-1,	-1,	-1,	-2,	-2,	-1,	-2,	-3,	-3,	-2,	-4,	-3,	-4},
			{0,	  1, -1, -1,	 4,	0,	-1,	-2,	-1,	-1,	-2,	-1,	-1,	-1,	-1,	-1,	-2,	-2,	-2,	-3},
			{-3,  0,  1, -2,	 0,	6,	-2,	-1,	-2,	-2,	-2,	-2,	-2,	-3,	-4,	-4,	0,	-3,	-3,	-2},
			{-3,  1,  0, -2,	-2,	0,	6,	1,	0,	0,	-1,	0,	0,	-2,	-3,	-3,	-3,	-3,	-2,	-4},
			{-3,  0, -1, -1,	-2,	-1,	1,	6,	2,	0,	-1,	-2,	-1,	-3,	-3,	-4,	-3,	-3,	-3,	-4},
			{-4,  0,  0, -1,	-1,	-2,	0,	2,	5,	2,	0,	0,	1,	-2,	-3,	-3,	-3,	-3,	-2,	-3},
			{-3,  0,  0, -1,	-1,	-2,	0,	0,	2,	5,	0,	1,	1,	0,	-3,	-2,	-2,	-3,	-1,	-2},
			{-3, -1,  0, -2,	-2,	-2,	1,	1,	0,	0,	8,	0,	-1,	-2,	-3,	-3,	-2,	-1,	2,	-2},
			{-3, -1, -1, -2,	-1,	-2,	0,	-2,	0,	1,	0,	5,	2,	-1,	-3,	-2,	-3,	-3,	-2,	-3},
			{-3,  0,  0, -1,	-1,	-2,	0,	-1,	1,	1,	-1,	2,	5,	-1,	-3,	-2,	-3,	-3,	-2,	-3},
			{ 1,  -1, -1, -2,	-1,	-3,	-2,	-3,	-2,	0,	-2,	-1,	-1,	5,	1,	2,	-2,	0,	-1,	-1},
			{-1, -2,  -2, -3,	-1,	-4,	-3,	-3,	-3,	-3,	-3,	-3,	-3,	1,	4,	2,	1,	0,	-1,	-3},
			{-1, -2, -2, -3,	-1,	-4,	-3,	-4,	-3,	-2,	-3,	-2,	-2,	2,	2,	4,	3,	0,	-1,	-2},
			{-1, -2, -2, -2,	 0,	-3,	-3,	-3,	-2,	-2,	-3,	-3,	-2,	1,	3,	1,	4,	-1,	-1,	-3},
			{-2, -2, -2, -4,	-2,	-3,	-3,	-3,	-3,	-3,	-1,	-3,	-3,	0,	0,	0,	-1,	6,	3,	1},
			{-2, -2, -2, -3,	-2,	-3,	-2,	-3,	-2,	-1,	2,	-2,	-2,	-1,	-1,	-1,	-1,	3,	7,	2},
			{-2, -3, -3, -4,	-3,	-2,	-4,	-4,	-3,	-2,	-2,	-3,	-3,	-1,	-3,	-2,	-3,	1,	2,	11}};
	int[][] Blosum80 = new int[][]
			     {{5, -2, -2, -2, -1, -1, -1,  0, -2, -2, -2, -1, -1, -3, -1,  1,  0, -3, -2,  0, -2, -2, -1, -1, -6},
		     	 {-2,  6, -1, -2, -4,  1, -1, -3,  0, -3, -3,  2, -2, -4, -2, -1, -1, -4, -3, -3, -1, -3,  0, -1, -6},
		     	 {-2, -1,  6,  1, -3,  0, -1, -1,  0, -4, -4,  0, -3, -4, -3,  0,  0, -4, -3, -4,  5, -4,  0, -1, -6},
				 {-2, -2,  1,  6, -4, -1,  1, -2, -2, -4, -5, -1, -4, -4, -2, -1, -1, -6, -4, -4,  5, -5,  1, -1, -6},
				 {-1, -4, -3, -4,  9, -4, -5, -4, -4, -2, -2, -4, -2, -3, -4, -2, -1, -3, -3, -1, -4, -2, -4, -1, -6},
				 {-1,  1,  0, -1, -4,  6,  2, -2,  1, -3, -3,  1,  0, -4, -2,  0, -1, -3, -2, -3,  0, -3,  4, -1, -6},
				 {-1, -1, -1,  1, -5,  2,  6, -3,  0, -4, -4,  1, -2, -4, -2,  0, -1, -4, -3, -3,  1, -4,  5, -1, -6},
				 { 0, -3, -1, -2, -4, -2, -3,  6, -3, -5, -4, -2, -4, -4, -3, -1, -2, -4, -4, -4, -1, -5, -3, -1, -6},
				 {-2,  0,  0, -2, -4,  1,  0, -3,  8, -4, -3, -1, -2, -2, -3, -1, -2, -3,  2, -4, -1, -4,  0, -1, -6},
				 {-2, -3, -4, -4, -2, -3, -4, -5, -4,  5,  1, -3,  1, -1, -4, -3, -1, -3, -2,  3, -4,  3, -4, -1, -6},
				 {-2, -3, -4, -5, -2, -3, -4, -4, -3,  1,  4, -3,  2,  0, -3, -3, -2, -2, -2,  1, -4,  3, -3, -1, -6},
				 {-1,  2,  0, -1, -4,  1,  1, -2, -1, -3, -3,  5, -2, -4, -1, -1, -1, -4, -3, -3, -1, -3,  1, -1, -6},
				 {-1, -2, -3, -4, -2,  0, -2, -4, -2,  1,  2, -2,  6,  0, -3, -2, -1, -2, -2,  1, -3,  2, -1, -1, -6},
				 {-3, -4, -4, -4, -3, -4, -4, -4, -2, -1,  0, -4,  0,  6, -4, -3, -2,  0,  3, -1, -4,  0, -4, -1, -6},
				 {-1, -2, -3, -2, -4, -2, -2, -3, -3, -4, -3, -1, -3, -4,  8, -1, -2, -5, -4, -3, -2, -4, -2, -1, -6},
				 { 1, -1,  0, -1, -2,  0,  0, -1, -1, -3, -3, -1, -2, -3, -1,  5,  1, -4, -2, -2,  0, -3,  0, -1, -6},
				 { 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -2, -1, -1, -2, -2,  1,  5, -4, -2,  0, -1, -1, -1, -1, -6},
				 {-3, -4, -4, -6, -3, -3, -4, -4, -3, -3, -2, -4, -2,  0, -5, -4, -4, 11,  2, -3, -5, -3, -3, -1, -6},
				 {-2, -3, -3, -4, -3, -2, -3, -4,  2, -2, -2, -3, -2,  3, -4, -2, -2,  2,  7, -2, -3, -2, -3, -1, -6},
				 { 0, -3, -4, -4, -1, -3, -3, -4, -4,  3,  1, -3,  1, -1, -3, -2,  0, -3, -2,  4, -4,  2, -3, -1, -6},
				 {-2, -1,  5,  5, -4,  0,  1, -1, -1, -4, -4, -1, -3, -4, -2,  0, -1, -5, -3, -4,  5, -4,  0, -1, -6},
				 {-2, -3, -4, -5, -2, -3, -4, -5, -4,  3,  3, -3,  2,  0, -4, -3, -1, -3, -2,  2, -4,  3, -3, -1, -6},
				 {-1,  0,  0,  1, -4,  4,  5, -3,  0, -4, -3,  1, -1, -4, -2,  0, -1, -3, -3, -3,  0, -3,  5, -1, -6},
				 {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -6},
				 {-6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6,  1}};	
			
	int mselected=0;
	int i=0;
	int n=0;
	int j=0;
	int dbase=0;
	int query=0;
	int score=0;
	int fullscore=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comparisonscreen);
		
		initialiseC();
		
		Cmpr.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				seq1=input1.getText().toString().toUpperCase();
				seq2=input2.getText().toString().toUpperCase();
				tvSeq1.setText("" + seq1);
				tvSeq2.setText("" + seq2);
				sequence1=seq1.toCharArray();
				sequence2=seq2.toCharArray();
				int l1 = seq1.length();
				int l2 = seq2.length();
				//Toast.makeText(getBaseContext(),seq1,Toast.LENGTH_LONG).show();
				
				for(i=0; i<l1; i++){
					score=0;
					for(n=0; n<20; n++){
						if(sequence1[i]==amino_alphabet62[n])query = n;
					}
					for(n=0; n<20; n++){
						if(sequence2[i]==amino_alphabet62[n])dbase = n;
					}
				score = Blosum62[dbase][query];
				fullscore = fullscore + score;
				RES.setText("" + fullscore);
			}
			}
			
		});
		DNA.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
					if (DNA.isChecked()) {
			            RNA.setChecked(false);
					}else if (RNA.isChecked()) {
			            DNA.setChecked(false);
			        }
				}
			}
		);
		RNA.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (RNA.isChecked()) {
		            DNA.setChecked(false);
				}else if (DNA.isChecked()) {
		            RNA.setChecked(false);
		        }
			}
		});
		Matrices.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		left.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				//int a=sequence2.length;
				//if(sequence2[0]!='-')addgap(a-1);
				//if(sequence2[0]=='-')removegap(0);
				fullscore = 0;
				AS--; 
				ABS=Math.abs(AS);
				j=ABS;
				int l1 = seq1.length();
				int l2 = seq2.length();
				for(i=0; i<(l1 - ABS); i++){
					score=0;
					for(n=0; n<20; n++){
						if(sequence1[i]==amino_alphabet62[n])query = n;
					}
					for(j=j; j<l2; j=j){
						for(n=0; n<20; n++){
						if(sequence2[j]==amino_alphabet62[n]) dbase = n;
						}
					score = Blosum62[dbase][query];
					fullscore += score;
					j++;
					break;
					}
				}
				int ABS2 = 2*ABS*-10;
				int complete_score = fullscore + ABS2;
				RES.setText("" + complete_score);
				tvSeq1.setText("" + seq1);
				
				String gap = "-";
				sum = seq2;
				if(AS<0){
					for(int spaces = 0; spaces>AS; spaces--){
					sum += gap;
				}
				tvSeq2.setText("" + sum);
				}
				if(AS>0){
					
					for(int spaces = 0; spaces<AS; spaces++){
						gap = gap + "-";
					}
					gap += sum;
					sum = gap;
					tvSeq2.setText("" + sum);
				}
				if(AS==0){
					tvSeq2.setText(seq2);
				}
				
			}
		});
		
		right.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				//int a=sequence2.length;
				//if(sequence2[0]=='-')addgap(0);
				//if(sequence2[a-1]=='-')removegap(0);
				fullscore = 0;
				AS++; // add one when moving to the right
				ABS=Math.abs(AS);
				j=0;
				int l1 = seq1.length();
				int l2 = seq2.length();
				for(i=ABS; i<l1; i++){
					score=0;
					for(n=0; n<20; n++){
						if(sequence1[i]==amino_alphabet62[n])query = n;
					}
					for(j=j; j<(l2-ABS); j=j){
						for(n=0; n<20; n++){
						if(sequence2[j]==amino_alphabet62[n]) dbase = n;
						}
					score = Blosum62[dbase][query];
					fullscore += score;
					j++;
					break;
					}
				}
				int ABS2 = 2*ABS*-10;
				int complete_score = fullscore + ABS2;
				RES.setText("" + complete_score);
				String gap ="-";
				sum = seq2;
				
				if(AS<0){
					for(int spaces = 0; spaces<AS; spaces++){
					sum += "-";
				}
				tvSeq2.setText("" + sum);
				}
				if(AS>0){
					
					for(int spaces = 0; spaces<AS; spaces++){
						gap = gap + "-";
					}
					gap += sum;
					sum = gap;
					tvSeq2.setText("" + sum);
				}
				if(AS==0){
					tvSeq2.setText(seq2);
				}
					
				
		}});

	}	
	protected void addgap(int pos) {
		char gap='-';
		String st2 = gap + seq2;
		String st1 = seq1 + gap;
		seq2=st2;
		seq1=st1;
		tvSeq1.setText(seq1);
		tvSeq2.setText(seq2);
		RES.setText("" + score);
	}
	protected void removegap(int pos) {
		StringBuffer buf1 = new StringBuffer( seq1.length() - 1 );
		buf1.append( seq1.substring(0,pos) ).append( seq1.substring(pos+1) );
		seq1=buf1.toString();
		StringBuffer buf2 = new StringBuffer( seq2.length() - 1 );
		buf2.append( seq2.substring(0,pos) ).append( seq2.substring(pos+1) );
		seq2=buf2.toString();
		//your calculator goes here
		tvSeq1.setText(seq1);
		tvSeq2.setText(seq2);
		RES.setText("" + score);
		
	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	      Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString()+ " selected."
	          , Toast.LENGTH_LONG).show();
	    switch(pos){
	    case 0:
	    	mselected=45;
	    	break;
	    case 1:
	    	mselected=62;
	    	break;
	    case 2:
	    	mselected=80;
	    	break;	
	    }
	    }
	    
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }

	}
	
		public void onNothingSelected(AdapterView parent) {
		      // Do nothing.
		}
	
	

	
	private void initialiseC() {
		// TODO Auto-generated method stub
		Cmpr=(Button) findViewById(R.id.bCompare);
		left=(Button) findViewById(R.id.bleft);
		right=(Button) findViewById(R.id.bright);
		input1=(EditText) findViewById(R.id.Sequence1);
		input2=(EditText) findViewById(R.id.Sequence2);
		DNA=(CheckBox)findViewById(R.id.cDNA);
		RNA=(CheckBox)findViewById(R.id.cRNA);
		RES=(TextView) findViewById(R.id.tvScore);
		tvSeq1=(TextView) findViewById(R.id.tvSeq1);
		tvSeq2=(TextView) findViewById(R.id.tvSeq2);
		Matrices= (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		            this, R.array.BLOSUMS_ARRAY, android.R.layout.simple_spinner_item);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    Matrices.setAdapter(adapter);
	}
}

		