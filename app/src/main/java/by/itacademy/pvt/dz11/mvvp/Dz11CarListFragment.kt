package by.itacademy.pvt.dz11.mvvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.entity.Poi
import by.itacademy.pvt.dz9.Dz9Adapter

class Dz11CarListFragment : Fragment(), Dz9Adapter.ClickListener {

    private lateinit var adapter: Dz9Adapter
    private lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz9_car_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this.activity!!).get(ViewModel::class.java)
        viewModel
            .state
            .observe(this, Observer { state ->
                when (state) {
                    is CarRepositoryState.SuccessProgress -> {
                        callAdapter(state.carList)
                    }
                }
            })
    }

    override fun onCarClick(item: Poi) {
        viewModel.clickByCar(item)
    }

    fun callAdapter(poiList: List<Poi>) {
        val dz9RecyclerView = view!!.findViewById<RecyclerView>(R.id.dz9RecyclerView)
        dz9RecyclerView?.setHasFixedSize(false)
        dz9RecyclerView.isNestedScrollingEnabled = false
        dz9RecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = Dz9Adapter(poiList, this@Dz11CarListFragment)
        dz9RecyclerView.adapter = adapter
    }
}