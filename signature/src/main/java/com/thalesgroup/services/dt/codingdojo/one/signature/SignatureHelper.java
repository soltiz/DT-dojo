package com.thalesgroup.services.dt.codingdojo.one.signature;


public class SignatureHelper {
	private static Long privateKey=23L;
	private static int nbRunningSignatures=0;
	public static long signatureOf(String dataToSign) {
		long signature=1;
		synchronized(privateKey) {
			nbRunningSignatures++;
			if (nbRunningSignatures>1) {
				throw new RuntimeException("Multithread is badly supported by signature library : unable to run more than 1 parallel computation");
			}
		}
		StringBuilder shouldTakeSomeTime = new StringBuilder();
		for (int i=0;i<20000;i++) {
			shouldTakeSomeTime.append(dataToSign);
		}
		  for (char ch : shouldTakeSomeTime.toString().toCharArray()){
			  int ascii=(int)ch;
			  signature=((long) Math.pow(ascii+privateKey,Math.pow(signature*privateKey,privateKey))) | ((long)(Math.sqrt(signature))*privateKey);
			  
		    }
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
