package com.example.e_posyandu.config

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.e_posyandu.R
import com.example.e_posyandu.models.Jadwal
import com.example.e_posyandu.models.JadwalAnak
import com.example.e_posyandu.utilities.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@Suppress("DEPRECATION")
class MyFirebaseMessagingService : FirebaseMessagingService() {
    private lateinit var jadwalPosyandu : Jadwal

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        sendRegistrationToServer(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        println("DATA " + p0.data)

        Log.d(TAG, "Message data payload: ${p0.data}")

        val id = p0.data["id"]
        val tanggal = p0.data["tanggal"]
        val waktu = p0.data["waktu"]
        val keterangan = p0.data["keterangan"]
        val status = p0.data["status"]
        jadwalPosyandu = Jadwal(id, tanggal, waktu, keterangan, status)

        p0.notification?.let {
            showNotification(it.title.toString(), it.body.toString(), it.clickAction, jadwalPosyandu)
        }
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
        Constants.setDeviceToken(applicationContext, token!!)
    }

    private fun showNotification(title:String, body: String, clickAction: String?, jadwal: Jadwal?) {
        val channelId = getString(R.string.default_notification_channel_id)
        val intent = Intent(clickAction).apply {
            putExtra("jadwal", jadwal)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(getString(R.string.default_notification_channel_id),
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(20, builder.build())
    }

    companion object {

        private const val TAG = "MyFirebaseMsgService"
    }


}