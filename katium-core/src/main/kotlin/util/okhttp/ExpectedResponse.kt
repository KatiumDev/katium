package katium.core.util.okhttp

import okhttp3.Response

fun Response.expected(status: Int): Response {
    if (this.code != status) throw IllegalStateException("$this expected status $status but got $code")
    return this
}
