package `in`.live.tamasha

import `in`.live.tamasha.adapter.EmployeeListAdapter
import `in`.live.tamasha.databinding.ActivityMainBinding
import `in`.live.tamasha.models.EmployeeModel
import `in`.live.tamasha.models.GeneralNetworkResponse
import `in`.live.tamasha.viewmodel.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private val employeeListAdapter: EmployeeListAdapter by lazy { EmployeeListAdapter() }
    private val linearLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this) }
    private var recyclerView: RecyclerView? = null

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpInitialViews()
        setUpRequiredObserver()
        getEmployeeList()
    }

    private fun setUpInitialViews() {
        recyclerView = binding?.recyclerView
        recyclerView?.apply {
            adapter = employeeListAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun setUpRequiredObserver() {
        mainViewModel.apply {
            employeeResponseLiveData.observe(this@MainActivity) {
                parseNetworkResponse(it)
            }
        }
    }

    private fun getEmployeeList() {
        mainViewModel.getEmployeeListFromServer()
        showHidePgBar(true)
    }

    private fun parseNetworkResponse(generalNetworkResponse: GeneralNetworkResponse?) {
        showHidePgBar(false)
        when (generalNetworkResponse) {
            is GeneralNetworkResponse.Success<*> -> {
                val successValue = generalNetworkResponse.successResponse
                if (successValue is EmployeeModel) {
                    val data = successValue.data
                    if (data != null) {
                        employeeListAdapter.submitList(data)
                    }
                }
            }
            is GeneralNetworkResponse.ErrorMessage -> {
                val errorMessage = generalNetworkResponse.value
                if (!errorMessage.isNullOrEmpty()) {
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
                //go for local data source
            }
            else -> {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    private fun showHidePgBar(canShow: Boolean) {
        if (canShow) {
            binding?.progresBar?.visibility = View.VISIBLE
        } else {
            binding?.progresBar?.visibility = View.GONE
        }
    }

}