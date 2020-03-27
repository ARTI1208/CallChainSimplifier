// Generated from ru/art2000/callchainsimplifier/grammar/Lexer.g4 by ANTLR 4.8

    package ru.art2000.callchainsimplifier.grammar;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LexerParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, OPERATION=3, NUMBER=4, ELEMENT=5, BRACE_OPEN=6, BRACE_CLOSE=7, 
		MAP_FUN=8, FILTER_FUN=9, CHAIN=10, WS=11, Minus=12;
	public static final int
		RULE_constantExpression = 0, RULE_binaryExpression = 1, RULE_expression = 2, 
		RULE_mapCall = 3, RULE_filterCall = 4, RULE_call = 5, RULE_callChain = 6, 
		RULE_parse = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"constantExpression", "binaryExpression", "expression", "mapCall", "filterCall", 
			"call", "callChain", "parse"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", null, null, "'element'", "'{'", "'}'", "'map'", "'filter'", 
			"'%>%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "OPERATION", "NUMBER", "ELEMENT", "BRACE_OPEN", "BRACE_CLOSE", 
			"MAP_FUN", "FILTER_FUN", "CHAIN", "WS", "Minus"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Lexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LexerParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ConstantExpressionContext extends ParserRuleContext {
		public TerminalNode Minus() { return getToken(LexerParser.Minus, 0); }
		public TerminalNode NUMBER() { return getToken(LexerParser.NUMBER, 0); }
		public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).enterConstantExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).exitConstantExpression(this);
		}
	}

	public final ConstantExpressionContext constantExpression() throws RecognitionException {
		ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_constantExpression);
		try {
			setState(19);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Minus:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(16);
				match(Minus);
				setState(17);
				match(NUMBER);
				}
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(18);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryExpressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OPERATION() { return getToken(LexerParser.OPERATION, 0); }
		public BinaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).enterBinaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).exitBinaryExpression(this);
		}
	}

	public final BinaryExpressionContext binaryExpression() throws RecognitionException {
		BinaryExpressionContext _localctx = new BinaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_binaryExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(T__0);
			setState(22);
			expression();
			setState(23);
			match(OPERATION);
			setState(24);
			expression();
			setState(25);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode ELEMENT() { return getToken(LexerParser.ELEMENT, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		try {
			setState(30);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ELEMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				match(ELEMENT);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				binaryExpression();
				}
				break;
			case NUMBER:
			case Minus:
				enterOuterAlt(_localctx, 3);
				{
				setState(29);
				constantExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapCallContext extends ParserRuleContext {
		public TerminalNode MAP_FUN() { return getToken(LexerParser.MAP_FUN, 0); }
		public TerminalNode BRACE_OPEN() { return getToken(LexerParser.BRACE_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode BRACE_CLOSE() { return getToken(LexerParser.BRACE_CLOSE, 0); }
		public MapCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).enterMapCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).exitMapCall(this);
		}
	}

	public final MapCallContext mapCall() throws RecognitionException {
		MapCallContext _localctx = new MapCallContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_mapCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(MAP_FUN);
			setState(33);
			match(BRACE_OPEN);
			setState(34);
			expression();
			setState(35);
			match(BRACE_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterCallContext extends ParserRuleContext {
		public TerminalNode FILTER_FUN() { return getToken(LexerParser.FILTER_FUN, 0); }
		public TerminalNode BRACE_OPEN() { return getToken(LexerParser.BRACE_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode BRACE_CLOSE() { return getToken(LexerParser.BRACE_CLOSE, 0); }
		public FilterCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).enterFilterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).exitFilterCall(this);
		}
	}

	public final FilterCallContext filterCall() throws RecognitionException {
		FilterCallContext _localctx = new FilterCallContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_filterCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(FILTER_FUN);
			setState(38);
			match(BRACE_OPEN);
			setState(39);
			expression();
			setState(40);
			match(BRACE_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallContext extends ParserRuleContext {
		public MapCallContext mapCall() {
			return getRuleContext(MapCallContext.class,0);
		}
		public FilterCallContext filterCall() {
			return getRuleContext(FilterCallContext.class,0);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).exitCall(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_call);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MAP_FUN:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				mapCall();
				}
				break;
			case FILTER_FUN:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				filterCall();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallChainContext extends ParserRuleContext {
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public TerminalNode CHAIN() { return getToken(LexerParser.CHAIN, 0); }
		public CallChainContext callChain() {
			return getRuleContext(CallChainContext.class,0);
		}
		public CallChainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callChain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).enterCallChain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).exitCallChain(this);
		}
	}

	public final CallChainContext callChain() throws RecognitionException {
		CallChainContext _localctx = new CallChainContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_callChain);
		try {
			setState(51);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				call();
				setState(48);
				match(CHAIN);
				setState(49);
				callChain();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParseContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(LexerParser.EOF, 0); }
		public List<CallChainContext> callChain() {
			return getRuleContexts(CallChainContext.class);
		}
		public CallChainContext callChain(int i) {
			return getRuleContext(CallChainContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LexerListener ) ((LexerListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MAP_FUN || _la==FILTER_FUN) {
				{
				{
				setState(53);
				callChain();
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16@\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\5\2\26"+
		"\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\5\4!\n\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\5\7/\n\7\3\b\3\b\3\b\3\b\3\b\5\b\66\n\b\3"+
		"\t\7\t9\n\t\f\t\16\t<\13\t\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2="+
		"\2\25\3\2\2\2\4\27\3\2\2\2\6 \3\2\2\2\b\"\3\2\2\2\n\'\3\2\2\2\f.\3\2\2"+
		"\2\16\65\3\2\2\2\20:\3\2\2\2\22\23\7\16\2\2\23\26\7\6\2\2\24\26\7\6\2"+
		"\2\25\22\3\2\2\2\25\24\3\2\2\2\26\3\3\2\2\2\27\30\7\3\2\2\30\31\5\6\4"+
		"\2\31\32\7\5\2\2\32\33\5\6\4\2\33\34\7\4\2\2\34\5\3\2\2\2\35!\7\7\2\2"+
		"\36!\5\4\3\2\37!\5\2\2\2 \35\3\2\2\2 \36\3\2\2\2 \37\3\2\2\2!\7\3\2\2"+
		"\2\"#\7\n\2\2#$\7\b\2\2$%\5\6\4\2%&\7\t\2\2&\t\3\2\2\2\'(\7\13\2\2()\7"+
		"\b\2\2)*\5\6\4\2*+\7\t\2\2+\13\3\2\2\2,/\5\b\5\2-/\5\n\6\2.,\3\2\2\2."+
		"-\3\2\2\2/\r\3\2\2\2\60\66\5\f\7\2\61\62\5\f\7\2\62\63\7\f\2\2\63\64\5"+
		"\16\b\2\64\66\3\2\2\2\65\60\3\2\2\2\65\61\3\2\2\2\66\17\3\2\2\2\679\5"+
		"\16\b\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2\2\2<:\3\2\2\2"+
		"=>\7\2\2\3>\21\3\2\2\2\7\25 .\65:";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}