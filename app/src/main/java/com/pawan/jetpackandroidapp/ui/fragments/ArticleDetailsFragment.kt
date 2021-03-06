package com.pawan.jetpackandroidapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pawan.jetpackandroidapp.ui.onlinenews.OfflineViewModel
import com.google.android.material.snackbar.Snackbar
import com.pawan.jetpackandroidapp.R
import com.pawan.jetpackandroidapp.databinding.FragmentArticleDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment(R.layout.fragment_article_details) {

    val TAG = "ArticleDetailsFragment"
    val viewModel: OfflineViewModel by viewModels()

    private lateinit var binding: FragmentArticleDetailsBinding
    val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val article = args.articleData
        binding = FragmentArticleDetailsBinding.bind(view)
        binding.webview.apply {
            webChromeClient = WebChromeClient()
            article?.let { data ->
                loadUrl(data.url!!)
            }
        }

        binding.fab.setOnClickListener {
            if (article != null) {
                viewModel.insertArticle(article)
            }
            Snackbar.make(binding.root, "Article Saved ", Snackbar.LENGTH_LONG).show()
        }


    }
}