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
        <%@ include file="include/header.jsp" %>
            <div class="col-sm-12 col-xl-12">
                <h3>Member Signin Page</h3>
                <div class="bg-light rounded h-100 p-4">
                    <form action="/admin/index/" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Email address</label>
                            <input type="email" class="form-control" id="username" name="username"
                                aria-describedby="username">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                        <!-- Remember Me checkbox -->
                        <div>
                            <input type="checkbox" name="remember-me">
                            이 컴퓨터에 쿠키를 저장하실겁니까 ?
                        </div>
                        <button type="submit" class="btn btn-primary">Sign in</button>
                    </form>

                    <a href="http://localhost:8080/oauth2/authorization/kakao" class="btn btn-primary">kakao 로그인</a>
                    <a href="/admin/member/create" class="btn btn-primary text-white">Create Member</a>
                    <a href="/admin/member/createstore" class="btn btn-primary text-white">Create Store</a>

                </div>
            </div>
            <%@ include file="include/footer.jsp" %>
                <!-- Update Complete Message Start -->
                <div class="modal alertModal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-body">${message}</div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
                <!-- Update Complete Message End -->
                <script>
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