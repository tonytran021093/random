package tht.app.random.object;

public class TenRandom {
    private long id;
    private String name;

    public TenRandom(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
