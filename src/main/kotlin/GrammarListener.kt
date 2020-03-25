import grammar.LexerBaseListener
import grammar.LexerParser
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode

class GrammarListener : LexerBaseListener() {

    var hasErrors = false

    val calls = mutableListOf<LexerParser.CallContext>()

    override fun visitErrorNode(node: ErrorNode) {
        super.visitErrorNode(node)

        hasErrors = true
    }

    override fun enterCall(ctx: LexerParser.CallContext) {
        super.enterCall(ctx)
        calls.add(ctx)
    }
}