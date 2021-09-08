package by.epamtc.poliukov.entity;

public class Cactus extends Flower {
    private static final String DEFAULT_ORIGIN = "Unknown";

    private String name;
    private String origin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        if(origin == null || origin.isEmpty()) {
            return DEFAULT_ORIGIN;
        } else {
            return origin;
        }
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cactus cactus = (Cactus) o;
        if (name == null) {
            if (cactus.name != null) {
                return false;
            }
        } else if (!name.equals(cactus.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (origin == null ? 0 : origin.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", name=");
        sb.append(this.getName());
        sb.append(", origin=");
        sb.append(this.getOrigin());
        sb.append("]");
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
