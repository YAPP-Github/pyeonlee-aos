package com.peonlee.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.product.ProductRepository
import com.peonlee.domain.login.GetHomeProductUseCase
import com.peonlee.model.MainHomeListItem
import com.peonlee.model.type.PromotionType
import com.peonlee.model.type.SortType
import com.peonlee.model.type.StoreType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeProductUseCase: GetHomeProductUseCase,
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _products = MutableStateFlow<List<MainHomeListItem>>(emptyList())
    val products: StateFlow<List<MainHomeListItem>> = _products.asStateFlow()

    init {
        viewModelScope.launch {
            // 1. 신상품
            val recentProducts = getHomeProductUseCase(orderBy = SortType.RECENT)
            // 2. 인기 상품
            val popularProducts = getHomeProductUseCase(orderBy = SortType.POPULAR)
            // 3. 행사별 인기 상품
            val eventProduct = getHomeProductUseCase(
                orderBy = SortType.RECENT,
                retail = StoreType.values().map { it.storeDataName },
                promotion = PromotionType.values().map { it.promotionDataName }
            )
            // 4. 최근 리뷰 요청
        }
    }
}
