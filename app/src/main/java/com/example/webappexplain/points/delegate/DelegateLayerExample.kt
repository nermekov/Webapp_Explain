package com.example.webappexplain.points.delegate

import com.example.webappexplain.dummy.WebappRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/*
Слой Delegate - фрагмент логики презентера/vm
получает LiveData/Flow через DI для оправки событий в platform/view layer
 */

//1. помогает соблюсти Interface Segregation, это поможет тестируемости и удобству
//выносу в модуль и другим видам рефакторинга
internal interface DebugHostDelegate {
    fun onDebugHostOptionSelected(webappId: Int)

    fun changeDebugHost(host: String?, webappId: Int)

    fun getWebappDebugHost(webappId: Int): String?
}

//2. Умеренное количество зависимостей, высокая связность
internal class DebugHostDelegateImpl constructor (
    private val webappRepository: WebappRepository,

    private val coroutineScope: CoroutineScope,

    private val debugHostSharedFlow: MutableSharedFlow<String?>,
): DebugHostDelegate {

    override fun onDebugHostOptionSelected(webappId: Int) {
        coroutineScope.launch {
            debugHostSharedFlow.emit(webappRepository.getWebappDebugHost(webappId))
        }
    }

    override fun changeDebugHost(host: String?, webappId: Int) {
        webappRepository.setWebappDebugHost(
            if (host?.isNotBlank() == true)
                host
            else null,
            webappId
        )
    }

    override fun getWebappDebugHost(webappId: Int): String? =
        webappRepository.getWebappDebugHost(webappId)
}