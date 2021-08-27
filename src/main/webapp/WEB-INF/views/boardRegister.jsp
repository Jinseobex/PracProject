<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../resources/css/boardRegister.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../resources/css/semantic.min.css">
<script src="../../resources/css/semantic.min.js"></script>
<title>Main Board</title>
</head>

<body>

	 <div class="ui middle aligned center aligned grid">
        <div class="column">
            <h2 class="ui teal image header">
                게시글 작성하기
            </h2>
            <form class="ui large form">
                <div class="ui stacked segment">
                    <div class="field">
                        <input type="text" id="b_title" placeholder="게시글 제목" autocomplete="off" autofocus="autofocus">
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <textarea style="resize: vertical;" id="b_content" placeholder="게시글 내용" rows="8"></textarea>
                        </div>
                    </div>
                    <div class="ui fluid large teal submit button" id="write_bbs">게시글 작성하기</div>
                </div>

                <div class="ui error message"></div>

            </form>

            <a href="/coco/main"><button class="ui fluid large teal submit button">뒤로가기</button></a>
        </div>
    </div>
	
</body>

<script type="text/javascript" language="javascript">

$(document).ready(function(){

	$(function(){
		boardInsert(1, 10, "", "")
	});
	
		function boardInsert(page, size, type, keyword) {
		
		var btitle = page
		var bcontent = size
		var bdeleted = false
		var mno = mno

		$.ajax({
			url: "/board/list?page="+page+"&size="+size+"&type="+type+"&keyword="+keyword,
			method: "GET",
			contentType: "application/json; charset=utf-8",
			dataType: "JSON",
			
			error:function(error, status, msg){
				alert("상태코드 " + status + "에러메시지" + msg);
			},
			
            success : function(data){
            	
 

            }	
		
		})
	} // end pagingList
	
}); // end script

</script>
</html>