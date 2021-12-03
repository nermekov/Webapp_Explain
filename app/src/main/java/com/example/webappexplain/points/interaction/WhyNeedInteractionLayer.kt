package com.example.webappexplain.points.interaction

import android.widget.Button
import com.example.webappexplain.dummy.CacheManager
import com.example.webappexplain.dummy.LoadDelegate
import com.example.webappexplain.dummy.SystemDelegate

//1. Без InteractionLayer: если использовать delegates в delegates
private class SystemDelegateImpl(
    private val loadDelegate: LoadDelegate,
    private val cacheManager: CacheManager
): SystemDelegate {
    override fun clearCache() {
        loadDelegate.load()
        cacheManager.clearCache()
    }
}
/*
В данном примере работоспособность одного Delegate зависит от работоспособности другого
С течением времени зависимости будут накапливаться и будет та же суть что и в файле с кучей
взаимосвязанного функционала
 */

//2.  Без InteractionLayer: Есть не лучший вариант, когда view знает про delegate и вызывает их по одному
private class ViewModel1 (
    val loadDelegate: LoadDelegate,
    val systemDelegate: SystemDelegate
)

private class SomeView1(
    private val viewModel1: ViewModel1,
    private val button: Button
) {
    fun onCreateView() {
        button.setOnClickListener {
            viewModel1.loadDelegate.load()
            viewModel1.systemDelegate.clearCache()
        }
    }
}
/*
Теперь во view есть логика, которая гарантированно в классе с андроид импортами(Виджеты Андроида)
И сочетание вызовов Delegate трудно протестировать
 */