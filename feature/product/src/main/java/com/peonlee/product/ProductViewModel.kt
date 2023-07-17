package com.peonlee.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.peonlee.data.model.request.ProductSearchRequest
import com.peonlee.data.product.ProductRepository
import com.peonlee.model.product.ProductSearchConditionUiModel
import com.peonlee.model.product.ProductUiModel
import com.peonlee.model.product.toProductUiModel
import com.peonlee.model.type.Category
import com.peonlee.model.type.EventType
import com.peonlee.model.type.PriceFilter
import com.peonlee.model.type.SortType
import com.peonlee.model.type.StoreType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    // 현재 상품 검색 조건
    private val _productSearchCondition = MutableStateFlow(ProductSearchConditionUiModel())
    val productSearchCondition: StateFlow<ProductSearchConditionUiModel> = _productSearchCondition.asStateFlow()

    val products: Flow<PagingData<ProductUiModel>>

    private var keyword: String? = ""

    init {
        products = _productSearchCondition
            .flatMapLatest {
                getProdcuts(it)
            }
            .cachedIn(viewModelScope)
    }

    private fun getProdcuts(
        productSearch: ProductSearchConditionUiModel
    ): Flow<PagingData<ProductUiModel>> {
        return try {
            productRepository.getProductsPaging(
                ProductSearchRequest(
                    keyword = keyword,
                    maxPrice = productSearch.price?.maxPrice,
                    minPrice = productSearch.price?.minPrice,
                    orderBy = productSearch.sortedBy.sortName,
                    pbOnly = productSearch.categories?.contains(Category.PB),
                    productCategoryTypeList = productSearch.categories?.filter { it != Category.PB }?.map { it.categoryType },
                    promotionRetailerList = productSearch.stores?.map { it.storeDataName },
                    promotionTypeList = productSearch.events?.map { it.eventDataName }
                )
            ).map { it.map { product -> product.toProductUiModel() } }
        } catch (exception: Exception) {
            flowOf(PagingData.empty())
        }
    }

    // 정렬 타입 변경
    fun setProductSortType(sortType: SortType) {
        _productSearchCondition.value = _productSearchCondition.value.copy(sortedBy = sortType)
    }

    // 가격 필터 선택
    fun setPriceFilter(priceFilter: PriceFilter?) {
        _productSearchCondition.value = _productSearchCondition.value.copy(price = priceFilter)
    }

    // 이벤트 선택
    fun setEventFilter(stores: List<StoreType>, events: List<EventType>) {
        _productSearchCondition.value = _productSearchCondition.value.copy(
            stores = stores.ifEmpty { null },
            events = events.ifEmpty { null }
        )
    }

    // 카테고리 선택
    fun setCategoryFilter(categories: List<Category>) {
        _productSearchCondition.value = _productSearchCondition.value.copy(
            categories = categories.ifEmpty { null }
        )
    }

    // 초기화
    fun setInitProductSearchCondition() {
        _productSearchCondition.value = ProductSearchConditionUiModel(
            keyword = _productSearchCondition.value.keyword,
            sortedBy = _productSearchCondition.value.sortedBy
        )
    }

    fun setKeyword(keyword: String?) {
        this.keyword = keyword
    }
}
