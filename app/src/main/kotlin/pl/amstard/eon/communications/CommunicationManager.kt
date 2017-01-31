package pl.amstard.eon.communications

import pl.amstard.eon.communications.responses.RepoResponse
import rx.Observable

class CommunicationManager(private val githubApi: GithubApi) {

   fun getJakeRepos(page: Int = 1, perPage: Int = 10): Observable<List<RepoResponse>> {
       return githubApi.getJakeRepos(page, perPage)
   }

}