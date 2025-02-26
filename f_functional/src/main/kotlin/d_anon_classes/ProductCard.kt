package d_anon_classes

import b_kotlin_serialization_2.ProductCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductCard(
    @SerialName("product_name") val productName: String,
//    @SerialName("product_description") val productDescription: String,
    @SerialName("product_price") val productPrice: Double,
    @SerialName("product_category") val productCategory: ProductCategory,
    @SerialName("product_brand") val productBrand: String,
    @SerialName("product_rating") val productRating: Double,
    @SerialName("product_image_url") val productImageUrl: String
)


