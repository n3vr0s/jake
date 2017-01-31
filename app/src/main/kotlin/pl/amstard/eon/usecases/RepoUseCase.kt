package pl.amstard.eon.usecases

import pl.amstard.eon.communications.CommunicationManager
import pl.amstard.eon.communications.responses.RepoResponse
import rx.Observable

class RepoUseCase(
        private val communicationManager: CommunicationManager) : AubergineUseCase() {

    fun getJakeRepos(page: Int): Observable<List<RepoResponse>> {
        return communicationManager.getJakeRepos(page)
    }
}