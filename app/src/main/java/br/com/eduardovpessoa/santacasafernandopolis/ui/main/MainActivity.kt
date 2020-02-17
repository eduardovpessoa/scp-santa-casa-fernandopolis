package br.com.eduardovpessoa.santacasafernandopolis.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import br.com.eduardovpessoa.santacasafernandopolis.R
import br.com.eduardovpessoa.santacasafernandopolis.ui.login.LoginActivity
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.bed.BedFragment
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.classification.ClassificationFragment
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.home.HomeFragment
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.new_classification.NewClassificationFragment
import br.com.eduardovpessoa.santacasafernandopolis.ui.main.unity.UnityFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainContract.View, MainAdapterContract.BedAdapter, MainAdapterContract.ClassificationAdapter,
    MainAdapterContract.UnityAdapter {

    private var presenter: MainContract.Presenter? = null
    private lateinit var fragment: Fragment
    private var doubleBackToExitPressedOnce: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this)
    }

    override fun initViews() {
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        title = "SCP - Início"
        replaceFragment(HomeFragment())
    }

    override fun returnToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        val fm: FragmentManager = supportFragmentManager
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (fm.backStackEntryCount > 0) {
                fm.popBackStack()
            } else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed()
                    return
                } else {
                    doubleBackToExitPressedOnce = true
                    Toast.makeText(this, "Pressione novamente para sair...", Toast.LENGTH_SHORT)
                        .show()
                    Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                title = "SCP - Início"
                fragment = HomeFragment()
                replaceFragment(fragment)
            }
            R.id.nav_classification -> {
                title = "SCP - Unidades"
                fragment = UnityFragment()
                replaceFragment(fragment)
            }
            R.id.nav_info -> {
                Toast.makeText(this, "Créditos...", Toast.LENGTH_LONG).show()
            }
            R.id.nav_exit -> {
                presenter?.logout()
                returnToLogin()
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main, fragment)
        transaction.addToBackStack(fragment::class.java.name)
        transaction.commit()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun onClickBed(idUnity: String?, idBed: String?, nameBed: String?) {
        title = "SCP - Histórico $nameBed"
        replaceFragment(ClassificationFragment.newInstance(idUnity, idBed))
    }

    override fun onClickClassification(
        idUnity: String?,
        idBed: String?,
        idClassification: String?,
        dateClassification: Long?
    ) {
        /*val sdf: String = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale("pt", "BR")
        ).format(dateClassification?.let { Date(it).toString() })*/
        title = "SCP - Class. 17/02/2020"
        replaceFragment(
            NewClassificationFragment.newInstance(
                idUnity,
                idBed,
                idClassification.toString()
            )
        )
    }

    override fun onClickUnity(idUnity: String?, nameUnity: String?) {
        title = "SCP - $nameUnity"
        replaceFragment(BedFragment.newInstance(idUnity, nameUnity))
    }
}
