package com.consent.server.model;

import java.time.Instant;
import java.util.Arrays;

public class Action{

    private Instant instant;
    private String user_id;
    private byte[] photo;

    public Action() {

    }

    public Action(Instant instant, String user_id, byte[] photo) {
        this.instant = instant;
        this.user_id = user_id;
        this.photo = photo;
    }

    public Action(Instant instant, String user_id) {
        this.instant = instant;
        this.user_id = user_id;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public final boolean equals(Object o) {
        if(this == o) return true;
        if(! (o instanceof Action action)) return false;

        return instant.equals(action.instant) && user_id.equals(action.user_id) && Arrays.equals(photo, action.photo);
    }

    @Override
    public int hashCode() {
        int result = instant.hashCode();
        result = 31 * result + user_id.hashCode();
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
