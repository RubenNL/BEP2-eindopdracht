package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.data.SpringShoeRepository;
import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.domain.Shoe;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ShoeService {
	private final SpringShoeRepository shoeRepository;
	public ShoeService(SpringShoeRepository shoeRepository) {
		this.shoeRepository = shoeRepository;
	}
	public Card grabCard(PlayTable table) {
		Shoe shoe=shoeRepository.findByTable(table).get();
		System.out.println(shoe);
		Card card=shoe.grabCard();
		System.out.println(shoe);
		shoeRepository.save(shoe);
		return card;
	}
}
