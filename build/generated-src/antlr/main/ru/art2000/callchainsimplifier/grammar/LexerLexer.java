// Generated from ru/art2000/callchainsimplifier/grammar/Lexer.g4 by ANTLR 4.8

package ru.art2000.callchainsimplifier.grammar;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LexerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MAP_FUN=1, FILTER_FUN=2, BRACE_OPEN=3, BRACE_CLOSE=4, PAREN_OPEN=5, PAREN_CLOSE=6, 
		ELEMENT=7, NUMBER=8, MINUS=9, OPERATION=10, CHAIN=11, WS=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"MAP_FUN", "FILTER_FUN", "BRACE_OPEN", "BRACE_CLOSE", "PAREN_OPEN", "PAREN_CLOSE", 
			"ELEMENT", "NUMBER", "MINUS", "OPERATION", "CHAIN", "Digit", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'map'", "'filter'", "'{'", "'}'", "'('", "')'", "'element'", null, 
			"'-'", null, "'%>%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "MAP_FUN", "FILTER_FUN", "BRACE_OPEN", "BRACE_CLOSE", "PAREN_OPEN", 
			"PAREN_CLOSE", "ELEMENT", "NUMBER", "MINUS", "OPERATION", "CHAIN", "WS"
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


	public LexerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Lexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16O\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\5\t=\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16"+
		"\6\16J\n\16\r\16\16\16K\3\16\3\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\2\33\16\3\2\5\6\2((,->@~~\3\2\62;\5\2\13\f\16"+
		"\17\"\"\2O\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5!\3\2\2\2\7(\3\2\2\2\t*\3\2\2\2\13"+
		",\3\2\2\2\r.\3\2\2\2\17\60\3\2\2\2\21<\3\2\2\2\23>\3\2\2\2\25@\3\2\2\2"+
		"\27B\3\2\2\2\31F\3\2\2\2\33I\3\2\2\2\35\36\7o\2\2\36\37\7c\2\2\37 \7r"+
		"\2\2 \4\3\2\2\2!\"\7h\2\2\"#\7k\2\2#$\7n\2\2$%\7v\2\2%&\7g\2\2&\'\7t\2"+
		"\2\'\6\3\2\2\2()\7}\2\2)\b\3\2\2\2*+\7\177\2\2+\n\3\2\2\2,-\7*\2\2-\f"+
		"\3\2\2\2./\7+\2\2/\16\3\2\2\2\60\61\7g\2\2\61\62\7n\2\2\62\63\7g\2\2\63"+
		"\64\7o\2\2\64\65\7g\2\2\65\66\7p\2\2\66\67\7v\2\2\67\20\3\2\2\28=\5\31"+
		"\r\29:\5\31\r\2:;\5\21\t\2;=\3\2\2\2<8\3\2\2\2<9\3\2\2\2=\22\3\2\2\2>"+
		"?\7/\2\2?\24\3\2\2\2@A\t\2\2\2A\26\3\2\2\2BC\7\'\2\2CD\7@\2\2DE\7\'\2"+
		"\2E\30\3\2\2\2FG\t\3\2\2G\32\3\2\2\2HJ\t\4\2\2IH\3\2\2\2JK\3\2\2\2KI\3"+
		"\2\2\2KL\3\2\2\2LM\3\2\2\2MN\b\16\2\2N\34\3\2\2\2\5\2<K\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}