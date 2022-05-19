<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../header.jsp" %>


<div class="container">

    <form>
        <input type="hidden" id="id" value="${board.id}">

        <div class="form-group">
            <label for="title">Title:</label>
            <input id="title" value="${board.title}" type="text" class="form-control" >
        </div>

        <div class="form-group">
            <label for="content">Content:</label>
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>

    </form>

    <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <button id="btn-modify" class="btn btn-primary">글수정 완료</button>

</div>

<script>
    // $('#summernote').summernote({
    $('.summernote').summernote({   // id를 content로 줬기 때문에 class로 찾기위해 class에 summernote를 추가 .summernote로 찾는다.
        //placeholder: '내용 입력',
        tabsize: 2,
        height: 300
    });
</script>

<script src="/js/board.js"></script>

<%@ include file="../footer.jsp" %>



