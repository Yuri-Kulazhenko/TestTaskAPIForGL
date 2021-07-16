package logic.common;

public enum RequestJsonFiles {
    AUTH_JSON ("src/main/resources/auth/AuthData.json"),
    REQUEST_TEMPLATE ("src/main/resources/auth/RequestBodyTemplate.json");


    private String filePath;

    RequestJsonFiles(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
