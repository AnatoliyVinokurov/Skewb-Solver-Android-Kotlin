package com.anatoliyvinokurov.skewbsolver

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.anatoliyvinokurov.skewbsolver.databinding.ActivityMainBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

const val GAME_LENGTH_MILLISECONDS = 3000L
//ca-app-pub-3940256099942544/1033173712
const val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var interstitialAd: InterstitialAd? = null
    private var adIsLoading: Boolean = false
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar, view pager, and tab layout
        val toolbar = binding.toolbar
        val viewPager = binding.tabViewpager
        val tabLayout = binding.tabTablayout
        setSupportActionBar(toolbar)
        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        // Log the Mobile Ads SDK version
        /*Log.d(TAG, "Google Mobile Ads SDK Version: ${MobileAds.getVersion()}")*/

        // Initialize the Mobile Ads SDK
        MobileAds.initialize(this) {}

        // Set your test devices
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(listOf("ABCDEF012345")).build()
        )

        // Check if the INTERNET permission is available
        val hasInternetPermission = AppModificationChecker.hasInternetPermission(this)
        binding.retryButton.visibility = if (hasInternetPermission) View.INVISIBLE else View.VISIBLE
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(StartFragment(), "Start")
        adapter.addFragment(Step1Fragment(), "1")
        adapter.addFragment(Step2Fragment(), "2")
        adapter.addFragment(Step3Fragment(), "3")
        viewPager.adapter = adapter
    }

    class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        private val fragmentList = ArrayList<Fragment>()
        private val fragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }
    }

    private fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            AD_UNIT_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    interstitialAd = null
                    adIsLoading = false
                    val error = "domain: ${adError.domain}, code: ${adError.code}, " + "message: ${adError.message}"
                /* Toast.makeText(this@MainActivity,"onAdFailedToLoad() with error $error",Toast.LENGTH_SHORT).show() */
            }

            override fun onAdLoaded(ad: InterstitialAd) {
                interstitialAd = ad
                adIsLoading = false
                /* Toast.makeText(this@MainActivity, "onAdLoaded()", Toast.LENGTH_SHORT).show() */
            }
        }
    )
}

fun showInterstitial() {
    if (interstitialAd != null) {
        interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                interstitialAd = null
                loadAd()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                interstitialAd = null
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is dismissed.
            }
        }
        interstitialAd?.show(this)
    } else {
        /* Toast.makeText(this, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show() */
        startGame()
    }
}

fun startGame() {
    if (!adIsLoading && interstitialAd == null) {
        adIsLoading = true
        loadAd()
    }
}
}