package com.kantar.sessionsjob.model;


import lombok.Getter;

@Getter
public enum Activity {
    LIVE("Live"),
    PLAYBACK("PlayBack");

    private String activityText;

    Activity(String activityText) {
        this.activityText = activityText;
    }

    public static Activity getActivityByText(String activityText) {
        for (Activity activity : Activity.values()) {
            if (activity.activityText.equals(activityText)) {
                return activity;
            }
        }
        throw new IllegalArgumentException("No activity for string: " + activityText);
    }
}
