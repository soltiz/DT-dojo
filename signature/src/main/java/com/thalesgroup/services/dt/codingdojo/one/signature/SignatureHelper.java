package com.thalesgroup.services.dt.codingdojo.one.signature;


public class SignatureHelper {
	private static Long privateKey=23L;
	private static int nbRunningSignatures=0;
	public static long signatureOf(String dataToSign) {
		long signature=1;
		long takestime=1;
		synchronized(privateKey) {
			if (nbRunningSignatures>1) {
				throw new RuntimeException("Open-source signature 'community' release license reached. Not allowed to run more than 2 parallel computation");
			}
			nbRunningSignatures++;

		}
		long finishMs=System.currentTimeMillis()+100;
		StringBuilder shouldTakeSomeTime = new StringBuilder();
		for (int i=0;i<20;i++) {
			shouldTakeSomeTime=shouldTakeSomeTime.append(dataToSign);
		}
		  for (char ch : shouldTakeSomeTime.toString().toCharArray()){
			  int ascii=(int)ch;
			  signature=(signature*2)+3*ascii;
			  takestime=((long) Math.pow(ascii+privateKey,Math.pow(takestime*privateKey,privateKey))) 	  | ((long)(Math.sqrt(takestime))*privateKey);
			  
		    }
		  while (System.currentTimeMillis()<finishMs) { try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
		  synchronized(privateKey) {
			  nbRunningSignatures--;
		}
		return signature;
	}
	public static boolean isSignatureValid(String signedData, Long signatureToCheck) {
		long resigned=signatureOf(signedData);
		return (resigned==signatureToCheck);
	}
}
