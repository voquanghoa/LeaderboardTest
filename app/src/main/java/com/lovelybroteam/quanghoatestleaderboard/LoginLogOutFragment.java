package com.lovelybroteam.quanghoatestleaderboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by voqua on 2/22/2016.
 */
public class LoginLogOutFragment extends Fragment implements View.OnClickListener {
    private View saveView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(saveView==null) {
            saveView = inflater.inflate(R.layout.login_layout, container, false);

            saveView.findViewById(R.id.sign_in_button).setOnClickListener(this);
            saveView.findViewById(R.id.sign_out_button).setOnClickListener(this);
        }
        updateState();
        return saveView;
    }

    public void updateState(){
        if(!getGameActivity().getApiClient().isConnected()){
            saveView.findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            saveView.findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        }else{
            saveView.findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            saveView.findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button) {
            getGameActivity(). beginUserInitiatedSignIn();
        }
        else if (view.getId() == R.id.sign_out_button) {
            getGameActivity().signOut();
        }
        updateState();
    }

    private BaseGameActivity getGameActivity(){
        return ((BaseGameActivity)getActivity());
    }

    @Override
    public String toString() {
        return "Login/Logout";
    }
}
