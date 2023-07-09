package com.peonlee.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.getOrThrow
import com.peonlee.data.product.ProductRepository
import com.peonlee.domain.login.GetHomeProductUseCase
import com.peonlee.home.model.divider.DividerUiModel
import com.peonlee.home.model.product.ConditionalProductsUiModel
import com.peonlee.home.model.product.EventByStoresUiModel
import com.peonlee.home.model.product.ProductsByStoreUiModel
import com.peonlee.home.model.title.TitleUiModel
import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.product.toProductUiModel
import com.peonlee.model.type.ConditionType
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
            val recentProducts = getHomeProductUseCase(orderBy = SortType.RECENT).getOrThrow()
            // 2. 인기 상품
            val popularProducts = getHomeProductUseCase(orderBy = SortType.POPULAR).getOrThrow()
            // 3. 행사별 인기 상품
            val eventProduct = StoreType.values().map { store ->
                store to
                    getHomeProductUseCase(
                        orderBy = SortType.RECENT,
                        pageSize = 6,
                        retail = listOf(store.storeDataName),
                        promotion = PromotionType.values().map { it.promotionDataName }
                    ).getOrThrow()
            }
            _products.value = listOf(
                TitleUiModel(id = 1, title = "주목할 신상"),
                ConditionalProductsUiModel(
                    id = 2, viewType = MainHomeViewType.CONDITIONAL_PRODUCTS,
                    condition = ConditionType.NEW,
                    products = recentProducts.content.map { it.toProductUiModel() }
                ),
                TitleUiModel(id = 3, title = "꾸준한 인기상품이에요."),
                ConditionalProductsUiModel(
                    id = 4, viewType = MainHomeViewType.CONDITIONAL_PRODUCTS,
                    condition = ConditionType.POPULAR,
                    products = popularProducts.content.map { it.toProductUiModel() }
                ),
                DividerUiModel(id = 5),
                TitleUiModel(id = 6, title = "지금 행사 중!"),
                EventByStoresUiModel(
                    id = 7,
                    stores = eventProduct.map { (store, products) ->
                        ProductsByStoreUiModel(
                            stores = store,
                            products = products.content.slice(0..5).map { it.toProductUiModel() }
                        )
                    }
                )
            )
        }
    }
}
