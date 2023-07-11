class Factor {
	Id id;
	int constant;
	Expr expr;
	Boolean input;
	
	void parse() {
		if (Parser.scanner.currentToken() == Core.ID) {
			id = new Id();
			id.parse();
		} else if (Parser.scanner.currentToken() == Core.CONST) {
			constant = Parser.scanner.getCONST();
			Parser.scanner.nextToken();
		} else if (Parser.scanner.currentToken() == Core.LPAREN) {
			Parser.scanner.nextToken();
			expr = new Expr();
			expr.parse();
			Parser.expectedToken(Core.RPAREN);
			Parser.scanner.nextToken();
		} else if (Parser.scanner.currentToken() == Core.INPUT) {
			Parser.scanner.nextToken();
			Parser.expectedToken(Core.LPAREN);
			Parser.scanner.nextToken();
			Parser.expectedToken(Core.RPAREN);
			Parser.scanner.nextToken();
			input = true;
		} else {
			System.out.println("ERROR: Expected ID, CONST, LPAREN, or INPUT, recieved " + Parser.scanner.currentToken());
			System.exit(0);
		}
	}
	
	void print() {
		if (id != null) {
			id.print();
		} else if (expr != null) {
			System.out.print("(");
			expr.print();
			System.out.print(")");
		} else if (input != null) {
			System.out.print("input()");
		} else {
			System.out.print(constant);
		}
	}
	
	int execute() {
		int result = constant;
		if (id != null) {
			try {
				result = (int) id.getValue();
			} catch (Exception e) {
				System.out.println("ERROR: " + id.getString() + "is null");
				System.exit(0);
			}
		} else if (expr != null) {
			result = expr.execute();
		} else if (input != null) {
			if (Executor.dataFile.currentToken() == Core.EOS) {
				System.out.println("ERROR: Data file ran out of values!");
				System.exit(0);
			}
			result = Executor.dataFile.getCONST();
			Executor.dataFile.nextToken();
		}
		return result;
	}
}