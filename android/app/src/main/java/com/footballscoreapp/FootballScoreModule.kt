package com.footballscoreapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.NotificationTarget
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.WritableMap

class FootballScoreModule(private val reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    companion object {
        const val CHANNEL_ID = "football_score_channel"
        const val PREFS_NAME = "FootballScorePrefs"
    }

    private val notificationManager = NotificationManagerCompat.from(reactContext)

    init {
        createNotificationChannel()
    }

    override fun getName(): String {
        return "FootballScoreModule"
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Football Score Updates",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for football score updates"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    @ReactMethod
    fun updateScore(
        homeTeam: String,
        awayTeam: String,
        homeScore: Int,
        awayScore: Int,
        homeLogoUrl: String,
        awayLogoUrl: String,
        homeScorers: String,
        awayScorers: String,
        timestamp: String
    ) {
        // Salvar valores no SharedPreferences
        val sharedPreferences = reactContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("homeTeam", homeTeam)
            putString("awayTeam", awayTeam)
            putInt("homeScore", homeScore)
            putInt("awayScore", awayScore)
            putString("homeLogoUrl", homeLogoUrl)
            putString("awayLogoUrl", awayLogoUrl)
            putString("homeScorers", homeScorers)
            putString("awayScorers", awayScorers)
            putString("timestamp", timestamp)
            apply()
        }

        val notificationLayout = RemoteViews(reactContext.packageName, R.layout.notification_layout)
        val notificationSmallLayout = RemoteViews(reactContext.packageName, R.layout.notification_layout_small)

        // Atualiza as views da notificação expandida
        notificationLayout.setTextViewText(R.id.timestamp, timestamp)
        notificationLayout.setTextViewText(R.id.score, "$homeScore - $awayScore")
        notificationLayout.setTextViewText(R.id.home_scorers, homeScorers)
        notificationLayout.setTextViewText(R.id.away_scorers, awayScorers)
        
        // Atualiza as views da notificação retraída
        notificationSmallLayout.setTextViewText(R.id.score, "$homeScore - $awayScore")
        notificationSmallLayout.setTextViewText(R.id.timestamp, timestamp)

        // Intent para abrir o aplicativo quando a notificação for clicada
        val intent = Intent(reactContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            reactContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(reactContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setCustomContentView(notificationSmallLayout)
            .setCustomBigContentView(notificationLayout)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOngoing(true)
            .setContentIntent(pendingIntent) // Define o PendingIntent

        val notification = builder.build()

        // Carrega as imagens usando Glide
        val homeTarget = NotificationTarget(
            reactContext,
            R.id.home_logo,
            notificationLayout,
            notification,
            1
        )
        val awayTarget = NotificationTarget(
            reactContext,
            R.id.away_logo,
            notificationLayout,
            notification,
            1
        )

        val homeSmallTarget = NotificationTarget(
            reactContext,
            R.id.home_logo,
            notificationSmallLayout,
            notification,
            1
        )
        val awaySmallTarget = NotificationTarget(
            reactContext,
            R.id.away_logo,
            notificationSmallLayout,
            notification,
            1
        )

        Glide.with(reactContext.applicationContext)
            .asBitmap()
            .load(homeLogoUrl)
            .into(homeTarget)
        Glide.with(reactContext.applicationContext)
            .asBitmap()
            .load(awayLogoUrl)
            .into(awayTarget)
        
        Glide.with(reactContext.applicationContext)
            .asBitmap()
            .load(homeLogoUrl)
            .into(homeSmallTarget)
        Glide.with(reactContext.applicationContext)
            .asBitmap()
            .load(awayLogoUrl)
            .into(awaySmallTarget)

        // Exibe a notificação
        notificationManager.notify(1, notification)
    }

    @ReactMethod
    fun getSavedScore(promise: Promise) {
        val sharedPreferences = reactContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val homeTeam = sharedPreferences.getString("homeTeam", "")
        val awayTeam = sharedPreferences.getString("awayTeam", "")
        val homeScore = sharedPreferences.getInt("homeScore", 0)
        val awayScore = sharedPreferences.getInt("awayScore", 0)
        val homeLogoUrl = sharedPreferences.getString("homeLogoUrl", "")
        val awayLogoUrl = sharedPreferences.getString("awayLogoUrl", "")
        val homeScorers = sharedPreferences.getString("homeScorers", "")
        val awayScorers = sharedPreferences.getString("awayScorers", "")
        val timestamp = sharedPreferences.getString("timestamp", "")

        val result: WritableMap = Arguments.createMap().apply {
            putString("homeTeam", homeTeam)
            putString("awayTeam", awayTeam)
            putInt("homeScore", homeScore)
            putInt("awayScore", awayScore)
            putString("homeLogoUrl", homeLogoUrl)
            putString("awayLogoUrl", awayLogoUrl)
            putString("homeScorers", homeScorers)
            putString("awayScorers", awayScorers)
            putString("timestamp", timestamp)
        }

        promise.resolve(result)
    }
}
