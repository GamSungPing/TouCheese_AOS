package com.example.domain.model.detail

data class ProductItem(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val reviewCount: Int,
    val price: Int,
    val isGroup: Boolean
)

val PRODUCT_DUMMY = listOf(
    ProductItem(
        id = 1,
        name = "Wireless Earbuds",
        description = "High-quality sound with noise-cancellation.",
        imageUrl = "https://example.com/images/earbuds.jpg",
        reviewCount = 120,
        price = 99000,
        isGroup = false
    ),
    ProductItem(
        id = 2,
        name = "Smartphone Stand",
        description = "Adjustable stand for your smartphone or tablet.",
        imageUrl = "https://example.com/images/stand.jpg",
        reviewCount = 85,
        price = 15000,
        isGroup = false
    ),
    ProductItem(
        id = 3,
        name = "Gaming Mouse",
        description = "Ergonomic design with RGB lighting.",
        imageUrl = "https://example.com/images/mouse.jpg",
        reviewCount = 240,
        price = 45000,
        isGroup = false
    ),
    ProductItem(
        id = 4,
        name = "Bluetooth Speaker",
        description = "Portable speaker with excellent bass.",
        imageUrl = "https://example.com/images/speaker.jpg",
        reviewCount = 310,
        price = 77000,
        isGroup = true
    ),
    ProductItem(
        id = 5,
        name = "Fitness Tracker",
        description = "Track your steps, calories, and heart rate.",
        imageUrl = "https://example.com/images/tracker.jpg",
        reviewCount = 190,
        price = 55000,
        isGroup = false
    ),
    ProductItem(
        id = 6,
        name = "Laptop Sleeve",
        description = "Water-resistant sleeve for 15-inch laptops.",
        imageUrl = "https://example.com/images/sleeve.jpg",
        reviewCount = 67,
        price = 30000,
        isGroup = false
    ),
    ProductItem(
        id = 7,
        name = "Reusable Water Bottle",
        description = "Eco-friendly stainless steel water bottle.",
        imageUrl = "https://example.com/images/bottle.jpg",
        reviewCount = 150,
        price = 20000,
        isGroup = true
    ),
    ProductItem(
        id = 8,
        name = "Desk Organizer",
        description = "Keep your desk tidy with this organizer.",
        imageUrl = "https://example.com/images/organizer.jpg",
        reviewCount = 80,
        price = 18000,
        isGroup = false
    ),
    ProductItem(
        id = 9,
        name = "Wireless Charger",
        description = "Fast charging for all compatible devices.",
        imageUrl = "https://example.com/images/charger.jpg",
        reviewCount = 210,
        price = 39000,
        isGroup = false
    ),
    ProductItem(
        id = 10,
        name = "Noise-Cancelling Headphones",
        description = "Over-ear headphones with immersive sound.",
        imageUrl = "https://example.com/images/headphones.jpg",
        reviewCount = 340,
        price = 120000,
        isGroup = true
    )
)
