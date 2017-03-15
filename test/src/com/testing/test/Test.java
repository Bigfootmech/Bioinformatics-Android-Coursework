package com.testing.test;


public class Test {

	public static void main(String [] args)
	{
	char[] RNA=new char[]{'A','U','C','G'};
	char[][] PossibleCodons=new char[64][3];
	int i,j,k,l;
	k=0;
	int total;
	for(i=0;i<4;i++){
		for(j=0;j<4;j++){
			for(l=0;l<4;l++){
				PossibleCodons[k][0]=RNA[i];
				PossibleCodons[k][1]=RNA[j];
				PossibleCodons[k][2]=RNA[l];
				k++;	
			}
		}
		
	}
	
	for(total=0;total<(k-1);total++){
		System.out.println("hello");
	}
	}


}
