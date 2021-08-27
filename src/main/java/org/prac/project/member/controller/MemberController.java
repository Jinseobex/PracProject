package org.prac.project.member.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.prac.project.member.dto.MemberGetOneDTO;
import org.prac.project.member.dto.MemberInsertDTO;
import org.prac.project.member.dto.MemberLoginDTO;
import org.prac.project.member.dto.MemberLoginResponseDTO;
import org.prac.project.member.dto.MemberSessionDTO;
import org.prac.project.member.dto.MemberUpdateReqDTO;
import org.prac.project.member.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/member/*")
@RestController
@RequiredArgsConstructor
@Log4j
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping(value = "/getSession", produces = MediaType.APPLICATION_JSON_VALUE)
	public MemberSessionDTO getSession(HttpSession session) {
		MemberSessionDTO res = (MemberSessionDTO) (session.getAttribute("userSession"));
		return res;
	}
	
	@PostMapping(value = "/login")
	public int memberLogin(@RequestBody MemberLoginDTO dto, HttpSession session) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		log.info("=================== login dto: "+dto);
		MemberLoginResponseDTO result = memberService.memberLogin(dto);
		log.info("=================== login isCorrect: "+result.getResultNum());
		
		if(result.getResultNum() == 1) {
			log.info("=================== login session set");

			session.setAttribute("userSession", 
					MemberSessionDTO.builder()
					.mid(dto.getMid())
					.mno(result.getMno())
					.build()
					);
			
			return 1;
		} else {
			log.info("=================== login false in controller");
			return 0;
		}
		
	}
	
	@PostMapping(value= "/insert")
	public ResponseEntity<String> memberInsert(@RequestBody MemberInsertDTO dto) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		log.info("======================= starting Member Insert");
				memberService.memberInsert(dto);
				return ResponseEntity.ok(dto.getMid()+"회원님 회원가입 성공에 성공하셨습니다.");
	}
	
    @PostMapping(value="/idCheck")
    @ResponseBody
    public int idCheck(@RequestBody String mid) throws Exception {
        
        int count = 0;
        count = memberService.idCheck(mid);
 
        return count;    
    }

	@GetMapping(value = "/{mno}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MemberGetOneDTO> memberGetOne(@PathVariable Long mno) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		log.info("======================= starting memberGetOne");
		
		if(mno == null) {
			return null;
		}
		MemberGetOneDTO result = memberService.getOneMember(mno);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping(value = "/{mno}") 
	public ResponseEntity<String> memberDelete(@PathVariable Long mno) {
		log.info("======================= starting memberDelete");
		
			if(mno != null) {
				memberService.memberDelete(mno);
				return ResponseEntity.ok("탈퇴 완료");
			}
		return null;
		
	}
	
	@PutMapping(value = "/{mno}")
	public ResponseEntity<Long> memberUpdate(@RequestBody MemberUpdateReqDTO dto, @PathVariable Long mno) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		log.info("======================= starting memberUpdate");
		
		if(dto != null) {
			memberService.memberUpdate(dto);
			return ResponseEntity.ok(200L);
		}
		
		return null;
		
	}

}
