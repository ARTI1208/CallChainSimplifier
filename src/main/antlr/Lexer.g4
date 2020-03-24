grammar Lexer;


//<digit>   ::= “0” | “1" | “2” | “3" | “4” | “5" | “6” | “7" | “8” | “9"
//<number> ::= <digit> | <digit> <number>
//<operation> ::= “+” | “-” | “*” | “>” | “<” | “=” | “&” | “|”
//<constant-expression> ::= “-” <number> | <number>
//<binary-expression> ::= “(” <expression> <operation> <expression> “)”
//<expression> ::= “element” | <constant-expression> | <binary-expression>
//<map-call> ::= “map{” <expression> “}”
//<filter-call> ::= “filter{” <expression> “}”
//<call> ::= <map-call> | <filter-call>
//<call-chain> ::= <call> | <call> “%>%” <call-chain>

fragment Digit: [0-9];

fragment Minus: '-';

//SUB: '-';

OPERATION: '+' | '-' | '*' | '>' | '<' | '=' | '&' | '|';

NUMBER: Digit | Digit NUMBER;

constantExpression: (Minus NUMBER) | NUMBER;

ELEMENT: 'element';

binaryExpression: '(' expression OPERATION expression ')';

expression: ELEMENT | binaryExpression | constantExpression ;

BRACE_OPEN: '{';
BRACE_CLOSE: '}';

MAP_FUN: 'map';
FILTER_FUN: 'filter';

mapCall: MAP_FUN BRACE_OPEN expression BRACE_CLOSE;

filterCall: FILTER_FUN BRACE_OPEN expression BRACE_CLOSE;

call: mapCall | filterCall;

CHAIN: '%>%';

callChain: call | call CHAIN callChain;

parse: callChain* EOF;

WS  :  [ \t\r\n\u000C]+ -> skip;
