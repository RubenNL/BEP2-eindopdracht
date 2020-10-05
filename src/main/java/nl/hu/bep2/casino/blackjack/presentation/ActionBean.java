package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.application.strategies.DoubleDownStrategy;
import nl.hu.bep2.casino.blackjack.application.strategies.HandStrategie;
import nl.hu.bep2.casino.blackjack.application.strategies.HitStrategy;
import nl.hu.bep2.casino.blackjack.application.strategies.StandStrategy;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class ActionBean {
	//SINGLETON
	private static ActionBean actionBean;
	private HitStrategy hitStrategy;
	private StandStrategy standStrategy;
	private DoubleDownStrategy doubleDownStrategy;
	public ActionBean(HitStrategy hitStrategy,StandStrategy standStrategy,DoubleDownStrategy doubleDownStrategy) {
		if(this.actionBean==null) {
			this.hitStrategy=hitStrategy;
			this.standStrategy=standStrategy;
			this.doubleDownStrategy=doubleDownStrategy;
			actionBean=this;
			System.out.println("ActionBeanInit1");
		} else {
			this.hitStrategy = actionBean.hitStrategy;
			this.standStrategy = actionBean.standStrategy;
			this.doubleDownStrategy = actionBean.doubleDownStrategy;
			System.out.println("ActionBeanInit2");
		}
	}

	public String action;
	public HandStrategie getStrategy() {
		System.out.println(action);
		System.out.println(hitStrategy);
		if(action.equals("hit")) return hitStrategy;
		if(action.equals("stand")) return standStrategy;
		if(action.equals("double") || action.equals("doubledown")) return doubleDownStrategy;
		throw new UnsupportedOperationException("strategy not found");
	}
}
