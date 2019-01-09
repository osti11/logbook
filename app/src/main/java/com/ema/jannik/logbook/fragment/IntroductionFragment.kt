package com.ema.jannik.logbook.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ema.jannik.logbook.R

/**
 * This fragment
 */
class IntroductionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_introduction, container, false)
    }

    /**
     * Called to do initial creation of a fragment.  This is called after [.onAttach] and before [.onCreateView].
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);    //enable optionMenu
    }
}
