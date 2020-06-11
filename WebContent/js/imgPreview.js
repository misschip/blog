/**
 * 
 */

	$("#img__preview").on("change", function(e) {
		
		var f = e.target.files[0];
		if (!f.type.match("image*")) {	// image/png image/jpg 등
			alert("이미지만 첨부할 수 있습니다.");
			$("#img__preview").val('');
			return;
		}
		
		// f.size = 1024*1024*2;	// 2메가 넘어가면 바로 return
		// 숙제
		
		var reader = new FileReader();
		
		reader.onload = function(e) {
			$("#img__wrap").attr("src", e.target.result);
			console.log(e.target.result);
		}
		reader.readAsDataURL(f);	// 비동기적으로 읽어들임
		
	});