package org.prac.project.member.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.prac.project.member.dto.MemberGetOneDTO;
import org.prac.project.member.dto.MemberInsertDTO;
import org.prac.project.member.dto.MemberLoginDTO;
import org.prac.project.member.dto.MemberLoginResponseDTO;
import org.prac.project.member.dto.MemberUpdateReqDTO;

public interface MemberService {
	
	public MemberLoginResponseDTO memberLogin(MemberLoginDTO dto) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public void memberInsert(MemberInsertDTO dto) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	
	public int idCheck(String mid);

	public MemberGetOneDTO getOneMember(Long mno) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	
	public void memberUpdate(MemberUpdateReqDTO dto) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	
	public void memberDelete(Long mno);
}
