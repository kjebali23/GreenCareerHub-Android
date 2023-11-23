package tn.esprit.gestionevenement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.gestionevenement.network.RetrofitClient
import tn.esprit.gestionevenement.ui.fragments.ConfFragment



class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)

        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
    }
}

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        return ConfFragment().apply {
            fetchDataFromApi()
        }
    }
}






