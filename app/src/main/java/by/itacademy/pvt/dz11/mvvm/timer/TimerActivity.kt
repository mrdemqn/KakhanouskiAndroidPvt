package by.itacademy.pvt.dz11.mvvm.timer

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.pvt.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_timer.*

class TimerActivity: AppCompatActivity() {

    private var disposableBag: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val tim = resources.getString(R.string.timer123)


        val disposable : Disposable = timer()
            .filter { t -> t % 2 == 0L }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ numbers ->
                btnTimer.text = "$tim" + " $numbers"
            }, {
                Log.e("Error", "it ${it.localizedMessage}")
            }, {

            })

        disposableBag?.add(disposable)

    }

    fun timer() : Observable<Long> {
        return Observable.create { subscriber ->
            for (sec in 0..Long.MAX_VALUE) {
                Thread.sleep(1000)
                subscriber.onNext(sec)
            }
        }
    }

    override fun onDestroy() {
        disposableBag?.clear()
        super.onDestroy()
    }
}