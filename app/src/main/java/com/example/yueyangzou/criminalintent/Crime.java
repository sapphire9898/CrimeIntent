package com.example.yueyangzou.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by yueyangzou on 16/6/5.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;
    private long mcontactId;

    public UUID getUUID() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Crime() {
        this(UUID.randomUUID());
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public long getContactId() {
        return mcontactId;
    }

    public void setContactId(long contactId) {
        mcontactId = contactId;
    }

    public Crime(UUID id) {


        mId = id;
        mDate = new Date();
    }
}
