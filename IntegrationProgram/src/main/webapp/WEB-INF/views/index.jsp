<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
	
	// 페이지 로드
	$(document).ready(function(){
		fnInit();
		fnFindAllBoard();
		fnFindBoard();
	});
	
	function fnInit(){
		$('#query').val('');
	}  
	
	function fnFindAllBoard(){
		$.ajax({
			url: '/integration1/search/findAllBoard',
			type: 'get',
			dataType: 'json',
			success: function(map){
				$('#board_list').empty();
				alert(map.message);
				if (map.status == 200) {
					let result = '';
					$.each(map.list, function(i, searchBoard){
						result += '<tr><td>' + searchBoard.title + '</td><td>' + searchBoard.content + '</td><td>' + searchBoard.regDate + '</td></tr>';
					});
					$('#board_list').html(result);
				}
			},
			error: function(){
				
			}
		});
	}  
	
	function fnFindBoard(){
		$('#search_btn').click(function(){
			
			$.ajax({
				url: '/integration1/search/findBoard',
				type: 'get',
				data: 'column='+$('#column').val() + '&query='+$('#query').val() ,
				dataType: 'json',
				success: function(map){
					$('#board_list').empty();
					alert(map.message);
					if (map.status == 200) {
						let result = '';
						$.each(map.list, function(i, searchBoard){
							result +='<tr><td>' + searchBoard.title + '</td><td>' + searchBoard.content + '</td><td>' + searchBoard.regDate + '</td></tr>';
						});
						$('#board_list').html(result);
					}
				},
				error: function(){
					
				}
			});
		});
	} 
	
</script>
</head>
<body>

	<div>
		<select name="column" id="column">
			<option value="TITLE">제목</option>
			<option value="CONTENT">내용</option>
			<option value="REGDATE">작성일</option>
		</select>
		<input type="text" name="query" id="query">
		<input type="button" value="검색" id="search_btn">
		<input type="button" value="초기화" onclick="fnInit()">
	</div>
	
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td>제목</td>			
				<td>내용</td>			
				<td>작성일</td>	
				
			</tr>
		</thead>
		<c:if test="${empty list}">
			<tr>
				<td colspan="3">검색 결과 없음</td>
		</c:if>
		<c:if test="${not empty list}">
			<tbody id="board_list">
			
			</tbody>
		</c:if>
	</table>

</body>
</html>