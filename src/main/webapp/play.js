function handleResponse(response) {
	return new Promise(async (resolve,reject)=>{
		const contentType = response.headers.get("content-type");
		if (contentType && contentType.indexOf("application/json") !== -1) {
			if(response.ok) resolve(await response.json());
			else reject(await response.json());
		} else if(response.ok) resolve(response.text());
		else reject(response.status);
	}).then(response=>{
		$('#response').text(JSON.stringify(response,null,2));
		return response;
	}).catch(response=>{
		$('#response').text(JSON.stringify(response,null,2));
		return Promise.reject(response);
	})
}
function getJWT() {
	return $('#jwt').val();
}
function sendFetch(first,second) {
	if(!second.headers) second.headers={};
	second.headers.Authorization='Bearer ' + getJWT();
	if(navigator.onLine) return fetch('/'+first,second);
	alert("apparaat is offline! maak verbinding met het internet en probeer opnieuw.");
	return Promise.reject("offline");
}
function sendPost(path,data) {
	return sendFetch(path,{
		method:'POST',
		headers: {'Content-Type': 'application/json'},
		body:JSON.stringify(data)
	}).then(handleResponse);
}
function sendGet(path) {
	return sendFetch(path,{
		method:'GET'
	}).then(handleResponse);
}
function sendDelete(path) {
	return sendFetch(path,{
		method:'DELETE'
	}).then(handleResponse)
}

function getPlayerHtml(player) {
	return player.state+player.cards.map(card=>getCardHTML(card.face,card.rank)).join('')+'('+player.possibleValues.join('/')+')';
}
$('#show').on('click',()=>{
	sendGet('blackjack/'+$('#tableid').val()).then(data=>{
		$('#playerCards').html(getPlayerHtml(data.player));
		$('#dealerCards').html(getPlayerHtml(data.dealer));
		$('#betShow').text(data.bet);
		$('#shoe').text("kaarten gepakt:"+data.shoe.cardsTaken+'\nKaarten over:'+data.shoe.availableCards+'\ndecks:'+data.shoe.decks);
	})
})
$('#hit').on('click',()=> {
	sendPost('blackjack/'+$('#tableid').val()+'/action',{action:'hit'}).then(()=>$('#show').click());
})
$('#double').on('click',()=> {
	sendPost('blackjack/'+$('#tableid').val()+'/action',{action:'double'}).then(()=>$('#show').click());
})
$('#stand').on('click',()=> {
	sendPost('blackjack/'+$('#tableid').val()+'/action',{action:'stand'}).then(()=>$('#show').click());
})
$("#dealer").on('click',()=>{
	sendPost('blackjack/'+$('#tableid').val()+'/dealer').then(()=>$('#show').click());
})
$('#sendBet').on("click",()=>{
	sendPost('blackjack/'+$('#tableid').val()+'/bet',{bet:$('#bet').val()}).then(()=>$('#show').click());
})
$("#login").on('click',()=> {
	fetch("login", {
		method: "POST",
		headers: {'Content-Type': 'application/json'},
		body: JSON.stringify({username: "admin", password: "admin"})
	})
		.then(response => {
			$('#jwt').val(response.headers.get("Authorization").split(' ')[1])
		})
})
$("#newTable").on('click',()=>{
	sendPost("blackjack").then(response=>{
		$('#tableid').val(response.tableId);
	})
})