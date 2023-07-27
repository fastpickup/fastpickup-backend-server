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
            <form action="/admin/member/create" method="post">
                <h3>Member Join Page</h3>
                <div class="bg-light rounded h-100 p-4">
                    <label for="memberName" class="form-label">Name:</label>
                    <input type="text" id="memberName" name="memberName" class="form-control" required>
                </div>
                <div class="bg-light rounded h-100 p-4">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>
                <div class="bg-light rounded h-100 p-4">
                    <label for="memberPw" class="form-label">Password:</label>
                    <input type="password" id="memberPw" name="memberPw" class="form-control" required>
                </div>
                <div class="bg-light rounded h-100 p-4">
                    <label for="memberPhone" class="form-label">Phone:</label>
                    <input type="number" id="memberPhone" name="memberPhone" class="form-control" required>
                </div>
                <div class="bg-light rounded h-100 p-4">>
                    <button type="submit" class="btn btn-primary">Join Member</button>
                    <a href="/admin/index" class="btn btn-primary text-white btnSignin">Return Sign
                        Page</a>
                </div>
            </form>
            <%@ include file="../include/footer.jsp" %>
    </body>

    </html>