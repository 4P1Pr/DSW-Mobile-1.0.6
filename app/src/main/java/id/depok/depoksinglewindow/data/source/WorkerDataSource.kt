package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.WorkerDataResponse
import io.reactivex.Single

interface WorkerDataSource {

    fun getWorkers(): Single<WorkerDataResponse>
}