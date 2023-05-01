package geekbrians.serg.ui.fragments.earth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import geekbrians.serg.R
import geekbrians.serg.TimberLogger

class EarthDashboardChildFragmentB : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        lifecycle.addObserver(TimberLogger(this))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard_child, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvDashBoard = view.findViewById<TextView>(R.id.tv_dashboard)
        val name = requireArguments().getString(ARGUMENT_NAME)
        Log.d("[MYLOG]", "EarthDashboardChildFragmentB")
        tvDashBoard.setText(R.string.life_is_a_very_funny_proposition_after_all)
        tvDashBoard.append("\n")
        tvDashBoard.append("\n")
        tvDashBoard.append("\n")
        tvDashBoard.append(name)
    }

    companion object {
        private const val ARGUMENT_NAME = "argument_name"
        fun newInstance(name: String?): EarthDashboardChildFragmentB {
            val args = Bundle()
            args.putString(ARGUMENT_NAME, name)
            val fragment = EarthDashboardChildFragmentB()
            fragment.arguments = args
            return fragment
        }
    }
}