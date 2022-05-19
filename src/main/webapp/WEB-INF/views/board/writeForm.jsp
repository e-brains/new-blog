<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../header.jsp" %>


<div class="container">

    <form>
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" name="title" class="form-control" placeholder="Enter title" id="title">
        </div>

        <div class="form-group">
            <label for="content">Content:</label>
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>

    </form>

    <button id="btn-save" class="btn btn-primary">글쓰기 완료</button>

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



