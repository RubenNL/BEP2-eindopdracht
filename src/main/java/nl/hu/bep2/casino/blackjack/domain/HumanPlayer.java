package nl.hu.bep2.casino.blackjack.domain;

public class HumanPlayer extends Player {
	private int bet;
	private boolean finished;
	public HumanPlayer(Table table,int bet) {
		super(table);
		this.bet=bet;
	}
	public int getBet() {
		return this.bet;
	}
	public void doubleBet() {
		this.bet*=2;
	}
	public void stand() {this.finished=true;}
	public boolean getFinished() {return this.finished;}
}
