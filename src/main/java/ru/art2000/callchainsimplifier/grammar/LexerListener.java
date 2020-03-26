// Generated from /home/arti1208/Coding/Kotlin/CallChainSimplifier/src/ru.art2000.callchainsimplifier.main/antlr/Lexer.g4 by ANTLR 4.8
package ru.art2000.callchainsimplifier.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LexerParser}.
 */
public interface LexerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LexerParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(LexerParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LexerParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(LexerParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LexerParser#binaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(LexerParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LexerParser#binaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(LexerParser.BinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LexerParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LexerParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LexerParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LexerParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LexerParser#mapCall}.
	 * @param ctx the parse tree
	 */
	void enterMapCall(LexerParser.MapCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LexerParser#mapCall}.
	 * @param ctx the parse tree
	 */
	void exitMapCall(LexerParser.MapCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LexerParser#filterCall}.
	 * @param ctx the parse tree
	 */
	void enterFilterCall(LexerParser.FilterCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LexerParser#filterCall}.
	 * @param ctx the parse tree
	 */
	void exitFilterCall(LexerParser.FilterCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LexerParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(LexerParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LexerParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(LexerParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LexerParser#callChain}.
	 * @param ctx the parse tree
	 */
	void enterCallChain(LexerParser.CallChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link LexerParser#callChain}.
	 * @param ctx the parse tree
	 */
	void exitCallChain(LexerParser.CallChainContext ctx);
	/**
	 * Enter a parse tree produced by {@link LexerParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(LexerParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link LexerParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(LexerParser.ParseContext ctx);
}