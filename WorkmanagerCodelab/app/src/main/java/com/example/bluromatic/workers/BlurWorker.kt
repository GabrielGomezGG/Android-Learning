package com.example.bluromatic.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.bluromatic.DELAY_TIME_MILLIS
import com.example.bluromatic.KEY_BLUR_LEVEL
import com.example.bluromatic.KEY_IMAGE_URI
import com.example.bluromatic.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

private const val TAG = "BlurWorker"

class BlurWorker(
  context: Context,
  params: WorkerParameters
) : CoroutineWorker(context, params) {

  private val wea = flow {
    var count = 0
    while (true) {
      emit(count)
      delay(1000)
      count++
    }
  }

  private var counter = 0

  override suspend fun doWork(): Result {

    wea.collect {
      Log.d("BlurWorker", "doWork: $it")

      makeStatusNotification(
//      applicationContext.resources.getString(R.string.blurring_image),
        counter.toString(),
        applicationContext
      )

      counter = it
    }

    return Result.success()

  }


//  override suspend fun doWork(): Result {
//
//    // ADD THESE LINES
//    val resourceUri = inputData.getString(KEY_IMAGE_URI)
//    val blurLevel = inputData.getInt(KEY_BLUR_LEVEL, 1)
//
//    makeStatusNotification(
//      applicationContext.resources.getString(R.string.blurring_image),
//      applicationContext
//    )
//    return withContext(Dispatchers.IO) {
//      return@withContext try {
//
//        require(!resourceUri.isNullOrBlank()) {
//          val errorMessage =
//            applicationContext.resources.getString(R.string.invalid_input_uri)
//          Log.e(TAG, errorMessage)
//          errorMessage
//        }
//
//        val resolver = applicationContext.contentResolver
//
//        // This is an utility function added to emulate slower work.
//        delay(DELAY_TIME_MILLIS)
//
////        val picture = BitmapFactory.decodeResource(
////          applicationContext.resources,
////          R.drawable.android_cupcake
////        )
//
//        val picture = BitmapFactory.decodeStream(
//          resolver.openInputStream(Uri.parse(resourceUri))
//        )
//
//        //val output = blurBitmap(picture, 1)
//        val output = blurBitmap(picture, blurLevel)
//
//        // Write bitmap to a temp file
//        val outputUri = writeBitmapToFile(applicationContext, output)
//
////        makeStatusNotification(
////          "Output is $outputUri",
////          applicationContext
////        )
//
//        val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
//
//        //Result.success()
//        Result.success(outputData)
//      } catch (throwable: Throwable) {
//        Log.e(
//          TAG,
//          applicationContext.resources.getString(R.string.error_applying_blur),
//          throwable
//        )
//        Result.failure()
//      }
//    }
//  }
}