<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="../../resources/css/board.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../../resources/css/semantic.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="../../resources/css/semantic.min.js"></script>
<!-- bootstrap.min css -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Main Board</title>
</head>

<body>

  <div class="ui middle aligned center aligned grid">
        <div class="column">
            <h2 class="ui teal image header">
                게시판 페이지
            </h2>
            <div class="ui large form">
                <div class="ui stacked segment">
                    <a href="boardRegister"><button class="ui fluid large teal submit button">게시글 작성하기</button></a>
                    <table class="ui celled table" id="boardTable">
                        <thead>
                            <tr>
                                <th id="bno">#</th>
                                <th id="btitle">제목</th>
                                <th id="mid">등록자</th>
                                <th id="bmoddate">등록일</th>
                            </tr>
                        </thead>
                        <tbody id="list">
                        </tbody>
                    </table>
                </div>

                <div class="ui error message"></div>

            </div>
        </div>
    </div>

<!--     <div class="ui modal" id='view_modal'>
        <i class="close">x</i>
        <div class="header" id="detail_btitle">
            
        </div>
        <div class="content">
            <div class="description">
            	<p style = "text-align: right" id = "detail_breview"></p>
            	<div id = 'detail_bcontent'></div>
            </div>
        </div>
        <div class="actions">
            <div class="ui black deny button">
                닫기
            </div>
        </div> -->
        
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="detail_btitle">Modal title</h5>
	        <h6 id="detail_breview"></h6>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <div id='detail_bcontent'>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	        
    <!-- Pagination -->
	<section class="pagingSection">
		<div class="pagingBar" id="pagingBar">
		</div>
	</section>

</body>

<script type="text/javascript" language="javascript">
 
    $(document).ready(function(){

    	$(function(){
    		pagingList(1, 10, "", "")
    	});
    	
    		function pagingList(page, size, type, keyword) {
    		
    		var page = page
    		var size = size
    		var type = type
    		var keyword = keyword
    		
    		$.ajax({
    			url: "/board/list?page="+page+"&size="+size+"&type="+type+"&keyword="+keyword,
    			method: "GET",
    			contentType: "application/json; charset=utf-8",
    			dataType: "JSON",
    			
    			error:function(error, status, msg){
    				alert("상태코드 " + status + "에러메시지" + msg);
    			},
    			
                success : function(data){
                	
                	paging(data);
                	
                    const lists = [];
                    const boardLists = data.boardList
                    console.log(boardLists)
                    console.log(JSON.stringify(data.boardList))
                    
                    $("#list").empty();

                    $.each(boardLists, function(i) {

                    	lists.push("<tr class='table'>");
	                  	lists.push("<td class='bno'>" + boardLists[i].bno + "</td>");
                    	lists.push("<td class='selectBtn' data-toggle='modal' data-target='#exampleModal' data-bno=" + boardLists[i].bno + ">" + boardLists[i].btitle + "</td>");
                    	lists.push("<td>" + boardLists[i].mid + "</td>");
                    	lists.push("<td>" + boardLists[i].bmoddate + "</td>");
                    	lists.push("</tr>"); 
                    	
                    });
                    
                    $("#list").append(lists);

                }	
    		
    		})
    	} // end pagingList
    	
    	$(document).on("click", ".selectBtn", function(){
            
        	console.log("getOne 이벤트 시작")
        	
        	const bno = $(this).attr("data-bno");
        	
        	console.log("selected bno: "+bno);
        	
			$.ajax({
				url: "/board/"+bno,
				method: "GET",
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function(data) {
					console.log("data in suimodal", data);
/* 					$('#view_modal').modal('show');  */
					
                  	$("#detail_btitle").text(data['btitle']);
                    $("#detail_breview").text(data['bmoddate']);
                    $("#detail_bcontent").text(data['bcontent']); 

				}, error: function() {
					console.log("error")
				}
			}) // ajax end
    	}); // getOne end
    	
    	function paging(data) {
			var pageResult = data.pageResult
			var pageList = pageResult.pageNumList
			var currentPage = pageResult.page
			console.log("pageResult", pageResult)
			console.log("pageList", pageList)
			console.log("page", currentPage)
			
			var str = '<ul class="pagination justify-content-center">' 
			+ '<li class="page-item ' + ((pageResult.prev === false) ? "disabled" : "") + '">'
			+ '<a class="page-link" id="prev" tabindex="-1">Prev</a>'
			
			$("#pagingBar").empty();
			
			$.each(pageList,  function(i) {
				
				str += '</li>' 
					+ '<li class="page-item ' + ((currentPage === pageList[i]) ? "active" : "") 
					+ '"><a class="page-link" data-page=' + pageList[i] + '>' + pageList[i] + '</a></li>'
				})
				
				str += '<li class="page-item ' + ((pageResult.next === false) ? "disabled" : "") + '">' 
				+ '<a class="page-link" id="next" tabindex="-1">Next</a>' + '</ul>'
			
			$("#pagingBar").append(str);
				
		}; // end paging
		
		$(document).on("click", ".page-link", function(){
	
			console.log("paging btn clicked")
			var currentPage = $(this).attr("data-page");
			console.log("currentPage"+currentPage);
			
    		var page = currentPage
    		var size = 10
    		var type = ""
    		var keyword = ""
    		var id = $(this).attr("id");
    		
    		console.log(id);
			
			if(currentPage != undefined || null) {
				pagingList(page, size, type, keyword);
			} else if(currentPage === undefined || null && id === "next") {
				page += 10
				console.log("after next page: "+page)
				pagingList(page, size, type, keyword);
			}
			
		});

    }); // end script
 
</script>

</html>