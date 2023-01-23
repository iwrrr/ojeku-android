package com.hwaryun.core.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.hwaryun.core.R
import com.hwaryun.core.extensions.findIdByLazy

class TransportCardView(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    private val componentView: View by findIdByLazy(R.id.root_transport_card)
    private val imageView: ShapeableImageView by findIdByLazy(R.id.iv_transport_card)
    private val titleView: MaterialTextView by findIdByLazy(R.id.tv_transport_card)

    private var _image: Int = -1
    private var _title: String = ""
    private var _isTransportSelected: Boolean = false
    private var _type: Type = Type.BIKE

    var image: Int
        get() = _image
        set(value) {
            setImageView(value)
        }

    var title: String
        get() = _title
        set(value) {
            setText(value)
        }

    var isTransportSelected: Boolean
        get() = _isTransportSelected
        set(value) {
            setBackgroundRoot(value)
        }

    var type: Type
        get() = _type
        set(value) {
            setComponentType(value)
        }

    init {
        inflate(context, R.layout.component_transport_card, this)
        context.obtainStyledAttributes(
            attributeSet, R.styleable.TransportCardView, 0, 0
        ).apply {
            val image = getResourceId(R.styleable.TransportCardView_image, _image)
            val title = getString(R.styleable.TransportCardView_title).orEmpty()
            val isSelected =
                getBoolean(R.styleable.TransportCardView_isSelected, _isTransportSelected)
            val typeIndex = getInt(R.styleable.TransportCardView_transportType, _type.ordinal)
            val type = Type.values()[typeIndex]

            setImageView(image)
            setText(title)
            setBackgroundRoot(isSelected)
            setComponentType(type)
        }.recycle()
    }

    private fun setImageView(image: Int) {
        _image = image
        if (image != -1) {
            imageView.setImageResource(image)
        }
    }

    private fun setText(text: String) {
        titleView.text = text
    }

    private fun setBackgroundRoot(isSelected: Boolean) {
        _isTransportSelected = isSelected
        val backgroundRoot = if (isSelected) {
            R.drawable.bg_transport_card_stroke
        } else {
            R.drawable.bg_transport_card_white
        }

        componentView.setBackgroundResource(backgroundRoot)
    }

    private fun setComponentType(type: Type) {
        _type = type
        val (imgRes, title) = when (type) {
            Type.BIKE -> {
                Pair(R.drawable.ic_transport_bike, "TransBike")
            }
            Type.CAR -> {
                Pair(R.drawable.ic_transport_car, "TransCar")
            }
            Type.TAXI -> {
                Pair(R.drawable.ic_transport_taxi, "TransTaxi")
            }
        }

        this.image = imgRes
        this.title = title
    }

    enum class Type {
        BIKE,
        CAR,
        TAXI
    }
}