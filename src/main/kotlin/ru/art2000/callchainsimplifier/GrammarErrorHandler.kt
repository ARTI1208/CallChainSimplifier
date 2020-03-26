package ru.art2000.callchainsimplifier

import org.antlr.v4.runtime.*

class GrammarErrorHandler : BailErrorStrategy() {
    override fun reportMatch(recognizer: Parser?) {

    }

    override fun reportMissingToken(recognizer: Parser?) {

    }

    override fun reportNoViableAlternative(recognizer: Parser?, e: NoViableAltException?) {

    }

    override fun reportFailedPredicate(recognizer: Parser?, e: FailedPredicateException?) {

    }

    override fun reportUnwantedToken(recognizer: Parser?) {

    }

    override fun reportError(recognizer: Parser?, e: RecognitionException?) {

    }

    override fun reportInputMismatch(recognizer: Parser?, e: InputMismatchException?) {

    }
}