package com.example.webappexplain.interaction

import android.widget.Button
import com.example.webappexplain.dummy.LoadDelegate
import com.example.webappexplain.dummy.SystemDelegate

//1.помогает держать Delegates независимыми друг от друга
private interface UIInteraction {
    fun onClearCacheClicked()
}

private class UIInteractionImpl(
    private val systemDelegate: SystemDelegate,
    private val loadDelegate: LoadDelegate
): UIInteraction {
    override fun onClearCacheClicked() {
        systemDelegate.clearCache()
        loadDelegate.load()
    }
}
//2.помогает скрыть delegate от view
private class ViewModel(
    uiInteractionImpl: UIInteractionImpl
): UIInteraction by uiInteractionImpl

private class SomeView(
    private val viewModel: ViewModel,
    private val button: Button
) {
    fun onCreateView() {
        button.setOnClickListener {
            viewModel.onClearCacheClicked()
        }
    }
}