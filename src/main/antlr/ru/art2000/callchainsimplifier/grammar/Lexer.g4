grammar Lexer;

@header{
package ru.art2000.callchainsimplifier.grammar;
}

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

parse: callChain* EOF;

binaryExpression: PAREN_OPEN expression (OPERATION | MINUS) expression PAREN_CLOSE;

expression: ELEMENT | binaryExpression | MINUS? NUMBER;

mapCall: MAP_FUN BRACE_OPEN expression BRACE_CLOSE;

filterCall: FILTER_FUN BRACE_OPEN expression BRACE_CLOSE;

call: mapCall | filterCall;

callChain: call | call CHAIN callChain;

MAP_FUN: 'map';
FILTER_FUN: 'filter';

BRACE_OPEN: '{';
BRACE_CLOSE: '}';

PAREN_OPEN: '(';
PAREN_CLOSE: ')';

ELEMENT: 'element';
NUMBER: Digit | Digit NUMBER;

MINUS: '-';
OPERATION: '+' |'*' | '>' | '<' | '=' | '&' | '|';

CHAIN: '%>%';


fragment Digit: [0-9];


WS:  [ \t\r\n\u000C]+ -> skip;