let index = {
  init: function () {
    // 화실표 대신 function(){}을 사용하려면 this를 재할당해야 한다.
    // let _this = this;
    // $("#btn-save").on("click",function(){
    // _this.save();
    $("#btn-save").on("click", () => {
      // function(){}을 사용하면 this가 윈도우객체를 가리키게 된다.
      this.save();
    });

    $("#btn-delete").on("click", () => {
      // function(){}을 사용하면 this가 윈도우객체를 가리키게 된다.
      this.delete();
    });

    $("#btn-modify").on("click", () => {
      // function(){}을 사용하면 this가 윈도우객체를 가리키게 된다.
      this.modify();
    });

    $("#btn-reply-save").on("click", () => {
      // function(){}을 사용하면 this가 윈도우객체를 가리키게 된다.
      this.replySave();
    });

    
  },

  // 글쓰기 저장 수행 요청
  save: function () {
    alert("writeForm의 save함수 호출됨");
    let data = {
      title: $("#title").val(),
      content: $("#content").val()
    };

    // ajax호출시 default가 비동기 호출
    // ajax통신을 이용해 3개의 데이터를 json으로 변경해서 insert요청
    $.ajax({
      type: "POST",
      url: "/api/board/writeProc",
      data: JSON.stringify(data), // http body데이터 (javascript오브젝트 => json으로 변환)
      contentType: "application/json; charset=utf-8", // body데이터가 어떤타입인지(MIME)
      dataType: "json" // 응답은 기본적으로 문자열 => 문자열 중 json형태이면 여기서 javascript오브젝트로 변환해 줌
    })
      .done(function (resp) {
        console.log(resp);
        alert("글쓰기 저장이 완료되었습니다.");
        location.href = "/";
      })
      .fail(function (error) {
        // error도 json으로 오지만 alert에서는 javascript오브젝트로 변환해서 보여주게 된다.
        console.log(error);
        alert(JSON.stringify(error)); // json으로 다시 변환해서 보여준다.
      });
  },

  // 글 삭제 수행 요청
  delete: function () {
    alert("글삭제의 delete함수 호출됨");

    /*let id = $("#id").val();*/
    let id = $("#id").text();

    // ajax호출시 default가 비동기 호출
    // ajax통신을 이용해 3개의 데이터를 json으로 변경해서 insert요청
    $.ajax({
      type: "DELETE",
      url: "/api/board/delete/"+id,
      dataType: "json" // 응답은 기본적으로 문자열 => 문자열 중 json형태이면 여기서 javascript오브젝트로 변환해 줌
    })
        .done(function (resp) {
          console.log(resp);
          alert("글 삭제가 완료되었습니다.");
          location.href = "/";
        })
        .fail(function (error) {
          // error도 json으로 오지만 alert에서는 javascript오브젝트로 변환해서 보여주게 된다.
          console.log(error);
          alert(JSON.stringify(error)); // json으로 다시 변환해서 보여준다.
        });
  },

  // 글수정 수행 요청
  modify: function () {
    alert("modifyForm의 modify함수 호출됨");
    let id = $("#id").val();
    let data = {
      title: $("#title").val(),
      content: $("#content").val()
    };

    // ajax호출시 default가 비동기 호출
    // ajax통신을 이용해 3개의 데이터를 json으로 변경해서 insert요청
    $.ajax({
      type: "PUT",
      url: "/api/board/modifyProc/"+id,
      data: JSON.stringify(data), // http body데이터 (javascript오브젝트 => json으로 변환)
      contentType: "application/json; charset=utf-8", // body데이터가 어떤타입인지(MIME)
      dataType: "json" // 응답은 기본적으로 문자열 => 문자열 중 json형태이면 여기서 javascript오브젝트로 변환해 줌
    })
        .done(function (resp) {
          console.log(resp);
          alert("글 수정이 완료되었습니다.");
          location.href = "/";
        })
        .fail(function (error) {
          // error도 json으로 오지만 alert에서는 javascript오브젝트로 변환해서 보여주게 된다.
          console.log(error);
          alert(JSON.stringify(error)); // json으로 다시 변환해서 보여준다.
        });
  },

  // 댓글쓰기 저장 수행 요청
  replySave: function () {
    alert("detailForm의 replySave함수 호출됨");
    let boardId= $("#boardId").val();
    let data = {
      content: $("#replyContent").val()
    };

    // ajax호출시 default가 비동기 호출
    // ajax통신을 이용해 3개의 데이터를 json으로 변경해서 insert요청
    $.ajax({
      type: "POST",
      url: `/api/board/${boardId}/reply`,
      data: JSON.stringify(data), // http body데이터 (javascript오브젝트 => json으로 변환)
      contentType: "application/json; charset=utf-8", // body데이터가 어떤타입인지(MIME)
      dataType: "json" // 응답은 기본적으로 문자열 => 문자열 중 json형태이면 여기서 javascript오브젝트로 변환해 줌
    })
        .done(function (resp) {
          console.log(resp);
          alert("댓글쓰기 저장이 완료되었습니다.");
          location.href = `/board/detail/${boardId}`;
        })
        .fail(function (error) {
          // error도 json으로 오지만 alert에서는 javascript오브젝트로 변환해서 보여주게 된다.
          console.log(error);
          alert(JSON.stringify(error)); // json으로 다시 변환해서 보여준다.
        });
  }


};

index.init();
