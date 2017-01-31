package pl.amstard.eon.communications.responses

import com.google.gson.annotations.SerializedName

data class RepoResponse(
        var id: Int,
        var name: String,
        var description: String,
        @SerializedName("stargazers_count") var starCount: Int
) {

}
