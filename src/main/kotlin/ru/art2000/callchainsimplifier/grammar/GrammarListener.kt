package ru.art2000.callchainsimplifier.grammar

import ru.art2000.callchainsimplifier.grammar.LexerBaseListener
import ru.art2000.callchainsimplifier.grammar.LexerParser
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