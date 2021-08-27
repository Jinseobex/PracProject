<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../resources/css/board.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>Login Page</title>
</head>
<body>

	<h2>Login Page</h2>
	
     <table>
           <tr>
               <td id="title">아이디</td>
               <td>
                   <input type="text" id="id" maxlength="20">  
               </td>
           </tr>
                   
           <tr>
               <td id="title">비밀번호</td>
               <td>
                   <input type="password" id="password" maxlength="15">
               </td>
           </tr>
           
           <tr>
               <td>
                   <input type="button" value="로그인" id="loginBtn"/>
               </td>
           </tr>
           
           <tr>
               <td>
                   <input type="button" value="취소">
               </td>
           </tr>
       
      </table>
	
</body>

<script type="text/javascript" language="javascript">

$('#loginBtn').on("click", function() {
	login()
});

	function login() {
		
		console.log("로그인 이벤트 시작")
	    
	    const mid = $("#id").val();
		const mpassword = $("#password").val();
		
		const loginInfo = {
				"mid" : mid,
				"mpassword" : mpassword
		}
	   
	            $.ajax({
	                async : true, 
	                type : 'POST', 
	                data: JSON.stringify(loginInfo),
	                url: "/member/login",
	                dataType: "json",
	                contentType: "application/json; charset=UTF-8",
	                
	                success: function(result) {    
	                    if(result == 1) {
	                    	console.log("로그인 성공");
	                    } else if(result == 0 || result > 1) {
	                    	console.log("로그인 실패");
	                    	window.location.reload();
	                    }            
	                },
	                
	                error: function(error) {
	                	console.log("로그인 실패_error");
	                	window.location.reload
	                }        
	            });          
	    }

</script>

</html>