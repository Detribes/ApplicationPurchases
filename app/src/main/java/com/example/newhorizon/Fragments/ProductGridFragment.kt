package com.example.newhorizon.Fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newhorizon.NavigationIconClickListener
import com.example.newhorizon.Network.ProductEntry
import com.example.newhorizon.ProductGridItemDecoration
import com.example.newhorizon.R
import com.example.newhorizon.databinding.FragmentProductGridBinding
import com.example.newhorizon.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter

class ProductGridFragment : Fragment() {

    lateinit var _binding :FragmentProductGridBinding

    private fun init() = with(_binding){
        (activity as AppCompatActivity).setSupportActionBar(appBar)
        appBar.setNavigationOnClickListener(
            NavigationIconClickListener(requireActivity(),
            productGrid,
            AccelerateDecelerateInterpolator(),
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_menu),
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_closemenu))
        )
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }
        recyclerView.layoutManager = gridLayoutManager
        val adapter = StaggeredProductCardRecyclerViewAdapter(ProductEntry.initProductEntryList(resources))
        recyclerView.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.staggered_product_grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.staggered_product_grid_spacing_small)
        recyclerView.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            productGrid.background = context?.getDrawable(R.drawable.product_grid_background_shape)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductGridBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }
}