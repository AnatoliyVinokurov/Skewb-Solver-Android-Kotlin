package com.anatoliyvinokurov.skewbsolver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Fragment class representing Step 3 of the process.
 */
class Step3Fragment : Fragment() {
    /**
     * Called to create the view hierarchy associated with the fragment.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views
     * @param container          The parent view that the fragment's UI should be attached to
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state
     * @return The View for the fragment's UI, or null
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        showAdRekl()
        return inflater.inflate(
            R.layout.layout_step3, container, false
        )
    }
    /**
     * Method to show the advertisement.
     * Gets a reference to the MainActivity instance and calls the showInterstitial() function.
     */
    fun showAdRekl() {
        // Get a reference to the MainActivity instance
        val mainActivity = activity as MainActivity

        // Call the showInterstitial() function of MainActivity
        mainActivity.showInterstitial()
    }
}