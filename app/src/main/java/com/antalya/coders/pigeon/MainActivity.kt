package com.antalya.coders.pigeon

import android.support.design.widget.TabLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.antalya.coders.pigeon.fragments.ChatListFragment
import com.antalya.coders.pigeon.utils.DummyCreator

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainActivity : AppCompatActivity() {

  private var mSectionsPagerAdapter: SectionsPagerAdapter? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    DummyCreator().create()

    NumberContainer.number = "+905309042476"

    setSupportActionBar(toolbar)

    mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

    container.adapter = mSectionsPagerAdapter

    container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
    tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }

  }


  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId

    if (id == R.id.action_settings) {
      return true
    }

    return super.onOptionsItemSelected(item)
  }

  inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
      return getFragment(position)
    }

    override fun getCount(): Int {
      return 3
    }

    private fun getFragment(position: Int): Fragment{

      return when(position){

        0 -> ChatListFragment()
        else -> PlaceholderFragment.newInstance(position + 1)

      }

    }

  }

  class PlaceholderFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
      val rootView = inflater.inflate(R.layout.fragment_main, container, false)
      rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
      return rootView
    }

    companion object {

      private val ARG_SECTION_NUMBER = "section_number"

      fun newInstance(sectionNumber: Int): PlaceholderFragment {
        val fragment = PlaceholderFragment()
        val args = Bundle()
        args.putInt(ARG_SECTION_NUMBER, sectionNumber)
        fragment.arguments = args
        return fragment
      }
    }
  }
}
