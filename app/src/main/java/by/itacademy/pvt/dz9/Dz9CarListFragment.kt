package by.itacademy.pvt.dz9

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.entity.Coordinate
import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.Poi

class Dz9CarListFragment : Fragment(), Dz9Adapter.ClickListener, CarRepositoryResult {

    private val carRepository: CarRepository = provideCarRepository()
    private lateinit var adapter: Dz9Adapter
    private val poiList: MutableList<Poi> = mutableListOf()
    private var listener: ClickListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dz9_car_list, container, false)
        carRepository.getCarByCoordinate(CoordinateParams(Coordinate(2342.0, 342.0), Coordinate(3242.0, 3453.0)), this)

        return view
    }

    override fun onCarClick(item: Poi) {
        listener?.onCarClick(item)
    }

    override fun onSuccess(data: List<Poi>) {
        poiList.addAll(data)
        callAdapter()
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(context, "Something went wrong, \n please try again", Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListener)
            listener = context
    }

    fun callAdapter() {
        val dz9RecyclerView = view!!.findViewById<RecyclerView>(R.id.dz9RecyclerView)
        dz9RecyclerView?.setHasFixedSize(false)
        dz9RecyclerView.isNestedScrollingEnabled = false
        dz9RecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = Dz9Adapter(poiList, this@Dz9CarListFragment)
        dz9RecyclerView.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface ClickListener {
        fun onCarClick(item: Poi)
    }
}