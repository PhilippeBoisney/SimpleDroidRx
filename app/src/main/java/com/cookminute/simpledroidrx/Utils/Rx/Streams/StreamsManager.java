package com.cookminute.simpledroidrx.Utils.Rx.Streams;

import com.cookminute.simpledroidrx.Utils.Rx.Streams.Streams.StreamGithubMember;

/**
 * Created by Philippe on 20/08/16.
 */
public class StreamsManager {

    private StreamGithubMember streamGithubMember;

    public StreamsManager(StreamGithubMember streamGithubMember){
        this.streamGithubMember = streamGithubMember;
    }

    /**
     * Stream that handle Github features
     * @return
     */
    public StreamGithubMember githubMember(){ return this.streamGithubMember; }
}
