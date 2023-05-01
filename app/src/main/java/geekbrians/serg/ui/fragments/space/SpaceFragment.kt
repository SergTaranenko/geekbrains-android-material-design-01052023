package geekbrians.serg.ui.fragments.space

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import geekbrians.serg.R
import geekbrians.serg.TimberLogger

class SpaceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        lifecycle.addObserver(TimberLogger(this))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message, menu)
    }

    companion object {
        fun newInstance(): SpaceFragment {
            return SpaceFragment()
        }
    }
}