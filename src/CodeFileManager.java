import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;



public class CodeFileManager implements Serializable {

	private Cipher cipher;
	private KeyPairGenerator generator;
	private KeyPair keyPair;
	private PrivateKey privKey;
	private PublicKey pubKey;


	public CodeFileManager(){
		try {
			cipher =  Cipher.getInstance("RSA");
			generator = KeyPairGenerator.getInstance("RSA");
			keyPair = generator.generateKeyPair();
			privKey = keyPair.getPrivate();
			pubKey = keyPair.getPublic();
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public void SaveEncryptedFile(String code) {

		byte[] codeAsBytes = code.getBytes();

		try {
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);

			FileOutputStream outputFile = new FileOutputStream("encryptedCode.ser");
			CipherOutputStream cos = new CipherOutputStream(outputFile, cipher);

			for(byte b: codeAsBytes)
				cos.write(b);

			cos.close();
			outputFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

	public String OpenEncryptedFile() {
		
		String code = null;
		try{
			cipher.init(Cipher.DECRYPT_MODE, privKey);
			FileInputStream inputFile = new FileInputStream("encryptedCode.ser");
			CipherInputStream cis = new CipherInputStream(inputFile, cipher);

			BufferedReader br = new BufferedReader(new InputStreamReader(cis));
			code = br.readLine();

			br.close();
			cis.close();
			inputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return code;

	}

}
