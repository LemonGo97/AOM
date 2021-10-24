package cn.lemongo97.aom.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class GithubReleasesResponseDTO {

    @JsonProperty("url")
    private String url;
    @JsonProperty("assets_url")
    private String assetsUrl;
    @JsonProperty("upload_url")
    private String uploadUrl;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("author")
    private AuthorDTO author;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("tag_name")
    private String tagName;
    @JsonProperty("target_commitish")
    private String targetCommitish;
    @JsonProperty("name")
    private String name;
    @JsonProperty("draft")
    private Boolean draft;
    @JsonProperty("prerelease")
    private Boolean prerelease;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("published_at")
    private String publishedAt;
    @JsonProperty("assets")
    private List<?> assets;
    @JsonProperty("tarball_url")
    private String tarballUrl;
    @JsonProperty("zipball_url")
    private String zipballUrl;
    @JsonProperty("body")
    private String body;
    @JsonProperty("reactions")
    private ReactionsDTO reactions;

    @NoArgsConstructor
    @Data
    public static class AuthorDTO {
        @JsonProperty("login")
        private String login;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("url")
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        @JsonProperty("type")
        private String type;
        @JsonProperty("site_admin")
        private Boolean siteAdmin;
    }

    @NoArgsConstructor
    @Data
    public static class ReactionsDTO {
        @JsonProperty("url")
        private String url;
        @JsonProperty("total_count")
        private Integer totalCount;
        @JsonProperty("laugh")
        private Integer laugh;
        @JsonProperty("hooray")
        private Integer hooray;
        @JsonProperty("confused")
        private Integer confused;
        @JsonProperty("heart")
        private Integer heart;
        @JsonProperty("rocket")
        private Integer rocket;
        @JsonProperty("eyes")
        private Integer eyes;
    }
}
