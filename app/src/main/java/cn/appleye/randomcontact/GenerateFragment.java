package cn.appleye.randomcontact;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by iSpace on 2016/3/19.
 */
public class GenerateFragment extends Fragment implements Handler.Callback {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflateView(inflater, container);
    }

    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.activity_generate_contacts, null);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
