package pl.lodz.apzumi.fragments.descryption

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_descryption.*
import pl.lodz.apzumi.R

class DescryptionFragment : Fragment(), Descryption.View {

    private val args: DescryptionFragmentArgs by navArgs()
    private val presenter: Descryption.Presenter by lazy {
        DescryptionPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_descryption, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreated(args.ReposytoryInformation)
    }

    override fun setUserName(name: String) {
        userNameInput.text = name
    }

    override fun setReposytoryName(name: String) {
        reposytoryNameInput.text = name
    }

    override fun setDescryption(descryption: String) {
        reposytoryDescryption.text = descryption
    }

    override fun setAvatar(url: String) {
        Glide.with(this).load(url).fitCenter()
            .placeholder(R.drawable.placeholder)
            .into(imageView2)
    }
}