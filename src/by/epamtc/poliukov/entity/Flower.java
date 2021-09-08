package by.epamtc.poliukov.entity;

import java.util.Objects;

public abstract class Flower {
    private String id;
    private String soil;
    private String colorStem;
    private String colorLeaf;
    private int averageFlowerSize;
    private int temperature;
    private boolean isPhotophilous;
    private int watering;
    private String multiplying;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getColorStem() {
        return colorStem;
    }

    public void setColorStem(String colorStem) {
        this.colorStem = colorStem;
    }

    public String getColorLeaf() {
        return colorLeaf;
    }

    public void setColorLeaf(String colorLeaf) {
        this.colorLeaf = colorLeaf;
    }

    public int getAverageFlowerSize() {
        return averageFlowerSize;
    }

    public void setAverageFlowerSize(int averageFlowerSize) {
        this.averageFlowerSize = averageFlowerSize;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isPhotophilous() {
        return isPhotophilous;
    }

    public void setPhotophilous(boolean photophilous) {
        isPhotophilous = photophilous;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }
    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getOrigin();

    public abstract void setOrigin(String origin);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        if (averageFlowerSize != flower.averageFlowerSize) {
            return false;
        }
        if (temperature != flower.temperature) {
            return false;
        }
        if (isPhotophilous != flower.isPhotophilous) {
            return false;
        }
        if (watering != flower.watering) {
            return  false;
        }
        if (id == null) {
            if (flower.id != null) {
                return false;
            }
        } else if (!id.equals(flower.id)) {
            return false;
        }
        if (soil == null) {
            if (flower.soil != null) {
                return false;
            }
        } else if (!soil.equals(flower.soil)) {
            return false;
        }
        if (colorStem == null) {
            if (flower.colorStem != null) {
                return false;
            }
        } else if (!colorStem.equals(flower.colorStem)) {
            return false;
        }
        if (colorLeaf == null) {
            if (flower.colorLeaf != null) {
                return false;
            }
        } else if (!colorLeaf.equals(flower.colorLeaf)) {
            return false;
        }
        if (multiplying == null) {
            if (flower.multiplying != null) {
                return false;
            }
        } else if (!multiplying.equals(flower.multiplying)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (soil == null ? 0 : soil.hashCode());
        result = prime * result + (colorStem == null ? 0 : colorStem.hashCode());
        result = prime * result + (colorLeaf == null ? 0 : colorLeaf.hashCode());
        result = prime * result + (multiplying == null ? 0 : multiplying.hashCode());
        result = prime * result + Integer.hashCode(averageFlowerSize);
        result = prime * result + Integer.hashCode(temperature);
        result = prime * result + Integer.hashCode(watering);
        result = prime * result + Boolean.hashCode(isPhotophilous);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(" [id=");
        builder.append(id);
        builder.append(", soil=");
        builder.append(soil);
        builder.append(", colorStem=");
        builder.append(colorStem);
        builder.append(", colorLeaf=");
        builder.append(colorLeaf);
        builder.append(", averageFlowerSize=");
        builder.append(averageFlowerSize);
        builder.append(", temperature=");
        builder.append(temperature);
        builder.append(", isPhotophilous=");
        builder.append(isPhotophilous ? "yes" : "no");
        builder.append(", watering=");
        builder.append(watering);
        builder.append(", multiplying=");
        builder.append(multiplying);
        builder.append("]");
        return builder.toString();
    }


}
