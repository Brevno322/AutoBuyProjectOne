package buy.autobuy.autobuyproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "auto")
public class Automobile {
    @Id
private int id;
private String type;
private String kpp;
private String colour;
private String marka;
private double engine;
public Automobile(){};

    public Automobile(int id, String type, String kpp, String colour, String marka, double engine) {
        this.id = id;
        this.type = type;
        this.kpp = kpp;
        this.colour = colour;
        this.marka = marka;
        this.engine = engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public double getEngine() {
        return engine;
    }

    public void setEngine(double engine) {
        this.engine = engine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automobile that = (Automobile) o;
        return id == that.id && Double.compare(that.engine, engine) == 0 && Objects.equals(type, that.type) && Objects.equals(kpp, that.kpp) && Objects.equals(colour, that.colour) && Objects.equals(marka, that.marka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, kpp, colour, marka, engine);
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "id=" + id +
                ", type=" + type +
                ", kpp=" + kpp +
                ", colour=" + colour +
                ", marka=" + marka +
                ", engine=" + engine +
                '}';
    }
}
