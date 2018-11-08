package id.depok.depoksinglewindow.data.source

import id.depok.depoksinglewindow.data.api.WorkerDataResponse
import io.reactivex.Single

class WorkerDataRepository(private val remoteDataSource: WorkerDataSource): WorkerDataSource {

    override fun getWorkers(): Single<WorkerDataResponse> {
        return remoteDataSource.getWorkers()
    }
}
