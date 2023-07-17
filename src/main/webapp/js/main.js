/* 대문 이미지 슬라이드 */
document.addEventListener("DOMContentLoaded", function () {
  const slide = new Swiper("#my-swiper", {
    slidesPerView: "auto", // 한 슬라이드에 보여줄 갯수
    // spaceBetween: 6, // 슬라이드 사이 여백
    loop: false, // 슬라이드 반복 여부
    loopAdditionalSlides: 1, // 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
    pagination: false, // pager 여부
    autoplay: {
      // 자동 슬라이드 설정 , 비 활성화 시 false
      delay: 3000, // 시간 설정
      disableOnInteraction: false, // false로 설정하면 스와이프 후 자동 재생이 비활성화 되지 않음
    },
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
  });
});

/* 둘러보기 이미지 슬라이드 */

$(document).ready(function () {
  // 문서 로드가 완료되었을 때 실행되는 함수

  let imgs = $(".gallery");
  let img_count = imgs.children().length;
  let img_position = 0; // img_position을 0으로 초기화하여 첫 이미지가 보이도록 함

  $(".prev_btn").click(function (e) {
    back();
  });

  $(".next_btn").click(function (e) {
    if (img_position < img_count - 5) {
      next();
    } else {
      e.preventDefault();
    }
  });

  function back() {
    if (img_position > 0) {
      img_position--; // img_position을 감소
      imgs.css("transform", `translateX(${-img_position * 290}px)`); // translateX를 사용하여 이미지 이동
    }
  }

  function next() {
    if (img_position < img_count - 5) {
      img_position++; // img_position을 증가
      imgs.css("transform", `translateX(${-img_position * 290}px)`); // translateX를 사용하여 이미지 이동
    }
  }
  
	
  });
  
  
/*
 알림기능 - 대원
 */
  
const ws = new WebSocket(`ws://${location.host}/hairball/QuestionWebSocket`); 
// WebSocket 객체를 생성하고, 클라이언트에서 서버로 연결
// 서버측 endpoint 연결
console.log(location.host);

ws.addEventListener('open', (e) => { // 연결이 열릴 때마다 'open' 이벤트가 발생
	console.log('open: ', e);
});

ws.addEventListener('message', (e) => { // 메시지를 수신할 때마다 'message' 이벤트가 발생
	console.log('message: ', e);
	
	const {messageType, message} = JSON.parse(e.data); // 수신한 메시지를 JSON 형식으로 파싱
	
	switch(messageType) {
		case 'NEW_ANSWER' : // 새로운 게시글 댓글인 경우
			const wrapper = document.querySelector("#notification");
			const i = document.createElement("i");
			i.classList.add("fa-solid", "fa-bell", "bell");
			i.onclick = () => {
				alert(message);
				i.remove();
			};
			wrapper.append(i);
			break;
	}
  
});

ws.addEventListener('error', (e) => {
	console.log('error: ', e);
});

ws.addEventListener('close', (e) => {
	console.log('close: ', e);
});
