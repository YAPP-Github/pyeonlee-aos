package com.peonlee.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.handle
import com.peonlee.data.model.Content
import com.peonlee.data.model.home.HomeReviewResponse
import com.peonlee.domain.login.GetHomeProductUseCase
import com.peonlee.home.model.divider.HomeDividerUiModel
import com.peonlee.home.model.product.ConditionalProductsUiModel
import com.peonlee.home.model.product.EventByStoresUiModel
import com.peonlee.home.model.product.ProductsByStoreUiModel
import com.peonlee.home.model.review.toUiModel
import com.peonlee.home.model.title.TitleUiModel
import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.product.toProductUiModel
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
    private val getHomeProductUseCase: GetHomeProductUseCase
) : ViewModel() {
    private val _products = MutableStateFlow<List<MainHomeListItem>>(emptyList())
    val products: StateFlow<List<MainHomeListItem>> = _products.asStateFlow()

    init {
        viewModelScope.launch {
            getHomeProductUseCase().handle(
                onSuccess = { allInfo ->
                    _products.value = getConditionalProductsItem(1, SortType.RECENT, allInfo.newProductList) +
                        getConditionalProductsItem(3, SortType.POPULAR, allInfo.popularProductList) +
                        getEventProductsItem(allInfo.promotionProductMap) +
                        getRecentComment(allInfo.recentProductCommentList)
                }
            )
        }
    }

    private fun getConditionalProductsItem(
        id: Long,
        sortType: SortType,
        products: List<Content>
    ): List<MainHomeListItem> {
        return listOf(
            TitleUiModel(id = id, title = "주목할 신상"),
            ConditionalProductsUiModel(
                id = id + 1,
                viewType = MainHomeViewType.CONDITIONAL_PRODUCTS,
                sortType = sortType,
                products = products.map { it.toProductUiModel() }
            ),
        )
    }

    private fun getEventProductsItem(stores: Map<String, List<Content>>): List<MainHomeListItem> {
        val eventByStores = StoreType.values().map {
            val products = stores[it.storeDataName]?.map { it.toProductUiModel() } ?: emptyList()
            ProductsByStoreUiModel(
                stores = it,
                products = if (products.size > 6) products.subList(0, 6) else products
            )
        }
        return listOf(
            HomeDividerUiModel(id = 5),
            TitleUiModel(id = 6, title = "지금 행사 중!"),
            EventByStoresUiModel(
                id = 7,
                stores = eventByStores
            )
        )
    }

    private fun getRecentComment(comment: List<HomeReviewResponse>): List<MainHomeListItem> {
        return listOf(
            HomeDividerUiModel(id = 8),
            TitleUiModel(id = 9, title = "최근 리뷰")
        ) + comment.map { it.toUiModel() }
    }
}
