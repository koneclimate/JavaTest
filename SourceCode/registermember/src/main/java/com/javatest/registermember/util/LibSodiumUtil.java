package com.javatest.registermember.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import com.muquit.libsodiumjna.SodiumKeyPair;
import com.muquit.libsodiumjna.SodiumLibrary;
import com.muquit.libsodiumjna.exceptions.SodiumLibraryException;
import com.sun.jna.Platform;

import lombok.extern.log4j.Log4j2;



@Log4j2
public class LibSodiumUtil {

	private static String libraryPath = null;
		
	public static void LoadPath() {
		if (Platform.isMac())
			{
			    // MacOS
			    libraryPath = "config/libsodium.dylib";
//			    libraryPath = libraryPath;
			    log.info("Library path in Mac: " + libraryPath);
			}
			else if (Platform.isWindows())
			{
			    // Windows
			
			    libraryPath = "config/libsodium.dll";
			    log.info("Library path in Windows: " + libraryPath);
			}
			else
			{
			    // Linux
			    libraryPath = "config/libsodium.so";
			    log.info("Library path: " + libraryPath);
			}
			log.info("loading libsodium...");
			SodiumLibrary.setLibraryPath(libraryPath);
			// To check the native library is actually loaded, print the version of 
			// native sodium library
			String v = SodiumLibrary.libsodiumVersionString();
			log.info("libsodium version: " + v);
			
	}
	public static String encrypt(String message,String nonce) throws SodiumLibraryException, IOException {
		byte[] cipherText = message.getBytes();
//		byte[] cipherText = SodiumUtils.hex2Binary(message);
		
//		byte[] Nonce = SodiumUtils.hex2Binary(nonce);
		byte[] Nonce = Base64.getDecoder().decode(nonce);
		byte[] publicKey = Base64.getDecoder().decode(readFile("serverPublic"));
		byte[] privateKey = Base64.getDecoder().decode(readFile("ccdServer.key"));
		byte[] encrypt = SodiumLibrary.cryptoBoxEasy(
		        cipherText,
		        Nonce,
		        publicKey,
		        privateKey);
		return Base64.getEncoder().encodeToString(encrypt);
	}
	
	
	public static String decrypt(String message,String nonce) throws SodiumLibraryException, IOException {
		byte[] cipherText =  Base64.getDecoder().decode(message);
		byte[] Nonce =  Base64.getDecoder().decode(nonce);
		byte[] publicKey = Base64.getDecoder().decode(readFile("serverPublic"));
		byte[] privateKey = Base64.getDecoder().decode(readFile("ccdServer.key"));
		String decrypteString = null;
		byte[] decrypted = SodiumLibrary.cryptoBoxOpenEasy(
			        cipherText,
			        Nonce,
			        publicKey,
			        privateKey);
		decrypteString = new String(decrypted, "UTF-8");
		return decrypteString;
		
		
	}
	
	
	public static void GenerateKey(String name) throws SodiumLibraryException, IOException {
		SodiumKeyPair keypair = SodiumLibrary.cryptoBoxKeyPair();
		byte[] PublicKey = keypair.getPublicKey();
		byte[] PrivateKey = keypair.getPrivateKey();
		String publicString =  Base64.getEncoder().encodeToString(PublicKey);
		String privateString =  Base64.getEncoder().encodeToString(PrivateKey);
		writeFile(name+"Public", publicString);
		writeFile(name+"Server.key", privateString);
	}
	
	public static void writeFile(String name,String data) throws IOException {
		FileWriter nameData=new FileWriter(name); 
		for (int i = 0; i < data.length(); i++) 
			nameData.write(data.charAt(i)); 
		nameData.close(); 
	}
	
	public static String readFile(String name) throws IOException  {
		int ch; 
		  
        // check if File exists or not 

//		String dir1 = "D:/config/";
        FileReader fr=null; 
        try{ 
            fr = new FileReader("config/"+name); 
        } 
        catch (FileNotFoundException fe) { 
            System.out.println("File not found"); 
        } 
        String data = "";
        // read from FileReader till the end of file \
        while ((ch=fr.read())!=-1) {
//        	System.out.print((char)ch); 
        	data = data+ (char)ch;
        }
            

  
        // close the file 
        fr.close(); 
        return data;
	}
}
