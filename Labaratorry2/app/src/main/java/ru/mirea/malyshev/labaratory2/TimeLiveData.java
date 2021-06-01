package ru.mirea.malyshev.labaratory2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Date;

class TimeLiveData {
    private static MutableLiveData<Long> data = new MutableLiveData<Long>();
    //sets latest time to LiveData
    static LiveData<Long> getTime(){
        data.setValue(new Date().getTime());
        return data;
    }
    static void setTime(){
        data.setValue(new Date().getTime());
    }
}
