import grammar.LexerBaseVisitor
import grammar.LexerParser

class GrammarVisitor: LexerBaseVisitor<Boolean>() {
    override fun visitBinaryExpression(ctx: LexerParser.BinaryExpressionContext?): Boolean {

        return super.visitBinaryExpression(ctx)
    }
}