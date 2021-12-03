package com.example.webappexplain.points.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.SharedFlow
import java.lang.ref.WeakReference

//1. View Layer помогает разгрузить очень большой Fragment
//Знает только про interaction
//и минимально необходимые ссылки на lifecycle, binding/view и flow/livedata - на чтение
private class WebappDebugHostView(
    private val activityRef: WeakReference<FragmentActivity?>,
    private val uiInteraction: UIInteraction,
    private val debugHostSharedFlow: SharedFlow<String?>,
    lifecycleOwner: LifecycleOwner,
    button: Button
) {
    init {
        //сразу можно писать логику
        button.setOnClickListener {  }
    }
}

private class SomeFragment: Fragment() {
    private lateinit var someBtn: Button
    private lateinit var uiInteraction: UIInteraction
    private lateinit var sharedFlow: SharedFlow<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        WebappDebugHostView(
            WeakReference(activity),
            uiInteraction,
            sharedFlow,
            viewLifecycleOwner,
            someBtn
        )
    }
}

private interface UIInteraction {
    fun onBtnClicked()
}