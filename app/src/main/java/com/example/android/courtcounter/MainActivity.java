/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity keeps track of the basketball score for 2 teams.
 */
public class MainActivity extends AppCompatActivity {
    // Track - when the game stop (one team gets 3 points -> endGame = true)
    private boolean endGame = false;

    // Tracks the score for Team A
    private int scoreTeamA = 0;

    // Counts smashes for Team A
    private int smashesCounterTeamA = 0;

    // Counts blocks for Team A
    private int blocksCounterTeamA = 0;

    // Tracks the score for Team B
    private int scoreTeamB = 0;

    // Counts smashes for Team B
    private int smashesCounterTeamB = 0;

    // Counts blocks for Team B
    private int blocksCounterTeamB = 0;

    // shows actual set
    private int setActual = 1;

    // Tracks the score for Team A
    private int setScoreTeamA = 0;

    // Tracks the score for Team B
    private int setScoreTeamB = 0;

    private TextView teamAname = null;
    private TextView teamBname = null;

    static final String END_GAME = "endGame";
    static final String TEAM_A_SCORE = "scoreTeamA";
    static final String TEAM_B_SCORE = "scoreTeamB";
    static final String TEAM_A_SMASHES = "smashesCounterTeamA";
    static final String TEAM_B_SMASHES = "smashesCounterTeamB";
    static final String TEAM_A_BLOCKS = "blocksCounterTeamA";
    static final String TEAM_B_BLOCKS = "blocksCounterTeamB";
    static final String SET_ACTUAL = "setActual";
    static final String SET_SCORE_TEAM_A = "setScoreTeamA";
    static final String SET_SCORE_TEAM_B = "setScoreTeamB";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putBoolean(END_GAME, endGame);
        savedInstanceState.putInt(TEAM_A_SCORE, scoreTeamA);
        savedInstanceState.putInt(TEAM_B_SCORE, scoreTeamB);
        savedInstanceState.putInt(TEAM_A_SMASHES, smashesCounterTeamA);
        savedInstanceState.putInt(TEAM_B_SMASHES, smashesCounterTeamB);
        savedInstanceState.putInt(TEAM_A_BLOCKS, blocksCounterTeamA);
        savedInstanceState.putInt(TEAM_B_BLOCKS, blocksCounterTeamB);
        savedInstanceState.putInt(SET_ACTUAL, setActual);
        savedInstanceState.putInt(SET_SCORE_TEAM_A, setScoreTeamA);
        savedInstanceState.putInt(SET_SCORE_TEAM_B, setScoreTeamB);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        endGame = savedInstanceState.getBoolean(END_GAME);
        scoreTeamA = savedInstanceState.getInt(TEAM_A_SCORE);
        scoreTeamB = savedInstanceState.getInt(TEAM_B_SCORE);
        smashesCounterTeamA = savedInstanceState.getInt(TEAM_A_SMASHES);
        smashesCounterTeamB = savedInstanceState.getInt(TEAM_B_SMASHES);
        blocksCounterTeamA = savedInstanceState.getInt(TEAM_A_BLOCKS);
        blocksCounterTeamB = savedInstanceState.getInt(TEAM_B_BLOCKS);
        setScoreTeamA = savedInstanceState.getInt(SET_SCORE_TEAM_A);
        setScoreTeamB = savedInstanceState.getInt(SET_SCORE_TEAM_B);

        // display data again
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displaySmashesForTeamA(smashesCounterTeamA);
        displaySmashesForTeamB(smashesCounterTeamB);
        displayBlocksForTeamA(blocksCounterTeamA);
        displayBlocksForTeamB(blocksCounterTeamB);

        TextView TeamAsetScore = (TextView) findViewById(R.id.team_a_set_score);
        TeamAsetScore.setText(String.valueOf(setScoreTeamA));
        TextView TeamBsetScore = (TextView) findViewById(R.id.team_b_set_score);
        TeamBsetScore.setText(String.valueOf(setScoreTeamB));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change the hint Text in EditText View OnFocus and back
        teamAname = (TextView) findViewById(R.id.team_a_name);
        teamAname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    teamAname.setHint("Let's write");
                } else {
                    teamAname.setHint("Team_name_A");
                }
            }
        });

        // change another TextView TeamAname2 with new TeamAname

        teamAname.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                TextView teamAname2 = (TextView) findViewById(R.id.team_a_name2);
                teamAname2.setText(teamAname.getText());
                teamAname.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });

        // change the hint Text in EditText View OnFocus and back

        teamBname = (TextView) findViewById(R.id.team_b_name);
        teamBname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    teamBname.setHint("Let's write");
                } else {
                    teamBname.setHint("Team_name_B");
                }
            }
        });

        // change another TextView TeamBname2 with new TeamBname

        teamBname.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                TextView teamBname2 = (TextView) findViewById(R.id.team_b_name2);
                teamBname2.setText(teamBname.getText());
                teamBname.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });
    }

    /**
     * Decrease the score for Team A by 1 point.
     */
    public void addMinusOneTeamA(View v) {
        if (endGame) return;
        if (scoreTeamA > 0) {
            scoreTeamA--;
            displayForTeamA(scoreTeamA);
        }
    }

    /**
     * Increase the score for Team A by 1 points.
     * if it's set win point then   write down the setScore,
     * reset the TeamA and TeamB,
     * set new set :)
     */
    public void addPlusOneTeamA(View v) {
        if (endGame) return;
        if (scoreTeamA >= 24) {
            scoreTeamA++;
            displayForTeamA(scoreTeamA);
            if (scoreTeamA - scoreTeamB >= 2) {
                TextView teamAname = (TextView) findViewById(R.id.team_a_name);
                Toast.makeText(this, teamAname.getText().toString() + " WIN a set", Toast.LENGTH_LONG).show();
                addSetScore();
                if (endGame) return;
                resetScoreA(v);
                resetScoreB(v);
                setActual++;
            }
        } else {
            scoreTeamA++;
        }
        displayForTeamA(scoreTeamA);
    }

    /**
     * Decrease the score for Team B by 1 point if it's not 0.
     */
    public void addMinusOneTeamB(View v) {
        if (endGame) return;
        if (scoreTeamB > 0) {
            scoreTeamB--;
            displayForTeamB(scoreTeamB);
        }
    }

    /**
     * Increase the score for Team B by 1 points.
     * if it's set win point then   write down the setScore,
     * reset the TeamB and TeamA,
     * set new set :)
     */
    public void addPlusOneTeamB(View v) {
        if (endGame) return;
        if (scoreTeamB >= 24) {
            scoreTeamB++;
            displayForTeamB(scoreTeamB);
            if ((scoreTeamB - scoreTeamA) >= 2) {
                TextView teamBname = (TextView) findViewById(R.id.team_b_name);
                Toast.makeText(this, teamBname.getText().toString() + " WIN a set", Toast.LENGTH_LONG).show();
                addSetScore();
                if (endGame) return;
                resetScoreA(v);
                resetScoreB(v);
                setActual++;
            }
        } else {
            scoreTeamB++;
        }
        displayForTeamB(scoreTeamB);
    }

    /**
     * Decrease the smashes counter for Team A by 1 point if it's not 0.
     */
    public void addMinusOneSmashTeamA(View v) {
        if (endGame) return;
        if (smashesCounterTeamA > 0) {
            smashesCounterTeamA--;
            displaySmashesForTeamA(smashesCounterTeamA);
        }
    }

    /**
     * Increase the smashes counter for Team A by 1 .
     */
    public void addPlusOneSmashTeamA(View v) {
        if (endGame) return;
        smashesCounterTeamA++;
        displaySmashesForTeamA(smashesCounterTeamA);
    }

    /**
     * Decrease the smashes counter for Team B by 1 point if it's not 0.
     */
    public void addMinusOneSmashTeamB(View v) {
        if (endGame) return;
        if (smashesCounterTeamB > 0) {
            smashesCounterTeamB--;
            displaySmashesForTeamB(smashesCounterTeamB);
        }
    }

    /**
     * Increase the smashes counter for Team A by 1 .
     */
    public void addPlusOneSmashTeamB(View v) {
        if (endGame) return;
        smashesCounterTeamB++;
        displaySmashesForTeamB(smashesCounterTeamB);
    }

    /**
     * Decrease the blocks counter for Team A by 1 point if it's not 0.
     */
    public void addMinusOneBlockTeamA(View v) {
        if (endGame) return;
        if (blocksCounterTeamA > 0) {
            blocksCounterTeamA--;
            displayBlocksForTeamA(blocksCounterTeamA);
        }
    }

    /**
     * Increase the smashes counter for Team A by 1 .
     */
    public void addPlusOneBlockTeamA(View v) {
        if (endGame) return;
        blocksCounterTeamA++;
        displayBlocksForTeamA(blocksCounterTeamA);
    }

    /**
     * Decrease the blocks counter for Team B by 1 point if it's not 0.
     */
    public void addMinusOneBlockTeamB(View v) {
        if (endGame) return;
        if (blocksCounterTeamB > 0) {
            blocksCounterTeamB--;
            displayBlocksForTeamB(blocksCounterTeamB);
        }
    }

    /**
     * Increase the smashes counter for Team A by 1 .
     */
    public void addPlusOneBlockTeamB(View v) {
        if (endGame) return;
        blocksCounterTeamB++;
        displayBlocksForTeamB(blocksCounterTeamB);
    }

    /**
     * Resets the score, smashes and blocks counters for teamA back to 0.
     */
    public void resetScoreA(View v) {
        scoreTeamA = 0;
        displayForTeamA(scoreTeamA);
        if (endGame) smashesCounterTeamA = 0;
        displaySmashesForTeamA(smashesCounterTeamA);
        if (endGame) blocksCounterTeamA = 0;
        displayBlocksForTeamA(blocksCounterTeamA);
    }

    /**
     * Resets the score, smashes and blocks counters for teamB back to 0.
     */
    public void resetScoreB(View v) {
        scoreTeamB = 0;
        displayForTeamB(scoreTeamB);
        if (endGame) smashesCounterTeamB = 0;
        displaySmashesForTeamB(smashesCounterTeamB);
        if (endGame) blocksCounterTeamB = 0;
        displayBlocksForTeamB(blocksCounterTeamB);
    }

    /**
     * Resets both teams score back to 0 and reset all setScores
     * and set active set to 1
     */
    public void resetAll(View v) {
        endGame = true;
        TextView setScore;
        resetScoreA(v);
        resetScoreB(v);
        //  reset set scores
        setScore = (TextView) findViewById(R.id.team_a_set_1);
        setScore.setText("-");
        setScore = (TextView) findViewById(R.id.team_b_set_1);
        setScore.setText("-");
        setScore = (TextView) findViewById(R.id.team_a_set_2);
        setScore.setText("-");
        setScore = (TextView) findViewById(R.id.team_b_set_2);
        setScore.setText("-");
        setScore = (TextView) findViewById(R.id.team_a_set_3);
        setScore.setText("-");
        setScore = (TextView) findViewById(R.id.team_b_set_3);
        setScore.setText("-");
        setScore = (TextView) findViewById(R.id.team_a_set_4);
        setScore.setText("-");
        setScore = (TextView) findViewById(R.id.team_b_set_4);
        setScore.setText("-");
        setScore = (TextView) findViewById(R.id.team_a_set_5);
        setScore.setText("-");
        setScore = (TextView) findViewById(R.id.team_b_set_5);
        setScore.setText("-");
        //  reset set number
        setActual = 1;
        //  new game is running
        endGame = false;
        // new start 0:0
        TextView TeamAsetScore = (TextView) findViewById(R.id.team_a_set_score);
        TeamAsetScore.setText("0");
        setScoreTeamA = 0;
        TextView TeamBsetScore = (TextView) findViewById(R.id.team_b_set_score);
        TeamBsetScore.setText("0");
        setScoreTeamB = 0;
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays number of smashes for Team A.
     */
    public void displaySmashesForTeamA(int smashesCounterTeamA) {
        TextView smashesView = (TextView) findViewById(R.id.team_a_smashes);
        smashesView.setText(String.valueOf(smashesCounterTeamA));
    }

    /**
     * Displays number of smashes for Team B.
     */
    public void displaySmashesForTeamB(int smashesCounterTeamB) {
        TextView smashesView = (TextView) findViewById(R.id.team_b_smashes);
        smashesView.setText(String.valueOf(smashesCounterTeamB));
    }

    /**
     * Displays number of blocks for Team A.
     */
    public void displayBlocksForTeamA(int blocksCounterTeamA) {
        TextView blocksView = (TextView) findViewById(R.id.team_a_blocks);
        blocksView.setText(String.valueOf(blocksCounterTeamA));
    }

    /**
     * Displays number of blocks for Team B.
     */
    public void displayBlocksForTeamB(int blocksCounterTeamB) {
        TextView blocksView = (TextView) findViewById(R.id.team_b_blocks);
        blocksView.setText(String.valueOf(blocksCounterTeamB));
    }

    /**
     * Displays set result - score
     */
    public void addSetScore() {
        int idA = 0;
        int idB = 0;

        switch (setActual) {
            case 1:
                idA = R.id.team_a_set_1;
                idB = R.id.team_b_set_1;
                break;
            case 2:
                idA = R.id.team_a_set_2;
                idB = R.id.team_b_set_2;
                break;
            case 3:
                idA = R.id.team_a_set_3;
                idB = R.id.team_b_set_3;
                break;
            case 4:
                idA = R.id.team_a_set_4;
                idB = R.id.team_b_set_4;
                break;
            case 5:
                idA = R.id.team_a_set_5;
                idB = R.id.team_b_set_5;
        }

        TextView TeamAsetScore = (TextView) findViewById(idA);
        TextView TeamBsetScore = (TextView) findViewById(idB);

        TeamAsetScore.setText(String.valueOf(scoreTeamA));
        TeamBsetScore.setText(String.valueOf(scoreTeamB));

        // update setScore TextView
        if (scoreTeamA > scoreTeamB) {
            TeamAsetScore = (TextView) findViewById(R.id.team_a_set_score);
            TeamAsetScore.setText(String.valueOf(++setScoreTeamA));
        } else {
            TeamBsetScore = (TextView) findViewById(R.id.team_b_set_score);
            TeamBsetScore.setText(String.valueOf(++setScoreTeamB));
        }

        // end game checking
        if (setScoreTeamA == 3) {
            // get the teamA name and show WIN message
            String toastMessage = "And they are the winners.";
            Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
            endGame = true;
        }

        if (setScoreTeamB == 3) {
            // get the teamB name and show WIN message
            String toastMessage = "And they are the winners.";
            Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
            endGame = true;
        }
    }
}
