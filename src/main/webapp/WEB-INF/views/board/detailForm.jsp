<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../header.jsp" %>


<div class="container">

    <form>
        <div>
            글번호 : <span id="id"><i>${board.id}</i></span>&nbsp;&nbsp;&nbsp;
            <input type="hidden" id="boardId" value="${board.id}">
            작성자 : <span><i>${board.user.username}</i></span>
        </div>
        <br>
        <div class="d-flex justify-content-between">
            <div class="form-group">
                <h5>${board.title}</h5>
            </div>
            <div>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>

                <%--수정 삭제는 작성자만 클릭 할 수 있도록 해야한다.--%>
                <c:if test="${board.user.id == principal.user.id}"> <%--서버의 PrincipalDetail을 가리키고 PrincipalDetailService에서 세션이 생성됨--%>
                    <a href="/board/${board.id}/modifyForm" class="btn btn-warning">수정</a>
                    <button type="button" id="btn-delete" class="btn btn-danger">삭제</button>
                </c:if>
            </div>
        </div>
        <hr>
        <div class="form-group">
            <div>${board.content}</div>
        </div>
    </form>

    <br>
    <br>

    <%--댓글 달기--%>
    <div class="card">
        <div class="card-header">댓글 달기</div>
        <div class="card-body "><textarea id="replyContent" class="form-control" rows="1"></textarea></div>
        <div class="card-footer">
            <button id="btn-reply-save" class="btn btn-primary">등록</button>
        </div>
    </div>
    <br>

    <%--댓글리스트--%>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply--box" class="list-group"> <%--내가 만든 id를 구분하기 위해 -- 를 사용한다.--%>
            <c:forEach var="reply" items="${board.replys}">
                <li id="reply--1" class="list-group-item d-flex justify-content-between">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
                        <button class="badge">삭제</button>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>


</div>


<script src="/js/board.js"></script>

<%@ include file="../footer.jsp" %>



