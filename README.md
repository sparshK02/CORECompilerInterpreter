
# CORE Language Interpreter

## Overview
CORE programming language Interpreter, consisting of a Scanner, Parser, and Executor

The interpreter consists of the following components:
- **Scanner:** Inputs Core program, produces stream of *tokens*.
- **Parser:** Consumes stream of tokens, produces the *abstract parse tree* (PT).
- **Executor:** Given PT (and input data), executes the program.
- **Full Interpreter**: `Scanner` -> `Parser` -> `Executor`

The Scanner & Parser simulates an FSA (finite state automaton).
The Parser and Executor are written using a recursive descent (syntax-directed) approach.

## Details 

### Grammar for CORE
The context-free grammar for CORE, defined in BNF (Backus–Naur Form):

```
<prog> ::= program { <decl-seq> begin { <stmt-seq> } } | program { begin { <stmt-seq> } }
<decl-seq> ::= <decl> | <decl><decl-seq>
<stmt-seq> ::= <stmt> | <stmt><stmt-seq>
<decl> ::= <decl-int> | <decl-ref>
<decl-int> ::= int <id-list> ;
<decl-ref> ::= ref <id-list> ;
<id-list> ::= id | id , <id-list>
<stmt> ::= <assign> | <if> | <loop> | <out> | <decl>
<assign> ::= id = <expr> ; | id = new instance; | id = share id ;
<out> ::= output ( <expr> ) ;
<if> ::= if <cond> then { <stmt-seq> } | if <cond> then { <stmt-seq> } else { <stmt-seq> }
<loop> ::= while <cond> { <stmt-seq> }
<cond> ::= <cmpr> | ! ( <cond> ) | <cmpr> or <cond>
<cmpr> ::= <expr> == <expr> | <expr> < <expr> | <expr> <= <expr>
<expr> ::= <term> | <term> + <expr> | <term> – <expr>
<term> ::= <factor> | <factor> * <term>
<factor> ::= id | const | ( <expr> ) | input ( 
```

All of these grammars have their own files, enabling the recursive descent parsing to come into play.

### Code Samples
Some samples of the CORE language:

```
program {
	int x;
	begin {
		if 1 == 1 then {
			x = 1;
		} else {
			x = 2;
		}
		output(x);
	}
}
```

```
program {
	int x, y, z;
	begin {
		x = 5;
		y = 10;
		z = 100;
		while !(x==0) {
			x = x-1;
			while !(y==100) {
				y = y+1;
				while 0 <= z {
					z = z-1;
				}
			}
			output(x);
		}
		output(x);
	}
}
```

## Bugs
There are no known bugs in the program at this point.

## Disclaimer
All code in this project is the author's own work, other than ideas and structural details taken from lectures discussed in CSE 3341. Code from this project is not to be submitted for a class assignment by anyone other than the author.
