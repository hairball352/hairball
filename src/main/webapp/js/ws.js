/**
 * 로그인한 사용자용 웹소켓 연결처리
 */

const ws = new WebSocket(`ws://${location.host}/hairball/QuestionWebSocket`); // 서버측 endpoint 연결
console.log(location.host);

ws.addEventListener('open', (e) => {
    console.log('open: ', e);
    if (ws.readyState === WebSocket.OPEN) {
        console.log('웹소켓 연결 상태: 연결됨');
    } else {
        console.log('웹소켓 연결 상태: 연결 실패');
    }
});

ws.addEventListener('message', (e) => {
    let data = JSON.parse(e.data);  // 서버에서 받은 메시지를 파싱합니다
    let messageType = data.messageType;  // 메시지 형태를 설정합니다

    console.log('message: ', data);
    
    switch (messageType) {
        case 'NEW_ANSWER': 
            const wrapper = document.querySelector("#notification");
			const img = document.createElement("img");
			img.src = "/hairball/images/procedure/8.gif";
			img.classList.add("custom-image");  // CSS 클래스 추가
            img.onclick = () => {
                alert(data.message);
                img.remove();
            };
            wrapper.append(img);
            break;
    }
});

ws.addEventListener('error', (e) => {
    console.log('error: ', e);
});

ws.addEventListener('close', (e) => {
    console.log('close: ', e);
});