
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
<%@ include file="../include/header.jsp" %>
<h3>리뷰 수정</h3>
<form name="frm" method="post" >
	<div class="form_content">
		<div class="bg-light rounded h-100 p-4">
			<label for="rno" class="form-label">리뷰 번호</label>
			<input type="text" id="rno" name="rno" class="form-control" value="${reviewRead.rno}" readonly required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="ono" class="form-label">주문번호</label>
			<input type="text" id="ono" name="ono" class="form-control" value="${reviewRead.ono}"readonly required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="storeName" class="form-label">가맹점</label>
			<input type="text" id="storeName" name="storeName" class="form-control" value="${reviewRead.storeName}"readonly required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="email" class="form-label">작성자</label>
			<input type="text" id="email" name="email" class="form-control" value="${reviewRead.email}"readonly required>
		</div>
    <div class="bg-light rounded h-100 p-4">
			<label for="reviewTitle" class="form-label">리뷰 제목</label>
			<input type="text" id="reviewTitle" name="reviewTitle" class="form-control" value="${reviewRead.reviewTitle}" required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="reviewContent" class="form-label">리뷰 내용</label>
      <textarea class="form-control p-3" rows="4" placeholder="리뷰를 입력해주세요." name="reviewContent" required>${reviewRead.reviewContent}</textarea>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="productFile" class="form-label">상품 이미지</label>
			<input type="file" name="upload" multiple class="form-control uploadInput" id="productFile">
		</div>
		<div class="uploadHidden"></div>
	</div>
</form>
<div class="uploadWrap p-4">
	<ul class="uploadUL">
		<c:forEach items="${reviewRead.fileNames}" var="reviewImg" varStatus="status">
			<li data-originName="${reviewImg}">
				<a href="http://localhost/${reviewImg}" target="_blank">
					<img src="http://localhost/s_${reviewImg}"/>
				</a>
				<p>${fn:substring(product,37,fn:length(product))}</p>
				<button class="btn btn-danger" onclick="javascript:removeFile(event, '${product}')">X</button>
			</li>
		</c:forEach>
	</ul>
</div>
<div class="button_wrap p-4">
	<a href="/admin/review/read/${reviewRead.rno}" class="btn btn-outline-dark">리뷰 상세로</a>
	<button type="button" class="btn btn-primary btnDelete">리뷰 삭제</button>
	<button type="button" class="btn btn-dark btnAdd">리뷰 수정</button>
</div>

<!-- Delete Confrim Message Modal -->
<div class="modal deleteModal" tabindex="-1" role="dialog">
	<form method="post" action="/admin/review/delete/${reviewRead.rno}" class="actionForm">
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
<%@ include file="../include/footer.jsp" %>
<%-- Start Javascript --%>
<script>
  const uploadInput = document.querySelector(".uploadInput")
  const uploadUL = document.querySelector(".uploadUL")
  const uploadHidden = document.querySelector(".uploadHidden")
  const btnAdd = document.querySelector(".btnAdd")
  const deleteModal = new bootstrap.Modal(document.querySelector(".deleteModal"))
  const btnDelete = document.querySelector(".btnDelete")
  const btnDeleteModal = document.querySelector(".btnDeleteModal")

  //파일 업로드
  uploadInput.addEventListener("change", e => {

    //file 없으면 리턴
    if(!uploadInput.files || uploadInput.files.length === 0) {
      return
    }

    //form data
    const formData = new FormData()

    //formData에 파일 넣어주기
    for(let i = 0; i < uploadInput.files.length; i++) {
      formData.append("files", uploadInput.files[i])
    }
    console.dir(formData)

    //http header 타입 지정
    const header = {headers: {"Content-Type": "multipart/form-data"}}

    //파일 업로드 axios 호출
    axios.post("http://localhost:8080/api/files/upload", formData, header).then(res => {
      const result = res.data
      console.log(result)
      showProducts(result)
    })
  })

  //수정버튼 클릭
  btnAdd.addEventListener("click", e => {
    //console.log(frm.title.value);
    //li 전부 셀렉트
    const liArr = uploadUL.querySelectorAll("li")

    //li개수만큼 for문 돌려서 input hidden 추가
    let str = ""
    for(let liObj of liArr) {
      //console.log(liObj)
      const originName = liObj.getAttribute("data-originname")
      console.log(originName)
      //console.log("---------------------------------")
      str += '<input type="hidden" name="fileNames" value="'+originName+'">'
    }
    uploadHidden.innerHTML += str

    frm.submit()

  }, false)

  //삭제 modal show
  btnDelete.addEventListener("click", e => {
    deleteModal.show()
  }, false)

</script>
</body>

</html>