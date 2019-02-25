package com.ionnt.newscleanarchkotlin.ui.news

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ionnt.newscleanarchkotlin.R
import com.ionnt.newscleanarchkotlin.commons.base.BaseFragment
import com.ionnt.newscleanarchkotlin.commons.exception.Failure
import com.ionnt.newscleanarchkotlin.commons.extensions.invisible
import com.ionnt.newscleanarchkotlin.commons.extensions.visible
import com.ionnt.newscleanarchkotlin.model.Articles
import com.ionnt.newscleanarchkotlin.navigations.Navigator
import com.ionnt.newscleanarchkotlin.ui.news.adapter.HeadlineNewsAdapter
import com.ionnt.newscleanarchkotlin.ui.news.adapter.NewsByCategoryAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject


/**
 * Created by Martin De Girolamo on 23/01/2019.
 */

class NewsFragment: BaseFragment() {
    @Inject lateinit var navigator: Navigator
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsByCategoryAdapter: NewsByCategoryAdapter
    private lateinit var headlineNewsAdapter: HeadlineNewsAdapter
    private var selectedCountry: String = "ar"
    private var selectedCategory: String = "business"
    private var isAdapterHeadlines: Boolean = true

    override fun layoutId() = R.layout.fragment_news

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(NewsViewModel::class.java)
        loadInitialData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        viewModel.articles.observe(viewLifecycleOwner, Observer(::headlinesNews))
        viewModel.newsList.observe(viewLifecycleOwner, Observer(::NewsByCategory))
        viewModel.failure.observe(viewLifecycleOwner, Observer(::handleError))

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let { bundle ->
            bundle.getString("countryCode")?.let { selectedCountry = it }
            bundle.getString("categoryCode")?.let { selectedCategory = it }
            isAdapterHeadlines = bundle.getBoolean("isHeadline")
        }

        initAdapters()
        setupSpinners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("countryCode", selectedCountry)
        outState.putString("categoryCode", selectedCategory)
        outState.putBoolean("isHeadline", isAdapterHeadlines)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            return when(it.itemId) {
                R.id.news_headline -> {
                    isAdapterHeadlines = true
                    setAdapter(isAdapterHeadlines)
                    enableDisableSpinners(isAdapterHeadlines)
                    viewModel.getNewsHeadlinesArticles(selectedCountry)
                    frameProgress.visible()
                    true
                }
                R.id.news_category -> {
                    isAdapterHeadlines = false
                    setAdapter(isAdapterHeadlines)
                    enableDisableSpinners(isAdapterHeadlines)
                    // Get by category
                    viewModel.getNewsByCategory(selectedCategory)
                    true
                }
                else -> false
            }
        }

        return true
    }

    private fun loadInitialData() {
        if (isAdapterHeadlines) viewModel.getNewsHeadlinesArticles(selectedCountry)
        else viewModel.getNewsByCategory(selectedCategory)
    }

    private fun headlinesNews(articlesList: List<Articles>) {
        headlineNewsAdapter.setListData(articlesList)
        frameProgress.invisible()
    }

    private fun NewsByCategory(pagedList: PagedList<Articles>) {
        newsByCategoryAdapter.submitList(pagedList)
    }

    private fun itemClicked(item: Articles) {
        navigator.showNewsDetail(requireContext(), item)
    }

    private fun handleError(failure: Failure) {
        frameProgress.invisible()
        Log.i("Error Server", "Error en request = $failure")
    }

    private fun setupSpinners() {
        val categoryAdapter =
            ArrayAdapter.createFromResource(requireContext(), R.array.news_categories, android.R.layout.simple_spinner_item)
        val countryAdapter =
            ArrayAdapter.createFromResource(requireContext(), R.array.countre_code, android.R.layout.simple_spinner_item)

        // Configure spinners by menu option.
        enableDisableSpinners(isAdapterHeadlines)

        categorySpinner.adapter = categoryAdapter
        categorySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) { /* Nothing */ }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (selectedCategory != p0?.adapter?.getItem(p2).toString()) {
                    selectedCategory = p0?.adapter?.getItem(p2).toString()
                    news_recycler_view.smoothScrollToPosition(0)
                    viewModel.getNewsByCategory(selectedCategory)
                }
            }
        }

        countrySpinner.adapter = countryAdapter
        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) { /* Nothing */
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (selectedCountry != p0?.adapter?.getItem(p2).toString()) {
                    selectedCountry = p0?.adapter?.getItem(p2).toString()
                    news_recycler_view.smoothScrollToPosition(0)
                    viewModel.getNewsHeadlinesArticles(selectedCountry)
                    frameProgress.visible()
                }
            }
        }
    }

    private fun initAdapters() {
        headlineNewsAdapter = HeadlineNewsAdapter(ArrayList())
        newsByCategoryAdapter = NewsByCategoryAdapter()
        news_recycler_view.layoutManager = LinearLayoutManager(requireContext())

        setAdapter(isAdapterHeadlines) // By default top headlines news are first

        newsByCategoryAdapter.onClickAction = { item -> itemClicked(item) }
        headlineNewsAdapter.onClickAction   = { item -> itemClicked(item) }
    }

    private fun enableDisableSpinners(enableCountry: Boolean) {
        countrySpinner.isEnabled = enableCountry
        countrySpinner.isClickable = enableCountry
        categorySpinner.isEnabled = !enableCountry
        categorySpinner.isClickable = !enableCountry
    }

    private fun setAdapter(isAdapterHeadlines: Boolean) {
        if (isAdapterHeadlines) news_recycler_view.adapter = headlineNewsAdapter
        else news_recycler_view.adapter = newsByCategoryAdapter
    }
}