// Generated from /home/arti1208/Coding/Kotlin/CallChainSimplifier/src/ru.art2000.callchainsimplifier.main/antlr/Lexer.g4 by ANTLR 4.8
package ru.art2000.callchainsimplifier.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LexerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LexerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LexerParser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(LexerParser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LexerParser#binaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpression(LexerParser.BinaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LexerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LexerParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LexerParser#mapCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapCall(LexerParser.MapCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LexerParser#filterCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterCall(LexerParser.FilterCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LexerParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(LexerParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LexerParser#callChain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallChain(LexerParser.CallChainContext ctx);
	/**
	 * Visit a parse tree produced by {@link LexerParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(LexerParser.ParseContext ctx);
}