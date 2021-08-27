package org.prac.project.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lombok.extern.log4j.Log4j;

@Log4j
public class MemberAES256Util<DTO> {
	
	private DTO dto;
	AES256Util a256 = AES256Util.getInstance();
	private String mpassword;
	
	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	
	public String deMpassword() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return a256.decrypt(mpassword);
	}
	
	public String enMpassword() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return a256.encrypt(mpassword);
	}

	
	public MemberAES256Util(DTO dto) {
		this.dto = dto;
	}
	
}
