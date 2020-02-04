package tests.testing;

import java.util.List;

public class AllSpartans {

    private List<Spartan> spartanList;

    public AllSpartans(List<Spartan> spartanList) {
        this.spartanList = spartanList;
    }

    public AllSpartans(){}

    @Override
    public String toString() {
        return "AllSpartans{" +
                "spartanList=" + spartanList +
                '}';
    }

    public List<Spartan> getSpartanList() {
        return spartanList;
    }

    public void setSpartanList(List<Spartan> spartanList) {
        this.spartanList = spartanList;
    }


}
