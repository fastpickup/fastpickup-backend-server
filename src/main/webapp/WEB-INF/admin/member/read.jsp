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
                        <h3>Member Page</h3>
                        <div class="card">
                            <div class="bg-light rounded h-100 p-4">
                                <form onsubmit="onSubmitForm(event)" method="get">
                                    <h4 class="mb-4">Name</h4>
                                    <div class="alert alert-light" role="alert">${listMember.memberName}</div>
                                    <h4 class="mb-4">Email</h4>
                                    <div class="alert alert-light email" role="alert">${listMember.email}</div>
                                    <h4 class="mb-4">Phone Number</h4>
                                    <div class="alert alert-light" role="alert">${listMember.memberPhone}</div>
                                </form>
                                <!-- Member Delete & Member Signout & Member Update & Board List Page -->
                                <form onsubmit="return false;" action="/admin/member/delete" method="post">
                                    <button type="submit" class="btn btn-danger" onclick="confirmDelete(event)">Delete
                                        Account</button>
                                    <a href="/admin/member/logout" class="btn btn-primary text-white">Sign Out</a>
                                    <a href="/admin/member/update/${listMember.email}"
                                        class="btn btn-primary text-white">Member
                                        Update Page</a>
                                    <a href="/admin/member/list" class="btn btn-primary text-white">Member List Page</a>
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
                    if (confirm("정말로 탈퇴하시겠습니까?")) {
                        var email = document.querySelector('div.email').textContent;
                        var form = document.querySelector('form[action="/admin/member/delete"]');
                        form.action = '/admin/member/delete/' + encodeURIComponent(email);
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