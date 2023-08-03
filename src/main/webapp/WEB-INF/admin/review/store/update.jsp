
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="kr">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FastPickup</title>
</head>

<body>
<%@ include file="../../include/header.jsp" %>
<h3>리뷰 답글 수정</h3>
<form name="frm" method="post" action ="/admin/review/store/update/${reviewRead.rno}">
	<div class="form_content">
		<div class="bg-light rounded h-100 p-4">
			<label for="rno" class="form-label">리뷰 번호</label>
			<input type="text" id="rno" name="rno" class="form-control" value="${reviewRead.rno}" readonly >
		</div>
        <div class="bg-light rounded h-100 p-4">
			<label for="ono" class="form-label">답글 번호</label>
			<input type="text" id="gno" name="gno" class="form-control" value="${reviewRead.gno}"readonly >
		</div>
			<input type="hidden" id="ono" name="ono" class="form-control" value="${reviewRead.ono}"readonly >
		<div class="bg-light rounded h-100 p-4">
			<label for="storeName" class="form-label">가맹점</label>
			<input type="text" id="storeName" name="storeName" class="form-control" value="${reviewRead.storeName}"readonly >
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="email" class="form-label">작성자</label>
			<input type="text" id="email" name="email" class="form-control" value="${reviewRead.email}"readonly >
		</div>
    <div class="bg-light rounded h-100 p-4">
			<label for="reviewTitle" class="form-label">답글 제목</label>
			<input type="text" id="reviewTitle" name="reviewTitle" class="form-control" value="${reviewRead.reviewTitle}" required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="reviewContent" class="form-label">답글 내용</label>
      <textarea class="form-control p-3" rows="4" placeholder="리뷰를 입력해주세요." name="reviewContent" required>${reviewRead.reviewContent}</textarea>
		</div>

		<!-- <div class="uploadHidden"></div> -->
	</div>
</form>

<div class="button_wrap p-4">
	<a href="/admin/review/read/${reviewRead.gno}" class="btn btn-outline-dark">리뷰 상세로</a>
	<button type="button" class="btn btn-primary btnDelete">답글 삭제</button>
	<button type="button" class="btn btn-dark btnAdd">답글 수정</button>
</div>

<!-- Delete Confrim Message Modal -->
<div class="modal deleteModal" tabindex="-1" role="dialog">
	<form method="post" action="/admin/review/store/delete/${reviewRead.rno}" class="actionForm">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">정말 삭제 하시겠습니까?</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary btnDeleteModal" data-bs-dismiss="modal">삭제</button>
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</form>
</div>
<!-- Delete Confrim Message Modal -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/js/file.js"></script>
<%@ include file="../../include/footer.jsp" %>
<%-- Start Javascript --%>
<script>

  const btnAdd = document.querySelector(".btnAdd")
  const deleteModal = new bootstrap.Modal(document.querySelector(".deleteModal"))
  const btnDelete = document.querySelector(".btnDelete")
  const btnDeleteModal = document.querySelector(".btnDeleteModal")


  //수정버튼 클릭
  btnAdd.addEventListener("click", e => {


    frm.submit()

  }, false)

  //삭제 modal show
  btnDelete.addEventListener("click", e => {
    deleteModal.show()
  }, false)

</script>
</body>

</html>