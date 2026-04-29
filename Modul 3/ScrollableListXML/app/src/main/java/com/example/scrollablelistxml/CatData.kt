package com.example.scrollablelistxml

data class CatBreed(
    val id: Int,
    val name: String,
    val origin: String,
    val description: String,
    val imageResId: Int,
    val wikiUrl: String
)

object CatDataSource {
    val dummyCats = listOf(
        CatBreed(1, "Persian", "Iran", "Kucing berbulu panjang dengan wajah bulat dan hidung pesek yang menggemaskan.", R.drawable.cat1, "https://id.wikipedia.org/wiki/Kucing_persia"),
        CatBreed(2, "Maine Coon", "Amerika", "Salah satu ras kucing domestik terbesar yang pandai berburu.", R.drawable.cat2, "https://id.wikipedia.org/wiki/Maine_Coon"),
        CatBreed(3, "Sphynx", "Kanada", "Kucing unik tanpa bulu yang hangat, sangat penyayang dan aktif.", R.drawable.cat3, "https://id.wikipedia.org/wiki/Kucing_sfinks"),
        CatBreed(4, "Siamese", "Thailand", "Kucing elegan bermata biru dengan pola warna bulu yang khas.", R.drawable.cat4, "https://id.wikipedia.org/wiki/Kucing_siam"),
        CatBreed(5, "Scottish Fold", "Skotlandia", "Dikenal karena telinganya yang melipat ke depan, terlihat seperti burung hantu.", R.drawable.cat5, "https://id.wikipedia.org/wiki/Scottish_Fold")
    )
}