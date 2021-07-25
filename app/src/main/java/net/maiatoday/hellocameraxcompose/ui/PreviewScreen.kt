package net.maiatoday.hellocameraxcompose.ui

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import net.maiatoday.hellocameraxcompose.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PreviewScreen() {

    val outputDirectory = getOutputDirectory(LocalContext.current)
    Box(Modifier.fillMaxSize()) {
        SimpleCameraPreview()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                takePhoto(outputDirectory)
            /*takePhoto(LocalContext.current, )*/}) // Aargh how to transition from the composable world to the classic world
            { Text("Take Photo") }
        }
    }
}

@Composable
fun SimpleCameraPreview() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val executor = ContextCompat.getMainExecutor(ctx)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview
                )
            }, executor)
            previewView
        },
        modifier = Modifier.fillMaxSize(),
    )
}

private fun takePhoto(outputDirectory: File) {
    // Aaargh!!!! again transitions are messy, State of the camera and
    // events are bleeding into compose land
    val imageCapture = ImageCapture.Builder()
        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
       // .setTargetRotation(rotation)
        .build()

    // Create time-stamped output file to hold the image
    val photoFile = File(
        outputDirectory,
        SimpleDateFormat(
            FILENAME_FORMAT, Locale.US
        ).format(System.currentTimeMillis()) + ".jpg")

    // Create output options object which contains file + metadata
    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    // Set up image capture listener, which is triggered after photo has
    // been taken
//    imageCapture.takePicture(
//        outputOptions, ContextCompat.getMainExecutor(context), object : ImageCapture.OnImageSavedCallback {
//            override fun onError(exc: ImageCaptureException) {
//                Log.e("Compose", "Photo capture failed: ${exc.message}", exc)
//            }
//
//            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
//                val savedUri = Uri.fromFile(photoFile)
//                val msg = "Photo capture succeeded: $savedUri"
////                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
////                Log.d("Compose", msg)
//            }
//        })


}

private fun getOutputDirectory(context: Context): File {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() }
    }
    return if (mediaDir != null && mediaDir.exists())
        mediaDir else context.filesDir
}

private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

//private fun capture() {
//    // Create output file to hold the image
//    val photoFile = createFile(outputFolder, FILENAME, PHOTO_EXTENSION)
//
//    // Create output options object which contains file + metadata
//    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile)
//        .build()
//
//    imageCapture.takePicture(outputOptions, Executors.newSingleThreadExecutor(),
//        object : ImageCapture.OnImageSavedCallback {
//            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
//                captureFileUri = output.savedUri ?: Uri.fromFile(photoFile)
//                Log.d(TAG, "Photo capture succeeded: $captureFileUri")
//            }
//            override fun onError(exception: ImageCaptureException) {
//                Log.e(TAG, "Photo capture exception: $exception")
//            }
//        })
//}