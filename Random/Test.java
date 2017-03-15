package com.example.EducationalApp;

import java.util.Random;

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

public class Test extends Activity{

	
	char amino_alphabet62[]= {'C', 'S', 'T', 'P', 'A', 'G', 'N', 'D', 'E', 'Q', 'H', 'R', 'K', 'M', 'I', 'L', 'V', 'F', 'Y', 'W', '-'};
	char amino_alphabet45or80[]= {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V', 'B', 'J', 'Z', 'X', '*'};
	TextView randomseq;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		initialiseT();
		Randomseq(10,amino_alphabet62);
	}
	private void Randomseq(int slength, char[] matrix){
			
			String seq1="aa";
			StringBuffer sb= new StringBuffer();
			Random rand= new Random();
			char newchar;
			int q;
			int ind=0;
			int mlength=0;	
			mlength=(matrix.toString()).length();
			for(q=0;q<slength;q++){
				ind=rand.nextInt(mlength);
				newchar=matrix[ind];
				sb.append(newchar);
			}
			seq1=sb.toString();
			randomseq.setText(""+ seq1);
				
	}
	private void initialiseT() {
		// TODO Auto-generated method stub
		randomseq=(TextView) findViewById(R.id.textView1);
	}
		
		
}
