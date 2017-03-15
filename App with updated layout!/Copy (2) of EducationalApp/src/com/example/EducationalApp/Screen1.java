package com.example.EducationalApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Screen1 extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen1);
		Thread timer = new Thread(){
			public void run(){
				
				try{
					sleep(3000);
					
					} catch (InterruptedException e){
						e.printStackTrace();
					}finally{
						Intent openMenu = new Intent("com.example.EducationalApp.MENU");
						startActivity(openMenu);
					}
				
				}
			
			};
			timer.start();			
		}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}