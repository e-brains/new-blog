<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="header.jsp" %>


<div class="container">

    <c:forEach var="board" items="${boards.content}"> <%--Page타입으로 받으면 content에 board객체가 들어가 있다--%>
        <div class="card m-2">
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="/board/detail/${board.id}" class="btn btn-primary">상세보기</a>
            </div>
        </div>
    </c:forEach>

    <ul class="pagination justify-content-center">
        <%--boards는 page타입이므로 내용을 보면 number가 현재 페이지를 나타낸다.--%>
        <c:choose>
            <c:when test="${boards.first}">  <%--boards의 first 항목이 true이면 첫번째 페이지이므로 Previous disabled한다.--%>
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise> <%--첫번째 페이지가 아니면--%>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${boards.last}"> <%--boards의 last 항목이 true이면 마지막 페이지이므로 Next disabled한다.--%>
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>

        <%--<li class="page-item"><a class="page-link" href="#">1</a></li>--%>

    </ul>


</div>

<%@ include file="footer.jsp" %>



