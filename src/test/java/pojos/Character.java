package pojos;

public class Character {
    private String _id;
    private String name;
    private String role;
    private String house;
    private String school;
    private String boggart;
    private int __v;
    private boolean ministryOfMagic;
    private boolean orderOfThePhoenix;
    private boolean dumbledoresArmy;
    private boolean deathEater;
    private String bloodStatus;
    private String species;

    @Override
    public String toString() {

        return "Character{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", house='" + house + '\'' +
                ", school='" + school + '\'' +
                ", boggart='" + boggart + '\'' +
                ", __v=" + __v +
                ", ministryOfMagic=" + ministryOfMagic +
                ", orderOfThePhoenix=" + orderOfThePhoenix +
                ", dumbledoresArmy=" + dumbledoresArmy +
                ", deathEater=" + deathEater +
                ", bloodStatus='" + bloodStatus + '\'' +
                ", species='" + species + '\'' +
                '}';
    }

    public Character(String _id, String name, String role, String house, String school, String boggart, int __v, boolean ministryOfMagic, boolean orderOfThePhoenix, boolean dumbledoresArmy, boolean deathEater, String bloodStatus, String species) {
        this._id = _id;
        this.name = name;
        this.role = role;
        this.house = house;
        this.school = school;
        this.boggart = boggart;
        this.__v = __v;
        this.ministryOfMagic = ministryOfMagic;
        this.orderOfThePhoenix = orderOfThePhoenix;
        this.dumbledoresArmy = dumbledoresArmy;
        this.deathEater = deathEater;
        this.bloodStatus = bloodStatus;
        this.species = species;
    }
}
