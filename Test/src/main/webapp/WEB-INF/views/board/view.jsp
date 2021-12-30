<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function fnUpdate(f) {
			f.action = 'updateBoard.do';
			f.submit();
		}
		function fnDelete(f) {
			if (confirm('정말 삭제하시겠습니까?')) {
				f.action = 'deleteBoard.do';
				f.submit();
			}
		}
	</script>
</head>
<body>
	
	<form>
		<table border="1">
			<tr>
				<td>순번</td>
				<td>${board.idx}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${board.writer}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${board.title}">
			</td>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="50" name="content">${board.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="hidden" name="idx" value="${board.idx}">
					<input type="button" value="수정" onclick="fnUpdate(this.form)">
					<input type="button" value="목록" onclick="location.href='selectBoardList.do'">
					<input type="button" value="삭제" onclick="fnDelete(this.form)">
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>