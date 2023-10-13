package model;

// Represents a player with his team, name, position, age in years, height in cm, weight in kg, and salary in millions
// US dollars.
public class Player {
    private String team;       // the team this player is in.
    private String name;       // the name of the player.
    private String position;   // the position of the player.
    private int age;           // the age of the player in years.
    private int height;        // the height of the player in cm.
    private double weight;     // the weight of the player in kg.
    private double salary;     // the salary of the player in millions US dollars.

    // Requires: team, name, position is not an empty string.
    //           age, height, weight, salary is greater than 0.
    // Effects: construct a player with team, name, position, age, height, weight, and salary.
    public Player(String team, String name, String position, int age, int height, double weight, double salary) {
        this.team = team;
        this.name = name;
        this.position = position;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.salary = salary;
    }

    // Requires: newTeam must not be the same as this.team.
    // Modifies: this
    // Effects: change the team of player to newTeam.
    public void changeTeam(String newTeam) {
        this.team = newTeam;
    }

    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getSalary() {
        return salary;
    }
}
