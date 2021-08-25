package org.prac.project.member.controller;

import org.prac.project.member.dto.MemberGetOneDTO;
import org.prac.project.member.dto.MemberInsertDTO;
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
	
	@PostMapping(value= "/insert")
	public ResponseEntity<String> memberInsert(@RequestBody MemberInsertDTO dto) {
		log.info("======================= starting Member Insert");
		
		if(dto != null) {
			try {
				memberService.memberInsert(dto);
				return ResponseEntity.ok(dto.getMid()+"회원님 회원가입 성공에 성공하셨습니다.");
			} catch (Exception e) {
				return null;
			}
		}
		return null;
		
	}
	
	@GetMapping(value = "/{mno}", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MemberGetOneDTO> memberGetOne(@PathVariable Long mno) {
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
	public ResponseEntity<Long> memberUpdate(@RequestBody MemberUpdateReqDTO dto, @PathVariable Long mno) {
		log.info("======================= starting memberUpdate");
		
		if(dto != null) {
			memberService.memberUpdate(dto);
			return ResponseEntity.ok(200L);
		}
		
		return null;
		
	}

}
