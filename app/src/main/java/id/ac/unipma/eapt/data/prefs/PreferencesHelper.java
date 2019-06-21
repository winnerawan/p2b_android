package id.ac.unipma.eapt.data.prefs;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface PreferencesHelper {

    boolean isLoggedIn();
    void setLoggedIn(boolean loggedIn);

    boolean isFirstTime();
    void setFirstTime(boolean isFirstTime);

    void setIsStudent(int isStudent);
    int isStudent();

    void setParticipantId(int id);
    int getParticipantId();

    void setName(String name);
    String getName();

    boolean isStepOneDone();
    void setStepOneDone(boolean isDone);

    boolean isStepTwoDone();
    void setStepTwoDone(boolean isDone);
}
