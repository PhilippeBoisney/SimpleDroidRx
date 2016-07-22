package com.cookminute.simpledroidrx.API.Model;

import java.util.Locale;

/**
 * Created by Philippe on 21/07/16.
 */
public class GitHubMember {

    public String login;
    public int followers;

    @Override
    public String toString() {
        return String.format(Locale.US, "%s: %d followers\n", login, followers);
    }
}
