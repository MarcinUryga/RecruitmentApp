package com.example.recruitmentapp.ui.recruitment.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.recruitmentapp.databinding.FragmentDetailsBinding
import com.example.recruitmentapp.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _viewDataBinding: FragmentDetailsBinding? = null
    private val viewDataBinding get() = _viewDataBinding!!
    private val arguments: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewDataBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        arguments.title.let {
            (requireActivity() as MainActivity).supportActionBar?.title = it
        }
        setupWebView()
        return viewDataBinding.root
    }

    private fun setupWebView() {
        viewDataBinding.webView.apply {
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.allowContentAccess = true
            settings.domStorageEnabled = true
            webViewClient = CustomWebViewClient()
        }
        arguments.url.let {
            viewDataBinding.webView.loadUrl(it)
            Log.d(TAG, "setupWebView: $it")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewDataBinding = null
    }

    inner class CustomWebViewClient : WebViewClient() {
        private val progressBar: ProgressBar by lazy {
            viewDataBinding.progressBar
        }

        init {
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "DetailsFragment"
    }
}
