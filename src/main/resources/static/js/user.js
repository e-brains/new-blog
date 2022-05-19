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

    $("#btn-update").on("click", () => {
      // function(){}을 사용하면 this가 윈도우객체를 가리키게 된다.
      this.update();
    });

    
  },

  // 회원가입 수행 요청
  save: function () {
    alert("joinProc의 save함수 호출됨");
    let data = {
      username: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val()
    };

    // ajax호출시 default가 비동기 호출
    // ajax통신을 이용해 3개의 데이터를 json으로 변경해서 insert요청
    $.ajax({
      type: "POST",
      url: "/api/auth/joinProc",
      data: JSON.stringify(data), // http body데이터 (javascript오브젝트 => json으로 변환)
      contentType: "application/json; charset=utf-8", // body데이터가 어떤타입인지(MIME)
      dataType: "json" // 응답은 기본적으로 문자열 => 문자열 중 json형태이면 여기서 javascript오브젝트로 변환해 줌
    })
      .done(function (resp) {
        console.log(resp);
        alert("회원가입이 완료되었습니다.");
        location.href = "/";
      })
      .fail(function (error) {
        // error도 json으로 오지만 alert에서는 javascript오브젝트로 변환해서 보여주게 된다.
        console.log(error);
        //alert(JSON.stringify(error)); // json으로 다시 변환해서 보여준다.
        alert("회원가입에 실패하였습니다.");

      });
  },

  // 회원정보 수정 요청
  update: function () {
    alert("updateForm의 update함수 호출됨");

    let data = {
      id: $("#id").val(),
      username: $("#username").val(),
      password: $("#password").val(),
      email: $("#email").val()
    };

    // ajax호출시 default가 비동기 호출
    // ajax통신을 이용해 3개의 데이터를 json으로 변경해서 insert요청
    $.ajax({
      type: "PUT",
      url: "/api/user/updateProc",
      data: JSON.stringify(data), // http body데이터 (javascript오브젝트 => json으로 변환)
      contentType: "application/json; charset=utf-8", // body데이터가 어떤타입인지(MIME)
      dataType: "json" // 응답은 기본적으로 문자열 => 문자열 중 json형태이면 여기서 javascript오브젝트로 변환해 줌
    })
        .done(function (resp) {
          console.log(resp);
          alert("회원정보 수정이 완료되었습니다.");
          location.href = "/";
        })
        .fail(function (error) {
          // error도 json으로 오지만 alert에서는 javascript오브젝트로 변환해서 보여주게 된다.
          console.log(error);
          //alert(JSON.stringify(error)); // json으로 다시 변환해서 보여준다.
          alert("회원정보 수정에 수정에 실패하였습니다.");
        });
  }


};

index.init();
