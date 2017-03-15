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

	Button Cmpr;
	EditText input1,input2;
	String seq1;
	String seq2;
	char sequence1[],sequence2[];
	TextView display1,display2;
	CheckBox DNA,RNA; 
	Spinner Matrices;
	int Blosum45[],Blosum62[],Blosum80[];
	int mselected=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comparisonscreen);
		
		initialiseC();
		
		Cmpr.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				seq1=input1.getText().toString();
				seq2=input2.getText().toString();
				sequence1=seq1.toCharArray();
				sequence2=seq2.toCharArray();
				String a=sequence1.toString();
				Toast.makeText(getBaseContext(),seq1,Toast.LENGTH_LONG).show();
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
	private void initialiseC() {
		// TODO Auto-generated method stub
		Cmpr=(Button) findViewById(R.id.bCompare);
		input1=(EditText) findViewById(R.id.Sequence1);
		input2=(EditText) findViewById(R.id.Sequence2);
		DNA=(CheckBox)findViewById(R.id.cDNA);
		RNA=(CheckBox)findViewById(R.id.cRNA);
		Matrices= (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		            this, R.array.BLOSUMS_ARRAY, android.R.layout.simple_spinner_item);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    Matrices.setAdapter(adapter);
	}
		
}