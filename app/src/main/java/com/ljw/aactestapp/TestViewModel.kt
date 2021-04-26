package com.ljw.aactestapp

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ljw.aactestapp.util.Event

class TestViewModel : ViewModel() {
    private val _showErrorToast = MutableLiveData<Event<String>>()
    private val _openEvent = MutableLiveData<Event<String>>()

    val sampleText: MutableLiveData<String> = MutableLiveData()
    val isVisible: MutableLiveData<Boolean> = MutableLiveData()

    val showErrorToast: LiveData<Event<String>> = _showErrorToast
    val openEvent: LiveData<Event<String>> get() = _openEvent

    init {
        isVisible.value = true
    }

    fun setToast(text: String) {
        _showErrorToast.value = Event(text)
    }

    fun onClickEvent(text: String) {
        _openEvent.value = Event(text)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ljwLog", "onCleared")
    }
}

/**
 * 하나의 이벤트 당 한 번의 처리를 하기 위해서 확장함수 선언
 */
inline fun <T> LiveData<Event<T>>.eventObserve(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { t ->
        t.getContentIfNotHandled()?.let {
            onChanged.invoke(it)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

@BindingAdapter("android:visibleIf")
fun View.setVisibleIf(value: Boolean) {
    isVisible = value
}