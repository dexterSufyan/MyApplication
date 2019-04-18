public class teamRating {
    String TeamId;
    String TeamName;
    float rating;

    public String getTeamId() {
        return TeamId;
    }

    public void setTeamId(String teamId) {
        TeamId = teamId;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public teamRating(String teamId, String teamName, float rating) {
        TeamId = teamId;
        TeamName = teamName;
        this.rating = rating;
    }
}
