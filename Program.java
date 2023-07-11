

class Program {
	DeclSeq ds;
	StmtSeq ss;
	
	void parse() {
		Parser.expectedToken(Core.PROGRAM);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.LBRACE);
		Parser.scanner.nextToken();
		if (Parser.scanner.currentToken() != Core.BEGIN) {
			ds = new DeclSeq();
			ds.parse();
		}
		Parser.expectedToken(Core.BEGIN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.LBRACE);
		Parser.scanner.nextToken();
		ss = new StmtSeq();
		ss.parse();
		Parser.expectedToken(Core.RBRACE);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.RBRACE);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.EOS);
	}
	
	void print() {
		System.out.println("program {");
		if (ds != null) {
			ds.print(1);
		}
		System.out.println("begin {");
		ss.print(1);
		System.out.println("}}");
	}
	
	void execute(String dataFileName) {
		Executor.initialize(dataFileName);
		if (ds != null) {
			ds.execute();
		}
		Executor.pushFrame();
		ss.execute();
		Executor.popFrame();
		Executor.deAllocateHeap();
	}
}