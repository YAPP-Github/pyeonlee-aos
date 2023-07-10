package com.peonlee.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.getOrThrow
import com.peonlee.domain.login.GetHomeProductUseCase
import com.peonlee.home.model.divider.HomeDividerUiModel
import com.peonlee.home.model.product.ConditionalProductsUiModel
import com.peonlee.home.model.product.EventByStoresUiModel
import com.peonlee.home.model.product.ProductsByStoreUiModel
import com.peonlee.home.model.title.TitleUiModel
import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.product.toProductUiModel
import com.peonlee.model.type.PromotionType
import com.peonlee.model.type.SortType
import com.peonlee.model.type.StoreType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeProductUseCase: GetHomeProductUseCase
) : ViewModel() {
    private val _products = MutableStateFlow<List<MainHomeListItem>>(emptyList())
    val products: StateFlow<List<MainHomeListItem>> = _products.asStateFlow()

    init {
        viewModelScope.launch {
            val recentProduct = async { getConditionalProducts(2, SortType.RECENT) }
            val popularProduct = async { getConditionalProducts(4, SortType.POPULAR) }
            val eventProduct = async { getEventProducts() }
            _products.value = listOf(
                TitleUiModel(id = 1, title = "주목할 신상"),
                recentProduct.await(),
                TitleUiModel(id = 3, title = "꾸준한 인기상품이에요."),
                popularProduct.await(),
                HomeDividerUiModel(id = 5),
                TitleUiModel(id = 6, title = "지금 행사 중!"),
                eventProduct.await()
            )
        }
    }

    private suspend fun getConditionalProducts(id: Long, sortType: SortType): ConditionalProductsUiModel = withContext(Dispatchers.IO) {
        val conditionalProducts = getHomeProductUseCase(orderBy = sortType).getOrThrow().content.map {
            it.toProductUiModel()
        }
        ConditionalProductsUiModel(
            id = id, viewType = MainHomeViewType.CONDITIONAL_PRODUCTS,
            sortType = sortType,
            products = conditionalProducts
        )
    }

    private suspend fun getEventProducts(): EventByStoresUiModel = withContext(Dispatchers.IO) {
        val eventProduct =
            StoreType.values().map { store ->
                async {
                    val products = getHomeProductUseCase(
                        orderBy = SortType.RECENT,
                        pageSize = 6,
                        retail = listOf(store.storeDataName),
                        promotion = PromotionType.values().map { it.promotionDataName }
                    ).getOrThrow().content
                    ProductsByStoreUiModel(
                        stores = store,
                        products = products.subList(0, 5).map { it.toProductUiModel() }
                    )
                }
            }.awaitAll()
        EventByStoresUiModel(
            id = 7,
            stores = eventProduct
        )
    }
}
