function addPlayer(player) {
    let node=$('#player').contents("td").clone()
    node.attr('playerid',player.id)
    $('#players').append(node);
    $('#playerNames').append('<td>'+player.name+'</td>');
    $('#bets').append("<td>"+player.bet+'</td>')
    player.hands.forEach(hand=>{
        addHand(player.id,hand)
    })
}
function addHand(player,hand) {
    let html = '<tr><td handId="'+hand.id+'"></td></tr>';
    $('.handTd[playerid='+player+']')[0].innerHTML+=html;
    hand.cards.forEach(card => {
        addCard(player,hand.id,card);
    })
}
function parseData(data) {
    data.players.forEach(addPlayer);
    $('.handTd[playerid=0]').html(data.dealer.hand.map(card=>getCardHTML(card[0],card[1])).join(''));
    $('#shoe').text(data.shoe);
}
function addCard(playerId,hand,card) {
    let element=$('.handTd[playerid='+playerId+']');
    if(element.find('td').length==0) card=hand;
    else element=element.find('[handId='+hand+']')
    element.append(getCardHTML(card[0], card[1]));
}