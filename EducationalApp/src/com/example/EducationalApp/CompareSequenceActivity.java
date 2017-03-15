//optimise to check whether the "matching region" is longer or shorter than the BLOSUM matrix, to speed up running time

package com.example.EducationalApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CompareSequenceActivity extends Activity
{

	Button Cmpr, left, right;
	EditText input1,input2;
	String seq1;
	String seq2;
	int AS=0;
	int ABS=0;
	char sequence1[],sequence2[];
	char q, d;
	char amino_alphabet62[]= {'C', 'S', 'T', 'P', 'A', 'G', 'N', 'D', 'E', 'Q', 'H', 'R', 'K', 'M', 'I', 'L', 'V', 'F', 'Y', 'W'};
	char amino_alphabet45or80[]= {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V', 'B', 'J', 'Z', 'X', '*'};
	TextView display1, display2, align_comparison, tvSeq1, tvSeq2, RES;
	CheckBox DNA,RNA; 
	Spinner Matrices;
	int mselected=0;
	//int i=0;
	//int n=0;
	//int j=0;
	int dbase=0;
	int query=0;
	int score=0;
	int fullscore=0;
	int alignment = 0;
	
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
			{{9, -1,	-1,	-3,	0,	-3,	-3,	-3,	-4,	-3,	-3,	-3,	-3,	-1,	-1,	-1,	-1,	-2,	-2,	-2},
			{-1, 4,	1,	-1,	1,	0,	1,	0,	0,	0,	-1,	-1,	0,	-1,	-2,	-2,	-2,	-2,	-2,	-3},
			{-1, 1,	4,	1,	-1,	1,	0,	1,	0,	0,	0,	-1,	0,	-1,	-2,	-2,	-2,	-2,	-2,	-3},
			{-3, -1,	1,	7,	-1,	-2,	-1,	-1,	-1,	-1,	-2,	-2,	-1,	-2,	-3,	-3,	-2,	-4,	-3,	-4},
			{0,	1,	-1,	-1,	4,	0,	-1,	-2,	-1,	-1,	-2,	-1,	-1,	-1,	-1,	-1,	-2,	-2,	-2,	-3},
			{-3, 0,	1, -2,	0,	6,	-2,	-1,	-2,	-2,	-2,	-2,	-2,	-3,	-4,	-4,	0,	-3,	-3,	-2},
			{-3, 1,	0,	-2,	-2,	0,	6,	1,	0,	0,	-1,	0,	0,	-2,	-3,	-3,	-3,	-3,	-2,	-4},
			{-3, 0,	1,	-1,	-2,	-1,	1,	6,	2,	0,	-1,	-2,	-1,	-3,	-3,	-4,	-3,	-3,	-3,	-4},
			{-4, 0,	0,	-1,	-1,	-2,	0,	2,	5,	2,	0,	0,	1,	-2,	-3,	-3,	-3,	-3,	-2,	-3},
			{-3, 0,	0,	-1,	-1,	-2,	0,	0,	2,	5,	0,	1,	1,	0,	-3,	-2,	-2,	-3,	-1,	-2},
			{-3, -1, 0,	-2,	-2,	-2,	1,	1,	0,	0,	8,	0,	-1,	-2,	-3,	-3,	-2,	-1,	2,	-2},
			{-3, -1, -1,	-2,	-1,	-2,	0,	-2,	0,	1,	0,	5,	2,	-1,	-3,	-2,	-3,	-3,	-2,	-3},
			{-3, 0,	0, -1,	-1,	-2,	0,	-1,	1,	1,	-1,	2,	5,	-1,	-3,	-2,	-3,	-3,	-2,	-3},
			{1, -1,	-1,	-2,	-1,	-3,	-2,	-3,	-2,	0,	-2,	-1,	-1,	5,	1,	2,	-2,	0,	-1,	-1},
			{-1, -2, -2,	-3,	-1,	-4,	-3,	-3,	-3,	-3,	-3,	-3,	-3,	1,	4,	2,	1,	0,	-1,	-3},
			{-1, -2, -2,	-3,	-1,	-4,	-3,	-4,	-3,	-2,	-3,	-2,	-2,	2,	2,	4,	3,	0,	-1,	-2},
			{-1, -2, -2,	-2,	0,	-3,	-3,	-3,	-2,	-2,	-3,	-3,	-2,	1,	3,	1,	4,	-1,	-1,	-3},
			{-2, -2, -2,	-4,	-2,	-3,	-3,	-3,	-3,	-3,	-1,	-3,	-3,	0,	0,	0,	-1,	6,	3,	1},
			{-2, -2, -2,	-3,	-2,	-3,	-2,	-3,	-2,	-1,	2,	-2,	-2,	-1,	-1,	-1,	-1,	3,	7,	2},
			{-2, -3, -3,	-4,	-3,	-2,	-4,	-4,	-3,	-2,	-2,	-3,	-3,	-1,	-3,	-2,	-3,	1,	2,	11}};
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

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comparisonscreen);
		
		initialiseC();
		
		Cmpr.setOnClickListener
		(
				new View.OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				align_comparison.setText("COMPARE");
				// TODO Auto-generated method stub
				//seq1=;
				//seq2=;
				sequence1=input1.getText().toString().toCharArray();
				sequence2=input2.getText().toString().toCharArray();
				alignment = 0;
				new Thread
				(
						new Runnable()
				{
					//@Override
					public void run() 
					{		
						CompSeq(amino_alphabet62,Blosum62); // !!!!!!!! HMMM !!!!!!!!!
					}
				}).start();
				
				/*
				 * 
				int l1 = seq1.length();
				int l2 = seq2.length();
				//Toast.makeText(getBaseContext(),seq1,Toast.LENGTH_LONG).show();
				
				for(int i=0; i<l1; i++)
				{
					score=0;
					for(int n=0; n<20; n++)
					{
						if(sequence1[i]==amino_alphabet62[n])query = n;
					}
					for(int n=0; n<20; n++)
					{
						if(sequence2[i]==amino_alphabet62[n])dbase = n;
					}
				score = Blosum62[dbase][query];
				fullscore = fullscore + score;
				align_comparison.setText("" + fullscore);
				}
				
				*/
			}
			
		}); // End Cmpr OnClickListener
		
		DNA.setOnClickListener
		(
				new View.OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if (DNA.isChecked()) 
				{
			        RNA.setChecked(false);
				}
				else if (RNA.isChecked()) 
				{
			        DNA.setChecked(false);
			    }
			}
		}); // End DNA OnClickListener
		
		RNA.setOnClickListener
		(
				new View.OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if (RNA.isChecked()) 
				{
			        DNA.setChecked(false);
				}
				else if (DNA.isChecked()) 
				{
			        RNA.setChecked(false);
			    }
			}
		}); // End RNA OnClickListener
		
		Matrices.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		left.setOnClickListener
		(
				new View.OnClickListener() 
		{
			public void onClick(View v) 
			{
				/*// The original code :S
				int a=sequence2.length;
				if(sequence2[0]!='-')addgap(a-1);
				if(sequence2[0]=='-')removegap(0);
				AS= AS-1; //minus one moving one position to the left
				ABS=Math.abs(AS);
				int l1 = seq1.length();
				for(int i=0; i<l1; i++)
				{
					score=0;
					for(int n=0; n<20; n++)
					{
						if(sequence1[i]==amino_alphabet62[n])query = n;
					}
					for(int n=0; n<20; n++)
					{
						if(sequence2[i]==amino_alphabet62[n])dbase = n;
					}
					score = Blosum62[dbase][query];
					int ABS2 = 2*ABS;
					fullscore = fullscore + score + ABS2;
					align_comparison.setText("" + fullscore);
				
				}
				

				*/

				align_comparison.setText("LEFT");
				if (alignment > -sequence2.length) // check that it's not one out, but shouldn't be :/ 
				{
					alignment--;
					new Thread
					(
							new Runnable()
					{
						//@Override
						public void run() 
						{		
							CompSeq(amino_alphabet62,Blosum62); // !!!!!!!! HMMM !!!!!!!!!
						}
					});//.start();
				}
				
			}
		});// End left OnClickListener
		
		right.setOnClickListener
		(
				new View.OnClickListener() 
		{
			public void onClick(View v) 
			{
				/*// The original code :S
				int a=sequence2.length;
				if(sequence2[0]=='-')addgap(0);
				if(sequence2[a-1]=='-')removegap(0);
				AS = AS + 1; // add one when moving to the right
				ABS=Math.abs(AS);
				int l1 = seq1.length();
				for(int i=0; i<l1; i++)
				{
					score=0;
					for(int n=0; n<20; n++)
					{
						if(sequence1[i]==amino_alphabet62[n])query = n;
					}
					for(int n=0; n<20; n++)
					{
						if(sequence2[i]==amino_alphabet62[n])dbase = n;
					}
				score = Blosum62[dbase][query];
				int ABS2 = 2*ABS;
				fullscore = fullscore + score + ABS2;
				align_comparison.setText("" + fullscore);
				}
				*/
				if (alignment < sequence1.length)
				{
					alignment++;
					new Thread
					(
							new Runnable()
					{
						//@Override
						public void run() 
						{	
							align_comparison.setText("RIGHT");	
							//CompSeq(amino_alphabet62,Blosum62); // !!!!!!!! HMMM !!!!!!!!!
						}
					}).start();
				}
			}
		});

	} // Overwrite onCreate
	
	/*
	protected void addgap(int pos) 
	{
		char gap='-';
		String st2 = gap + seq2;
		String st1 = seq1 + gap;
		seq2=st2;
		seq1=st1;
		int l1 = seq1.length();
		for(int i=0; i<l1; i++)
		{
			score=0;
			for(int n=0; n<20; n++)
			{
				if(sequence1[i]==amino_alphabet62[n])query = n;
			}
			for(int n=0; n<20; n++)
			{
				if(sequence2[i]==amino_alphabet62[n])dbase = n;
			}
		score = Blosum62[dbase][query];
		tvSeq1.setText(seq1);
		tvSeq2.setText(seq2);
		RES.setText("" + score);
		}
	}
	
	*/
	
	/*
	protected void removegap(int pos) 
	{
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
	
	*/
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener 
	{

	    public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) 
	    {
	    	Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString()+ " selected.", Toast.LENGTH_LONG).show();
	    	switch(pos)
	    	{
		    case 0:
		    	mselected=45;
		    	break;
		    case 1:
		    	mselected=62;
		    	break;
		    case 2:
		    	mselected=80; // set blosumchars and blosumscore from here?
		    	break;	
	    	}
	    }
	    
	    public void onNothingSelected(AdapterView parent) 
	    {
	      // Do nothing.
	    }

	}
	
	public void onNothingSelected(AdapterView parent) 
	{
	// Do nothing.
	}
	
	public void CompSeq(char[] BLOSUMCHARS, int[][] BLOSUMSCORE){
		// set penalty for gaps left and right?
		int seqlength1=sequence1.length;
		int seqlength2=sequence2.length;
		int matching = 0;
		int start = 0;
		int end = 0;
		int x_coord = 0; // need an error coordinate
		int y_coord = 0; // need an error coordinate, or adding in code checking text belongs to a BLOSUM matrix
		boolean pingx = true;
		boolean pingy = true;
		align_comparison.setText("WHAT DO");
		if (alignment > 0) start = alignment; //only if we've moved the bottom string to the right should we move our start point.
		if (seqlength2+alignment > seqlength1) // If sequence2 extends past 1
		{
			end = seqlength1; // have the end lookup at the end of the 1st segment, so you don't over reach
		}
		else
		{
			end = seqlength2 + end;  //else have it at the shorter tail (again).
		}
		
		
		for(int i=start; i<end; i++) //check if < end or <= end . Loop this as "bigger" because the sequences aren't of restricted size, so will save on running time with longer inputs than the BLOSUM matrices. Could optimise to check :/
		{
			//optimization lookup - go along, if you find one, stop for it, if the other one not, look from stopped to end.
			score=0;
			for(int n=0; n<BLOSUMCHARS.length; n++) //check if < or <=
			{

				align_comparison.setText(i + " " + n);
				if(sequence1[i]==BLOSUMCHARS[n]) // check alphabet changing <--- feed in?
				{
					x_coord = n;
					pingx = false;
				}
				if(sequence2[i]==BLOSUMCHARS[n]) // no point having two loops
				{
					y_coord = n;
					pingy = false;
				}
				
			}
			if (pingx)
			{
				//align_comparison.setText("Letter " + sequence1[i] + " doesn't exist in this comparison matrix.");
				Toast.makeText(getApplicationContext(), "Letter " + sequence1[i] + " doesn't exist in this comparison matrix.", Toast.LENGTH_LONG).show();
		    	//error warning letter doesn't exist in matrix
				
			}
			else if (pingy)
			{
				//align_comparison.setText("Letter " + sequence2[i] + " doesn't exist in this comparison matrix.");
				Toast.makeText(getApplicationContext(), "Letter " + sequence2[i] + " doesn't exist in this comparison matrix.", Toast.LENGTH_LONG).show();
		    	//doesn't matter if they overwrite each other, there'll only be no error if all the letters are fixed.
			}
			else
			{
				matching += BLOSUMSCORE[x_coord][y_coord];
			}
				
		
		}// end character loop
		if (!(pingx || pingy)) align_comparison.setText(matching); // faster than doing it every time
		
	}
	
	

	
	private void initialiseC() 
	{
		// TODO Auto-generated method stub
		Cmpr=(Button) findViewById(R.id.bCompare);
		left=(Button) findViewById(R.id.bleft);
		right=(Button) findViewById(R.id.bright);
		input1=(EditText) findViewById(R.id.Sequence1);
		input2=(EditText) findViewById(R.id.Sequence2);
		DNA=(CheckBox)findViewById(R.id.cDNA);
		RNA=(CheckBox)findViewById(R.id.cRNA);
		RES=(TextView) findViewById(R.id.tvResults);
		align_comparison = (TextView) findViewById(R.id.res);
		tvSeq1=(TextView) findViewById(R.id.tvSeq1);
		tvSeq2=(TextView) findViewById(R.id.tvSeq2);
		Matrices= (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		            this, R.array.BLOSUMS_ARRAY, android.R.layout.simple_spinner_item);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    Matrices.setAdapter(adapter);
	}
} // End CompareSequenceActivity

		