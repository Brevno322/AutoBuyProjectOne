package buy.autobuy.autobuyproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "client_info")
public class InfoClient {
    @Id()

    private int id;
    private String name;
    private String phone;
    private String surname;
    private String country;
    private String city;
    private String gender;
    private String age;
    public InfoClient(){};



    public InfoClient(int id, String name, String phone, String surname, String country, String city, String gender, String age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.surname = surname;
        this.country = country;
        this.city = city;
        this.gender = gender;
        this.age = age;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoClient that = (InfoClient) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(surname, that.surname) && Objects.equals(country, that.country) && Objects.equals(city, that.city) && Objects.equals(gender, that.gender) && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, surname, country, city, gender, age);
    }

    @Override
    public String toString() {
        return "InfoClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
