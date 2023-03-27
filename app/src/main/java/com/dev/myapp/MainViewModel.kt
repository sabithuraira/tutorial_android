package com.dev.myapp

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.dev.myapp.api.ApiConfig
import com.dev.myapp.fragment_api.FormFragmentDirections
import com.dev.myapp.fragment_api.TodoAdapter
import com.dev.myapp.models.ResponseDeleteTodo
import com.dev.myapp.models.ResponseStoreTodo
import com.dev.myapp.models.ResponseTodo
import com.dev.myapp.models.Todo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    var result = 0

    fun multiply(data: Int){
        result = data * data
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listTodo = MutableLiveData<List<Todo>?>()
    val listTodo: LiveData<List<Todo>?> = _listTodo

    private val _todo = MutableLiveData<Todo?>()
    val todo: LiveData<Todo?> = _todo

    private val _listError = MutableLiveData<List<String>?>()
    val listError: LiveData<List<String>?> = _listError

    private val _isFinish = MutableLiveData<Boolean>()
    val isFinish: LiveData<Boolean> = _isLoading

    fun setIsLoading(data: Boolean){
        _isLoading.value = data
    }

    fun setTargetTodo(data: Todo?){
        _todo.value =data
    }

    fun loadDatas(){
        setIsLoading(true)
        val client = ApiConfig.getApiService().getTodos()

        client.enqueue(object: Callback<ResponseTodo> {
            override fun onFailure(call: retrofit2.Call<ResponseTodo>, t: Throwable) {
                setIsLoading(false)
                Log.e("error", t.message!!)
            }

            override fun onResponse(
                call: retrofit2.Call<ResponseTodo>,
                response: Response<ResponseTodo>
            ) {
                if(response.body()===null){
                    Log.e("error", "Your data API is empty")
                }
                else{
                    _listTodo.value = response.body()?.result?.data
                }
                setIsLoading(false)
            }
        })
    }

//    private fun saveData(view: View){
    fun saveData(title: String, description: String){
        setIsLoading(true)
        _listError.value = listOf()
        _isFinish.value = false

        var errors = mutableListOf<String>()

        val jsonObject = JSONObject()
        jsonObject.put("title", title)
        jsonObject.put("description", description)

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        if(todo.value==null){
            val client = ApiConfig.getApiService().storeTodo(requestBody)

            client.enqueue(object: Callback<ResponseStoreTodo> {
                override fun onFailure(call: Call<ResponseStoreTodo>, t: Throwable) {
                    Log.e("error", t.message!!.toString())
                    errors.add(t.message!!.toString())
                    _listError.value = errors
                    _isFinish.value = true

                    setIsLoading(false)
                }

                override fun onResponse(
                    call: Call<ResponseStoreTodo>,
                    response: Response<ResponseStoreTodo>
                ) {
                    setIsLoading(false)
                    _listError.value = errors
                    _isFinish.value = true
                }
            })
        }
        else{
            val client = ApiConfig.getApiService().updateTodo(todo.value!!.encId!!, requestBody)

            client.enqueue(object: Callback<ResponseStoreTodo> {
                override fun onFailure(call: Call<ResponseStoreTodo>, t: Throwable) {
                    setIsLoading(false)
                    Log.e("error", t.message!!.toString())
                    errors.add(t.message!!.toString())
                    _listError.value = errors
                    _isFinish.value = true
                }

                override fun onResponse(
                    call: Call<ResponseStoreTodo>,
                    response: Response<ResponseStoreTodo>
                ) {
                    setIsLoading(false)
                    _listError.value = errors
                    _isFinish.value = true
                }
            })
        }
    }

    fun deleteData(){
        setIsLoading(true)
        _listError.value = listOf()
        _isFinish.value = false
        var errors = mutableListOf<String>()

        val client = ApiConfig.getApiService().deleteToto(todo.value!!.encId!!)

        client.enqueue(object: Callback<ResponseDeleteTodo> {
            override fun onFailure(call: Call<ResponseDeleteTodo>, t: Throwable) {
                setIsLoading(false)
                Log.e("error", t.message!!.toString())
                errors.add(t.message!!.toString())
                _listError.value = errors
                _isFinish.value = true
            }

            override fun onResponse(
                call: Call<ResponseDeleteTodo>,
                response: Response<ResponseDeleteTodo>
            ) {
                setIsLoading(false)
                _isFinish.value = true
            }
        })
    }
}