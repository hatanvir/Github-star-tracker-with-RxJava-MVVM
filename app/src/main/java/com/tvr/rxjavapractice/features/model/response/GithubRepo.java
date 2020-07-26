package com.tvr.rxjavapractice.features.model.response;

public class GithubRepo {
    String name;
    String htmlUrl;
    Owner owner;


    public GithubRepo(String name, String htmlUrl,Owner owner) {
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public Owner getowner() {
        return owner;
    }

    public class Owner{
        String avatarUrl;

        public Owner(String avaterUrl) {
            this.avatarUrl = avaterUrl;
        }

        public String getAvaterUrl() {
            return avatarUrl;
        }
    }
}
