package com.thalesgroup.services.dt.codingdojo.one.signature;

public class SignatureHelper {
	private static Long privateKey = 23L;
	private static int nbRunningSignatures = 0;

	public static long signatureOf(String dataToSign) {
		long signature = 1;
		long takestime = 1;
		StringBuilder signatureKeyPattern = new StringBuilder();

//			nbRunningSignatures++;
//			if (nbRunningSignatures > 2) {
//				throw new RuntimeException(
//						"Multithread is badly supported by signature library : unable to run more than 2 parallel computation");
//				// TODO : warn user?
//			}

			for (int i = 0; i < 1; i++) {
				signatureKeyPattern = signatureKeyPattern.append(dataToSign);
			}

			for (char ch : signatureKeyPattern.toString().toCharArray()) {
				int ascii = (int) ch;
				signature = (signature * 2) + 3 * ascii;
				takestime = ((long) Math.pow(ascii + privateKey,
						Math.pow(takestime * privateKey, privateKey)))
						| ((long) (Math.sqrt(takestime)) * privateKey);

			}

			nbRunningSignatures--;
		return signature;
	}

	public static boolean isSignatureValid(String signedData,
			Long signatureToCheck) {
		long resigned = signatureOf(signedData);
		return (resigned == signatureToCheck);
	}
}
