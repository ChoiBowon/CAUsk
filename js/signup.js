function init() {
	document.querySelector("#join").addEventListener("click", function(evt) { // signup-submit 버튼이 눌러지면 활성화
	evt.preventDefault();
  let id = $("#id").value;
  let password = $("#password").value;
  let password2 = $("#password2").value;
  let store = $("#store").value;
  let phone = $("#phone").value;
  let postCode = $("#postCode").value;
  let roadAddress = document.querySelector("#roadAddress").value;
console.log(id + password + store + phone + roadAddress);
 var geocoder = new daum.maps.services.Geocoder();

 var callback = function(result, status) {
    if (status === daum.maps.services.Status.OK) {
        console.log(result);
    }
 };
geocoder.addressSearch(roadAddress, callback);
});
}
/*
    if(_comment.length == 0) {
        alert("덧글 내용이 없습니다.");
    } else { // commentajax.php로 덧글 내용 전송
        $.ajax({
            type: "POST", // POST형식으로 폼 전송
            url: "commentajax.php", // 목적지
            timeout: 10000,
            data: ({comment: _comment, mid: _mid}),
            cache: false,
            dataType: "text",
            error: function(xhr, textStatus, errorThrown) { // 전송 실패
                alert("전송에 실패했습니다.");
            }
        }); 
    }       
    return false;
});*/


document.addEventListener("DOMContentLoaded", function() {
	console.log("hello world!");
	init();
});
