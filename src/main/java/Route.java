public class Route {

    private EMethods method;
    private String path;
    private String action;

    public Route(EMethods method, String path, String action) {
        this.method = method;
        this.path = path;
        this.action = action;
    }

    public EMethods getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getAction() {
        return action;
    }
}
