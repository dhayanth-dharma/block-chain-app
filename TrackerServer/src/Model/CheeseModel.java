/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author user
 */
public class CheeseModel {
        public String version;
        public int c_id;
	public Date Timestamp;
	public String hash;
	public String previousHash;
	public Transaction_B data;
	public int dificulty;
        public int nonce;
        public CheeseModel()
        {}
	public CheeseModel(String version, Date timestamp, Transaction_B data) {
		this.version = version;
		this.Timestamp = timestamp;
		this.data = data;
		this.hash = computeHash();
	}
	
	public String computeHash() {
		
		String dataToHash = "" + this.version + this.Timestamp + this.previousHash + this.data;
		
		MessageDigest digest;
		String encoded = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		this.hash = encoded;
		return encoded;
		
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public Transaction_B getData() {
		return data;
	}

	public void setData(Transaction_B data) {
		this.data = data;
	}
	
}
