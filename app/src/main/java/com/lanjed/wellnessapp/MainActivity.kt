package com.lanjed.wellnessapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        initialize admob ads
        MobileAds.initialize(this)

//        load banner ad
        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

//        load interstitial ads
        loadInterstitialAd()


//        nutrition advice intent
//        nutrition advice button
        val nutritionAdvice = findViewById<Button>(R.id.nutrition_advice)
//        nutrition onClick listener implementation
        nutritionAdvice.setOnClickListener {
            val intent = Intent(applicationContext, NutritionAdvice::class.java)
            startActivity(intent)
        }

//        healthy recipe intent
        val healthyRecipes = findViewById<Button>(R.id.healthy_recipes)
        healthyRecipes.setOnClickListener {
            val intent = Intent(applicationContext, HealthyRecipes::class.java)

            showInterstitialAd()
            startActivity(intent)
        }

//        meditation intent
        val meditation = findViewById<Button>(R.id.meditation)
        meditation.setOnClickListener {
            val intent = Intent(applicationContext, Meditation::class.java)
            startActivity(intent)
        }
//        hydration alert intent
        val hydrationAlert = findViewById<Button>(R.id.hydration_alert)
        hydrationAlert.setOnClickListener {
            val intent = Intent(applicationContext, HydrationAlert::class.java)
            startActivity(intent)

        }
//        start exercise
        val startExercise = findViewById<Button>(R.id.start_exercise)
        startExercise.setOnClickListener {
            val intent = Intent(applicationContext, StartExercise::class.java)
            startActivity(intent)

        }

//        daily motivation
        val dailyMotivation = findViewById<Button>(R.id.daily_motivation)
        dailyMotivation.setOnClickListener {
            val intent = Intent(applicationContext, DailyMotivation::class.java)
            startActivity(intent)
        }

//        weekly goals
        val weeklyGoals = findViewById<Button>(R.id.weekly_goals)
        weeklyGoals.setOnClickListener {
            val intent = Intent(applicationContext, WeeklyGoals::class.java)
            startActivity(intent)
        }

//        check progress
        val checkProgress = findViewById<Button>(R.id.check_progress)
        checkProgress.setOnClickListener {
            val intent = Intent(applicationContext, CheckProgress::class.java)
            startActivity(intent)
        }

    }
//interstitial ad implementation
fun loadInterstitialAd() {
    val adRequest = AdRequest.Builder().build()
    //Requests interstitial ads
    InterstitialAd.load(
        this,
        "ca-app-pub-3940256099942544/1033173712", // Test ID
        adRequest,
        object : InterstitialAdLoadCallback() {

            override fun onAdLoaded(ad: InterstitialAd) {
                mInterstitialAd = ad
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                mInterstitialAd = null
            }
        }
    )
}
        //Function checks if ad already running not to run anothet one and overlap - which is wrong
        fun showInterstitialAd() {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            }
        }



}