const IMP = window.IMP; 
IMP.init('imp12711251');

function requestPay() {

		var totalCost = document.getElementById("orderTotalCost").value;
		var originCost = document.getElementById("originalTotalCost").value;
		var integerCost = parseInt(totalCost, 10);
		var name = document.getElementById("memName").value;
		var email = document.getElementById("memEmail").value;
		var phone = document.getElementById("memTel").value;
		var addr = document.getElementById("memAddr").value;
		var post = document.getElementById("memPost").value;
		var pickedCouponNo = document.getElementById("useCouponNo").value;
		var usePoint = document.getElementById("usePoint").value;
		var addPoint = 0;
		
		if (originCost > 50000) {
		  addPoint = Math.round((originCost * 0.05) + 2000);
		} else {
		  addPoint = Math.round(originCost * 0.05);
		  
		}
		
		if ( originCost < 15000) {
		   integerCost += 2500;
		} 
		
		if (pickedCouponNo === null || pickedCouponNo === "") {
		    pickedCouponNo = 0;
		} else {
		    pickedCouponNo = parseInt(pickedCouponNo);
		}
		
		if (usePoint === null || usePoint === "") {
		    usePoint = 0;
		} else {
		    usePoint = parseInt(usePoint);
		}
		
		if(integerCost !== 0){
		
		    IMP.request_pay({
		      pg: "html5_inicis",
		      pay_method: "card",
		      merchant_uid: "PTP20180131-0000011",   // 주문번호
		      name: "피터팬 도서 구매",
		      amount: integerCost,                         // 숫자 타입
		      buyer_email: email,
		      buyer_name: name,
		      buyer_tel: phone,
		      buyer_addr: addr,
		      buyer_postcode: "01181"
		    }, function (rsp) { // callback
		  		  location.href="buySuccess.go?couponNo="
		  		  +pickedCouponNo+"&usePoint="+usePoint+
		  		  "&payMoney="+integerCost+"&addPoint="+addPoint;
		    });
		    
   		 } else {
    		location.href="buySuccess.go"
  		 }
  		  
  }
  
  
  
  
// 모든 payBox 요소를 가져옴
var payBoxes = document.getElementsByClassName("payBox");

// payBox 요소를 클릭했을 때 실행되는 함수
function handleClick(event) {
    // 모든 payBox 요소의 테두리 속성 초기화
    for (var i = 0; i < payBoxes.length; i++) {
        payBoxes[i].style.border = "1px solid #ccc";
        payBoxes[i].style.backgroundColor = "white";
    }

    // 클릭한 요소의 테두리 속성 변경
    event.currentTarget.style.border = "1.5px solid #5055b1";
    event.currentTarget.style.backgroundColor = "#f7f7f7";

    // 클릭한 payBox 요소 안의 img 태그의 alt 속성 값 추출
    var altValue = event.currentTarget.querySelector("img").getAttribute("alt");
}

// 각 payBox 요소에 클릭 이벤트 핸들러 등록
for (var i = 0; i < payBoxes.length; i++) {
    payBoxes[i].addEventListener("click", handleClick);
}



