package my.com.toru.pagelibtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class Answer {
    @SerializedName("answer_id")
    public int answerId;

    @SerializedName("is_accepted")
    public boolean accepted;

    public int score;

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", accepted=" + accepted +
                ", score=" + score +
                '}';
    }
}