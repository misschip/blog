
Kakao.init('0c97a016786477488a57b08d3cd9c4ab');

function sendToMe(str) {
	Kakao.Auth.login({
		scope: 'PROFILE, TALK_MESSAGE',
		success: function() {
			Kakao.API.request({
				url:'/v2/api/talk/memo/default/send',
				data: 
			})
		}
	})
}