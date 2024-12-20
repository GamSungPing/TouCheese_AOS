package com.example.presentation.studio.navigation.parcelable

import android.os.Parcel
import android.os.Parcelable
import androidx.navigation.NavHostController
import com.example.domain.model.ProductOption
import com.example.presentation.studio.navigation.parcelable.ReservationParcelable.Companion.RESERVATION_PARCELABLE
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler

@Parcelize
@TypeParceler<ProductOption, ProductOptionParceler>
data class ReservationParcelable(
    val studio: ProductInfoParcelable,
    val selectedOption: List<ProductOption>,
    val productName: String,
    val productImgPath: String,
    val addPeopleCount: Int?,
    val payment: String,
    val date: String,
    val time: String,
) : Parcelable {

    companion object {
        const val RESERVATION_PARCELABLE = "reservationParcelable"
    }
}

object ProductOptionParceler : Parceler<ProductOption> {
    override fun create(parcel: Parcel): ProductOption {
        val name = parcel.readString().orEmpty()
        val price = parcel.readInt()
        return ProductOption(name, price)
    }

    override fun ProductOption.write(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(price)
    }
}

fun NavHostController.setReservationParcelable(reservation: ReservationParcelable) {
    this.currentBackStackEntry?.savedStateHandle?.apply {
        set(RESERVATION_PARCELABLE, reservation)
    }
}

fun NavHostController.getReservationParcelable(): ReservationParcelable {
    return this.previousBackStackEntry?.savedStateHandle?.get<ReservationParcelable>(
        RESERVATION_PARCELABLE
    ) ?: ReservationParcelable(
        ProductInfoParcelable(0,"", "", "", 0, ""),
        emptyList(),
        productName = "",
        productImgPath = "",
        addPeopleCount = null,
        payment = "",
        date = "",
        time = ""
    )
}