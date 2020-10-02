package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = true)
public class CardsConverter implements AttributeConverter<List<Card>, String> {
		@Override
		public String convertToDatabaseColumn(List<Card> cards) {
			String response="";
			List<String> stringied=new ArrayList<>();
			for(Card card:cards) stringied.add(card.getFace().name()+"-"+card.getRank().name());
			return String.join(",",stringied);
		}

		@Override
		public List<Card> convertToEntityAttribute(String dbData) {
			List<Card> cards=new ArrayList<>();
			if(dbData.length()<2) return cards;
			for(String string:dbData.split(",")) {
				String[] parts=string.split("-");
				cards.add(new Card(Faces.valueOf(parts[0]),Rank.valueOf(parts[1])));
			}
			return cards;
		}

}
