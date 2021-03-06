function getCardHTML(suite,rank) {
    const cards=[['🂡','🂢','🂣','🂤','🂥','🂦','🂧','🂨','🂩','🂪','🂫','🂭','🂮'],
        ['🂱','🂲','🂳','🂴','🂵','🂶','🂷','🂸','🂹','🂺','🂻','🂽','🂾'],
        ['🃁','🃂','🃃','🃄','🃅','🃆','🃇','🃈','🃉','🃊','🃋','🃍','🃎'],
        ['🃑','🃒','🃓','🃔','🃕','🃖','🃗','🃘','🃙','🃚','🃛','🃝','🃞']]
    const suites={
        'SPADES': 0,
        '♠': 0,
        '♤':0,
        'HEARTS': 1,
        '♥': 1,
        '♡':1,
        'DIAMONDS': 2,
        '♦': 2,
        '♢':2,
        'CLUBS': 3,
        '♣': 3,
        '♧':3
    }
    const ranks= {
        'A': 0,
        'ACE':0,
        '2': 1,
        'TWO':1,
        '3': 2,
        'THREE':2,
        '4': 3,
        'FOUR':3,
        '5': 4,
        'FIVE':4,
        '6': 5,
        'SIX':5,
        '7': 6,
        'SEVEN':6,
        '8': 7,
        'EIGHT':7,
        '9': 8,
        'NINE':8,
        '10': 9,
        'TEN':9,
        'J': 10,
        'JACK':10,
        'Q': 11,
        'QUEEN':11,
        'K': 12,
        'KING':12
    }
    if(typeof suite=='string') suite=suites[suite.toUpperCase()];
    if(typeof rank=='string') rank=ranks[rank.toUpperCase()];
    return '<span class="card suite'+suite+'">'+cards[suite][rank]+'</span>';
}