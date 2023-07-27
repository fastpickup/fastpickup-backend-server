<!--
/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */
 -->
 <%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
		 <div class="container-fluid">
			 <div class="row">
				 <div class="col-md-12">
					 <h3>Store Page</h3>
					 <div class="card">
						 <div class="bg-light rounded h-100 p-4">
							 <form onsubmit="onSubmitForm(event)" method="get">
								 <h4 class="mb-4">가맹점 번호</h4>
								 <div class="alert alert-light sno" role="alert">${listStore.sno}</div>
								 <h4 class="mb-4">가맹점 이메일</h4>
								 <div class="alert alert-light email" role="alert">${listStore.email}</div>
								 <h4 class="mb-4">가맹점 사업자 번호</h4>
								 <div class="alert alert-light" role="alert">${listStore.storeNumber}</div>
								 <h4 class="mb-4">가맹점 주소</h4>
								 <div class="alert alert-light" role="alert">${listStore.storeAddress}</div>
							 </form>
							 <!-- Member Delete & Member Signout & Member Update & Board List Page -->
							 <form onsubmit="return false;" action="/admin/store/delete" method="post">
								 <button type="submit" class="btn btn-danger" onclick="confirmDelete(event)">가맹점
									 퇴출</button>
								 <a href="/admin/store/update/${listStore.sno}"
									 class="btn btn-primary text-white">가맹점
									 업데이트 Page</a>
								 <a href="/admin/store/list" class="btn btn-primary text-white">가맹점 리스트 Page</a>
							 </form>
							 <!-- Update Complete Message Start -->
							 <div class="modal alertModal" tabindex="-1" role="dialog">
								 <div class="modal-dialog" role="document">
									 <div class="modal-content">
										 <div class="modal-body">${message}</div>
										 <div class="modal-footer">
											 <button type="button" class="btn btn-secondary"
												 data-dismiss="modal">닫기</button>
										 </div>
									 </div>
								 </div>
							 </div>
						 </div>
						 <!-- Update Complete Message End -->
					 </div>
				 </div>
			 </div>
			 <%@ include file="../include/footer.jsp" %>
		 </div>

		 <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
		 <script>
			 function confirmDelete() {
				 if (confirm("정말로 가맹점을 삭제하시겠습니까?")) {
					 var sno = document.querySelector('div.sno').textContent;
					 var form = document.querySelector('form[action="/admin/store/delete"]');
					 form.action = '/admin/store/delete/' + sno;
					 form.submit();
				 }
			 }
			 const alertModal = new bootstrap.Modal(document.querySelector(".alertModal"))
			 let message = "${message}";
			 if (message !== "") {
				 alertModal.show();
			 }
			 setTimeout(function () {
				 alertModal.hide();
			 }, 1500);
		 </script>


 </body>

 </html>