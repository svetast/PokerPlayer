package PokerPlayer;


public class PokerPlayer {
    private String username;
    private String email;
    private static String firstname;
    private String lastname;
    private static String city;
    private static String address;
    private static String phone;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public static String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public static String getCity() {
        return city;
    }

   public static String getAddress() {
        return address;
    }

    public static String getPhone() {
        return phone;
    }

    public PokerPlayer() {
        super();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PokerPlayer(
                       String username,
                       String email,
                       String firstname,
                       String lastname,
                       String city,
                       String address,
                       String phone) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokerPlayer)) return false;

        PokerPlayer that = (PokerPlayer) o;

        if (!getUsername().equals(that.getUsername())) return false;
        if (!getEmail().equals(that.getEmail())) return false;
        if (!getFirstname().equals(that.getFirstname())) return false;
        if (!getLastname().equals(that.getLastname())) return false;
        if (!getCity().equals(that.getCity())) return false;
        if (!getAddress().equals(that.getAddress())) return false;
        return getPhone().equals(that.getPhone());
    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getFirstname().hashCode();
        result = 31 * result + getLastname().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getPhone().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PokerPlayer{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static void main(String[] args) {
        PokerPlayer pp = new PokerPlayer(
                  "Ivan",
                  "ivan@gmail.com",
                  "Ivan",
                  "Ivanov",
                  "Ivanovo",
                  "Ivanova street, 11",
                  "1234567");
        System.out.println(pp);
    }
}

