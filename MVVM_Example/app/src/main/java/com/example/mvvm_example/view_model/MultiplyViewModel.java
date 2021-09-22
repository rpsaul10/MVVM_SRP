package com.example.mvvm_example.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_example.model.MultiplyUseCase;

import java.util.LinkedList;

public class MultiplyViewModel extends ViewModel {
    private MutableLiveData<LinkedList<String>> history;
    private MutableLiveData<String> result;

    public MultiplyViewModel() {
        history = new MutableLiveData<>();
        result = new MutableLiveData<>();
    }

    public LiveData<LinkedList<String>> getHistory() {
        return history;
    }

    public LiveData<String> getResult() {
        return result;
    }

    public void doMultiply(String n1, String n2) {

        String res = MultiplyUseCase.doMultiply(n1, n2);
        String his_res = String.format("%s x %s = %s",n1, n2, res);
        result.setValue(res);
        LinkedList<String> tem_his = history.getValue();
        if (tem_his == null) {
            tem_his = new LinkedList<>();
        }
        tem_his.addFirst(his_res);
        history.setValue(tem_his);
    }
}
