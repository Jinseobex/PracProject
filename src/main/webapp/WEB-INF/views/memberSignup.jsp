<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../resources/css/memberSignup.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>SignUp</title>
</head>
<body>

	<!-- 왼쪽, 오른쪽 바깥여백을 auto로 주면 중앙정렬된다.  -->
	<div id="wrap">
	    <br><br>
	    <b><font size="6" color="gray">회원가입</font></b>
	    <br><br><br>
	    
	    <form>
	        <table>
	            <tr>
	                <td id="title">아이디</td>
	                <td>
	                    <input type="text" id="id" maxlength="20">
	                    <input type="button" id="idCheck" value="중복확인" >    
	                </td>
	            </tr>
	                    
	            <tr>
	                <td id="title">비밀번호</td>
	                <td>
	                    <input type="password" id="password" maxlength="15">
	                </td>
	            </tr>
	            
	            <tr>
	                <td id="title">비밀번호 확인</td>
	                <td>
	                    <input type="passwordCheck" id="passwordCheck" maxlength="15">
	                </td>
	            </tr>
	                
	            <tr>
	                <td id="title">이름</td>
	                <td>
	                    <input type="text" id="name" maxlength="40">
	                </td>
	            </tr>
	            
	            <tr>
	                <td id="title">연락처</td>
	                <td>
	                    <input type="text" id="phone" maxlength="11">
	                </td>
	            </tr>
	            
	            <tr>
	                <td id="title">주소</td>
	                <td>
	                    <input type="text" size="50" id="address"/>
	                </td>
	            </tr>
	            
	        </table>
	        <br>
	        <input type="button" value="가입" id="submitBtn" disabled/>  <input type="button" value="취소">
	    </form>
	    
</body>

<script type="text/javascript">
$(document).ready(function(){
	
	// 회원가입 버튼 클릭 이벤트 시작
    $('#submitBtn').on("click", function() {
    
    	console.log("회원가입 이벤트 시작")
    	
	     const id  = document.getElementById('id').value;
	     const password  = document.getElementById('password').value;
	     const name    = document.getElementById('name').value;
	     const phone = document.getElementById('phone').value;
	     const address = document.getElementById('address').value;
	     const phoneRegex = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/
	    		 
	     var insertData = {
	    		    "mid": id,
	    		    "mpassword": password,
	    		    "mname": name,
	    		    "mphone": phone,
	    		    "maddress": address,
	    		    "mdeleted": false
	    		}

	     $.ajax({
            async : true, 
            type : 'POST', 
            data: JSON.stringify(insertData),
            url: "/member/insert",
            contentType: "application/json; charset=UTF-8",
			
		 	success:function(data){
			 console.log("성공"+data);
			 alert("회원가입을 축하합니다!")
			 location.href = "/";
			 }, //success end
		 
			 error:function(error){
			     alert("error:"+JSON.stringify(error));
			 }
		 
	     })//ajax end
	     
	});//회원가입 버튼 클릭 이벤트 끝
	
	 $('#idCheck').on("click", function() {
		 idCheck()
	 });
	
		function idCheck() {
			
			console.log("중복확인 이벤트 시작")
		    
		    var mid = $("#id").val();
		    
		    if(mid.search(/\s/) != -1) { 
		        alert("아이디에는 공백이 들어갈 수 없습니다.");     
                $("#submitBtn").attr("disabled", "disabled");
		    } else {             
		        if(mid.trim().length != 0) {
		            $.ajax({
		                async : true, 
		                type : 'POST', 
		                data: mid,
		                url: "/member/idCheck",
		                dataType: "json",
		                contentType: "application/json; charset=UTF-8",
		                success: function(count) {    
		                    if(count > 0) {
		                        alert("해당 아이디 존재");    
		                        $("#submitBtn").attr("disabled", "disabled");
		                    } else {
		                        alert("사용가능 아이디");
		                        $("#submitBtn").removeAttr("disabled");
		                    }            
		                },
		                error: function(error) {
		                    alert("아이디를 입력해주세요.");
	                        $("#submitBtn").attr("disabled", "disabled");
		                }        
		            });
		        } else {
		            alert("아이디를 입력해주세요.");
                    $("#submitBtn").attr("disabled", "disabled");
		        }        
		    }
		}
	
	
});//jQuery end
</script>

</html>