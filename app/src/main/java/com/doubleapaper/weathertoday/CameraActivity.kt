package com.doubleapaper.weathertoday

import android.graphics.*
import android.location.Address
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SwitchCompat
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import com.doubleapaper.weathertoday.Object.Header
import com.doubleapaper.weathertoday.Object.StationsItem
import com.google.android.gms.location.DetectedActivity
import com.google.android.gms.location.Geofence

import io.fotoapparat.Fotoapparat
import io.fotoapparat.configuration.CameraConfiguration
import io.fotoapparat.configuration.UpdateConfiguration
import io.fotoapparat.log.logcat
import io.fotoapparat.parameter.Flash
import io.fotoapparat.parameter.Zoom
import io.fotoapparat.result.transformer.scaled
import io.fotoapparat.selector.*
import io.nlopez.smartlocation.*
import io.nlopez.smartlocation.geofencing.utils.TransitionGeofence
import io.nlopez.smartlocation.location.config.LocationAccuracy
import io.nlopez.smartlocation.location.config.LocationParams
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesWithFallbackProvider
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

private const val LOGGING_TAG = "Fotoapparat Example"


class CameraActivity : AppCompatActivity() , OnLocationUpdatedListener, OnActivityUpdatedListener, OnGeofencingTransitionListener {


    private var orientationEventListener: OrientationEventListener? = null

    private var permissionsGranted: Boolean = false
    private var activeCamera: Camera = Camera.Back
    private var frontCamera: Bitmap? = null
    private var backCamera: Bitmap? = null
    private lateinit var fotoapparat: Fotoapparat
    private lateinit var cameraZoom: Zoom.VariableZoom
    private var ui_rotation:Int = 0
    private lateinit var realm: Realm
    private var showImageFull = false
    private var provider: LocationGooglePlayServicesWithFallbackProvider? = null
    private var province = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        realm = Realm.getDefaultInstance()
        cameraView.visibility = View.VISIBLE
        permissionsGranted =  true
        setGpsStatus()
        startLocation()

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        showLast()
        fotoapparat = Fotoapparat(
                context = this,
                view = cameraView,
                focusView = focusView,
                logger = logcat(),
                lensPosition = activeCamera.lensPosition,
                cameraConfiguration = activeCamera.configuration,
                cameraErrorCallback = { Log.e(LOGGING_TAG, "Camera error: ", it) }
        )
        capture onClick takePicture()
        switchCamera onClick changeCamera()

        torchSwitch onCheckedChanged toggleFlash()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            orientationEventListener = object : OrientationEventListener(this) {
                override fun onOrientationChanged(orientation: Int) {
                    this@CameraActivity.onOrientationChanged(orientation)
                }
            }
        }

    }


    override fun onLocationUpdated(p0: Location?) {
        showLocation(p0)
    }

    override fun onActivityUpdated(p0: DetectedActivity?) {
        showActivity(p0)
    }

    override fun onGeofenceTransition(p0: TransitionGeofence) {
        showGeofence(p0.geofenceModel.toGeofence(), p0.transitionType)
    }

    private fun showLast() {
        val lastLocation = SmartLocation.with(this).location().lastLocation
        setGpsStatus()
       // if (lastLocation != null)
           /* locationText.setText(
                    String.format("[From Cache] Latitude %.6f, Longitude %.6f",
                            lastLocation.latitude,
                            lastLocation.longitude)
            )*/

        val detectedActivity: DetectedActivity?
        detectedActivity = SmartLocation.with(this).activity().lastActivity
        //if (detectedActivity != null)
            /*activityText.setText(
                    String.format("[From Cache] Activity %s with %d%% confidence",
                            getNameFromType(detectedActivity),
                            detectedActivity.confidence)
            )*/
    }
    private fun showLocation(location: Location?) {
        if (location != null) {
            gpsStatus.setImageResource(R.drawable.round_gps_fixed_24)
            val text = String.format("Latitude %.8f, Longitude %.8f",
                    location.latitude,
                    location.longitude)

            SmartLocation.with(this).geocoding().reverse(location) { original, results ->
                if (results.size > 0) {
                    val result = results[0]
                    val builder = StringBuilder(text)
                    builder.append("\n[Reverse Geocoding] ")
                    val addressElements = ArrayList<String>()
                    for (i in 0..result.maxAddressLineIndex) {
                        addressElements.add(result.getAddressLine(i))
                    }
                    builder.append(TextUtils.join(", ", addressElements))

                    province = result.adminArea

                }
            }
        } else {
            //locationText.setText("Null location")
            gpsStatus.setImageResource(R.drawable.round_gps_not_fixed_24)
        }
    }

    private fun startLocation() {

        provider = LocationGooglePlayServicesWithFallbackProvider(this@CameraActivity)

        val params = LocationParams.Builder()
                .setAccuracy(LocationAccuracy.HIGH)
                .setDistance(1f)
                .setInterval(1000)
                .build()
        val smartLocation = SmartLocation.Builder(this).logging(true).build()

        smartLocation.location(provider).config(params).start(this)
        smartLocation.activity().start(this)

    }

    private fun stopLocation() {
        SmartLocation.with(this).location().stop()
       // locationText.setText("Location stopped!")

        SmartLocation.with(this).activity().stop()
       // activityText.setText("Activity Recognition stopped!")

        SmartLocation.with(this).geofencing().stop()
        //geofenceText.text = "Geofencing stopped!"
    }
    private fun showActivity(detectedActivity: DetectedActivity?) {
        if (detectedActivity != null)
            /*activityText.setText(
                    String.format("Activity %s with %d%% confidence",
                            getNameFromType(detectedActivity),
                            detectedActivity.confidence)
            )*/
        else {
           // activityText.setText("Null activity")
        }
    }

    private fun getNameFromType(activityType: DetectedActivity): String {
        when (activityType.type) {
            DetectedActivity.IN_VEHICLE -> return "in_vehicle"
            DetectedActivity.ON_BICYCLE -> return "on_bicycle"
            DetectedActivity.ON_FOOT -> return "on_foot"
            DetectedActivity.STILL -> return "still"
            DetectedActivity.TILTING -> return "tilting"
            else -> return "unknown"
        }
    }
    private fun setGpsStatus() {
        if (SmartLocation.with(this).location().state().isGpsAvailable)
            gpsStatus.setImageResource(R.drawable.round_gps_not_fixed_24)
        else
            gpsStatus.setImageResource(R.drawable.round_gps_off_24)
    }

    private fun showGeofence(geofence: Geofence?, transitionType: Int) {
        if (geofence != null) {
            //geofenceText.setText("Transition " + getTransitionNameFromType(transitionType) + " for Geofence with id = " + geofence.requestId)
        } else {
            //geofenceText.setText("Null geofence")
        }
    }

    private fun getTransitionNameFromType(transitionType: Int): String {
        when (transitionType) {
            Geofence.GEOFENCE_TRANSITION_ENTER -> return "enter"
            Geofence.GEOFENCE_TRANSITION_EXIT -> return "exit"
            else -> return "dwell"
        }
    }
    internal infix fun SwitchCompat.onCheckedChanged(function: (CompoundButton, Boolean) -> Unit) {
        setOnCheckedChangeListener(function)
    }

    internal infix fun View.onClick(function: () -> Unit) {
        setOnClickListener { function() }
    }

    internal infix fun SeekBar.onProgressChanged(zoomUpdated: () -> Unit) {
        setOnSeekBarChangeListener(object : OnProgressChanged() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                zoomUpdated()
            }
        })
    }
    abstract class OnProgressChanged : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(seekBar: SeekBar) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {

        }
    }
    private fun changeCamera(): () -> Unit = {
        activeCamera = when (activeCamera) {
            Camera.Front -> Camera.Back
            Camera.Back -> Camera.Front
        }

        fotoapparat.switchTo(
                lensPosition = activeCamera.lensPosition,
                cameraConfiguration = activeCamera.configuration
        )

        adjustViewsVisibility()

        torchSwitch.isChecked = false

        Log.i(LOGGING_TAG, "New camera position: ${if (activeCamera is Camera.Back) "back" else "front"}")
    }

    private fun toggleFlash(): (CompoundButton, Boolean) -> Unit = { _, isChecked ->
        fotoapparat.updateConfiguration(
                UpdateConfiguration(
                        flashMode = if (isChecked) {
                            firstAvailable(
                                    torch(),
                                    off()
                            )
                        } else {
                            off()
                        }
                )
        )

        Log.i(LOGGING_TAG, "Flash is now ${if (isChecked) "on" else "off"}")
    }

    override fun onStart() {
        super.onStart()
        if (permissionsGranted) {
            fotoapparat.start()
            adjustViewsVisibility()
        }
    }

    override fun onResume() {
        super.onResume()
        this.orientationEventListener?.enable()

    }
    override fun onStop() {
        super.onStop()
        if (permissionsGranted) {
            fotoapparat.stop()
            stopLocation()
        }
    }
    private fun adjustViewsVisibility() {
        fotoapparat.getCapabilities()
                .whenAvailable { capabilities ->
                    capabilities
                            ?.let {
                                (it.zoom as? Zoom.VariableZoom)
                                        ?.let { zoom -> setupZoom(zoom) }
                                        ?: run { zoomSeekBar.visibility = View.GONE }

                                torchSwitch.visibility = if (it.flashModes.contains(Flash.Torch)) View.VISIBLE else View.GONE
                            }
                            ?: Log.e(LOGGING_TAG, "Couldn't obtain capabilities.")
                }

        switchCamera.visibility = if (fotoapparat.isAvailable(front())) View.VISIBLE else View.GONE
    }

    private fun setupZoom(zoom: Zoom.VariableZoom) {
        zoomSeekBar.max = zoom.maxZoom
        cameraZoom = zoom
        zoomSeekBar.visibility = View.VISIBLE
        zoomSeekBar onProgressChanged { updateZoom(zoomSeekBar.progress) }
        updateZoom(0)
    }

    private fun updateZoom(progress: Int) {
        fotoapparat.setZoom(progress.toFloat() / zoomSeekBar.max)
        val value = cameraZoom.zoomRatios[progress]
        val roundedValue = ((value.toFloat()) / 10).roundToInt().toFloat() / 10
        zoomLvl.text = String.format("%.1f ×", roundedValue)
    }
    private fun onOrientationChanged(orientation: Int) {
        var orientation = orientation
        var current_orientation = 0
        if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN)
            return

        orientation = (orientation + 45) / 90 * 90
        orientation = orientation % 360
        current_orientation = orientation

        val rotation = this.windowManager.defaultDisplay.rotation
        var degrees = 0
        when (rotation) {
            Surface.ROTATION_0 -> degrees = 0
            Surface.ROTATION_90 -> degrees = 90
            Surface.ROTATION_180 -> degrees = 180
            Surface.ROTATION_270 -> degrees = 270
        }
        val relative_orientation = (current_orientation + degrees) % 360
        ui_rotation = (360 - relative_orientation) % 360

        switchCamera.setRotation(ui_rotation.toFloat())
        result.setRotation(ui_rotation.toFloat())
        capture.setRotation(ui_rotation.toFloat())
    }
    private fun takePicture(): () -> Unit = {
        val photoResult = fotoapparat
                .autoFocus()
                .takePicture()
        var ffile:String = "photos"
        if (activeCamera is Camera.Back) ffile+= "back" else ffile+="front"
        photoResult
                .saveToFile(File(
                        getExternalFilesDir("photos"),
                        ffile+".jpg"
                ))

        photoResult
                .toBitmap(scaled(scaleFactor = 0.25f))
                .whenAvailable { photo ->
                    photo
                            ?.let {
                                Log.i(LOGGING_TAG, "New photo captured. Bitmap length: ${it.bitmap.byteCount}")

                                val rotation = ui_rotation

                                val imageView = findViewById<ImageView>(R.id.result)
                                var bitmap:Bitmap = RotateBitmap(it.bitmap,-it.rotationDegrees)
                                if (backCamera == null){

                                    if (activeCamera is Camera.Back) {
                                        backCamera = bitmap;
                                        imageView.setImageBitmap(bitmap)
                                        imageView.rotation = rotation.toFloat()

                                        activeCamera = Camera.Front
                                        fotoapparat.switchTo(lensPosition = activeCamera.lensPosition,
                                                cameraConfiguration = activeCamera.configuration)
                                    }else {
                                        backCamera = RotateBitmap(it.bitmap, rotation - it.rotationDegrees)
                                        imageView.setImageBitmap(bitmap)
                                        imageView.rotation = rotation.toFloat()
                                    }
                                    val handler = Handler()
                                    handler.postDelayed({ takePicture().invoke() }, 2000)
                                }
                                else if (frontCamera == null){
                                    bitmap = RotateBitmap(it.bitmap, rotation - it.rotationDegrees)
                                    frontCamera = getCircularBitmap(bitmap)
                                }

                                if (backCamera != null && frontCamera != null) {
                                    Log.i(LOGGING_TAG, "capture 2photo.")
                                    try{
                                        var src:Bitmap = DrawBitmap(backCamera!!, frontCamera!!)
                                        imageView.setImageBitmap(src)
                                        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
                                        val cal = Calendar.getInstance()
                                        val f = File(getExternalFilesDir("photos"), dateFormat.format(cal.time) + ".jpg")
                                        f.createNewFile()
                                        val bos = ByteArrayOutputStream()
                                        src.compress(Bitmap.CompressFormat.JPEG, 90 /*ignored for PNG*/, bos)
                                        val bitmapdata = bos.toByteArray()

                                        //write the bytes in file
                                        val fos = FileOutputStream(f)
                                        fos.write(bitmapdata)
                                        fos.flush()
                                        fos.close()
                                        backCamera = null
                                        frontCamera = null
                                        activeCamera = Camera.Back
                                        fotoapparat.switchTo(lensPosition = activeCamera.lensPosition,
                                                cameraConfiguration = activeCamera.configuration)

                                    }catch (ex: Exception){
                                        Log.e(LOGGING_TAG, "capture $ex")
                                    }

                                }
                                imageView.rotation = rotation.toFloat()

                            }
                            ?: Log.e(LOGGING_TAG, "Couldn't capture photo.")
                }
    }
    fun toSimpleString(date: Date?) = with(date ?: Date()) {
        SimpleDateFormat("dd/MM/yyy HH:mm:ss").format(this)
    }
    fun RotateBitmap(source: Bitmap, angle: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle.toFloat())
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    fun getCircularBitmap(bitmap: Bitmap): Bitmap {

        val width = bitmap.width
        val height = bitmap.height
        val outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val path = Path()

        if (width > height) {

            path.addCircle(
                    (width / 2).toFloat(), (height / 2).toFloat(), Math.min(width, height / 2).toFloat(), Path.Direction.CCW)
        } else {

            path.addCircle(
                    (width / 2).toFloat(), (height / 2).toFloat(), Math.min(width, width / 2).toFloat(), Path.Direction.CCW)

        }


        val canvas = Canvas(outputBitmap)


        canvas.clipPath(path)
        canvas.drawBitmap(bitmap, 0f, 0f, null)


        return outputBitmap
    }
    private fun DrawBitmap(back:Bitmap, front:Bitmap): Bitmap{


        var puppies = realm.where(StationsItem::class.java).equalTo("province", province).findAll()
        var Temperature: Double? = null
        if (puppies.size > 0)Temperature = puppies[0]!!.temperatureValue
        else {
            var puppies = realm.where(StationsItem::class.java).findAll()
            Temperature = puppies[0]!!.temperatureValue
        }
        val mutableBitmap = back.copy(Bitmap.Config.ARGB_8888, true)

        var canvas = Canvas(mutableBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        val rw = mutableBitmap.width
        val rh = mutableBitmap.height
        val w = mutableBitmap.width
        val h = mutableBitmap.height

        /* Calculate logo size */
        val nh = (h * 0.13).toInt()
        val nw = (nh * 0.56).toInt()

        /********************* Draw user face *************************/
        val border = 2

        val px = (h * 0.02).toInt()
        val py = (h * 0.7).toInt()

        val rect_w = (px + h * 0.085).toInt()
        val rect_h = (py + h * 0.109).toInt()
        val img_w = rect_w - px - 4
        val img_h = rect_h - py - 4


        /* Paint text */
        paint.isAntiAlias = true
        paint.textSize = 45.0f
        paint.strokeWidth = 2.0f
        paint.style = Paint.Style.FILL
        paint.setShadowLayer(5.0f, 3.0f, 3.0f, Color.BLACK)
        var xpos = 0
        if (w > h) run {
            var custom_width = w - w / 6 - (px + 4)

            //Paint Strip Line
            paint.color = this.resources.getColor(R.color.background_floating_material_dark)
            paint.alpha = 0
            canvas.drawRect((rw - rw).toFloat(), (rh * 0.75).toInt().toFloat(), rw.toFloat(), rh.toFloat(), paint)

            //Draw Face
            if (front != null) {
                paint.alpha = 200 // alpha 200
                val dwFace = Bitmap.createScaledBitmap(front, w / 5, h / 5, true)
                custom_width = w - w / 6 - (px + border)
                canvas.drawBitmap(dwFace,  custom_width.toFloat(), ((h * 0.775).toInt() + border).toFloat(), paint)
            }

            val SizeTextDetailGPS = (h * 0.025).toInt()
            val SizeTextDetailGPSAddress = (h * 0.023).toInt()

            xpos = (rw * 0.035).toInt()

            paint.color = Color.WHITE

            paint.textAlign = Paint.Align.LEFT




            //Paint DateTime
            paint.textSize = SizeTextDetailGPS.toFloat()
            paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            canvas.drawText("DateStamp" + " " + toSimpleString(Date()), xpos.toFloat(), (rh * 0.955).toInt().toFloat(), paint)

            //Paint GPS
            paint.textSize = SizeTextDetailGPSAddress.toFloat()
            paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            if (Temperature != null)
            canvas.drawText("จังหวัด $province อุณหภูมิ ${Temperature.toString()}" , xpos.toFloat(), (rh * 0.985).toInt().toFloat(), paint)
            else
                canvas.drawText("จังหวัด $province " , xpos.toFloat(), (rh * 0.985).toInt().toFloat(), paint)


        } else run {

            val custom_width = w - w / 6 - (px + 20)

            //Paint Strip Line
            paint.color = this.resources.getColor(R.color.background_floating_material_dark)
            paint.alpha = 0
            canvas.drawRect((rw - rw).toFloat(), (rh * 0.82).toInt().toFloat(), rw.toFloat(), rh.toFloat(), paint)

            if (front != null) {
                paint.color = -0x37000001 // alpha 200
                val dwFace = Bitmap.createScaledBitmap(front, w / 5, h / 5, true)
                canvas.drawBitmap(dwFace, custom_width.toFloat(), (rh * 0.81).toInt().toFloat(), paint)
                dwFace.recycle()
                System.gc()
            }

            val SizeTextDetail = (h * 0.018).toInt()
            val SizeTextStamp = (h * 0.025).toInt()
            val SizeTextDetailGPS = (h * 0.015).toInt()
            val SizeTextDetailGPSAddress = (h * 0.014).toInt()
            val SizeLine = (h * 0.001).toInt()
            val SizeTest = (h * 0.06).toInt()
            xpos = (rw * 0.035).toInt()

            paint.color = Color.WHITE
            paint.alpha = 255

            paint.textAlign = Paint.Align.LEFT




            //Paint DateTime
            paint.textSize = SizeTextDetailGPS.toFloat()
            paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            canvas.drawText("DateStamp" + " " + toSimpleString(Date()), xpos.toFloat(), (rh * 0.965).toInt().toFloat(), paint)

            //Paint GPS
            paint.textSize = SizeTextDetailGPSAddress.toFloat()
            paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            if (Temperature != null)
            canvas.drawText("จังหวัด $province อุณหภูมิ ${Temperature.toString()}", xpos.toFloat(), (rh * 0.985).toInt().toFloat(), paint)
            else
                canvas.drawText("จังหวัด $province " , xpos.toFloat(), (rh * 0.985).toInt().toFloat(), paint)

        }
        return mutableBitmap
    }
}
private sealed class Camera(
        val lensPosition: LensPositionSelector,
        val configuration: CameraConfiguration
) {

    object Back : Camera(
            lensPosition = back(),
            configuration = CameraConfiguration(
                    previewResolution = firstAvailable(
                            wideRatio(highestResolution()),
                            standardRatio(highestResolution())
                    ),
                    previewFpsRange = highestFps(),
                    flashMode = off(),
                    focusMode = firstAvailable(
                            continuousFocusPicture(),
                            autoFocus()
                    ),
                    frameProcessor = {
                        // Do something with the preview frame
                    }
            )
    )

    object Front : Camera(
            lensPosition = front(),
            configuration = CameraConfiguration(
                    previewResolution = firstAvailable(
                            wideRatio(highestResolution()),
                            standardRatio(highestResolution())
                    ),
                    previewFpsRange = highestFps(),
                    flashMode = off(),
                    focusMode = firstAvailable(
                            fixed(),
                            autoFocus()
                    )
            )
    )
}
