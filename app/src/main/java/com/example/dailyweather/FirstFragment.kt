package com.example.dailyweather

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dailyweather.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.log


class FirstFragment : Fragment() {



 private lateinit var binding: FragmentFirstBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)
        SearchCity()
        fetchWeatherData("Delhi") // You can change "Delhi" to any default city you want to display initially
    }



    private fun SearchCity() {
        val searchView= binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    fetchWeatherData(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    fetchWeatherData(newText)
                }
                return true
            }

        })
    }

    private fun fetchWeatherData(cityName: String){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(ApiInterface ::class.java)

        val response = retrofit.getWeatherData(cityName, "13176c001cf7c033212df14e39d59c3d", "metric")
        response.enqueue(object : Callback<DailyWeather>{
            override fun onResponse(call: Call<DailyWeather>, response: Response<DailyWeather>) {
                var responseBody = response.body()
                Log.d("TAG", "onResponse: ${response.body()}")
                if(response.isSuccessful && responseBody != null)
                {
                    val temperature = responseBody.main.temp.toString()
                    val humidity = responseBody.main.humidity
                    val windSpeed = responseBody.wind.speed
                    val sunrise = responseBody.sys.sunrise.toLong()
                    val sunset = responseBody.sys.sunset.toLong()
                    val seaLevel = responseBody.main.pressure
                    val condition = responseBody.weather.firstOrNull()?.main?: "unknown"
                    val maxTemp = responseBody.main.temp_max
                    val minTemp = responseBody.main.temp_min

                    binding.temp.text = "$temperature ºC"
                    binding.weather.text = condition
                    binding.maxTemp.text = "Max Temp: $maxTemp"
                    binding.minTemp.text = "Min Temp: $minTemp"
                    binding.humidity.text = "$humidity %"
                    binding.wind.text = "$windSpeed m/s"
                    binding.sunrise.text = "${time(sunrise)}"
                    binding.sunset.text = "${time(sunset)}"
                    binding.sea.text = "$seaLevel hPa"
                    binding.condition.text = condition

                    binding.day.text = dayName(System.currentTimeMillis())
                    binding.date.text = date()
                    binding.cityName.text = "$cityName"

                    changeBackground(condition)
                }
            }

            override fun onFailure(call: Call<DailyWeather>, t: Throwable) {

            }
        })

    }

    private fun changeBackground(conditions : String) {
        Log.d("TAG", "changeBackground: $conditions")
        when(conditions.uppercase()){

            "CLEAR SKY" -> {
                binding.root.setBackgroundResource(R.drawable.sunny_background)
                binding.lottieAnimationView.setAnimation(R.raw.sun)
            }

            "SUNNY"-> {
                binding.root.setBackgroundResource(R.drawable.sunny_background)
                binding.lottieAnimationView.setAnimation(R.raw.sun)
            }

            "CLEAR" -> {
                binding.root.setBackgroundResource(R.drawable.sunny_background)
                binding.lottieAnimationView.setAnimation(R.raw.sun)
            }

            "CLOUDS" -> {
                binding.root.setBackgroundResource(R.drawable.cloud_background)
                binding.lottieAnimationView.setAnimation(R.raw.cloud)
            }

            "PARTLY CLOUDS" -> {
                binding.root.setBackgroundResource(R.drawable.cloud_background)
                binding.lottieAnimationView.setAnimation(R.raw.cloud)
            }

            "OVERCAST" -> {
                binding.root.setBackgroundResource(R.drawable.cloud_background)
                binding.lottieAnimationView.setAnimation(R.raw.cloud)
            }

            "MIST" -> {
                binding.root.setBackgroundResource(R.drawable.cloud_background)
                binding.lottieAnimationView.setAnimation(R.raw.cloud)
            }
            "FOGGY"-> {
                binding.root.setBackgroundResource(R.drawable.cloud_background)
                binding.lottieAnimationView.setAnimation(R.raw.cloud)
            }

            "HAZE" -> {
                binding.root.setBackgroundResource(R.drawable.cloud_background)
                binding.lottieAnimationView.setAnimation(R.raw.haze)
            }

            "LIGHT RAIN" -> {
                binding.root.setBackgroundResource(R.drawable.rain_background)
                binding.lottieAnimationView.setAnimation(R.raw.rain)
            }

            "DRIZZLE" -> {
                binding.root.setBackgroundResource(R.drawable.rain_background)
                binding.lottieAnimationView.setAnimation(R.raw.rain)
            }

            "MODERATE RAIN"-> {
                binding.root.setBackgroundResource(R.drawable.rain_background)
                binding.lottieAnimationView.setAnimation(R.raw.rain)
            }

            "SHOWERS" -> {
                binding.root.setBackgroundResource(R.drawable.rain_background)
                binding.lottieAnimationView.setAnimation(R.raw.rain)
            }

            "RAIN" -> {
                binding.root.setBackgroundResource(R.drawable.rain_background)
                binding.lottieAnimationView.setAnimation(R.raw.rain)
            }

            "HEAVY RAIN" -> {
                binding.root.setBackgroundResource(R.drawable.rain_background)
                binding.lottieAnimationView.setAnimation(R.raw.rain)
            }

            "LIGHT SNOW" -> {
                binding.root.setBackgroundResource(R.drawable.snow_background)
                binding.lottieAnimationView.setAnimation(R.raw.snow)
            }

            "SNOW" -> {
                binding.root.setBackgroundResource(R.drawable.snow_background)
                binding.lottieAnimationView.setAnimation(R.raw.snow)
            }

            "MODERATE SNOW" -> {
                binding.root.setBackgroundResource(R.drawable.snow_background)
                binding.lottieAnimationView.setAnimation(R.raw.snow)
            }

            "HEAVY SNOW" -> {
                binding.root.setBackgroundResource(R.drawable.snow_background)
                binding.lottieAnimationView.setAnimation(R.raw.snow)
            }

            "BLIZZARD" -> {
                binding.root.setBackgroundResource(R.drawable.snow_background)
                binding.lottieAnimationView.setAnimation(R.raw.snow)
            }

            else -> {
                binding.root.setBackgroundResource(R.drawable.sunny_background)
                binding.lottieAnimationView.setAnimation(R.raw.sun)
            }

        }

        binding.lottieAnimationView.playAnimation()
    }

    private fun date(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun time(timeStamp : Long): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date(timeStamp*1000))
    }

    fun dayName(timeStamp : Long) : String{
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        return sdf.format(Date())
    }
}
