// Generated from /home/arti1208/Coding/Kotlin/CallChainSimplifier/src/ru.art2000.callchainsimplifier.main/antlr/Lexer.g4 by ANTLR 4.8
package grammar;
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, NUMBER=8, OPERATION=9, 
		WS=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "NUMBER", "OPERATION", 
			"WS", "Digit", "Minus"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'element'", "'map{'", "'}'", "'filter{'", "'%>%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "NUMBER", "OPERATION", 
			"WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\fM\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\5\t?\n\t\3\n\3\n\3\13\6\13D\n\13\r\13\16\13E\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\2\2\16\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\2\31\2\3\2\5\7\2((,-//>@~~\5\2\13\f\16\17\"\"\3\2\62;\2L\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\3\33\3\2\2\2\5\35"+
		"\3\2\2\2\7\37\3\2\2\2\t\'\3\2\2\2\13,\3\2\2\2\r.\3\2\2\2\17\66\3\2\2\2"+
		"\21>\3\2\2\2\23@\3\2\2\2\25C\3\2\2\2\27I\3\2\2\2\31K\3\2\2\2\33\34\7*"+
		"\2\2\34\4\3\2\2\2\35\36\7+\2\2\36\6\3\2\2\2\37 \7g\2\2 !\7n\2\2!\"\7g"+
		"\2\2\"#\7o\2\2#$\7g\2\2$%\7p\2\2%&\7v\2\2&\b\3\2\2\2\'(\7o\2\2()\7c\2"+
		"\2)*\7r\2\2*+\7}\2\2+\n\3\2\2\2,-\7\177\2\2-\f\3\2\2\2./\7h\2\2/\60\7"+
		"k\2\2\60\61\7n\2\2\61\62\7v\2\2\62\63\7g\2\2\63\64\7t\2\2\64\65\7}\2\2"+
		"\65\16\3\2\2\2\66\67\7\'\2\2\678\7@\2\289\7\'\2\29\20\3\2\2\2:?\5\27\f"+
		"\2;<\5\27\f\2<=\5\21\t\2=?\3\2\2\2>:\3\2\2\2>;\3\2\2\2?\22\3\2\2\2@A\t"+
		"\2\2\2A\24\3\2\2\2BD\t\3\2\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2F"+
		"G\3\2\2\2GH\b\13\2\2H\26\3\2\2\2IJ\t\4\2\2J\30\3\2\2\2KL\7/\2\2L\32\3"+
		"\2\2\2\5\2>E\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}