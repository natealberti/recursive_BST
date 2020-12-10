package Program_7;

public interface Keyed {

    //returns 1 if other is greater, 0 if equal, and -1 if smaller
    int compare(Keyed other);

    //abbreviated toString that only returns object number
    String toStr();

}

class KeyedNum implements Keyed {

    private int _num;

    public KeyedNum(int num) {
        _num = num;
    }

    public int getNum() {
        return _num;
    }

    public int compare(Keyed other) {
        Integer num = _num;
        KeyedNum o = (KeyedNum) other;
        return num.compareTo(o.getNum());
    }

    public String toStr() {
        return "" + _num;
    }
}

class NFLPlayerKey implements Keyed {

    private int number;
    private String team;

    public NFLPlayerKey(int num, String team) {
        number = num;
        this.team = team;
    }

    public int getNumber() {
        return number;
    }

    public String getTeam() {
        return team;
    }

    //orders by team name then number
    //two instances are only the same if both are equal
    public int compare(Keyed other) {
        NFLPlayerKey o = (NFLPlayerKey) other;
            int compare = team.toLowerCase().compareTo(o.team.toLowerCase());
            if(compare==0) {
                Integer num = (Integer) number;
                compare = num.compareTo(o.number);
            }
            return compare;
    }

    public String toStr() {
        return "" + number + team.substring(0, 3);
    }

}

class NFLPlayer extends NFLPlayerKey {

    private String name;
    private double salary;

    public NFLPlayer(int num, String team, String name, double salary) {
        super(num, team);
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String toStr() {
        return getNumber() + " " + name.substring(0, 3);
    }

    public String toString() {
        return "#" + getNumber() + " " + getTeam().toUpperCase() + " " + getName() + "\n$" + getSalary();
    }

}
