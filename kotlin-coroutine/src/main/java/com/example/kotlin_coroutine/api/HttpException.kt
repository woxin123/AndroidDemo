package com.example.kotlin_coroutine.api

import okhttp3.Response
import java.io.IOException

class HttpException(val response: Response) : IOException(response.toString())