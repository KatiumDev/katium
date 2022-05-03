package katium.core.util.okhttp

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException
import kotlin.coroutines.resumeWithException

@Suppress("OPT_IN_USAGE")
suspend fun Call.await(): Response {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                continuation.resume(response) {
                    response.close()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                if (continuation.isCancelled) return
                continuation.resumeWithException(e)
            }

        })

        continuation.invokeOnCancellation {
            try {
                cancel()
            } catch (ignored: Throwable) {
            }
        }
    }
}
