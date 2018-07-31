package enumchapter;
//Unicode 16
//枚举类型  121  实际当中参考 纸牌 花色
enum Suit{
	CLUBS("CLUBS"),
	DIAMONS("DIAMONDS"),
	HEARTS("HEARTS"), 
	SPADES("SPADES");
	String name;
	Suit(String name){
		this.name = name;
		
	}
	public String toString(){
		return name;
	}
};

class Position{
	
}

class Set<Position>{
	Set(){
		
	}
	public void SetPos(){

	}
}

class ChessRules{
	public static Set<Position> reachable(ChessPiece type, Position current){
		if (type == ChessPiece.PAWN)
			return pawnReachable(current);
		return null;
	}
	public static Set<Position> pawnReachable(Position current){
		return null;
	}
	public static Set<Position> queenReachable(Position current) {
		return null;
	}
	public static Set<Position> rookReachable(Position current) {
		return null;
	}
}

enum ChessPiece{
	PAWN{
		Set<Position> reachable(Position current){
			return ChessRules.pawnReachable(current);
		}
	},
	ROOK{
		Set<Position> reachable(Position current){
			return ChessRules.rookReachable(current);
		}
	},
	QUEEN{
		Set<Position> reachable(Position current){
			return ChessRules.queenReachable(current);
		}
	};
	abstract Set<Position> reachable(Position current);
}

public class EnumDemo {
	public void testSuit(){
		Suit s;
		s = Suit.CLUBS;
		System.out.print(s.name);
		if (s == Suit.CLUBS){
			Suit.values();
		}
	}
	public static void main(String[] args){
		EnumDemo ed = new EnumDemo();
		if (ed instanceof EnumDemo )
		ed.testSuit();
		System.out.println("=============遍历开始==================");
		// 枚举遍历
		for (Suit suit: Suit.values()){
			System.out.println(suit.name); 
		}
	}

}
