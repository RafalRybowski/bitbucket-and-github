package pl.lodz.apzumi.fragments.list

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import pl.lodz.apzumi.R
import pl.lodz.apzumi.model.ReposytoryInformation
import pl.lodz.apzumi.navigation.getNavController
import pl.lodz.apzumi.utils.adapter.RepositoryAdapter

class ReposytoryListFragment : Fragment(), ReposytoryList.View {

    private val presenter: ReposytoryList.Presenter by lazy {
        ReposytoryListPresenter(
            this,
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )
    }

    private val listAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
        switchSort.setOnCheckedChangeListener { _, isChecked ->
            presenter.onSwitchChange(isChecked)
        }
        presenter.onViewCreated()
    }

    override fun setRecyclerView(list: List<ReposytoryInformation>) {
        listAdapter.notifyItemRangeChanged(0, listAdapter.itemCount)
        listAdapter.setReposytoryList(list) { reposytory ->
            requireActivity().getNavController().navigate(
                ReposytoryListFragmentDirections.actionListFragmentToDescryptionFragment(reposytory)
            )
        }
        listAdapter.notifyDataSetChanged()
    }

    override fun unBlockSwitch() {
        switchSort.isClickable = true
    }

    override fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }
}