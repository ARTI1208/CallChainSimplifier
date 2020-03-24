import grammar.LexerBaseListener
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import grammar.LexerParser

class GrammarListener: LexerBaseListener() {

    var hasErrors = false

    val calls = mutableListOf<LexerParser.CallContext>()

    override fun visitErrorNode(node: ErrorNode) {
        super.visitErrorNode(node)

        hasErrors = true;
    }

    override fun enterEveryRule(ctx: ParserRuleContext) {
        super.enterEveryRule(ctx)
    }

    override fun enterCall(ctx: LexerParser.CallContext) {
        super.enterCall(ctx)
        calls.add(ctx)
    }
}