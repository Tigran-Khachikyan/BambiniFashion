package com.example.bambinifashion.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bambinifashion.domain.Request
import com.example.bambinifashion.domain.models.Content
import com.example.bambinifashion.domain.models.Promotion
import com.example.bambinifashion.domain.repositories.ContentRepository
import com.example.bambinifashion.domain.repositories.PromotionRepository
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val promotionRepository by inject<PromotionRepository>()
    private val contentRepository by inject<ContentRepository>()

    private val _content = MutableLiveData<List<Content>?>()
    val content: LiveData<List<Content>?> = _content

    private val _promotion = MutableLiveData<Promotion>()
    val promotion: LiveData<Promotion> = _promotion


    init {
        loadPromotions()
        loadContent()
    }

    private fun loadPromotions() {
        viewModelScope.launch {
            when (val result = promotionRepository.getPromotions()) {
                is Request.Success -> {
                    withContext(Dispatchers.Main) {
                        promotionRepository.savePromotions(result.data)
                        launchTimer(result.data)
                    }
                }
                else -> { // Handle error if necessary}
                    val savedPromotions = promotionRepository.getSavedPromotions()
                    if (!savedPromotions.isNullOrEmpty()) {
                        launchTimer(savedPromotions)
                    }
                }
            }
        }
    }

    private fun loadContent() {
        viewModelScope.launch {
            when (val result = contentRepository.getContent()) {
                is Request.Success -> {
                    withContext(Dispatchers.Main) {
                        _content.value = result.data
                        contentRepository.saveContent(result.data)
                    }
                }
                else -> { // Handle error if necessary}
                    _content.value = contentRepository.getSavedContent()
                }
            }
        }
    }


    private suspend fun launchTimer(promotions: List<Promotion>) {
        val loopPromotions = arrayListOf<Promotion>()
        promotions.forEach {
            if (it.periodicity > 1) {
                loopPromotions.add(it.copy(periodicity = it.periodicity - 1))
            }
            _promotion.value = it
            delay(it.duration)
        }
        if (loopPromotions.isNotEmpty()) {
            launchTimer(loopPromotions)
        }
    }
}