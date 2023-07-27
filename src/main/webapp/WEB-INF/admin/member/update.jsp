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
            <h1>Member Update Page</h1>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <form action="/admin/member/update" method="post">
                                    <div class="bg-light rounded h-100 p-4">
                                        <label for="memberName" class="form-label">Name</label>
                                        <input type="text" name="memberName" class="form-control" id="memberName"
                                            value="${listMember.memberName}">
                                    </div>
                                    <div class="bg-light rounded h-100 p-4">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="email" name="email" class="form-control" id="email"
                                            value="${listMember.email}">
                                    </div>
                                    <div class="bg-light rounded h-100 p-4">
                                        <label for="memberPhone" class="form-label">Member Phone</label>
                                        <input type="text" name="memberPhone" class="form-control" id="memberPhone"
                                            value="${listMember.memberPhone}">
                                    </div>
                                    <div class="bg-light rounded h-100 p-4">
                                        <label for="memberPw" class="form-label">Password(Protected)</label>
                                        <input type="password" name="memberPw" class="formcontrol" id="memberPw"
                                            value="${listMember.memberPw}">
                                    </div>

                                    <div class="bg-light rounded h-100 p-4">
                                        <div class="col-sm-9 offset-sm-3">
                                            <button type="submit" class="btn btn-primary">Update</button>
                                            <a href="/admin/member/read/${listMember.email}"
                                                class="btn btn-primary text-white">Return My
                                                Page</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="../include/footer.jsp" %>

    </body>

    </html>