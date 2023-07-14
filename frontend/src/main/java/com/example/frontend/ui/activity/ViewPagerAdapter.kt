package com.example.androiddeveloper.ui.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.androiddeveloper.R
import java.util.*

class ViewPagerAdapter(val context: Context, val imageList: List<Int>) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView: View = mLayoutInflater.inflate(R.layout.imagem_slider_item, container, false)

        val imageView: ImageView = itemView.findViewById<View>(R.id.idIVImage) as ImageView
        val boxPosition01: ConstraintLayout = itemView.findViewById<View>(R.id.position_one) as ConstraintLayout
        val boxPosition02: ConstraintLayout = itemView.findViewById<View>(R.id.position_two) as ConstraintLayout
        val boxPosition03: ConstraintLayout = itemView.findViewById<View>(R.id.position_three) as ConstraintLayout

        if(position == 0){
            boxPosition01.visibility = View.VISIBLE
        } else if(position == 1){
            boxPosition02.visibility = View.VISIBLE
        } else {
            boxPosition03.visibility = View.VISIBLE
        }

        imageView.setImageResource(imageList.get(position))

        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}
