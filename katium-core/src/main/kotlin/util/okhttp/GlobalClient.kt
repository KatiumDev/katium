package katium.core.util.okhttp

import okhttp3.OkHttpClient

val GlobalHttpClient = OkHttpClient.Builder()
    .build()
