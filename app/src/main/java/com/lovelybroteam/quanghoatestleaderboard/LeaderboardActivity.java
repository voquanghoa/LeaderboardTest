package com.lovelybroteam.quanghoatestleaderboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import java.util.Random;

/**
 * Created by Vo Quang Hoa on 2/7/2016.
 */
public class LeaderboardActivity extends Fragment {

    private String leaderBoardName;
    private String leaderBoardId;
    private View saveView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(saveView==null) {
            saveView = inflater.inflate(R.layout.leaderboard_test_layout, container, false);
            saveView.findViewById(R.id.bt_random).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    randomScore();
                }
            });
            saveView.findViewById(R.id.bt_submit).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    submitScore();
                }
            });

            saveView.findViewById(R.id.bt_show_leaderboard).setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    showLeaderboard();
                }
            });

            saveView.findViewById(R.id.bt_show_all_leaderboard).setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    showAllLeaderboard();
                }
            });
        }

        return saveView;
    }

    public void showAllLeaderboard(){
        GoogleApiClient apiClient = getApiClient();
        if(apiClient.isConnected()){
            startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(getApiClient()), 2);
        }else{
            showMessage("Not connected yet");
        }
    }

    public String toString(){
        return this.getLeaderBoardName();
    }

    public void showLeaderboard(){
        GoogleApiClient apiClient = getApiClient();
        if(apiClient.isConnected()){
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(getApiClient(), leaderBoardId), 2);
        }else{
            showMessage("Not connected yet");
        }
    }

    public void submitScore(){
        GoogleApiClient apiClient = getApiClient();
        if(apiClient.isConnected()){
            int score = 0;
            try{
                score = Integer.parseInt(((EditText)saveView.findViewById(R.id.txt_score)).getText().toString());
            }catch (Exception ex){
                showMessage(ex.getMessage());
                return;
            }
            Games.Leaderboards.submitScore(getGameActivity().getApiClient(), leaderBoardId,score);
        }else{
            showMessage("Not connected yet");
        }
    }

    private GoogleApiClient getApiClient(){
        return getGameActivity().getApiClient();
    }


    private BaseGameActivity getGameActivity(){
        return ((BaseGameActivity)getActivity());
    }

    public void randomScore(){
        ((EditText)saveView.findViewById(R.id.txt_score)).setText(new Random().nextInt()+"");
    }

    public String getLeaderBoardName() {
        return leaderBoardName;
    }

    public void setLeaderBoardName(String leaderBoardName) {
        this.leaderBoardName = leaderBoardName;
    }

    public String getLeaderBoardId() {
        return leaderBoardId;
    }

    public void setLeaderBoardId(String leaderBoardId) {
        this.leaderBoardId = leaderBoardId;
    }

    private void showMessage(String content){
        Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
    }
}
