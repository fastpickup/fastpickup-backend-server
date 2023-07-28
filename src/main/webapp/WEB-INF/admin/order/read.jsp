<!--
/*
 * Date   : 2023.07.28
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */
 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <h3>Order Read Page</h3>
                        <div class="card">
                            <div class="bg-light rounded h-100 p-4">
                                <form>
                                    <h4 class="mb-4">주문 번호</h4>
                                    <div class="alert alert-light" role="alert">${listOrder.ono}</div>
                                    <h4 class="mb-4">주문 개수</h4>
                                    <div class="alert alert-light email" role="alert">${listOrder.orderCount}</div>
                                    <h4 class="mb-4">가맹점 이메일</h4>
                                    <div class="alert alert-light" role="alert">${listOrder.email}</div>
                                    <h4 class="mb-4">가맹점 이름</h4>
                                    <div class="alert alert-light" role="alert">${listOrder.storeName}</div>
                                    <h4 class="mb-4">가맹점 주소</h4>
                                    <div class="alert alert-light" role="alert">${listOrder.storeAddress}</div>
                                    <h4 class="mb-4">가맹점 번호</h4>
                                    <div class="alert alert-light" role="alert">${listOrder.sno}</div>
                                    <h4 class="mb-4">상품 번호</h4>
                                    <div class="alert alert-light" role="alert">${listOrder.pno}</div>
                                    <h4 class="mb-4">상품 가격</h4>
                                    <div class="alert alert-light" role="alert">${listOrder.productPrice}</div>
                                    <h4 class="mb-4">주문 이력 처리 번호</h4>
                                    <div class="alert alert-light" role="alert">${listOrder.orderHistory}</div>
                                    <h4 class="mb-4">주문 상태</h4>
                                    <div class="alert alert-light" role="alert">${listOrder.orderStatus}</div>
                                    <h4 class="mb-4">상품 사진</h4>
                                    <div>
                                        <c:forEach items="${listOrder.fileName}" var="order" varStatus="status">
                                           
                                                <img src="http://192.168.0.64/${order}" width="500"/>
                                      
                                        </c:forEach>
                                    </div>
                            </div>
                            </form>
                            <a href="/admin/order/updatehistory/${listOrder.ono}" class="btn btn-primary text-white">주문
                                상태 업데이트 Page</a>
                            <a href="/admin/order/list" class="btn btn-primary text-white">Order List Page</a>
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