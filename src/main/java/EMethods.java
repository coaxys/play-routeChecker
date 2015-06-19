public enum EMethods {
    POST("POST"), GET("GET"), ALL("*");

    private String name;

    EMethods(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EMethods getByName(String name) {
        for (EMethods method : values()) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }
}
