package pl.amstard.eon.communications

import pl.amstard.eon.communications.responses.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface GithubApi {

    @GET("users/JakeWharton/repos") fun getJakeRepos(@Query("page") page: Int, @Query("per_page") perPage: Int): Observable<List<RepoResponse>>
}

