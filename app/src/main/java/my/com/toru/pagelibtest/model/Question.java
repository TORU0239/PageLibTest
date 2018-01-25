package my.com.toru.pagelibtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class Question {
    public String title;
    public String body;

    @SerializedName("question_id")
    public String questionId;

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", questionId='" + questionId + '\'' +
                '}';
    }
}