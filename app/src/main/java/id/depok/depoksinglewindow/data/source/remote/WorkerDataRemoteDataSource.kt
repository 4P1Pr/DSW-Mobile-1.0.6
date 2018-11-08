package id.depok.depoksinglewindow.data.source.remote

import id.depok.depoksinglewindow.data.api.ApiService
import id.depok.depoksinglewindow.data.api.WorkerDataResponse
import id.depok.depoksinglewindow.data.source.WorkerDataSource
import io.reactivex.Single

class WorkerDataRemoteDataSource (private val apiService: ApiService): WorkerDataSource {

    override fun getWorkers(): Single<WorkerDataResponse> {
        return apiService.getWorkers()
    }
}