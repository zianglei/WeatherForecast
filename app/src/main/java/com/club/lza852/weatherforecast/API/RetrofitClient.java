package com.club.lza852.weatherforecast.API;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ziang on 2017/1/16.
 */

public class RetrofitClient {

    private static String BASE_URL = "https://api.caiyunapp.com";
    //彩云天气预报token
    private static String wftoken = "E-4s4HJLOrf3EcWR";

    //获取天气预报信息
    public static void getForecastInfo(String cityLocation, Callback<CaiYunForecastModel> mCallback){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        CaiYunWeatherInterface.mForecastInterface mForecastAPI = retrofit.create(CaiYunWeatherInterface.mForecastInterface.class);

        Call<CaiYunForecastModel> mForecastCall = mForecastAPI.getCaiYunForecastInfo(wftoken,cityLocation);

        if (mCallback==null) {
            mCallback = new Callback<CaiYunForecastModel>() {
                @Override
                public void onResponse(Call<CaiYunForecastModel> call, Response<CaiYunForecastModel> response) {
                    if (response.isSuccessful()) {
                        CaiYunForecastModel mCaiYunForecastModel = response.body();
                        Log.d("ForecastInfo", mCaiYunForecastModel.toString());
                    }
                }

                @Override
                public void onFailure(Call<CaiYunForecastModel> call, Throwable t) {
                    t.printStackTrace();
                }
            };
        }

        mForecastCall.enqueue(mCallback);
    }

    //获取实时天气信息
    public static void getRealTimeInfo(String cityLocation,Callback<CaiYunRealTimeModel> mCallback){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final CaiYunWeatherInterface.mRealTimeInterface mRealTimeAPI = retrofit.create(CaiYunWeatherInterface.mRealTimeInterface.class);

        Call<CaiYunRealTimeModel> mRealTimeCall = mRealTimeAPI.getCaiYunRealTimeInfo(wftoken,cityLocation);

        if (mCallback==null){
            mCallback = new Callback<CaiYunRealTimeModel>() {
                @Override
                public void onResponse(Call<CaiYunRealTimeModel> call, Response<CaiYunRealTimeModel> response) {
                    if (response.isSuccessful()){
                        CaiYunRealTimeModel mRealTimeList = response.body();
                        Log.d("RealTimeInfo",mRealTimeList.toString());
                    }
                }

                @Override
                public void onFailure(Call<CaiYunRealTimeModel> call, Throwable t) {
                    t.printStackTrace();
                }
            };
        }
        mRealTimeCall.enqueue(mCallback);
    }


}
