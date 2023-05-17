package com.anatoliyvinokurov.skewbsolver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Fragment class representing Step 1 of the process.
 */
class Step1Fragment : Fragment() {
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
        downloadAdRekl()
        return inflater.inflate(
            R.layout.layout_step1, container, false
        )
    }


    /**
     * Method to download advertisements.
     */
    fun downloadAdRekl() {
        // Get a reference to the MainActivity instance
        val mainActivity = activity as MainActivity

        // Call the startGame() method of MainActivity
        mainActivity.startGame()
    }
}