package com.atifpervaiz.sqlitecontactphoneimagecrud_kotlin_atifpervaiz

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // dbHelper
    lateinit var dbHelper: MyDbHelper

    // orderBy / sort queries
    private val NEWEST_FIRST = "${Constants.C_ADDED_TIMESTAMP} DESC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init dbHelper
        dbHelper = MyDbHelper(this)

        loadRecords()

        // click FloatingActionButton to start AddUpdateRecordActivity
        addRecordBtn.setOnClickListener {
            startActivity(Intent(this, AddUpdateRecordActivity::class.java))
        }
    }

    private fun loadRecords() {
        val adapterRecord = AdapterRecord(this, dbHelper.getAllRecords(NEWEST_FIRST))

        recordRv.adapter = adapterRecord
    }

    private fun searchRecords(query:String) {
        val adapterRecord = AdapterRecord(this, dbHelper.searchRecords(query))

        recordRv.adapter = adapterRecord
    }

    override fun onResume() {
        super.onResume()
        loadRecords()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // inflate menu
        menuInflater.inflate(R.menu.menu_main, menu)

        // searchView
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                // search as you type
                if (newText != null){
                    searchRecords(newText)
                }

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // search when search button on keyboard is clicked
                if (query != null){
                    searchRecords(query)
                }

                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


}
