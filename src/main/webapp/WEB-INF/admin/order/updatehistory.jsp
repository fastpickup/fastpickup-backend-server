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
            <h1>주문 상태 변경 Page</h1>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <form id="order-form" action="/admin/order/updatehistory" method="post">
                                    <div class="bg-light rounded h-100 p-4">
                                        <label for="ono" class="form-label">주문 번호</label>
                                        <input type="text" name="ono" class="form-control" id="sno"
                                            value="${listOrder.ono}">
                                    </div>
                                    <div class="bg-light rounded h-100 p-4">
                                        <label for="orderHistory" class="form-label">주문 이력 번호</label>
                                        <input type="number" name="orderHistory" class="form-control" id="orderHistory"
                                            value="${listOrder.orderHistory}">
                                    </div>

                                    <input type="hidden" id="status" name="orderStatus">

                                    <button type="button" class="btn btn-primary" onclick="submitForm('반려')">상품
                                        반려</button>
                                    <button type="button" class="btn btn-primary" onclick="submitForm('주문접수')">상품 접수
                                        확인</button>
                                    <button type="button" class="btn btn-primary" onclick="submitForm('주문준비완료')">상품 준비
                                        완료</button>

                                    <div class="bg-light rounded h-100 p-4">
                                        <div class="col-sm-9 offset-sm-3">
                                            <a href="/admin/order/read/${listOrder.ono}"
                                                class="btn btn-primary text-white">주문 상세 정보 돌아가기</a>
                                            <a href="/admin/order/list" class="btn btn-primary text-white">Order List
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
                <script>
                    function submitForm(value) {
                        document.getElementById('status').value = value;
                        document.getElementById('order-form').submit();
                    }
                </script>
    </body>

    </html>