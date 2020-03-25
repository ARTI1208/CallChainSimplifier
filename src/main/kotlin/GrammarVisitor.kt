import grammar.LexerBaseVisitor
import grammar.LexerParser
import org.antlr.v4.runtime.tree.TerminalNode
import java.util.*

class GrammarVisitor: LexerBaseVisitor<Pair<String, String>>() {

    var inMap = false

    val stack = Stack<Expression>()

    override fun visitMapCall(ctx: LexerParser.MapCallContext?): Pair<String, String> {
        inMap = true

        return super.visitMapCall(ctx)
    }

    override fun visitFilterCall(ctx: LexerParser.FilterCallContext?): Pair<String, String> {
        inMap = false

        return super.visitFilterCall(ctx)
    }

    override fun visitBinaryExpression(ctx: LexerParser.BinaryExpressionContext): Pair<String, String> {

        val pair = super.visitBinaryExpression(ctx)

        ctx.OPERATION()

        return pair
    }

    override fun visitConstantExpression(ctx: LexerParser.ConstantExpressionContext): Pair<String, String> {
        stack.push(ConstantExpression(ctx.text.toInt()))
        return super.visitConstantExpression(ctx)
    }

    override fun visitTerminal(node: TerminalNode): Pair<String, String> {
        if (node.text == "element") {
            stack.push(ElementExpression(Element.map))
        }

        return super.visitTerminal(node)
    }


}