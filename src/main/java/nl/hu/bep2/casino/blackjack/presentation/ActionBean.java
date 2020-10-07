package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.application.strategies.*;
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
	private SurrenderStrategy surrenderStrategy;
	public ActionBean(HitStrategy hitStrategy,StandStrategy standStrategy,DoubleDownStrategy doubleDownStrategy,SurrenderStrategy surrenderStrategy) {
		if(this.actionBean==null) {
			this.hitStrategy=hitStrategy;
			this.standStrategy=standStrategy;
			this.doubleDownStrategy=doubleDownStrategy;
			this.surrenderStrategy=surrenderStrategy;
			actionBean=this;
		} else {
			this.hitStrategy = actionBean.hitStrategy;
			this.standStrategy = actionBean.standStrategy;
			this.doubleDownStrategy = actionBean.doubleDownStrategy;
			this.surrenderStrategy = actionBean.surrenderStrategy;
		}
	}

	public String action;
	public HandStrategie getStrategy() {
		if(action.equals("hit")) return hitStrategy;
		if(action.equals("stand")) return standStrategy;
		if(action.equals("double") || action.equals("doubledown")) return doubleDownStrategy;
		if(action.equals("surrender")) return surrenderStrategy;
		throw new UnsupportedOperationException("strategy not found");
	}
}
