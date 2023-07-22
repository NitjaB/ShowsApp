package infinuma.android.shows.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ActivityShowsBinding
import infinuma.android.shows.login.LoginFragmentDirections
import infinuma.android.shows.login.domain.LoginRepository
import infinuma.android.shows.logout.LogoutBottomSheetDialog
import infinuma.android.shows.logout.model.LogoutBottomSheetDialogUi
import infinuma.android.shows.shows.adapter.ShowsAdapter
import infinuma.android.shows.shows.data.ShowsRepository
import infinuma.android.shows.utils.SharedPrefsSource

class ShowsFragment : Fragment() {

    private lateinit var binding: ActivityShowsBinding

    private val repository = ShowsRepository()

    private val loginRepository = LoginRepository(SharedPrefsSource.getSharedPrefs())

    private val adapter = ShowsAdapter(arrayListOf()) {
        val action = ShowsFragmentDirections.actionShowsFragmentToShowDetailsFragment(it.id)
        findNavController().navigate(action)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityShowsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profilePictureImageView.setOnClickListener {
            LogoutBottomSheetDialog(
                LogoutBottomSheetDialogUi(
                    "Nitja", R.drawable.ic_profile_picture
                ),
                {
                    loginRepository.setRememberedUser(false)
                    findNavController().navigate(LoginFragmentDirections.actionGlobalLoginFragment())
                },
                {},
                requireContext()
            ).show()
        }
        setUpRecyclerView()
        setUpToggleButton()
    }

    private fun setUpRecyclerView() {
        binding.showsRecyclerView.apply {
            val manager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(this.context, manager.orientation).apply {
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.show_divider)
                if (drawable != null) {
                    setDrawable(drawable)
                }
            }
            this.layoutManager = manager
            this.adapter = this@ShowsFragment.adapter
            this.addItemDecoration(decoration)
        }
        adapter.addShows(repository.getShows(requireContext()))
    }

    private fun setUpToggleButton() {
        binding.toggleShowList.setOnClickListener {
            if (adapter.getShows().isEmpty()) {
                adapter.addShows(repository.getShows(requireContext()))
                binding.toggleShowList.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_trash_can)
                hideNoShowViews()
            } else {
                adapter.deleteShows()
                binding.toggleShowList.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_add)
                showNoShowsViews()
            }
        }
    }

    private fun showNoShowsViews() {
        binding.noShowsImageView.visibility = View.VISIBLE
        binding.noShowsTextView.visibility = View.VISIBLE
    }

    private fun hideNoShowViews() {
        binding.noShowsImageView.visibility = View.GONE
        binding.noShowsTextView.visibility = View.GONE
    }
}