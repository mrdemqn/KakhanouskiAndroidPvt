package by.itacademy.pvt.dz11.mvvm.timer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.pvt.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_timer.*
import java.util.concurrent.TimeUnit

class TimerActivity : AppCompatActivity() {

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val tim = resources.getString(R.string.timer123)


        val disposable: Disposable = Observable
            .interval(1, TimeUnit.SECONDS)
            .filter { t -> t % 2 == 0L }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ numbers ->
                btnTimer.text = "$tim" + " $numbers"
            }, {
                Log.e("Error", "it ${it.localizedMessage}")
            })
        compositeDisposable?.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable?.clear()
        super.onDestroy()
    }
}