package geekbrians.serg.ui.mainactivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import geekbrians.serg.R
import geekbrians.serg.ui.mainactivity.listeners.BottomNavItemSelectedListener
import geekbrians.serg.ui.mainactivity.listeners.SideNavItemSelectedListener
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var navigation: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var fab: FloatingActionButton

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            showSearch()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSearch() {
        Snackbar.make(navigation, "Go to Search", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        appBarLayout = findViewById(R.id.appBarLayout)
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout)
        fab = findViewById(R.id.fab)

        collapsingToolbarLayout.setTitle(" ")
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))

        appBarLayout.addOnOffsetChangedListener(object: AppBarLayout.OnOffsetChangedListener {
            var isShown = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    // Collapsed
                    collapsingToolbarLayout.setTitle("Title")
                    if (isShown) {
                        isShown = false
                        ViewCompat.animate(fab)
                            .scaleX(0.0f).scaleY(0.0f)
                            .alpha(0.0f)
                            .setDuration(500)
                            .start()
                    }
                } else {
                    // Expanded
                    collapsingToolbarLayout.setTitle(" ")
                    if (!isShown) {
                        isShown = true
                        ViewCompat.animate(fab)
                            .scaleX(1.0f).scaleY(1.0f)
                            .alpha(1.0f)
                            .setDuration(500)
                            .start()
                    }
                }
            }
        })

        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val adapter = AppFragmentPageAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = adapter.count - 1

        navigation = findViewById(R.id.navigation)
        val listener = BottomNavItemSelectedListener(viewPager, toolbar)
        navigation.setOnNavigationItemSelectedListener(listener)

        bindNavigationDrawer
        initTitle()
    }

    private fun initTitle() {
        toolbar.post { toolbar.title = navigation.menu.getItem(0).title }
    }

    private fun bindNavigationDrawer() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val listener = SideNavItemSelectedListener(drawer, navigation)
        navigationView.setNavigationItemSelectedListener(listener)
    }

    fun onFabClicked(view: View?) {
        Snackbar.make(navigation, "Fab Share", Snackbar.LENGTH_SHORT).show()
    }
}
